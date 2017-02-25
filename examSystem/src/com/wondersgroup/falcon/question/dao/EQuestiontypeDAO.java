package com.wondersgroup.falcon.question.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.wondersgroup.falcon.abstracts.HibernateDAO;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.model.EQuestiontype;


public class EQuestiontypeDAO    {
	private static final Log log = LogFactory.getLog(EQuestiontypeDAO.class);
	 
	
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTOR = "descriptor";
	public static final String ZT = "zt";
	public static final String PRIORITY = "priority";
 
	public void save(final EQuestiontype transientInstance)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("saving EQuestiontype instance");
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
 
	public void delete(final EQuestiontype persistentInstance) 
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("deleting EQuestiontype instance");
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
 
	public EQuestiontype findById(final Long id)throws Exception{
		return (EQuestiontype) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("getting EQuestiontype instance with id: " + id);
				try {
					EQuestiontype instance = (EQuestiontype) session.get(
							"com.wondersgroup.falcon.question.model.EQuestiontype", id);
					return instance;
				} catch (RuntimeException re) {
					log.error("get failed", re);
					throw re;
				} 

			}
		});


	}
 
	public List findByExample(final EQuestiontype instance)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding EQuestiontype instance by example");
				try {
					List results = session.createCriteria("com.wondersgroup.falcon.question.model.EQuestiontype").add(
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
 
	public List findByProperty(final String propertyName, final Object value)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding EQuestiontype instance with property: "
						+ propertyName + ", value: " + value);
				try {
					String queryString = "from EQuestiontype as model where model."
							+ propertyName + "= ?";
					Query queryObject = session.createQuery(queryString);
					queryObject.setParameter(0, value);
					return queryObject.list();
				} catch (RuntimeException re) {
					log.error("find by property name failed", re);
					throw re;
				} 

			}
		});


	}
	public List findByName(Object name) {
		try {
			return findByProperty(NAME, name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}

	public List findByDescriptor(Object descriptor) {
		try {
			return findByProperty(DESCRIPTOR, descriptor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}

	public List findByZt(Object zt) {
		try {
			return findByProperty(ZT, zt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List findByPriority(Object priority) {
		try {
			return findByProperty(PRIORITY, priority);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

 
	public List findAll()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding all EQuestiontype instances");
				try {
					String queryString = "from EQuestiontype as eq where eq.zt=1 order by eq.priority asc";
					Query queryObject = session.createQuery(queryString);
					return queryObject.list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
				} 

			}
		});


	}
	//找寻考试结束的试卷
	public List findPaperAll()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding all EPapers instances");
				try {
					String queryString = "from EPapers as eq where eq.sjZt=2 ";
					Query queryObject = session.createQuery(queryString);
					//System.out.print("长度----------------"+queryObject.list().size());
					return queryObject.list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
				} 

			}
		});


	}

 
	public EQuestiontype merge(final EQuestiontype detachedInstance)throws Exception{
		return (EQuestiontype) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("merging EQuestiontype instance");
				try {
					EQuestiontype result = (EQuestiontype) session.merge(
							detachedInstance);
					log.debug("merge successful");
					return result;
				} catch (RuntimeException re) {
					log.error("merge failed", re);
					throw re;
				} 

			}
		});


	}
 
	public void attachDirty(final EQuestiontype instance)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("attaching dirty EQuestiontype instance");
				try {
					session.saveOrUpdate(instance);
					log.debug("attach successful");
				} catch (RuntimeException re) {
					log.error("attach failed", re);
					throw re;
				} 
				return null;
			}
		});
		}
 
	public void attachClean(final EQuestiontype instance)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("attaching clean EQuestiontype instance");
				try {
					session.lock(instance, LockMode.NONE);
					log.debug("attach successful");
				} catch (RuntimeException re) {
					log.error("attach failed", re);
					throw re;
				} 
				return null;
			}
		});
		}
}