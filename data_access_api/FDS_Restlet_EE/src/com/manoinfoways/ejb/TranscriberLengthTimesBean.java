package com.manoinfoways.ejb;

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.manoinfoways.model.DoctorData;
import com.manoinfoways.model.HibernateUtil;
import com.manoinfoways.model.TranscriberLengthTimes;

/**
 * Bean to handle TranscriberLengthTimes table operations.
 * 
 * @see com.manoinfoways.model.TranscriberLengthTimes
 */
public class TranscriberLengthTimesBean {

	private static final Log log = LogFactory.getLog(TranscriberLengthTimesBean.class);

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

	public TranscriberLengthTimes persist(TranscriberLengthTimes transientInstance) {
		log.debug("persisting TranscriberLengthTimes instance");
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

	public void attachDirty(TranscriberLengthTimes instance) {
		log.debug("attaching dirty TranscriberLengthTimes instance");
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
	public void attachClean(TranscriberLengthTimes instance) {
		log.debug("attaching clean TranscriberLengthTimes instance");
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

	public TranscriberLengthTimes delete(TranscriberLengthTimes persistentInstance) {
		log.debug("deleting TranscriberLengthTimes instance");
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

	public TranscriberLengthTimes merge(TranscriberLengthTimes detachedInstance) {
		log.debug("merging TranscriberLengthTimes instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			TranscriberLengthTimes result = (TranscriberLengthTimes) session
					.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TranscriberLengthTimes findById(java.lang.Integer id) {
		log.debug("getting TranscriberLengthTimes instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			TranscriberLengthTimes instance = (TranscriberLengthTimes) session.get(
					"com.manoinfoways.model.TranscriberLengthTimes", id);
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
	public List<TranscriberLengthTimes> findByExample(TranscriberLengthTimes instance) {
		log.debug("finding TranscriberLengthTimes instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<TranscriberLengthTimes> results = (List<TranscriberLengthTimes>) session
					.createCriteria("com.manoinfoways.model.TranscriberLengthTimes")
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

	public TranscriberLengthTimes update(TranscriberLengthTimes transcriberData) {
		log.debug("Updating DoctorData instance");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(transcriberData);
			log.debug("Update successful");
			session.getTransaction().commit();
			return transcriberData;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public TranscriberLengthTimes deleteTranscriberLengthTimesById(int transcriberId) {
		return delete(findById(transcriberId));
	}

	@SuppressWarnings("unchecked")
	public List<TranscriberLengthTimes> getTranscriberLengths(int transcriberId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<TranscriberLengthTimes> results = (List<TranscriberLengthTimes>) session
					.createCriteria(TranscriberLengthTimes.class)
					.add(Restrictions.eq("transcriberdata.transcriberId", transcriberId))
					.list();
			
			for (TranscriberLengthTimes transcriberLengths : results)
			{
				Hibernate.initialize(transcriberLengths.getTranscriberdata());
				Hibernate.initialize(transcriberLengths.getTranscribertypedata());
			}
			
			session.getTransaction().commit();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
}
