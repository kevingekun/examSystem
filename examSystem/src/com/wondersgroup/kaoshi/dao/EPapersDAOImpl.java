/**
 * 
 */
package com.wondersgroup.kaoshi.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.mira.lucene.analysis.e;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.paper.model.EAnswerquestions;
import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPaperquestions_temp;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.kaoshi.bo.EKaoshi;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.kaoshi.bo.UploadGradeDTO;
import com.wondersgroup.kaoshi.bo.UploadToYth;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.local.bo.Answerpaper_wsDTO;
import com.wondersgroup.local.bo.E_answerpaper;
import com.wondersgroup.local.bo.Elogmonitor_wsDTO;
import com.wondersgroup.local.bo.UploadScoreToJdzxRequest;
import com.wondersgroup.local.bo.UploadScoreToJdzxResponse;
import com.wondersgroup.local.bo.UploadScoreToYthRequest;
import com.wondersgroup.local.bo.UploadScoreToYthResponse;
import com.wondersgroup.local.bo.User_examinfo;
import com.wondersgroup.local.bo.WrongPersent_ws;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.utils.WsUtil_yth;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

/**
 * <p>
 * Title:[�����]
 * </p>
 * <p>
 * Description: [�����Ծ��dao]
 * </p>
 * 
 * Created by [www] [Aug 10, 2009] Midified by [�޸���] [�޸�ʱ��]
 * 
 */
public class EPapersDAOImpl extends HibernateDaoSupport {

