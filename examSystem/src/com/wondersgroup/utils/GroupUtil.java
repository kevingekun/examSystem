package com.wondersgroup.utils;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.wondersgroup.falcon.model.auth.Group;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class GroupUtil {

	public GroupUtil() {
		HibernateUtil.beginTransaction();
	}

	@SuppressWarnings("unchecked")
	public  List<Group> getGroups()throws Exception{
		return (List<Group>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria c = session.createCriteria(Group.class);
				List<Group> list = (List<Group>) c.uniqueResult();
				HibernateUtil.commitTransaction();
				return list;

			}
		});
	}
}
