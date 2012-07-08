package com.manoinfoways.ejb;

import java.util.Collection;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.manoinfoways.model.ClinicData;
import com.manoinfoways.model.DoctorData;
import com.manoinfoways.model.HibernateUtil;

import static org.hibernate.criterion.Example.create;

/**
 * Bean for handling clinicdata table operations
 * 
 * @see com.manoinfoways.model.ClinicData
 */
public class ClinicDataBean {

	private static final Log log = LogFactory.getLog(ClinicDataBean.class);

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

	public void persist(ClinicData transientInstance) {
		log.debug("persisting ClinicData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.persist(transientInstance);
			log.debug("persist successful");
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ClinicData instance) {
		log.debug("attaching dirty ClinicData instance");
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
	public void attachClean(ClinicData instance) {
		log.debug("attaching clean ClinicData instance");
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

	public void delete(ClinicData persistentInstance) {
		log.debug("deleting ClinicData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.delete(persistentInstance);
			log.debug("delete successful");
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ClinicData merge(ClinicData detachedInstance) {
		log.debug("merging ClinicData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			ClinicData result = (ClinicData) session.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ClinicData findById(java.lang.Integer id) {
		log.debug("getting ClinicData instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			ClinicData instance = (ClinicData) session.get(
					"com.manoinfoways.model.ClinicData", id);
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
	public List<ClinicData> findByExample(ClinicData instance) {
		log.debug("finding ClinicData instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<ClinicData> results = (List<ClinicData>) session
					.createCriteria("com.manoinfoways.model.ClinicData")
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

	@SuppressWarnings("unchecked")
	public Collection<ClinicData> getAllClinicData() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from ClinicData");
		Collection<ClinicData> list = query.list();
		session.getTransaction().commit();
		return list;
	}

	public void update(ClinicData clinicData) {
		merge(clinicData);
	}

	public void deleteClinicDataById(String clinicId) {
		ClinicData clinicData = new ClinicData(clinicId);
		delete(clinicData);
	}
	
	public Collection<ClinicData> getClinicAbbrs()
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		Collection<ClinicData> clinicAbbrs = session.createQuery( 
				"select new ClinicData(clinicId, clinicAbbr) from ClinicData as clinicData order by clinicData.clinicAbbr")
				.list();
		
		session.getTransaction().commit();
		return clinicAbbrs;
	}
	
	public Integer getClinicIdByAbbr(String clinicAbbr)
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Integer clinicId = (Integer) session.createCriteria(ClinicData.class)
		.setProjection(Projections.projectionList()
				.add(Projections.property("clinicId")))
		.add(Restrictions.eq("clinicAbbr", clinicAbbr))
		.uniqueResult();
		
		session.getTransaction().commit();
		return clinicId;
		
	}
}
