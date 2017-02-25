package com.wondersgroup.falcon.paper.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.wondersgroup.falcon.abstracts.HibernateDAO;
import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.kaoshi.bo.Tdjobexamdot;

/**
 * 
 * <p>
 * Title:[�����]
 * </p>
 * <p>
 * Description: [�๦������]
 * </p>
 * 
 * Created by [Kevin Liang] [2009-7-8] Midified by [�޸���] [�޸�ʱ��]
 * 
 */

public class EPapersDAO  {
	private static final Log log = LogFactory.getLog(EPapersDAO.class);

	/**
	 * 
	 * <p>
	 * Description:[���ȡ�ÿ�����Ŀ]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-8] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param questionNum
	 * @param importanceLevel
	 * @param questionType
	 * @return
	 */
	public void  saveOrUpdate(final EPapers papaer)
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		 session.saveOrUpdate(papaer);
		return null;
	}
});
}
	
	public List getPaperQuestionsRandom(final int questionNum,final int importanceLevel,
			final int questionType,final String serviceType,final String bxType,final String zycd,final String ndxs)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List list = null;
				try {
					StringBuffer sql = new StringBuffer();
					sql.append("select ");
					sql.append("e.ST_ID as {tb.stId},");
					sql.append("e.ST_TG as {tb.stTg},");
					sql.append("e.ST_XX as {tb.stXx},");
					sql.append("e.ST_DA as {tb.stDa},");
					sql.append("e.ST_DASM as {tb.stDasm},");
					sql.append("e.ST_FJLJ as {tb.stFjlj},");
					sql.append("e.ST_KSZY as {tb.stKszy},");
					sql.append("e.ST_YWLXID as {tb.ebusinesstype},");
					sql.append("e.ST_ZYXID as {tb.eimportance},");
					sql.append("e.ST_LXID as {tb.equestiontype},");
					sql.append("e.ST_LRSJ as {tb.stLrsj},");
					sql.append("e.ST_MTRY_ID as {tb.stMtryId},");
					sql.append("e.ST_XGSJ as {tb.stXgsj},");
					sql.append("e.ST_CC as {tb.stCc},");
					sql.append("e.ST_WH as {tb.stWh},");
					sql.append("e.ST_JYXGCS as {tb.stJyxgcs},");
					sql.append("e.ST_SCBZ as {tb.stScbz}, ");
					sql.append("e.ST_NODE_ID as {tb.stNodeId} ,");
					sql.append("e.ST_NODE_NAME as {tb.stNodeName} , ");
					sql.append("e.ST_MODEFY as {tb.stModefy} ,");
					sql.append("e.ST_CHECK as {tb.stCheck} ,");// 审核
					sql.append("e.ST_SYRY_ID as {tb.stSyryId} ,");// 审核
					sql.append("e.ST_ADVICE as {tb.stAdvice}, ");// 审核
					sql.append("e.ST_SYSJ as {tb.stSysj}, ");// 审核
					sql.append("e.BX_TYPE as {tb.bxType} ");
					sql.append("from E_QUESTIONS e");
					sql.append(" where e.ST_KSZY=1 and e.ST_SCBZ=0 and e.ST_CHECK=1");
					sql.append(" and e.ST_ZYXID = " + importanceLevel);
					sql.append(" and e.ST_LXID = " + questionType);
//					if (!"".equals(serviceType)) {
//						sql.append(" and e.ST_YWLXID = " + serviceType);
//					}
//					if (!"".equals(bxType)) {
//						sql.append(" and e.ST_WH = " + bxType);
//					}
//					if(!"".equals(zycd)){
//						sql.append(" and e.BX_TYPE = " + zycd);
//					}
//					if(!"".equals(ndxs)){
//						sql.append(" and e.ST_ZYXID = " + ndxs);
//					}
					sql.append(" order by dbms_random.random");
					System.out.println("-------------->" + sql.toString());
					Query query = session.createSQLQuery(sql.toString()).addEntity("tb",
							EQuestions.class);
					query.setFirstResult(0);
					query.setMaxResults(questionNum);
					list = query.list();
					return list;

				} catch (RuntimeException re) {
					log.error("get failed", re);
					re.printStackTrace();
					throw re;
				} 

			}
		});


	}
	/**
	 * 根据坚定要素进行出题
	 * @param questionNum
	 * @param importanceLevel
	 * @param questionType
	 * @param lis
	 * @param serviceType
	 * @param bxType
	 * @return
	 */
	
	public  List getPaperQuestionsRandomByJd(final int questionNum, final String importanceLevel,
			final int questionType, final String serviceType, final String bxType,final String zycd,final String ndxs,final List<Long> quid,final String grade,final String jddid)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List list = null;
				try {
					StringBuffer sql = new StringBuffer();
					sql.append("select ");
					sql.append("e.ST_ID as {tb.stId},");
					sql.append("e.ST_TG as {tb.stTg},");
					sql.append("e.ST_XX as {tb.stXx},");
					sql.append("e.ST_DA as {tb.stDa},");
					sql.append("e.ST_DASM as {tb.stDasm},");
					sql.append("e.ST_FJLJ as {tb.stFjlj},");
					sql.append("e.ST_KSZY as {tb.stKszy},");
					sql.append("e.ST_YWLXID as {tb.ebusinesstype},");
					sql.append("e.ST_ZYXID as {tb.eimportance},");
					sql.append("e.ST_LXID as {tb.equestiontype},");
					sql.append("e.ST_LRSJ as {tb.stLrsj},");
					sql.append("e.ST_MTRY_ID as {tb.stMtryId},");
					sql.append("e.ST_XGSJ as {tb.stXgsj},");
					sql.append("e.ST_CC as {tb.stCc},");
					sql.append("e.ST_WH as {tb.stWh},");
					sql.append("e.ST_JYXGCS as {tb.stJyxgcs},");
					sql.append("e.ST_SCBZ as {tb.stScbz}, ");
					sql.append("e.ST_NODE_ID as {tb.stNodeId} ,");
					sql.append("e.ST_NODE_NAME as {tb.stNodeName} , ");
					sql.append("e.ST_MODEFY as {tb.stModefy} ,");
					sql.append("e.ST_CHECK as {tb.stCheck} ,");// 审核
					sql.append("e.ST_SYRY_ID as {tb.stSyryId} ,");// 审核
					sql.append("e.ST_ADVICE as {tb.stAdvice}, ");// 审核
					sql.append("e.ST_SYSJ as {tb.stSysj}, ");// 审核
					sql.append("e.BX_TYPE as {tb.bxType} ");
					if("".equals(jddid)){
						sql.append("from E_QUESTIONS e ,TJOBSUBJECT c where  e.st_node_name =c.id  ");
					}else{
						sql.append("from E_QUESTIONS e , TMDOT t  ,Tdjobexamdot j,TJOBSUBJECT c where  e.st_node_name =c.id and e.st_id=t.st_id and t.jdysid=j.jdysid");
					}
					sql.append(" and  e.ST_KSZY=1 and e.ST_SCBZ=0 and e.ST_CHECK=1");
					if (!"".equals(importanceLevel)) {
						sql.append(" and e.bx_type = '" + importanceLevel+"'");
					}
//					if(!"".equals(ndxs)){
//						if("1".equals(ndxs)){
//							sql.append(" and e.st_zyxid in (1,2)");
//						}else if("2".equals(ndxs)){
//							sql.append(" and e.st_zyxid in (1,2,3)");
//						} else if("3".equals(ndxs)){
//							sql.append(" and e.st_zyxid in (2,3,4)");
//						} else if("4".equals(ndxs)){
//							sql.append(" and e.st_zyxid in (3,4,5)");
//						} else if("5".equals(ndxs)){
//							sql.append(" and e.st_zyxid in (4,5)");
//						}
//						
//					}
					sql.append(" and e.ST_LXID = " + questionType);
					if (!"".equals(serviceType)) {
						sql.append(" and c.id_job = '" + serviceType+"'");
					}
					if (!"".equals(jddid)) {
						sql.append(" and j.jdysid = '" + jddid+"'");
					}
					if(!"".equals(grade)) {
						sql.append(" and c.rankname = '" + grade+"'");
					}
					if(quid.size()>0){
						sql.append(" and e.ST_ID not in( ");
						for(int i=0;i<quid.size();i++){
							sql.append(quid.get(i)+",");
						}
						sql.append(" 1) ");
					}
					sql.append(" order by dbms_random.random");
					System.out.println("-------------->" + sql.toString());
					Query query = session.createSQLQuery(sql.toString()).addEntity("tb",EQuestions.class);
					query.setFirstResult(0);
					query.setMaxResults(questionNum);
					list = query.list();
					return list;

				} catch (RuntimeException re) {
					log.error("get failed", re);
					re.printStackTrace();
					throw re;
				} 

			}
		});


	}
	
	public List getPaperQuestionsRandom2(final int questionNum,final int importanceLevel,
			final int questionType,final  List<Long> lis,final  String serviceType,final  String bxType)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List list = null;
				try {
					StringBuffer sql = new StringBuffer();
					sql.append("select ");
					sql.append("e.ST_ID as {tb.stId},");
					sql.append("e.ST_TG as {tb.stTg},");
					sql.append("e.ST_XX as {tb.stXx},");
					sql.append("e.ST_DA as {tb.stDa},");
					sql.append("e.ST_DASM as {tb.stDasm},");
					sql.append("e.ST_FJLJ as {tb.stFjlj},");
					sql.append("e.ST_KSZY as {tb.stKszy},");
					sql.append("e.ST_YWLXID as {tb.ebusinesstype},");
					sql.append("e.ST_ZYXID as {tb.eimportance},");
					sql.append("e.ST_LXID as {tb.equestiontype},");
					sql.append("e.ST_LRSJ as {tb.stLrsj},");
					sql.append("e.ST_MTRY_ID as {tb.stMtryId},");
					sql.append("e.ST_XGSJ as {tb.stXgsj},");
					sql.append("e.ST_CC as {tb.stCc},");
					sql.append("e.ST_WH as {tb.stWh},");
					sql.append("e.ST_JYXGCS as {tb.stJyxgcs},");
					sql.append("e.ST_SCBZ as {tb.stScbz} ,");
					sql.append("e.ST_NODE_ID as {tb.stNodeId} ,");
					sql.append("e.ST_NODE_NAME as {tb.stNodeName} ,");
					sql.append("e.ST_MODEFY as {tb.stModefy} ,");
					sql.append("e.ST_CHECK as {tb.stCheck} ,");// 审核
					sql.append("e.ST_SYRY_ID as {tb.stSyryId}, ");// 审核
					sql.append("e.ST_ADVICE as {tb.stAdvice}, ");// 审核
					sql.append("e.ST_SYSJ as {tb.stSysj}, ");// 审核
					sql.append("e.BX_TYPE as {tb.bxType} ");
					sql.append("from E_QUESTIONS e ");
					sql.append("where e.ST_KSZY=1 ");
					sql.append("and e.ST_SCBZ=0 ");
					sql.append("and e.ST_CHECK=1 ");
					sql.append(" and e.ST_ZYXID = " + importanceLevel);
					sql.append(" and e.ST_LXID = " + questionType);
					if (!"".equals(serviceType)) {
						sql.append(" and e.ST_YWLXID = " + serviceType);
					}
					if (!"".equals(bxType)) {
						sql.append(" and e.BX_TYPE = " + bxType);
					}
					if (lis != null && lis.size() != 0) {
						sql.append(" and e.ST_ID not in ( ");
						for (int i = 0; i < lis.size(); i++) {
							if (i == lis.size() - 1) {
								sql.append(lis.get(i).intValue());
							} else {
								sql.append(lis.get(i).intValue() + ",");
							}
						}
						sql.append(") ");
					}
					sql.append(" order by dbms_random.random");
					Query query = session.createSQLQuery(sql.toString()).addEntity("tb",
							EQuestions.class);
					query.setFirstResult(0);
					query.setMaxResults(questionNum);
					list = query.list();
					return list;

				} catch (RuntimeException re) {
					log.error("get failed", re);
					throw re;
				}

			}
		});


	}
	public void save(final EPapers transientInstance)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("saving EPapers instance");
				try {
					session.save(transientInstance);
					log.debug("save successful");
				} catch (RuntimeException re) {
					log.error("save failed", re);
					throw re;
				} 
				return null;
			}
		});
		}

 
	public void delete(final EPapers persistentInstance)
			throws Exception {
		        HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("deleting EPapers instance");
				try {
					session.delete(persistentInstance);
					log.debug("delete successful");
				} catch (RuntimeException re) {
					log.error("delete failed", re);
					throw re;
				} 
				return null;
			}
		});
		}
	 
	public EPapers findById(final Long id)throws Exception{
		return (EPapers) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("getting EPapers instance with id: " + id);
				try {
					EPapers instance = (EPapers) session.get(
							"com.wondersgroup.falcon.paper.model.EPapers", id);
					return instance;
				} catch (RuntimeException re) {
					log.error("get failed", re);
					throw re;
				}	 

			}
		});


	}
	
	public List findByExample(final EPapers instance)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding EPapers instance by example");
				try {
					List results = session.createCriteria(
							"com.wondersgroup.falcon.paper.model.EPapers").add(
							Example.create(instance)).list();
					log.debug("find by example successful, result size: "
							+ results.size());
					return results;
				} catch (RuntimeException re) {
					log.error("find by example failed", re);
					throw re;
				} 

			}
		});


	}
	
	public List findByProperty(final String propertyName,final  Object value)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding EPapers instance with property: " + propertyName
						+ ", value: " + value);
				try {
					String queryString = "from EPapers as model where model."
							+ propertyName + "= ?";
					Query queryObject = session.createQuery(
							queryString);
					queryObject.setParameter(0, value);
					return queryObject.list();
				} catch (RuntimeException re) {
					log.error("find by property name failed", re);
					throw re;
				} 

			}
		});


	}
	 
	public List findAll()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				log.debug("finding all EPapers instances");
				try {
					String queryString = "from EPapers";
					Query queryObject = session.createQuery(
							queryString);
					return queryObject.list();
				} catch (RuntimeException re) {
					log.error("find all failed", re);
					throw re;
				}

			}
		});


	}
	/**
	 * 
	 * <p>
	 * Description:[ȡ�÷������ļ�¼����]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param sjMc
	 * @param sjZt
	 * @param sjKksjbegin
	 * @param sjKksjend
	 * @param sjZjsjbegin
	 * @param sjZjsjend
	 * @return
	 */
	
	public int getPapersTotalCount(final String sjMc,final  long sjZt, final Date sjKksjbegin,
			final Date sjKksjend, final Date sjZjsjbegin, final Date sjZjsjend)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.clear();

				Criteria criterion = session.createCriteria(EPapers.class);
				// 删除的不显示
				criterion.add(Restrictions.ne("sjZt", new Long(3)));
				// ��ѯ����
				if (!sjMc.equals(""))
					criterion.add(Property.forName("sjMc").like(sjMc.trim(),
							MatchMode.ANYWHERE));
				// criterion.add(Expression.disjunction().add(Property.forName("sjMc").
				// like(sjMc.trim(),MatchMode.ANYWHERE)));
				// Property.Restrictions.like("sjMc", sjMc,MatchMode.ANYWHERE));
				if (sjZt != -1)
					criterion.add(Restrictions.eq("sjZt", new Long(sjZt)));
				// 未审阅和审阅不通过的不显示
				else {
					criterion.add(Restrictions.ne("sjZt", new Long(4)));
					criterion.add(Restrictions.ne("sjZt", new Long(5)));
				}

				if (sjKksjbegin != null && sjKksjend != null)
					criterion.add(Restrictions
							.between("sjKksj", sjKksjbegin, sjKksjend));
				else if (sjKksjbegin != null && sjKksjend == null)
					criterion.add(Restrictions.le("sjKksj", sjKksjbegin));
				else if (sjKksjbegin == null && sjKksjend != null)
					criterion.add(Restrictions.ge("sjKksj", sjKksjend));

				if (sjZjsjbegin != null && sjZjsjend != null)
					criterion.add(Restrictions
							.between("sjZjsj", sjZjsjbegin, sjZjsjend));
				else if (sjZjsjbegin != null && sjZjsjend == null)
					criterion.add(Restrictions.le("sjZjsj", sjZjsjbegin));
				else if (sjZjsjbegin == null && sjZjsjend != null)
					criterion.add(Restrictions.ge("sjZjsj", sjZjsjend));

				criterion.setProjection(Projections.projectionList().add(
						Projections.rowCount()));
				return (Integer.valueOf(criterion.uniqueResult().toString())).intValue();

			}
		});


	}
	/**
	 * 
	 * <p>
	 * Description:[ȡ�÷�������һҳ���]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param sjMc
	 * @param sjZt
	 * @param sjKksjbegin
	 * @param sjKksjend
	 * @param sjZjsjbegin
	 * @param sjZjsjend
	 * @param currentPage
	 * @param pageSize
	 * @param orderby
	 * @return
	 */
	
	public List findPapers( final String sjMc,final  long sjZt,final  Date sjKksjbegin,
			final Date sjKksjend,final  Date sjZjsjbegin,final  Date sjZjsjend, final int currentPage,
			final int pageSize, final String orderby)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				 

				 
				Criteria criterion = session.createCriteria(EPapers.class);
				// 删除的不显示
				criterion.add(Restrictions.ne("sjZt", new Long(3)));

				// String[] menber={"3","4","5"};

				// criterion.add(Restrictions.not(Restrictions.in("sjZt", menber)));
				// ��ѯ����
				if (!"".equals(sjMc))
					criterion.add(Property.forName("sjMc").like(sjMc.trim(),
							MatchMode.ANYWHERE));
				// criterion.add(Expression.disjunction().add(Property.forName("sjMc").
				// like(sjMc.trim(),MatchMode.ANYWHERE)));
				// Property.Restrictions.like("sjMc", sjMc,MatchMode.ANYWHERE));
				if (sjZt != -1)
					criterion.add(Restrictions.eq("sjZt", new Long(sjZt)));
				// 未审阅和审阅不通过的不显示
				else {
					criterion.add(Restrictions.ne("sjZt", new Long(4)));
					criterion.add(Restrictions.ne("sjZt", new Long(5)));
				}

				if (sjKksjbegin != null && sjKksjend != null)
					criterion.add(Restrictions
							.between("sjKksj", sjKksjbegin, sjKksjend));
				else if (sjKksjbegin != null && sjKksjend == null)
					criterion.add(Restrictions.le("sjKksj", sjKksjbegin));
				else if (sjKksjbegin == null && sjKksjend != null)
					criterion.add(Restrictions.ge("sjKksj", sjKksjend));

				if (sjZjsjbegin != null && sjZjsjend != null)
					criterion.add(Restrictions
							.between("sjZjsj", sjZjsjbegin, sjZjsjend));
				else if (sjZjsjbegin != null && sjZjsjend == null)
					criterion.add(Restrictions.le("sjZjsj", sjZjsjbegin));
				else if (sjZjsjbegin == null && sjZjsjend != null)
					criterion.add(Restrictions.ge("sjZjsj", sjZjsjend));
				// �������
				criterion.addOrder(Order.desc("sjZjsj"));
				int currentPage1=currentPage;
				int k = --currentPage1 * pageSize;

				criterion.setFirstResult(k);
				criterion.setMaxResults(pageSize);
				return criterion.list();
			}
		});


	}
	
	public int getQuestionCountByType(final Long type, final String serviceType,
			final String bxType,final String zycd,final String ndxs)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EQuestions.class);
				criteria.createAlias("equestiontype", "equestiontype");
				criteria.add(Restrictions.eq("equestiontype.priority", type));
				criteria.add(Restrictions.eq("stScbz", new Long(0).longValue()));// 删除的不算
				criteria.add(Restrictions.eq("stCheck", new Long(1).longValue()));//未审核，
				// 审核不通过的不算
