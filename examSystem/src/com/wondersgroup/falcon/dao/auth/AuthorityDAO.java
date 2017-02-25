package com.wondersgroup.falcon.dao.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.archives.Users;
import com.wondersgroup.falcon.model.auth.Authority;
import com.wondersgroup.falcon.model.auth.Group;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.falcon.model.auth.UserMenus;
import com.wondersgroup.falcon.model.auth.UserType;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class AuthorityDAO {

	public AuthorityDAO() {
		HibernateUtil.beginTransaction();
	}
	
	public Authority getAuthorityById(Long authorityId)
		throws InfrastructureException {
		
		Authority authority = null;
		try {
			authority = (Authority) HibernateUtil.getSession().load(Authority.class, authorityId);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return authority;
	}
	
	@SuppressWarnings("rawtypes")
	public Collection findAll()
		throws InfrastructureException {
		
		Collection authorities;
		try {
			authorities = HibernateUtil.getSession().createCriteria(Authority.class).list();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return authorities;
	}
	
	 
	@SuppressWarnings("rawtypes")
	public Object getById(final Class classtype,final Serializable id)throws Exception{
		return (Object) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					Object obj = null;
					obj = session.load(classtype,id);
					return obj;
				}catch(HibernateException ex){
					ex.printStackTrace();
				}
				return null; 

			}
		});


	}
	
	public List hqlGetChildren(final String hql)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = null;
					Query query = session.createQuery(hql);
					list = query.list();
					return list;
				}catch(HibernateException ex){
					ex.printStackTrace();
				}
				return null;
			}
		});


	}
	@SuppressWarnings("rawtypes")
	public List findDuDaoByStatus()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = null;
					String hql = "from User t where t.status!=1 and t.usertype.usertypename like ?";
					Query query = session.createQuery(hql);
					query.setString(0, "����%");
					list = query.list();

					return list;
				}catch(HibernateException ex){
					ex.printStackTrace();
				}
				return null; 

			}
		});


	}
	
	public List findAllUsers()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = null;
					Criteria crit = session.createCriteria(Users.class).addOrder(Order.asc("group"));
					list = crit.list();
					return list;
				}catch(HibernateException ex){
					ex.printStackTrace();
				}
				return null; 

			}
		});


	}
	 
	public List findAllGroups()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = null;
					Criteria crit = session.createCriteria(Group.class);
					list = crit.list();
					return list;
				}catch(HibernateException ex){
					ex.printStackTrace();
				}
				return null; 

			}
		});


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
}
