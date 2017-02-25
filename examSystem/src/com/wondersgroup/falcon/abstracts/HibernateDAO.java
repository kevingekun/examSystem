/**
 * 
 */
package com.wondersgroup.falcon.abstracts;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.persistence.HibernateUtil;

/**
 * <p>Title:[类标题] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2009-7-1]
 * Midified by [修改人] [修改时间]
 *
 */
public class HibernateDAO {

	/**
	 * 
	 */
	public HibernateDAO() {
		
		HibernateUtil.beginTransaction();
	}

	/**
	 * 
	 * <p>Description:[新增、修改] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-24]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param obj
	 */
	public void saveOrUpdate(Object obj){
		try{
			Session s = HibernateUtil.getSession();
			s.saveOrUpdate(obj);
		}catch(HibernateException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * 
	 * <p>Description:[通过ID查询] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-24]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param classtype
	 * @param id
	 * @return
	 */
	public Object findById(Class classtype,Long id){
		try{
			Object obj = null;
			Session s = HibernateUtil.getSession();
			obj = s.load(classtype, id);
			return obj;
		}catch(HibernateException ex){
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * <p>Description:[删除] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-24]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param ep
	 */
	public void delete(Object ep){
		try{
			Session s = HibernateUtil.getSession();
			s.delete(ep);

		}catch(HibernateException ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>Description:[HQL语句查询] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-24]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param hql
	 * @return
	 */
	public List findByHql(String hql){
		try{
			Session s = HibernateUtil.getSession();
			Query query = s.createQuery(hql);
			List list = null;
			list = query.list();
			return list;
		}catch(HibernateException ex){
			ex.printStackTrace();
		}
		return null;
	}

}
