package com.wondersgroup.falcon.dao.call;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


import com.wondersgroup.falcon.Util.PageTool;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.call.Summary;
import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class CallDAO {

	public CallDAO() throws InfrastructureException {

	}

	public List getRecordUrl(final String callid) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() { 
			public Object execute(Session session) throws Throwable {
				String sql = " select a.RECORDREFERENCE as rd, a.VOICEIP as ip, a.STARTRECORDTIME as dt, a.AGENTID as ag "
						+ " from recordoriginaldata a "
						+ " where a.RECORDREFERENCE='"
						+ callid
						+ "'"
						+ " order by a.STARTRECORDTIME desc";
				Query query = session.createSQLQuery(sql).addScalar("rd", Hibernate.STRING).addScalar("ip",
						Hibernate.STRING).addScalar("dt", Hibernate.TIMESTAMP).addScalar("ag", Hibernate.STRING);
				query.setFirstResult(0);
				query.setMaxResults(10);
				return query.list();
			}
		});
	}
 
	public List getHisRecordUrl(final String callid) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = " select a.RECORDREFERENCE as rd, a.VOICEIP as ip, a.STARTRECORDTIME as dt, a.AGENTID as ag, a.CALLERID as ex "
						+ " from RECORDORIGINALDATA a "
						+ " where a.RESERVEDTHREE='"
						+ callid
						+ "'"
						+ " and a.startrecordtime in (select min(t.startrecordtime) from recordoriginaldata t where t.reservedthree = '"+callid+"')";
				Query query = session.createSQLQuery(sql).addScalar("rd", Hibernate.STRING).addScalar("ip",
						Hibernate.STRING).addScalar("dt", Hibernate.TIMESTAMP).addScalar("ag", Hibernate.STRING).addScalar("ex", Hibernate.STRING);
				query.setFirstResult(0);
				query.setMaxResults(10);
				return query.list();
			}
		});
	}

	public List findRecordByCrit(final String username, final String starttime, final String endtime) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				String start = null;
				String end = null;

				StringBuffer sql = new StringBuffer();
				sql
						.append(" select a.RECORDREFERENCE as rd, a.VOICEIP as ip, a.STARTRECORDTIME as dt, a.AGENTID as ag "
								+ " from vclog.recordoriginaldata a " + " where 1=1 ");
				if (!username.equals("")) {
					sql.append(" and a.AGENTID='" + username + "'");
				}
				if (starttime.equals("")) {
					start = "1900-1-1";
				}
				if (endtime.equals("")) {
					end = "3000-1-1";
				}
				if (starttime.equals("") && !endtime.equals("")) {
					sql.append("and trunc(a.STARTRECORDTIME) between to_date('" + start + "','yyyy-MM-dd') "
							+ " and to_date('" + endtime + "','yyyy-MM-dd') order by a.STARTRECORDTIME desc");

				}
				else if (!starttime.equals("") && endtime.equals("")) {
					sql.append("and trunc(a.STARTRECORDTIME) between to_date('" + starttime + "','yyyy-MM-dd') "
							+ " and to_date('" + end + "','yyyy-MM-dd') order by a.STARTRECORDTIME desc");

				}
				else if (starttime.equals("") && endtime.equals("")) {
					sql.append("and trunc(a.STARTRECORDTIME) between to_date('" + start + "','yyyy-MM-dd') "
							+ " and to_date('" + end + "','yyyy-MM-dd') order by a.STARTRECORDTIME desc");
				}
				else {
					sql.append("and trunc(a.STARTRECORDTIME) between to_date('" + starttime + "','yyyy-MM-dd') "
							+ " and to_date('" + endtime + "','yyyy-MM-dd') order by a.STARTRECORDTIME desc");
				}
				Query query = session.createSQLQuery(sql.toString()).addScalar("rd", Hibernate.STRING).addScalar("ip",
						Hibernate.STRING).addScalar("dt", Hibernate.TIMESTAMP).addScalar("ag", Hibernate.STRING);
				query.setFirstResult(0);
				query.setMaxResults(10);
				return query.list();
			}
		});
	}

	public List findTopTenHistory(final int first, final int size) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = " select t.phoneid,t.endtime," + " t.id,t.sort,t.type,t.gonghao,"
						+ " t.callid,t.zhuidian " + " from History t order by t.endtime desc";
				Query query = session.createSQLQuery(hql).addScalar("phoneid", Hibernate.STRING).addScalar("endtime",
						Hibernate.STRING).addScalar("id", Hibernate.STRING).addScalar("sort", Hibernate.STRING)
						.addScalar("type", Hibernate.STRING).addScalar("gonghao", Hibernate.STRING).addScalar("callid",
								Hibernate.STRING).addScalar("zhuidian", Hibernate.STRING);

				query.setFirstResult(first);
				query.setMaxResults(size);
				List list = query.list();
				if (list != null && list.size() != 0) {
					return list;
				}
				else {
					return null;
				}
			}
		});
	}

	public List findHistoryByCrit(final String username, final String starttime, final String endtime) {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				StringBuffer hql = new StringBuffer();
				String start = null;
				String end = null;
				hql.append(" select t.phoneid,t.endtime," + " t.id,t.sort,t.type,t.gonghao," + " t.callid,t.zhuidian "
						+ " from History t where 1=1 ");
				if (!username.equals("")) {
					hql.append(" and t.gonghao='" + username + "'");
				}
				if (starttime.equals("")) {
					start = "1900-1-1";
				}
				if (endtime.equals("")) {
					end = "3000-1-1";
				}
				if (starttime.equals("") && !endtime.equals("")) {
					hql.append(" and trunc(t.endtime) between to_date('" + start + "','yyyy-mm-dd') and to_date('"
							+ endtime + "','yyyy-mm-dd') " + " order by t.endtime desc");

				}
				else if (!starttime.equals("") && endtime.equals("")) {
					hql.append(" and trunc(t.endtime) between to_date('" + starttime + "','yyyy-mm-dd') and to_date('"
							+ end + "','yyyy-mm-dd') " + " order by t.endtime desc");

				}
				else if (starttime.equals("") && endtime.equals("")) {
					hql.append(" and trunc(t.endtime) between to_date('" + start + "','yyyy-mm-dd') and to_date('"
							+ end + "','yyyy-mm-dd') " + " order by t.endtime desc");
				}
				else {
					hql.append(" and trunc(t.endtime) between to_date('" + starttime + "','yyyy-mm-dd') and to_date('"
							+ endtime + "','yyyy-mm-dd') " + " order by t.endtime desc");
				}

				Query query = session.createSQLQuery(hql.toString()).addScalar("phoneid", Hibernate.STRING).addScalar(
						"endtime", Hibernate.STRING).addScalar("id", Hibernate.STRING).addScalar("sort",
						Hibernate.STRING).addScalar("type", Hibernate.STRING).addScalar("gonghao", Hibernate.STRING)
						.addScalar("callid", Hibernate.STRING).addScalar("zhuidian", Hibernate.STRING);

				query.setFirstResult(0);
				query.setMaxResults(10);
				List list = query.list();
				if (list != null && list.size() != 0) {
					return list;
				}
				else {
					return null;
				}
			}
		});
	}

	public List getDataByID(final Long id) throws HibernateException {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = "select t.gonghao,t.type,t.sort,t.comments,t.callid from History t where t.id='" + id
						+ "'";
				Query query = session.createSQLQuery(hql).addScalar("gonghao", Hibernate.STRING).addScalar("type",
						Hibernate.STRING).addScalar("sort", Hibernate.STRING).addScalar("comments", Hibernate.STRING)
						.addScalar("callid", Hibernate.STRING);
				List list = query.list();
				return list;
			}
		});

	}

	public List getArichivelog(final String callid) throws HibernateException {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = "select t.name,t.dt from ARCHIVELOG t where t.callid='" + callid + "' order by t.dt desc";
				Query query = session.createSQLQuery(hql).addScalar("name", Hibernate.STRING).addScalar("dt",
						Hibernate.STRING);
				List list = query.list();
				return list;

			}
		});  
	}
	
	
	public List getSummary(final String userid,final String callid) throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	   

				List l = null;
				Criteria criteria =s.createCriteria(Summary.class);		
				criteria.add(Expression.eq("userid",new Long(userid) ));
				criteria.add(Expression.eq("callid",new Long(callid) ));
				l=criteria.list();
				return l;
			}}); 
	}
	
	public List getCallinfo(final String userid,final int start) throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	   
				DBOperation db = new DBOperation();
				List l = null;
				String sql="";
				sql+=" select t.telno, to_char(t.calldt,'yyyy-mm-dd hh24:mi:ss') ,m.callid ";
