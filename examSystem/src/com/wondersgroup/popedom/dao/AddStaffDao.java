package com.wondersgroup.popedom.dao;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.paper.model.JiaoShiJi;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.kaoshi.bo.Admission_card_file;
import com.wondersgroup.kaoshi.bo.Admission_card_pc;
import com.wondersgroup.kaoshi.bo.Admission_card_user;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.local.bo.ImportUserResponse;
import com.wondersgroup.local.bo.Temp_count;
import com.wondersgroup.local.bo.Temp_cur;
import com.wondersgroup.local.bo.Temp_cur_c;
import com.wondersgroup.local.bo.Temp_hf11;
import com.wondersgroup.local.bo.Temp_hf12;
import com.wondersgroup.popedom.bo.CalcBsFhDTO;
import com.wondersgroup.popedom.bo.ExamStaff;
import com.wondersgroup.popedom.bo.Exam_Arrangement;
import com.wondersgroup.popedom.bo.HZ95;
import com.wondersgroup.popedom.bo.ImportExamSysDTO;
import com.wondersgroup.popedom.bo.WsExamArrangeDTO;
import com.wondersgroup.utils.WsUtil_yth;
public class AddStaffDao extends HibernateDaoSupport {
	/**
	 * 考生人员新增
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  PageReturn addStaff(final PageTool pageTool,final String sj_mc)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				PageReturn pageReturn=new PageReturn();
				    StringBuffer qs = new StringBuffer();
				    qs.append("  select a.password , a.realname , a.username , e.sj_mc,  c.team_name ,  c.teamaddress ");
				    qs.append("  from users a, e_Exam_Arrange b, E_user_team c,e_papers e");
				    qs.append("  where a.user_id = b.userid and b.examtype = '1' and b.teamid = c.team_id  and b.examid=e.sj_id and a.userstar='1'");
				    if(!"".equals(sj_mc)){
				    	qs.append(" and e.sj_mc='"+sj_mc+"'");
				    	}
				    qs.append("group by a.password,a.realname,a.username,e.sj_mc,c.team_name,c.teamaddress");
				    qs.append(" order by a.password asc");
					Query queryObject = session.createSQLQuery(qs.toString());
					//queryObject.setParameter("SJ_MC", sj_mc);
					pageReturn.setTotal(queryObject.list().size());
					queryObject.setFirstResult(pageTool.getStart());
					 queryObject.setMaxResults(pageTool.getSize());
					List li=queryObject.list();
					List<ExamStaff> list = new ArrayList<ExamStaff>();
					Iterator<Object[]> it = li.iterator();
					while(it.hasNext()){
						Object[] o = it.next();
						ExamStaff e = new ExamStaff();
						e.setZkh((String)o[0]);
						e.setExamineename((String)o[1]);
						e.setIDnumber((String)o[2]);
						e.setExamid((String)o[3]);
						e.setExamroom((String)o[4]);
						e.setExamroomadress((String)o[5]);
						//e.setExamroomadress("山东路15号");
						list.add(e);
					} 
					
					pageReturn.setReturnList(list);
					return pageReturn; 

			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<ExamStaff> getExportInfo(final String sj_mc){
		return (List<ExamStaff>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				StringBuffer qs = new StringBuffer();
			    qs.append("  select a.password , a.realname , a.username , e.sj_mc,  c.team_name ,  c.teamaddress ");
			    qs.append("  from users a, e_Exam_Arrange b, E_user_team c,e_papers e");
			    qs.append("  where a.user_id = b.userid and b.examtype = '1' and b.teamid = c.team_id  and b.examid=e.sj_id and a.userstar='1'");
			    if(!"".equals(sj_mc)){
			    	qs.append(" and e.sj_mc='"+sj_mc+"'");
			    }
			    qs.append(" order by a.password asc");
				Query queryObject = session.createSQLQuery(qs.toString());
				List li=queryObject.list();
				List<ExamStaff> list = new ArrayList<ExamStaff>();
				Iterator<Object[]> it = li.iterator();
				while(it.hasNext()){
					Object[] o = it.next();
					ExamStaff e = new ExamStaff();
					e.setZkh((String)o[0]);
					e.setExamineename((String)o[1]);
					e.setIDnumber((String)o[2]);
					e.setExamid((String)o[3]);
					e.setExamroom((String)o[4]);
					e.setExamroomadress((String)o[5]);
					//e.setExamroomadress("山东路15号");
					list.add(e);
				} 
				return list;
			}
		});
	}
	
	/**
	 * 根据鉴定批次查询考生信息
	 */
	public  PageReturn queryExameen(final PageTool pageTool,final String jdpcId)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				PageReturn pageReturn=new PageReturn();
				    StringBuffer qs = new StringBuffer();
				    qs.append("  select a.password , a.realname , a.username , e.jd_mc,  c.team_name ,  c.teamaddress ");
				    qs.append("  from users a, e_Exam_Arrange b, E_user_team c,hz95 e");
				    qs.append("  where a.user_id = b.userid and b.examtype = '1' and b.teamid = c.team_id  and b.examid=e.jd_id and a.userstar='1'");
				    if(!"".equals(jdpcId)){
				    	qs.append(" and e.jd_mc like'"+jdpcId+"%'");
				    }
					Query queryObject = session.createSQLQuery(qs.toString());
					//queryObject.setParameter("SJ_MC", sj_mc);
					pageReturn.setTotal(queryObject.list().size());
					queryObject.setFirstResult(pageTool.getStart());
					 queryObject.setMaxResults(pageTool.getSize());
					List li=queryObject.list();
					List<ExamStaff> list = new ArrayList<ExamStaff>();
					Iterator<Object[]> it = li.iterator();
					while(it.hasNext()){
						Object[] o = it.next();
						ExamStaff e = new ExamStaff();
						e.setZkh((String)o[0]);
						e.setExamineename((String)o[1]);
						e.setIDnumber((String)o[2]);
						e.setExamid((String)o[3]);
						e.setExamroom((String)o[4]);
						e.setExamroomadress((String)o[5]);
						//e.setExamroomadress("山东路15号");
						list.add(e);
					} 
					
