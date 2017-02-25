package com.wondersgroup.falcon.beans.exam;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.abstracts.AbstractServiceImple;
import com.wondersgroup.falcon.dao.exam.ExamDAO;
import com.wondersgroup.falcon.dao.exam.ExamMarksDAO;
import com.wondersgroup.falcon.dao.exam.ExamNoticeDAO;
import com.wondersgroup.falcon.dao.exam.ExamQuestionDAO;
import com.wondersgroup.falcon.model.exam.ExamImportance;
import com.wondersgroup.falcon.model.exam.ExamKeys;
import com.wondersgroup.falcon.model.exam.ExamQuestionType;
import com.wondersgroup.falcon.model.exam.ExamQuestions;
import com.wondersgroup.falcon.model.exam.ExamRealQuestions;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ExamMarksServiceImple extends AbstractServiceImple implements ExamMarksService{

	public List finByAnswersByUser(String username){

		try{
			ExamMarksDAO emd = new ExamMarksDAO();
			list = emd.finByAnswersByUser(username);
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
		// TODO Auto-generated method stub
		
	}

}
