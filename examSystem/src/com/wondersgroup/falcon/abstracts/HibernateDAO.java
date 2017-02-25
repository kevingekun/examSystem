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
 * <p>Title:[�����] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2009-7-1]
 * Midified by [�޸���] [�޸�ʱ��]
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
	 * <p>Description:[�������޸�] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-24]
	 * Midified by [�޸���] [�޸�ʱ��]
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
	 * <p>Description:[ͨ��ID��ѯ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-24]
	 * Midified by [�޸���] [�޸�ʱ��]
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
	 * <p>Description:[ɾ��] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-24]
	 * Midified by [�޸���] [�޸�ʱ��]
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
	 * <p>Description:[HQL����ѯ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-24]
	 * Midified by [�޸���] [�޸�ʱ��]
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
