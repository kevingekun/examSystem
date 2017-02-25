/**
 * 
 */
package com.wondersgroup.falcon.question.beans;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.dao.EImportanceDAO;
import com.wondersgroup.falcon.question.model.EImportance;

/**
 * <p>Title:[类标题] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2009-6-29]
 * Midified by [修改人] [修改时间]
 *
 */
public class EImportanceService {
	private static final Log log = LogFactory.getLog(EImportanceService.class);


	public EImportance findEImportanceById(Long id) {

		try {
			EImportanceDAO dao= new EImportanceDAO();
			EImportance instance;
			try {
				instance = (EImportance)dao.findById(id);
				return instance;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HibernateUtil.commitTransaction();
		} catch (RuntimeException re) {
			HibernateUtil.rollbackTransaction();
			log.error("业务类型字典表信息查询错误！", re);
			throw re;
		}finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	/**
	 * 
	 * <p>Description:[列表所有字典信息] </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-1]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param id
	 * @return
	 */
	public List findEImportanceAll() {
		EImportanceDAO dao= new EImportanceDAO();
		List instance = null;
		try {
			instance= dao.findAll();
			HibernateUtil.commitTransaction();
			return instance;
		}catch (RuntimeException re) {
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
