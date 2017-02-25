package com.wondersgroup.falcon.question.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.abstracts.HibernateDAO;
import com.wondersgroup.falcon.model.citizeninfo.HisAttr;
import com.wondersgroup.falcon.model.citizeninfo.PaperType;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.model.EBusinesstype;
import com.wondersgroup.falcon.question.model.EQuestiontype;
import com.wondersgroup.popedom.bo.EUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * EBusinesstype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wondersgroup.falcon.question.model.EBusinesstype
 * @author MyEclipse Persistence Tools
 */

public class EBusinesstypeDAO  {
	private static final Log log = LogFactory.getLog(EBusinesstypeDAO.class);
	// property constants
	 
	public static final String NAME = "name";
	public static final String DESCRIPTOR = "descriptor";
	public static final String ZT = "zt";
	public static final String PRIORITY = "priority";


 

public void save(final EBusinesstype transientInstance)
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		log.debug("saving EBusinesstype instance");
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

 

public void delete(final EBusinesstype persistentInstance)
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		log.debug("deleting EBusinesstype instance");
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
	 
	public List findAll()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding all EBusinesstype instances");
				try {
					String queryString = "from EBusinesstype as ebt where ebt.zt=1 order by ebt.priority asc";
					Query queryObject = session.createQuery(queryString);
					return queryObject.list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
				} 

			}
		});


	}
	/** 获取服务类型 */
 
	@SuppressWarnings("unchecked")
	public  List<EBusinesstype> findServiceType() throws Exception{
		return (List<EBusinesstype>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<EBusinesstype> list = null;
				try {
					String queryString = "from EBusinesstype hs  order by hs.priority";
					list = session.createQuery(queryString).list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
				}
				return list;

			}
		});


	}
	/** 获取保险类型 */
 
	@SuppressWarnings("unchecked")
	public List<HisAttr> findBaoXianType()throws Exception{
		return (List<HisAttr>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<HisAttr> list = null;
				try {
					String queryString = "from HisAttr ha where ha.visible=1 order by ha.ordering";
					list = session.createQuery(queryString).list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
				}
				return list; 

			}
		});


	}
	/** 获取试卷类型 */
 
	public List<PaperType> findPaperType()throws Exception{
		return (List<PaperType>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<PaperType> list = null;
				try {
					String queryString = "from PaperType ha order by ha.id";
					list = session.createQuery(queryString).list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
				}
				return list;

			}
		});


	}
	/** 获取试题类型 */
 
	@SuppressWarnings("unchecked")
	public List<EQuestiontype> findShiTiType()throws Exception{
		return (List<EQuestiontype>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<EQuestiontype> list = null;
				try {
					String queryString = "from EQuestiontype ha order by ha.id";
					list = session.createQuery(queryString).list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
				}
				return list;

			}
		});


	}
 
	public List queryGroupList()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = " from EKaoshiGroup t";
				Query query = session.createQuery(hql);
				return query.list();

			}
		});


	}
 
	@SuppressWarnings("unchecked")
	public List<EUser> findUserByGroup(final Long id)throws Exception{
		return ( List<EUser>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = " from EUser t where t.group.id=" + id;
				Query query = session.createQuery(hql);
				return query.list();

			}
		});


	}
}