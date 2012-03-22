package com.manoinfoways.ejb;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.manoinfoways.model.ClinicMetadata;
import com.manoinfoways.model.HibernateUtil;

/**
 * Bean for ClinicMetadata table operations
 * 
 * @see com.manoinfoways.model.ClinicMetadata
 */
public class ClinicMetadataBean {

	private static final Log log = LogFactory.getLog(ClinicMetadataBean.class);

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

	public void persist(ClinicMetadata transientInstance) {
		log.debug("persisting ClinicMetadata instance");
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

	public void attachDirty(ClinicMetadata instance) {
		log.debug("attaching dirty ClinicMetadata instance");
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
	public void attachClean(ClinicMetadata instance) {
		log.debug("attaching clean ClinicMetadata instance");
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

	public void delete(ClinicMetadata persistentInstance) {
		log.debug("deleting ClinicMetadata instance");
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

	public ClinicMetadata merge(ClinicMetadata detachedInstance) {
		log.debug("merging ClinicMetadata instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			ClinicMetadata result = (ClinicMetadata) session
					.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ClinicMetadata findById(java.lang.Integer id) {
		log.debug("getting ClinicMetadata instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			ClinicMetadata instance = (ClinicMetadata) session.get(
					"com.manoinfoways.model.ClinicMetadata", id);
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
	public List<ClinicMetadata> findByExample(ClinicMetadata instance) {
		log.debug("finding ClinicMetadata instance by example");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<ClinicMetadata> results = (List<ClinicMetadata>) session
					.createCriteria("com.manoinfoways.model.ClinicMetadata")
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

	public void update(ClinicMetadata clinicMetadata) {
		merge(clinicMetadata);
	}

	public void deleteClinicMetadataById(int clinicMetadataId) {
		ClinicMetadata clinicMetadata = new ClinicMetadata();
		clinicMetadata.setMetaDataId(clinicMetadataId);
		delete(clinicMetadata);
	}
}
