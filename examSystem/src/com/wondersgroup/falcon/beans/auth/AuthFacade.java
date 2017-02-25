package com.wondersgroup.falcon.beans.auth;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;

import com.wondersgroup.falcon.dao.auth.AuthorityDAO;
import com.wondersgroup.falcon.dao.auth.GroupDAO;
import com.wondersgroup.falcon.dao.auth.UserDAO;
import com.wondersgroup.falcon.exceptions.ConstraintViolationException;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.auth.Authority;
import com.wondersgroup.falcon.model.auth.Group;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.falcon.model.auth.UserType;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class AuthFacade {

	private static Logger logger = Logger.getLogger(AuthFacade.class);
	
	/**
	 * Get all authorities.
	 * 
	 * @return
	 */
	public Collection getAuthorities() {
		
		Collection authorities;
		try {
			AuthorityDAO authorityDAO = new AuthorityDAO();
			authorities = authorityDAO.findAll();
			
			HibernateUtil.commitTransaction();
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't get authorities: ", ex);
			throw ex;
		} finally {
			HibernateUtil.closeSession();
		}

		return authorities;
	}
	
	/**
	 * Get users by authority id.
	 * 
	 * @param authorityId
	 * @return
	 */
	public Collection getUsersByAuthorityId(Long authorityId) {

		Collection users;
		try {
			UserDAO userDAO = new UserDAO();
			
			users = userDAO.getUsersByAuthorityId(authorityId);
			
			HibernateUtil.commitTransaction();
			return users;
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't get users by authority id: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	/**
	 * Get all groups.
	 * 
	 * @return
	 */
	public Collection getGroups() {
		
		Collection groups;
		try {
			GroupDAO groupDAO = new GroupDAO();
			groups = groupDAO.findAll();
			
			HibernateUtil.commitTransaction();
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't get groups: ", ex);
			throw ex;
		} finally {
			HibernateUtil.closeSession();
		}
		
		return groups;
	}
	
	/**
	 * Add a user. 
	 * 
	 * @param user
	 * @param groupId
	 */
	public void addUser(User user, Long groupId) {
		
		try {
			UserDAO userDAO = new UserDAO();
			GroupDAO groupDAO = new GroupDAO();
			
			Group group = groupDAO.getGroupById(groupId);
			user.setGroup(group);
			// By default, agent status is AS_LOGOFF
			user.setStatus(UserDAO.US_LOGOFF);
			// By default, agent is enabled
			user.setEnabled(UserDAO.ENABLED);
			userDAO.makePersistent(user);
			
			HibernateUtil.commitTransaction();
		} catch (ConstraintViolationException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't add user: constraint unique username.");
			throw ex;
		}
		catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't add user: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Just set user disabled.
	 * 
	 * @param userId
	 */
	public void removeUser(Long userId) {
		
		try {
			UserDAO userDAO = new UserDAO();
			
			User user = userDAO.getUserById(userId, false);
//			userDAO.makeTransient(user);
			user.setEnabled(UserDAO.DISABLED);
			userDAO.makePersistent(user);
			
			HibernateUtil.commitTransaction();
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't remove user: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Get user by user id.
	 * 
	 * @param userId
	 */
	public User getUserById(Long userId) {
		
		User user;
		try {
			UserDAO userDAO = new UserDAO();
			
			user = userDAO.getUserById(userId, false);
			Hibernate.initialize(user);
			
			HibernateUtil.commitTransaction();
			return user;
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't get user: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * Update user.
	 * 
	 * @param newUser
	 * 
	 */
	public void updateUser(User newUser) {
		
		try {
			UserDAO userDAO = new UserDAO();
			
			User user = userDAO.getUserById(newUser.getId(), false);
			// set username, realname, password, group
			// to be adjusted in the future
			
			//���� ��ɫ���� liangkd
			//user.setColor(newUser.getColor());
			
			
			userDAO.makePersistent(user);
			
			HibernateUtil.commitTransaction();
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't update user: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	/**
	 * 
	 * @param newUser
	 * @param groupId
	 */
	public void updateUser(User newUser, Long groupId) {
		
		try {
			UserDAO userDAO = new UserDAO();
			GroupDAO groupDAO = new GroupDAO();
			
			User user = userDAO.getUserById(newUser.getId(), false);
			Group group = groupDAO.getGroupById(groupId);
			// set username, realname, password, group
			// to be adjusted in the future
			user.setGroup(group);
			user.setUsername(newUser.getUsername());
			user.setRealname(newUser.getRealname());
			user.setPassword(newUser.getPassword());
			user.setAgentId(newUser.getAgentId());
			user.setUsertype(newUser.getUsertype());
			
			userDAO.makePersistent(user);
			
			HibernateUtil.commitTransaction();
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't update user: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Set user enabled info.
	 * 
	 * @param userId
	 * @param enabled
	 */
	public void setUserEnabled(Long userId, Byte enabled) {
		
		try {
			UserDAO userDAO = new UserDAO();
			
			User user = userDAO.getUserById(userId, false);
			user.setEnabled(enabled);
			
			userDAO.makePersistent(user);
			
			HibernateUtil.commitTransaction();
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't set user enabled info: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * Set user status info.
	 * 
	 * @param userId
	 * @param status
	 */
	public void setUserStatus(Long userId, Byte status) {
		
		try {
			UserDAO userDAO = new UserDAO();
			
			User user = userDAO.getUserById(userId, false);
			user.setStatus(status);
			
			userDAO.makePersistent(user);
			
			HibernateUtil.commitTransaction();
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't set user status info: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * Reset user's authorities.
	 * 
	 * @param userId
	 * @param authorityIds
	 */
	public void resetAuthorities(Long userId, Long[] authorityIds) {
		
		try {
			UserDAO userDAO = new UserDAO();
			userDAO.resetAuthorities(userId, authorityIds);
			
			HibernateUtil.commitTransaction();
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't reset authorities: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Get all users.
	 * 
	 * @return
	 */
	public Collection getAllUsers() {
		
		Collection users;
		try {
			UserDAO userDAO = new UserDAO();
			users = userDAO.findAll();
			
			HibernateUtil.commitTransaction();
			return users;
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't get users: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
return null;
	}
	
	public List findOnlyUsers(){
		try {
			UserDAO userDAO = new UserDAO();
			List list = userDAO.findOnlyUsers();
			HibernateUtil.commitTransaction();
			return list;
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't get users: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	/**
	 * Find users.
	 * 
	 * @param username
	 * @param realname
	 * @param enabled
	 * @param status
	 * @param groudId
	 * @param authorityId
	 * @return
	 */
	public Collection findUsers(String username, String realname,
			Byte enabled, Byte status, Long groupId, Long authorityId) {
		
		Collection users;
		try {
			UserDAO userDAO = new UserDAO();
			
			User userExample = new User();
			userExample.setUsername(username);
			userExample.setRealname(realname);
			userExample.setEnabled(enabled);
			userExample.setStatus(status);
			users = userDAO.findByExample(userExample, groupId, authorityId);
			
			HibernateUtil.commitTransaction();
			return users;
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't find users by example: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
		
	}
	
	/**
	 * Get logoff users.
	 * 
	 * @return
	 */
	public Collection getLogoffUsers() {
		
		Collection users;
		try {
			UserDAO userDAO = new UserDAO();
			
			users = userDAO.getLogoffUsers();
			
			HibernateUtil.commitTransaction();
			return users;
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't find users by example: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	/**
	 * Set user password info.
	 * 
	 * @param userId
	 * @param Password
	 */
	public void setUserPassword(Long userId, String Password) {
		
		try {
			UserDAO userDAO = new UserDAO();
			
			User user = userDAO.getUserById(userId, false);
			user.setPassword(Password);
			
			userDAO.makePersistent(user);
			
			HibernateUtil.commitTransaction();
		} catch (InfrastructureException ex) {
			HibernateUtil.rollbackTransaction();
			logger.error("Can't set user password: ", ex);
			throw ex;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	

	
	/**
	 * ���usertypeid�õ�usertype
	 * 
	 * 
	 */
	
	public UserType findById(String id){
		AuthorityDAO ad = new AuthorityDAO();
		try{
			UserType ut = null;
			ut = (UserType)ad.getById(UserType.class, id);
			return ut;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public Group findGroupById(String id){
		AuthorityDAO ad = new AuthorityDAO();
		try{
			Group group = null;
			group = (Group)ad.getById(Group.class, new Long(id));
			return group;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
		
	}
	
	public List findDuDaoByStatus(){
		try{
			AuthorityDAO ad = new AuthorityDAO();
			List list = null;
			list = ad. findDuDaoByStatus();
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List findAllUsers(){
		AuthorityDAO ad = new AuthorityDAO();
		try{
			return ad.findAllUsers();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List findAllGroups(){
		AuthorityDAO ad = new AuthorityDAO();
		try{
			return ad.findAllGroups();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;		
	}

}
