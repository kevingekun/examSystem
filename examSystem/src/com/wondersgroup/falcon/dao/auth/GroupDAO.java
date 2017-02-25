package com.wondersgroup.falcon.dao.auth;

import java.util.Collection;

import org.hibernate.HibernateException;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.auth.Group;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class GroupDAO {

	public GroupDAO() {
		HibernateUtil.beginTransaction();
	}
	
	public Group getGroupById(Long groupId)
		throws InfrastructureException {
		
		Group group = null;
		try {
			group = (Group)HibernateUtil.getSession().load(Group.class, groupId);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return group;
	}
	
	public Collection findAll()
		throws InfrastructureException {
	
		Collection groups;
		try {
			groups = HibernateUtil.getSession().createCriteria(Group.class).list();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return groups;
	}

}
