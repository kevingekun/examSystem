package com.wondersgroup.falcon.dao.citizeninfo;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


import com.wondersgroup.falcon.Util.DateUtil;
import com.wondersgroup.falcon.beans.common.CommFunc;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.archives.ArchiveLog;
import com.wondersgroup.falcon.model.call.Zhijianpingfen;
import com.wondersgroup.falcon.model.call.ZhijianpingfenVO;
import com.wondersgroup.falcon.model.citizeninfo.History;
import com.wondersgroup.falcon.model.select.Select1;
import com.wondersgroup.falcon.model.select.SelectVO;

import com.wondersgroup.falcon.persistence.DBConnection;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ZhijianDAO {
	public ZhijianDAO(){
		//HibernateUtil.beginTransaction();
	}
	
	public List findBixiid(){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
		
			String hql = "from Bixiid t order by t.reasonid";
			Query query = session.createQuery(hql);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
			else return null;
			}
			
		});
	}
	
	public List findTopHistory(final String zhijianid,final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
	
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where t.zhijianid = '"+zhijianid+"' and t.flag in ('0','1','3') and t.tjflag is null and t.sureflag ='1' ");
			
			sb.append(" order by t.startrecordtime desc");
			Query query = session.createQuery(sb.toString());
			query.setFirstResult(prepage);
			query.setMaxResults(nextpage);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	public List findTopHistory2(final String zhijianid,final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where t.zhijianid = '"+zhijianid+"' and t.tjflag='1' ");
			
			sb.append(" order by t.startrecordtime desc");
			Query query = s.createQuery(sb.toString());
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	public List findTopHistory3(final String zhijianid,final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where t.zhijianid = '"+zhijianid+"' and t.tjflag='1' ");
			
			sb.append(" order by t.startrecordtime desc");
			Query query = s.createQuery(sb.toString());
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	public List findTopHistory21(final String gonghao,final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append(" from Select1 t where t.agentid = '"+gonghao+"' and t.tjflag='1' ");
			
			sb.append(" order by t.startrecordtime desc");
			Query query = s.createQuery(sb.toString());
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	public List findHistory21(final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where 1=1 and tjflag='1' " );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid = "+callid+"");
			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			System.out.println("xiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	public List findTopLogin(final String gonghao,final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from Login t order by t.logindt desc";
			Query query = s.createQuery(hql);
			query.setFirstResult(prepage);
			query.setMaxResults(15);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	
	public List findTopBixi(final String gonghao,final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from Select1 t where t.id = '"+gonghao+"'";
			Query query = s.createQuery(hql);
			//query.setFirstResult(prepage);
			//query.setMaxResults(15);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	
	public List findTopZhishiku(final String gonghao,final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "select t.user.username, t.dt,t.name from ArchiveLog t order by t.dt desc";
			Query query = s.createQuery(hql);
			query.setFirstResult(prepage);
			query.setMaxResults(15);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	
	public List findAll(final String gonghao,final String zhijianid) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from Select1 t where t.agentid like ?");
			if(!zhijianid.equals("")){
				sb.append(" and t.zhijianid = '"+zhijianid+"'");
			}
			sb.append(" order by t.startrecordtime desc");
			Query query = s.createQuery(sb.toString());
			query.setString(0, "'%"+gonghao+"%'");
			return query.list();
		
}
			
		});
	}
	//
	public List findHistory(final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end,final String zhijianid
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where 1=1 and t.flag in ('0','1','3') and tjflag is null and sureflag ='1' " );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid = '"+callid+"'");
			}
			if(!zhijianid.equals("")){
				sb.append(" and t.zhijianid = '"+zhijianid+"'");
			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult((prepage - 1) * nextpage);
			query.setMaxResults(nextpage);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
	
			else return null;	
}
			
		});
	}
	public List findHistory2(final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end,final String zhijianid
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where 1=1 and tjflag='1' " );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid = '"+callid+"'");
			}
			if(!zhijianid.equals("")){
				sb.append(" and t.zhijianid = "+zhijianid+"");
			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc ");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult((prepage - 1) * nextpage);
			query.setMaxResults(nextpage);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
	
			else return null;	
}
			
		});
	}
	public List findLogin(final String gonghao,final String startime,final String endtime
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Login t where 1=1");
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.logindt) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.logindt) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.logindt) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.logindt desc");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
		return null;
}
			
		});
	}
	
	public List findBixi(final String gonghao,final String sort,final String startime,final String endtime
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Bixi t where 1=1");
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!sort.equals("")){
				sb.append(" and t.bixiid.reasonid = '"+sort+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.withdraw_btime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.withdraw_btime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.withdraw_btime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.withdraw_btime desc");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	
	public List findZhishiku(final String gonghao,final String name,final String startime,final String endtime
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select t.user.username, t.dt,t.name from ArchiveLog t where 1=1");
			if(!gonghao.equals("")){
				sb.append(" and t.user.username = '"+gonghao+"'");
			}
			if(!name.equals("")){
				sb.append(" and t.name like ?");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.dt) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.dt) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.dt) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.dt desc");
			Query query = s.createQuery(sb.toString());
			
			if(!name.equals("")){
				System.out.println("aaaaaaaa");
				query.setString(i, "%" + name + "%");
				i++;
			}
			else if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult(prepage);
			query.setMaxResults(15);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	
	public List countHistory(final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end,final String zhijianid ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from Select1 t where 1=1 and t.flag in ('0','1','3') and tjflag is null and sureflag ='1' " );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid = '"+callid+"'");
			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			Query query = s.createQuery(sb.toString());
			
		
			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}

			else return null;
}
			
		});
	}
	
	public List countLogin(final String gonghao,final String startime,final String endtime ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from Login t where 1=1");
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.logindt) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.logindt) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.logindt) < to_date(?,'yyyy-mm-dd')");
			}
			Query query = s.createQuery(sb.toString());
			
		
			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	
	public List countBixi(final String gonghao,final String sort,final String startime,final String endtime ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from Bixi t where 1=1");
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!sort.equals("")){
				sb.append(" and t.bixiid.reasonid = '"+sort+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.withdraw_btime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.withdraw_btime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.withdraw_btime) < to_date(?,'yyyy-mm-dd')");
			}
			Query query = s.createQuery(sb.toString());
			
		
			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
		return null;
}
			
		});
	}
	
	public List countZhishiku(final String gonghao,final String name,final String startime,final String endtime ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from ArchiveLog t where 1=1");
			if(!gonghao.equals("")){
				sb.append(" and t.user.username = '"+gonghao+"'");
			}
			if(!name.equals("")){
				sb.append(" and t.name like ?");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.dt) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.dt) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.dt) < to_date(?,'yyyy-mm-dd')");
			}
			Query query = s.createQuery(sb.toString());
			
			if(!name.equals("")){
				query.setString(i, "%" + name + "%");
				i++;
			}
			else if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	
	public Zhijianpingfen getMarkByID(final String id) throws Exception{
		return (Zhijianpingfen) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			Zhijianpingfen zhijianpingfen=new Zhijianpingfen();
			
			//Session s = HibernateUtil.getSession(); 
			String sql = "select t.* from ZHIJIANPINGFEN t where t.pingfenid='"+id+"' and t.riqi=(select max(w.riqi) from ZHIJIANPINGFEN w where w.pingfenid='"+id+"')";
			//System.out.println("查询语句："+sql);
			//			Query query = s.createQuery(hql);
			ArrayList list =new ArrayList(); ;
			DBConnection db = new DBConnection();
			ResultSet rs = null;
			rs = db.execQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
			sdf.setLenient(false);
			 while (rs.next()) {
				 //ZhijianpingfenVO zhijianpingfenVO=new ZhijianpingfenVO();
				 System.out.print(CommFunc.convertString(rs.getString("riqi").substring(0,19)));
				 zhijianpingfen.setId(rs.getString("id"));
				 zhijianpingfen.setGonghao(rs.getString("gonghao"));
				 zhijianpingfen.setMark(rs.getString("mark"));
				 zhijianpingfen.setAuth(rs.getString("auth"));
				 zhijianpingfen.setPingfenid(rs.getString("pingfenid"));
				 zhijianpingfen.setRiqi(CommFunc.convertString(rs.getString("riqi").substring(0,19)));
				 zhijianpingfen.setDafenmemo(rs.getString("dafenmemo"));
				 zhijianpingfen.setStartrecordtime(rs.getDate("startrecordtime"));
				 zhijianpingfen.setFlag(rs.getString("flag"));
				 zhijianpingfen.setTjflag(rs.getString("tjflag"));
			    list.add(zhijianpingfen);
			 }
			if(list!=null&&list.size()!=0){
				zhijianpingfen=(Zhijianpingfen)list.get(0);
				return zhijianpingfen;
			}
		
			else return null;	
}
			
		});
	}
	
	public Zhijianpingfen getMarkByIDFinal(final String id) throws Exception{
		return (Zhijianpingfen) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			Zhijianpingfen zhijianpingfen=new Zhijianpingfen();
			
			//Session s = HibernateUtil.getSession(); 
			String sql = "select t.* from ZHIJIANPINGFEN t where t.pingfenid='"+id+"' and (t.flag = '10' or t.tjflag is null) and t.riqi=(select max(w.riqi) from ZHIJIANPINGFEN w where w.pingfenid='"+id+"' and (w.flag='10' or w.tjflag is null))";
			//System.out.println("查询语句："+sql);
			//			Query query = s.createQuery(hql);
			ArrayList list =new ArrayList(); ;
			DBConnection db = new DBConnection();
			ResultSet rs = null;
			rs = db.execQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
			sdf.setLenient(false);
			 while (rs.next()) {
				 //ZhijianpingfenVO zhijianpingfenVO=new ZhijianpingfenVO();
				 System.out.print(CommFunc.convertString(rs.getString("riqi").substring(0,19)));
				 zhijianpingfen.setId(rs.getString("id"));
				 zhijianpingfen.setGonghao(rs.getString("gonghao"));
				 zhijianpingfen.setMark(rs.getString("mark"));
				 zhijianpingfen.setAuth(rs.getString("auth"));
				 zhijianpingfen.setPingfenid(rs.getString("pingfenid"));
				 zhijianpingfen.setRiqi(CommFunc.convertString(rs.getString("riqi").substring(0,19)));
				 zhijianpingfen.setDafenmemo(rs.getString("dafenmemo"));
				 zhijianpingfen.setStartrecordtime(rs.getDate("startrecordtime"));
				 zhijianpingfen.setFlag(rs.getString("flag"));
				 zhijianpingfen.setTjflag(rs.getString("tjflag"));
			    list.add(zhijianpingfen);
			 }
			if(list!=null&&list.size()!=0){
				zhijianpingfen=(Zhijianpingfen)list.get(0);
				return zhijianpingfen;
			}
		
			else return null;	
}
			
		});
	}
	
	
	


	@SuppressWarnings("unchecked")
	public History getDataByID(final String id) throws HibernateException{
		return (History) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			History history=new History();
			String hql = "from History t where t.calllog.callid='"+id+"'";
			Query query = s.createQuery(hql);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				history=(History)list.get(0);
				return history;
			}
		
	
			else return null;
}
			
		});
	}
	
	@SuppressWarnings("unchecked")
	public List getArchivesID(final String id) throws HibernateException{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from ArchiveLog t where t.callid='"+id+"'";
			Query query = s.createQuery(hql);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
	
			else return null;
}
			
		});
	}
	
	@SuppressWarnings("unchecked")
	public List findBixi(final String userid,final Date date) throws HibernateException{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from Bixi t where t.agentid='"+userid+"'" +
					" and trunc(t.withdraw_btime) = to_date('"+date.toString().substring(0, 10)+"','yyyy-mm-dd')" +
							"and t.withdraw_btime < to_date('"+date.toString().substring(0, 19)+"','yyyy-mm-dd hh24:mi:ss')" +
									" order by t.withdraw_btime desc";
			Query query = s.createQuery(hql);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
	
			else return null;
}
			
		});
	}
	
	@SuppressWarnings("unchecked")
	public List findLogin(final String userid,final Date date) throws HibernateException{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			String hql = "from Login t where t.agentid='"+userid+"'" +
					" and trunc(t.logindt) = to_date('"+date.toString().substring(0, 10)+"','yyyy-mm-dd')" +
									" order by t.logindt desc";
			Query query = s.createQuery(hql);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
	
			else return null;
}
			
		});
	}
	

	public int replace(String id) throws Exception {
		int ret = -1;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" update zhijian_select a " );
			sql.append(" set a.flag ='1'");
			sql.append(" where a.id ='"+id+"'");
			
			DBConnection db = new DBConnection();
			ret=db.execUpdate(sql.toString());
			db.close();
			}catch(HibernateException ex){
				ex.printStackTrace();
			}
			return ret;	
		}
	
	public int addreplace(String id,Date STARTRECORDTIME,String FLAG,String ZHIJIANID,String TJFLAG,String RECORDLENGTH,String agentid,String CALLERID) throws Exception {
		int ret = -1;
		
		try{
			
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into zhijian_select(id,STARTRECORDTIME,FLAG,ZHIJIANID,TJFLAG,RECORDLENGTH,agentid,sort,CALLERID) values ('"+id+"',sysdate,'"+FLAG+"','"+ZHIJIANID+"','"+TJFLAG+"','"+RECORDLENGTH+"','"+agentid+"','99','"+CALLERID+"') " );
			
			
			DBConnection db = new DBConnection();
			ret=db.execUpdate(sql.toString());
			db.close();
			
			}catch(HibernateException ex){
				ex.printStackTrace();
			}
			return ret;		
		}
	//微调申请flag ='2'
	public int replace1(String id,String weitiaoreason) throws Exception {
		int ret = -1;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" update zhijian_select a " );
			sql.append(" set a.flag ='2'");
			sql.append(" , a.weitiaoreason ='"+weitiaoreason+"'");
			sql.append(" , a.weitiaodate = sysdate ");
			sql.append(" where a.id ='"+id+"'");
			DBConnection db = new DBConnection();
			ret=db.execUpdate(sql.toString());
			db.close();
			}catch(HibernateException ex){
				ex.printStackTrace();
			}
			return ret;	
		}
