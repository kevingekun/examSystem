package com.wondersgroup.falcon.dao.exam;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.model.exam.ExamAnswers;
import com.wondersgroup.falcon.model.exam.ExamAnswersQuestions;
import com.wondersgroup.falcon.model.exam.ExamRealQuestions;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ExamNoticeDAO extends ExamDAO{
	
	/**
	 * 取得所有的考试公告
	 * @return 所有考试公告
	 */
	public List findAllAnnoucement(){
		try{
			ExamDAO ed = new ExamDAO();
			String hql = "select t.id,t.title,t.exampaper.examname,t.sender.realname,t.sendtime from ExamAnnoucement t order by t.sendtime desc";
			List list = ed.findByHql(hql);
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;			
	}

}
