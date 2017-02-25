package com.wondersgroup.falcon.question_batch_add.dao;

import java.math.BigDecimal;
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
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestions_temp;
import com.wondersgroup.falcon.question.model.Tmdot;
import com.wondersgroup.kaoshi.bo.Admission_card_file;
import com.wondersgroup.kaoshi.bo.Admission_card_user;
import com.wondersgroup.kaoshi.bo.Ae02;
import com.wondersgroup.kaoshi.bo.EPapersSet;
import com.wondersgroup.kaoshi.bo.E_Users_Temp;
import com.wondersgroup.kaoshi.bo.Tdjobexamdot;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

public class BatchAddDAO extends HibernateDaoSupport {

	public void save(final EQuestions_temp eqt) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(eqt);
				return null;
			}
		});
	}

	public void saveEquestions(final EQuestions q) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(q);
				return null;
			}
		});
	}

	public void saveEuserstemp(final E_Users_Temp users) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(users);
				return null;
			}
		});
	}

	public void saveTmdot(final Tmdot tmdot) {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(tmdot);
				return null;
			}
		});
	}

	public void saveAe02(final Ae02 ae02) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(ae02);
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<EQuestions_temp> findByStateAndBatchNumber(final long state,
			final long bcn) throws Exception {
		return (List<EQuestions_temp>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session
								.createCriteria(EQuestions_temp.class);
						try {
							// criteria.createCriteria("eimportance");
							// criteria.createCriteria("equestiontype");
							criteria.add(Restrictions.eq("state", state));
							criteria.add(Restrictions.eq("batchNumber", bcn));
						} catch (Exception e) {
							e.printStackTrace();
						}
						return criteria.list();

					}
				});

	}

	public Tjobsubject findTkByid_jobAndRankname(final String id_job,
			final String rankname) throws Exception {
		return (Tjobsubject) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session
								.createCriteria(Tjobsubject.class);
						criteria.add(Restrictions.eq("id_job", id_job));
						criteria.add(Restrictions.eq("rankname", rankname));
						if (criteria.list().size() > 0) {
							return (Tjobsubject) criteria.list().get(0);
						} else {
							return null;
						}

					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Tdjobexamdot> findJdysBygzid(final String gzid) {
		return (List<Tdjobexamdot>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session
								.createCriteria(Tdjobexamdot.class);
						criteria.add(Restrictions.eq("ccz137", gzid)).add(
								Restrictions.eq("aaa131", "1"));
						return criteria.list();
					}
				});
	}

	public int checkQuestions(final String sjid) {
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						String sql = "select count(e.st_id) from E_QUESTIONS e where e.st_scbz=0 and e.st_sjid='"
								+ sjid + "'";
						Query query = session.createSQLQuery(sql);
						BigDecimal count = (BigDecimal) query.uniqueResult();
						return count.intValue();
					}
				});
	}

	public String getjdid(final String jdmc) {
		return (String) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						
						String sql ="select * from hz95 where jd_mc='"+jdmc+"' ";
						List<String> list1 = session.createSQLQuery(sql).list();
						if(list1.size()>0){
							return "";
						}
						else{
						String sql1 = " insert into hz95(JD_ID,JD_MC,KS_GL, AAA131, KS_RS,AAE036) values (pape_sequ.nextval,'"
								+ jdmc + "','0','1', '0',sysdate)";
						Query query1 = session.createSQLQuery(sql1);
						query1.executeUpdate();
						String sql2 = "select jd_id from hz95 where jd_mc ='"
								+ jdmc + "'";
						List<String> list = session.createSQLQuery(sql2).list();
						String jdid = String.valueOf(list.get(0));
						return jdid;
						}
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<E_Users_Temp> getusers(final String jdid) {
		return (List<E_Users_Temp>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						String sql = "from E_Users_Temp where  jd_id = '"
								+ jdid + "'";
						List<E_Users_Temp> list_users = session
								.createQuery(sql).list();
						return list_users;
					}
				});
	}

	public List<Object> excuteProc(String jdid,Connection conn) {
		List<Object> list = new ArrayList<Object>();
		//String driver = "oracle.jdbc.driver.OracleDriver";
		//String strUrl = ProcedureUrl.PROC_URL_STRING;
		//Connection conn = null;
		CallableStatement proc = null;
		try {
			//Class.forName(driver).newInstance();
			//conn = DriverManager.getConnection(strUrl, "","");
			proc = conn.prepareCall("{ call exam_Arrange_manual(?,?,?) }");
			proc.setString(1, jdid);
			proc.registerOutParameter(2, Types.INTEGER);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();
			list.add(proc.getString(2));
			list.add(proc.getString(3));
		} catch (Exception ex2) {
			ex2.printStackTrace();
			list.add("2");
			list.add(ex2.getMessage());
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
		return list;
	}

	public EPapersSet getById(final String sjid){
		return (EPapersSet) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select * from e_papers_set where sj_id=? ";
				Query query = session.createSQLQuery(sql).addEntity(EPapersSet.class).setParameter(0, sjid);
				EPapersSet eps = (EPapersSet) query.uniqueResult();
				return eps;
			}
		});
	}
	
	public void savePrintCardFileInfo(final Admission_card_file acf){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			@Override
			public Object execute(Session session) throws Throwable {
				session.save(acf);
				return null;
			}
		});
	}
	
	public boolean savePrintCardUsers(final List<Admission_card_user> users){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				for (int i = 0; i < users.size(); i++) {
					Admission_card_user user = users.get(i);
					session.save(user);
				}
				return true;
			}
		});
	}
}
