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

public interface ExamNoticeService extends AbstractService{
	/**
	 * 取得所有的考试公告
	 * @return 所有考试公告
	 */
	public List findAllAnnoucement();
}