//提交分数
	public int replace2(String id) throws Exception {
		int ret = -1;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(" update ZHIJIAN_SELECT a " );
			sql.append(" set a.tjflag ='1'");
			sql.append(" , a.submitdate = sysdate ");
			sql.append(" where a.id ='"+id+"'");
			
			DBConnection db = new DBConnection();
			ret=db.execUpdate(sql.toString());
			db.close();
			}catch(HibernateException ex){
				ex.printStackTrace();
			}
			return ret;	
		}
	public List findHistory1(final String gonghao,final String zhijianid,final String callid,final String startime,final String endtime,final String start,final String end
			,final String sort,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

			int i=0;
			
			System.out.println("dao:"+gonghao);
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where 1=1 and t.flag in ('1') and t.tjflag = '1' " );
			if(!gonghao.equals("")){
				System.out.println("不为空");
				if(!"admin".equals(gonghao) && !"015".equals(gonghao) && !"033".equals(gonghao)){	
					sb.append(" and t.agentid in ("+gonghao+")");
				}
				
			}//else{
//				sb.append(" and t.agentid in ('')");
//			}
			
			if("".equals(gonghao)){
				System.out.println("为空");
				sb.append(" and t.agentid in ('')");
			}
			
			if(!sort.equals("")&&!sort.equals("1")){ 
				sb.append(" and t.sort = '"+sort+"'");
			}
			if(sort.equals("1")){ 
				sb.append(" and t.sort is null");
			}
			if(!callid.equals("")){ 
				sb.append(" and t.callerid = '"+callid+"'");
			}
			if(!zhijianid.equals("")){ 
				sb.append(" and t.zhijianid = '"+zhijianid+"'");
			}
//			if(!zhijianid.equals("")){
//				sb.append(" and t.zhijianid = "+zhijianid+"");
//			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.zhijianid,t.startrecordtime desc");
			
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			
			
			
			query.setFirstResult((prepage - 1) * nextpage);
			query.setMaxResults(nextpage);
			
			System.out.println(sb.toString());
			
			List list = query.list();
			if(list!=null&&list.size()!=0){
				return list;
			}
	
			else return null;
}
			
		});
	}
	public List zzQueryMingxi1(final String groupname,final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where  t.flag in ('1') and t.tjflag = '1' " );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!callid.equals("")){ 
				sb.append(" and t.callerid = '"+callid+"'");
			}
			
			if(!groupname.equals("")){
				sb.append(" and t.groupname = '"+groupname+"'");
			}