//				if (!"".equals(serviceType)) {
//					criteria.add(Restrictions.eq("ebusinesstype.id", new Long(serviceType)
//							.longValue()));// 工种信息
//				}
//				if (!"".equals(bxType)) {
//					criteria.add(Restrictions.eq("stWh", bxType));// 等级
//				}
//				if(!"".equals(zycd)){
//					criteria.add(Restrictions.eq("bxType", zycd));// 重要程度
//				}
//				if(!"".equals(ndxs)){
//					criteria.add(Restrictions.eq("eimportance.id", Long.valueOf(ndxs)));// 难度系数
//				}
				return (Integer.valueOf(criteria.setProjection(Projections.rowCount())
						.uniqueResult().toString())).intValue();	 

			}
		});


	}
	/**
	 * 获得坚定要素
	 */
	 
	@SuppressWarnings("unchecked")
	public List<Tdjobexamdot> queryTdjobexamdot(final String ccz137)throws Exception{
		return ( List<Tdjobexamdot>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String queryString = "from Tdjobexamdot as model where model.ccz137=? order by model.rank1pent,model.rank2pent,model.rank3pent,model.rank4pent,model.rank5pent desc";
				Query queryObject = session.createQuery(queryString);
				queryObject.setParameter(0, ccz137);
				return queryObject.list();

			}
		});
	}
	public String checkUser(){
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				String color = AcegiUtil.getEUser().getColor();
				String sql = "select count(*) from E_user_team a where a.team_id="+color;
				Query query = session.createSQLQuery(sql);
				BigDecimal count = (BigDecimal) query.uniqueResult();
				return String.valueOf(count);
			}
		});
	}
}