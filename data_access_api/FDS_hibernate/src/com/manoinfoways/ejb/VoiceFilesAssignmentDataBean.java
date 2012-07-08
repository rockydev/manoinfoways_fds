package com.manoinfoways.ejb;

// Generated Mar 30, 2012 7:20:31 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.manoinfoways.model.VoiceFilesAssignmentData;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class VoiceFilesAssignmentData.
 * @see com.manoinfoways.model.VoiceFilesAssignmentData
 * @author Hibernate Tools
 */
public class VoiceFilesAssignmentDataBean {

	private static final Log log = LogFactory
			.getLog(VoiceFilesAssignmentDataBean.class);

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

	public void persist(VoiceFilesAssignmentData transientInstance) {
		log.debug("persisting VoiceFilesAssignmentData instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VoiceFilesAssignmentData instance) {
		log.debug("attaching dirty VoiceFilesAssignmentData instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(VoiceFilesAssignmentData instance) {
		log.debug("attaching clean VoiceFilesAssignmentData instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VoiceFilesAssignmentData persistentInstance) {
		log.debug("deleting VoiceFilesAssignmentData instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VoiceFilesAssignmentData merge(
			VoiceFilesAssignmentData detachedInstance) {
		log.debug("merging VoiceFilesAssignmentData instance");
		try {
			VoiceFilesAssignmentData result = (VoiceFilesAssignmentData) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VoiceFilesAssignmentData findById(java.lang.Integer id) {
		log.debug("getting VoiceFilesAssignmentData instance with id: " + id);
		try {
			VoiceFilesAssignmentData instance = (VoiceFilesAssignmentData) sessionFactory
					.getCurrentSession().get(
							"com.manoinfoways.model.VoiceFilesAssignmentData",
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
	public List<VoiceFilesAssignmentData> findByExample(
			VoiceFilesAssignmentData instance) {
		log.debug("finding VoiceFilesAssignmentData instance by example");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<VoiceFilesAssignmentData> results = (List<VoiceFilesAssignmentData>) session
					.createCriteria(
							"com.manoinfoways.model.VoiceFilesAssignmentData")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			session.getTransaction().commit();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}
	
}
