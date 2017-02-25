package com.wondersgroup.technocracy.dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.HZ93;

public class MaintainExpertDao extends HibernateDaoSupport{

	public PageReturn checkExpert(final PageTool pageTool,final String name,final String org,final String c,final String style){
		 return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				StringBuffer sb = new StringBuffer();
				sb.append("select a from Addexpert as a,Addexperts as ad");
				if(!"".equals(style)){
					sb.append(",HZ93 as hz93");
				}
				sb.append(" where a.hzz001=ad.hzz001 and a.indicate='1' and ad.indicate='1'");
				if(!"".equals(name)){
					sb.append(" and a.name="+"'"+name+"'");
				}
				if(!"".equals(org)){
					sb.append(" and a.org="+"'"+org+"'");
				}
				if(!"".equals(c)){
					sb.append(" and ad.committee="+"'"+c+"'");
				}
				if(!"".equals(style)){
					sb.append(" and a.hzz001=hz93.hzz001 and hz93.aaa131='1' and hz93.hzz911='"+style+"'");
				}
				Query query = session.createQuery(sb.toString());
				pageReturn.setTotal(query.list().size());
				query.setFirstResult(pageTool.getStart());
				query.setMaxResults(pageTool.getSize());
				pageReturn.setReturnList(query.list());
				return pageReturn;
			}
		});
	 }
	public void deleteExpert(final String id){ 
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Long expertId = Long.parseLong(id);
				String sql = "update   hz90 a set a.aaa131='0' where a.hzz001="+expertId;
				String sql1 = "update  hz99 a set a.aaa131='0' where a.hzz001="+expertId;
				String sql2 = "update  hz92 a set a.aaa131='0' where a.hzz001="+expertId;
				String sql3 = "update  hz93 a set a.aaa131='0' where a.hzz001="+expertId;
				Query query = session.createSQLQuery(sql);
				Query query1 = session.createSQLQuery(sql1);
				Query query2 = session.createSQLQuery(sql2);
				Query query3 = session.createSQLQuery(sql3);
				query.executeUpdate();
				query1.executeUpdate();
				query2.executeUpdate();
				query3.executeUpdate();
				return null;
			}
		});
	}
	public void updateExpert(final Addexpert expert,final Addexperts fexpert){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.saveOrUpdate(expert);
				session.saveOrUpdate(fexpert);
				return null;
			}
		});
	}
	public void updateExpertStyle(final String[] s,final long zjid){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = "select h from HZ93 h where h.hzz001="+zjid;
				Query query = session.createQuery(hql);
				List<HZ93> list = query.list();
				Iterator<HZ93> it = list.iterator();
				while(it.hasNext()){
					HZ93 hz93 = it.next();
					hz93.setAaa131("0");
					session.saveOrUpdate(hz93);
				}
				for(int i=1;i<s.length;i++){
					Criteria criteria = session.createCriteria(HZ93.class);
					criteria.add(Restrictions.eq("hzz001", zjid));
					criteria.add(Restrictions.eq("hzz911", s[i]));
					if(criteria.list().size()>0){
						HZ93 hz93 = (HZ93) criteria.list().get(0);
						hz93.setAaa131("1");
						session.saveOrUpdate(hz93);
					}else{
						HZ93 hz93 = new HZ93();
						hz93.setHzz001(zjid);
						hz93.setHzz911(s[i]);
						hz93.setAaa131("1");
						session.save(hz93);
					}
				}
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> findById(final String id){
		return (List<Object>)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Long expertId = Long.parseLong(id);
				List<String> li = new ArrayList<String>();
				String hql = "select a from Addexpert as a where a.hzz001="+expertId;
				String hql2 = "select a from Addexperts as a where a.hzz001="+expertId;
				String hql3 = "select a from HZ93 as a where a.hzz001="+expertId+" and a.aaa131='1'";
				Query query = session.createQuery(hql);
				Query query2 = session.createQuery(hql2);
				Query query3 = session.createQuery(hql3);
				List<Object> list = new ArrayList<Object>();
				list.add(query.list().get(0));
				list.add(query2.list().get(0));
				Iterator<HZ93> it = query3.list().iterator();
				while (it.hasNext()) {
					HZ93 hz93 = it.next();
					li.add(hz93.getHzz911());
				}
				list.add(li);
				return list;
			}
		});
	}
	 
}
