/*
 * @author: cjj
 * @创建日期: 2010-4-20
 *
 */

package com.wondersgroup.falcon.model.rc.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.rc.DicRc;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class DicRcDAO {
	public DicRcDAO() throws InfrastructureException {
	}

	public void getListById(final DicRc n, final Serializable id) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.load(n, id);
				return null;
			}
		});
	}

	// 查询记录集
	public List getList() throws InfrastructureException {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List ll;
				Criteria c = session.createCriteria(DicRc.class);
				c.addOrder(Order.asc("id"));
				ll = c.list();
				return ll;
			}
		});
	}
}
