package com.wondersgroup.falcon.beans.exam;

import java.util.List;

import com.wondersgroup.falcon.abstracts.AbstractService;
import com.wondersgroup.falcon.dao.exam.ExamDAO;
import com.wondersgroup.falcon.model.exam.ExamImportance;
import com.wondersgroup.falcon.model.exam.ExamKeys;
import com.wondersgroup.falcon.model.exam.ExamQuestionType;
import com.wondersgroup.falcon.model.exam.ExamQuestions;
import com.wondersgroup.falcon.model.exam.ExamRealQuestions;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public interface ExamQuestionService extends AbstractService{
	public ExamQuestions findQuestionById(String id);
	
	public ExamKeys findKeysById(String id);
	
	/**
	 * 根据ID查询一个试题
	 * @param id
	 * @return
	 */
	public ExamRealQuestions findRealQuesById(String id);
	
	public ExamQuestionType findTypeById(String id);
	
	public ExamImportance findImportanceById(String id);

	public List findByCrits(String realsubname, String businesstype, String subjecttype, String importance);

	public List findKeysByCrit(String realkeyname);
}
