package com.manoinfoways.ejb;

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.manoinfoways.model.DoctorData;
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

	public TranscriberData persist(TranscriberData transientInstance) {
		log.debug("persisting TranscriberData instance");
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

	public TranscriberData delete(TranscriberData persistentInstance) {
		log.debug("deleting TranscriberData instance");
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

	public TranscriberData update(TranscriberData transcriberData) {
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

	public TranscriberData deleteTranscriberDataById(int transcriberId) {
		return delete(findById(transcriberId));
	}

	@SuppressWarnings("unchecked")
	public List<TranscriberData> getAllTranscribers() {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<TranscriberData> results = (List<TranscriberData>) session
					.createCriteria(TranscriberData.class).list();

			session.getTransaction().commit();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TranscriberData> getAllUserNames() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		List<TranscriberData> transcriberUserNames = session
				.createCriteria(TranscriberData.class)
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.alias(
										Projections.property("transcriberId"),
										"transcriberId"))
								.add(Projections.alias(
										Projections.property("userName"),
										"userName"))
								.add(Projections.alias(Projections
										.property("transcriberTypeId"),
										"transcriberTypeId")))
				.setResultTransformer(
						Transformers.aliasToBean(TranscriberData.class)).list();

		session.getTransaction().commit();
		return transcriberUserNames;
	}

	@SuppressWarnings("unchecked")
	public List<TranscriberData> getTranscribersByTranscriberTypeId(
			Integer transcriberTypeId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(TranscriberData.class);
		ArrayList<Integer> transcriberTypeIds = new ArrayList<Integer>();
		switch (transcriberTypeId) {
		case 1:
			// {1,3,5,7,9,11,13,15};
			transcriberTypeIds.add(1);
			transcriberTypeIds.add(3);
			transcriberTypeIds.add(5);
			transcriberTypeIds.add(7);
			transcriberTypeIds.add(9);
			transcriberTypeIds.add(11);
			transcriberTypeIds.add(13);
			transcriberTypeIds.add(15);
			break;
		case 2:
			// { 2, 3, 6, 7, 10, 11, 14, 15 };
			transcriberTypeIds.add(2);
			transcriberTypeIds.add(3);
			transcriberTypeIds.add(6);
			transcriberTypeIds.add(7);
			transcriberTypeIds.add(10);
			transcriberTypeIds.add(11);
			transcriberTypeIds.add(14);
			transcriberTypeIds.add(15);
			break;
		case 4:
			// { 4, 5, 6, 7, 12, 13, 14, 15 };
			transcriberTypeIds.add(4);
			transcriberTypeIds.add(5);
			transcriberTypeIds.add(6);
			transcriberTypeIds.add(7);
			transcriberTypeIds.add(12);
			transcriberTypeIds.add(13);
			transcriberTypeIds.add(14);
			transcriberTypeIds.add(15);
			break;
		case 8:
			// { 8, 9, 10, 11, 12, 13, 14, 15 };
			transcriberTypeIds.add(8);
			transcriberTypeIds.add(9);
			transcriberTypeIds.add(10);
			transcriberTypeIds.add(11);
			transcriberTypeIds.add(12);
			transcriberTypeIds.add(13);
			transcriberTypeIds.add(14);
			transcriberTypeIds.add(15);
			break;
		}
		
		criteria.add(Restrictions.in("transcriberTypeId", transcriberTypeIds));
		
		List<TranscriberData> transcriberList = criteria.addOrder(Order.asc("transcriberId")).list();
		session.getTransaction().commit();
		return transcriberList;
	}
}
