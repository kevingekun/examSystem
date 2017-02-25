package com.wondersgroup.kaoshi.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.RECORDORIGINALDATA;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class RecordoriginaldataDao extends HibernateDaoSupport {
	public PageReturn findAllRecord(final PageTool pageTool) throws Exception {
		return (PageReturn) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						PageReturn pr = new PageReturn();
						Criteria criteria = session
								.createCriteria(RECORDORIGINALDATA.class);
						pr.setTotal(criteria.list().size());

						criteria.setFirstResult(pageTool.getStart());
						criteria.setMaxResults(pageTool.getSize());
						pr.setReturnList(criteria.list());
						return pr;

					}
				});
	}

	public RECORDORIGINALDATA loadRecord(final String recordreference)
			throws Exception {
		return (RECORDORIGINALDATA) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						return (RECORDORIGINALDATA) session.load(
								RECORDORIGINALDATA.class, recordreference);

					}
				});
	}
}
