package com.manoinfoways.ejb;

import static org.hibernate.criterion.Example.create;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.manoinfoways.model.DoctorData;
import com.manoinfoways.model.HibernateUtil;

/**
 * Bean to handle DoctorData table operations.
 * 
 * @see com.manoinfoways.model.DoctorData
 */
public class DoctorDataBean {

	private static final Log log = LogFactory.getLog(DoctorDataBean.class);

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

	public DoctorData persist(DoctorData transientInstance) {
		log.debug("persisting DoctorData instance");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.persist(transientInstance);
			log.debug("persist successful");
			session.getTransaction().commit();
			return transientInstance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public void attachDirty(DoctorData instance) {
		log.debug("attaching dirty DoctorData instance");
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
	public void attachClean(DoctorData instance) {
		log.debug("attaching clean DoctorData instance");
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

	public DoctorData delete(DoctorData persistentInstance) {
		log.debug("deleting DoctorData instance");
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

	public DoctorData merge(DoctorData detachedInstance) {
		log.debug("merging DoctorData instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			DoctorData result = (DoctorData) session.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DoctorData findById(java.lang.Integer id) {
		log.debug("getting DoctorData instance with id: " + id);
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			DoctorData instance = (DoctorData) session.get(
					"com.manoinfoways.model.DoctorData", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			session.getTransaction().commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<DoctorData> findByExample(DoctorData instance) {
		log.debug("finding DoctorData instance by example");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction(); session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<DoctorData> results = (List<DoctorData>) session
					.createCriteria("com.manoinfoways.model.DoctorData")
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

	public DoctorData update(DoctorData doctorData) {
		log.debug("Updating DoctorData instance");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(doctorData);
			log.debug("Update successful");
			session.getTransaction().commit();
			return doctorData;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public DoctorData deleteDoctorDataById(int doctorId) {
		return delete(findById(doctorId));
	}
	
	@SuppressWarnings("unchecked")
	public Collection<DoctorData> getClinicDoctors(int clinicId)
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Collection<DoctorData> doctorAbbrs = session.createCriteria(DoctorData.class)
				.add(Restrictions.eq("clinicdata.clinicId", clinicId))
				.addOrder(Order.asc("doctorAbbr"))
				.list();
		
		session.getTransaction().commit();
		return doctorAbbrs;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<DoctorData> getDoctorAbbrs(int clinicId)
	{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Collection<DoctorData> doctorAbbrs = session
				.createQuery(
						"select new DoctorData(doctorId, doctorAbbr) " +
						"from DoctorData as doctorData " +
						"order by doctorData.doctorAbbr")
				.list();
		
		session.getTransaction().commit();
		return doctorAbbrs;
	}
}