					pageReturn.setReturnList(list);
					return pageReturn; 

			}
		});
	}
	/**
	 * 通过web service 方式从一体化将考试信息导入到临时表，返回一个batch号
	 * @param pcid
	 * @return
	 */
	public String downloadUserInfo(final String pcid){
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			@Override
			public Object execute(Session session) throws Throwable {
				//WsUtil_download util = new WsUtil_download();
				//CompZpUploadResponse2 response = util.downloadUserFromYth(pcid);
				ImportUserResponse response = WsUtil_yth.getUserFromYth(pcid);
				List<Temp_cur> list1 = response.getList1();
				List<Temp_cur_c> list2 = response.getList2();
				List<Temp_count> list3 = response.getList3();
				List<Temp_hf12> list4 = response.getList4();
				List<Temp_hf11> list5 = response.getList5();
				String sql1 = "insert into temp_cur(AAC003,chz037,aac147,chz039,chz038,chz035,aac001,batch) values(?,?,?,?,?,?,?,?)";
				String sql2 = "insert into temp_cur_c(chz039,chz038,chz009,chz035,batch) values(?,?,?,?,?)";
				String sql3 = "insert into temp_count(countnum,chz035,batch) values(?,?,?)";
				String sql4 = "insert into Temp_hf12(Chz007,Chz001,Chz102,Aca111,Aac015,Chz010,Chz011,Chz012,Chz013,Chz014,Chz015,batch) values(?,?,?,?,?,?,?,?,?,?,?,?)";
				String sql5 = "insert into Temp_hf11(Chz001,Chz002,Chz003,Chz004,Chz005,Chz006,Chz015,batch) values(?,?,?,?,?,?,?,?)";
				Object[] objects;
				Date date = new Date();
				String batch = String.valueOf(date.getTime());
				for (Temp_cur t1 : list1) {
					objects = new Object[]{t1.getAac003(),t1.getChz037(),t1.getAac147(),t1.getChz039(),t1.getChz038(),t1.getChz035(),t1.getAac001(),batch};
					insertBySql(session, sql1, objects);
				}
				for (Temp_cur_c t2 : list2) {
					objects = new Object[]{t2.getChz039(),t2.getChz038(),t2.getChz009(),t2.getChz035(),batch};
					insertBySql(session, sql2, objects);
				}
				for (Temp_count t3 : list3) {
					objects = new Object[]{t3.getCountnum(),t3.getChz035(),batch};
					insertBySql(session, sql3, objects);
				}
				for (Temp_hf12 t4 : list4) {
					objects = new Object[]{t4.getChz007(),t4.getChz001(),t4.getChz102(),t4.getAca111(),t4.getAac015(),t4.getChz010(),t4.getChz011(),t4.getChz012(),t4.getChz013(),t4.getChz014(),t4.getChz015(),batch};
					insertBySql(session, sql4, objects);
				}
				for (Temp_hf11 t5 : list5) {
					objects = new Object[]{t5.getChz001(),t5.getChz002(),t5.getChz003(),t5.getChz004(),t5.getChz005(),t5.getChz006(),t5.getChz015(),batch};
					insertBySql(session, sql5, objects);
				}
				return batch;
			}
		});
	}
	
	public void insertBySql(Session session,String sql,Object[] parameter){
		Query query = session.createSQLQuery(sql);
		for(int i=0;i<parameter.length;i++){
			query.setParameter(i, parameter[i]);
		}
		query.executeUpdate();
	}
	/**
	 * 调用存过，安排webservice方式导入进来的考生
	 * @param dto
	 * @return
	 */
	public WsExamArrangeDTO WsExamArrange(WsExamArrangeDTO dto,Connection conn){
	    //String driver = "oracle.jdbc.driver.OracleDriver";  
	    //String strUrl = ProcedureUrl.PROC_URL_STRING;   
	    //Connection conn = null;  
	    CallableStatement proc = null;  
	    try {
	    	//conn = DriverManager.getConnection(strUrl, "","");  
	    	proc = conn.prepareCall("{ call ws_exam_Arrange(?,?,?,?) }");
	    	proc.setString(1, dto.getJdpcId());  
	    	proc.setString(2, dto.getBatch());
	    	proc.registerOutParameter(3, Types.INTEGER);  
	    	proc.registerOutParameter(4, Types.VARCHAR);  
	    	proc.execute();  
	    	dto.setRetCode(Long.valueOf(proc.getString(3)));
	    	dto.setRetMsg(proc.getString(4));
	    }  
	    catch (SQLException ex2) {
	    	dto.setRetCode(Long.valueOf("2"));
	    	dto.setRetMsg(ex2.getMessage());
	    	ex2.printStackTrace();  
	    }  
	    catch (Exception ex2) {  
	    	ex2.printStackTrace();
	    	dto.setRetCode(Long.valueOf("3"));
	    	dto.setRetMsg(ex2.getMessage());
	    }finally{
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
	    if(dto.getRetCode() != Long.valueOf("0")){
			throw new BusinessException(dto.getRetMsg());
		}
		return dto; 
	}
	public CalcBsFhDTO UploadExamneeInfo(CalcBsFhDTO validateDTO){
	    String driver = "oracle.jdbc.driver.OracleDriver";  
	    String strUrl = ProcedureUrl.PROC_URL_STRING;   
	    Connection conn = null;  
	    CallableStatement proc = null;  
	    try {
	    	Class.forName(driver);
	    	conn = DriverManager.getConnection(strUrl, "","");  
	    	proc = conn.prepareCall("{ call exam_Arrange(?,?,?) }");  
	    	proc.setString(1, validateDTO.getJdpcId());  
	    	proc.registerOutParameter(2, Types.INTEGER);  
	    	proc.registerOutParameter(3, Types.VARCHAR);  
	    	proc.execute();  
	    	validateDTO.setRetCode(Long.valueOf(proc.getString(2)));
	    	validateDTO.setRetMsg(proc.getString(3));
	    	return validateDTO;
	    }  
	    catch (SQLException ex2) {
	    	validateDTO.setRetCode(Long.valueOf("2"));
	    	validateDTO.setRetMsg(ex2.getMessage());
	    	ex2.printStackTrace();  
	    }  
	    catch (Exception ex2) {  
	    	ex2.printStackTrace();
	    	validateDTO.setRetCode(Long.valueOf("3"));
	    	validateDTO.setRetMsg(ex2.getMessage());
	    }finally{  
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
	    if(validateDTO.getRetCode() != Long.valueOf("0")){
			throw new BusinessException(validateDTO.getRetMsg());
		}
		return validateDTO; 
	}
	@SuppressWarnings("unchecked")
	public List<Exam_Arrangement> searchArrangements(final String sjmc) {
		return (List<Exam_Arrangement>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql2 = "select (select ut2.team_name from E_user_team ut2 where ut2.team_id=ut.p_teamid)," +
						"ut.team_name,u.username,u.password from " +
						"E_user_team ut, e_exam_arrange ea, E_PAPERS p, users u where " +
						"p.sj_mc = '"+sjmc+"' and p.sj_id = ea.examid and ea.usertype = '2' and " +
						"ea.teamid = ut.team_id and ea.userid = u.user_id";
				/*String sql2 = "select (select ut2.team_name from E_user_team ut2 where ut2.team_id=ut.p_teamid)," +
						"ut.team_name,u.username,u.password,hz90.aac003,hz92.hzz034 from " +
						"hz90 hz90, E_user_team ut, e_exam_arrange ea, E_PAPERS p, users u, hz92 hz92 where " +
						"p.sj_mc = '"+sjmc+"' and p.sj_id = ea.examid and ea.usertype = '2' and " +
						"ea.teamid = ut.team_id and ea.userid = u.user_id and ea.teamid = hz92.chz007 and " +
						"hz92.hzz021 = ea.examid and hz90.hzz001 = hz92.hzz001 and hz92.aaa131='1'";*/
				/*String sql = "select ut2.team_name, ut.team_name,u.username,u.password,hz90.aac003,hz92.hzz034 from " +
						"hz90 hz90,E_user_team ut left join (select * from e_user_team) ut2 on " +
						"ut.p_teamid=ut2.team_id,e_exam_arrange ea,E_PAPERS p,users u,hz92 hz92 where " +
						"p.sj_mc = '"+sjmc+"' and p.sj_id = ea.examid and " +
						"ea.teamid = ut.team_id and ut.team_id = hz92.chz007 and " +
						"hz90.hzz001 = hz92.hzz001 and u.user_id = ea.userid " +
						"and ea.usertype='2'";*/
				Query query = session.createSQLQuery(sql2);
				List<Object[]> list = query.list();
				Iterator<Object[]> it = list.iterator();
				List<Exam_Arrangement> list_e = new ArrayList<Exam_Arrangement>();
				while(it.hasNext()){
					Exam_Arrangement e = new Exam_Arrangement();
					Object[] o = it.next();
					e.setKdid((String)o[0]);
					e.setKcid((String)o[1]);
					e.setUsername((String)o[2]);
					e.setPassword((String)o[3]);
					/*e.setExpertName((String)o[4]);
					e.setJobTitle((String)o[5]);*/
					list_e.add(e);
				}
				return list_e;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<HZ95> findJdpc(){
		return (List<HZ95>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(HZ95.class);
				criteria.add(Restrictions.eq("ks_gl", "0"));
				criteria.add(Restrictions.eq("aaa131", "1"));
				return criteria.list();
			}
		});
	}
	public String findSjmc(final String sjmc){
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EPapers.class);
				criteria.add(Restrictions.eq("sjMc", sjmc));
				criteria.add(Restrictions.eq("sjZt", Long.valueOf(6l)));
				String sjid = "";
				if (criteria.list().size()>0) {
					EPapers e = (EPapers)criteria.list().get(0);
					sjid = String.valueOf(e.getSjId());
					return sjid;
				}else{
					return sjid;
				}
			}
		});
	}
	
	public boolean relateksAndkspc(final String pcid,final String checkid){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String[] ids = checkid.split(",");
				if(!"".equals(pcid)){
					try {
						for(int i=0;i<ids.length;i++){
							String sql1 = "update ADMISSION_CARD_FILE set pc_id="+pcid+" where id = "+ids[i]+"";
							Query query1 = session.createSQLQuery(sql1);
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
	
	public boolean relateJdpcAndSjmc(final String sjid,final String checkid){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String[] ids = checkid.split(",");
				if(!"".equals(sjid)){
					try {
						for(int i=0;i<ids.length;i++){
							String sql1 = "update users set userbianhao='"+sjid+"' where userbianhao='"+ids[i]+"'";
							String sql2 = "update e_Exam_Arrange set EXAMID='"+sjid+"' where EXAMID='"+ids[i]+"'";
							String sql3 = "update E_LOGMONITOR set EXAMID='"+sjid+"' where EXAMID='"+ids[i]+"'";
							String sql4 = "update hz95 set ks_gl='1', sj_id="+sjid+" where jd_id="+ids[i];
							Query query1 = session.createSQLQuery(sql1);
							Query query2 = session.createSQLQuery(sql2);
							Query query3 = session.createSQLQuery(sql3);
							Query query4 = session.createSQLQuery(sql4);
							query1.executeUpdate();
							query2.executeUpdate();
							query3.executeUpdate();
							query4.executeUpdate();
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
	public List<String> getJdpcBySjmc(final String sjmc){
		return (List<String>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql="select a.jd_mc from hz95 a,E_PAPERS b where a.sj_id=b.sj_id and b.sj_mc='"+sjmc+"'";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
//教师机关联 查询试卷名称
	@SuppressWarnings("unchecked")
	public List<JiaoShiJi> findsj(){
		return (List<JiaoShiJi>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
						//	String sql11="select b.sj_mc,sum(a.ks_rs), b.sj_id from hz95 a, E_PAPERS b  where a.ks_gl = 1 " +
						//				"and a.aaa131 = '1' and a.sj_id = b.sj_id and b.sj_zt=6   group by b.sj_mc,b.sj_id";
				String sql11="select t2.sjid,t2.sjmc,t1.rs from (select a.sj_id as sjid, sum(a.ks_rs) as rs from hz95 a" +
						" where a.aaa131 = '1' and a.ks_gl = '1'  group by a.sj_id) t1  join (select e.sj_id as sjid, e.sj_mc as sjmc" +
						" from e_papers e where    e.sj_zt='6' and   e.sj_id in (select a.sj_id from hz95 a    where a.aaa131 = '1'" +
						" and a.ks_gl = '1'  group by a.sj_id)and e.sj_id not in (select ea.examid as sjid  from e_exam_arrange ea" +
						"   where ea.kcid is not null)) t2  on (t1.sjid = t2.sjid) ";	
				
				Query query = session.createSQLQuery(sql11);
				
				List<JiaoShiJi> list = new ArrayList<JiaoShiJi>();
				List<Object[]> list2=query.list();
				for(int i=0;i<list2.size();i++){
					Object[] o = list2.get(i);
					JiaoShiJi js = new JiaoShiJi();
					js.setSj_id((Long.valueOf(String.valueOf(o[0]))));
					js.setSjMc((String)o[1]);
					js.setKs_rs(String.valueOf((BigDecimal)o[2]));
					list.add(js);
				}
				return list;
			}
		});
	}
	//教师机  试卷之间进行关联	
	public boolean relatesj(final String checkid){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String[] ids = checkid.split(",");
				if(!"".equals(ids)){
					try {
						
						for(int i=0;i<ids.length;i++){	
							String sql1 = "update e_Exam_Arrange set kcid='"+ids[0]+"' where examid='"+ids[i]+"'";							
							Query query1 = session.createSQLQuery(sql1);	
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
	public List<Object[]> printView(final String pcid){
		return (List<Object[]>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select a.ks_name,c.name,c.idcard,c.zkh,c.dw_name,c.kc_name,c.seat_no," +
						"a.kd_name,a.kd_address,a.kssj,a.jssj,a.major,a.rank from ADMISSION_CARD_PC a,ADMISSION_CARD_FILE b,ADMISSION_CARD_USER c " +
						"where a.id="+pcid+" and a.id=b.pc_id and b.id = c.file_id";
				Query query = session.createSQLQuery(sql);
				List<Object[]> list = query.list();
				return list;
			}
		});
	}
	
	public boolean deletePrintCard(final String checkedid){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String[] ids = checkedid.split(",");
				for(int i=0;i<ids.length;i++){
					String sql1 = "delete from ADMISSION_CARD_USER where file_id="+ids[i];
					String sql2 = "delete from ADMISSION_CARD_FILE where id="+ids[i];
					Query query1 = session.createSQLQuery(sql1);
					Query query2 = session.createSQLQuery(sql2);
					query1.executeUpdate();
					query2.executeUpdate();
				}
				return true;
			}
		});
	}
	
	public boolean deleteJdpc(final String checkedid) {
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String[] ids = checkedid.split(",");
					try {
						for(int i=0;i<ids.length;i++){
							String sql1 = "delete from hz95 where jd_id="+ids[i];
							String sql2 = "delete  from users where userbianhao="+ids[i];
							String sql3 = "delete  from e_exam_arrange where examid="+ids[i];
							String sql4 = "delete from  e_logmonitor where examid="+ids[i];
							String sql5 = "select * from  e_users_temp where jd_id ="+ids[i];
							String sql8 = "select * from E_USER_AUTHORITIES  where user_id in (select userid  from e_exam_arrange where examid='"+ids[i]+"')";
							List<String> list = session.createSQLQuery(sql5).list();
							List<String> list1 = session.createSQLQuery(sql8).list();
							if(list1.size()>0 && list1!=null)
							{
								String sql7 = "delete from E_USER_AUTHORITIES  where user_id in (select userid from  e_exam_arrange where examid='"+ids[i]+"')";
								Query query7 = session.createSQLQuery(sql7);
								query7.executeUpdate();
							}
							Query query1 = session.createSQLQuery(sql1);
							Query query2 = session.createSQLQuery(sql2);
							Query query3 = session.createSQLQuery(sql3);
							Query query4 = session.createSQLQuery(sql4);
							
							query1.executeUpdate();
							query2.executeUpdate();
							query3.executeUpdate();
							query4.executeUpdate();
							if(list!=null&&list.size()>0 )
							{
								String sql6 = "delete from e_users_temp where jd_id="+ids[i];
								Query query6 = session.createSQLQuery(sql6);
								query6.executeUpdate();
							}
						}
						return true;
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getDetailJdpc(final String jdid) {
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql ="select u.realname, u.username,u.password from  users u where u.userbianhao='"+jdid+"' and u.userstar='1'";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Admission_card_user> getDetailPrintCard(final String file_id){
		return (List<Admission_card_user>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql ="select * from ADMISSION_CARD_USER where file_id="+file_id;
				Query query = session.createSQLQuery(sql).addEntity(Admission_card_user.class);
				return query.list();
			}
		});
	}
	
	public boolean checkJdpc(final String pcid){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select * from hz95 where jd_mc like '"+pcid+"%'";
				Query query = session.createSQLQuery(sql);
				List<Object[]> list = query.list();
				if(list.size()>0){
					return true;
				}else{
					return false;
				}
			}
		});
	}
	/**
	 * 打印准考证 获取所有没关联的考试批次
	 * @return
	 * @author gkk
	 * @date 2016-12-6 上午11:33:39
	 */
	@SuppressWarnings("unchecked")
	public List<Admission_card_pc> find_admission_card_pc(){
		return (List<Admission_card_pc>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select * from ADMISSION_CARD_PC where valid='1' and id not in (select distinct(pc_id) from ADMISSION_CARD_FILE where valid='1' and pc_id is not null)";
				Query query = session.createSQLQuery(sql).addEntity(Admission_card_pc.class);
				
				return query.list();
			}
		});
	}
	/**
	 * 打印准考证 获取所有没关联的考生批次
	 * @return
	 * @author gkk
	 * @date 2016-12-6 上午11:33:05
	 */
	@SuppressWarnings("unchecked")
	public List<Admission_card_file> find_admission_card_file(){
		return (List<Admission_card_file>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select * from ADMISSION_CARD_file where valid='1' and pc_id is null";
				Query query = session.createSQLQuery(sql).addEntity(Admission_card_file.class);
				return query.list();
			}
		});
	}
	
	public boolean deletePrintCardInfo(final String id){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "update ADMISSION_CARD_PC a set a.valid=0 where a.id=?";
				Query query = session.createSQLQuery(sql).setLong(0, Long.valueOf(id));
				query.executeUpdate();
				return true;
			}
		});
	}
	
	public ImportExamSysDTO importExamSys(ImportExamSysDTO dto, Connection conn) {
		 CallableStatement proc = null;  
		    try {
		    	proc = conn.prepareCall("{ call import_to_exam_sys(?,?,?) }");
		    	proc.setString(1, dto.getPcId());  
		    	proc.registerOutParameter(2, Types.INTEGER);  
		    	proc.registerOutParameter(3, Types.VARCHAR);  
		    	proc.execute();  
		    	dto.setRetCode(Long.valueOf(proc.getString(2)));
		    	dto.setRetMsg(proc.getString(3));
		    }  
		    catch (SQLException ex2) {
		    	dto.setRetCode(Long.valueOf("2"));
		    	dto.setRetMsg(ex2.getMessage());
		    	ex2.printStackTrace();  
		    }  
		    catch (Exception ex2) {  
		    	ex2.printStackTrace();
		    	dto.setRetCode(Long.valueOf("3"));
		    	dto.setRetMsg(ex2.getMessage());
		    }finally{
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
			return dto; 
	}

}
