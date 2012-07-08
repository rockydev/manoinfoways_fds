package com.manoinfoways.ejb;

// Generated Apr 13, 2012 2:07:44 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

import com.manoinfoways.model.TranscriberDoctorPriority;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TranscriberDoctorPriority.
 * 
 * @see com.manoinfoways.model.TranscriberDoctorPriority
 * @author Hibernate Tools
 */
public class TranscriberDoctorPriorityBean {

	private static final Log log = LogFactory
			.getLog(TranscriberDoctorPriorityBean.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TranscriberDoctorPriority transientInstance) {
		log.debug("persisting TranscriberDoctorPriority instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TranscriberDoctorPriority instance) {
		log.debug("attaching dirty TranscriberDoctorPriority instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(TranscriberDoctorPriority instance) {
		log.debug("attaching clean TranscriberDoctorPriority instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TranscriberDoctorPriority persistentInstance) {
		log.debug("deleting TranscriberDoctorPriority instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TranscriberDoctorPriority merge(
			TranscriberDoctorPriority detachedInstance) {
		log.debug("merging TranscriberDoctorPriority instance");
		try {
			TranscriberDoctorPriority result = (TranscriberDoctorPriority) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TranscriberDoctorPriority findById(java.lang.Integer id) {
		log.debug("getting TranscriberDoctorPriority instance with id: " + id);
		try {
			TranscriberDoctorPriority instance = (TranscriberDoctorPriority) sessionFactory
					.getCurrentSession().get(
							"com.manoinfoways.model.TranscriberDoctorPriority",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TranscriberDoctorPriority> findByExample(
			TranscriberDoctorPriority instance) {
		log.debug("finding TranscriberDoctorPriority instance by example");
		try {
			List<TranscriberDoctorPriority> results = (List<TranscriberDoctorPriority>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.manoinfoways.model.TranscriberDoctorPriority")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getPriorityDoctors(int transcriberId,int transcriberTypeId,int priority)
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Integer> priorityDoctors = (List<Integer>)session.createCriteria(TranscriberDoctorPriority.class)
			.setProjection(Projections.property("doctorId"))
			.add(Restrictions.eq("transcriberId", new Integer(transcriberId)))
			.add(Restrictions.eq("transcriberTypeId", new Integer(transcriberTypeId)))
			.add(Restrictions.eq("priority", new Integer(priority)))
			.list();
		return priorityDoctors;
			
	}
}
