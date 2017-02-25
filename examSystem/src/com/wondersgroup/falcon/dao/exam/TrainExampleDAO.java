package com.wondersgroup.falcon.dao.exam;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.CLOB;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.exam.TrainExample;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class TrainExampleDAO {
	
	
	public TrainExampleDAO() throws InfrastructureException {
		HibernateUtil.beginTransaction();
	}
	
	public void saveOrUpdate(final Object obj)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
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
	 
	public void  saveTrainExample(final TrainExample tf)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					tf.setContent(Hibernate.createClob(" "));
					session.saveOrUpdate(tf);
					session.flush();
					
					session.refresh(tf,LockMode.UPGRADE);
					String neirongstring = tf.getContentstring();
					SerializableClob sc = (SerializableClob)tf.getContent();
					Clob wrapped = sc.getWrappedClob();
					CLOB clob = (CLOB)wrapped;
					clob.putString(1, neirongstring);
				} catch (HibernateException ex) {
					throw new InfrastructureException(ex);
				}
				return null;
			}
		});
		}
	 
	public List findTrainExample(final int first,final int size)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = new ArrayList();
					String hql = "from TrainExample t order by t.sendtime desc";
					Query query = session.createQuery(hql);
					query.setFirstResult(first);
					query.setMaxResults(size);
					list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null; 

			}
		});


	}
	
	public List findTrainExampleByCrit(final int first,final int size,final String username,final String realname,final String status,final String starttime,final String endtime)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = new ArrayList();
					StringBuffer hql = new StringBuffer();
					hql.append("from TrainExample t where 1=1 ");
					if(!username.equals("")){
						hql.append(" and t.sender.username='"+username+"'");
					}
					if(!realname.equals("")){
						hql.append(" and t.sender.realname=? ");
					}			
					if(!status.equals("")){
						hql.append(" and t.status ="+status+" ");
					}
					String starttime1=starttime;
					if(starttime.equals("")){
						starttime1="1900-01-01";
					}	
					String endtime1=endtime;
					if(endtime.equals("")){
						endtime1="3000-01-01";
					}
					hql.append(" and trunc(t.sendtime) between to_date('"+starttime1+"','yyyy-MM-dd') and to_date('"+endtime1+"','yyyy-MM-dd')");
					hql.append("order by t.sendtime desc");
					Query query = session.createQuery(hql.toString());
					if(!realname.equals("")){
						query.setString(0, realname);
					}		
					query.setFirstResult(first);
					query.setMaxResults(size);
					list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null; 

			}
		});


	}
	
	
	public int findCountByCrit(final int first,final int size,final String username,final String realname,final String status,final String starttime,final String endtime)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = new ArrayList();
					StringBuffer hql = new StringBuffer();
					hql.append("select count(*) from TrainExample t where 1=1 ");
					if(!username.equals("")){
						hql.append(" and t.sender.username='"+username+"'");
					}
					if(!realname.equals("")){
						hql.append(" and t.sender.realname=? ");
					}			
					if(!status.equals("")){
						hql.append(" and t.status ="+status+" ");
					}
					String starttime1=starttime;
					if(starttime.equals("")){
						starttime1="1900-01-01";
					}		
					String endtime1=endtime;
					if(endtime.equals("")){
						endtime1="3000-01-01";
					}
					hql.append(" and trunc(t.sendtime) between to_date('"+starttime1+"','yyyy-MM-dd') and to_date('"+endtime1+"','yyyy-MM-dd')");
					hql.append("order by t.sendtime desc");
					Query query = session.createQuery(hql.toString());
					if(!realname.equals("")){
						query.setString(0, realname);
					}		
					query.setFirstResult(first);
					query.setMaxResults(size);
					list = query.list();
					if(list!=null&&list.size()>0){
						return (Integer.valueOf(list.get(0).toString())).intValue();
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return 0;

			}
		});


	}
	
	
	
	public int findAllTrainExample()throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = new ArrayList();
					String hql = "select count(*) from TrainExample t order by t.sendtime desc";
					Query query = session.createQuery(hql);
					list = query.list();
					if(list.size()>0){
						int count = (Integer.valueOf(list.get(0).toString())).intValue();
						return count;
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return 0;

			}
		});


	}
	public List findTrainExampleBySender(final String username,final int first,final int size)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = new ArrayList();
					String hql = "from TrainExample t where t.sender.username='"+username+"' order by t.sendtime desc";
					Query query = session.createQuery(hql);
					query.setFirstResult(first);
					query.setMaxResults(size);
					list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;

			}
		});


	}
	
	public int findCountBySender(final String username,final int first,final int size)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = new ArrayList();
					String hql = "select count(*) from TrainExample t where t.sender.username='"+username+"' order by t.sendtime desc";
					Query query = session.createQuery(hql);
					query.setFirstResult(first);
					query.setMaxResults(size);
					list = query.list();
					if(list!=null&&list.size()>0){
						return ( Integer.valueOf(list.get(0).toString())).intValue();
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return 0;

			}
		});


	}
	
	
	 
	public TrainExample findOneTrainExample(final String tfid)throws Exception{
		return (TrainExample) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					TrainExample tf = (TrainExample) session.load(TrainExample.class, tfid);
					return tf;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;

			}
		});


	}
	
 
	public List findReplyBySid(final String tfid)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = new ArrayList();
					String hql = "from TrainExampleReply t where t.feedback.id='"+tfid+"' order by t.sendtime desc";
					Query query = session.createQuery(hql);
					list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;	 

			}
		});


	}
	/**
	 * �ҳ�ĳ��δ���Ļظ�
	 * @param username �û���
	 * @return ĳ��δ����ҵ�����ظ�
	 */
	
	 
	public List findNoReadReply(final String username)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = new ArrayList();
					String hql = "from TrainExampleReply t where t.isread=0 and t.feedback.sender.username='"+username+"' order by t.sendtime desc";
					Query query = session.createQuery(hql);
					query.setFirstResult(0);
					query.setMaxResults(20);
					list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;

			}
		});


	}
}
