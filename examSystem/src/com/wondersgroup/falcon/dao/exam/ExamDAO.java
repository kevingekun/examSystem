package com.wondersgroup.falcon.dao.exam;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ExamDAO {
	public ExamDAO(){
		HibernateUtil.beginTransaction();
	}
	
	 
	
	public void saveOrUpdate(final Object obj)
			throws Exception { HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					session.saveOrUpdate(obj);
				}catch(HibernateException ex){
					ex.printStackTrace();
				} 
				return null;
			}
		});

		}
	 
	public Object findById(final Class classtype,final String id)throws Exception{
		return (Object) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					Object obj = null;
					obj = session.load(classtype, id);
					return obj;
				}catch(HibernateException ex){
					ex.printStackTrace();
				}
				return null;
			}
		});
	}
	
	public List findByHql(final String hql)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					Query query = session.createQuery(hql);
					List list = null;
					list = query.list();
					return list;
				}catch(HibernateException ex){
					ex.printStackTrace();
				}
				return null; 

			}
		});


	}
	
	 
	public void delete(final Object ep)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					session.delete(ep);
				}catch(HibernateException ex){
					ex.printStackTrace();
				} 
				return null;
			}
		});

		}
}
