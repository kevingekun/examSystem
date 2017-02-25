package com.wondersgroup.kaoshi.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class TEXTEPapersDAO extends HibernateDaoSupport {

	public PageReturn getPapers(final PageTool pageInfo) throws Exception {
		return (PageReturn) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						PageReturn pr = new PageReturn();
						Query query = session.createQuery(" from EPapers");
						pr.setTotal(query.list().size());

						query.setFirstResult(pageInfo.getStart());
						query.setMaxResults(pageInfo.getSize());
						pr.setReturnList(query.list());

						return pr;

					}
				});
	}

	public List getPapers() throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = session.createQuery(" from EPapers");
				return query.list();

			}
		});
	}
}
