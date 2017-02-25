package com.wondersgroup.falcon.beans.exam;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.abstracts.AbstractServiceImple;
import com.wondersgroup.falcon.dao.exam.ExamDAO;
import com.wondersgroup.falcon.dao.exam.ExamQuestionDAO;
import com.wondersgroup.falcon.model.exam.ExamImportance;
import com.wondersgroup.falcon.model.exam.ExamKeys;
import com.wondersgroup.falcon.model.exam.ExamQuestionType;
import com.wondersgroup.falcon.model.exam.ExamQuestions;
import com.wondersgroup.falcon.model.exam.ExamRealQuestions;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ExamQuestionServiceImple extends AbstractServiceImple implements ExamQuestionService{

	public ExamQuestions findQuestionById(String id) {
		ExamQuestionDAO ed = new ExamQuestionDAO();
		try{
			ExamQuestions eq = new ExamQuestions();
			eq = (ExamQuestions) ed.findById(ExamQuestions.class, id);
			HibernateUtil.commitTransaction();
			return eq;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public List findByCrits(String realsubname,String businesstype,String subjecttype,String importance){
		try{
			ExamQuestionDAO eqd = new ExamQuestionDAO();
			list = eqd.findByCrits(realsubname, businesstype, subjecttype, importance);
			HibernateUtil.commitTransaction();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}

	public List findKeysByCrit(String realkeyname){
		try{
			ExamQuestionDAO eqd = new ExamQuestionDAO();
			list = eqd.findKeysByCrit(realkeyname);
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
		ExamQuestionDAO eqd = new ExamQuestionDAO();
		try{
			eqd.saveOrUpdate(obj);
			HibernateUtil.commitTransaction();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		
	}
	public ExamQuestionType findTypeById(String id) {
		ExamQuestionDAO ed = new ExamQuestionDAO();
		try{
			ExamQuestionType eq = new ExamQuestionType();
			eq = (ExamQuestionType) ed.findById(ExamQuestionType.class, id);
			HibernateUtil.commitTransaction();
			return eq;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	
	public ExamImportance findImportanceById(String id) {
		ExamQuestionDAO ed = new ExamQuestionDAO();
		try{
			ExamImportance eq = new ExamImportance();
			eq = (ExamImportance) ed.findById(ExamImportance.class, id);
			HibernateUtil.commitTransaction();
			return eq;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}	
	
	public ExamKeys findKeysById(String id) {
		ExamQuestionDAO ed = new ExamQuestionDAO();
		try{
			ExamKeys eq = new ExamKeys();
			eq = (ExamKeys) ed.findById(ExamKeys.class, id);
			HibernateUtil.commitTransaction();
			return eq;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}

	public ExamRealQuestions findRealQuesById(String id) {
		ExamQuestionDAO ed = new ExamQuestionDAO();
		try{
			ExamRealQuestions eq = new ExamRealQuestions();
			eq = (ExamRealQuestions) ed.findById(ExamRealQuestions.class, id);
			HibernateUtil.commitTransaction();
			Hibernate.initialize(eq.getExamquestype());
			Hibernate.initialize(eq.getExamrqueskeys());
			return eq;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}

	public void delete(Object obj) {
		ExamQuestionDAO ed = new ExamQuestionDAO();
		try{
			ed.delete(obj);
			HibernateUtil.commitTransaction();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}	
	}	
}
