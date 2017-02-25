package com.wondersgroup.technocracy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.popedom.bo.EuserTest;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.HZ93;

public class AddExpertDao extends HibernateDaoSupport {
	public void addexpert(final Addexpert expert) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(expert);
				return null;
			}
		});
	}

	public void addexperts(final Addexperts experts) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(experts);
				return null;
			}
		});
	}

	public void addexpertHz93(final HZ93 expertHz93) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(expertHz93);
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Addexpert> queryexpert(final String idnumber) throws Exception {
		return (List<Addexpert>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session
								.createCriteria(Addexpert.class);
						criteria.add(Restrictions.eq("idnumber", idnumber));
						return criteria.list();

					}
				});
	}
}