///				sql+="  from callinfo t ,manualcall_log  m,summary w";
///				sql+="  where m.callid=w.callid(+) and t.callid=m.callid and m.userid='" + userid + "' and w.userid='" + userid + "'  ";
		       
		        sql+="  from manualcall_log m ,callinfo t";
		        sql+="  where  m.userid= '" + userid + "' and m.connectdt is not null  and  t.callid=m.callid  ";
		        sql+="  order by t.calldt ";	         
		        //System.out.println(sql);
				l=db.Query_new(sql,start);
				return l;

//				String[][] a=new String[l.size()][3];
//				for(int i=0;i<l.size();i++){
//					Object[] ne=(Object[]) l.get(i);
//					a[i][0]=(String) ne[0];
//					a[i][1]=(String) ne[1];
//					a[i][2]=(String) ne[2];
//				}
//				return a;	
			}}); 
	}
	
	public int getAllCallinfo(final String userid) throws InfrastructureException{
		return (Integer)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	   
				DBOperation db = new DBOperation();
				BigDecimal ss = new BigDecimal(0);   
				String sql="";
				  sql+=" select count( m.callid)  ";
				  sql+="  from manualcall_log m ";
				  sql+="  where  m.userid= '" + userid + "' and  m.connectdt is not null ";
	

					List l = null;				
					
					l=db.Query_new(sql);
					
					Integer a = 0 ; 
					if(l!=null && !l.isEmpty()){
						//System.out.println("---------------"+l.get(0));
						ss = (BigDecimal)l.get(0);
						a = ss.intValue();
						
					}
					
					return a.intValue();
			}}); 
	}
///当月接听量 
	public int getAllHisCallinfo(final String userid) throws InfrastructureException{
		return (Integer)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	   
				DBOperation db = new DBOperation();
				BigDecimal ss = new BigDecimal(0);   
				String sql="";
				  sql+=" select count( m.callid)  ";
				  sql+="  from his_manualcall_log m ";
				  sql+="  where  m.userid= '" + userid + "' and  m.connectdt is not null and to_char(m.connectdt,'yyyy-mm')=to_char(sysdate,'yyyy-mm')  ";
	

					List l = null;				
					
					l=db.Query_new(sql);
					
					Integer a = 0 ; 
					if(l!=null && !l.isEmpty()){
						//System.out.println("---------------"+l.get(0));
						ss = (BigDecimal)l.get(0);
						a = ss.intValue();
						
					}
					
					return a.intValue();
			}}); 
	}
	

	
}
