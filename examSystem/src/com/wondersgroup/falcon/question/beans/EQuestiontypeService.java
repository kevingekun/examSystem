/**
 * 
 */
package com.wondersgroup.falcon.question.beans;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.dao.EBusinesstypeDAO;
import com.wondersgroup.falcon.question.dao.EQuestiontypeDAO;
import com.wondersgroup.falcon.question.model.EBusinesstype;
import com.wondersgroup.falcon.question.model.EQuestiontype;

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
public class EQuestiontypeService {
	private static final Log log = LogFactory
			.getLog(EQuestiontypeService.class);

	/**
	 * 
	 * <p>
	 * Description:[方法功能中文描述]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-29] Midified by [修改人] [修改时间]
	 * 
	 * @param id
	 * @return
	 */
	public EQuestiontype findEQuestiontypeById(Long id) {

		try {
			EQuestiontypeDAO dao = new EQuestiontypeDAO();
			EQuestiontype instance;
			try {
				instance = dao.findById(id);
				HibernateUtil.commitTransaction();
				return instance;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RuntimeException re) {
			HibernateUtil.rollbackTransaction();
			log.error("业务类型字典表信息查询错误！", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
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
	public List findEQuestiontypeAll() {
		EQuestiontypeDAO dao = new EQuestiontypeDAO();
		List instance = null;
		try {

			instance = dao.findAll();

			HibernateUtil.commitTransaction();
			return instance;
		} catch (RuntimeException re) {
			HibernateUtil.rollbackTransaction();
			log.error("业务类型字典表信息查询错误！", re);
			throw re;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;

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
	public List findEPaperAll() {
		EQuestiontypeDAO dao = new EQuestiontypeDAO();
		List instance = null;
		try {
			instance = dao.findPaperAll();
			HibernateUtil.commitTransaction();
			return instance;
		} catch (RuntimeException re) {
			HibernateUtil.rollbackTransaction();
			log.error("业务类型字典表信息查询错误！", re);
			throw re;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;

	}

}