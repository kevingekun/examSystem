package com.wondersgroup.gonggao.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.gonggao.bo.Mgg;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class MggDao extends HibernateDaoSupport {
	
	
 
	public PageReturn findgg(final PageTool pageTool,final String sj_mc)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn=new PageReturn();
			    StringBuffer qs = new StringBuffer();
			    qs.append("select a.hzz092,a.sj_id,b.sjMc from Mgg as a, EPapers as b where a.sj_id=b.sjId");
			    if(!"".equals(sj_mc)){
			    	qs.append(" and b.sjMc="+"'"+sj_mc+"'");
			    	}
					Query queryObject = session.createQuery(qs.toString());
					pageReturn.setTotal(queryObject.list().size());
					queryObject.setFirstResult(pageTool.getStart());
					 queryObject.setMaxResults(pageTool.getSize());
					 pageReturn.setReturnList(queryObject.list());
						//return pageReturn;
					 List li=queryObject.list();
						List<Mgg> list = new ArrayList<Mgg>();
						Iterator<Object[]> it = li.iterator();
						while(it.hasNext()){
							Object[] o = it.next();
							Mgg e = new Mgg();
							 e.setHzz092((String)o[0]);
							 e.setSj_id((Long)(o[1]));
							 e.setIdentifynumber((String)(o[2]));
							list.add(e);
						} 
					 
						
						pageReturn.setReturnList(list);
						return pageReturn;
			 

			}
		});


	}
	public void savenotice(final String hzz092,final String sjmc)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				 Mgg mgg=new Mgg();
					mgg.setAae036(new Date());
					mgg.setAaa131("1");
					mgg.setHzz092(hzz092);
					mgg.setSj_id(Long.valueOf(sjmc));
					//Transaction tx = session.beginTransaction();
					session.save(mgg);
					 //tx.commit();
				return null;
			}
		});
		}
	@SuppressWarnings("unchecked")
	public List<Object[]> queryNotice()throws Exception{
		return (List<Object[]>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				 StringBuffer qs = new StringBuffer();
				 qs.append("select e.sj_Id,e.sj_Mc from E_Papers   e");
				 qs.append(" left join hz98    h on e.sj_Id=h.sj_id");
				 qs.append(" where (e.sj_Zt ='0' or e.sj_Zt ='1' or e.sj_Zt ='4' )");
				 qs.append(" and  h.sj_id is null");
				 Query queryObject = session.createSQLQuery(qs.toString());
				 List li=queryObject.list();
				 return li;

			}
		});


	}

	/*public List<Object[]> queryNotice(){
		 StringBuffer qs = new StringBuffer();
		 qs.append("select e.sj_Id,e.sj_Mc from E_Papers   e");
		 qs.append(" left join hz98  h on e.sj_Id=h.sj_id");
		 qs.append(" where (e.sj_Zt ='0' or e.sj_Zt ='1' or e.sj_Zt ='4' )");
		 qs.append(" and  h.sj_id is null");
		 Session session=HibernateUtil.getSession();
		 Query queryObject = session.createSQLQuery(qs.toString());
		 List li=queryObject.list();
		 return li;*/

	@SuppressWarnings("unchecked")
	public List<Mgg> getadd(final String sjid)throws Exception{
		return (List<Mgg>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql="select a from Mgg as a  where a.sj_id='"+sjid+"'";
				Query query=session.createQuery(sql);
				List<Mgg> li=query.list();
				return li;
			}
		});

	}
	public void updateMgg(final Mgg mgg){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.saveOrUpdate(mgg);
				return null;
			}
		});
	
	
	}
}

 
