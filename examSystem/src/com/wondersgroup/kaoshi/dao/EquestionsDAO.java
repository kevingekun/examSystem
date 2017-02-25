package com.wondersgroup.kaoshi.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.model.archives.CaseNode;
import com.wondersgroup.falcon.model.archives.FaqNode;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.model.archives.ServiceNode;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.model.EAdvice;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestionsDTO;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EUser;

public class EquestionsDAO extends HibernateDaoSupport {

	public List getKindEntity(PageTool pageInfo) {

		final int size1 = pageInfo.getSize();
		final int startRow = pageInfo.getStart();

		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public List doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery("from EQuestions");
				query.setFirstResult(startRow);
				query.setMaxResults(size1);
				return query.list();
			}
		});
	}
	public  List getList(final PageTool pageInfo)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				final int size1 = pageInfo.getSize();
				final int startRow = (pageInfo.getCur() - 1) * pageInfo.getSize();
				Query query = session.createQuery(
						"select count(*) from EQuestions");
				// query.setFirstResult(startRow);
				query.setMaxResults(size1);
				Object o = query.uniqueResult();
				return query.list(); 

			}
		});
	}
	public  int getRows()throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return getHibernateTemplate().find("from EQuestions").size(); 
			}
		});
	}


	@SuppressWarnings("unchecked")
	public  PageReturn findQuetionsByType(final PageTool pageInfo,final String typeId,
			final String stMc,final  Long busType,final Date inputtime,final Long eimportance,
			final Date sjbegin,final  Date sjend,final String xingzhi,final String gzid,final String gzdj,final long difficulty)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				// Query query=this.getSession().createQuery(
				// " from EQuestions e where e.equestiontype.id=:id");
				// query.setLong("id", new Long(typeId));
				StringBuffer buffer = new StringBuffer();
				List<EQuestionsDTO> list = null;
				if (pageInfo.getCur() == 0) {
					pageInfo.setCur(1);
				}
				try {
					buffer.append("select qs.stId,js.jobname,qs.stTg,qs.bxType,qs.stLrsj,ip.name,qt.name, " +
							"js.rankname,qs.stSyryId,qs.stFjlj,qs.stCc,qs.stCheck,qs.stModefy from " +
							"EQuestions as qs,Tjobsubject as js,EImportance as ip,EQuestiontype as qt where " +
							"qs.stNodeName=to_char(js.id) and qs.eimportance.id=ip.id and qs.equestiontype.id=qt.id and " +
							"qs.stScbz=0 and qs.equestiontype.priority="+typeId);
					/*if(!subject.equals("")){
						buffer.append(" and qs.stTg="+"'"+subject+"'");
					}
					if(questiontype!=0){
						buffer.append(" and qt.id="+questiontype);
					}*/
					if(difficulty!=-1){
						buffer.append(" and ip.id="+difficulty);
					}
					if(!"".equals(gzid)){
						buffer.append(" and js.id_job="+"'"+gzid+"'");
					}
					if(!"".equals(gzdj)){
						buffer.append(" and js.rankname="+"'"+gzdj+"'");
					}
					/*if(state!=3){
						buffer.append(" and qs.stCheck="+"'"+state+"'");
					}
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(recorddatebegin!=null && recorddateend!=null){
						buffer.append(" and qs.stLrsj between to_date('"+formatter.format(recorddatebegin)+"','yyyy-MM-dd HH24:mi:ss') and to_date('"+formatter.format(recorddateend)+"','yyyy-MM-dd HH24:mi:ss')");
					}else if(recorddatebegin==null && recorddateend!=null){
						buffer.append(" and qs.stLrsj<"+recorddateend);
					}else if(recorddatebegin!=null && recorddateend==null){
						buffer.append(" and qs.stLrsj>"+recorddatebegin);
					}
					buffer.append(" order by qs."+orderby+" desc");*/
					String querysString = buffer.toString();
					//System.out.println(querysString);
					list = new ArrayList<EQuestionsDTO>();
					Query query = session.createQuery(querysString);
					pageReturn.setTotal(query.list().size());
					query.setFirstResult((pageInfo.getCur() - 1) * pageInfo.getSize());
					query.setMaxResults(pageInfo.getSize());
					List<Object[]> list1 = query.list();
					
					Iterator<Object[]> it = list1.iterator();
					while(it.hasNext()){
						EQuestionsDTO eq = new EQuestionsDTO();
						Object[] object = it.next();
						eq.setId((Long) object[0]);
						eq.setProfession((String)object[1]);
						eq.setTg((String)object[2]);
						eq.setImportence((String)object[3]);
						eq.setLrsj((Date)object[4]);
						eq.setDifficulty((String)object[5]);
						eq.setQuestiontype((String)object[6]);
						eq.setGrade((String)object[7]);
						eq.setExpert((String)object[8]);
						eq.setRemark((String)object[9]);
						eq.setReference((String)object[10]);
						eq.setState((Long) object[11]);
						eq.setUsageCount((Long)object[12]);
						list.add(eq);
					}
					pageReturn.setReturnList(list);
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
				}
				return pageReturn;
				/*Criteria cre = this.getSession().createCriteria(EQuestions.class);
				cre.createAlias("equestiontype", "questionType");
				cre.createAlias("ebusinesstype", "busType");
				cre.createAlias("eimportance", "eimportance");
				if (xingzhi == null) {
					Long[] ll = new Long[2];
					ll[0] = new Long(1);
					ll[1] = new Long(2);
					cre.add(Restrictions.in("stKszy", ll));// 只显示考试题和阶段性试题
				} else
					cre.add(Restrictions.eq("stKszy", new Long(xingzhi))); // 只显示考试题或阶段性试题
				cre.add(Restrictions.eq("stCheck", new Long(1))); // 只审阅通过的
				cre.add(Restrictions.eq("stScbz", new Long(0))); // 不显示已删除的题目
				cre.add(Restrictions.eq("questionType.id", new Long(typeId)));
				if (stMc != null && !stMc.equals("")) {
					cre.add(Restrictions.like("stTg", stMc, MatchMode.ANYWHERE));
				}
				if (busType != null && busType.longValue() != -1) {
					cre.add(Restrictions.eq("busType.id", busType));
				}
				if (eimportance != null && eimportance.longValue() != -1) {
					cre.add(Restrictions.eq("eimportance.id", eimportance));
				}
				if (inputtime != null) {
					cre.add(Restrictions.eq("stLrsj", inputtime));
				}

				System.out.println(sjbegin);
				if (sjbegin != null && sjend != null) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(sjend);
					cal.add(Calendar.DAY_OF_MONTH, +1);

					cre.add(Restrictions.between("stLrsj", sjbegin, cal.getTime()));
				}

				pageReturn.setTotal(cre.list().size());

				cre.setFirstResult(pageInfo.getStart());
				cre.setMaxResults(pageInfo.getSize());

				pageReturn.setReturnList(cre.list());
				return pageReturn;*/	 
			}
		});
	}

	
	// 遍历找到题目中的政策法规号
	public  List findNode()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List lst = null;
				Query query = session.createQuery(
						" select e.stNodeId from EQuestions e  where e.stNodeName='P'");
				lst = query.list();
				return lst; 

			}
		});
	}
	public  List findSNode()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List lst = null;
				Query query = session.createQuery(
						" select e.stNodeId from EQuestions e where e.stNodeName='S' ");
				lst = query.list();
				return lst;	 
			}
		});
	}
	public  List findFNode()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List lst = null;
				Query query = session.createQuery(
						" select e.stNodeId from EQuestions e where e.stNodeName='f' ");
				lst = query.list();
				return lst; 
			}
		});
	}
	public  List findCNode()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List lst = null;
				Query query = session.createQuery(
						" select e.stNodeId from EQuestions e where e.stNodeName='c'");
				lst = query.list();
				return lst; 

			}
		});
	}

	// 找到修改过的政策法规
	public  PageReturn findPolicyNodeByNodeState(final PageTool pageInfo)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(PolicyNode.class);
				criteria.add(Restrictions.eq("attribute.modifystate", new Long(0)));
				Query query =  session.createQuery(
								"select p from PolicyNode p  where  p.attribute.modifystate!=0  ");
				PageReturn pr = new PageReturn();
				pr.setTotal(query.list().size());
				// System.out.println(query.list().size()+"数量-----------------------");
				query.setFirstResult(pageInfo.getStart());
				query.setMaxResults(pageInfo.getSize());
				pr.setReturnList(query.list());
				// System.out.println(query.list().size()+"-----------------------");
				return pr; 

			}
		});
	}
	public  PageReturn findServiceNodeByNodeState(final PageTool pageInfo)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				// List lst=null;
				//		
				// lst=this.findSNode();
				// //System.out.print(lst.size());
				// String NodeIds="";
				// if(lst!=null){
				// for(int i=0;i<lst.size();i++){
				// if(lst.get(i)!=null){
				// NodeIds=NodeIds+"'"+String.valueOf(lst.get(i))+"',";
				// }
				// }
				// NodeIds=NodeIds.substring(0,NodeIds.length()-1);
				//			
				// }
				// if(NodeIds.equals("")){
				//			
				// NodeIds=null;
				// }
				// System.out.print(NodeIds);
				Query query = session.createQuery(
								"select p from ServiceNode p  where  p.attribute.modifystate!=0  ");
				PageReturn pageReturn = new PageReturn();
				pageReturn.setTotal(query.list().size());

				query.setFirstResult(pageInfo.getStart());
				query.setMaxResults(pageInfo.getSize());
				pageReturn.setReturnList(query.list());
				// System.out.println(query.list().size()+"-----------------------");
				return pageReturn;	 

			}
		});
	}
	public  PageReturn findFaqNodeByNodeState(final PageTool pageInfo)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				// List lst=null;
				//		
				// lst=this.findFNode();
				// //System.out.print(lst.size());
				// String NodeIds="";
				// if(lst!=null){
				// for(int i=0;i<lst.size();i++){
				// if(lst.get(i)!=null){
				// NodeIds=NodeIds+"'"+String.valueOf(lst.get(i))+"',";
				// }
				// }
				// NodeIds=NodeIds.substring(0,NodeIds.length()-1);
				//			
				// }
				// if(NodeIds.equals("")){
				//			
				// NodeIds=null;
				// }
				// System.out.print(NodeIds);
				Query query = session.createQuery(
						"select p from FaqNode p  where  p.attribute.modifystate!=0  ");
				PageReturn pageReturn = new PageReturn();
				pageReturn.setTotal(query.list().size());
				query.setFirstResult(pageInfo.getStart());
				query.setMaxResults(pageInfo.getSize());
				pageReturn.setReturnList(query.list());
				// System.out.println(query.list().size()+"-----------------------");
				return pageReturn;

			}
		});
	}
	public  PageReturn findCaseNodeByNodeState(final PageTool pageInfo)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				// List lst=null;
				//		
				// lst=this.findCNode();
				// //System.out.print(lst.size());
				// String NodeIds="";
				// if(lst!=null){
				// for(int i=0;i<lst.size();i++){
				// if(lst.get(i)!=null){
				// NodeIds=NodeIds+"'"+String.valueOf(lst.get(i))+"',";
				// }
				// }
				// NodeIds=NodeIds.substring(0,NodeIds.length()-1);
				//			
				// }
				// if(NodeIds.equals("")){
				//			
				// NodeIds=null;
				// }
				// System.out.print(NodeIds);
				Query query = session.createQuery(
						"select p from CaseNode p  where  p.attribute.modifystate!=0 ");
				PageReturn pageReturn = new PageReturn();
				pageReturn.setTotal(query.list().size());
				query.setFirstResult(pageInfo.getStart());
				query.setMaxResults(pageInfo.getSize());
				pageReturn.setReturnList(query.list());
				// System.out.println(query.list().size()+"-----------------------");
				return pageReturn; 

			}
		});
	}

	// 不用的
	public  PageReturn findQuestionsByNodeState(final PageTool pageInfo)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = session.createQuery(
								"select e from EQuestions e,PolicyNode p  where e.stNodeId=p.id and e.stNodeName='P' and p.attribute.modifystate!=0");
				PageReturn pageReturn = new PageReturn();
				pageReturn.setTotal(query.list().size());
				query.setFirstResult(pageInfo.getStart());
				query.setMaxResults(pageInfo.getSize());
				pageReturn.setReturnList(query.list());
				return pageReturn;
			}
		});
	}

	// 将该政策法规的试题STmodefy 状态改成1
	public void updateStmodefy(final String policyNodeId) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PolicyNode policyNode = new PolicyNode();
				policyNode = new EquestionsDAO()
						.findById(new Long(policyNodeId));
				Long state = policyNode.getAttribute().getModifystate();
				if (String.valueOf(state) != null
						&& String.valueOf(state).equals("1")) {
					Query query = session
							.createQuery("select e from EQuestions e where e.stNodeId='"
									+ policyNodeId + "' and e.stNodeName='P' ");
					List lst = null;
					lst = query.list();
					if (lst != null) {
						for (int i = 0; i < lst.size(); i++) {
							EQuestions e = new EQuestions();
							e = (EQuestions) lst.get(i);
							e.setStModefy(new Long(1).longValue());
							session.saveOrUpdate(e);
						}
					}
				}
				return null;
			}
		});
	}

	public void updateSmodefy(final String serviceNodeId) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				ServiceNode serviceNode = new ServiceNode();
				serviceNode = new EquestionsDAO().findSById(new Long(
						serviceNodeId));
				Long state = serviceNode.getAttribute().getModifystate();
				if (String.valueOf(state) != null
						&& String.valueOf(state).equals("1")) {
					Query query = session
							.createQuery("select e from EQuestions e where e.stNodeId='"
									+ serviceNodeId + "' and e.stNodeName='S' ");
					List lst = null;
					lst = query.list();
					if (lst != null) {
						for (int i = 0; i < lst.size(); i++) {
							EQuestions e = new EQuestions();
							e = (EQuestions) lst.get(i);
							e.setStModefy(new Long(1).longValue());
							session.saveOrUpdate(e);
						}
					}
				}
				return null;
			}
		});
	}

	public void updateFmodefy(final String faqNodeId) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				FaqNode faqNode = new FaqNode();
				faqNode = new EquestionsDAO().findFById(new Long(faqNodeId));
				Long state = faqNode.getAttribute().getModifystate();
				if (String.valueOf(state) != null
						&& String.valueOf(state).equals("1")) {
					Query query = session
							.createQuery("select e from EQuestions e where e.stNodeId='"
									+ faqNodeId + "' and e.stNodeName='f' ");
					List lst = null;
					lst = query.list();
					if (lst != null) {
						for (int i = 0; i < lst.size(); i++) {
							EQuestions e = new EQuestions();
							e = (EQuestions) lst.get(i);
							e.setStModefy(new Long(1).longValue());
							session.saveOrUpdate(e);
						}
					}
				}
				return null;
			}
		});
	}
	public void  updateCmodefy(final String caseNodeId)
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		CaseNode caseNode = new CaseNode();
		caseNode = new EquestionsDAO().findCById(new Long(caseNodeId));
		Long state = caseNode.getAttribute().getModifystate();
		if (String.valueOf(state) != null && String.valueOf(state).equals("1")) {
			Query query = session.createQuery(
					"select e from EQuestions e where e.stNodeId='"
							+ caseNodeId + "' and e.stNodeName='c' ");
			List lst = null;
			lst = query.list();
			if (lst != null) {
				for (int i = 0; i < lst.size(); i++) {
					EQuestions e = new EQuestions();
					e = (EQuestions) lst.get(i);
					e.setStModefy(new Long(1).longValue());
					session.saveOrUpdate(e);
				}
			}
		}
		return null;
	}
});
}
	// 开始修改题目时将状态改成修改状态2 政策法规
	public void updateModefy(final String policyNodeId) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PolicyNode policyNode = new PolicyNode();
				policyNode = new EquestionsDAO()
						.findById(new Long(policyNodeId));
				policyNode.getAttribute().setModifystate(new Long(2));
				session.saveOrUpdate(policyNode);
				return null;
			}
		});
	}
	public void updateSModefy(final String serviceNodeId) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				ServiceNode serviceNode = new ServiceNode();
				serviceNode = new EquestionsDAO().findSById(new Long(
						serviceNodeId));
				serviceNode.getAttribute().setModifystate(new Long(2));
				session.saveOrUpdate(serviceNode);
				return null;
			}
		});
	}
	public void updateFModefy(final String faqNodeId) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				FaqNode faqNode = new FaqNode();
				faqNode = new EquestionsDAO().findFById(new Long(faqNodeId));
				faqNode.getAttribute().setModifystate(new Long(2));
				session.saveOrUpdate(faqNode);
				return null;
			}
		});
	}

	public void updateCModefy(final String caseNodeId) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				CaseNode caseNode = new CaseNode();
				caseNode = new EquestionsDAO().findCById(new Long(caseNodeId));
				caseNode.getAttribute().setModifystate(new Long(2));
				session.saveOrUpdate(caseNode);
				return null;
			}
		});
	}
	// 找到修改过的政策法规的试题
	public  PageReturn findstBypolicyNodeId(final String policyNodeId,
			final PageTool pageInfo)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = session.createQuery(
								"select e from EQuestions e where e.stNodeId='"
										+ policyNodeId
										+ "' and e.stNodeName='P' and e.stModefy='1' and e.stScbz=0");
				PageReturn pageReturn = new PageReturn();
				pageReturn.setTotal(query.list().size());
				query.setFirstResult(pageInfo.getStart());
				query.setMaxResults(pageInfo.getSize());
				pageReturn.setReturnList(query.list());
				return pageReturn; 
			}
		});
	}
	public  PageReturn findSstBypolicyNodeId(final String serviceNodeId,
			final PageTool pageInfo) throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = session.createQuery(
								"select e from EQuestions e where e.stNodeId='"
										+ serviceNodeId
										+ "' and e.stNodeName='S' and e.stModefy='1' and e.stScbz=0");
				PageReturn pageReturn = new PageReturn();
				pageReturn.setTotal(query.list().size());
				query.setFirstResult(pageInfo.getStart());
				query.setMaxResults(pageInfo.getSize());
				pageReturn.setReturnList(query.list());
				return pageReturn; 
			}
		});
	}
	public  PageReturn findFstBypolicyNodeId(final String faqNodeId,final  PageTool pageInfo)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = session.createQuery(
								"select e from EQuestions e where e.stNodeId='"
										+ faqNodeId
										+ "' and e.stNodeName='f' and e.stModefy='1' and e.stScbz=0 ");
				PageReturn pageReturn = new PageReturn();
				pageReturn.setTotal(query.list().size());
				query.setFirstResult(pageInfo.getStart());
				query.setMaxResults(pageInfo.getSize());
				pageReturn.setReturnList(query.list());
				return pageReturn; 

			}
		});
	}
	public  PageReturn findCstBypolicyNodeId(final String caseNodeId,final  PageTool pageInfo)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query =session.createQuery(
								"select e from EQuestions e where e.stNodeId='"
										+ caseNodeId
										+ "' and e.stNodeName='c' and e.stModefy='1' and e.stScbz=0 ");
				PageReturn pageReturn = new PageReturn();
				pageReturn.setTotal(query.list().size());
				query.setFirstResult(pageInfo.getStart());
				query.setMaxResults(pageInfo.getSize());
				pageReturn.setReturnList(query.list());
				return pageReturn; 

			}
		});
	}
	// 将试题存入log表

	// public void findstBypolicyNodeId(String policyNodeId){
	// Query query=this.getSession().createQuery(
	// "select e from EQuestions e where e.stNodeId='"
	// +policyNodeId+"' and e.stNodeName='P' ");
	// List lst=null;
	// lst=query.list();
	// if(lst!=null){
	// for(int i=0;i<lst.size();i++){
	// EQuestions eQuestions=(EQuestions) lst.get(0);
	// EModefy eModefy=new EModefy();
	// eModefy.setSt_id(eQuestions.getStId());
	// eModefy.setZt(0);
	// this.getSession().save(eModefy);
	// }
	// }
	// }
	//	
	public  PolicyNode findById(final Long id)throws Exception{
		return (PolicyNode) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				PolicyNode instance = (PolicyNode) session.get(
						PolicyNode.class, id);
				return instance;

			}
		});
	}
	public  ServiceNode findSById(final Long id)throws Exception{
		return (ServiceNode) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				ServiceNode instance = (ServiceNode) session.get(
						ServiceNode.class, id);
				return instance;

			}
		});
	}
	public  FaqNode findFById(final Long id)throws Exception{
		return (FaqNode) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				FaqNode instance = (FaqNode) session.get(
						FaqNode.class, id);
				return instance; 

			}
		});
	}
	public  CaseNode findCById(final Long id)throws Exception{
		return (CaseNode) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				CaseNode instance = (CaseNode) session.get(
						CaseNode.class, id);
				return instance; 

			}
		});
	}
	// public Node getById(Long id) {
	//		
	// Node instance = (PolicyNode) this.getSession().get(Node.class, id);
	// return instance;
	// }
	public void updatestate(final String id) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PolicyNode policyNode = new PolicyNode();
				policyNode = new EquestionsDAO().findById(new Long(id));
				policyNode.getAttribute().setModifystate(new Long(0));
				session.saveOrUpdate(policyNode);
				return null;
			}
		});
	}
	public void updateSstate(final String id) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				ServiceNode ServiceNode = new ServiceNode();
				ServiceNode = new EquestionsDAO().findSById(new Long(id));
				ServiceNode.getAttribute().setModifystate(new Long(0));
				session.saveOrUpdate(ServiceNode);
				return null;
			}
		});
	}

	public void updateFstate(final String id) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				FaqNode faqNode = new FaqNode();
				faqNode =  new EquestionsDAO().findFById(new Long(id));
				faqNode.getAttribute().setModifystate(new Long(0));
				session.saveOrUpdate(faqNode);
				return null;
			}
		});
	}

	public void updateCstate(final String id) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				CaseNode caseNode = new CaseNode();
				caseNode = new EquestionsDAO().findCById(new Long(id));
				caseNode.getAttribute().setModifystate(new Long(0));
				session.saveOrUpdate(caseNode);
				return null;
			}
		});
	}
	/**
	 * 
	 * 
	 * <p>
	 * Description:ͨ��һ�������ѯһ����Ŀ
	 * </p>
	 * 
	 * Created by [www] [Aug 21, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param sequence
	 *            ��ţ��ڼ���
	 * @param stTg
	 *            ��Ŀ���
	 * @param equestions_type
	 *            ��Ŀ����
	 * @param seequestionBuTypes
	 *            ��Ŀҵ������
	 * @return
	 * @throws Exception
	 */
	public  EQuestions findOneQuestion(final int sequence,final  String stTg,
			final long equestions_type,final  long seequestionBuTypes,final  long important)throws Exception{
		return (EQuestions) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(EQuestions.class);
				if (stTg != null && !stTg.equals("")) {
					criteria.add(Restrictions.like("stTg", stTg, MatchMode.ANYWHERE));
				}
				if (equestions_type != -1) {
					criteria.createAlias("equestiontype", "equestiontype").add(
							Restrictions.eq("equestiontype.priority", equestions_type));
				}

				if (seequestionBuTypes != -1) {
					criteria.createAlias("ebusinesstype", "ebusinesstype").add(
							Restrictions.eq("ebusinesstype.priority",
									seequestionBuTypes));
				}
				if (important != -1) {
					criteria.createAlias("eimportance", "eimportance").add(
							Restrictions.eq("eimportance.priority", important));
				}

				criteria.add(Restrictions.eq("stKszy", new Long(0).longValue()));
				criteria.add(Restrictions.eq("stCheck", new Long(1).longValue()));
				criteria.setMaxResults(1);
				criteria.setFirstResult(sequence);
				EQuestions equestions = (EQuestions) criteria.uniqueResult();
				return equestions; 

			}
		});
	}
	public  EQuestions load(final Long id)throws Exception{
		return (EQuestions) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return (EQuestions) session.load(EQuestions.class,
						id);
			}
		});
	}
	public  PageReturn findAdvice(final PageTool pageInfo,final  Long id) throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EAdvice eadvice = new EAdvice();
				PageReturn pageReturn = new PageReturn();
				Criteria criteria = session.createCriteria(EAdvice.class);
				criteria.add(Restrictions.eq("ST_ID", id));
				pageReturn.setTotal(criteria.list().size());
				criteria.setMaxResults(pageInfo.getSize());
				criteria.setFirstResult(pageInfo.getStart());
				pageReturn.setReturnList(criteria.list());
				// System.out.println("长度==================>"+criteria.list().size());
				return pageReturn;	 

			}
		});
	}
	/**
	 * 
	 * 
	 * <p>
	 * Description:�������ҵ���Ŀ
	 * </p>
	 * 
	 * Created by [www] [Aug 27, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param pageInfo
	 * @param equestions
	 * @return
	 */
	public  PageReturn findQuetionsBytiaojian(final PageTool pageInfo,
			final EQuestions equestions)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn = new PageReturn();
				Criteria criteria = session.createCriteria(EQuestions.class);
				if (equestions.getStTg() != null && !equestions.getStTg().equals("")) {
					criteria.add(Restrictions.like("stTg", equestions.getStTg(),
							MatchMode.ANYWHERE));
				}

				if (equestions.getEbusinesstype() != null
						&& equestions.getEbusinesstype().getPriority() != -1) {
					criteria.createAlias("ebusinesstype", "ebusinesstype").add(
							Restrictions.eq("ebusinesstype.priority", equestions
									.getEbusinesstype().getPriority()));
				}

				if (equestions.getEquestiontype() != null
						&& equestions.getEquestiontype().getPriority() != -1) {
					criteria.createAlias("equestiontype", "equestiontype").add(
							Restrictions.eq("equestiontype.priority", equestions
									.getEquestiontype().getPriority()));
				}
				pageReturn.setTotal(criteria.list().size());
				criteria.setMaxResults(pageInfo.getSize());
				criteria.setFirstResult(pageInfo.getStart());
				pageReturn.setReturnList(criteria.list());
				return pageReturn;	 

			}
		});
	}
	public  int findCountByType(final Long type)throws Exception{
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query = session.createQuery(
								"select count(*) from EQuestions e where e.equestiontype.priority=:type and e.stKszy=1  and e.stScbz=0 and e.stCheck=1");
				query.setLong("type", type);
				query.setMaxResults(1);
				Integer count = Integer.valueOf(query.uniqueResult().toString());
				return count.intValue();

			}
		});
	}

	public void saveQuestion(final EQuestions equestions) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(equestions);
				return null;
			}
		});
	}
	// 保存建议内容
	public void saveAdvice(final long ST_ID, final String content)
			throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				// 找到我的角色
				AcegiUtil acegiUtil = new AcegiUtil();
				EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails())
						.getUser();

				EAdvice eadvice = new EAdvice();
				if (content != null && !content.equals("")) {
					eadvice.setContent(content);
				}
				eadvice.setDt(new Date());
				eadvice.setST_ID(ST_ID);
				eadvice.setRyId(user.getUsername().toString());
				eadvice.setRymc(user.getRealname().toString());
				session.save(eadvice);
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public    List<EQuestions> findCounts(final String serviceType,final  String bxType)throws Exception{
		return (List<EQuestions>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				StringBuffer hql = new StringBuffer(
						"from EQuestions e where e.stKszy=1  and e.stScbz=0 and e.stCheck=1 ");
				if (!"".equals(serviceType) && serviceType != null) {
					hql.append(" and e.ebusinesstype.id=" + serviceType);
				}
				if (!"".equals(bxType) && bxType != null) {
					hql.append(" and e.bxType=" + bxType);
				}
				List<EQuestions> list = session.createQuery(hql.toString())
						.list();
				return list;

			}
		});
	}
}
