package com.wondersgroup.falcon.beans.exam;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.abstracts.AbstractServiceImple;
import com.wondersgroup.falcon.dao.exam.ExamDAO;
import com.wondersgroup.falcon.dao.exam.ExamPaperDAO;
import com.wondersgroup.falcon.dao.exam.ExamQuestionDAO;
import com.wondersgroup.falcon.model.exam.ExamAnswerStatus;
import com.wondersgroup.falcon.model.exam.ExamAnswers;
import com.wondersgroup.falcon.model.exam.ExamAnswersQuestions;
import com.wondersgroup.falcon.model.exam.ExamImportance;
import com.wondersgroup.falcon.model.exam.ExamKeys;
import com.wondersgroup.falcon.model.exam.ExamPaper;
import com.wondersgroup.falcon.model.exam.ExamQuestionType;
import com.wondersgroup.falcon.model.exam.ExamQuestions;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ExamPaperServiceImple extends AbstractServiceImple implements ExamPaperService{
	/**
	 * �õ�ѡ�е�����
	 * @param ischeck �Ƿ�ѡ��
	 * @return ���ѡ�е����� 
	 */
	public List findQuestionType(int ischeck){
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			list = ed.findQuestionType(ischeck);
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}

	public void save(Object obj) {
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			ed.saveOrUpdate(obj);
			HibernateUtil.commitTransaction();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}		
	}
	
	/**
	 * ����Ȩ�����������ͬȨ����Ŀ����.
	 * @param allnum
	 * @return
	 */
	public int[] getImportanceNum(int allnum){
		int[] nums = new int[3];
		nums[0]=0;
		nums[1]=0;
		nums[2]=0;
		try{
			List list1 = new ArrayList();
			List list2 = new ArrayList();
			List list3 = new ArrayList();
			
			for(int i=0;i<allnum;i++){
				int randomnum = (int) Math.ceil(Math.random()*600);
				if(randomnum>=1&&randomnum<=100)list1.add(new Integer(randomnum));
				if(randomnum>100&&randomnum<=300)list2.add(new Integer(randomnum));				
				if(randomnum>300&&randomnum<=600)list3.add(new Integer(randomnum));				
			}
			
			if(list1!=null&&list1.size()>0) nums[0]=list1.size();
			if(list2!=null&&list2.size()>0) nums[1]=list2.size();			
			if(list3!=null&&list3.size()>0) nums[2]=list3.size();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return nums;
	}
	
	/**
	 * ͨ����ͬȨ�ص����������ݿ����ȡ��¼.
	 */
	public List findByNumAndImp(int num,int numfigure,String subtype){
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			list = ed.findByNumAndImp(num, numfigure, subtype);
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	
	/**
	 * ����ID��ѯһ���Ծ�(���ţ�����������Ŀ)
	 * @param id
	 * @return
	 */
	public ExamPaper findExamPaperById(String id){
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			ExamPaper ep = (ExamPaper) ed.findById(ExamPaper.class, id);
			HibernateUtil.commitTransaction();
			Hibernate.initialize(ep);
			Hibernate.initialize(ep.getPaperquestion());
			Hibernate.initialize(ep.getExamanswers());
			return ep;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	/**
	 * ����ID��ѯ�������
	 */
	public ExamAnswerStatus findExamAnsStaById(String id){
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			ExamAnswerStatus eas = null;
			eas = (ExamAnswerStatus)ed.findById(ExamAnswerStatus.class, id);
			HibernateUtil.commitTransaction();
			return eas;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	/**
	 * ���ݴ��ID��ѯһ�Ŵ��
	 * @param anspaperid ���ID
	 * @return ���
	 */
	public ExamAnswers finAnswerPaperById(String anspaperid){
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			ExamAnswers ea = ed.finAnswerPaperById(anspaperid); 
			HibernateUtil.commitTransaction();
			Hibernate.initialize(ea.getExamansrques());
			return ea;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	/**
	 * ���ݴ��ID������IDȡ�ö�Ӧ�������
	 * @param answerpageid ���ID
	 * @param realsubid ����ID
	 * @return ĳ�Ŵ���Ӧ������
	 */
	public ExamAnswersQuestions findAnswerQuestionsByCrit(String answerpageid,String realsubid){
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			ExamAnswersQuestions eaq = ed.findAnswerQuestionsByCrit(answerpageid, realsubid);
			HibernateUtil.commitTransaction();
			return eaq;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List findRquesKeysByExamrquesid(String examrsubid){
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			list = ed.findRquesKeysByExamrquesid(examrsubid);
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;	
	}

	public void delete(Object obj) {
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			ed.delete(obj);
			HibernateUtil.commitTransaction();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * ͨ������ID��ѯ������Ϣ
	 * @param examid
	 * @return
	 */
	public List findQuestionsByExamid(String examid){
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			list = ed.findQuestionsByExamid(examid);
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;	
	}
	
	/**
	 * ��ѯ�����Ծ�
	 * @return
	 */
	public List findExamPaper(){
		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			List list = ed.findExamPaper();
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}	
	
	/**
	 * �����Ծ����û���ѯ��Ӧ��һ�Ŵ��
	 */
	
	public ExamAnswers finByPaperAndUser(ExamPaper exapaper,String username){

		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			ExamAnswers ea = ed.finByPaperAndUser(exapaper, username);
			HibernateUtil.commitTransaction();
			Hibernate.initialize(ea.getExamansrques());
			return ea;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	/**
	 * �����Ծ��ѯ�����д��
	 * @param exapaper �Ծ�
	 * @return ĳ���Ծ�����д��
	 */
	public List findAnswerPageByPaper(ExamPaper exapaper,boolean order){

		try{
			ExamPaperDAO ed = new ExamPaperDAO();
			list = ed.findAnswerPageByPaper(exapaper, order);
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
}
