package com.wondersgroup.falcon.beans.exam;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.abstracts.AbstractServiceImple;
import com.wondersgroup.falcon.dao.exam.ExamDAO;
import com.wondersgroup.falcon.dao.exam.ExamNoticeDAO;
import com.wondersgroup.falcon.dao.exam.ExamQuestionDAO;
import com.wondersgroup.falcon.model.exam.ExamAnnoucement;
import com.wondersgroup.falcon.model.exam.ExamImportance;
import com.wondersgroup.falcon.model.exam.ExamKeys;
import com.wondersgroup.falcon.model.exam.ExamQuestionType;
import com.wondersgroup.falcon.model.exam.ExamQuestions;
import com.wondersgroup.falcon.model.exam.ExamRealQuestions;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ExamNoticeServiceImple extends AbstractServiceImple implements ExamNoticeService{

	/**
	 * 取得所有的考试公告
	 * @return 所有考试公告
	 */
	public List findAllAnnoucement(){
		try{
			ExamNoticeDAO ed = new ExamNoticeDAO();
			list = ed.findAllAnnoucement();
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
		// TODO Auto-generated method stub
		
	}

	public void save(Object obj) {
		ExamNoticeDAO ed = new ExamNoticeDAO();
		try{
			ed.saveOrUpdate(obj);
			HibernateUtil.commitTransaction();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * 取得考试公告Byid
	 * @param id 公告id
	 * @return 一条公告
	 */
	public ExamAnnoucement findAnnoucementById(String id){
		try{
			ExamNoticeDAO ed = new ExamNoticeDAO();
			ExamAnnoucement ea = (ExamAnnoucement)ed.findById(ExamAnnoucement.class, id);
			HibernateUtil.commitTransaction();
			return ea;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;			
	}		
}
