package com.wondersgroup.falcon.question.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.wondersgroup.falcon.abstracts.HibernateDAO;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.model.EImportance;

/**
 * A data access object (DAO) providing persistence and search support for
 * EImportance entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wondersgroup.falcon.question.model.EImportance
 * @author MyEclipse Persistence Tools
 */

public class EImportanceDAO  {
	private static final Log log = LogFactory.getLog(EImportanceDAO.class);
 
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTOR = "descriptor";
	public static final String ZT = "zt";
	public static final String PRIORITY = "priority";
	 
	 
	public void save(final EImportance transientInstance)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("saving EImportance instance");
				try {
					session.save(transientInstance);
					log.debug("save successful");
				} catch (RuntimeException re) {
					log.error("save failed", re);
					throw re;
				} 
				return null;
			}
		});
		}
	 
	public void delete(final  EImportance persistentInstance)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("deleting EImportance instance");
				try {
					session.delete(persistentInstance);
					log.debug("delete successful");
				} catch (RuntimeException re) {
					log.error("delete failed", re);
					throw re;
				}
				return null;
			}
		});
		}
	 
	public  EImportance findById(final Long id)throws Exception{
		return (EImportance) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("getting EImportance instance with id: " + id);
				try {
					EImportance instance = (EImportance) session.get(
							"com.wondersgroup.falcon.question.model.EImportance", id);
					return instance;
				} catch (RuntimeException re) {
					log.error("get failed", re);
					throw re;
				} 

			}
		});


	}
	
	public List findByExample(final EImportance instance)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding EImportance instance by example");
				try {
					List results = session.createCriteria(
							"com.wondersgroup.falcon.question.model.EImportance").add(
							Example.create(instance)).list();
					log.debug("find by example successful, result size: "
							+ results.size());
					return results;
				} catch (RuntimeException re) {
					log.error("find by example failed", re);
					throw re;
				} 

			}
		});


	}
	

	 
	public List findAll()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding all EImportance instances");
				try {
					String queryString = "from EImportance as ei where ei.zt=1 order by ei.priority asc";
					Query queryObject = session.createQuery(queryString);
					return queryObject.list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
				}	 

			}
		});


	}

}