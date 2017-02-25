/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.popedom.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.paper.model.EAnswerquestions;
import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.EAnswertemp;
import com.wondersgroup.popedom.bo.EExamArrange;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.utils.PingIp;

/**查询监控信息
 * @author mxk
 *
 */
public class MonitorDao extends HibernateDaoSupport {
	/**
	 * 查询监控信息
	 */
	
	@SuppressWarnings("unchecked")
	public List  queryMoitor() {
		return (List ) HibernateUtil
				.doInSession(new HibernateSessionCallback() {

					public Object execute(Session session) throws Throwable {
				        EUser user=((UserDetailsImpl)AcegiUtil.getUserDetails()).getUser();
						StringBuffer sqlbuffer = new StringBuffer();
						String sql = "select a.kcid from e_exam_arrange a where userid = '"+user.getId()+"'";
					    @SuppressWarnings("unchecked")
					    List<String> list = session.createSQLQuery(sql).list();
					    if(null==list || list.size()==0)
					    {
					    	sqlbuffer.append("SELECT T.LOGID, U.REALNAME, U.PASSWORD, T.EXAMID, T.IP,");
							sqlbuffer.append("  to_char(T.STARTDATE, 'yyyy-mm-dd hh24:mi:ss'),  to_char( T.ENDDATE,'yyyy-mm-dd hh24:mi:ss'),");
							sqlbuffer.append("  T.STATE, T.SURPLUS, T.REMARKS, T.CHEATFLAG,a.examid,U.User_id,e.dj_zf,count(k.userid) FROM E_LOGMONITOR T,e_exam_arrange a, USERS U left join E_ANSWERPAPER e ");
							sqlbuffer.append("  on u.password=e.user_sex left join e_answertemp k on u.user_id = k.userid WHERE U.USER_ID = T.USERID and u.user_id=a.userid and t.EXAMID=a.examid and a.examtype='1' and a.examid=(select examid from e_exam_arrange where userid='"+user.getId()+"')");
							sqlbuffer.append(" group by k.userid,T.LOGID,U.REALNAME,U.PASSWORD,T.EXAMID,T.IP,T.STARTDATE,T.ENDDATE,T.STATE,T.SURPLUS,T.REMARKS,T.CHEATFLAG,a.examid,U.User_id,e.dj_zf");
					    }
					    else{
					        if("".equals(list.get(0))||list.get(0)==null){
					        	sqlbuffer.append("SELECT T.LOGID, U.REALNAME, U.PASSWORD, T.EXAMID, T.IP,");
								sqlbuffer.append("  to_char(T.STARTDATE, 'yyyy-mm-dd hh24:mi:ss'),  to_char( T.ENDDATE,'yyyy-mm-dd hh24:mi:ss'),");
								sqlbuffer.append("  T.STATE, T.SURPLUS, T.REMARKS, T.CHEATFLAG,a.examid,U.User_id,e.dj_zf,count(k.userid) FROM E_LOGMONITOR T,e_exam_arrange a, USERS U left join E_ANSWERPAPER e ");
								sqlbuffer.append("  on u.password=e.user_sex left join e_answertemp k on u.user_id = k.userid WHERE U.USER_ID = T.USERID and u.user_id=a.userid and t.EXAMID=a.examid and a.examtype='1' and a.examid=(select examid from e_exam_arrange where userid='"+user.getId()+"')");
								sqlbuffer.append(" group by k.userid,T.LOGID,U.REALNAME,U.PASSWORD,T.EXAMID,T.IP,T.STARTDATE,T.ENDDATE,T.STATE,T.SURPLUS,T.REMARKS,T.CHEATFLAG,a.examid,U.User_id,e.dj_zf");
								
					        }
					        else{
					        	sqlbuffer.append("SELECT T.LOGID,U.REALNAME,U.PASSWORD,T.EXAMID,T.IP,");
					        	sqlbuffer.append("  to_char(T.STARTDATE, 'yyyy-mm-dd hh24:mi:ss'), to_char( T.ENDDATE,'yyyy-mm-dd hh24:mi:ss'),");
					        	sqlbuffer.append("  T.STATE, T.SURPLUS, T.REMARKS, T.CHEATFLAG,a.examid,U.User_id,e.dj_zf,count(k.userid) FROM E_LOGMONITOR T,e_exam_arrange a, USERS U left join E_ANSWERPAPER e ");
					        	sqlbuffer.append("  on u.password=e.user_sex left join e_answertemp k on u.user_id = k.userid WHERE U.USER_ID = T.USERID and u.user_id=a.userid and t.EXAMID=a.examid and a.examtype='1' and a.examid in (select distinct examid from e_exam_arrange where kcid in(select kcid from e_exam_arrange where userid = '"+user.getId()+"'))");
					        	sqlbuffer.append("  and u.userbianhao in (select distinct examid from e_exam_arrange where kcid in (select kcid from e_exam_arrange where userid ='"+user.getId()+"')) ");
					        	sqlbuffer.append(" group by k.userid,T.LOGID,U.REALNAME,U.PASSWORD,T.EXAMID,T.IP,T.STARTDATE,T.ENDDATE,T.STATE,T.SURPLUS,T.REMARKS,T.CHEATFLAG,a.examid,U.User_id,e.dj_zf");
					        }}
						SQLQuery squery = session.createSQLQuery(sqlbuffer
								.toString());
						List result = squery.list();
						 
						return result;
					}
				});
	}
	
