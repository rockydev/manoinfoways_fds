package com.manoinfoways.ejb;

// Generated Mar 30, 2012 7:20:31 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import com.manoinfoways.model.FileTransactionData;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class FileTransactionData.
 * @see com.manoinfoways.model.FileTransactionData
 * @author Hibernate Tools
 */
public class FileTransactionDataBean {

	private static final Log log = LogFactory
			.getLog(FileTransactionDataBean.class);

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

	public void persist(FileTransactionData transientInstance) {
		log.debug("persisting FileTransactionData instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(FileTransactionData instance) {
		log.debug("attaching dirty FileTransactionData instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(FileTransactionData instance) {
		log.debug("attaching clean FileTransactionData instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(FileTransactionData persistentInstance) {
		log.debug("deleting FileTransactionData instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FileTransactionData merge(FileTransactionData detachedInstance) {
		log.debug("merging FileTransactionData instance");
		try {
			FileTransactionData result = (FileTransactionData) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FileTransactionData findById(java.lang.Integer id) {
		log.debug("getting FileTransactionData instance with id: " + id);
		try {
			FileTransactionData instance = (FileTransactionData) sessionFactory
					.getCurrentSession().get(
							"com.manoinfoways.model.FileTransactionData", id);
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
	public List<FileTransactionData> findByExample(FileTransactionData instance) {
		log.debug("finding FileTransactionData instance by example");
		try {
			List<FileTransactionData> results = (List<FileTransactionData>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.manoinfoways.model.FileTransactionData")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