//			if(!zhijianid.equals("")){
//				sb.append(" and t.zhijianid = "+zhijianid+"");
//			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.agentid,t.startrecordtime  desc");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	public List zzQueryMingxi(final String groupid,final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where  t.tjflag = '1' " );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid = '"+callid+"'");
			}
			if(!groupid.equals("")){
				sb.append(" and t.groupid = '"+groupid+"'");
			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	public List baddianxingfindTopHistory1(final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where  t.dianxingflag2 ='2' and t.tjflag = '1'");
			
			sb.append(" order by t.startrecordtime desc t.zhijianid");
			Query query = s.createQuery(sb.toString());
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	public List baddianxingfindHistory1(final String sort,final String type,final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where 1=1 and t.dianxingflag2 ='2' and t.tjflag = '1' " );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid in ("+gonghao+")");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid = '"+callid+"'");
			}
			if(!sort.equals("")){
				sb.append(" and t.baoxian='"+sort+"'");
			}
			//System.out.print(type+"qqqqqqqqq");
			if(!type.equals("")){
				sb.append(" and t.fuwu='"+type+"'");
			}
//			if(!zhijianid.equals("")){
//				sb.append(" and t.zhijianid = "+zhijianid+"");
//			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc t.zhijianid ");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult((prepage - 1) * nextpage);
			query.setMaxResults(nextpage);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	public List gooddianxingfindTopHistory1(final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where  t.dianxingflag ='1' and t.tjflag = '1' ");
			
			sb.append(" order by t.startrecordtime desc t.zhijianid");
			Query query = s.createQuery(sb.toString());
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	
	public Object[][] getzuzhang(final String gonghao)
	throws InfrastructureException{
		return (Object[][])HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	 
				List l = null;
				StringBuffer sql = new StringBuffer();

				sql.append("select t.name,t.job");
				sql.append("	from usergroup_view t ");
				sql.append(" where t.gonghao = '"+gonghao+"' ");


				SQLQuery sqlQuery = s.createSQLQuery(sql.toString());
				l = sqlQuery.list();
				
				Object[][] o  = new Object[l.size()][2]; 
				//for(int i=0;i<l.size();i++){
				if(l!=null&&l.size()>0){
					Object[] oj = (Object[])l.get(0);
					o[0][0] = (Object)oj[0];
					o[0][1] = (Object)oj[1];
				}
					
				//}		                                	
				return o;
			}
		});
	}
	public List gooddianxingfindHistory1(final String sort,final String type,final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where 1=1 and t.dianxingflag ='1' and t.tjflag = '1'" );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid = '"+callid+"'");
			}
			if(!sort.equals("")){
				sb.append(" and t.baoxian='"+sort+"'");
			}
			//System.out.print(type+"qqqqqqqqq");
			if(!type.equals("")){
				sb.append(" and t.fuwu='"+type+"'");
			}