	@SuppressWarnings("unchecked")
	public List<EPapers> getPaperInfo(){
		return (List<EPapers>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				EUser user=((UserDetailsImpl)AcegiUtil.getUserDetails()).getUser();
				String sql = "select a.* from e_papers a where a.sj_id in " +
						"(select b.examid  from e_exam_arrange b where b.kcid in " +
						"(select c.kcid from e_exam_arrange c where c.userid="+user.getId()+") " +
								"group by b.examid union select d.examid from e_exam_arrange d where d.userid = "+user.getId()+")";
				Query query = session.createSQLQuery(sql).addEntity(EPapers.class);
				List<EPapers> list = query.list();
				return list;
			}
		});
		
	}
	
	@SuppressWarnings("unchecked")
	public  List<EExamArrange> queryArrange(final String examid)throws Exception{
		return (List<EExamArrange>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria=session.createCriteria(EExamArrange.class);
				criteria.add(Restrictions.eq("examid", Long.valueOf(examid)));
				List<EExamArrange> list=criteria.list();
				return list; 

			}
		});
	}

		public List  queryArrangeUser(final String teamid,final String examid) {
			return (List ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							StringBuffer sqlbuffer = new StringBuffer();
							sqlbuffer
									.append("SELECT u.realname,u.username,u.password, e.usertype  FROM e_exam_arrange e ,users u where u.user_id=e.userid and e.examtype ='1' and  e.teamid ='");
							sqlbuffer.append(teamid);
							sqlbuffer.append("' and e.examid='");
							sqlbuffer.append(examid);
							sqlbuffer.append("'");
							SQLQuery squery = session.createSQLQuery(sqlbuffer
									.toString());
							List result = squery.list();
							return result;
						}
					});
		}
		/**
		 * 
		 * @param userid
		 * @param examid
		 * @param flag 是否清零0否1是
		 * @param cheatflag 是否作弊0否1是
		 * @param remarks
		 */
		public void updateQZJJ(String userid,String examid,String flag,String cheatflag,String remarks){
			
			//添加人员答题登记表
			MonitorDao dao=new MonitorDao();
			Date date=dao.updateLogmonitor(examid, userid, flag, cheatflag, remarks);
			if("0".equals(cheatflag)){
				EPapers ep=dao.queryPapers(examid);
				EUser user=dao.queryStudent(userid);
				String djid=dao.updateEanswerpaper(user, userid, ep,date);
				//获取当前人员答案信息
				Map<String,String> tempmap=dao.queryAnswerTemp(examid, userid);
				//获得当前考生的答案
				List<Object[]> answerlist=dao.queryTrueAnswer(examid,userid);
				EAnswerpaper eanswerpaper=dao.queryEAnswerpaper(djid);
				dao.saveAnswer(answerlist, tempmap, ep, eanswerpaper, examid);
			}
		}
		//查询强制交卷人员答题信息
		@SuppressWarnings("unchecked")
		public Map<String,String>  queryAnswerTemp(final String teamid,final String userid) {
			return (Map<String,String> ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {

							Criteria criteria=session.createCriteria(EAnswertemp.class);
							criteria.add(Restrictions.eq("examid", Long.valueOf(teamid)));
							criteria.add(Restrictions.eq("userid", Long.valueOf(userid)));
							List<EAnswertemp> list=criteria.list();
							System.out.println("临时答题表teamid："+teamid);
							System.out.println("临时答题表userid："+userid);
							System.out.println("临时答题表："+list.size());
							Map<String,String> map=new HashMap<String,String>();
							for (EAnswertemp eAnswertemp : list) {
								String qid=eAnswertemp.getQuestionid();
								if(map.get(qid)==null||map.get(qid)==""){
									map.put(qid, eAnswertemp.getAnswer());
								}else{
									String answerstring=map.get(qid);
									map.remove(qid);
									map.put(qid, answerstring+"||"+eAnswertemp.getAnswer());
								}
							}
							return map;
						
						}
					});
		}
		/*
		 * 获得试题正确答案
		 */
		public List  queryTrueAnswer(final String teamid,final String userid) {
			return (List ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							StringBuffer sqlbuffer = new StringBuffer();
							sqlbuffer
									.append("select p.sj_id,q.st_id,q.st_da ,z.sj_stfs from E_papers p ,E_Paperquestions z ,E_questions q where p.sj_id=z.sj_id and z.sj_stid=q.st_id and p.sj_id='");
							sqlbuffer.append(teamid);
							sqlbuffer.append("'");
							SQLQuery squery = session.createSQLQuery(sqlbuffer
									.toString());
							List result = squery.list();
							return result;
						}
					});
		}
		/*
		 * 更新监控表
		 */
		public Date  updateLogmonitor(final String examid,final String userid,final String flag,final String cheatflag,final String remarks) {
			return (Date ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							Criteria criteria=session.createCriteria(ELogMonitor.class);
							criteria.add(Restrictions.eq("examid", Long.valueOf(examid)));
							criteria.add(Restrictions.eq("userid", Long.valueOf(userid)));
							Date date=new Date();
							List<ELogMonitor> list=criteria.list();
							if( list.size()>0){
								ELogMonitor monitor=(ELogMonitor)list.get(0);
								monitor.setFlag(flag);
								monitor.setState("4");
								monitor.setIfxz("1");
								monitor.setCheatflag(cheatflag);
								monitor.setRemarks(remarks);
								date=monitor.getStartdate();
								session.update(monitor);
								
							}
							return date;
						}
					});
		}
		/*
		 * 查询参考人员信息
		 */
		public EUser  queryStudent(final String userid) {
			return (EUser ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							EUser user=(EUser)session.load(EUser.class, Long.valueOf(userid));
							return user;
						}
					});
		}
		/*
		 * 查询试卷信息
		 */
		public EPapers  queryPapers(final String examid) {
			return (EPapers ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							EPapers epapers=(EPapers)session.load(EPapers.class, Long.valueOf(examid));
							return epapers;
						}
					});
		}
		/*
		 * 查询试卷及试题信息
		 */
		public EAnswerpaper  queryEAnswerpaper(final String djid) {
			return (EAnswerpaper ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							EAnswerpaper epapers=(EAnswerpaper) session.load(EAnswerpaper.class, Long.valueOf(djid));
							return epapers;
						}
					});
		}
		/*
		 * 查询EPaperquestions
		 */
		public EPaperquestions  queryEPaperquestions(final String stid,final String examid) {
			return (EPaperquestions ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							Criteria criteria=session.createCriteria(EPaperquestions.class);
							criteria.add(Restrictions.eq("epapers.sjId", Long.valueOf(examid)));
							criteria.add(Restrictions.eq("equestions.stId", Long.valueOf(stid)));
							EPaperquestions  epapers=(EPaperquestions)criteria.list().get(0);
							return epapers;
						}
					});
		}
		/*
		 *保存登记信息
		 */
		public String  updateEanswerpaper(final EUser user,final String userid, final EPapers ep,final Date date) {
			return (String ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							EAnswerpaper eap=new EAnswerpaper();
							eap.setEpapers(ep);
							eap.setDjRyid(user.getUsername());
							eap.setDjJssj(new Date());
							eap.setDjZf(new Double(0.0));
							eap.setDjSyzt(new Long(0));
							eap.setDjCjpm(new Long(0));
							eap.setDjKssj(date);
							eap.setUserSex(user.getPassword());
							eap.setUserStar(userid);
							eap.setDjRymc(user.getRealname());
							eap.setGroupId(user.getGroup().getId().toString());
							session.save(eap);
							String djid=String.valueOf(eap.getDjId());
							return djid;
						}
					});
		}
		/*
		 * 保存答题信息
		 */
		public String  saveAnswer(final List<Object[]> answerlist,final Map<String,String> tempmap, final EPapers ep,final EAnswerpaper eanswerpaper,final String examid) {
			return (String ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							MonitorDao dao=new MonitorDao();
							//判断成绩并保存在答题表中
							for(int i=0;i<answerlist.size();i++){
								EPaperquestions epaperquestions=dao.queryEPaperquestions(answerlist.get(i)[1].toString(), examid);
								String mapkey=answerlist.get(i)[1].toString();
								if(tempmap.get(mapkey)==null||tempmap.get(mapkey)==""){
									EAnswerquestions answerq=new EAnswerquestions();
									
									answerq.setEanswerpaper(eanswerpaper);
									answerq.setStPx(new Long(0));
									answerq.setStDf(new Double(0.0));
									answerq.setEpaperquestions(epaperquestions);
									session.save(answerq);
								}else{
									EAnswerquestions answerq=new EAnswerquestions();
									answerq.setEanswerpaper(eanswerpaper);
									answerq.setStPx(new Long(0));
									if(answerlist.get(i)[2].toString().equals(tempmap.get(mapkey))){
										answerq.setStDa(tempmap.get(answerlist.get(i)[1]));
										answerq.setStDf(new Double(answerlist.get(i)[3].toString()));
									}else{
										answerq.setStDa(tempmap.get(mapkey));
										answerq.setStDf(new Double(0.0));
									}
									answerq.setEpaperquestions(epaperquestions);
									session.save(answerq);	
									
								}
							}
							return null;
						}
					});
		}
		
		/*
		 * 监控网络
		 */
		public String queryPingIp(){
			return (String ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							AcegiUtil acegiUtil=new AcegiUtil();
					          EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
							StringBuffer sqlbuffer = new StringBuffer();
							
							sqlbuffer.append("SELECT T.LOGID, U.REALNAME, U.USERNAME, T.EXAMID, T.IP,");
							sqlbuffer.append("  to_char(T.STARTDATE, 'yyyy-MM-dd hh:mm:ss'),  to_char( T.ENDDATE,'yyyy-MM-dd hh:mm:ss'),");
							sqlbuffer.append("  T.STATE, T.SURPLUS, T.REMARKS, T.CHEATFLAG,a.examid,U.User_id FROM E_LOGMONITOR T, USERS U,e_exam_arrange a ");
							sqlbuffer.append("  WHERE U.USER_ID = T.USERID and u.user_id=a.userid and a.teamid=(select teamid from e_exam_arrange where userid='"+user.getId()+"')");
							SQLQuery squery = session.createSQLQuery(sqlbuffer
									.toString());
							List<Object[]> result = squery.list();
							/* for(int i=0;i<result.size();i++){
								 Boolean flag=PingIp.ping(result.get(i)[4].toString(), 5, 5000);
								 if(flag){
									 System.out.println("网络状态良好");
								 }else{
									new MonitorDao().updateUserStats(result.get(i)[0].toString()); 
								 }
							 }*/
							return null;
						}
					});
		}
		public String updateUserStats(final String id){
			return (String ) HibernateUtil
					.doInSession(new HibernateSessionCallback() {
						public Object execute(Session session) throws Throwable {
							ELogMonitor logm=(ELogMonitor)session.load(ELogMonitor.class, Long.valueOf(id));
							logm.setState("3");
							session.update(logm);
							return null;
						}
					});
		}
		
		public String startExam(){
			return (String)HibernateUtil.doInSession(new HibernateSessionCallback() {
				@SuppressWarnings("static-access")
				public Object execute(Session session) throws Throwable {
					AcegiUtil acegiUtil=new AcegiUtil();
			        EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
			        String sql = "select e.startdate from E_LOGMONITOR e where e.examid=(select ea.examid from e_exam_arrange ea where ea.userid='"+user.getId()+"')";
			        @SuppressWarnings("unchecked")
					List<String> list = session.createSQLQuery(sql).list();
			        
			        String sql1 = "select a.kcid from e_exam_arrange a where userid = '"+user.getId()+"'";
				    @SuppressWarnings("unchecked")
				    List<String> list1 = session.createSQLQuery(sql1).list();
				       
			        if("".equals(list.get(0))||list.get(0)==null){
			        	Date date = new Date();
				        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        String date2 = sdf.format(date);
				        //没有进行教师机关联的 考试
				         if("".equals(list1.get(0))||list1.get(0)==null){				        	
				        	String hql4="update E_LOGMONITOR e set e.startdate=to_date('"+date2+"','yyyy-MM-dd HH24:mi:ss') " +
				        		"where e.examid=(select ea.examid from e_exam_arrange ea where ea.userid='"+user.getId()+"')";
				        	 try {
						        	session.createSQLQuery(hql4).executeUpdate();
						        	return "1";//操作成功
								} catch (Exception e) {
									e.printStackTrace();
									return "2";//操作失败
								}
				        					        }  //else  进行教师机关联的试卷
				        else{
				        String hql3 = "update E_LOGMONITOR e set e.startdate=to_date('"+date2+"','yyyy-MM-dd HH24:mi:ss') " +
				        		"where e.examid in(select  distinct ea.examid from e_exam_arrange ea where kcid in(select kcid from e_exam_arrange where userid = '"+user.getId()+"'))";
				        try {
				        	session.createSQLQuery(hql3).executeUpdate();
				        	return "1";//操作成功
						} catch (Exception e) {
							e.printStackTrace();
							return "2";//操作失败
						}
				        }
			        }
			        
			        else{
			        	return "3";//已经开始考试
			        }
				}
			});
		}
		public String startLogin(){
			return (String)HibernateUtil.doInSession(new HibernateSessionCallback() {
				@SuppressWarnings({ "static-access", "unchecked" })
				public Object execute(Session session) throws Throwable {
					AcegiUtil acegiUtil=new AcegiUtil();
			        EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
			        String hql1 = "select e.sj_zt from E_PAPERS e where e.sj_id=(select ea.examid from e_exam_arrange ea where ea.userid='"+user.getId()+"')";
			        List<BigDecimal> list = session.createSQLQuery(hql1).list();
			        
			        String sql11 = "select a.kcid from e_exam_arrange a where userid = '"+user.getId()+"'";
				    @SuppressWarnings("unchecked")
				    List<String> list11 = session.createSQLQuery(sql11).list();
			        
			        if(list.size()>0){
			        	String s = String.valueOf(list.get(0));
			        	if(!"1".equals(s)){//教师机点开始登陆，判断是否已经开始考试，若已开始则操作无效
			        		
			        			if("".equals(list11.get(0))||list11.get(0)==null){//判断教师机是否进行关联，kcid为空
			        				String hql = "update users e set e.enabled=1 where e.user_id in " +
					        		"(select u.user_id from users u,e_exam_arrange a where u.user_id=a.userid and " +
					        		"a.examid=(select ea.examid from e_exam_arrange ea where ea.userid='"+user.getId()+"'))";
			        				String hql2 = "update E_PAPERS e set e.sj_zt='1' where e.sj_id=(select ea.examid from e_exam_arrange ea where ea.userid='"+user.getId()+"')";
			        				try {
			        					session.createSQLQuery(hql).executeUpdate();
			        					session.createSQLQuery(hql2).executeUpdate();
			        					return "1";//可以登录
			        				} catch (Exception e) {
			        					e.printStackTrace();
			        					return "2";//执行出错
			        				}
			        		 }
			        			else{			        						
			        				String hql3 = "update users e set e.enabled=1 where e.user_id in " +
					        		"(select u.user_id from users u,e_exam_arrange a where u.user_id=a.userid and " +
					        		"a.examid  in" +
					        		"(select distinct ea.examid from e_exam_arrange ea where kcid in(select kcid from e_exam_arrange where userid = '"+user.getId()+"')))";
			        				String hql4 = "update E_PAPERS e set e.sj_zt='1' where e.sj_id  in" +
			        						"(select  distinct ea.examid from e_exam_arrange ea where kcid in(select kcid from e_exam_arrange where userid = '"+user.getId()+"'))";
			        				try {
			        					session.createSQLQuery(hql3).executeUpdate();
			        					session.createSQLQuery(hql4).executeUpdate();
			        					return "1";//可以登录
			        				} catch (Exception e) {
			        					e.printStackTrace();
			        					return "2";//执行出错
			        				}			        				}
			        	}else{
			        		return "3";//已经点过登录
			        	}
			        }else{
			        	return "4";//试题信息有误
			        }
				}
			});
		}
		public String forbiddenExam(){
			return (String)HibernateUtil.doInSession(new HibernateSessionCallback() {
				@SuppressWarnings("static-access")
				public Object execute(Session session) throws Throwable {
					AcegiUtil acegiUtil=new AcegiUtil();
			        EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
			        String hql = "update users e set e.enabled=0 where e.userstar=1 and e.user_id in " +
			        		"(select u.user_id from users u,e_exam_arrange a where u.user_id=a.userid and " +
			        		"a.teamid=(select ea.teamid from e_exam_arrange ea where ea.userid='"+user.getId()+"'))";
			        String hql2 = "update E_LOGMONITOR e set e.ifxz='1' where e.logindate=null and e.examid=" +
			        		"(select ea.examid from e_exam_arrange ea where ea.userid='"+user.getId()+"')";
			        //HibernateUtil.beginTransaction();
			        try {
			        	session.createSQLQuery(hql).executeUpdate();
				        session.createSQLQuery(hql2).executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
						//HibernateUtil.rollbackTransaction();
					}
			        //HibernateUtil.commitTransaction();
					return null;
				}
			});
		}
		
		public String cleanIp(final String userid,final String examid){
			return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
				public Object execute(Session session) throws Throwable {
					//清理冲突者的ip
					String sql = "update users u set u.putong='' where" +
							" u.user_id in (select e.userid from E_LOGMONITOR e where e.examid="+examid+")" +
							" and u.putong in (select e1.ip from E_LOGMONITOR e1 where e1.userid="+userid+")";
					//清理当前考生的ip
					String sql2 = "update users u set u.putong='' where u.user_id="+userid+"";
					try {
						Query query1 = session.createSQLQuery(sql);
						Query query2 = session.createSQLQuery(sql2);
						query1.executeUpdate();
						query2.executeUpdate();
						return "success";
					} catch (Exception e) {
						e.printStackTrace();
						return "failed";
					}
				}
			});
		}
		public String cleanExam(final String userid,final String examid){
			return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
				public Object execute(Session session) throws Throwable {
					String sql = "select * from e_answerpaper a where a.sj_id=? and a.user_star=? and a.dj_zf=0";
					Query query = session.createSQLQuery(sql).setString(0, examid).setString(1, userid);
					if(query.list().size()>0){
						String sql1 = "delete from E_ANSWERQUESTIONS a where a.dj_id = (select b.dj_id from e_answerpaper b where b.sj_id=? and b.user_star=? and b.dj_zf=0)";
						String sql2 = "delete from e_answerpaper a where a.sj_id=? and a.user_star=? and a.dj_zf=0";
						String sql3 = "update E_LOGMONITOR e set e.startdate=sysdate,e.state='2',e.enddate='' where e.examid=? and e.userid=?";
						String sql4 = "update users a set a.putong='' where a.user_id=?";
						Query query1 = session.createSQLQuery(sql1).setString(0, examid).setString(1, userid);
						Query query2 = session.createSQLQuery(sql2).setString(0, examid).setString(1, userid);
						Query query3 = session.createSQLQuery(sql3).setString(0, examid).setString(1, userid);
						Query query4 = session.createSQLQuery(sql4).setString(0, userid);
						query1.executeUpdate();
						query2.executeUpdate();
						query3.executeUpdate();
						query4.executeUpdate();
						return "success";
					}else{
						return "failed";
					}
				}
			});
		}
		/**
		 * 考生延时操作
		 * @param examid
		 * @param userid
		 * @param delayTime
		 * @return
		 */
		public String delayTime(final String examid,final String userid,final String delayTime,final String remarks){
			return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
				
				public Object execute(Session session) throws Throwable {
					try {
						ELogMonitor eLogMonitor = (ELogMonitor) session.createCriteria(ELogMonitor.class)
								.add(Restrictions.eq("examid", Long.valueOf(examid)))
								.add(Restrictions.eq("userid", Long.valueOf(userid)))
								.uniqueResult();
						long time = eLogMonitor.getStartdate().getTime()+Long.valueOf(delayTime)*1000*60;
						Date date = new Date(time);
						eLogMonitor.setStartdate(date);
						eLogMonitor.setRemarks((eLogMonitor.getRemarks()==null?"":eLogMonitor.getRemarks()+"||")+remarks);
						session.update(eLogMonitor);
						return "success！";
					} catch (Exception e) {
						e.printStackTrace();
						return "error！";
					}
				}
			});
		}
		/**
		 * 获取缺考考生信息
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public List<Object> getUnExamedUser(){
			return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
				
				@Override
				public Object execute(Session session) throws Throwable {
					long userid = AcegiUtil.getEUser().getId();
					StringBuffer sqlbuffer = new StringBuffer();
					String sql = "select a.kcid from e_exam_arrange a where userid = '"+userid+"'";
				    List<String> list = session.createSQLQuery(sql).list();
				    if(null==list || list.size()==0)
				    {
				    	sqlbuffer.append("SELECT U.PASSWORD,U.REALNAME,u.username,T.CHEATFLAG,T.EXAMID");
						sqlbuffer.append(" FROM E_LOGMONITOR T, USERS U,e_exam_arrange a ");
						sqlbuffer.append(" WHERE U.USER_ID = T.USERID and u.user_id=a.userid and t.cheatflag in ('1','2') and t.EXAMID=a.examid and a.examtype='1' and a.examid=(select examid from e_exam_arrange where userid='"+userid+"')");
				    }else{
				    	if("".equals(list.get(0))||list.get(0)==null){
				        	sqlbuffer.append("SELECT U.PASSWORD,U.REALNAME,u.username,T.CHEATFLAG,T.EXAMID");
							sqlbuffer.append(" FROM E_LOGMONITOR T, USERS U,e_exam_arrange a ");
							sqlbuffer.append(" WHERE U.USER_ID = T.USERID and u.user_id=a.userid and t.cheatflag in ('1','2') and t.EXAMID=a.examid and a.examtype='1' and a.examid=(select examid from e_exam_arrange where userid='"+userid+"')");
							
				        }else{
				        	sqlbuffer.append("SELECT U.PASSWORD,U.REALNAME,u.username,T.CHEATFLAG,T.EXAMID");
				        	sqlbuffer.append(" FROM E_LOGMONITOR T, USERS U,e_exam_arrange a ");
				        	sqlbuffer.append(" WHERE U.USER_ID = T.USERID and u.user_id=a.userid and t.cheatflag in ('1','2') and t.EXAMID=a.examid and a.examtype='1'  and a.examid in (select distinct examid from e_exam_arrange where  kcid in(select kcid from e_exam_arrange where userid = '"+userid+"'))");
				        	sqlbuffer.append(" and  u.userbianhao in  (select distinct  examid from e_exam_arrange where kcid  in (select kcid from e_exam_arrange  where userid ='"+userid+"')) ");
				        }
				    }
				    SQLQuery squery = session.createSQLQuery(sqlbuffer.toString());
					List<Object> result = squery.list();
					return result;
				}
			});
		}
}
