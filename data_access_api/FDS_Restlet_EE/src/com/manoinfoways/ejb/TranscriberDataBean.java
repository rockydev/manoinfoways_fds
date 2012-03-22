package com.manoinfoways.ejb;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.manoinfoways.model.HibernateUtil;
import com.manoinfoways.model.TranscriberData;

/**
 * Bean to handle TranscriberData table operations.
 * 
 * @see com.manoinfoways.model.TranscriberData
 */
public class TranscriberDataBean {

	private static final Log log = LogFactory.getLog(TranscriberDataBean.class);

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

	public void persist(TranscriberData transientInstance) {
		log.debug("persisting TranscriberData instance");
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

	public void attachDirty(TranscriberData instance) {
		log.debug("attaching dirty TranscriberData instance");
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
	public void attachClean(TranscriberData instance) {
		log.debug("attaching clean TranscriberData instance");
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

	public void delete(TranscriberData persistentInstance) {
		log.debug("deleting TranscriberData instance");
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

	public TranscriberData merge(TranscriberData detachedInstance) {
		log.debug("merging TranscriberData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			TranscriberData result = (TranscriberData) session
					.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TranscriberData findById(java.lang.Integer id) {
		log.debug("getting TranscriberData instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			TranscriberData instance = (TranscriberData) session.get(
					"com.manoinfoways.model.TranscriberData", id);
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
	public List<TranscriberData> findByExample(TranscriberData instance) {
		log.debug("finding TranscriberData instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<TranscriberData> results = (List<TranscriberData>) session
					.createCriteria("com.manoinfoways.model.TranscriberData")
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

	public void update(TranscriberData transcriberData) {
		merge(transcriberData);
	}

	public void deleteClinicDataById(int transcriberId) {
		TranscriberData transcriberData = new TranscriberData();
		transcriberData.setTranscriberId(transcriberId);
		delete(transcriberData);
	}
}
