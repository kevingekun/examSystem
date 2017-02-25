package com.wondersgroup.falcon.paper.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.question.hibernate.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EAnswerpaper entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wondersgroup.falcon.paper.model.EAnswerpaper
 * @author MyEclipse Persistence Tools
 */

public class EAnswerpaperDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(EAnswerpaperDAO.class);

	public void save(EAnswerpaper transientInstance) {
		log.debug("saving EAnswerpaper instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EAnswerpaper persistentInstance) {
		log.debug("deleting EAnswerpaper instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EAnswerpaper findById(Long id) {
		log.debug("getting EAnswerpaper instance with id: " + id);
		try {
			EAnswerpaper instance = (EAnswerpaper) getSession().get(
					"com.wondersgroup.falcon.paper.model.EAnswerpaper", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EAnswerpaper instance) {
		log.debug("finding EAnswerpaper instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wondersgroup.falcon.paper.model.EAnswerpaper").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding EAnswerpaper instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EAnswerpaper as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all EAnswerpaper instances");
		try {
			String queryString = "from EAnswerpaper";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
}