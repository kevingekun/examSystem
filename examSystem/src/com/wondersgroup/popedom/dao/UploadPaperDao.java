package com.wondersgroup.popedom.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import oracle.sql.TIMESTAMP;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sun.tools.javac.v8.tree.Tree.NewArray;
import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.kaoshi.bo.EPaperTeam;
import com.wondersgroup.kaoshi.bo.E_paper_team_xf;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;

public class UploadPaperDao extends HibernateDaoSupport {

	public boolean relatePaperAndTeam(final String sjid,final String checkid){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String[] ids = checkid.split(",");
				if(!"".equals(sjid)){
					try {
						for(int i=0;i<ids.length;i++){
							EPaperTeam ePaperTeam = new EPaperTeam();
							ePaperTeam.setSj_id(new Long(sjid));
							ePaperTeam.setTeam_id(new Long(ids[i]));
							ePaperTeam.setFlag(new Integer(0));
							ePaperTeam.setDownload(new Integer(0));
							ePaperTeam.setRelate_time(new Timestamp(new Date().getTime()));
							ePaperTeam.setUpload(new Integer(0));
							session.save(ePaperTeam);
						}
						return true;
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}else{
					return false;
				}
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getRelatedPaper() {
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select tt.sj_mc,tt.sj_id from e_papers tt where tt.sj_id in ( select distinct  r.sj_id  from "+ProcedureUrl.E_PAPER_TEAM+" r where r.flag=0 and r.upload=0)";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	/**
	 * 获取已上传试卷
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getAlreadyUploadPaper(){
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				String sql = "select a.sj_mc,a.sj_id from e_papers a where a.sj_id in ( select distinct  r.sj_id  from "+ProcedureUrl.E_PAPER_TEAM+" r where r.flag=0 and r.upload=1)";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getRelatedteam( final String sjid) {

		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select qw.team_name,qw.team_id from e_user_team  qw where qw.team_id in (select y.team_id from "+ProcedureUrl.E_PAPER_TEAM+" y where y.sj_id='"+sjid+"' and y.flag=0)";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	/**
	 * 根据已上传试卷查询已关联的鉴定所
	 * @param sjid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getAlreadyTeamDetails( final String sjid) {

		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select a.team_name,a.team_id," +
						"to_char(b.relate_time,'yyyy-mm-dd HH24:mm:ss')," +
						"to_char(b.upload_time,'yyyy-mm-dd HH24:mm:ss')," +
						"to_char(b.download_time,'yyyy-mm-dd HH24:mm:ss') " +
						"from e_user_team a,qdyth.e_paper_team@QDYTH b where " +
						"a.team_id=b.team_id and a.team_id in " +
						"(select y.team_id from "+ProcedureUrl.E_PAPER_TEAM+" y where y.sj_id='"+sjid+"' and y.flag=0)";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	
	public boolean remove_teamid(final String teamid ,final String sjid) {
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql1 = "delete from "+ProcedureUrl.E_PAPER_TEAM+" e where e.sj_id='"+sjid+"' and e.team_id='"+teamid+"'";
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
	/**
	 * 试卷信息上传
	 * @param sjid 试卷id
	 * @param idCard 上传者用户名
	 * @return
	 */
	public List<Object> uploadPaperToYth(String sjid,String idCard){
		String driver = "oracle.jdbc.driver.OracleDriver";
		String strUrl = ProcedureUrl.PROC_URL_STRING;
		List<Object> returnList = new ArrayList<Object>();
		Connection conn = null;
		CallableStatement proc = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(strUrl, "","");
			proc = conn
					.prepareCall("{ call exam_uploadpaper(?,?,?,?) }");
			proc.setString(1, sjid);
			proc.setString(2, idCard);
			proc.registerOutParameter(3, Types.INTEGER);
			proc.registerOutParameter(4, Types.VARCHAR);
			proc.execute();
			returnList.add(proc.getLong(3));
			returnList.add(proc.getString(4));
			return returnList;
		} catch (SQLException ex2) {
			returnList.add(Long.valueOf("2"));
			returnList.add(ex2.getMessage());
			ex2.printStackTrace();
		} catch (Exception ex2) {
			returnList.add(Long.valueOf("3"));
			returnList.add(ex2.getMessage());
			ex2.printStackTrace();
		} finally {
			if (proc != null) {
				try {
					proc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (returnList.get(0) != Long.valueOf("0")) {
			throw new BusinessException((String) returnList.get(1));
		}
		return returnList;
	}
	/*
	 * 试卷下放
	 */
	public boolean relatePaperAndTeam_xf(final  String sjid, final String checkid) {
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session1) throws Throwable {
				String[] ids = checkid.split(",");
				if(!"".equals(sjid)){
					try {
						for(int i=0;i<ids.length;i++){
							String sql1 = "insert into e_paper_team_xf select Seq_E_paper_team_xf.nextval," +
									"'"+sjid+"','"+ids[i]+"',sysdate,'1',null,'0',null,'0',null,null from dual";
							Query query1 = session1.createSQLQuery(sql1);
							query1.executeUpdate();
						}
						return true;
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}else{
					return false;
				}
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getRelatedPaper_xf() {
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select a.sj_mc, a.sj_id, sum(b.ks_rs) from e_papers a, hz95 b where" +
						" a.sj_id = b.sj_id and a.sj_id in " +
						"(select distinct r.sj_id from e_paper_team_xf r where r.flag = 1 and r.upload = 0) " +
						"group by a.sj_mc, a.sj_id";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getRelatedteam_xf(final String sjid) {
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				//String sql = "select qw.team_name,qw.team_id from e_user_team  qw where qw.team_id in (select team_id from e_paper_team_xf where sj_id='"+sjid+"' and flag=1 and download=0  and upload =0)";
				String sql = "select qw.station_name,qw.id from station_xf  qw where qw.id in (select team_id from e_paper_team_xf where sj_id='"+sjid+"' and flag=1 and download=0  and upload =0)";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	/**
	 * 根据下放鉴定所id查看下放记录
	 * @param station_id
	 * @return
	 * @author gkk
	 * @date 2017-2-8 下午2:17:17
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getStationRecordOfXf(final String station_id) {
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select a.sj_mc,to_char(b.upload_time,'yyyy-mm-dd HH24:mm:ss'),sum(c.ks_rs) from e_papers a,e_paper_team_xf b,hz95 c where a.sj_id=b.sj_id and b.team_id="+station_id+" and a.sj_id=c.sj_id group by a.sj_id,a.sj_mc,b.upload_time";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	public boolean remove_teamid_xf(final String teamid, final String sjid) {
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql1 = "delete from e_paper_team_xf e where e.sj_id='"+sjid+"' and e.team_id='"+teamid+"' and download=0 and upload=0";
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
	public String xf_paper(final String sjid) {
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql2 ="select * from e_exam_arrange o where o.examid='"+sjid+"' ";
				Query query = session.createSQLQuery(sql2);
				List  list1= query.list();
				if(list1.size()==0||null ==list1||list1.isEmpty()){
					return "No_users";
				}
				else {
				String sql1 = "update e_paper_team_xf o set o.upload='1' ,o.upload_time=sysdate where o.sj_id='"+sjid+"'";
				try {
					Query query1 = session.createSQLQuery(sql1);
					query1.executeUpdate();
					return "true";
				} catch (Exception e) {
					e.printStackTrace();
					return "false";
				}}
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getAlready_xf_Paper() {
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				//String sql = "select tt.sj_mc,tt.sj_id from e_papers tt where tt.sj_id in ( select distinct  r.sj_id  from  e_paper_team_xf r where r.flag=1 and r.upload=1 ) ";
				String sql = "select a.sj_mc, a.sj_id, sum(b.ks_rs) from e_papers a, hz95 b where" +
						" a.sj_id = b.sj_id and a.sj_id in " +
						"(select distinct r.sj_id from e_paper_team_xf r where r.flag = 1 and r.upload = 1) " +
						"group by a.sj_mc, a.sj_id order by a.sj_id desc";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getAlreadyDloadTeamDetails_xf(final String sjid) {

		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				/*String sql = "select a.team_name,a.team_id," +
						"to_char(b.relate_time,'yyyy-mm-dd HH24:mi:ss')," +
						"to_char(b.upload_time,'yyyy-mm-dd HH24:mi:ss')," +
						"to_char(b.download_time,'yyyy-mm-dd HH24:mi:ss')" +
						"from e_user_team a,e_paper_team_xf b where " +
						"a.team_id=b.team_id and a.team_id in " +
						"(select y.team_id from  e_paper_team_xf y where y.sj_id='"+sjid+"' and y.flag=1 ) and b.sj_id='"+sjid+"'";*/
				String sql = "select a.station_name,a.id," +
						"to_char(b.relate_time,'yyyy-mm-dd HH24:mi:ss')," +
						"to_char(b.upload_time,'yyyy-mm-dd HH24:mi:ss')," +
						"to_char(b.download_time,'yyyy-mm-dd HH24:mi:ss')" +
						"from station_xf a,e_paper_team_xf b where " +
						"a.id=b.team_id and a.id in " +
						"(select y.team_id from  e_paper_team_xf y where y.sj_id='"+sjid+"' and y.flag=1 ) and b.sj_id='"+sjid+"'";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
}
