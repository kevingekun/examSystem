package com.wondersgroup.technocracy.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EuserTest;
import com.wondersgroup.popedom.bo.ExamStaff;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.ExpertInfo;
import com.wondersgroup.technocracy.bo.HZ92;
import com.wondersgroup.technocracy.bo.HZ94;
import com.wondersgroup.technocracy.bo.IdentifyInfo;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

public class ArrangeExpertsDao extends HibernateDaoSupport {
	public PageReturn arrangequery(final PageTool pageTool, final String sj_mc)
			throws Exception {
		return (PageReturn) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						PageReturn pageReturn = new PageReturn();
						StringBuffer qs = new StringBuffer();
						/*
						 * qs.append(
						 * "select a.examid, c.teamName as kcmc,c.contactname as lxr,c.contactph as lxdh,c.teamaddress as dz,count(a.userid) as ksrs"
						 * );
						 * qs.append(" from EExamArrange a, EPapers b, EUserTeam c"
						 * ); qs.append(
						 * " where a.examid = b.sjId  and a.examtype = '1' and a.usertype='1' and c.teamId = a.teamid  and c.flag='1'"
						 * );
						 */
						qs.append(" select a.examid,c.team_id,c.team_name as kcmc,c.contactname as lxr,c.contactph as lxdh,c.teamaddress as dz,count(a.userid) as ksrs,b.sj_mc");
						qs.append(" from e_Exam_Arrange a, e_papers b, E_user_team c");
						qs.append(" where a.examid = b.sj_id  and a.examtype = '1'  and usertype='1'  and c.team_id = a.teamid and c.flag='1' ");
						if (!"".equals(sj_mc) && sj_mc != null) {
							qs.append(" and b.sj_mc like '%" + sj_mc + "%'");
						}
						qs.append(" group by a.examid,c.team_Name,c.contactname,c.contactph,c.teamaddress,c.team_id,b.sj_mc");
						qs.append(" order by a.examid desc");
						Query queryObject = session.createSQLQuery(qs
								.toString());
						// queryObject.setParameter("sj_mc", sj_mc);
						pageReturn.setTotal(queryObject.list().size());
						queryObject.setFirstResult(pageTool.getStart());
						queryObject.setMaxResults(pageTool.getSize());
						List li = queryObject.list();
						List<IdentifyInfo> list = new ArrayList<IdentifyInfo>();
						Iterator<Object[]> it = li.iterator();
						while (it.hasNext()) {
							Object[] o = it.next();
							IdentifyInfo e = new IdentifyInfo();
							e.setExamid(((BigDecimal) o[0]).longValue());
							e.setTeamid(((BigDecimal) o[1]).longValue());
							e.setKcmc((String) o[2]);
							e.setLxr((String) o[3]);
							e.setLxdh((String) o[4]);
							e.setDz((String) o[5]);
							e.setKsrs(String.valueOf(o[6]));
							e.setJdpch((String) o[7]);
							list.add(e);
						}
						pageReturn.setReturnList(list);
						return pageReturn;

					}
				});
	}

	public void arrangeExperts(final String ksid, final String kcid,
			final String committeeId, final String nduty, final String nremark,
			final String ZJID) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				HZ92 hz92 = new HZ92();
				// Transaction tx = session.beginTransaction();
				hz92.setAaa131("1");
				hz92.setChz007(Long.valueOf(ksid));//实际接收的值为考场id
				hz92.setHzz021(kcid);//实际接收的值为考试id
				hz92.setHzz900(Long.valueOf(committeeId));
				hz92.setAae036(new Date());
				hz92.setHzz034(nduty);
				hz92.setAae013(nremark);
				hz92.setHzz001(Long.valueOf(ZJID));
				session.save(hz92);
				// tx.commit();
				// session.close();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Addexperts> findcid(final Long id) throws Exception {
		return (List<Addexperts>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session
								.createCriteria(Addexperts.class);
						criteria.add(Restrictions.eq("hzz001", id));
						return criteria.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<ExpertInfo> checkExpert(final String kcid, final String sjid) {
		return (List<ExpertInfo>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						String sql = "select ep.sj_mc,eut.team_name,hz90.aac003,hz90.aae005,hz90.hzz003,hz90.hzz210,ep.sj_id,hz90.hzz001,hz92.hzz200 from "
								+ "hz90 hz90,hz92 hz92,E_user_team eut,E_PAPERS ep where "
								+ "ep.sj_id="
								+ sjid
								+ " and eut.team_id="
								+ kcid
								+ " and hz92.chz007="
								+ kcid
								+ " and hz92.hzz021='"
								+ sjid
								+ "' and hz92.hzz001=hz90.hzz001 and hz92.aaa131=1";
						Query query = session.createSQLQuery(sql);
						Iterator<Object[]> it = query.list().iterator();
						List<ExpertInfo> list = new ArrayList<ExpertInfo>();
						while (it.hasNext()) {
							ExpertInfo e = new ExpertInfo();
							Object[] o = it.next();
							e.setSjmc((String) o[0]);
							e.setKcmc((String) o[1]);
							e.setExpertname((String) o[2]);
							e.setPhone((String) o[3]);
							e.setMajor((String) o[4]);
							e.setZc((String) o[5]);
							e.setExamid(((BigDecimal) o[6]).longValue());
							e.setTeamid(((BigDecimal) o[7]).longValue());
							e.setHz92id(((BigDecimal) o[8]).longValue());
							list.add(e);
						}
						return list;
					}
				});
	}

	public boolean deleteUseOfExpert(final String id) {
		return (Boolean) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						String sql = "update hz92 e set e.aaa131='0' where e.hzz200="
								+ id;
						Query query = session.createSQLQuery(sql);
						int re = query.executeUpdate();
						if (re == 1) {
							return true;
						} else {
							return false;
						}
					}
				});
	}

	public boolean replaceExpert(final String duty, final String remark,
			final String reson, final String expertid, final String idof92) {
		return (Boolean) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						try {
							Criteria criteria = session
									.createCriteria(HZ92.class);
							criteria.add(Restrictions.eq("hzz200",
									Long.valueOf(idof92)));
							HZ92 hz92 = (HZ92) criteria.list().get(0);
							long kcid = hz92.getChz007();
							String sjid = hz92.getHzz021();
							hz92.setAaa131("0");
							hz92.setHzz026(reson);
							session.saveOrUpdate(hz92);
							Criteria criteria2 = session
									.createCriteria(Addexperts.class);
							criteria2.add(Restrictions.eq("hzz001",
									Long.valueOf(expertid)));
							Addexperts addexperts = (Addexperts) criteria2
									.list().get(0);
							HZ92 hz922 = new HZ92();
							hz922.setChz007(kcid);
							hz922.setHzz021(sjid);
							hz922.setHzz026(reson);
							hz922.setHzz900(addexperts
									.getCommitteeid());
							hz922.setHzz001(Long.valueOf(expertid));
							hz922.setAaa131("1");
							hz922.setAae036(new Date());
							hz922.setAae013(remark);
							hz922.setHzz034(duty);
							session.saveOrUpdate(hz922);
						} catch (Exception e) {
							e.printStackTrace();
							return false;
						}
						return true;
					}
				});
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, List<Object>> arrangeExpertAuto2(final String sjid) {
		return (Map<String, List<Object>>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select * from hz90 a where a.hzz003 like (select distinct(b.jobname) from e_papers a,TJOBSUBJECT b where a.sj_gzid=b.id_job and a.sj_id="+sjid+")";
				Query query = session.createSQLQuery(sql);
				List<Object> list = query.list();
				Random random = new Random();
				List<Object> list1 = new ArrayList<Object>();
				List<Object> list2 = new ArrayList<Object>();
				Set<Integer> set = new HashSet<Integer>();
				for(int i=0;i<list.size();i++){
					int num = random.nextInt(list.size());
					set.add(num);
					if(set.size()==6){
						break;
					}
				}
				for (Integer it : set) {
				      if(list1.size()==3){
				    	  list2.add(list.get(it));
				      }else{
				    	  list1.add(list.get(it));
				      }
				}
				Map<String, List<Object>> map = new HashMap<String, List<Object>>();
				map.put("list1", list1);
				map.put("list2", list2);
				return map;
			}
		});
	}

	public List<Object> arrangeExpertAuto(final String sjmc,Connection conn) {
		//String driver = "oracle.jdbc.driver.OracleDriver";
		//String strUrl = ProcedureUrl.PROC_URL_STRING;
		List<Object> list = new ArrayList<Object>();
		//Connection conn = null;
		CallableStatement proc = null;
		try {
			//Class.forName(driver);
			//conn = DriverManager.getConnection(strUrl, "","");

			proc = conn.prepareCall("{ call automatic_PlanExpert(?,?,?,?,?,?) }");
			proc.setString(1, sjmc);
			proc.setString(2, null);
			proc.setString(3, null);
			proc.setLong(4, 3);
			proc.registerOutParameter(5, Types.INTEGER);
			proc.registerOutParameter(6, Types.VARCHAR);
			proc.execute();
			list.add(proc.getString(5));
			list.add(proc.getString(6));
			return list;
		} catch (SQLException ex2) {
			list.add(2);
			list.add(ex2.getMessage());
			ex2.printStackTrace();
		} catch (Exception ex2) {
			ex2.printStackTrace();
			list.add(3);
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
		if ((Long) list.get(0) != 0) {
			throw new BusinessException((String) list.get(1));
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<String> findTeamidAndExamidBysjmc(final String sjmc){
		return (List<String>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select distinct a.chz007,a.hzz021 from hz92 a,E_PAPERS b where a.hzz021=b.sj_id and b.sj_mc='"+sjmc+"'";
				Query query = session.createSQLQuery(sql);
				List<String> list = query.list();
				return list;
			}
		});
	}
	public boolean relateMajorAndProfession(final String zymc,final String checkedid){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					String[] job_id = checkedid.split(",");
					for(int i=0;i<job_id.length;i++){
						HZ94 hz94 = new HZ94();
						hz94.setHzz003(zymc);
						hz94.setCcz137(job_id[i]);
						session.save(hz94);
					}
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		});
	}
	public String arrangeExpertAutoSubmit(final List<Object> list,final String sjid){
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			@Override
			public Object execute(Session session) throws Throwable {
				try {
					for(int i=0;i<list.size();i++){
						Object[] o = (Object[]) list.get(i);
						HZ92 hz92 = new HZ92();
						System.out.println(o);
						Object idObject = o[0];
						long id = Long.valueOf(idObject.toString());
						hz92.setHzz001(id);
						hz92.setHzz021(sjid);
						hz92.setChz007(141l);
						hz92.setAae036(new Date());
						hz92.setAaa131("1");
						session.save(hz92);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "success";
			}
		});
	}
}