//			if(!zhijianid.equals("")){
//				sb.append(" and t.zhijianid = "+zhijianid+"");
//			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc t.zhijianid");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult((prepage - 1) * nextpage);
			query.setMaxResults(nextpage);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	public List yewuerrordianxingfindTopHistory1(final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where  t.dianxingflag3 ='3' and t.tjflag = '1' ");
			
			sb.append(" order by t.startrecordtime desc t.zhijianid");
			Query query = s.createQuery(sb.toString());
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	
	public List jiqiaodianxingfindTopHistory1(final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where  t.dianxingflag4 ='4' and t.tjflag = '1' ");
			
			sb.append(" order by t.startrecordtime desc t.zhijianid");
			Query query = s.createQuery(sb.toString());
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	public List yewuerrordianxingfindHistory1(final String sort,final String type,final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where 1=1 and t.dianxingflag3 ='3' and t.tjflag = '1'" );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid in ("+gonghao+")");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid = '"+callid+"'");
			}
			if(!sort.equals("")){
				sb.append(" and t.baoxian='"+sort+"'");
			}
			//System.out.print(type+"qqqqqqqqq");
			if(!type.equals("")){
				sb.append(" and t.fuwu='"+type+"'");
			}
//			if(!zhijianid.equals("")){
//				sb.append(" and t.zhijianid = "+zhijianid+"");
//			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc t.zhijianid ");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult((prepage - 1) * nextpage);
			query.setMaxResults(nextpage);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	
	public List jiqiaodianxingfindHistory1(final String sort,final String type,final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where 1=1 and t.dianxingflag4 ='4' and t.tjflag = '1'" );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid in ("+gonghao+")");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid = '"+callid+"'");
			}
			if(!sort.equals("")){
				sb.append(" and t.baoxian='"+sort+"'");
			}
			//System.out.print(type+"qqqqqqqqq");
			if(!type.equals("")){
				sb.append(" and t.fuwu='"+type+"'");
			}
