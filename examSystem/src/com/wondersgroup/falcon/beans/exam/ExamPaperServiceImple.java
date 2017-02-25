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
	 * 得到选中的题型
	 * @param ischeck 是否被选中
	 * @return 多个选中的题型 
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
	 * 根据权重随机产生不同权重题目数量.
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
	 * 通过不同权重的数量往数据库随机取记录.
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
	 * 根据ID查询一张试卷(整张，包括所有题目)
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
	 * 根据ID查询答卷类型
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
	 * 根据答卷ID查询一张答卷
	 * @param anspaperid 答卷ID
	 * @return 答卷
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
	 * 根据答卷ID与试题ID取得对应答卷试题
	 * @param answerpageid 答卷ID
	 * @param realsubid 试题ID
	 * @return 某张答卷对应的试题
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
	 * 通过考卷ID查询试题信息
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
	 * 查询所有试卷
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
	 * 根据试卷与用户查询对应的一张答卷
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
	 * 根据试卷查询其所有答卷
	 * @param exapaper 试卷
	 * @return 某张试卷的所有答卷
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
