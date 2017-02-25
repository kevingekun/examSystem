package com.wondersgroup.falcon.beans.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.dao.auth.AuthorityDAO;
import com.wondersgroup.falcon.dao.auth.UserDAO;
import com.wondersgroup.falcon.model.archives.Users;
import com.wondersgroup.falcon.model.auth.Group;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.falcon.model.auth.UserMenus;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class AuthBeans {
	private static Log log = LogFactory.getLog(AuthBeans.class);
	public AuthBeans(){
		
	}
	
	/**
	 * 得到下一层的节点的详细输出
	 * 
	 * @throws Exception
	 */
	public List getChildren(String id) throws Exception {
		try {
			AuthorityDAO dao = new AuthorityDAO();
			String hql = "from UserMenus t where t.parentid = "+id+" order by t.ordering asc";
			ArrayList list = (ArrayList) dao.hqlGetChildren(hql); 
			//System.out.println("list.size()==>>>"+list.size());
			HibernateUtil.commitTransaction();
			return list;
		} catch (Exception ex) {
			HibernateUtil.rollbackTransaction();
			log.debug(ex);
			throw ex;
		}
	}
	
	public User findById(Long userid){
		try{
			UserDAO dao = new UserDAO();	
			User user = null;
			user = dao.getUserById(userid, false);
			//HibernateUtil.commitTransaction();
			return user;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//HibernateUtil.closeSession();
		}
		return null;
	}
	
	public Users findByUsername(String username){
		try{
			AuthorityDAO dao = new AuthorityDAO();		
			String hql = "from Users t where t.username = '"+username+"' ";
			List list = dao.hqlGetChildren(hql);
			HibernateUtil.commitTransaction();
			if(list!=null){
				Users users = (Users)list.get(0);
				//Hibernate.initialize(users.getUsertype());
				//Hibernate.initialize(users.getGroup());
				return users;
			}
			
		}catch(Exception ex){
			ex.printStackTrace(); 
			HibernateUtil.rollbackTransaction();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List findByFirstMenues(Object[] ums,UserMenus um){
		try{
			AuthorityDAO dao = new AuthorityDAO();
			List list = dao.findByFirstMenues(ums,um); 
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			HibernateUtil.rollbackTransaction();
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
//	public void saveOrUpdateServer(String radiovalue){
//		try{
//			HibernateUtil.beginTransaction();
//			Session s = HibernateUtil.getSession();
//			SoftPhoneSet sps = (SoftPhoneSet)s.load(SoftPhoneSet.class, "mainurl");
//			if(radiovalue.equals("0")){
//				sps.setValue("http://168.30.1.50:8080/falnew/");
//			}else{
//				sps.setValue("http://168.30.1.54:8080/falnew/");
//			}
//			s.saveOrUpdate(sps);
//			HibernateUtil.commitTransaction();
//		}catch(HibernateException ex){
//			ex.printStackTrace();
//		}finally{
//			HibernateUtil.closeSession();
//		}
//	}
	
	public Group findGroupById(String id){
		AuthorityDAO ad = new AuthorityDAO();
		try{
			Group group = null;
			group = (Group)ad.getById(Group.class, new Long(id));
			Hibernate.initialize(group.getUsers());
			HibernateUtil.commitTransaction();
			return group;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
		
	}

	
}
