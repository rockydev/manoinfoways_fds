package com.manoinfoways.ejb;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.manoinfoways.model.ClinicRequirements;
import com.manoinfoways.model.HibernateUtil;

/**
 * Bean to handle ClinicRequirements table operations.
 * 
 * @see com.manoinfoways.model.ClinicRequirements
 */
public class ClinicRequirementsBean {

	private static final Log log = LogFactory
			.getLog(ClinicRequirementsBean.class);

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

	public void persist(ClinicRequirements transientInstance) {
		log.debug("persisting ClinicRequirements instance");
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

	public void attachDirty(ClinicRequirements instance) {
		log.debug("attaching dirty ClinicRequirements instance");
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
	public void attachClean(ClinicRequirements instance) {
		log.debug("attaching clean ClinicRequirements instance");
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

	public void delete(ClinicRequirements persistentInstance) {
		log.debug("deleting ClinicRequirements instance");
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

	public ClinicRequirements merge(ClinicRequirements detachedInstance) {
		log.debug("merging ClinicRequirements instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			ClinicRequirements result = (ClinicRequirements) session
					.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ClinicRequirements findById(java.lang.Integer id) {
		log.debug("getting ClinicRequirements instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			ClinicRequirements instance = (ClinicRequirements) session.get(
					"com.manoinfoways.model.ClinicRequirements", id);
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
	public List<ClinicRequirements> findByExample(ClinicRequirements instance) {
		log.debug("finding ClinicRequirements instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<ClinicRequirements> results = (List<ClinicRequirements>) session
					.createCriteria("com.manoinfoways.model.ClinicRequirements")
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

	public void update(ClinicRequirements clinicRequirements) {
		merge(clinicRequirements);
	}

	public void deleteClinicConnectionDetailsById(int clinicRequirementsId) {
		ClinicRequirements clincRequirements = new ClinicRequirements();
		clincRequirements.setClinicRequirementId(clinicRequirementsId);
		delete(clincRequirements);
	}
}
