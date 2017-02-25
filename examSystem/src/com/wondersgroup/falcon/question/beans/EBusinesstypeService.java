/**
 * 
 */
package com.wondersgroup.falcon.question.beans;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.dao.EBusinesstypeDAO;
import com.wondersgroup.falcon.question.model.EBusinesstype;

/**
 * <p>
 * Title:[类标题]
 * </p>
 * <p>
 * Description: [类功能描述]
 * </p>
 * 
 * Created by [Kevin Liang] [2009-6-29] Midified by [修改人] [修改时间]
 * 
 */
public class EBusinesstypeService {
	private static final Log log = LogFactory
			.getLog(EBusinesstypeService.class);

	/**
	 * 
	 * <p>
	 * Description:[根据业务类型ID号，查询该实例信息]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-29] Midified by [修改人] [修改时间]
	 * 
	 * @param id
	 * @return
	 */
	public EBusinesstype findEBusinesstypeById(Long id) {
		EBusinesstype instance = null;
		try {
			EBusinesstypeDAO dao = new EBusinesstypeDAO();
			//instance = (EBusinesstype) dao.findById(EBusinesstype.class, id);
			// HibernateUtil.commitTransaction();
			return instance;
		} catch (RuntimeException re) {
			// HibernateUtil.rollbackTransaction();
			log.error("业务类型字典表信息查询错误！", re);
			throw re;
		} finally {
			// HibernateUtil.closeSession();
		}
	}

	/**
	 * 
	 * <p>
	 * Description:[列表所有字典信息]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-1] Midified by [修改人] [修改时间]
	 * 
	 * @param id
	 * @return
	 */
	public List findEBusinesstypeAll() {
		EBusinesstypeDAO dao = new EBusinesstypeDAO();
		List instance = null;
		try {
			instance = dao.findAll();
			// HibernateUtil.commitTransaction();
			return instance;
		} catch (RuntimeException re) {
			// HibernateUtil.rollbackTransaction();
			log.error("业务类型字典表信息查询错误！", re);
			throw re;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// HibernateUtil.closeSession();
		}
		return null;

	}

	public List queryGroupList() {
		EBusinesstypeDAO dao = new EBusinesstypeDAO();
		List instance = null;
		try {
			instance=dao.queryGroupList();
			return instance;
		} catch (RuntimeException re) {
			log.error("业务类型字典表信息查询错误！", re);
			throw re;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return null;

	}
}
