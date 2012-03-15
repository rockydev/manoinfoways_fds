package com.manoinfoways.ejb;
// Generated Mar 14, 2012 4:42:37 PM by Hibernate Tools 3.4.0.CR1


import java.util.Collection;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.manoinfoways.model.ClinicData;
import com.manoinfoways.model.HibernateUtil;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class ClinicData.
 * @see com.manoinfoways.model.ClinicData
 * @author Hibernate Tools
 */
public class ClinicDataBean {

    private static final Log log = LogFactory.getLog(ClinicDataBean.class);

    private final SessionFactory sessionFactory = getSessionFactory();
    
    protected SessionFactory getSessionFactory() {
        try {
            return HibernateUtil.getSessionFactory();
        }
        catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void persist(ClinicData transientInstance) {
        log.debug("persisting ClinicData instance");
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.persist(transientInstance);
            session.getTransaction().commit();
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ClinicData instance) {
        log.debug("attaching dirty ClinicData instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ClinicData instance) {
        log.debug("attaching clean ClinicData instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ClinicData persistentInstance) {
        log.debug("deleting ClinicData instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ClinicData merge(ClinicData detachedInstance) {
        log.debug("merging ClinicData instance");
        try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			ClinicData result = (ClinicData) session.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ClinicData findById( java.lang.String id) {
        log.debug("getting ClinicData instance with id: " + id);
        try {
            ClinicData instance = (ClinicData) sessionFactory.getCurrentSession()
                    .get("com.manoinfoways.model.ClinicData", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List<ClinicData> findByExample(ClinicData instance) {
        log.debug("finding ClinicData instance by example");
        try {
            List<ClinicData> results = (List<ClinicData>) sessionFactory.getCurrentSession()
                    .createCriteria("com.manoinfoways.model.ClinicData")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
    
    @SuppressWarnings("unchecked")
	public Collection<ClinicData> getAllClinicData() {
//		Session session = super.getSessionFactory().openSession();
//		
//		session.beginTransaction();
//		Query query = session.createQuery("from ClinicData");
//		
//		Collection<ClinicData> list = query.list();
//		
//		session.getTransaction().commit();
//		session.close();
		return null;

	}


	public void update(ClinicData clinicData) {
//		super.merge(clinicData);
	}
	
	public ClinicData deleteClinicDataById(String clinicId) {
		return null;
	}
}