	public EPapers load(final String paperid) throws Exception{
		return (EPapers) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EPapers epapers = (EPapers) session.load(EPapers.class,
						new Long(paperid));
				return epapers; 
			}
		});
	}
	/**
	 * <p>
	 * Title:[�����]
	 * </p>
	 * <p>
	 * Description: [�����Ծ��dao]
	 * </p>
	 * 
	 * Created by [www] [Aug 10, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 */
	// /将每个考生的题目随机顺序插入
	public void saveEpaperquestions_temp(final String paperId,final  String ryid)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List lsth = null;
				Query qy = session.createQuery(
						"from EPaperquestions_temp  e where e.epapers.sjId ='"
								+ paperId + "' and e.syid='" + ryid + "'  ");
				lsth = qy.list();
				if (lsth == null || lsth.size() == 0) {
					Query query = session.createQuery(
							"from EPaperquestions  e where e.epapers.sjId ='" + paperId
									+ "'   ");
					List lst = null;
					lst = query.list();
					if (lst != null) {
						Collections.shuffle(lst);
						int px = 1;
						for (int i = 0; i < lst.size(); i++) {
							EPaperquestions_temp epqT = new EPaperquestions_temp();
							EPaperquestions ep = (EPaperquestions) lst.get(i);
							epqT.setEpapers(ep.getEpapers());
							epqT.setEquestions(ep.getEquestions());
							epqT.setSjStfs(ep.getSjStfs());
							epqT.setSjStpx(new Long(px).longValue());
							epqT.setState(new Long(0).longValue());
							epqT.setSyid(ryid);
							epqT.setStpx(ep.getSjStpx());
							session.save(epqT);
							px++;
						}
					}
				} 
				return null;
			}
		});
		}
	// 找到一个题目
	public EPaperquestions_temp findOneQuestion(final int sequence,
			final long equestions_type,final  String ryid)throws Exception{
		return (EPaperquestions_temp) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				// Criteria
				// criteria=this.getSession().createCriteria(EPaperquestions_temp
				// .class);
				//			
				// criteria.add(Restrictions.eq("equestions.equestiontype",
				// equestions_type));
				// criteria.add(Restrictions.eq("syid", ryid));
				// criteria.addOrder(Order.asc("sjStpx"));
				// criteria.setMaxResults(1);
				// criteria.setFirstResult(sequence);
				// EPaperquestions_temp equestions=(EPaperquestions_temp)
				// criteria.uniqueResult();
				//			
				// return equestions;

				String sql = "from EPaperquestions_temp  e where e.equestions.equestiontype ='"
						+ equestions_type
						+ "' and e.syid='"
						+ ryid
						+ "' order by e.sjStpx asc ";
				// /System.out.println(user.getUsername());

				// System.out.println(sql);
				Query query = session.createQuery(sql);

				query.setMaxResults(1);
				query.setFirstResult(sequence);

				EPaperquestions_temp equestions = (EPaperquestions_temp) query
						.uniqueResult();
				return equestions;	 

			}
		});
	}
	// /考生读取自己所有的题目 ///将题目插入正式表
	public void saveEpaperquestions_tempAll(final String paperId,final  String ryid)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = session.createQuery(
						"from EPaperquestions_temp  e where e.epapers.sjId ='"
								+ paperId + "' and  e.syid='" + ryid + "' ");
				List lst = null;
				lst = query.list();

				Query query2 = session.createQuery(
						"from EAnswerpaper  e where e.epapers.sjId ='" + paperId
								+ "' and  e.djRyid='" + ryid + "' ");
				EAnswerpaper ea = new EAnswerpaper();
				ea = (EAnswerpaper) query2.list().get(0);

				if (lst != null && lst.size() > 0) {
					for (int i = 0; i < lst.size(); i++) {

						EAnswerquestions eanswerquestions = new EAnswerquestions();
						EPaperquestions_temp epqT = (EPaperquestions_temp) lst.get(i);

						EPaperquestions epq = new EPapersDAOImpl().findbytwo(paperId, epqT
								.getEquestions().getStId());

						eanswerquestions.setEanswerpaper(ea);
						eanswerquestions.setEpaperquestions(epq);
						if (epqT.getStDa() != null) {
							eanswerquestions.setStDa(epqT.getStDa());
						}
						if (epqT.getStDasm() != null) {
							eanswerquestions.setStDasm(epqT.getStDasm());
						}

						eanswerquestions.setStPx(epqT.getStpx());

						session.save(eanswerquestions);
						session.delete(epqT);
					}

				} 
				return null;
			}
		});
		}
	// /找到epaperquestions
	public int findQuestionsBytypeTotal(final int sequence,final  long equestions_type,
			final String ryid)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int i = 0;
				String sql = "from EPaperquestions_temp  e where e.equestions.equestiontype ='"
						+ equestions_type
						+ "' and e.syid='"
						+ ryid
						+ "' order by e.sjStpx asc ";
				Query query = session.createQuery(sql);
				if (query.list().size() != 0) {
					i = query.list().size();
				}
				return i; 
			}
		});
	}
	public int findQuestionsTotal(final String ryid)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int i = 0;
				String sql = "from EPaperquestions_temp  e where  e.syid='" + ryid
						+ "' order by e.sjStpx asc ";
				Query query = session.createQuery(sql);
				if (query.list().size() != 0) {
					i = query.list().size();
				}
				return i; 
			}
		});
	}
	public  int findQuestionsDone(final String ryid)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int i = 0;
				String sql = "from EPaperquestions_temp  e where  e.syid='" + ryid
						+ "' and e.state=1 ";
				Query query = session.createQuery(sql);
				if (query.list().size() != 0) {
					i = query.list().size();
				}
				return i; 

			}
		});
	}
	public int findQuestionsBytypeDone(final int sequence,final  long equestions_type,
			final String ryid)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int i = 0;
				String sql = "from EPaperquestions_temp  e where e.equestions.equestiontype ='"
						+ equestions_type
						+ "' and e.syid='"
						+ ryid
						+ "' and e.state=1 ";
				Query query = session.createQuery(sql);
				if (query.list().size() != 0) {
					i = query.list().size();
				}
				return i; 

			}
		});
	}
	// 找到题目 find byid
	public EPaperquestions_temp loadePaperquestions_temp(final String epqtId)throws Exception{
		return (EPaperquestions_temp) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EPaperquestions_temp epqt = (EPaperquestions_temp) session
						.load(EPaperquestions_temp.class, new Long(epqtId).longValue());
				return epqt;


			}
		});
	}
	// 找到题目 find by type
	public List findByType(final String paperId,final  long equestions_type,final  String ryid)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List lst = null;
				Query query = session.createQuery(
						" from EPaperquestions_temp  e where e.epapers.sjId ='"
								+ paperId + "'   " + "and e.equestions.equestiontype='"
								+ equestions_type + "' " + "and e.syid='" + ryid + "' "
								+ "order by e.sjStpx ");
				lst = query.list();

				return lst;

			}
		});
	}
	public  EPaperquestions_temp findUndone(final int sequence, final String ryid)throws Exception{
		return (EPaperquestions_temp) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "from EPaperquestions_temp  e where e.syid='" + ryid
						+ "' and e.state='0' order by e.sjStpx asc ";
				Query query = session.createQuery(sql);
				query.setMaxResults(1);
				query.setFirstResult(sequence);
				EPaperquestions_temp equestions = (EPaperquestions_temp) query
						.uniqueResult();
				return equestions;	 
			}
		});
	}

	// save eanswerpaper
	public  EPaperquestions findbytwo(final String paperId,final  long questionId)throws Exception{
		return (EPaperquestions) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = session.createQuery(
						"from EPaperquestions  e where e.epapers.sjId ='" + paperId
								+ "' and  e.equestions.stId='" + questionId + "' ");
				EPaperquestions epq = (EPaperquestions) query.list().get(0);
				return epq;
			}
		});
	}

	/**
	 * 
	 * <p>
	 * Description:�����Ծ�
	 * </p>
	 * 
	 * Created by [www] [Aug 14, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param epapers
	 */
	public void savePaper(final EPapers epapers) 
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		try {
			session.saveOrUpdate(epapers);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
});
}

	public void saveEpaperquestions_temp(final EPaperquestions_temp epqt)
			throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					session.saveOrUpdate(epqt);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return null;
			}
		});
	}
	/**
	 * 点击考试完成时找出答题但没有交卷的考生，提交答卷
	 * @param map
	 * @param ac
	 * @author gkk
	 * @date 2017-2-16 下午4:30:18
	 */
	public void calculateScore(Map<Long, Long> map,ApplicationContext ac){
		DataSource dataSource = (DataSource) ac.getBean("dataSource");
		EAnswerpaperDAOImpl eAnswerpaperDAOImpl = new EAnswerpaperDAOImpl();
		for (Map.Entry<Long, Long> entry : map.entrySet()) {
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			eAnswerpaperDAOImpl.submitPaper(entry.getKey(),String.valueOf(entry.getValue()), conn);
		}
	}
	/**
	 * 
	 * 
	 * <p>
	 * Description:�ı��Ծ��״̬
	 * </p>
	 * 
	 * Created by [www] [Aug 16, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param paperid
	 */
	@SuppressWarnings("unchecked")
	public Map<Long, Long> updateState(final String paperid,
			final String paperState)
			throws Exception {
		return (Map<Long, Long>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Map<Long, Long> execute(Session session) throws Throwable {
						// 统计错误率
						EAnswerpaperDAOImpl apd = new EAnswerpaperDAOImpl();
						apd.saveEAnswerresult(Long.valueOf(paperid));
						// 将参加考试但是没有提交答卷的考生的试卷提交
						String sql = "select * from e_logmonitor a where a.examid="
								+ paperid + " and a.state in ('2','3')";
						Query query = session.createSQLQuery(sql).addEntity(
								ELogMonitor.class);
						List<ELogMonitor> list = query.list();
						Iterator<ELogMonitor> it = list.iterator();
						String sql2 = "select * from users a where a.user_id=?";
						String sql3 = "delete from e_kaoshi a where a.ry_id=?";
						EPapersDAOImpl ePapersDAOImpl = new EPapersDAOImpl();
						Map<Long, Long> map = new HashMap<Long, Long>();
						while (it.hasNext()) {
							ELogMonitor elog = (ELogMonitor) it.next();
							Query query2 = session.createSQLQuery(sql2)
									.addEntity(EUser.class)
									.setLong(0, elog.getUserid());
							EUser user = (EUser) query2.uniqueResult();
							EPapers papers = ePapersDAOImpl.load(paperid);
							Query query3 = session.createSQLQuery(sql3)
									.setLong(0, elog.getUserid());
							query3.executeUpdate();
							ePapersDAOImpl.updateLogMonitore(paperid,
									String.valueOf(elog.getUserid()));
							EAnswerpaper eanswerpaper = new EAnswerpaper();
							eanswerpaper.setEpapers(papers);
							eanswerpaper.setDjRyid(user.getUsername()
									.toString());// 考试人员用户名(身份证号)
							eanswerpaper.setDjRymc(user.getRealname());
							eanswerpaper.setUserSex(user.getPassword());// 考试人员密码(准考证号)
							eanswerpaper.setUserStar(String.valueOf(user
									.getId()));// 考试人员id
							eanswerpaper.setUserDate(user.getCreatetime());// 考试人进中心时间
							if (!"4".equals(papers.getPaperType())) {
								eanswerpaper.setFlag("0");
							} else {
								eanswerpaper.setFlag("1");
							}
							eanswerpaper.setDjSyzt(new Long(2));// 2表示立即出分
							// eanswerpaper.setDjZf(99);
							eanswerpaper.setGroupId(user.getGroup().getId()
									.toString());// 添加小组的id
							eanswerpaper.setDjJssj(new Date());
							eanswerpaper.setDjKssj(new Date());
							session.save(eanswerpaper);
							map.put(eanswerpaper.getDjId(), user.getId());
						}

						// EPapers epapers = new EPapersDAOImpl().load(paperid);
						Criteria criteria = session
								.createCriteria(EPapers.class);
						criteria.add(Restrictions.eq("sjId",
								Long.valueOf(paperid)));
						EPapers epapers = (EPapers) criteria.list().get(0);
						// EPapers epapers = (EPapers)
						// session.get(EPapers.class, Long.valueOf(paperid));
						epapers.setSjZt(new Long(paperState).longValue());
						session.saveOrUpdate(epapers);
						// new EPapersDAOImpl().savePaper(epapers);
						return map;
					}
				});
	}
	/**
	 * 
	 * 
	 * <p>
	 * Description:�ı��Ծ��״̬
	 * </p>
	 * 
	 * Created by [hch] [] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param paperid
	 */
	public void  updatecheckpaper(final String paperid,final  String paperState,
			final String advice,final  String rymc)
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		EPapers epapers = new EPapersDAOImpl().load(paperid);
		epapers.setSjZt(new Long(paperState).longValue());
		epapers.setSjAdvice(advice);
		epapers.setSjSysj(new Date());
		epapers.setSjSyrid(rymc);
		new EPapersDAOImpl().savePaper(epapers);
		return null;
	}
});
}
	/**
	 * 
	 * 
	 * <p>
	 * Description:���ÿ�����Ա״̬
	 * </p>
	 * 
	 * Created by [hch] [2.3, 2010] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param userid
	 */
	public void addEKaoshi(final EKaoshi eKaoshi) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					session.save(eKaoshi);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				return null;
			}
		});
	}
	//
	public  EKaoshi findbyryid()throws Exception{
		return (EKaoshi) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EKaoshi n = new EKaoshi();
				Criteria criteria = session.createCriteria(EKaoshi.class);
				AcegiUtil acegiUtil = new AcegiUtil();
				EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
				criteria.add(Restrictions.eq("Ryid", user.getId()));
				List li = criteria.list();
				if (li != null && li.size() > 0) {
					n = (EKaoshi) li.get(0);
				}
				return n;
			}
		});
	}

	public void delete() throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				//HibernateUtil.beginTransaction();
				EKaoshi n = new EKaoshi();
				Criteria criteria = session.createCriteria(EKaoshi.class);
				AcegiUtil acegiUtil = new AcegiUtil();
				EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails())
						.getUser();
				criteria.add(Restrictions.eq("Ryid", user.getId()));
				List li = criteria.list();
				for (int i = 0; i < li.size(); i++) {
					session.delete((EKaoshi) li.get(i));
				}
				//HibernateUtil.commitTransaction();
				return null;
			}
		});
	}
	/**
	 * 
	 * 
	 * <p>
	 * Description:�ҵ������ܿ��Ե�
	 * </p>
	 * 
	 * Created by [www] [Aug 17, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param pagetool
	 * @return
	 */

	public  PageReturn findpaperbyCanexam(final PageTool pagetool,final  String sjMc,
			final Long sjZt)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				AcegiUtil acegiUtil = new AcegiUtil();
				EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
				/*Criteria criteria = session.createCriteria(EExamArrange.class);
					criteria.add(Restrictions.eq("userid", user));
				List<EExamArrange> list=criteria.list();
				String paperid="";
				for (EExamArrange eExamArrange : list) {
					if(paperid==""){
						paperid=eExamArrange.getExamid().toString();
					}else{
						paperid+=","+eExamArrange.getExamid().toString();
					}
				}
				String sql = "from EPapers  e where e.sjZt =1 and TO_number(TO_CHAR(sysdate, 'YYYYMMDD')) between TO_number(TO_CHAR(e.sjKksj, 'YYYYMMDD')) and TO_number(TO_CHAR(e.sjYxqjzsj, 'YYYYMMDD')) and e.paperType !=4 "
						+ "and e.sjId not in (select t.epapers.sjId from EAnswerpaper t where t.djRyid ='"
						+ user.getUsername() + "') ";
				if (paperid != null && !paperid.equals("")) {
					String d = paperid.substring(paperid.length()-1, paperid.length());
					if((",").equals(d)){
						paperid = paperid.substring(0, paperid.length()-1);
					}
					sql += " and e.sjId in("+paperid+") ";
				}
				// /System.out.println(user.getUsername());
				if (sjMc != null && !sjMc.equals("")) {
					sql += " and e.sjMc like '%" + sjMc + "%'";
				}
				if (sjZt != null && sjZt.longValue() != -1) {
					sql += " and e.sjZt = " + sjZt.longValue() + "";
				}*/
				
				/*String sql1 = "from EPapers e where e.sjZt =1 and TO_number(TO_CHAR(sysdate, 'YYYYMMDD')) between TO_number(TO_CHAR(e.sjKksj, 'YYYYMMDD')) and TO_number(TO_CHAR(e.sjYxqjzsj, 'YYYYMMDD')) and " +
						"e.paperType !=4 and e.sjId in ("+user.getUserbianhao()+")";*/
				
				String sql1 = "from EPapers e where e.sjZt =1 and sysdate between e.sjKksj and e.sjYxqjzsj and " +
						"e.paperType !=4 and e.sjId in ("+user.getUserbianhao()+")";
				
				Query query = session.createQuery(sql1);
				pageReturn.setTotal(query.list().size());
				query.setMaxResults(pagetool.getSize());
				query.setFirstResult(pagetool.getStart());
				pageReturn.setReturnList(query.list());
				return pageReturn;

			}
		});
	}
	public  PageReturn findpaperbyCanceping(final PageTool pagetool,final  String sjMc,
			final Long sjZt)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				String sql = "from EPapers  e where e.sjZt =9 and sysdate between e.sjKksj and e.sjYxqjzsj ";
				if (sjMc != null && !sjMc.equals("")) {
					sql += " and e.sjMc like '%" + sjMc + "%'";
				}
				if (sjZt != null && sjZt.longValue() != -1) {
					sql += " and e.sjZt = " + sjZt.longValue() + "";
				}
				Query query = session.createQuery(sql);
				pageReturn.setTotal(query.list().size());
				query.setMaxResults(pagetool.getSize());
				query.setFirstResult(pagetool.getStart());
				pageReturn.setReturnList(query.list());
				return pageReturn; 

			}
		});
	}
	public String uploadToYthByWs(final UploadToYth uploadToYth){
		
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select a.username,a.realname,a.password,b.sj_mc,b.sj_zt,c.state,c.cheatflag,b.sj_id,a.userflag from e_papers b, E_LOGMONITOR c,users a where b.sj_mc='"+uploadToYth.getSjMc()+"' and b.sj_zt='2' and b.sj_id=c.examid and c.userid=a.user_id";
				String sql2 = "select a.* from E_ANSWERPAPER a,e_papers b where b.sj_mc='"+uploadToYth.getSjMc()+"' and b.sj_id=a.sj_id";
				String sql3 = "select sj_bhgfs from e_papers where sj_mc='"+uploadToYth.getSjMc()+"' and sj_zt=2";
				Query query = session.createSQLQuery(sql);
				Query query2 = session.createSQLQuery(sql2);
				Query query3 = session.createSQLQuery(sql3);
				List<Object[]> list = query.list();
				List<Object[]> list2 = query2.list();
				BigDecimal bhgfs = (BigDecimal) query3.uniqueResult();
				List<User_examinfo> uList = new ArrayList<User_examinfo>();
				List<E_answerpaper> eList = new ArrayList<E_answerpaper>();
				String batch = String.valueOf(new Date().getTime());
				for (Object[] o : list) {
					User_examinfo uef = new User_examinfo();
					uef.setUsername((String)o[0]);
					uef.setRealname((String)o[1]);
					uef.setPassword((String)o[2]);
					uef.setSj_mc((String)o[3]);
					uef.setSj_zt(((BigDecimal)o[4]).toString());
					uef.setState((String)o[5]);
					uef.setCheatflag((String)o[6]);
					uef.setSj_id(((BigDecimal)o[7]).longValue());
					uef.setAac001(Long.valueOf("".equals((String)o[8])?"0":((String)o[8])));
					uef.setBatch(batch);
					uList.add(uef);
				}
				for (Object[] o  : list2) {
					E_answerpaper eap = new E_answerpaper();
					eap.setSj_id(((BigDecimal)o[1]).longValue());
					eap.setDj_ryid((String)o[2]);
					eap.setDj_kssj((Date) o[3]);
					eap.setDj_jssj((Date) o[4]);
					eap.setDj_zf((BigDecimal)o[5]);
					eap.setDj_syzt(((BigDecimal)o[6]).intValue());
					eap.setDj_cjpm(((BigDecimal)o[7]).intValue());
					eap.setDj_rymc((String)o[8]);
					eap.setGroup_id((String)o[9]);
					eap.setUser_star((String)o[10]);
					eap.setUser_sex((String)o[11]);
					eap.setUser_date((Date) o[12]);
					eap.setE_cause((String) o[13]);
					eap.setE_flag("0");
					eap.setBatch(batch);
					eList.add(eap);
				}
				//WsUtil util = new WsUtil();
				//CompZpUploadResponse response = util.uploadToYth(list, list2,uploadToYth.getSjMc(),AcegiUtil.getEUser().getRealname(),bhgfs.intValue());
				UploadScoreToYthRequest input = new UploadScoreToYthRequest();
				input.setList(uList);
				input.setAnswerPaperList(eList);
				input.setSjmc(uploadToYth.getSjMc());
				input.setName(AcegiUtil.getEUser().getRealname());
				input.setBhgfs(bhgfs.intValue());
				input.setBatch(batch);
				UploadScoreToYthResponse response = WsUtil_yth.uploadScoreToYth(input);
				
				if(response.getRtCode()==0){
					String sql4 = "update e_papers set sj_cjgbzt=1 where sj_mc='"+uploadToYth.getSjMc()+"'";
					Query query4 = session.createSQLQuery(sql4);
					query4.executeUpdate();
				}
				return response.getRtMsg();
			}
		});
	}
	/**
	 * 上传成绩到鉴定中心（webservice）
	 * @param uploadToYth
	 * @return
	 */
	public String uploadToJdzxByWs(final UploadToYth uploadToYth){
		
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sjmc = uploadToYth.getSjMc();
				String sql = "select a.* from e_logmonitor a,e_papers b where a.examid=b.sj_id and b.sj_mc='"+sjmc+"'";
				String sql2 = "select a.* from e_answerpaper a,e_papers b where a.sj_id=b.sj_id and b.sj_mc='"+sjmc+"'";
				
				String sql4 = "select a.userid,a.examid,a.ip,to_char(a.logindate,'yyyy-mm-dd hh24:mi:ss') logindate," +
						"to_char(a.startdate,'yyyy-mm-dd hh24:mi:ss') startdate," +
						"to_char(a.enddate,'yyyy-mm-dd hh24:mi:ss') enddate," +
						"a.flag,a.state,a.surplus,a.cheatflag,a.remarks,a.ifxz from " +
						"e_logmonitor a,e_papers b where b.sj_mc=? and a.examid=b.sj_id";
				String sql5 = "select a.sj_id,a.dj_ryid,to_char(a.dj_kssj,'yyyy-mm-dd hh24:mi:ss') dj_kssj," +
						"to_char(a.dj_jssj,'yyyy-mm-dd hh24:mi:ss') dj_jssj," +
						"a.dj_zf,a.dj_syzt,a.dj_cjpm,a.dj_rymc,a.group_id,a.user_star,a.user_sex," +
						"to_char(a.user_date,'yyyy-mm-dd hh24:mi:ss') user_date,a.e_cause,a.e_flag from" +
						" e_answerpaper a,e_papers b where a.sj_id=b.sj_id and b.sj_mc=?";
				String sql6 = "select a.sj_id,b.st_tg st_tg,nvl(wrong_percent,'0') wrong_persent,'1' valid,b.st_lxid st_lx from e_paperquestions a,e_questions b,e_papers c where a.sj_stid=b.st_id and a.sj_id=c.sj_id and c.sj_mc=?";
				List<Elogmonitor_wsDTO> logList = CommonJdbcDaoUtils.query(sql4, Elogmonitor_wsDTO.class, sjmc);
				List<Answerpaper_wsDTO> ansList = CommonJdbcDaoUtils.query(sql5, Answerpaper_wsDTO.class, sjmc);
				List<WrongPersent_ws> wpList = CommonJdbcDaoUtils.query(sql6, WrongPersent_ws.class, sjmc);
				
				/*Query query = session.createSQLQuery(sql).addEntity(Elogmonitor_ws.class);
				Query query2 = session.createSQLQuery(sql2).addEntity(Answerpaper_ws.class);
				List<Elogmonitor_ws> list1 = query.list();
				List<Answerpaper_ws> list2 = query2.list();*/
				
				//WsUtil_uploadScoreToJdzx util = new WsUtil_uploadScoreToJdzx();
				//ggjyBaseInfoResponse response = util.uploadScoreToJdzx(uploadToYth.getExamId(), list1, list2);
				UploadScoreToJdzxRequest request = new UploadScoreToJdzxRequest();
				request.setList_log(logList);
				request.setList_ans(ansList);
				request.setList_wp(wpList);
				request.setSjid(uploadToYth.getExamId());
				UploadScoreToJdzxResponse response = WsUtil_yth.uploadScoreToJdzx(request);
				if("0".equals(response.getCode())){
					String sql3 = "update e_papers a set a.sj_cjhc=1 where a.sj_mc='"+uploadToYth.getSjMc()+"'";
					Query query3 = session.createSQLQuery(sql3);
					query3.executeUpdate();
				}
				return response.getMsg();
				
			}
		});
	}
	public UploadToYth uploadToYth(UploadToYth uploadToYth){
		String driver = "oracle.jdbc.driver.OracleDriver";  
	    String strUrl = ProcedureUrl.PROC_URL_STRING;   
	     Connection conn = null;
	     CallableStatement proc = null;  
	     try {  
	     Class.forName(driver);  
	     conn = DriverManager.getConnection(strUrl, "","");
	      
	      proc = conn.prepareCall("{ call exam_UploadScore(?,?,?,?,?) }");  
	      proc.setString(1, uploadToYth.getExamId());
	      proc.setString(2, uploadToYth.getSjMc());
	      proc.setString(3, uploadToYth.getName());
	      proc.registerOutParameter(4, Types.INTEGER);  
	      proc.registerOutParameter(5, Types.VARCHAR);  
	      proc.execute();  
	      uploadToYth.setRetCode(Long.valueOf(proc.getString(4)));
	      uploadToYth.setRetMsg(proc.getString(5));
	      return uploadToYth;
	   }  
	    catch (SQLException ex2) {
	    	uploadToYth.setRetCode(Long.valueOf("2"));
	    	uploadToYth.setRetMsg(ex2.getMessage());
	      ex2.printStackTrace();  
	    }  
	    catch (Exception ex2) {  
	      ex2.printStackTrace();
	      uploadToYth.setRetCode(Long.valueOf("3"));
	      uploadToYth.setRetMsg(ex2.getMessage());
	    }finally{  
	         if(proc!=null){  
	             try {  
	                proc.close();  
	       	    } catch (SQLException e) {  
	       	     e.printStackTrace();  
	       	      }  
	       	    }  
	       	   if(conn!=null){  
	             try {  
	                     conn.close();  
	       	     } catch (SQLException e) {  
	       	        e.printStackTrace();  
	       }  
	       	     }  
	        }
	    if(uploadToYth.getRetCode() != Long.valueOf("0")){
			throw new BusinessException(uploadToYth.getRetMsg());
		}
		return uploadToYth; 
	}
	public boolean changeGrade(final String sjid, final String userid, final String grade) {
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				long sjId = Long.parseLong(sjid);
				Long zf = Long.valueOf(grade);
				//String hql = "update EAnswerpaper as ea set ea.djZf="+grade+" where ea.epapers.sjId="+sjId+" and ea.userStar='"+userid+"'";
				String hql = "update E_ANSWERPAPER e set e.dj_zf="+zf+" where e.sj_id="+sjId+" and e.user_star='"+userid+"'";
				Query query = session.createSQLQuery(hql);
				try {
					query.executeUpdate();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		});
	}
	/**
	 * 成绩上传查询
	 * @param pagetool
	 * @param sjzt
	 * @param epapers
	 * @param eUser
	 * @param begin
	 * @param flag
	 * @param pcid
	 * @return
	 * @throws Exception
	 * @author gkk
	 * @date 2016-11-15 上午10:37:02
	 */
	public  PageReturn findgrade(final PageTool pagetool,final  Long sjzt,final  EPapers epapers,
			final EUser eUser,final Date begin,final String flag,final String pcid)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				StringBuffer sb = new StringBuffer();
				/*sb.append("select ep.sjMc,ep.sjKksj,u.realname,u.username,ea.djZf,el.cheatflag,ea.djId from " +
						"EPapers as ep,EUser as u,ELogMonitor as el,EExamArrange as b,EAnswerpaper as ea " +
						"where ep.sjId=b.examid and b.userid=u.id and el.userid=u.id and ea.djRyid=u.username and ea.epapers.sjId=ep.sjId");*/
				sb.append("select p.sj_mc,ea.dj_kssj,u.realname,u.username,ea.dj_zf,a.cheatflag,p.sj_id,u.user_id FROM" +
						" users u, e_papers p, hz95 b ,e_logmonitor a left join e_answerpaper ea on" +
						" a.userid=ea.user_star where p.sj_kslx='1' and u.user_id=a.userid and a.examid=p.sj_id and u.olduserbianhao = b.jd_id");
				if(sjzt!=null){
					sb.append(" and p.sj_zt="+sjzt);
				}
				if(epapers.getSjMc()!=null&&!"".equals(epapers.getSjMc())){
					sb.append(" and p.sj_mc='"+epapers.getSjMc()+"'");
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(begin!=null){
					sb.append(" and p.sj_kksj = to_date('"+formatter.format(begin)+"','yyyy-MM-dd HH24:mi:ss')");
				}
				if(eUser.getRealname()!=null&&!"".equals(eUser.getRealname())){
					sb.append(" and u.realname='"+eUser.getRealname()+"'");
				}
				if(eUser.getUsername()!=null&&!"".equals(eUser.getUsername())){
					sb.append(" and u.username='"+eUser.getUsername()+"'");
				}
				if(!"".equals(pcid)&&pcid!=null){
					sb.append(" and b.jd_mc like '"+pcid+"%'");
				}
				Query query = session.createSQLQuery(sb.toString());
				pageReturn.setTotal(query.list().size());
				query.setMaxResults(pagetool.getSize());
				query.setFirstResult(pagetool.getStart());
				List<Object[]> list = query.list();
				Iterator<Object[]> it = list.iterator();
				List<UploadGradeDTO> u_list = new ArrayList<UploadGradeDTO>();
				while(it.hasNext()){
					Object[] o = it.next();
					UploadGradeDTO ug = new UploadGradeDTO();
					ug.setSjMc((String)o[0]);
					ug.setSjKksj((Date)o[1]);
					ug.setRealname((String)o[2]);
					ug.setUsername((String)o[3]);
					ug.setDjZf(((BigDecimal)(o[4]==null?new BigDecimal(0):o[4])).doubleValue());
					ug.setCheatflag((String)o[5]);
					ug.setSjId(((BigDecimal)o[6]).longValue());
					ug.setUserid(((BigDecimal)o[7]).longValue());
					u_list.add(ug);
				}
				pageReturn.setReturnList(u_list);
				return pageReturn;

			}
		});
	}
	/**
	 * 成绩查询
	 * @param pagetool
	 * @param sjzt
	 * @param epapers
	 * @param eUser
	 * @param begin
	 * @param flag
	 * @param pcid
	 * @return
	 * @throws Exception
	 * @author gkk
	 * @date 2016-11-15 上午10:36:47
	 */
	public  PageReturn gradeSearch(final PageTool pagetool,final  Long sjzt,final  EPapers epapers,
			final EUser eUser,final Date begin,final String flag,final String pcid)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				StringBuffer sb = new StringBuffer();
				sb.append("select p.sj_mc,ea.dj_kssj,u.realname,u.username,ea.dj_zf,a.cheatflag,p.sj_id,u.user_id FROM" +
						" users u, e_papers p, hz95 b ,e_logmonitor a left join e_answerpaper ea on" +
						" a.userid=ea.user_star where u.user_id=a.userid and a.examid=p.sj_id and u.olduserbianhao = b.jd_id");
				if(sjzt!=null){
					sb.append(" and p.sj_zt="+sjzt);
				}
				if(epapers.getSjMc()!=null&&!"".equals(epapers.getSjMc())){
					sb.append(" and p.sj_mc like '%"+epapers.getSjMc()+"%'");
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(begin!=null){
					sb.append(" and p.sj_kksj = to_date('"+formatter.format(begin)+"','yyyy-MM-dd HH24:mi:ss')");
				}
				if(eUser.getRealname()!=null&&!"".equals(eUser.getRealname())){
					sb.append(" and u.realname like '%"+eUser.getRealname()+"%'");
				}
				if(eUser.getUsername()!=null&&!"".equals(eUser.getUsername())){
					sb.append(" and u.username like '%"+eUser.getUsername()+"%'");
				}
				if(!"".equals(pcid)&&pcid!=null){
					sb.append(" and b.jd_mc like '%"+pcid+"%'");
				}
				Query query = session.createSQLQuery(sb.toString());
				pageReturn.setTotal(query.list().size());
				query.setMaxResults(pagetool.getSize());
				query.setFirstResult(pagetool.getStart());
				List<Object[]> list = query.list();
				Iterator<Object[]> it = list.iterator();
				List<UploadGradeDTO> u_list = new ArrayList<UploadGradeDTO>();
				while(it.hasNext()){
					Object[] o = it.next();
					UploadGradeDTO ug = new UploadGradeDTO();
					ug.setSjMc((String)o[0]);
					ug.setSjKksj((Date)o[1]);
					ug.setRealname((String)o[2]);
					ug.setUsername((String)o[3]);
					ug.setDjZf(((BigDecimal)(o[4]==null?new BigDecimal(0):o[4])).doubleValue());
					ug.setCheatflag((String)o[5]);
					ug.setSjId(((BigDecimal)o[6]).longValue());
					ug.setUserid(((BigDecimal)o[7]).longValue());
					u_list.add(ug);
				}
				pageReturn.setReturnList(u_list);
				return pageReturn;
				
			}
		});
	}
	/**
	 * 获取可上传成绩到鉴定中心的试卷考生信息
	 * @param pagetool
	 * @param sjzt
	 * @param epapers
	 * @param eUser
	 * @param begin
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public  PageReturn findGradeForUploadToJdzx(final PageTool pagetool,final  Long sjzt,final  EPapers epapers,
			final EUser eUser,final  Date begin,final  String flag)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				StringBuffer sb = new StringBuffer();
				/*sb.append("select ep.sjMc,ep.sjKksj,u.realname,u.username,ea.djZf,el.cheatflag,ea.djId from " +
						"EPapers as ep,EUser as u,ELogMonitor as el,EExamArrange as b,EAnswerpaper as ea " +
						"where ep.sjId=b.examid and b.userid=u.id and el.userid=u.id and ea.djRyid=u.username and ea.epapers.sjId=ep.sjId");*/
				sb.append("select p.sj_mc,ea.dj_kssj,u.realname,u.username,ea.dj_zf,a.cheatflag,p.sj_id,u.user_id FROM" +
						" users u, e_papers p, e_logmonitor a left join e_answerpaper ea on" +
						" a.userid=ea.user_star where p.sj_kslx='1' and u.user_id=a.userid and a.examid=p.sj_id and p.SJ_CJHC=0");
				if(sjzt!=null){
					sb.append(" and p.sj_zt="+sjzt);
				}
				if(epapers.getSjMc()!=null&&!"".equals(epapers.getSjMc())){
					sb.append(" and p.sj_mc='"+epapers.getSjMc()+"'");
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(begin!=null){
					sb.append(" and p.sj_kksj = to_date('"+formatter.format(begin)+"','yyyy-MM-dd HH24:mi:ss')");
				}
				if(eUser.getRealname()!=null&&!"".equals(eUser.getRealname())){
					sb.append(" and u.realname='"+eUser.getRealname()+"'");
				}
				if(eUser.getUsername()!=null&&!"".equals(eUser.getUsername())){
					sb.append(" and u.username='"+eUser.getUsername()+"'");
				}
				Query query = session.createSQLQuery(sb.toString());
				pageReturn.setTotal(query.list().size());
				query.setMaxResults(pagetool.getSize());
				query.setFirstResult(pagetool.getStart());
				List<Object[]> list = query.list();
				Iterator<Object[]> it = list.iterator();
				List<UploadGradeDTO> u_list = new ArrayList<UploadGradeDTO>();
				while(it.hasNext()){
					Object[] o = it.next();
					UploadGradeDTO ug = new UploadGradeDTO();
					ug.setSjMc((String)o[0]);
					ug.setSjKksj((Date)o[1]);
					ug.setRealname((String)o[2]);
					ug.setUsername((String)o[3]);
					ug.setDjZf(((BigDecimal)(o[4]==null?new BigDecimal(0):o[4])).doubleValue());
					ug.setCheatflag((String)o[5]);
					ug.setSjId(((BigDecimal)o[6]).longValue());
					ug.setUserid(((BigDecimal)o[7]).longValue());
					u_list.add(ug);
				}
				pageReturn.setReturnList(u_list);
				return pageReturn;
				
			}
		});
	}
	public  PageReturn findpaperbysjzt(final PageTool pagetool,final  Long sjzt,
			final EPapers epapers,final  Date begin,final Date zjsj,final  String flag) throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				Criteria criteria = session.createCriteria(EPapers.class);
				if ("4".equals(flag)) {
					criteria.add(Restrictions.eq("paperType", flag));
				} else {
					criteria.add(Restrictions.not(Restrictions.eq("paperType", "4")));
				}
				// ���Խ����
				if (sjzt != null) {
					criteria.add(Restrictions.eq("sjZt", sjzt));
				}
				// ������ɵ�
				// criteria.add(Restrictions.eq("sjSyzt", new Long(2)));
				if (epapers.getSjMc() != null && !epapers.getSjMc().equals("")) {
					criteria.add(Restrictions.like("sjMc", epapers.getSjMc(),
							MatchMode.ANYWHERE));
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (begin != null) {
					Date date1 = new Date();
					Date date2 = new Date();
					String d = formatter.format(begin);
					Calendar cal = Calendar.getInstance();
					try {
						date1 = formatter.parse(d);
						cal.setTime(date1);
						cal.add(Calendar.DATE, 1);
						String d2 = formatter.format(cal.getTime());
						date2 = formatter.parse(d2);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					criteria.add(Restrictions.between("sjKksj", date1, date2));
				}
				if (zjsj != null) {
					Date date1 = new Date();
					Date date2 = new Date();
					String d = formatter.format(zjsj);
					Calendar cal = Calendar.getInstance();
					try {
						date1 = formatter.parse(d);
						cal.setTime(date1);
						cal.add(Calendar.DATE, 1);
						String d2 = formatter.format(cal.getTime());
						date2 = formatter.parse(d2);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					criteria.add(Restrictions.between("sjZjsj", date1, date2));
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
	public EUser queryUserById(final String toUserId) throws Exception{
		return (EUser) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EUser.class);
				criteria.add(Restrictions.eq("id", new Long(toUserId)));
				EUser u = (EUser) criteria.list().get(0);
				return u; 

			}
		});
	}

	public void updateUserIP(final String userIP,final Long examid) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				// 得到人员
				AcegiUtil acegiUtil = new AcegiUtil();
				EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails())
						.getUser();
				Criteria criteria = session.createCriteria(ELogMonitor.class);
				criteria.add(Restrictions.eq("userid", user.getId()));
				criteria.add(Restrictions.eq("examid", examid));
				criteria.add(Restrictions.ne("state", "4"));
				List list = criteria.list();
				if (list.size() > 0) {
					ELogMonitor monitor = (ELogMonitor) list.get(0);
					monitor.setIp(userIP);
					monitor.setLogindate(new Date());
					// 设置此账户正常，更新考试时间
					monitor.setCheatflag("0");
					session.saveOrUpdate(monitor);
				}
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<String> getGz(final String gzid,final String gzdj){
		return (List<String>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<String> rList = new ArrayList<String>();
				Criteria criteria = session.createCriteria(Tjobsubject.class);
				criteria.add(Restrictions.eq("id_job", gzid));
				List<Tjobsubject> list = criteria.list();
				if(list!=null&&list.size()!=0){
					Tjobsubject tjobsubject = list.get(0);
					rList.add(tjobsubject.getJobname());
					String dj = "";
					if("1".equals(gzdj)){
						dj = "一级";
					}else if("2".equals(gzdj)){
						dj = "二级";
					}else if("3".equals(gzdj)){
						dj = "三级";
					}else if("4".equals(gzdj)){
						dj = "四级";
					}else if("5".equals(gzdj)){
						dj = "五级";
					}else if("6".equals(gzdj)){
						dj = "专项";
					}
					rList.add(dj);
				}
				return rList;
			}
		});
	}

	public void updateUserStratDt(final String paperId) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				// 得到人员
				AcegiUtil acegiUtil = new AcegiUtil();
				EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails())
						.getUser();
				Criteria criteria = session.createCriteria(ELogMonitor.class);
				criteria.add(Restrictions.eq("userid", user.getId()));
				criteria.add(Restrictions.eq("examid", Long.valueOf(paperId)));
				criteria.add(Restrictions.ne("state", "4"));
				//System.out.println("userid=" + user.getId() + "    listsize()="+ criteria.list());
				List list = criteria.list();
				if (list.size() > 0) {
					ELogMonitor monitor = (ELogMonitor) list.get(0);
					//monitor.setStartdate(new Date());
					monitor.setState("2");
					session.saveOrUpdate(monitor);
				}
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> getSurplus(final String paperid) throws Exception{
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				 AcegiUtil acegiUtil=new AcegiUtil();
		         EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
				String hql="select e from ELogMonitor e where e.userid='"+user.getId()+"'and e.examid='"+paperid+"'";
				ELogMonitor logmonitor=new ELogMonitor();
				List<ELogMonitor> list=session.createQuery(hql).list();
				List<Object> list2 = new ArrayList<Object>();
				if(list.size()>0){
					ELogMonitor eLogMonitor = list.get(0);
					list2.add(eLogMonitor.getSurplus());
					list2.add(eLogMonitor.getStartdate());
				}
				return list2;
			}
		});
	}

	public void updateLogMonitore(final String paperid, final String userid)
			throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(ELogMonitor.class);
				criteria.add(Restrictions.eq("userid", Long.valueOf(userid)));
				criteria.add(Restrictions.eq("examid", Long.valueOf(paperid)));
				List<ELogMonitor> list = criteria.list();
				if (list.size() > 0) {
					ELogMonitor eea = list.get(0);
					eea.setEnddate(new Date());
					eea.setState("4");
					session.saveOrUpdate(eea);
				}
				return null;
			}
		});
	}
	public ELogMonitor getELogMonitor(final Long userid,final String paperid){
		return (ELogMonitor) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(ELogMonitor.class);
				criteria.add(Restrictions.eq("userid", userid));
				criteria.add(Restrictions.eq("examid", Long.valueOf(paperid)));
				List<ELogMonitor> list = criteria.list();
				ELogMonitor eLogMonitor = null;
				if(list.size()>0){
					eLogMonitor = list.get(0);
				}
				return eLogMonitor;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<String> getIpList(final String paperid){
		return (List<String>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select u.putong from E_LOGMONITOR e,users u where e.userid=u.user_id and e.examid='"+paperid+"'";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	}
	/**
	 * 考生每次登录都会在E_LOGMONITOR中更新当前登录ip（多变的），只有在点击参加考试并进入考试页面时才在users表中记录答题ip（恒定的），
	 * 通过比较这两个ip判断是否有违规行为。
	 * @param ip
	 * @param userid
	 * @param paperid
	 * @return
	 */
	public String checkIp(final String ip,final Long userid,final String paperid){
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				//String sql = "select u.putong from users u where u.user_id in (select e.userid from e_exam_arrange e where e.examid="+paperid+")";
				String sql = "select u.putong from users u where u.user_id="+userid+"";
				Query query = session.createSQLQuery(sql);
				@SuppressWarnings("unchecked")
				List<String> list = query.list();
				if(list.size()>0){
					String oip = list.get(0);
					List<String> ipList = getIpList(paperid);
					if("".equals(oip)||oip==null){//users表中putong（存ip为空，表示第一次登陆
						if(ipList.contains(ip)){//查看此ip有没有被使用过，有 说明一台电脑登陆了多个账号
							return "flase2";
						}else{
							String s = "update users u set u.putong='"+ip+"' where u.user_id="+userid;
							Query query2 = session.createSQLQuery(s);
							query2.executeUpdate();
							return "success";
						}
					}else if(oip.equals(ip)){//同一台机器登陆，允许考试
						return "success";
					}else if(!oip.equals(ip)){//一个账号在不同电脑登陆，禁止考试
						return "false1";
					}else{
						return "false3";
					}
				}else{//用户不存在，
					return "false";
				}
			}
		});
	}
	
	public boolean checkSubmit(final String userid,final String sjid){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@SuppressWarnings("unchecked")
			public Object execute(Session session) throws Throwable {
				//EPapers ePapers = (EPapers) session.get(EPapers.class, Long.valueOf(sjid));
				List<EAnswerpaper> list = session.createCriteria(EAnswerpaper.class)
						.add(Restrictions.eq("epapers.sjId", Long.valueOf(sjid)))
						.add(Restrictions.eq("userStar", userid)).list();
				if (list.size()>0) {
					return true;
				}else{
					return false;
				}
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Object> checkResult(final Long userid,final String sjid){
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				EPapers ePapers = (EPapers) session.get(EPapers.class, Long.valueOf(sjid));
				EAnswerpaper eAnswerpaper = (EAnswerpaper) session.createCriteria(EAnswerpaper.class)
						.add(Restrictions.eq("userStar", String.valueOf(userid)))
						.uniqueResult();
				List<Object> list = new ArrayList<Object>();
				list.add(ePapers);
				list.add(eAnswerpaper);
				return list;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public Object[] getFs( final long i ,final String sjid) {
		return (Object[]) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
		String sql = "select count(*) as shumu, sum(q.sj_stfs) from e_Paperquestions_Tmp q where q.sj_id = '"+sjid+"' and q.st_lxid='"+i+"'";
				Query query = session.createSQLQuery(sql);
				List<Object[]> list = query.list();
				Object[] o = list.get(0);				
				return o;
			}
		});
	}
	
}
