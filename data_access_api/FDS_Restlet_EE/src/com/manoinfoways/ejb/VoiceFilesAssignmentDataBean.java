package com.manoinfoways.ejb;

// Generated Mar 30, 2012 7:20:31 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manoinfoways.model.HibernateUtil;
import com.manoinfoways.model.VoiceFilesAssignmentData;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class VoiceFilesAssignmentData.
 * 
 * @see com.manoinfoways.model.VoiceFilesAssignmentData
 * @author Hibernate Tools
 */
public class VoiceFilesAssignmentDataBean {

	private static final Log log = LogFactory
			.getLog(VoiceFilesAssignmentDataBean.class);

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

	public void persist(VoiceFilesAssignmentData transientInstance) {
		log.debug("persisting VoiceFilesAssignmentData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.persist(transientInstance);
			log.debug("persist successful");
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw re;
		}
	}

	public VoiceFilesAssignmentData attachDirty(VoiceFilesAssignmentData instance) {
		log.debug("attaching dirty VoiceFilesAssignmentData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(instance);
			log.debug("attach successful");
			session.getTransaction().commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(VoiceFilesAssignmentData instance) {
		log.debug("attaching clean VoiceFilesAssignmentData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw re;
		}
	}

	public VoiceFilesAssignmentData delete(VoiceFilesAssignmentData persistentInstance) {
		log.debug("deleting VoiceFilesAssignmentData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.delete(persistentInstance);
			log.debug("delete successful");
			return persistentInstance;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw re;
		}
	}

	public VoiceFilesAssignmentData merge(
			VoiceFilesAssignmentData detachedInstance) {
		log.debug("merging VoiceFilesAssignmentData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			VoiceFilesAssignmentData result = (VoiceFilesAssignmentData) session
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			sessionFactory.getCurrentSession().getTransaction().rollback();
			throw re;
		}
	}

	public VoiceFilesAssignmentData findById(java.lang.Integer id) {
		log.debug("getting VoiceFilesAssignmentData instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			VoiceFilesAssignmentData instance = (VoiceFilesAssignmentData) session
					.get("com.manoinfoways.model.VoiceFilesAssignmentData", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			sessionFactory.getCurrentSession().getTransaction().rollback();
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

	@SuppressWarnings("unchecked")
	public List<VoiceFilesAssignmentData> getVoiceFileAssignments() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session
				.createCriteria(VoiceFilesAssignmentData.class);
		
		List<VoiceFilesAssignmentData> results = criteria
				.addOrder(Order.desc("fileAssignmentId")).list();
		
		session.getTransaction().commit();
		return results;
	}

	public VoiceFilesAssignmentData update(
			VoiceFilesAssignmentData fileAssignment) {
		
		return attachDirty(fileAssignment);
	}

	public VoiceFilesAssignmentData deleteVoiceFilesAssignmentDataById(
			Integer fileAssignmentId) {
		return delete(findById(fileAssignmentId));
	}
}