//			if(!zhijianid.equals("")){
//				sb.append(" and t.zhijianid = "+zhijianid+"");
//			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc t.zhijianid ");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult((prepage - 1) * nextpage);
			query.setMaxResults(nextpage);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	public List findTopHistory1(final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where  t.flag in ('1') and t.tjflag = '1' ");
			
			sb.append(" order by t.zhijianid,t.startrecordtime desc");
			Query query = s.createQuery(sb.toString());
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
		return null;
}
			
		});
	}
	public List findHistory3(final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end
			,final int prepage,final int nextpage ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where t.modifyapplyflag='1' " );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid ='"+callid+"'");
			}
//			if(!zhijianid.equals("")){
//				sb.append(" and t.zhijianid = "+zhijianid+"");
//			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc t.zhijianid ");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
	
			else return null;
}
			
		});
	}
	public List findTopHistory3(final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where t.modifyapplyflag='1' ");
			
			sb.append(" order by t.startrecordtime desc t.zhijianid");
			Query query = s.createQuery(sb.toString());
			query.setFirstResult(prepage);
			query.setMaxResults(999999999);
			List list = query.list();
			System.out.println("find top ten history!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	public List countHistory1(final String gonghao,final String callid,final String startime,final String endtime,final String start,final String end,final String zhijianid ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from Select1 t where 1=1 and t.flag in ('1') and t.tjflag = '1' " );
			if(!gonghao.equals("")){
				sb.append(" and t.agentid = '"+gonghao+"'");
			}
			if(!callid.equals("")){
				sb.append(" and t.callerid = '"+callid+"'");
			}
			if(!start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength between '"+start+"' and '"+end+"'");
			}
			if(start.equals("")&&!end.equals("")){
				sb.append(" and t.recordlength < '"+end+"'");
			}
			if(!start.equals("")&&end.equals("")){
				sb.append(" and t.recordlength > '"+start+"'");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			Query query = s.createQuery(sb.toString());
			
		
			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				
				query.setString(i, "" + endtime + "");
				i++;
			}
			List list = query.list();
			
			
			if(list!=null&&list.size()!=0){
				return list;
			}
	
			else return null;	
}
			
		});
	}
	public List findAll1(final String gonghao) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from Select1 t where t.agentid like ?");
			
			sb.append(" order by t.startrecordtime desc");
			Query query = s.createQuery(sb.toString());
			query.setString(0, "%"+gonghao+"%");
			return query.list();
		
}
			
		});
	}
	//质检互查
	public List queryOthers1(final String startime,final String endtime,final String zhijianid){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where 1=1 and tjflag='1' " );
			
			if(!zhijianid.equals("")){
				sb.append(" and t.zhijianid = "+zhijianid+"");
			}
			
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc");
			Query query = s.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	public List queryOthers(final String agentid,final String startime,final String endtime,final String zhijianid){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Select1 t where  t.tjflag='1' ");
			if(!zhijianid.equals("")){
				sb.append(" and t.zhijianid = "+zhijianid+"");
			}
			if(!agentid.equals("")){
				sb.append(" and t.agentid in ('"+agentid+"')");
			}
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.startrecordtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.startrecordtime desc");
			
			Query query = s.createQuery(sb.toString());
			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + endtime + "");
				i++;
			}
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
}
