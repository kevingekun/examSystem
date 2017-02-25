package com.wondersgroup.falcon.jdys.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.Tdjobexamdot;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.kaoshi.bo.Tkcategory;

public class JdDAO extends HibernateDaoSupport{

	@SuppressWarnings("unchecked")
	public  List<Object> searchByGzAndDj(final String gz,final  int dj)throws Exception{
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String dengji="";
				switch (dj) {
				case 1:
					dengji = "rank1pent";
					break;
				case 2:
					dengji = "rank2pent";
					break;
				case 3:
					dengji = "rank3pent";
					break;
				case 4:
					dengji = "rank4pent";
					break;
				case 5:
					dengji = "rank5pent";
					break;
				case 6:
					dengji = "specialpent";
					break;
				default:
					break;
				}
				/*String s = "select ccz137,l1name,l2name,"+dengji+" from Tdjobexamdot where ccz137='"+gz+"'";
				
				Query query = this.getSession().createSQLQuery(s).addEntity(Tdjobexamdot.class);
				@SuppressWarnings("unchecked")*/
				/*System.out.println(gz);
				String hql = "from Tdjobexamdot where ccz137='"+gz+"'";
				Query query =  this.getSession().createQuery(hql);
				List<Object> list = query.list();
				System.out.println("99999"+list.size());*/
				Criteria criteria = session.createCriteria(Tdjobexamdot.class);
				
				ProjectionList projectionList = Projections.projectionList();
				projectionList.add(Projections.property("jdysid"))
				.add(Projections.property("l1name"))
				.add(Projections.property("l2name"))
				.add(Projections.property("dotname"));
				if(!"".endsWith(dengji)){
					projectionList.add(Projections.property(dengji));
				}
				criteria.setProjection(projectionList).add(Restrictions.eq("ccz137", gz))
				//.add(Restrictions.isNotNull(dengji)).add(Restrictions.eq("aaa131", "1")).addOrder(Order.asc("l1name"));
				.add(Restrictions.eq("aaa131", "1")).addOrder(Order.asc("l1name"));
				List<Object> list = new ArrayList<Object>();
				list = criteria.list();
				return list;
				//return list; 

			}
		});
	}
	/**
	 * 新增题库记录
	 * @param tjobsubject
	 */
	public void saveOrUpdateTk(final Tjobsubject tjobsubject){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.saveOrUpdate(tjobsubject);
				return null;
			}
		});
	}
	
	/**
	 * 新增鉴定要素
	 * @param tdjobexamdot
	 */
	public void saveOrUpdateJdys(final Tdjobexamdot tdjobexamdot){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.saveOrUpdate(tdjobexamdot);
				return null;
			}
		});
	}
	/**
	 * 删除鉴定要素
	 */
	public void changeState(final String[] s){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				for(int i=0;i<s.length;i++){
					String sql = "update TDJOBEXAMDOT t set t.aaa131='0' where t.jdysid="+s[i];
					Query query = session.createSQLQuery(sql);
					query.executeUpdate();
				}
				return null;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getRelatedPaper(){
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select e.sj_mc,e.sj_id from E_PAPERS e where e.sj_zt=6 and e.sj_id in (select a.sj_id from hz95 a where a.ks_gl=1 and a.aaa131='1')";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getRelatedJdpc(final String sjid){
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select a.jd_id,a.jd_mc,a.sj_id,a.ks_rs from hz95 a where a.aaa131='1' and a.sj_id="+sjid;
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	public boolean removeJdpc(final String jdid){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql1 = "update users u set u.userbianhao='"+jdid+"' where u.olduserbianhao='"+jdid+"'";
				String sql2 = "update e_exam_arrange e set e.examid="+jdid+",e.kcid='' where " +
						"e.examid in (select a.sj_id from hz95 a where a.jd_id="+jdid+") and " +
						"e.userid in (select b.user_id from users b where b.olduserbianhao="+jdid+")";
				String sql3 = "update E_LOGMONITOR e set e.examid="+jdid+" where " +
						"e.examid in (select a.sj_id from hz95 a where a.jd_id="+jdid+") and " +
						"e.userid in (select b.user_id from users b where b.olduserbianhao="+jdid+")";
				String sql4 = "update hz95 a set a.sj_id=null,a.ks_gl='0' where a.jd_id="+jdid;
				try {
					Query query1 = session.createSQLQuery(sql1);
					Query query2 = session.createSQLQuery(sql2);
					Query query3 = session.createSQLQuery(sql3);
					Query query4 = session.createSQLQuery(sql4);
					query1.executeUpdate();
					query2.executeUpdate();
					query3.executeUpdate();
					query4.executeUpdate();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getRelatekc() {
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql1 ="select distinct kcid from e_exam_arrange e, e_Papers t  where t.sj_id = e.examid " +
						" and e.kcid is not null   and t.sj_zt='6'";
				Query query = session.createSQLQuery(sql1);
				return query.list();
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getRelatedsj(final String kcid) {

		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select c.sj_mc, sum(a.ks_rs) ,c.sj_id from hz95 a, e_Papers c  where " +
						" c.sj_id = a.sj_id  and c.sj_id in(select distinct examid from e_exam_arrange where kcid="+kcid+" )group by c.sj_mc,c.sj_id";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	
	}
	public boolean removesj(final String sjid) {
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql1 ="update e_Exam_Arrange set kcid =null where examid='"+sjid+"'";			
				try {
					Query query1 = session.createSQLQuery(sql1);
					query1.executeUpdate();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		});	
	}	
	@SuppressWarnings("unchecked")
	public List<Object[]> changepaper(final  String sjid) {
		return (List<Object[]>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select  t.sj_mc,t.sj_zf,t.sj_bhgfs,t.sj_djsx,to_char(t.sj_kksj,'yyyy-mm-dd'),to_char(t.sj_yxqjzsj,'yyyy-mm-dd'),sj_zt ,t.sj_kslx,t.sj_jjsj ,t.sj_ljcf from e_papers t " +
						"where  t.sj_id="+sjid;
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	
	public String updatePaper(final String sjid, final String sjmc, final String sjzf,
			final String bhgfs, final String djsx,final  String kksj,final  String yxqjzsj,final  String zt,
			final String sj_kslx,final  String jjsj, final String ljcf) {
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select q.surplus  from e_logmonitor q where q.examid= '"+sjid+"'";
			    List<String> list = session.createSQLQuery(sql).list();
			    String sql1="";
			    Boolean flag=true;
			    for (int i=0;i<list.size();i++){
			    	  if (list.get(i) == null) {
			    		  flag=true&flag;
			    	  }else{
			    		  flag=false&flag;
			    	  }
			    	}
			    if(flag){
			    	 sql1 = "update e_papers t set  t.sj_mc='"+sjmc+"',t.sj_zf='"+sjzf+"',t.sj_bhgfs='"+bhgfs+"',t.sj_djsx='"+djsx+"',t.sj_kksj=to_date('"+kksj+" 00:00:01','yyyy-MM-dd hh24:mi:ss'), " +
							"t.sj_yxqjzsj=to_date('"+yxqjzsj+" 23:59:59','yyyy-MM-dd hh24:mi:ss'),t.sj_zt='"+zt+"',t.sj_kslx='"+sj_kslx+"',t.sj_jjsj ='"+jjsj+"',t.sj_ljcf='"+ljcf+"' where t.sj_id='"+sjid+"'";
			    }
			    else{
			    	return "fail";
			    }
				try {
					Query query1 = session.createSQLQuery(sql1);
					query1.executeUpdate();
					return "success";
				} catch (Exception e) {
					e.printStackTrace();
					return "error";
				}
			}
		});
	}
	public String clearTime(final String sjid) {
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select q.surplus  from e_logmonitor q where q.examid= '"+sjid+"'";
			    List<String> list = session.createSQLQuery(sql).list();
			    String sql1="";
			    Boolean flag=true;
			    for (int i=0;i<list.size();i++){
			    	  if (list.get(i) == null) {
			    		  flag=true&flag;
			    	  }else {
			    		  flag=false&flag;
			    	  }
			    	}
			    if(flag){
			    	 sql1 = "update e_logmonitor set startdate =null where examid='"+sjid+"'";
			    }
			    else{
			    	return "fail";
			    	}
				try {
					Query query1 = session.createSQLQuery(sql1);
					query1.executeUpdate();
					return "success";
				} catch (Exception e) {
					e.printStackTrace();
					return "error";
				}
			}
		});
	}
	
	public Tdjobexamdot findJdysById(final Integer id){
		return (Tdjobexamdot) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(Tdjobexamdot.class);
				criteria.add(Restrictions.eq("jdysid", Long.valueOf(id)));
				Tdjobexamdot t = (Tdjobexamdot) criteria.uniqueResult();
				return t;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Tjobsubject> findJdysByJobsubjectname(final String jobsubjectname){
		return (List<Tjobsubject>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(Tjobsubject.class)
						.add(Restrictions.eq("jobsubjectname", jobsubjectname));
				List<Tjobsubject> list = criteria.list();
				return list;
			}
		});
	}
	
	public String tkCategoryAdd(final Tkcategory t){
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				if("".equals(t.getName())){
					return "请输入题库名称！";
				}else{
					try {
						session.save(t);
						return "新增成功！";
					} catch (Exception e) {
						e.printStackTrace();
						return "新增失败！错误信息："+e.getMessage();
					}
				}
			}
		});
	}
	
	public String gzCheck(final String gzmc,final String gzid){
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select * from cz70 where ccz137=?";
				String sql2 = "select * from cz70 where ccz136=?";
				Query query = session.createSQLQuery(sql).setString(0, gzid);
				Query query2 = session.createSQLQuery(sql2).setString(0, gzmc);
				String rtmsg = "0";
				if(query.list().size()>0){
					rtmsg = rtmsg + "1";
				}
				if(query2.list().size()>0){
					rtmsg = rtmsg + "2";
				}
				return rtmsg;
			}
		});
	}
	public String gzAdd(final String gzmc,final String gzid){
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "insert into cz70 select ?,?,'','','','' from dual";
				Query query = session.createSQLQuery(sql).setParameter(0, gzid).setParameter(1, gzmc);
				query.executeUpdate();
				return "success";
			}
		});
	}
}
