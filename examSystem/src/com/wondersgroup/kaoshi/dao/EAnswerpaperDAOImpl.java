package com.wondersgroup.kaoshi.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
import com.wondersgroup.falcon.question.beans.EImportanceService;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.local.bo.WrongPersent_ws;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

public class EAnswerpaperDAOImpl extends HibernateDaoSupport {
	private Object getSession;

	/**
	 * 
	 * <p>
	 * Description:通过人员查找答卷
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by
	 * 
	 * @param pagetool
	 * @param ryid
	 * @return
	 */
	public PageReturn findByUser(final PageTool pagetool, final String username,
			final EPapers epaper) throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				Criteria criteria = session.createCriteria(EAnswerpaper.class);
				// 设置查询条件
				criteria.add(Restrictions.eq("djRyid", username));
				criteria = criteria.createAlias("epapers", "epapers");
				if (epaper.getSjMc() != null && !epaper.getSjMc().equals("")) {
					criteria.add(Restrictions.like("epapers.sjMc", epaper.getSjMc(),
							MatchMode.ANYWHERE));
				}
				// 只显示审核完毕的 新增加的功能
				criteria.add(Restrictions.eq("djSyzt", new Long(2).longValue()));
				if (epaper.getSjZt() != -1) {
					criteria.add(Restrictions.eq("epapers.sjZt", epaper.getSjZt()));
				}
				if (epaper.getSjKksj() != null && epaper.getSjKksjEnd() != null) {
					// 结束日期加一天
					Calendar can = new GregorianCalendar();
					can.setTime(epaper.getSjKksjEnd());
					can.add(Calendar.DATE, 1);
					criteria.add(Restrictions.between("epapers.sjKksj", epaper
							.getSjKksj(), can.getTime()));
				} else if (epaper.getSjKksj() != null && epaper.getSjKksjEnd() == null) {
					criteria.add(Restrictions.ge("epapers.sjKksj", epaper.getSjKksj()));
				} else if (epaper.getSjKksj() == null && epaper.getSjKksjEnd() != null) {
					// 结束日期加一天
					Calendar can = new GregorianCalendar();
					can.setTime(epaper.getSjKksjEnd());
					can.add(Calendar.DATE, 1);
					criteria.add(Restrictions.le("epapers.sjKksj", can.getTime()));
				}
				// 新增排序
				criteria.addOrder(Order.desc("epapers.sjKksj"));
				// Criteria criteria1=criteria.setProjection(Projections.rowCount());
				// pageReturn.setTotal(((Integer)criteria1.uniqueResult()).intValue());
				pageReturn.setTotal(criteria.list().size());
				criteria.setMaxResults(pagetool.getSize());
				criteria.setFirstResult(pagetool.getStart());
				pageReturn.setReturnList(criteria.list());
				return pageReturn; 

			}
		});


	}
	/**
	 * 
	 * <p>
	 * Description:找到所有的答卷
	 * </p>
	 * 
	 * Created by [www] [Aug 19, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @param pagetool
	 * @return
	 */
