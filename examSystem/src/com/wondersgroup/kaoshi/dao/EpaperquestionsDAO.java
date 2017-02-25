package com.wondersgroup.kaoshi.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPaperquestions_tmp;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.model.EBusinesstype;
import com.wondersgroup.falcon.question.model.EImportance;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class EpaperquestionsDAO extends HibernateDaoSupport {

	/**
	 * 
	 * <p>
	 * Description:[�ҵ����е� ����]
	 * </p>
	 * 
	 * Created by [www] [Aug 7, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param pageInfo
	 * @return
	 */
	public PageReturn findAllByPage(final PageTool pageInfo,
			final EQuestions equestion) throws Exception {
		return (PageReturn) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						PageReturn pr = new PageReturn();
						Criteria criteria = session
								.createCriteria(EPaperquestions.class);
						criteria.createCriteria("equestions", "equestions");
						if (equestion.getStTg() != null
								&& !equestion.equals("")) {
							criteria.add(Restrictions.like("equestions.stTg",
									equestion.getStTg(), MatchMode.ANYWHERE));
						}
						if (equestion.getEbusinesstype().getPriority() != -1) {
							criteria.add(Restrictions.eq(
									"equestions.ebusinesstype.priority",
									equestion.getEbusinesstype().getPriority()));
						}
						if (equestion.getEquestiontype().getPriority() != -1) {
							criteria.add(Restrictions.eq(
									"equestions.equestiontype.priority",
									equestion.getEquestiontype().getPriority()));
						}
						criteria.addOrder(Order.asc("sjStpx"));
						pr.setTotal(criteria.list().size());

						criteria.setFirstResult(pageInfo.getStart());
						criteria.setMaxResults(pageInfo.getSize());
						pr.setReturnList(criteria.list());

						return pr;

					}
				});
	}

	/**
	 * 
	 * <p>
	 * Description:[����Ծ�4��������]
	 * </p>
	 * 
	 * Created by [www] [Aug 7, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param pageInfo
	 * @param sjid
	 * @return
	 */
	public PageReturn findAllByPage(final PageTool pageInfo, final String sjid,
			final EQuestions equestion) throws Exception {
		return (PageReturn) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						PageReturn pr = new PageReturn();
						Criteria criteria = session
								.createCriteria(EPaperquestions.class);
						criteria.add(Restrictions.eq("epapers.sjId", new Long(
								sjid)));
						criteria.createAlias("equestions", "equestions");
						if (equestion.getStTg() != null
								&& !equestion.equals("")) {
							criteria.add(Restrictions.like("equestions.stTg",
									equestion.getStTg(), MatchMode.ANYWHERE));
						}
						if (equestion.getStLrsj() != null
								&& equestion.getStlrsjend() != null) {
							criteria.add(Restrictions.between(
									"equestions.stLrsj", equestion.getStLrsj(),
									equestion.getStlrsjend()));
						} else if (equestion.getStLrsj() != null
								&& equestion.getStlrsjend() == null) {
							criteria.add(Restrictions.ge("equestions.stLrsj",
									equestion.getStLrsj()));
						} else if (equestion.getStLrsj() == null
								&& equestion.getStlrsjend() != null) {
							criteria.add(Restrictions.le("equestions.stLrsj",
									equestion.getStlrsjend()));
						}

						criteria.addOrder(Order.asc("sjStpx"));
						pr.setTotal(criteria.list().size());

						criteria.setFirstResult(pageInfo.getStart());
						criteria.setMaxResults(pageInfo.getSize());
						pr.setReturnList(criteria.list());

						return pr;

					}
				});
	}

	/**
	 * 
	 * <p>
	 * Description:ͨ���Ծ��ҵ����е�����,���������4����4����
	 * </p>
	 * 
	 * Created by [www] [Aug 11, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param sjid
	 * @return
	 */
	public List findAllById(final String sjid) throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session
						.createCriteria(EPaperquestions.class)
						.add(Restrictions.eq("epapers.sjId", new Long(sjid)))
						.createCriteria("equestions", "equestions")
						.addOrder(Order.asc("equestions.equestiontype"));
				return criteria.list();

			}
		});
	}

	/**
	 * 
	 * <p>
	 * Description:���idɾ��һ����Ϣ
	 * </p>
	 * 
	 * Created by [www] [Aug 12, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param id
	 */
	public void deleteById(final String id) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EPaperquestions epq = (EPaperquestions) session.load(
						EPaperquestions.class, new Long(id));
				session.delete(epq);
				return null;
			}
		});
	}

	// 删除整份试卷
	public void delete(final String id) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List lst = null;
				lst = findAllById(id);

				if (lst != null && !lst.equals("")) {
					// System.out.println(id+"============");
					// System.out.println(lst.size()+"-----------");
					for (int i = 0; i < lst.size(); i++) {
						EPaperquestions ePa = (EPaperquestions) lst.get(0);
						// System.out.println(ePa.getEpapers().getSjId()+"============");
						session.delete(ePa);
					}
				}
				EPapers epq = (EPapers) session.load(EPapers.class,
						new Long(id));
				session.delete(epq);
				return null;
			}
		});
	}

	// 删除整份试卷 逻辑删除，将状态改成3
	public void deleteAll(final String id) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EPapers epq = (EPapers) session.load(EPapers.class,
						new Long(id));
				epq.setSjZt(new Long(3));
				session.saveOrUpdate(epq);
				return null;
			}
		});
	}
	
	public boolean checkPaperRelate(final String sjid){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				String sql = "select * from hz95 where sj_id="+sjid;
				Query query = session.createSQLQuery(sql);
				if (query.list().size()>0) {
					return true;
				}else{
					return false;
				}
			}
		});
	}

	/**
	 * 
	 * <p>
	 * Description:�Ծ��������
	 * </p>
	 * 
	 * Created by [www] [Aug 13, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param questionsId
	 * @param sjid
	 */
	public void add(final String[] questionsId, final String sjid,
			final Map<String, Double> map) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				if (questionsId != null && questionsId.length > 0) {
					// �ȼ����Ծ�
					EPapers epapers = (EPapers) session.load(EPapers.class,
							new Long(sjid));
					// �ҳ���������ֵ

					Long max = (Long) session
							.createQuery(
									"select max(e.sjStpx) from EPaperquestions e where e.epapers.sjId=:sjid")
							.setLong("sjid", new Long(sjid)).uniqueResult();
					if (max == null) {
						max = new Long(0);
					}
					for (int i = 0; i < questionsId.length; i++) {
						max++;
						EQuestions equestions = (EQuestions) session.load(
								EQuestions.class, new Long(questionsId[i]));
						EPaperquestions epaperquestions = new EPaperquestions();
						epaperquestions.setEpapers(epapers);
						epaperquestions.setEquestions(equestions);
						epaperquestions.setSjStpx(max.longValue());
						epaperquestions.setSjStfs(map.get(questionsId[i]
								+ "fenshu"));
						session.save(epaperquestions);

					}
				}
				return null;
			}
		});
	}

	// 代替上面的方法 add 改成分数统一
	public void addQs(final String[] questionsId, final String sjid,
			final double fenzhi) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				if (questionsId != null && questionsId.length > 0) {
					// �ȼ����Ծ�
					EPapers epapers = (EPapers) session.load(EPapers.class,
							new Long(sjid));
					// �ҳ���������ֵ

					Long max = (Long) session
							.createQuery(
									"select max(e.sjStpx) from EPaperquestions e where e.epapers.sjId=:sjid")
							.setLong("sjid", new Long(sjid)).uniqueResult();
					if (max == null) {
						max = new Long(0);
					}
					for (int i = 0; i < questionsId.length; i++) {
						max++;
						EQuestions equestions = (EQuestions) session.load(
								EQuestions.class, new Long(questionsId[i]));
						EPaperquestions epaperquestions = new EPaperquestions();
						epaperquestions.setEpapers(epapers);
						epaperquestions.setEquestions(equestions);
						epaperquestions.setSjStpx(max.longValue());
						epaperquestions.setSjStfs(fenzhi);
						session.save(epaperquestions);

					}
				}
				return null;
			}
		});
	}

	/**
	 * ����Ծ���������� �ҵ�����
	 */
	public List findQuestionByIdType(final String sjid, final String typeId)
			throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return session
						.createCriteria(EPaperquestions.class)
						.add(Restrictions.eq("epapers.sjId", new Long(sjid)))
						.createCriteria("equestions", "equestions")
						.add(Restrictions.eq("equestions.equestiontype.id",
								new Long(typeId))).list();

			}
		});
	}

	public void addEquestionPaper(final String paperid, final String newpaperId)
			throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EPapers epapers = (EPapers) session.load(EPapers.class,
						new Long(newpaperId));
				// ����Ծ��ҵ�����
				List lis = new EpaperquestionsDAO().findAllById(paperid);
				for (int i = 0; i < lis.size(); i++) {
					EPaperquestions epaperquestions = (EPaperquestions) lis
							.get(i);
					EPaperquestions newepaperquestions = new EPaperquestions();
					newepaperquestions.setEpapers(epapers);
					newepaperquestions.setEquestions(epaperquestions
							.getEquestions());
					newepaperquestions.setSjStfs(epaperquestions.getSjStfs());
					newepaperquestions.setSjStpx(epaperquestions.getSjStpx());
					session.save(newepaperquestions);
				}
				return null;
			}
		});
	}

	/**
	 * 
	 * <p>
	 * Description:�ҵ����е�ҵ������
	 * </p>
	 * 
	 * Created by [www] [Aug 20, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @return
	 */
	public List findallbuType() throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EBusinesstype.class);
				return criteria.list();

			}
		});
	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:�ҵ����е���Ҫ��
	 * </p>
	 * 
	 * Created by [www] [Nov 18, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @return
	 */
	public List findallEimportances() throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EImportance.class);
				criteria.addOrder(Order.asc("id"));
				return criteria.list();

			}
		});
	}

	public double findpaperquestionfenshu(final String paperid)
			throws Exception {
		return (Double) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						EPapers epapers = (EPapers) session.load(EPapers.class,
								new Long(paperid));
						Iterator<EPaperquestions> it = epapers
								.getEpaperquestionses().iterator();
						double dou = 0;
						while (it.hasNext()) {
							EPaperquestions epaperquestions = it.next();
							dou += epaperquestions.getSjStfs();
						}
						return dou;

					}
				});
	}

	// 找题目被出过几次

	public int getAllNunber(final String questionId) throws Exception {
		return (Integer) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						int i = 0;
						List lst1 = null;
						String queryString = " from EPapers e  ";
						Query queryObject = session.createQuery(queryString);
						lst1 = queryObject.list();
						if (lst1 != null && lst1.size() > 0) {
							for (int j = 0; j < lst1.size(); j++) {
								List lst2 = null;
								EPapers ep = (EPapers) lst1.get(j);
								String sql = " from EPaperquestions e where e.equestions.stId='"
										+ questionId
										+ "' and e.epapers.sjId='"
										+ ep.getSjId() + "' ";
								// System.out.println(sql);
								Query query = session.createQuery(sql);
								lst2 = query.list();
								if (lst2 != null && lst2.size() > 0) {
									i++;
								}
							}

						}

						return i;

					}
				});
	}

}
