package com.wondersgroup.popedom.dao;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.BLOB;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.model.EBusinesstype;
import com.wondersgroup.falcon.question.model.EImportance;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestiontype;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.local.bo.DownloadPaperAndUserInfoRequest;
import com.wondersgroup.local.bo.DownloadPaperAndUserInfoResponse;
import com.wondersgroup.local.bo.E_paperquestions_ws;
import com.wondersgroup.local.bo.E_papers_ws;
import com.wondersgroup.local.bo.E_user_team_ws;
import com.wondersgroup.local.bo.Equestions_ws;
import com.wondersgroup.local.bo.GetPaperInfoRequest;
import com.wondersgroup.local.bo.GetPaperInfoResponse;
import com.wondersgroup.local.bo.HZ95_ws;
import com.wondersgroup.local.bo.PaperInfo;
import com.wondersgroup.local.bo.Tjobsubject_ws;
import com.wondersgroup.local.bo.User_ws;
import com.wondersgroup.popedom.bo.EExamArrange;
import com.wondersgroup.popedom.bo.EGroup;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.Euserauthorith;
import com.wondersgroup.popedom.bo.HZ95;
import com.wondersgroup.utils.WsUtil_yth;

public class DownloadPaperDao extends HibernateDaoSupport {

	/**
	 * 获取可以下载的试卷
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EPapers> getDownloadablePapers(){
		return (List<EPapers>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				String username = AcegiUtil.getUserDetails().getUsername();
				Criteria criteria = session.createCriteria(EUser.class);
				criteria.add(Restrictions.eq("username", username))
					.add(Restrictions.eq("enabled", (byte) 1));
				if(criteria.list().size()>0){
					EUser eUser = (EUser) criteria.list().get(0);
					String teamId = eUser.getColor();
					String sql = "select b.* from "+ProcedureUrl.E_PAPER_TEAM+" a,"+ProcedureUrl.E_PAPERS+" b " +
									"where a.team_id="+teamId+" and " +
									"a.sj_id=b.sj_id and " +
									"a.flag=0 and " +
									"a.download=0 ";
					Query query = session.createSQLQuery(sql).addEntity(EPapers.class);
					return query.list();
				}else{
					return null;
				}
			}
		});
	}
	/**
	 * 试卷从一体化下载
	 * @param sjid
	 * @param teamId
	 * @return
	 */
	public List<Object> downloadPaperFromYth(String sjid,String teamId){
		String driver = "oracle.jdbc.driver.OracleDriver";
		String strUrl = ProcedureUrl.PROC_URL_STRING;
		List<Object> returnList = new ArrayList<Object>();
		Connection conn = null;
		CallableStatement proc = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(strUrl, "","");

			proc = conn.prepareCall("{ call exam_downloadPaper(?,?,?,?) }");
			proc.setString(1, sjid);
			proc.setString(2, teamId);
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
	@SuppressWarnings("unchecked")
	public List<EPapers> get_xf_Papers() {
		return (List<EPapers>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String username = AcegiUtil.getUserDetails().getUsername();
				Criteria criteria = session.createCriteria(EUser.class);
				criteria.add(Restrictions.eq("username", username))
					.add(Restrictions.eq("enabled", (byte) 1));
				if(criteria.list().size()>0){
					EUser eUser = (EUser) criteria.list().get(0);
					String teamId = eUser.getColor();
					String sql = "select *  from e_papers@qd_jdzx where sj_id in( select sj_id from e_paper_team_xf@qd_jdzx where team_id ='"+teamId+"')";
					Query query = session.createSQLQuery(sql).addEntity(EPapers.class);
					return query.list();
				}else{
					return null;
				}
			}
		});
	}
	/**
	 * webservice方式获取要下载的试卷信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PaperInfo> getPaperInfo(){
		return (List<PaperInfo>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String username = AcegiUtil.getUserDetails().getUsername();
				Criteria criteria = session.createCriteria(EUser.class);
				criteria.add(Restrictions.eq("username", username))
					.add(Restrictions.eq("enabled", (byte) 1));
				if(criteria.list().size()>0){
					EUser eUser = (EUser) criteria.list().get(0);
					String teamId = eUser.getColor();
					if("".equals(teamId)||teamId==null){
						teamId="0";
					}
					//WsUtil_getPaperInfo util = new WsUtil_getPaperInfo();
					//JobTainningResponse2 response = util.getPaperInfo(teamId);
					GetPaperInfoResponse response = WsUtil_yth.getPaperInfo(new GetPaperInfoRequest(teamId));
					return response.getList();
				}else{
					return null;
				}
			}
		});
	}
	/*
	 * 下载试卷
	 * */
	public List<Object> downloaPaperFromJdzx(String sjid, String teamId) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String strUrl = ProcedureUrl.PROC_URL_STRING;
		List<Object> returnList = new ArrayList<Object>();
		Connection conn = null;
		CallableStatement proc = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(strUrl, "","");
			proc = conn.prepareCall("{ call exam_downloaPaperFromJdzx(?,?,?,?) }");
			proc.setString(1, sjid);
			proc.setString(2, teamId);
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
	
	public String deleteUserBySjid(final String sjid) throws Exception{
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String msg = "";
				try {
					String sql1 = "delete from hz95 where sj_id="+sjid;
					String sql2 = "delete from users where userbianhao="+sjid;
					Query query = session.createSQLQuery(sql1);
					Query query2 = session.createSQLQuery(sql2);
					query.executeUpdate();
					query2.executeUpdate();
					msg = "删除成功！";
				} catch (Exception e) {
					e.printStackTrace();
					msg = e.getMessage();
					throw e;
				}
				return msg;
			}
		});
	}
	
	public String deletePaperBySjid(String sjid,Connection conn) throws Exception{
		List<Object> returnList = new ArrayList<Object>();
		CallableStatement proc = null;
		try {
			proc = conn.prepareCall("{ call deletePaperInfo(?,?,?) }");
			proc.setString(1, sjid);
			proc.registerOutParameter(2, Types.INTEGER);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();
			returnList.add(proc.getLong(2));
			returnList.add(proc.getString(3));
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
		return (String) returnList.get(1);
	}
	
	/**
	 * webservice方式下载试题考生信息
	 * @return
	 */
	public String downloadPaperFromJdzxByWs(final String sjid) throws Exception{
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Exception {
				String msg = "";
				try {
					DownloadPaperAndUserInfoResponse response = WsUtil_yth.downloadPaperAndUserInfo(new DownloadPaperAndUserInfoRequest(sjid));
					List<Equestions_ws> qList = response.getqList();
					List<E_papers_ws> pList = response.getpList();
					List<E_paperquestions_ws> pqList = response.getPqList();
					List<E_user_team_ws> utList = response.getUtList();
					List<HZ95_ws> hzList = response.getHzList();
					List<User_ws> ulist1 = response.getuList1();
					List<User_ws> ulist2 = response.getuList2();
					List<Tjobsubject_ws> jList = response.getjList();
					
					long examid = paperSave(pList, session);
					if (examid==0) {
						msg = "试卷已经存在！请勿重复下载！";
					}else{
						tjobsubjectSave(jList, session);
						List<EQuestions> questionsList = getQuestions(qList);
						batchSave(session, questionsList);
						
						List<EPaperquestions> ePaperquestions = getEPaperquestions(pqList,questionsList,examid);
						batchSave(session, ePaperquestions);
						hz95Save(hzList, examid, session);
						userSave(ulist1, 0, "1", examid, session);//考生
						userSave(ulist2, 1, "3", examid, session);//教师机
						long teamid = Long.valueOf(ulist2.get(0).getColor());
						List<ELogMonitor> eLogMonitors = getELogMonitors(ulist1, examid);
						List<EExamArrange> eExamArranges = geteExamArranges(ulist1, ulist2, teamid, examid);
						
						batchSave(session, eLogMonitors);
						batchSave(session, eExamArranges);
						insertIntoAuth(ulist1, ulist2, session);
						userTeamSave(utList, session);
						msg = "下载成功！";
					}
				} catch (Exception e) {
					e.printStackTrace();
					msg = e.getMessage();
					session.close();
					throw e;
				}
				return msg;
			}
		});
	}
	public <T> void batchSave(Session session,List<T> list){
		for (T t : list) {
			session.saveOrUpdate(t);
		}
	}
	
	public void userTeamSave(List<E_user_team_ws> utList,Session session){
		for (E_user_team_ws eut : utList) {
			Criteria criteria = session.createCriteria(UserTeam.class);
			criteria.add(Restrictions.eq("teamId", eut.getTeam_id()));
			List<UserTeam> list = criteria.list();
			if (list.size()>0) {
				UserTeam ut = list.get(0);
				ut.setTeamName(eut.getTeam_name());
				ut.setDescription(eut.getDescription());
				ut.setPteamid(eut.getP_teamid());
				ut.setContactname(eut.getContactname());
				ut.setContactph(eut.getContactph());
				ut.setTeamaddress(eut.getTeamaddress());
				ut.setFlag(eut.getFlag());
				session.saveOrUpdate(ut);
			}else{
				UserTeam ut = new UserTeam();
				ut.setTeamId(eut.getTeam_id());
				ut.setTeamName(eut.getTeam_name());
				ut.setDescription(eut.getDescription());
				ut.setPteamid(eut.getP_teamid());
				ut.setContactname(eut.getContactname());
				ut.setContactph(eut.getContactph());
				ut.setTeamaddress(eut.getTeamaddress());
				ut.setFlag(eut.getFlag());
				session.save(ut);
			}
		}
	}
	
	public void tjobsubjectSave(List<Tjobsubject_ws> jList,Session session){
		for (Tjobsubject_ws tjob : jList) {
			Criteria criteria = session.createCriteria(Tjobsubject.class);
			criteria.add(Restrictions.eq("id_job", tjob.getId_job()));
			@SuppressWarnings("unchecked")
			List<Tjobsubject> list = criteria.list();
			if (list.size()>0) {
				return;
			}else{
				Tjobsubject tjobsubject = new Tjobsubject();
				BeanUtils.copyProperties(tjob, tjobsubject, new String[]{"id"});
				session.saveOrUpdate(tjobsubject);
			}
		}
	}
	public void insertIntoAuth(List<User_ws> uList1,List<User_ws> uList2,Session session){
		for (User_ws u1 : uList1) {
			Criteria criteria  = session.createCriteria(Euserauthorith.class);
			criteria.add(Restrictions.eq("user_id", u1.getUser_id()));
			List<Euserauthorith> list = criteria.list();
			if(list.size()>0){
				Euserauthorith user = list.get(0);
				user.setAuthority_id((long)27);
				session.saveOrUpdate(user);
			}else{
				Euserauthorith eu = new Euserauthorith((long)27, u1.getUser_id());
				session.save(eu);
			}
		}
		for (User_ws u2 : uList2) {
			Criteria criteria  = session.createCriteria(Euserauthorith.class);
			criteria.add(Restrictions.eq("user_id", u2.getUser_id()));
			List<Euserauthorith> list = criteria.list();
			if(list.size()>0){
				Euserauthorith user = list.get(0);
				user.setAuthority_id((long)165);
				session.saveOrUpdate(user);
			}else{
				Euserauthorith eu = new Euserauthorith((long)165, u2.getUser_id());
				session.save(eu);
			}
		}
	}
	public void hz95Save(List<HZ95_ws> hzList,long sjid,Session session){
		//String sql = "insert into hz95(JD_ID,JD_MC,SJ_ID,KS_GL,AAA131,KS_RS,AAE036)values(?,?,?,?,?,?,?)";
		for (HZ95_ws whz : hzList) {
			Criteria criteria = session.createCriteria(HZ95.class);
			criteria.add(Restrictions.eq("jd_id", whz.getJd_id()));
			List<HZ95> list = criteria.list();
			if (list.size()>0) {
				HZ95 hz95 = list.get(0);
				BeanUtils.copyProperties(whz, hz95,new String[]{"jd_id"});
				session.saveOrUpdate(hz95);
			}else{
				HZ95 hz95 = new HZ95();
				BeanUtils.copyProperties(whz, hz95);
				session.save(hz95);
			}
		}
	}
	public Long paperSave(List<E_papers_ws> list, Session session) {
		long sjid = 0;
		for (E_papers_ws ep : list) {
			Criteria criteria = session.createCriteria(EPapers.class);
			criteria.add(Restrictions.eq("sjId", ep.getSj_id()));
			List<EPapers> plist = criteria.list();
			if(plist.size()>0){
				return sjid;
			}else{
				String sql = "insert into e_papers(sj_id,sj_mc,sj_zf,sj_bhgfs,sj_djsx,"
						+ "sj_kksj,sj_yxqjzsj,sj_zjsj,sj_zt,sj_syzt,"
						+ "sj_bz,sj_cjgbzt,paper_type,sj_jjsj,sj_ljcf,"
						+ "sj_kslx,sj_zych,sj_fs,sj_nych,sj_dj,"
						+ "sj_ctfs,sj_gzid,sj_scyth,sj_cjhc) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				Query query  = session.createSQLQuery(sql);
				query.setParameter(0,ep.getSj_id() );
				query.setParameter(1, ep.getSj_mc());
				query.setParameter(2, ep.getSj_zf());
				query.setParameter(3, ep.getSj_bhgfs());
				query.setParameter(4, ep.getSj_djsx());
				query.setParameter(5, ep.getSj_kksj());
				query.setParameter(6, ep.getSj_yxqjzsj());
				query.setParameter(7, ep.getSj_zjsj());
				query.setParameter(8, ep.getSj_zt());
				query.setParameter(9, ep.getSj_syzt());
				query.setParameter(10, ep.getSj_bz());
				query.setParameter(11, ep.getSj_cjgbzt());
				query.setParameter(12, ep.getPaper_type());
				query.setParameter(13, ep.getSj_jjsj());
				query.setParameter(14, ep.getSj_ljcf());
				query.setParameter(15, ep.getSj_kslx());
				query.setParameter(16, ep.getSj_zych());
				query.setParameter(17, ep.getSj_fs());
				query.setParameter(18, ep.getSj_nych());
				query.setParameter(19, ep.getSj_dj());
				query.setParameter(20, ep.getSj_ctfs());
				query.setParameter(21, ep.getSj_gzid());
				query.setParameter(22, ep.getSj_scyth());
				query.setParameter(23, ep.getSj_cjhc());
				query.executeUpdate();
				sjid = ep.getSj_id();
			}
		}
		return sjid;
	}
	public void userSave(List<User_ws> uList, int enabled, String userstar,long sjid,Session session) {
		for (User_ws uw : uList) {
			String sql = "insert into users(USER_ID,USERNAME,REALNAME,PASSWORD,GROUP_ID,STATUS,ENABLED,"
					+ "USERTYPE,COLOR,VISIBLE,userbianhao,FLAG,userflag,iskaohe,USERSTAR,olduserbianhao)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Query query = session.createSQLQuery(sql);
			query.setParameter(0,uw.getUser_id());
			query.setParameter(1,uw.getUsername() );
			query.setParameter(2,uw.getRealname() );
			query.setParameter(3,uw.getPassword() );
			query.setParameter(4,(long)2 );
			query.setParameter(5, 1);
			query.setParameter(6, enabled);
			query.setParameter(7, "1");
			query.setParameter(8, uw.getColor());
			query.setParameter(9, 1);
			query.setParameter(10,String.valueOf(sjid));
			query.setParameter(11,1 );
			query.setParameter(12,uw.getUserflag() );
			query.setParameter(13,(byte) 1);
			query.setParameter(14, userstar);
			query.setParameter(15, uw.getOlduserbianhao());
			query.executeUpdate();
		}
	}
	
	public List<EExamArrange> geteExamArranges(List<User_ws> uList1,List<User_ws> uList2,long teamid,long examid){
		List<EExamArrange> list = new ArrayList<EExamArrange>();
		for (User_ws u1 : uList1) {
			EExamArrange e = new EExamArrange();
			e.setExamid(examid);
			e.setTeamid(new UserTeam(teamid));
			e.setUserid(new EUser(u1.getUser_id()));
			e.setUsertype("1");
			e.setExamtype("1");
			list.add(e);
		}
		for (User_ws u2 : uList2) {
			EExamArrange e = new EExamArrange();
			e.setExamid(examid);
			e.setTeamid(new UserTeam(teamid));
			e.setUserid(new EUser(u2.getUser_id()));
			e.setUsertype("2");
			e.setExamtype("1");
			list.add(e);
		}
		return list;
	}
	public List<ELogMonitor> getELogMonitors(List<User_ws> uList,long examid){
		List<ELogMonitor> list = new ArrayList<ELogMonitor>();
		for (User_ws u : uList) {
			ELogMonitor e = new ELogMonitor();
			e.setExamid(examid);
			e.setUserid(u.getUser_id());
			e.setState("1");
			e.setCheatflag("2");
			list.add(e);
		}
		return list;
	}
	
	public List<EUser> getEUsers(List<User_ws> uList,int enabled,String userstar,long sjid){
		List<EUser> list = new ArrayList<EUser>();
		for (User_ws uw : uList) {
			EUser u = new EUser();
			//u.setId(uw.getUser_id());
			u.setUsername(uw.getUsername());
			u.setRealname(uw.getRealname());
			u.setPassword(uw.getPassword());
			u.setUserflag(uw.getUserflag());
			u.setOlduserbianhao(uw.getOlduserbianhao());
			u.setColor(uw.getColor());
			u.setGroup(new EGroup((long)2));
			u.setStatus((byte) 1);
			u.setEnabled((byte) enabled);
			u.setUserType("1");
			u.setVisible((byte) 1);
			u.setUserbianhao(String.valueOf(sjid));
			u.setFlag((byte) 1);
			u.setIskaohe((byte) 1);
			u.setUserstar(userstar);
			list.add(u);
		}
		return list;
	}
	
	public List<HZ95> getHz95s(List<HZ95_ws> hzList,long sjid){
		List<HZ95> list = new ArrayList<HZ95>();
		for (HZ95_ws whz : hzList) {
			HZ95 hz = new HZ95();
			hz.setJd_id(whz.getJd_id());
			hz.setJd_mc(whz.getJd_mc());
			hz.setSj_id(sjid);
			hz.setKs_gl(whz.getKs_gl());
			hz.setKs_rs(whz.getKs_rs());
			hz.setAae036(whz.getAae036());
			hz.setAaa131(whz.getAaa131());
			list.add(hz);
		}
		return list;
	}
	public List<UserTeam> getEUserTeams(List<E_user_team_ws> utList){
		List<UserTeam> list = new ArrayList<UserTeam>();
		for (E_user_team_ws eut : utList) {
			UserTeam ut = new UserTeam();
			ut.setTeamId(eut.getTeam_id());
			ut.setPteamid(eut.getP_teamid());
			ut.setTeamName(eut.getTeam_name());
			ut.setTeamaddress(eut.getTeamaddress());
			ut.setDescription(eut.getDescription());
			ut.setFlag(eut.getFlag());
			ut.setContactname(eut.getContactname());
			ut.setContactph(eut.getContactph());
			list.add(ut);
		}
		return list;
	}
	public List<EPaperquestions> getEPaperquestions(List<E_paperquestions_ws> pqlist,List<EQuestions> qList,long sjid){
		List<EPaperquestions> list = new ArrayList<EPaperquestions>();
		int i=1;
		for (EQuestions ques : qList) {
			EPaperquestions ep = new EPaperquestions();
			EPapers ePapers = new EPapers();
			ePapers.setSjId(sjid);
			ep.setEpapers(ePapers);
			ep.setEquestions(ques);
			ep.setSjStfs((ques.getStFz()==null)?(double)1:Double.valueOf(ques.getStFz()));
			ep.setSjStpx(i);
			i++;
			list.add(ep);
		}
		return list;
	}
	
	public List<EPapers> getPapers(List<E_papers_ws> pList){
		List<EPapers> list = new ArrayList<EPapers>();
		for (E_papers_ws ep : pList) {
			EPapers p = new EPapers();
			//p.setSjId(ep.getSj_id());
			p.setSjMc(ep.getSj_mc());
			p.setSjZf(((BigDecimal)ep.getSj_zf()).doubleValue());
			p.setSjBhgfs(((BigDecimal)ep.getSj_bhgfs()).doubleValue());
			p.setSjDjsx(ep.getSj_djsx());
			p.setSjKksj(ep.getSj_kksj());
			p.setSjYxqjzsj(ep.getSj_yxqjzsj());
			p.setSjZjsj(ep.getSj_zjsj());
			p.setSjZjrid(ep.getSj_zjrid());
			p.setModel(ep.getSj_model());
			p.setPaperType(ep.getPaper_type());
			p.setToUserId(ep.getTo_userid());
			p.setSjZt(ep.getSj_zt());
			p.setSjSyzt(ep.getSj_syzt());
			p.setSjBz(ep.getSj_bz());
			p.setSjCjgbzt(ep.getSj_cjgbzt());
			p.setSjSyrid(ep.getSj_syrid());
			p.setSjSysj(ep.getSj_sysj());
			p.setSjAdvice(ep.getSj_advice());
			p.setSjJjsj(ep.getSj_jjsj());
			p.setSjLjcf(ep.getSj_ljcf());
			p.setSjKslx(ep.getSj_kslx());
			p.setSjZych(ep.getSj_zych());
			p.setSjNych(ep.getSj_nych());
			p.setSjDj(ep.getSj_dj());
			p.setSjCtfs(ep.getSj_ctfs());
			p.setSjGzid(ep.getSj_gzid());
			p.setSjScyth(ep.getSj_scyth());
			p.setSjCjhc(ep.getSj_cjhc());
			list.add(p);
		}
		return list;
	}
	public List<EQuestions> getQuestions(List<Equestions_ws> qlist){
		List<EQuestions> list = new ArrayList<EQuestions>();
		for (Equestions_ws eq : qlist) {
			EQuestions q = new EQuestions();
			//q.setStId(eq.getSt_id());
			EImportance eImportance = new EImportance();
			eImportance.setId(eq.getSt_zyxid());
			q.setEimportance(eImportance);
			EBusinesstype ebt = new EBusinesstype();
			ebt.setId(eq.getSt_ywlxid());
			q.setEbusinesstype(ebt);
			EQuestiontype eqt = new EQuestiontype();
			eqt.setId(eq.getSt_lxid());
			q.setEquestiontype(eqt);
			q.setStCc(eq.getSt_cc());
			q.setStFjlj(eq.getSt_fjlj());
			q.setBxType(eq.getBx_type());
			q.setStTg(eq.getSt_tg());
			q.setStXx(eq.getSt_xx());
			q.setStDa(eq.getSt_da());
			q.setStDasm(eq.getSt_dasm());
			q.setStKszy(eq.getSt_kszy());
			q.setStLrsj(eq.getSt_lrsj());
			q.setStWh(eq.getSt_wh());
			q.setStJyxgcs(eq.getSt_jyxgcs());
			q.setStScbz(eq.getSt_scbz());
			q.setStNodeName(eq.getSt_node_name());
			q.setStNodeId(eq.getSt_node_id());
			q.setStModefy(eq.getSt_modefy());
			q.setStCheck(eq.getSt_check());
			q.setStSjid(eq.getSt_sjid());
			q.setStFz(eq.getSt_fz());
			q.setStImg(blobToBytes(eq.getSt_img()));
			q.setStImgA(blobToBytes(eq.getSt_img_a()));
			q.setStImgB(blobToBytes(eq.getSt_img_b()));
			q.setStImgC(blobToBytes(eq.getSt_img_c()));
			q.setStImgD(blobToBytes(eq.getSt_img_d()));
			q.setStImgE(blobToBytes(eq.getSt_img_e()));
			q.setStImgF(blobToBytes(eq.getSt_img_f()));
			q.setStImgG(blobToBytes(eq.getSt_img_g()));
			list.add(q);
		}
		return list;
	}

	private byte[] blobToBytes(BLOB blob) {
		if(blob==null){
			return null;
		}else{
			InputStream is = null;
			byte[] b = null;
			try {
				is = blob.getBinaryStream();
				b = new byte[(int) blob.length()];
				is.read(b);
				return b;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return b;
		}
	}
}
