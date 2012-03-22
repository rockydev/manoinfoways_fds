package com.manoinfoways.ejb;

import static org.hibernate.criterion.Example.create;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.manoinfoways.model.HibernateUtil;
import com.manoinfoways.model.TranscriberTypeData;

/**
 * Bean for handling TranscriberTypeData table operations.
 * 
 * @see com.manoinfoways.model.TranscriberTypeData
 */
public class TranscriberTypeDataBean {

	private static final Log log = LogFactory
			.getLog(TranscriberTypeDataBean.class);

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

	public void persist(TranscriberTypeData transientInstance) {
		log.debug("persisting TranscriberTypeData instance");
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

	public void attachDirty(TranscriberTypeData instance) {
		log.debug("attaching dirty TranscriberTypeData instance");
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
	public void attachClean(TranscriberTypeData instance) {
		log.debug("attaching clean TranscriberTypeData instance");
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

	public void delete(TranscriberTypeData persistentInstance) {
		log.debug("deleting TranscriberTypeData instance");
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

	public TranscriberTypeData merge(TranscriberTypeData detachedInstance) {
		log.debug("merging TranscriberTypeData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			TranscriberTypeData result = (TranscriberTypeData) session
					.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TranscriberTypeData findById(java.lang.Integer id) {
		log.debug("getting TranscriberTypeData instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			TranscriberTypeData instance = (TranscriberTypeData) session.get(
					"com.manoinfoways.model.TranscriberTypeData", id);
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
	public List<TranscriberTypeData> findByExample(TranscriberTypeData instance) {
		log.debug("finding TranscriberTypeData instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<TranscriberTypeData> results = (List<TranscriberTypeData>) session
					.createCriteria(
							"com.manoinfoways.model.TranscriberTypeData")
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

	public void update(TranscriberTypeData transcriberTypeData) {
		merge(transcriberTypeData);
	}

	public void deleteClinicDataById(int transcriberTypeId) {
		TranscriberTypeData transcriberTypeData = new TranscriberTypeData();
		transcriberTypeData.setTranscriberTypeId(transcriberTypeId);
		delete(transcriberTypeData);
	}
}
