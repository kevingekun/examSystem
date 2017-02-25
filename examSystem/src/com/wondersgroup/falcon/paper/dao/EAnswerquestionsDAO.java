package com.wondersgroup.falcon.paper.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.wondersgroup.falcon.paper.model.EAnswerquestions;
import com.wondersgroup.falcon.question.hibernate.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EAnswerquestions entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wondersgroup.falcon.paper.model.EAnswerquestions
 * @author MyEclipse Persistence Tools
 */

public class EAnswerquestionsDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(EAnswerquestionsDAO.class);

	public void save(EAnswerquestions transientInstance) {
		log.debug("saving EAnswerquestions instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EAnswerquestions persistentInstance) {
		log.debug("deleting EAnswerquestions instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EAnswerquestions findById(Long id) {
		log.debug("getting EAnswerquestions instance with id: " + id);
		try {
			EAnswerquestions instance = (EAnswerquestions) getSession().get(
					"com.wondersgroup.falcon.paper.model.EAnswerquestions", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EAnswerquestions instance) {
		log.debug("finding EAnswerquestions instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wondersgroup.falcon.paper.model.EAnswerquestions").add(
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
		log.debug("finding EAnswerquestions instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EAnswerquestions as model where model."
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
		log.debug("finding all EAnswerquestions instances");
		try {
			String queryString = "from EAnswerquestions";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
}