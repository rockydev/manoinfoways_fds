package com.manoinfoways.ejb;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.manoinfoways.model.HibernateUtil;
import com.manoinfoways.model.InboundVoiceFilesData;

/**
 * Bean for handling InboundVoiceFilesData table operations.
 * 
 * @see com.manoinfoways.model.InboundVoiceFilesData
 */
public class InboundVoiceFilesDataBean {

	private static final Log log = LogFactory.getLog(InboundVoiceFilesDataBean.class);

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

	public void persist(InboundVoiceFilesData transientInstance) {
		log.debug("persisting InboundVoiceFilesData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InboundVoiceFilesData instance) {
		log.debug("attaching dirty InboundVoiceFilesData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(InboundVoiceFilesData instance) {
		log.debug("attaching clean InboundVoiceFilesData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InboundVoiceFilesData persistentInstance) {
		log.debug("deleting InboundVoiceFilesData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InboundVoiceFilesData merge(InboundVoiceFilesData detachedInstance) {
		log.debug("merging InboundVoiceFilesData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			InboundVoiceFilesData result = (InboundVoiceFilesData) session
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InboundVoiceFilesData findById(java.lang.Integer id) {
		log.debug("getting InboundVoiceFilesData instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			InboundVoiceFilesData instance = (InboundVoiceFilesData) session.get(
					"com.manoinfoways.model.InboundVoiceFilesData", id);
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
	public List<InboundVoiceFilesData> findByExample(InboundVoiceFilesData instance) {
		log.debug("finding InboundVoiceFilesData instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<InboundVoiceFilesData> results = (List<InboundVoiceFilesData>) session
					.createCriteria("com.manoinfoways.model.InboundVoiceFilesData")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public void update(InboundVoiceFilesData voiceFilesData) {
		merge(voiceFilesData);
	}

	public void deleteClinicDataById(int fileId) {
		InboundVoiceFilesData voiceFilesData = new InboundVoiceFilesData();
		voiceFilesData.setFileId(fileId);
		delete(voiceFilesData);
	}
}
