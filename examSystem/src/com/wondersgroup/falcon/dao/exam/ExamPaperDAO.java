package com.wondersgroup.falcon.dao.exam;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.model.exam.ExamAnswers;
import com.wondersgroup.falcon.model.exam.ExamAnswersQuestions;
import com.wondersgroup.falcon.model.exam.ExamPaper;
import com.wondersgroup.falcon.model.exam.ExamRealQuestions;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ExamPaperDAO extends ExamDAO{
	
	/**
	 * �õ�ѡ�е�����
	 * @param ischeck �Ƿ�ѡ��
	 * @return ���ѡ�е�����
	 */
	public List findQuestionType(final int ischeck )throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					String hql = "from ExamQuestionType t where t.ischeck="+ischeck+" ";
					Query query = session.createQuery(hql);
					List list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;

			}
		});


	}
	
	/**
	 * ͨ��ͬȨ�ص���������ݿ����ȡ��¼.
	 */
	 
	public List findByNumAndImp(final int num,final int numfigure,final String subtype)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					List list = null;
					String hql = "select t.REALQUESID as {tb.realquesid},t.QUESTIONID as {tb.examquestions}, "+
								 "t.TYPEID as {tb.examquestype},t.IMPID as {tb.importance},t.BUSINESSTYPE as {tb.businesstype}, "+
								 "t.CORRECTKEY as {tb.correctkey} from EXAM_REALQUESTIONS t where t.typeid='"+subtype+"' "+
								 "and t.IMPID="+numfigure+" order by dbms_random.random";
					Query query = session.createSQLQuery(hql).addEntity("tb", ExamRealQuestions.class);
					query.setFirstResult(0);
					query.setMaxResults(num);
					list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;

			}
		});


	}
	
	public ExamAnswers finAnswerPaperById(final String anspaperid)throws Exception{
		return (ExamAnswers) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					String hql = "from ExamAnswers t where t.examanswerid=?";
					Query query = session.createQuery(hql);
					query.setString(0, ""+anspaperid+"");
					List list = null;
					list = query.list();
					if(list!=null){
						return (ExamAnswers)list.get(0);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;  

			}
		});


	}
	/**
	 * ��ݴ��ID������IDȡ�ö�Ӧ�������
	 * @param answerpageid ���ID
	 * @param realsubid ����ID
	 * @return ĳ�Ŵ���Ӧ������
	 */
	
	public ExamAnswersQuestions findAnswerQuestionsByCrit(final String answerpageid,final String realsubid)throws Exception{
		return (ExamAnswersQuestions) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					String hql = " from ExamAnswersQuestions t "+
								 " where t.examanswer.examanswerid='"+answerpageid+"' "+
								 " and t.examrealques.realquesid='"+realsubid+"'";
					Query query = session.createQuery(hql);
					List list = query.list();
					if(list!=null){
						ExamAnswersQuestions eas = (ExamAnswersQuestions)list.get(0);
						return eas;
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;

			}
		});


	}
	
	public List findRquesKeysByExamrquesid(final  String examrsubid)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					String hql = " from ExamRqueKeys t where t.examrques.id='"+examrsubid+"'";
					Query query = session.createQuery(hql);
					List  list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;

			}
		});


	}
	/**
	 * ͨ���ID��ѯ������Ϣ
	 * @param examid
	 * @return
	 */
	public List findQuestionsByExamid(final String examid)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					String hql = "select t.realquestion.examquestype.typeid,t.questionmarks,t.realquestion from ExamPaperQuestions t where t.exampaper.id='"+examid+"'";
					Query query = session.createQuery(hql);
					List  list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;

			}
		});


	}
	public List findRquesKeysByExamansid(final String examrsubid)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					String hql = "from ExamRqueKeys t where t.examrques.id='"+examrsubid+"'";
					Query query = session.createQuery(hql);
					List  list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null; 

			}
		});


	}
	
	public List findExamPaper()throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					String hql = "from ExamPaper t where sysdate between t.effectstarttime and t.effectendtime";
					Query query = session.createQuery(hql);
					List list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null; 

			}
		});


	}
	 
	public ExamAnswers finByPaperAndUser(final ExamPaper exapaper,final String username)throws Exception{
		return (ExamAnswers) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					String hql = "from ExamAnswers t where t.exampaper.examid=? and t.username=?";
					Query query = session.createQuery(hql);
					query.setString(0, ""+exapaper.getExamid()+"");
					query.setString(1, ""+username+"");
					List list = null;
					list = query.list();
					if(list!=null&&list.size()!=0){
						return (ExamAnswers)list.get(0);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;	 

			}
		});


	}
	
	public List findAnswerPageByPaper(final ExamPaper exapaper,final boolean order)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					String hql = "from ExamAnswers t where t.exampaper.examid=? ";
					if(order){
						hql = hql+" order by t.wholemark desc";
					}else{
						hql = hql+" order by t.wholemark asc";
					}
					Query query = session.createQuery(hql);
					query.setString(0, ""+exapaper.getExamid()+"");
					List list = null;
					list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;

			}
		});


	}
}
