package com.manoinfoways.ejb;

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.manoinfoways.model.ClinicConnectionDetails;
import com.manoinfoways.model.ClinicData;
import com.manoinfoways.model.HibernateUtil;

/**
 * Bean for handling ClinicConnectionDetails table operations.
 * 
 * @see com.manoinfoways.model.ClinicConnectionDetails
 */
public class ClinicConnectionDetailsBean {

	private static final Log log = LogFactory
			.getLog(ClinicConnectionDetailsBean.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public ClinicConnectionDetails persist(ClinicConnectionDetails transientInstance) {
		log.debug("persisting ClinicConnectionDetails instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.persist(transientInstance);
			log.debug("persist successful");
			session.getTransaction().commit();
			return transientInstance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ClinicConnectionDetails instance) {
		log.debug("attaching dirty ClinicConnectionDetails instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(instance);
			log.debug("attach successful");
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(ClinicConnectionDetails instance) {
		log.debug("attaching clean ClinicConnectionDetails instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public ClinicConnectionDetails delete(ClinicConnectionDetails persistentInstance) {
		log.debug("deleting ClinicConnectionDetails instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.delete(persistentInstance);
			log.debug("delete successful");
			session.getTransaction().commit();
			return persistentInstance;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ClinicConnectionDetails merge(
			ClinicConnectionDetails detachedInstance) {
		log.debug("merging ClinicConnectionDetails instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			ClinicConnectionDetails result = (ClinicConnectionDetails) session
					.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ClinicConnectionDetails findById(java.lang.Integer id) {
		log.debug("getting ClinicConnectionDetails instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			ClinicConnectionDetails instance = (ClinicConnectionDetails) session
					.get("com.manoinfoways.model.ClinicConnectionDetails", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			session.getTransaction().commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ClinicConnectionDetails> findByExample(
			ClinicConnectionDetails instance) {
		log.debug("finding ClinicConnectionDetails instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<ClinicConnectionDetails> results = (List<ClinicConnectionDetails>) session
					.createCriteria(
							"com.manoinfoways.model.ClinicConnectionDetails")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			session.getTransaction().commit();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public void update(ClinicConnectionDetails clinicConnectionDetails) {
		merge(clinicConnectionDetails);
	}

	public void deleteClinicConnectionDetailsById(int clinicConnectionDetailsId) {
		ClinicConnectionDetails clinicConnectionDetails = new ClinicConnectionDetails();
		clinicConnectionDetails
				.setClinicConnectionId(clinicConnectionDetailsId);
		delete(clinicConnectionDetails);
	}
	
	public List<ClinicConnectionDetails> findByClinicId(java.lang.Integer clinicId)
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Set<ClinicConnectionDetails> result = ((ClinicData) session
				.createCriteria(ClinicData.class)
				.add(Restrictions.idEq(clinicId))
				.uniqueResult()).getClinicconnectiondetailses();
		
		ArrayList<ClinicConnectionDetails> connDetailsList = new ArrayList<ClinicConnectionDetails>();
		
		Iterator<ClinicConnectionDetails> setIter = result.iterator();
		while(setIter.hasNext())
		{
			connDetailsList.add(setIter.next());
		}
		
		session.getTransaction().commit();
		return connDetailsList;
	}
}
