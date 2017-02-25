package com.wondersgroup.kaoshi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.model.EQuestiontype;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.kaoshi.bo.EAnswertemp;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;

public class EQuestiontypeDAOImpl extends HibernateDaoSupport {
	public  List findEQuestiontype()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return session.createCriteria(EQuestiontype.class).add(
						Restrictions.not(Restrictions.eq("id", new Long("13"))))
						.addOrder(Order.asc("priority")).list();

			}
		});
	}
	@SuppressWarnings("unchecked")
	public  List<EQuestiontype> findEQuestiontypeByPaperType()throws Exception{
		return (List<EQuestiontype>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return session.createCriteria(EQuestiontype.class).add(
						Restrictions.eq("id", new Long("13"))).addOrder(
						Order.asc("priority")).list();	 

			}
		});
	}
	@SuppressWarnings("rawtypes")
	public  List findEQuestiontypeAll()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return session.createCriteria(EQuestiontype.class).addOrder(
						Order.asc("priority")).list();

			}
		});
	}

	/*public void addAnswerTemp(final String userid, final String answer,
			final String shijuanid, final String questionid,
			final String questionidtype) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				// 因答题过程中E_ANSWERTEMP表会出现记录同一道题多次的情况
				synchronized (this) {
					String sql = "select * from E_ANSWERTEMP a where a.userid="
							+ Long.valueOf(userid) + " " + "and a.examid="
							+ Long.valueOf(shijuanid) + " and a.questionid="
							+ questionid;
					Query query = session.createSQLQuery(sql).addEntity(
							EAnswertemp.class);
					List<EAnswertemp> list = query.list();
					// List<EAnswertemp> list=criteria.list();
					if (list.size() > 0) {
						EAnswertemp temp = (EAnswertemp) list.get(0);
						temp.setAnswer(answer);
						session.update(temp);
					} else {
						EAnswertemp temp = new EAnswertemp();
						temp.setAnswer(answer);
						temp.setExamid(Long.valueOf(shijuanid));
						temp.setUserid(Long.valueOf(userid));
						temp.setQuestionid(questionid);
						temp.setQuestiontype(questionidtype);
						session.save(temp);
					}
				}
				return null;
			}
		});
	}*/
	
	public void addAnswerTemp(final String userid, final String answer,
			final String sjid, final String questionid,
			final String questionidtype,final Connection conn) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				// 因答题过程中E_ANSWERTEMP表会出现记录同一道题多次的情况
				List<Object> returnList = new ArrayList<Object>();
				CallableStatement proc = null;
				try {
					proc = conn.prepareCall("{ call add_answer_tmp(?,?,?,?,?,?,?) }");
					proc.setString(1, userid);
					proc.setString(2, sjid);
					proc.setString(3, questionid);
					proc.setString(4, questionidtype);
					proc.setString(5, answer);
					proc.registerOutParameter(6, Types.INTEGER);
					proc.registerOutParameter(7, Types.VARCHAR);
					proc.execute();
					returnList.add(proc.getLong(6));
					returnList.add(proc.getString(7));
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
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public  List<EAnswertemp> pingIp(final String examid)throws Exception{
		return (List<EAnswertemp>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				//得到人员
			      AcegiUtil acegiUtil=new AcegiUtil();
			          EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
				//获取此人监控信息 判断是否网络断开
			          Criteria criteria =session.createCriteria(ELogMonitor.class);
			          criteria.add(Restrictions.eq("userid", user.getId()));
			          criteria.add(Restrictions.eq("examid", Long.valueOf(examid)));
			          List list=criteria.list();
			          //System.out.println(Long.valueOf(examid));
			         // System.out.println(user.getId());
			          List<EAnswertemp> templist=new ArrayList<EAnswertemp>();
			          //System.out.println("查询是否网络断开="+list.size());
			          if(list.size()>0){
			        	  //此用户网络断开进行数据回复
			        	  Criteria criteriatemp =session.createCriteria(EAnswertemp.class);
			        	  criteriatemp.add(Restrictions.eq("userid", user.getId()));
			        	  criteriatemp.add(Restrictions.eq("examid", Long.valueOf(examid)));
				          templist=criteriatemp.list();
				          //System.out.println("答题记录："+templist.size());
			          }else{
			        	  //没有断开请继续答题
			        	  
			          }
			          return templist; 

			}
		});
	}
}