//	public PageReturn findall(PageTool pagetool, String sjMc, Date djsj,
//			Date djsjend) {
//		PageReturn pageReturn = new PageReturn();
////		Criteria criteria = this.getSession()
////				.createCriteria(EAnswerpaper.class);
////		criteria.createAlias("epapers", "epaper");
////		if (sjMc != null && !sjMc.equals("")) {
////			criteria.add(Restrictions.like("epaper.sjMc", sjMc, MatchMode.ANYWHERE));
////		}
////		if (djsj != null && djsjend != null) {
////			criteria.add(Restrictions.between("djKssj", djsj, djsjend));
////		} else if (djsj != null && djsjend == null) {
////			criteria.add(Restrictions.ge("djKssj", djsj));
////		} else if (djsj == null && djsjend != null) {
////			criteria.add(Restrictions.le("djKssj", djsjend));
////		}
//		StringBuffer hql=new StringBuffer();
//		hql.append("from EAnswerpaper where 1=1 ");
//		if (sjMc != null && !sjMc.equals("")) {
//			hql.append(" and epaper.sjMc like %"+sjMc+"% ");
//		}
//		if (djsj != null && djsjend != null) {
//			hql.append(" and djKssj between "+djsj+" and "+djsjend );
//		} else if (djsj != null && djsjend == null) {
//			hql.append(" and  djKssj> "+djsj);
//		} else if (djsj == null && djsjend != null) {
//			hql.append(" and  djKssj< "+djsjend);
//		}
//		Query query=this.getSession().createQuery(hql.toString());
//		List<EAnswerpaper> list=query.list();
//		pageReturn.setTotal(list.size());
//		query.setMaxResults(pagetool.getSize());
//		query.setFirstResult(pagetool.getStart());
//		pageReturn.setReturnList(list);
//		return pageReturn;
//	}

	public PageReturn findall(final PageTool pagetool,final String sjMc,final Date djsj, final Date djsjend)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			@SuppressWarnings("unchecked")
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				StringBuffer hql=new StringBuffer();
				hql.append("select e from EAnswerpaper as e");
				if (sjMc != null && !sjMc.equals("")) {
					hql.append(" where e.epapers.sjMc like '%"+sjMc+"%'");
				}
				if (djsj != null && djsjend != null) {
					hql.append(" and e.djKssj between "+djsj+" and "+djsjend );
				} else if (djsj != null && djsjend == null) {
					hql.append(" and e.djKssj>"+djsj);
				} else if (djsj == null && djsjend != null) {
					hql.append(" and e.djKssj<"+djsjend);
				}
				hql.append(" order by e.djId desc");
				try {
					Query query=session.createQuery(hql.toString());
					pageReturn.setTotal(query.list().size());
					query.setMaxResults(pagetool.getSize());
					query.setFirstResult(pagetool.getStart());
					List<EAnswerpaper> list=query.list();
					pageReturn.setReturnList(list);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				return pageReturn;

			}
		});
	}
	// 找到所有的考试中或者结束的试卷
	public  PageReturn findallPaper(final PageTool pagetool,final String sjMc,final Date djsj,
			final Date djsjend)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				Criteria criteria = session.createCriteria(EPapers.class);
				criteria.add(Restrictions.le("sjZt", 2));
				criteria.add(Restrictions.ge("sjZt", 1));
				if (sjMc != null && !sjMc.equals("")) {
					criteria.add(Restrictions.like("sjMc", sjMc, MatchMode.ANYWHERE));
				}
				if (djsj != null && djsjend != null) {
					// Calendar cal=new GregorianCalendar();
					// cal.setTime(djsjend);
					// cal.add(Calendar.DATE, 1);
					criteria.add(Restrictions.between("sjKksj", djsj, djsjend));
				} else if (djsj != null && djsjend == null) {
					criteria.add(Restrictions.ge("sjKksj", djsj));
				} else if (djsj == null && djsjend != null) {
					// Calendar cal=new GregorianCalendar();
					// cal.setTime(djsjend);
					// cal.add(Calendar.DATE, 1);
					criteria.add(Restrictions.le("sjKksj", djsjend));
				}
				criteria.addOrder(Order.desc("sjKksj"));
				pageReturn.setTotal(criteria.list().size());
				criteria.setMaxResults(pagetool.getSize());
				criteria.setFirstResult(pagetool.getStart());
				pageReturn.setReturnList(criteria.list());
				return pageReturn; 

			}
		});


	}
	/**
	 * 
	 * <p>
	 * Description:查找审阅试卷
	 * </p>
	 * 
	 * Created by [www] [Aug 19, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @param pagetool
	 * @return
	 */
	public PageReturn findbyZt(final PageTool pagetool,final Long djSyzt, final String sjMc,
			final Date kksj, final String flag)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				System.out.println(djSyzt+"----"+flag);
				PageReturn pageReturn = new PageReturn();
				Criteria criteria = session.createCriteria(EAnswerpaper.class);
				//criteria.add(Restrictions.not(Restrictions.eq("djSyzt", new Long(2)))); 用于立即出分
				criteria.add(Restrictions.eq("djSyzt", new Long(33)));//功能暂时未使用，需使用可用上一条语句
				criteria.add(Restrictions.eq("flag", "0"));
				criteria.createAlias("epapers", "epaper");
				if (sjMc != null && !sjMc.equals("")) {
					criteria.add(Restrictions.like("epaper.sjMc", sjMc,
							MatchMode.ANYWHERE));
				}
				if (kksj != null) {
					criteria.add(Restrictions.eq("djKssj", kksj));
				}
				criteria.addOrder(Order.desc("djJssj"));
				// ((Integer)criteria.setProjection(Projections.rowCount()).uniqueResult(
				// )).intValue()
				pageReturn.setTotal(criteria.list().size());
				criteria.setMaxResults(pagetool.getSize());
				criteria.setFirstResult(pagetool.getStart());
				pageReturn.setReturnList(criteria.list());
				return pageReturn;

			}
		});


	}
	/**
	 * 
	 * <p>
	 * Description: 根据试卷ID找到，找到对应的案卷列表
	 * </p>
	 * 
	 * Created by [hch] [Nov 27, 2012] Midified by [修改人] [修改时间]
	 * 
	 * @param pagetool
	 * @return
	 */
	public  PageReturn findAnswerPaperBySjId(final PageTool pagetool,final  Long SjId)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				Criteria criteria = session.createCriteria(EAnswerpaper.class);
				// criteria.add(Restrictions.eq("djSyzt", djSyzt.longValue()));
				criteria.createAlias("epapers", "epaper");
				criteria.add(Restrictions.eq("epaper.sjId", SjId.longValue()));
				criteria.addOrder(Order.asc("djJssj"));
				// ((Integer)criteria.setProjection(Projections.rowCount()).uniqueResult(
				// )).intValue()
				pageReturn.setTotal(criteria.list().size());
				criteria.setMaxResults(pagetool.getSize());
				criteria.setFirstResult(pagetool.getStart());
				pageReturn.setReturnList(criteria.list());
				return pageReturn; 

			}
		});


	}
	public PageReturn findPaperBySjId(final PageTool pagetool,final Long SjId) throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				Criteria criteria = session.createCriteria(EPapers.class);
				criteria.add(Restrictions.eq("epaper.sjId", SjId.longValue()));
				criteria.addOrder(Order.asc("djJssj"));
				pageReturn.setTotal(criteria.list().size());
				criteria.setMaxResults(pagetool.getSize());
				criteria.setFirstResult(pagetool.getStart());
				pageReturn.setReturnList(criteria.list());
				return pageReturn;

			}
		});


	}
	public PageReturn findAnswerPaper(final PageTool pagetool,final String sjMc)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				Criteria criteria = session.createCriteria(EAnswerpaper.class);
				criteria.createAlias("epapers", "epaper");
				if (sjMc != null && !sjMc.equals("")) {
					criteria.add(Restrictions.like("epaper.sjMc", sjMc,
							MatchMode.ANYWHERE));
				}
				pageReturn.setTotal(criteria.list().size());
				criteria.setMaxResults(pagetool.getSize());
				criteria.setFirstResult(pagetool.getStart());
				pageReturn.setReturnList(criteria.list());
				return pageReturn; 

			}
		});


	}
	/**
	 * 
	 * 
	 * <p>
	 * Description:保存考试答卷
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @param eanswerpaper
	 */
	public long saveEAnswerpaper(final EAnswerpaper eanswerpaper  )throws Exception{
		return (Long) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(eanswerpaper);
				long eap=eanswerpaper.getDjId();
				//System.out.println("测试"+eap);
				return eap;

			}
		});
	}
	public List<Object> submitPaper(Long djid,String userid, Connection conn){
		//String driver = "oracle.jdbc.driver.OracleDriver";
		//String strUrl = ProcedureUrl.PROC_URL_STRING;
		List<Object> returnList = new ArrayList<Object>();
		//Connection conn = null;
		CallableStatement proc = null;
		try {
			//Class.forName(driver);
			//conn = DriverManager.getConnection(strUrl, "","");

			proc = conn.prepareCall("{ call hand_in_paper(?,?,?,?,?) }");
			proc.setString(1, userid);
			proc.setLong(2, djid);
			proc.registerOutParameter(3, Types.INTEGER);
			proc.registerOutParameter(4, Types.VARCHAR);
			proc.registerOutParameter(5, Types.INTEGER);
			proc.execute();
			returnList.add(proc.getLong(3));
			returnList.add(proc.getString(4));
			returnList.add(proc.getLong(5));
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
	/**
	 * 
	 * <p>
	 * Description:保存一个答题
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @param eanswerquestions
	 */
	public void saveEAnswerquestions(final EAnswerquestions eanswerquestions)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(eanswerquestions);
				return null;
			}
		});
		}
	// 不可用hql中的update 配置文件中二级缓存没有管理 用update会出现数据不一致
	public void updateEAnswerquestions(final EAnswerquestions eanswerquestions)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.update(eanswerquestions);
				// this.getHibernateTemplate().update(eanswerquestions);
				/*
				 * EAnswerquestions qq = new EAnswerquestions();
				 * qq.setId(eanswerquestions.getId());
				 * qq.setEpaperquestions(eanswerquestions.getEpaperquestions());
				 * qq.setEanswerpaper(eanswerquestions.getEanswerpaper());
				 * qq.setStDa(eanswerquestions.getStDa());
				 * qq.setStDasm(eanswerquestions.getStDasm());
				 * qq.setStPx(eanswerquestions.getStPx());
				 * qq.setStDf(eanswerquestions.getStDf());
				 * qq.setStsyzt(eanswerquestions.getStsyzt());
				 * qq.setRight(eanswerquestions.getRight());
				 * qq.setStContent(eanswerquestions.getStContent());
				 * this.getSession().delete(eanswerquestions);
				 * this.getSession().save(qq);
				 */

				/*
				 * 
				 * Transaction tran = this.getSession().beginTransaction();// 启动事务
				 * String hql = "UPDATE EAnswerquestions SET stDf=" +
				 * eanswerquestions.getStDf() + " WHERE id=" + eanswerquestions.getId();
				 * Query q = this.getSession().createQuery(hql); q.executeUpdate();
				 * tran.commit();// 提交事务 第三种方式 在配置文件中<property
				 * name="hibernate.cache.use_second_level_cache" >false</property>
				 * 关闭二级缓存 这样会影响性能
				 */	 
				return null;
			}
		});
		}
	/**
	 * 
	 * <p>
	 * Description:根据id加载一个答卷
	 * </p>
	 * 
	 * Created by [www] [Aug 25, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @param id
	 * @return
	 */
	public EAnswerpaper load(final Long id)throws Exception{
		return (EAnswerpaper) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = " from EAnswerpaper t where t.djId=" + id + "";
				Query query = session.createQuery(hql);
				return (EAnswerpaper) query.list().get(0);

			}
		});


	}
	/**
	 * 
	 * 
	 * <p>
	 * Description:根据试卷查找答卷
	 * </p>
	 * 
	 * Created by [www] [Aug 25, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @return
	 */
	public PageReturn findanswerpaperbypaper(final PageTool pagetool,final  Long paperid,
			final String groupId,final  String startScore,final  String endScore,final  String sex,
			final String userstar, final Date startctime,final  Date endctime)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				StringBuffer sb = new StringBuffer();
				/*sb.append("select ep.sjMc,u.realname,u.password,u.username,ea.djKssj,ea.djJssj,ea.djZf,el.cheatflag,ea.djId,ea.epapers.sjId from " +
						"EPapers as ep,EUser as u,ELogMonitor as el,EExamArrange as b,EAnswerpaper as ea " +
						"where ep.sjId=b.examid and b.userid=u.id and el.userid=u.id and ea.djRyid=u.username and ea.epapers.sjId=ep.sjId and ep.sjId="+paperid);
				sb.append(" order by ea.djZf desc");*/
				sb.append("select p.sj_mc,u.realname,u.password,u.username,to_char(ea.dj_kssj,'YYYY-MM-DD HH:MI:SS'),to_char(ea.dj_jssj,'YYYY-MM-DD HH:MI:SS'),ea.dj_zf,a.cheatflag,ea.dj_id,p.sj_id FROM" +
						" users u, e_papers p, e_logmonitor a left join e_answerpaper ea on" +
						" a.userid=ea.user_star and ea.sj_id=a.examid where u.user_id=a.userid and a.examid=p.sj_id and p.sj_id="+paperid);
				sb.append(" order by nvl(ea.dj_zf,0) desc");
				Query query = session.createSQLQuery(sb.toString());
				pageReturn.setTotal(query.list().size());
				query.setMaxResults(pagetool.getSize());
				query.setFirstResult(pagetool.getStart());
				pageReturn.setReturnList(query.list());
				return pageReturn; 

			}
		});


	}
	/*public PageReturn findanswerpaperbypaper(PageTool pagetool, Long paperid,
			String groupId, String startScore, String endScore, String sex,
			String userstar, Date startctime, Date endctime) {
		Criteria criteria = this.getSession()
				.createCriteria(EAnswerpaper.class);
		criteria.add(Restrictions.eq("epapers.sjId", paperid));
		criteria.add(Restrictions.eq("djSyzt", new Long(2)));
		if (!"".equals(groupId) && groupId != null) {
			criteria.add(Restrictions.eq("groupId", groupId));
		}
		if (!"".equals(startScore) && startScore != null) {
			criteria.add(Restrictions
					.ge("djZf", Double.parseDouble(startScore)));
		}
		if (!"".equals(endScore) && endScore != null) {
			criteria.add(Restrictions.le("djZf", Double.parseDouble(endScore)));
		}

		if (!"".equals(sex) && sex != null) {
			criteria.add(Restrictions.eq("userSex", sex));
		}
		if (!"".equals(userstar) && userstar != null) {
			criteria.add(Restrictions.eq("userStar", userstar));
		}
		if (!"".equals(startctime) && startctime != null) {
			criteria.add(Restrictions.ge("userDate", DateUtils.addDays(
					startctime, -1)));
		}
		if (!"".equals(endctime) && endctime != null) {
			criteria.add(Restrictions.le("userDate", DateUtils.addDays(
					endctime, +1)));
		}

		PageReturn pageReturn = new PageReturn();
		pageReturn.setTotal(criteria.list().size());
		criteria.setMaxResults(pagetool.getSize());
		criteria.setFirstResult(pagetool.getStart());
		criteria.addOrder(Order.desc("djZf"));
		pageReturn.setReturnList(criteria.list());
		System.out.println("------------>" + pageReturn.getReturnList().size());
		return pageReturn;
	}*/
	public int countall(final Long paperid,final String groupId,final String startScore,
			final String endScore,final  String sex,final  String userstar,final  Date startctime,
			final Date endctime)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EAnswerpaper.class);
				criteria.add(Restrictions.eq("epapers.sjId", paperid));
				criteria.add(Restrictions.eq("djSyzt", new Long(2)));
				if (!"".equals(groupId) && groupId != null) {
					criteria.add(Restrictions.eq("groupId", groupId));
				}
				if (!"".equals(startScore) && startScore != null) {
					criteria.add(Restrictions
							.ge("djZf", Double.parseDouble(startScore)));
				}
				if (!"".equals(endScore) && endScore != null) {
					criteria.add(Restrictions.le("djZf", Double.parseDouble(endScore)));
				}

				if (!"".equals(sex) && sex != null) {
					criteria.add(Restrictions.eq("userSex", sex));
				}
				if (!"".equals(userstar) && userstar != null) {
					criteria.add(Restrictions.eq("userStar", userstar));
				}
				if (!"".equals(startctime) && startctime != null) {
					criteria.add(Restrictions.ge("userDate", DateUtils.addDays(
							startctime, -1)));
				}
				if (!"".equals(endctime) && endctime != null) {
					criteria.add(Restrictions.le("userDate", DateUtils.addDays(
							endctime, +1)));
				}

				Integer count = Integer.valueOf(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
				return count.intValue();	 

			}
		});


	}
	public int countunpass(final  Long paperid,final String groupId,final String startScore,
			final String endScore,final  String sex,final  String userstar,final  Date startctime,
			final Date endctime) throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				//Session session = HibernateUtil.getSession();
				String bjgfs = new EAnswerpaperDAOImpl().getBhgfs(paperid);
				double sjbjgfs = Double.parseDouble(bjgfs);
				StringBuffer sb = new StringBuffer();
				/*sb.append("select ep.sjMc,u.realname,u.password,u.username,ea.djKssj,ea.djJssj,ea.djZf,el.cheatflag,ea.djId,ea.epapers.sjId from " +
						"EPapers as ep,EUser as u,ELogMonitor as el,EExamArrange as b,EAnswerpaper as ea " +
						"where ep.sjId=b.examid and b.userid=u.id and el.userid=u.id and ea.djRyid=u.username and ea.epapers.sjId=ep.sjId and ep.sjId="+paperid);
				sb.append(" and ea.djZf<"+sjbjgfs);*/
				sb.append("select p.sj_mc,u.realname,u.password,u.username,ea.dj_kssj,ea.dj_jssj,nvl(ea.dj_zf,0),a.cheatflag,ea.dj_id,p.sj_id FROM" +
						" users u, e_papers p, e_logmonitor a left join e_answerpaper ea on" +
						" a.userid=ea.user_star and ea.sj_id=a.examid where u.user_id=a.userid and a.examid=p.sj_id and p.sj_id="+paperid);
				sb.append(" and nvl(ea.dj_zf,0)<"+sjbjgfs);
				Query query = session.createSQLQuery(sb.toString());
				int count = query.list().size();
				return count;
				/*Criteria criteria = session.createCriteria(EAnswerpaper.class);
				
				criteria.add(Restrictions.eq("epapers.sjId", paperid));
				criteria.add(Restrictions.eq("djSyzt", new Long(2)));
				criteria.add(Restrictions.lt("djZf", sjbjgfs));
				int cri = criteria.list().size();*/
				/*Integer count = Integer.valueOf( criteria
						.setProjection(Projections.rowCount()).uniqueResult().toString());
				return count.intValue();*/
				//return criteria.list().size(); 

			}
		});


	}
	/*public int countunpass(Long paperid, String groupId, String startScore,
			String endScore, String sex, String userstar, Date startctime,
			Date endctime) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(EAnswerpaper.class);
		String bjgfs = this.getBhgfs(paperid);
		double sjbjgfs = Double.parseDouble(bjgfs);
		// 增加查询条件
		criteria.add(Restrictions.eq("epapers.sjId", paperid));
		criteria.add(Restrictions.eq("djSyzt", new Long(2)));
		criteria.add(Restrictions.lt("djZf", sjbjgfs));
		//criteria.add(Restrictions.lt("djZf", "epapers.sjbhgfs"));
		if (!"".equals(groupId) && groupId != null) {
			criteria.add(Restrictions.eq("groupId", groupId));
		}
		if (!"".equals(startScore) && startScore != null) {
			criteria.add(Restrictions
					.ge("djZf", Double.parseDouble(startScore)));
		}
		if (!"".equals(endScore) && endScore != null) {
			criteria.add(Restrictions.le("djZf", Double.parseDouble(endScore)));
		}

		if (!"".equals(sex) && sex != null) {
			criteria.add(Restrictions.eq("userSex", sex));
		}
		if (!"".equals(userstar) && userstar != null) {
			criteria.add(Restrictions.eq("userStar", userstar));
		}
		if (!"".equals(startctime) && startctime != null) {
			criteria.add(Restrictions.ge("userDate", DateUtils.addDays(
					startctime, -1)));
		}
		if (!"".equals(endctime) && endctime != null) {
			criteria.add(Restrictions.le("userDate", DateUtils.addDays(
					endctime, +1)));
		}

		Integer count = Integer.valueOf( criteria
				.setProjection(Projections.rowCount()).uniqueResult().toString());
		return count.intValue();
	}*/
	public double counttotal(final Long paperid,final  String groupId,final  String startScore,
			final String endScore,final  String sex,final  String userstar,final  Date startctime,
			final Date endctime)throws Exception{
		return (Double) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String bjgfs = new EAnswerpaperDAOImpl().getBhgfs(paperid);
				double sjbjgfs = Double.parseDouble(bjgfs);
				StringBuffer sb = new StringBuffer();
				/*sb.append("select ep.sjMc,u.realname,u.password,u.username,ea.djKssj,ea.djJssj,ea.djZf,el.cheatflag,ea.djId,ea.epapers.sjId from " +
						"EPapers as ep,EUser as u,ELogMonitor as el,EExamArrange as b,EAnswerpaper as ea " +
						"where ep.sjId=b.examid and b.userid=u.id and el.userid=u.id and ea.djRyid=u.username and ea.epapers.sjId=ep.sjId and ep.sjId="+paperid);*/
				sb.append("select p.sj_mc,u.realname,u.password,u.username,ea.dj_kssj,ea.dj_jssj,nvl(ea.dj_zf,0),a.cheatflag,ea.dj_id,p.sj_id FROM" +
						" users u, e_papers p, e_logmonitor a left join e_answerpaper ea on" +
						" a.userid=ea.user_star and ea.sj_id=a.examid where u.user_id=a.userid and a.examid=p.sj_id and p.sj_id="+paperid);
				Query query = session.createSQLQuery(sb.toString());
				List<Object[]> list = query.list();
				double point = 0.0;
				Iterator<Object[]> it = list.iterator();
				while(it.hasNext()){
					Object[] o = it.next();
					point += ((BigDecimal)o[6]).doubleValue();
				}
				return point; 

			}
		});


	}
	/*public double counttotal(Long paperid, String groupId, String startScore,
			String endScore, String sex, String userstar, Date startctime,
			Date endctime) {
		Session session = HibernateUtil.getSession();
		EAnswerpaper n = new EAnswerpaper();
		Criteria criteria = session.createCriteria(EAnswerpaper.class);
		criteria.add(Restrictions.eq("epapers.sjId", paperid));
		criteria.add(Restrictions.eq("djSyzt", new Long(2)));
		if (!"".equals(groupId) && groupId != null) {
			criteria.add(Restrictions.eq("groupId", groupId));
		}
		if (!"".equals(startScore) && startScore != null) {
			criteria.add(Restrictions
					.ge("djZf", Double.parseDouble(startScore)));
		}
		if (!"".equals(endScore) && endScore != null) {
			criteria.add(Restrictions.le("djZf", Double.parseDouble(endScore)));
		}

		if (!"".equals(sex) && sex != null) {
			criteria.add(Restrictions.eq("userSex", sex));
		}
		if (!"".equals(userstar) && userstar != null) {
			criteria.add(Restrictions.eq("userStar", userstar));
		}
		if (!"".equals(startctime) && startctime != null) {
			criteria.add(Restrictions.ge("userDate", DateUtils.addDays(
					startctime, -1)));
		}
		if (!"".equals(endctime) && endctime != null) {
			criteria.add(Restrictions.le("userDate", DateUtils.addDays(
					endctime, +1)));
		}
		List li = criteria.list();
		double point = 0.0;
		for (int i = 0; i < li.size(); i++) {
			n = (EAnswerpaper) li.get(i);
			point += n.getDjZf();
		}
		return point;
	}*/

	/**
	 * 
	 * <p>
	 * Description:保存一个答题
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by [Godspeed He] [2010 7 1]
	 * 
	 * @param eanswerquestions
	 */
