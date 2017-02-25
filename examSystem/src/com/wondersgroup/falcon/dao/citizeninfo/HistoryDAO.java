package com.wondersgroup.falcon.dao.citizeninfo;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.Util.StringUtil;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.call.HisServiceType;
import com.wondersgroup.falcon.model.call.ServiceType;
import com.wondersgroup.falcon.model.call.Summary;
import com.wondersgroup.falcon.model.citizeninfo.History;
import com.wondersgroup.falcon.model.citizeninfo.Manualcall_log;
import com.wondersgroup.falcon.model.select.Select1;
import com.wondersgroup.falcon.model.zhijian.Luyin1;
import com.wondersgroup.falcon.model.zhijian.Luyin_his;
import com.wondersgroup.falcon.model.zhijian.RECORDORIGINALDATA;
import com.wondersgroup.falcon.model.zhijian.ZJKPZPSJ;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class HistoryDAO {
	/*
	 * 查询所有来电历史
	 */
	
	
	public HistoryDAO(){
		//HibernateUtil.beginTransaction();
	}
	 
	public List findAll1() {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "select count(*) from History t order by t.endtime";
			Query query = session.createQuery(hql);
			System.out.println(query.list().size());
			return query.list();
			}
			
		});
}
	
	
	public List countHistory(final String agentid,final String realname,final String comments,final Long recordlength1,final Long recordlength2,final String startime,final String endtime
			,final String flag,final String callerid ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from Luyin_his t where 1=1");
			if(!agentid.equals("")){
				sb.append(" and t.username = '"+agentid+"'");
			}
			if(!realname.equals("")){
				sb.append(" and t.realname= '"+realname+"'");
			}
			if(recordlength1!=null&&recordlength2!=null){
				sb.append(" and t.recordlength between "+recordlength1+" and "+recordlength2+"");
			}
			if(recordlength1!=null&&recordlength2==null){
				sb.append(" and t.recordlength > "+recordlength1+"");
			}
			if(recordlength1==null&&recordlength2!=null){
				sb.append(" and t.recordlength < "+recordlength2+"");
			}
			if(!flag.equals("")){
				sb.append(" and t.flag  is not null");
			}
			if(flag.equals("0")){
				sb.append(" and t.flag is null");
			}
			if(!callerid.equals("")){
				sb.append(" and t.callerid = '"+callerid+"'");
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
			if(!comments.equals("")){
				sb.append(" and t.summary like ?");
			}
			Query query = session.createQuery(sb.toString());
			
		
			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				System.out.println("aaaaaaaa");
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				System.out.println("bbbbbbbbbb");
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!comments.equals("")){
				query.setString(i, "'%" + comments + "%'");
				i++;
			}
			List list = query.list();
			System.out.println("countxiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
			else return null;
	}
			
		});		
	}
	
	public List countDangHistory(final String agentid,final String realname,final String comments,final Long recordlength1,final Long recordlength2,final String startime,final String endtime
			,final String flag,final String callerid ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from Luyin1 t where 1=1");
			if(!agentid.equals("")){
				sb.append(" and t.username = '"+agentid+"'");
			}
			if(!realname.equals("")){
				sb.append(" and t.realname= '"+realname+"'");
			}
			if(recordlength1!=null&&recordlength2!=null){
				sb.append(" and t.recordlength between "+recordlength1+" and "+recordlength2+"");
			}
			if(recordlength1!=null&&recordlength2==null){
				sb.append(" and t.recordlength > "+recordlength1+"");
			}
			if(recordlength1==null&&recordlength2!=null){
				sb.append(" and t.recordlength < "+recordlength2+"");
			}
			if(!flag.equals("")){
				sb.append(" and t.flag  is not null");
			}
			if(flag.equals("0")){
				sb.append(" and t.flag is null");
			}
			if(!callerid.equals("")){
				sb.append(" and t.callerid = '"+callerid+"'");
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
			if(!comments.equals("")){
				sb.append(" and t.summary like ?");
			}
			Query query = session.createQuery(sb.toString());
			
		
			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				System.out.println("aaaaaaaa");
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				System.out.println("bbbbbbbbbb");
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!comments.equals("")){
				query.setString(i, "'%" + comments + "%'");
				i++;
			}
			List list = query.list();
			System.out.println("countxiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
			else return null;
	}
			
		});		
	}
	
	
	public List countWaiHistory(final String agentid,final String realname,final Long recordlength1,final Long recordlength2,final String startime,final String endtime
			,final String flag,final String callerid ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from RECORDORIGINALDATA t where t.directionflag='0'");
			if(!agentid.equals("")){
				sb.append(" and t.agentid = '"+agentid+"'");
			}
			if(!realname.equals("")){
				sb.append(" and t.agentid= '"+realname+"'");
			}
			if(recordlength1!=null&&recordlength2!=null){
				sb.append(" and t.recordlength between "+recordlength1+" and "+recordlength2+"");
			}
			if(recordlength1!=null&&recordlength2==null){
				sb.append(" and t.recordlength > "+recordlength1+"");
			}
			if(recordlength1==null&&recordlength2!=null){
				sb.append(" and t.recordlength < "+recordlength2+"");
			}
			if(!flag.equals("")){
				sb.append(" and t.flag  is not null");
			}
			if(flag.equals("0")){
				sb.append(" and t.flag is null");
			}
			if(!callerid.equals("")){
				sb.append(" and t.calledid = '"+callerid+"'");
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
			
			Query query = session.createQuery(sb.toString());
			
		
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
			System.out.println("countxiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
			else return null;
	}
			
		});		
	}
	
	public List countHistory1(final String fuwu,final String baoxian,final String startime,final String endtime
			,final String flag ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from HisServiceType t where 1=1");
			if(!fuwu.equals("")){
				sb.append(" and t.servicetype = '"+fuwu+"'");
			}
			if(!baoxian.equals("")){
				sb.append(" and t.servicetype like '"+baoxian+"%'");
			}
			if(!flag.equals("")){
				sb.append(" and t.flag  is not null");
			}
			
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.eventtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.eventtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.eventtime) < to_date(?,'yyyy-mm-dd')");
			}
			Query query = session.createQuery(sb.toString());
			
		
			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				System.out.println("aaaaaaaa");
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				System.out.println("bbbbbbbbbb");
				query.setString(i, "" + endtime + "");
				i++;
			}
			List list = query.list();
			System.out.println("countxiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
			else return null;
}
			
		});			
	}
	
	public List countDangHistory1(final String fuwu,final String baoxian,final String startime,final String endtime
			,final String flag ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from ServiceType t where 1=1");
			if(!fuwu.equals("")){
				sb.append(" and t.servicetype = '"+fuwu+"'");
			}
			if(!baoxian.equals("")){
				sb.append(" and t.servicetype like '"+baoxian+"%'");
			}
			if(!flag.equals("")){
				sb.append(" and t.flag  is not null");
			}
			
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.eventtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.eventtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.eventtime) < to_date(?,'yyyy-mm-dd')");
			}
			Query query = session.createQuery(sb.toString());
			
		
			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				System.out.println("aaaaaaaa");
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				System.out.println("bbbbbbbbbb");
				query.setString(i, "" + endtime + "");
				i++;
			}
			List list = query.list();
			System.out.println("countxiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
			else return null;
}
			
		});			
	}
	
	public List findAll11() {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from His_view t order by t.id";
			Query query = session.createQuery(hql);
			System.out.println(query.list().size());
			return query.list();
}
			
		});
	}

	
	
	/*
	 * 根据来电号码查询所有来电历史
	 */
	
	public List findByCallid(final String callid) {
		System.out.println("callid==?????>>>"+callid);
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from History t where t.callid='"+callid+"' order by t.endtime desc";
			Query query = session.createQuery(hql);
			System.out.println("History find by telphoneid =+>>>"+query.list().size());
			return query.list();
}
			
		});	
	}
	
	/*
	 * 根据来电号码查询所有来电历史
	 */
	
	public List findByGonghao(final String gonghao) {
		System.out.println("gonghao===>>>"+gonghao);
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from History t where t.gonghao='"+gonghao+"' and trunc(sysdate)=trunc(t.endtime) order by t.endtime desc";
			Query query = session.createQuery(hql);
			System.out.println("aaaaaaaaaaaaaaaaa"+query.list().size());
			return query.list();
}
			
		});	
	}
	
	
	public Luyin_his getDataByID(final Long id) throws HibernateException{
		return (Luyin_his) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Luyin_his history=new Luyin_his();
			String hql = "from Luyin_his t where t.callid='"+id+"'";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("history query by id!!!");
			
			if(list!=null&&list.size()!=0){
				history=(Luyin_his)list.get(0);
				return history;
			}
			else return null;
}
			
		});	
	}
	
	public Luyin1 getTDataByID(final Long id) throws HibernateException{
		return (Luyin1) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Luyin1 history=new Luyin1();
			String hql = "from Luyin1 t where t.callid='"+id+"'";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("history query by id!!!");
			
			if(list!=null&&list.size()!=0){
				history=(Luyin1)list.get(0);
				return history;
			}
			else return null;
}
			
		});	
	}
	
	public HisServiceType getDataBycallid(final String callid) throws HibernateException{
		return (HisServiceType) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				HisServiceType history=new HisServiceType();
			String hql = "from HisServiceType t where t.callid='"+callid+"'";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("history query by id!!!");
			
			if(list!=null&&list.size()!=0){
				history=(HisServiceType)list.get(0);
				return history;
			}
			else return null;
}
			
		});	
	}
	
	public List gettDataBycallid(final String callid) throws HibernateException{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from ServiceType t where t.callid='"+callid+"'";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("history query by id!!!");
			
			if(list!=null&&list.size()!=0){
				return list;
			}
			else return null;
}
			
		});	
	}
	
	public ServiceType gettDataBycallid1(final String callid) throws HibernateException{
		return (ServiceType) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from ServiceType t where t.callid='"+callid+"'";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("history query by id!!!");
			
			if(list!=null&&list.size()!=0){
				return (ServiceType)list.get(0);
			}
			else return null;
}
			
		});	
	}
	
	public List getListDataBycallid(final String callid) throws HibernateException{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				HisServiceType history=new HisServiceType();
			String hql = "from HisServiceType t where t.callid='"+callid+"'";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("history query by id!!!");
			
			if(list!=null&&list.size()!=0){
				return list;
			}
			else return null;
}
			
		});	
	}
	
	
	public Manualcall_log getDataBycallID(final String callid) throws HibernateException{
		return (Manualcall_log) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			Manualcall_log history=new Manualcall_log();
			String hql = "from Manualcall_log t where t.callid='"+callid+"'";
			Query query = session.createQuery(hql);
			List list = query.list();
			
			if(list!=null&&list.size()!=0){
				history=(Manualcall_log)list.get(0);
				return history;
			}
			else return null;
}
			
		});
	}
	
	public String[][] findAllByCallid(final String callid){
		return (String[][]) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String[][] strs=null;
			String hql = "select round(to_number(t.endteldt-t.hangupdt)*24*60*60)," +
					" round(to_number(t.endteldt-t.establisheddt)*1440*60)," +
					" round(to_number(t.endteldt-t.transdt)*24*60*60) " +
					" from Manualcall_log t where t.callid='"+callid+"'";
			
			Query query =session.createQuery(hql);
			//query.setFirstResult(0);
			//query.setMaxResults(10);
			List list = query.list();
			//System.out.println("list.size()==>>>"+list.size());			
			if(list!=null&&list.size()!=0){
					strs= new String[list.size()][3];
					for(int i=0;i<list.size();i++){
						Object[] obj= (Object[])list.get(i);
						strs[i][0]=StringUtil.trim(obj[0]);
						strs[i][1]=StringUtil.trim(obj[1]);
						strs[i][2]=StringUtil.trim(obj[2]);
					}
			}
			return strs;	
}
			
		});
	}
	
	
	
	public String[][] findAllOrdering(final String gonghao){
		return (String[][]) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String[][] strs=null;
			String hql = " select round(avg(round(to_number(t.endteldt-t.hangupdt)*24*60*60))),"+
						 " max(round(to_number(t.endteldt-t.hangupdt)*24*60*60)),"+
						 " min(round(to_number(t.endteldt-t.hangupdt)*24*60*60)),"+
						 " sum(round(to_number(t.endteldt-t.hangupdt)*24*60))" +
						 " from Manualcall_log t where t.userid=? and to_char(t.establisheddt,'YYYY-MM') = to_char(trunc(sysdate),'YYYY-MM')";
			
			Query query =session.createQuery(hql);
			query.setString(0, ""+gonghao+"");
			List list = query.list();
			if(list!=null){
					strs= new String[list.size()][4];
					//for(int i=0;i<list.size();i++){
						Object[] obj= (Object[])list.get(0);
						strs[0][0]=StringUtil.trim(obj[0]);
						strs[0][1]=StringUtil.trim(obj[1]);
						strs[0][2]=StringUtil.trim(obj[2]);
						strs[0][3]=StringUtil.trim(obj[3]);
				//	}
			}
			return strs;	
}
			
		});	
	}
	
	public List findCommentsByGonghao(final String gonghao) {
		System.out.println("gonghao R===>>>"+gonghao);
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from History t where t.gonghao=? and to_char(t.endtime,'YYYY-MM') = to_char(trunc(sysdate),'YYYY-MM')";
			Query query = session.createQuery(hql);
			query.setString(0, ""+gonghao+"");
			System.out.println("aaaaaaaaaaaaaaaaa"+query.list().size());
			return query.list();
}
			
		});		
	}	
	
	
	public List getByID(final String id) throws HibernateException{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from History t where t.id='"+id+"'";
			Query query = session.createQuery(hql);
			List list = query.list();
			System.out.println("121212aaaa");
			
			//if(list!=null&&list.size()!=0){
				return list;
			//}
		
}
					
		});				
		
	}
	
	public void addHisphone(final History pi) throws InfrastructureException {
		 HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.saveOrUpdate(pi);
				return null;
			}

		});	
	}
	
	
	public Summary getBySummaryid(final String phoneid){
		return (Summary) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from Summary t where t.callid='"+phoneid+"'";
			Query query = session.createQuery(hql);
			List list = query.list();
			if(list!=null&&list.size()!=0){
				Summary history= (Summary)list.get(0);		
				return history;
			}
			else return null;
			}
			
		});	
	}
	
	public Select1 getBySelect(final String phoneid){
		return (Select1) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from Select1 t where t.id='"+phoneid+"'";
			Query query = session.createQuery(hql);
			List list = query.list();
			if(list!=null&&list.size()!=0){
				Select1 history= (Select1)list.get(0);		
				return history;
			}
			else return null;
			}
			
		});	
	}
	
	public RECORDORIGINALDATA getByGonghaoPhoneid(final String phoneid){
		return (RECORDORIGINALDATA) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from RECORDORIGINALDATA t where t.reservedthree='"+phoneid+"' ";
			Query query = session.createQuery(hql);
			List list = query.list();
			if(list!=null&&list.size()!=0){
				RECORDORIGINALDATA history= (RECORDORIGINALDATA)list.get(0);		
				return history;
			}
			else return null;
	}
			
		});	
	}
	
	public Manualcall_log getTheLatestPhone(final String username){
		return (Manualcall_log) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from Manualcall_log t where t.userid = ? order by t.ringdt desc";
			Query query = session.createQuery(hql);
			query.setString(0, ""+username+"");
			query.setFirstResult(0);
			query.setMaxResults(1);
			List list = null;
			list = query.list();
			if(list!=null&&list.size()!=0){
				System.out.println("list.size() while find latest phone==>>>>"+list.size());
				Manualcall_log call_log = (Manualcall_log)list.get(0);
				return call_log;

			}
			else return null;
	}
			
		});	
	}
	
	public Integer findManualcall_logall(){
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from Manualcall_log t where trunc(t.ringdt)= trunc(sysdate)";
			Query query = session.createQuery(hql);
			List list = null;
			list = query.list();
			if(list!=null&&list.size()!=0){
				return new Integer(list.size());
			}
		
			else return new Integer(0);
	}
			
		});
	}
	
	public List findTopTenHistory(){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from History t order by t.endtime desc";
			Query query = session.createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(10);
			List list = query.list();
			System.out.println("find top ten history!!!");
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;
}
			
		});
	}
	
	public List findTopHistory(final int prepage,final int nextpage){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			String hql = "from History t  order by t.endtime desc";
			Query query = session.createQuery(hql);
			query.setFirstResult(prepage);
			query.setMaxResults(15);
			List list = query.list();
			System.out.println("find top ten history!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else return null;	
}
			
		});
	}
	
	public List findHistory(final String agentid,final String realname,final String comments,final Long recordlength1,final Long recordlength2,final String startime,final String endtime
			,final String flag,final String callerid,final int currpage,final int pagesize ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Luyin_his t where 1=1");
			if(!agentid.equals("")){
				sb.append(" and t.username = '"+agentid+"'");
			}
			if(!realname.equals("")){
				sb.append(" and t.realname = '"+realname+"'");
			}
			if(recordlength1!=null&&recordlength2!=null){
				sb.append(" and t.recordlength between "+recordlength1+" and "+recordlength2+"");
			}
			if(recordlength1!=null&&recordlength2==null){
				sb.append(" and t.recordlength > "+recordlength1+"");
			}
			if(recordlength1==null&&recordlength2!=null){
				sb.append(" and t.recordlength < "+recordlength2+"");
			}
			if(!flag.equals("")){
				sb.append(" and t.flag  is not null");
			}
			if(flag.equals("0")){
				sb.append(" and t.flag = is null");
			}
			if(!callerid.equals("")){
				sb.append(" and t.callerid = '"+callerid+"'");
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
			if(!comments.equals("")){
				sb.append(" and t.summary like ?");
			}
			sb.append(" order by t.startrecordtime desc");
			Query query = session.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				System.out.println("aaaaaaaa");
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				System.out.println("bbbbbbbbbb");
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!comments.equals("")){
				query.setString(i, "'%" + comments + "%'");
				i++;
			}
			if(currpage!=0){
				query.setFirstResult((currpage - 1) * pagesize);
				query.setMaxResults(pagesize);
			}
			
			
			List list = query.list();
			System.out.println("xiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
	
			else	return null;	
}
			
		});
	}
	
	public List findDangHistory(final String agentid,final String realname,final String comments,final Long recordlength1,final Long recordlength2,final String startime,final String endtime
			,final String flag,final String callerid,final int currpage,final int pagesize ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from Luyin1 t where 1=1");
			if(!agentid.equals("")){
				sb.append(" and t.username = '"+agentid+"'");
			}
			if(!realname.equals("")){
				sb.append(" and t.realname = '"+realname+"'");
			}
			if(recordlength1!=null&&recordlength2!=null){
				sb.append(" and t.recordlength between "+recordlength1+" and "+recordlength2+"");
			}
			if(recordlength1!=null&&recordlength2==null){
				sb.append(" and t.recordlength > "+recordlength1+"");
			}
			if(recordlength1==null&&recordlength2!=null){
				sb.append(" and t.recordlength < "+recordlength2+"");
			}
			if(!flag.equals("")){
				sb.append(" and t.flag is not null");
			}
			if(flag.equals("0")){
				sb.append(" and t.flag = is null");
			}
			if(!callerid.equals("")){
				sb.append(" and t.callerid = '"+callerid+"'");
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
			if(!comments.equals("")){
				sb.append(" and t.summary like '%" + comments + "%'");
			}
			sb.append(" order by t.startrecordtime desc");
			Query query = session.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				System.out.println("aaaaaaaa");
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				System.out.println("bbbbbbbbbb");
				query.setString(i, "" + endtime + "");
				i++;
			}
			if(currpage!=0){
				query.setFirstResult((currpage - 1) * pagesize);
				query.setMaxResults(pagesize);
			}
			
			
			List list = query.list();
			System.out.println("xiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
	
			else	return null;	
}
			
		});
	}
	
	public List findWaiHistory(final String agentid,final String realname,final Long recordlength1,final Long recordlength2,final String startime,final String endtime
			,final String flag,final String callerid,final int currpage,final int pagesize ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from RECORDORIGINALDATA t where t.directionflag='0'");
			if(!agentid.equals("")){
				sb.append(" and t.agentid = '"+agentid+"'");
			}
			if(!realname.equals("")){
				sb.append(" and t.agentid = '"+realname+"'");
			}
			if(recordlength1!=null&&recordlength2!=null){
				sb.append(" and t.recordlength between "+recordlength1+" and "+recordlength2+"");
			}
			if(recordlength1!=null&&recordlength2==null){
				sb.append(" and t.recordlength > "+recordlength1+"");
			}
			if(recordlength1==null&&recordlength2!=null){
				sb.append(" and t.recordlength < "+recordlength2+"");
			}
			if(!flag.equals("")){
				sb.append(" and t.flag is not null");
			}
			if(flag.equals("0")){
				sb.append(" and t.flag = is null");
			}
			if(!callerid.equals("")){
				sb.append(" and t.calledid = '"+callerid+"'");
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
			Query query = session.createQuery(sb.toString());
			

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
			if(currpage!=0){
				query.setFirstResult((currpage - 1) * pagesize);
				query.setMaxResults(pagesize);
			}
			
			
			List list = query.list();
			System.out.println("xiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
	
			else	return null;	
}
			
		});
	}
	
	public List findHistory1(final String fuwu,final String baoxian,final String startime,final String endtime
			,final String flag,final int currpage,final int pagesize ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from HisServiceType t where 1=1");
			if(!fuwu.equals("")){
				sb.append(" and t.servicetype = '"+fuwu+"'");
			}
			if(!baoxian.equals("")){
				sb.append(" and t.servicetype like '"+baoxian+"%'");
			} 
			if(!flag.equals("")){
				sb.append(" and t.flag  is not null");
			}
			
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.eventtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.eventtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.eventtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.eventtime desc");
			Query query = session.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				System.out.println("aaaaaaaa");
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				System.out.println("bbbbbbbbbb");
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult((currpage - 1) * pagesize);
			query.setMaxResults(pagesize);
			List list = query.list();
			System.out.println("xiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else	return null;
}
			
		});
	}
	
	public List findDangHistory1(final String fuwu,final String baoxian,final String startime,final String endtime
			,final String flag,final int currpage,final int pagesize ){
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			int i=0;
			StringBuffer sb = new StringBuffer();
			sb.append("from ServiceType t where 1=1");
			if(!fuwu.equals("")){
				sb.append(" and t.servicetype = '"+fuwu+"'");
			}
			if(!baoxian.equals("")){
				sb.append(" and t.servicetype like '"+baoxian+"%'");
			} 
			if(!flag.equals("")){
				sb.append(" and t.flag  is not null");
			}
			
			if(!startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.eventtime) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')");
			}
			else if(!startime.equals("")&&endtime.equals("")){
				sb.append(" and trunc(t.eventtime) between to_date(?,'yyyy-mm-dd') and trunc(sysdate)");
			}
			else if(startime.equals("")&&!endtime.equals("")){
				sb.append(" and trunc(t.eventtime) < to_date(?,'yyyy-mm-dd')");
			}
			sb.append(" order by t.eventtime desc");
			Query query = session.createQuery(sb.toString());
			

			if(!startime.equals("")&&!endtime.equals("")){
				query.setString(i, "" + startime + "");
				i++;
				query.setString(i, "" + endtime + "");
				i++;
			}
			else if(!startime.equals("")&&endtime.equals("")){
				System.out.println("aaaaaaaa");
				query.setString(i, "" + startime + "");
				i++;
			}
			else if(startime.equals("")&&!endtime.equals("")){
				System.out.println("bbbbbbbbbb");
				query.setString(i, "" + endtime + "");
				i++;
			}
			query.setFirstResult((currpage - 1) * pagesize);
			query.setMaxResults(pagesize);
			List list = query.list();
			System.out.println("xiangxi!!!"+list.size());
			
			if(list!=null&&list.size()!=0){
				return list;
			}
		
			else	return null;
}
			
		});
	}

}
