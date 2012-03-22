package com.manoinfoways.ejb;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.manoinfoways.model.HibernateUtil;
import com.manoinfoways.model.VoiceFilesData;

/**
 * Bean for handling VoiceFilesData table operations.
 * 
 * @see com.manoinfoways.model.VoiceFilesData
 */
public class VoiceFilesDataBean {

	private static final Log log = LogFactory.getLog(VoiceFilesDataBean.class);

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

	public void persist(VoiceFilesData transientInstance) {
		log.debug("persisting VoiceFilesData instance");
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

	public void attachDirty(VoiceFilesData instance) {
		log.debug("attaching dirty VoiceFilesData instance");
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
	public void attachClean(VoiceFilesData instance) {
		log.debug("attaching clean VoiceFilesData instance");
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

	public void delete(VoiceFilesData persistentInstance) {
		log.debug("deleting VoiceFilesData instance");
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

	public VoiceFilesData merge(VoiceFilesData detachedInstance) {
		log.debug("merging VoiceFilesData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			VoiceFilesData result = (VoiceFilesData) session
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VoiceFilesData findById(java.lang.Integer id) {
		log.debug("getting VoiceFilesData instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			VoiceFilesData instance = (VoiceFilesData) session.get(
					"com.manoinfoways.model.VoiceFilesData", id);
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
	public List<VoiceFilesData> findByExample(VoiceFilesData instance) {
		log.debug("finding VoiceFilesData instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<VoiceFilesData> results = (List<VoiceFilesData>) session
					.createCriteria("com.manoinfoways.model.VoiceFilesData")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public void update(VoiceFilesData voiceFilesData) {
		merge(voiceFilesData);
	}

	public void deleteClinicDataById(int fileId) {
		VoiceFilesData voiceFilesData = new VoiceFilesData();
		voiceFilesData.setFileId(fileId);
		delete(voiceFilesData);
	}
}
