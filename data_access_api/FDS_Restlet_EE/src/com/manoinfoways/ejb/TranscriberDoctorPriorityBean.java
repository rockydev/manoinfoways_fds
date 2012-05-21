package com.manoinfoways.ejb;

// Generated Apr 13, 2012 2:07:44 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;

import com.manoinfoways.model.DoctorData;
import com.manoinfoways.model.HibernateUtil;
import com.manoinfoways.model.TranscriberData;
import com.manoinfoways.model.TranscriberDoctorPriority;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TranscriberDoctorPriority.
 * 
 * @see com.manoinfoways.model.TranscriberDoctorPriority
 * @author Hibernate Tools
 */
public class TranscriberDoctorPriorityBean {

	private static final Log log = LogFactory
			.getLog(TranscriberDoctorPriorityBean.class);

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

	public TranscriberDoctorPriority persist(
			TranscriberDoctorPriority transientInstance) {
		log.debug("persisting TranscriberDoctorPriority instance");
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

	public TranscriberDoctorPriority attachDirty(
			TranscriberDoctorPriority instance) {
		log.debug("attaching dirty TranscriberDoctorPriority instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(instance);
			log.debug("attach successful");
			session.getTransaction().commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(TranscriberDoctorPriority instance) {
		log.debug("attaching clean TranscriberDoctorPriority instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public TranscriberDoctorPriority delete(
			TranscriberDoctorPriority persistentInstance) {
		log.debug("deleting TranscriberDoctorPriority instance");
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

	public TranscriberDoctorPriority merge(
			TranscriberDoctorPriority detachedInstance) {
		log.debug("merging TranscriberDoctorPriority instance");
		try {
			TranscriberDoctorPriority result = (TranscriberDoctorPriority) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TranscriberDoctorPriority findById(java.lang.Integer id) {
		log.debug("getting TranscriberDoctorPriority instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			TranscriberDoctorPriority instance = (TranscriberDoctorPriority) session
					.get("com.manoinfoways.model.TranscriberDoctorPriority", id);
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
	public List<TranscriberDoctorPriority> findByExample(
			TranscriberDoctorPriority instance) {
		log.debug("finding TranscriberDoctorPriority instance by example");
		try {
			List<TranscriberDoctorPriority> results = (List<TranscriberDoctorPriority>) sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.manoinfoways.model.TranscriberDoctorPriority")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public TranscriberDoctorPriority deleteByPriorityId(Integer priorityId) {
		return delete(findById(priorityId));
	}

	@SuppressWarnings("unchecked")
	public List<TranscriberDoctorPriority> getTranscriberPriorities(
			Integer transcriberId, Integer transcriberTypeId, Integer priority) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session
				.createCriteria(TranscriberDoctorPriority.class);
		criteria.add(Restrictions.eq("transcriberdata.transcriberId",
				new Integer(transcriberId)));
		criteria.setFetchMode("transcriberdata", FetchMode.JOIN);
		criteria.setFetchMode("transcribertypedata", FetchMode.JOIN);
		criteria.setFetchMode("doctordata", FetchMode.JOIN);

		if (transcriberTypeId != null) {
			criteria.add(Restrictions.eq("transcriberTypeId", new Integer(
					transcriberTypeId)));
		}

		if (priority != null) {
			criteria.add(Restrictions.eq("priority", new Integer(priority)));
		}

		criteria.addOrder(Order.asc("priority"));
		List<TranscriberDoctorPriority> priorities = criteria.list();
		session.getTransaction().commit();
		return priorities;
	}

	@SuppressWarnings("unchecked")
	public List<DoctorData> getTranscriberPriorityDoctors(
			Integer transcriberId, Integer transcriberTypeId, Integer priority) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session
				.createCriteria(TranscriberDoctorPriority.class);
		criteria.add(Restrictions.eq("transcriberdata.transcriberId",
				new Integer(transcriberId)));
		criteria.setFetchMode("transcriberdata", FetchMode.JOIN);
		criteria.setFetchMode("transcribertypedata", FetchMode.JOIN);
		criteria.setFetchMode("doctordata", FetchMode.JOIN);

		if (transcriberTypeId != null) {
			criteria.add(Restrictions.eq("transcribertypedata.transcriberTypeId", new Integer(
					transcriberTypeId)));
		}

		if (priority != null) {
			criteria.add(Restrictions.eq("priority", new Integer(priority)));
		}
		criteria.setProjection(Projections.projectionList()
				.add(Projections.alias(Projections.property("doctordata"),"doctordata")));
//				.add(Projections.alias(Projections.property("doctordata.doctorAbbr"),"doctorAbbr"))
//				).setResultTransformer(Transformers.aliasToBean(DoctorData.class));
		criteria.addOrder(Order.asc("priority"));
		List<DoctorData> priorities = criteria.list();
		for (DoctorData doctor : priorities)
		{
			Hibernate.initialize(doctor);
		}
		session.getTransaction().commit();
		return priorities;
	}

	public int getPriorityCount(Integer transcriberId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Integer distinctPriorityCount =(Integer) session.createCriteria(TranscriberDoctorPriority.class)
			.add(Restrictions.eq("transcriberdata.transcriberId",transcriberId))
			.setProjection(Projections.projectionList()
					.add(Projections.max("priority")))
			.list().get(0);
		session.getTransaction().commit();
		return distinctPriorityCount;
	}
	
	public int getPriorityCount() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Integer distinctPriorityCount =session.createCriteria(TranscriberDoctorPriority.class)
			.setProjection(Projections.projectionList()
					.add(Projections.distinct(Projections.property("priority"))))
			.list().size();
		session.getTransaction().commit();
		return distinctPriorityCount;
	}

}