public void saveEAnswerquentions(final long paperId)
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		List lst = new EAnswerpaperDAOImpl().findEAnswerquentionsBypaperId(paperId);
		if (lst != null) {
			for (int i = 0; i < lst.size(); i++) {
				EAnswerquestions eAnswerquestions = (EAnswerquestions) lst
						.get(i);
				if (eAnswerquestions.getEpaperquestions().getSjStfs() == eAnswerquestions
						.getStDf()) {

					eAnswerquestions.setRight(new Long(1));
				} else {
					eAnswerquestions.setRight(new Long(0));
				}
				// this.getHibernateTemplate().saveOrUpdate(eAnswerquestions);
				session.merge(eAnswerquestions);
			}
		}
		return null;
	}
});
}
	public List findEAnswerquentionsBypaperId(final long paperId)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EAnswerquestions.class);
				criteria.createAlias("eanswerpaper", "paper").add(
						Restrictions.eq("paper.djId", new Long(paperId).longValue()));
				return criteria.list();	 

			}
		});


	}
	// 将试卷题目的错误统计保存
	public void saveEAnswerresult(final long paperId)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				DecimalFormat per = new DecimalFormat("0.00");
				Criteria criteria = session.createCriteria(
						EPaperquestions.class);
				criteria.add(Restrictions.eq("epapers.sjId", paperId));
				List lst = criteria.list();
				if (lst != null) {
					for (int i = 0; i < lst.size(); i++) {
						EPaperquestions ePaperquestions = (EPaperquestions) lst.get(i);

						String hql = "select t from EAnswerquestions t where t.eanswerpaper.epapers.sjId='"+paperId+ "'" +
								" and t.epaperquestions.equestions.stId='"
								+ ePaperquestions.getEquestions().getStId() + "' ";
						Query query = session.createQuery(hql);
						List lst3 = query.list();
						// System.out.println(lst3.size()+"-----------------");
						// Criteria
						// criteria3=this.getSession().createCriteria(EAnswerquestions
						// .class);
						// criteria3.add(Restrictions.eq("eanswerpaper.epapers",
						// paperId));
						// criteria3.add(Restrictions.eq("epaperquestions.equestions.stId"
						// , ePaperquestions.getEquestions().getStId()));
						// List lst3=criteria3.list();
						long eQuestionId = new Long(0).longValue();
						eQuestionId = ePaperquestions.getEquestions().getStId();
						int right = 0;
						if (lst3 != null && lst3.size() > 0) {
							for (int j = 0; j < lst3.size(); j++) {
								EAnswerquestions eAnswerquestions = (EAnswerquestions) lst3
										.get(j);
								// System.out.println(eAnswerquestions.getRight()+
								// "-----------------");
								if (eAnswerquestions.getRight() != null
										&& eAnswerquestions.getRight() == 1) {
									right++;
								}
							}
							double percent = Double.parseDouble(per
									.format((double) (lst3.size() - right) * 100 / (double) lst3.size()));
							ePaperquestions.setWrong_percent(percent);
							session.merge(ePaperquestions);

							if (eQuestionId != new Long(0).longValue()) {
								EQuestions eQuestions = new EAnswerpaperDAOImpl().findById(eQuestionId);
								//System.out.println("试卷ID-------" + eQuestionId);

								EImportanceService eImportanceService = new EImportanceService();
								if (percent <= (double) 30) {
									eQuestions.setEimportance(eImportanceService
											.findEImportanceById(new Long(1)));

								}
								if (percent > (double) 30 && percent <= (double) 71) {
									eQuestions.setEimportance(eImportanceService
											.findEImportanceById(new Long(2)));

								}
								if (percent > (double) 70) {
									eQuestions.setEimportance(eImportanceService
											.findEImportanceById(new Long(3)));
								}
								session.saveOrUpdate(eQuestions);

							}
						}

					}

				} 
				return null;
			}
		});
		}
	
	public PageReturn findwrongPercent(final PageTool pagetool,final long paperId)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(
						EPaperquestions.class);
				criteria.add(Restrictions.eq("epapers.sjId", paperId));
				PageReturn pageReturn = new PageReturn();
				pageReturn.setTotal(criteria.list().size());
				criteria.setMaxResults(pagetool.getSize());
				criteria.setFirstResult(pagetool.getStart());
				criteria.addOrder(Order.desc("sjStpx"));
				pageReturn.setReturnList(criteria.list());
				return pageReturn;

			}
		});
	}
	/**
	 * 判断试卷是否下放
	 * @param paperid
	 * @return
	 * @throws Exception
	 * @author gkk
	 * @date 2017-1-11 上午9:39:02
	 */
	@SuppressWarnings("unchecked")
	public List<WrongPersent_ws> getpaperXfState(final long paperid)throws Exception{
		return (List<WrongPersent_ws>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select * from WRONGPERSENT_ws where sj_id=? and valid=1";
				//Query query = session.createSQLQuery(sql).addEntity(WrongPersent_ws.class).setLong(0, paperid);
				List<WrongPersent_ws> list = CommonJdbcDaoUtils.query(sql, WrongPersent_ws.class, paperid);
				return list;
			}
		});
	}

	public EQuestions findById(final long id)throws Exception{
		return (EQuestions) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return (EQuestions) session.load(EQuestions.class, id);
			}
		});


	}
	public List queryGroupList()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = " from EKaoshiGroup ";
				Query query = session.createQuery(hql);
				return query.list(); 
			}
		});
	}

	/** 根据相关信息查找某个人员参加过的考试信息 */
	public List findAllanswerpaperbyUser(final EAnswerpaper info)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EAnswerpaper.class);
				if (!"".equals(info.getDjRyid()) && info.getDjRyid() != null) {
					criteria.add(Restrictions.eq("djRyid", info.getDjRyid()));
				}
				criteria.addOrder(Order.desc("djKssj"));
				List list = criteria.list();
				return criteria.list();
			}
		});
	}
	public List selectZhuHeAnswerpaper(final String papers) throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hqlString = "from EAnswerpaper s where s.epapers.sjId in ("
						+ papers + ") order by s.djRyid";
				List list = session.createQuery(hqlString).list();
				return list; 
			}
		});
	}
	public List selectALLExamGroups()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hqlString = "from EKaoshiGroup ";
				List list = session.createQuery(hqlString).list();
				return list;

			}
		});


	}
	public List selectALLExamGroupsIdsNames(final String paperid)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				StringBuffer sbf = new StringBuffer();
				String hqlString = "select u from EKaoshiGroup g,EUser u where u.group.id=g.groupId ";
				hqlString = hqlString
						+ " and u.id not in( select s.djRyid from EAnswerpaper s where s.epapers.sjId in ("
						+ paperid + "))";
				List list = session.createQuery(hqlString).list();
				return list; 

			}
		});


	}

	/** 设置未参加考试原因 */
	public void nopartinexamCause(final EPapers epapers,final String ryid,final String cause)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EUser user = (EUser) session.createQuery(
						"from EUser u where u.id=" + ryid).list().get(0);
				EAnswerpaper answerpaper = new EAnswerpaper();
				answerpaper.setDjId(new Long(0));
				answerpaper.setEpapers(epapers);
				answerpaper.setDjRyid(user.getId().toString());
				answerpaper.setDjRymc(user.getRealname());
				answerpaper.setEanswerquestionses(null);
				answerpaper.setUserSex(user.getSex());// 考试人性别
				answerpaper.setUserStar(user.getUserstar());// 考试人进中心时间
				answerpaper.setUserDate(user.getCreatetime());// 考试人星级
				answerpaper.setGroupId(user.getGroup().getId().toString());// 添加小组的id
				answerpaper.setDjJssj(new Date());
				answerpaper.setDjKssj(new Date());
				answerpaper.setCause(cause);
				session.save(answerpaper);// 保存未参加考试原因答卷 
				return null;
			}
		});
		}
	public EUser findEUser(final String ryid)throws Exception{
		return (EUser) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EUser user = (EUser) session.createQuery(
						"from EUser u where u.id=" + ryid).list().get(0);
				return user; 

			}
		});
	}
	public List findCauseList()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return session.createQuery("from Qingkuang").list();
			}
		});
	}
	public List queryEPapersByDate(final Date startctime,final  Date endctime)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EPapers.class);
				criteria.add(Restrictions.eq("sjZt", new Long("2")));
				String[] paperTypes = {"1","5","6","7"};// 培训评估考试一、培训评估考试二、培训评估考试三、 培训评估考试四
				criteria.add(Restrictions.in("paperType", paperTypes));
				if (startctime != null) {
					criteria.add(Restrictions.ge("sjYxqjzsj", startctime));
				}
				if (endctime != null) {
					criteria.add(Restrictions.le("sjYxqjzsj", endctime));
				}
				return criteria.list();
			}
		});
	}
	public  List queryEAnswerPapersBySjIds(final String sjids) throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				StringBuffer sbf = new StringBuffer(
						"from EAnswerpaper s where s.epapers.sjId in (" + sjids + ")");
				return session.createQuery(sbf.toString()).list();	 

			}
		});
	}
	public  List selectALLExamGroupsInformations(final String paperid,final String sjids)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				StringBuffer sbf = new StringBuffer(
						"from EAnswerpaper p where p.epapers.sjId in(" + sjids + ") ");
				if (paperid != null && !"".equals(paperid)) {
					sbf.append(" and p.groupId='" + paperid + "'");
				}
				return session.createQuery(sbf.toString()).list(); 
			}
		});
	}
	public List getGroupsUserByGroupId(final String id)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hqlString = "select u from EKaoshiGroup g,EUser u where u.group.id=g.groupId ";
				if (id != null && !"".equals(id)) {
					hqlString = hqlString + " and g.groupId='" + id + "'";
				}
				return session.createQuery(hqlString).list(); 

			}
		});
	}
	public List getStarList() throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = " from Estar e ";
				Query query = session.createQuery(hql);
				return query.list(); 

			}
		});
	}
	public String getBhgfs(final Long paperid)throws Exception{
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = "select e.sjBhgfs from EPapers e where e.sjId='"+paperid+"'";
				Query query = session.createQuery(hql);
				String str = query.uniqueResult().toString();
				return str;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public ELogMonitor updateSurplus(final String paperid,final String times)throws Exception{
		return (ELogMonitor) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				AcegiUtil acegiUtil=new AcegiUtil();
		         EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
				String hql="select e from ELogMonitor e where e.userid='"+user.getId()+"'and e.examid='"+paperid+"'";
				ELogMonitor logmonitor=new ELogMonitor();
				List<ELogMonitor> list=session.createQuery(hql).list();
				if(list.size()>0){
					for (ELogMonitor eLogMonitor : list) {
						logmonitor=eLogMonitor;
					}
				}
				logmonitor.setSurplus(times);
				session.saveOrUpdate(logmonitor);
				return logmonitor;

			}
		});
	}
	public void updateEanserPapersForZf(final String paperid,final double defen)throws Exception {
	        HibernateUtil.doInSession(new HibernateSessionCallback() {
				public Object execute(Session session) throws Throwable {
					EAnswerpaper eap=(EAnswerpaper)session.load(EAnswerpaper.class, Long.valueOf(paperid));
					eap.setDjZf(defen);
					session.saveOrUpdate(eap);
					return null;
				}
	        });
	}
	/**
	 * 根据试卷是否为下放试卷获取错误率
	 * @param paperid
	 * @param state
	 * @return
	 * @author gkk
	 * @date 2017-1-11 上午11:27:45
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getWrongPercentList(final long paperid,final int state){
		return (List<Object[]>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "";
				if (state==1) {//1为常规试卷
					sql = "select b.st_tg,b.st_lxid,a.wrong_percent from e_paperquestions a,e_questions b where a.sj_stid=b.st_id and a.sj_id=?";
				}else if (state == 2) {//2为下放试卷
					sql = "select a.st_tg,a.st_lx,a.wrong_persent from wrongpersent_ws a where a.sj_id=?";
				}
				Query query = session.createSQLQuery(sql).setLong(0, paperid);
				List<Object[]> list = query.list();
				return list;
			}
		});
	}
	public EPapers getPaperByid(final long paperid){
		return (EPapers) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select * from E_PAPERS where sj_id=?";
				Query query = session.createSQLQuery(sql).addEntity(EPapers.class).setLong(0, paperid);
				return query.uniqueResult();
			}
		});
	}
}
