package com.wondersgroup.falcon.beans.exam;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.abstracts.AbstractService;
import com.wondersgroup.falcon.dao.exam.ExamDAO;
import com.wondersgroup.falcon.dao.exam.ExamPaperDAO;
import com.wondersgroup.falcon.model.exam.ExamAnswerStatus;
import com.wondersgroup.falcon.model.exam.ExamAnswers;
import com.wondersgroup.falcon.model.exam.ExamAnswersQuestions;
import com.wondersgroup.falcon.model.exam.ExamImportance;
import com.wondersgroup.falcon.model.exam.ExamKeys;
import com.wondersgroup.falcon.model.exam.ExamPaper;
import com.wondersgroup.falcon.model.exam.ExamQuestionType;
import com.wondersgroup.falcon.model.exam.ExamQuestions;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public interface ExamPaperService extends AbstractService{
	
	/**
	 * 得到选中的题型
	 * @param ischeck 是否被选中
	 * @return 多个选中的题型
	 */
	
	public List findQuestionType(int ischeck);
	
	/**
	 * 根据权重随机产生不同权重题目数量.
	 * @param allnum
	 * @return 某个题型不同权重的题目数量
	 */
	
	public int[] getImportanceNum(int allnum);
	
	/**
	 * 通过不同权重的数量往数据库随机取记录.
	 */
	
	public List findByNumAndImp(int num,int numfigure,String subtype);
	
	/**
	 * 根据ID查询一张试卷(整张，包括所有题目)
	 * @param id
	 * @return
	 */
	
	public ExamPaper findExamPaperById(String id);
	
	/**
	 * 根据ID查询答卷类型
	 */
	public ExamAnswerStatus findExamAnsStaById(String id);
	
	/**
	 * 根据答卷ID查询一张答卷
	 * @param anspaperid 答卷ID
	 * @return 答卷
	 */
	public ExamAnswers finAnswerPaperById(String anspaperid);
	
	/**
	 * 根据答卷ID与试题ID取得对应答卷试题
	 * @param answerpageid 答卷ID
	 * @param realsubid 试题ID
	 * @return 某张答卷对应的试题
	 */
	public ExamAnswersQuestions findAnswerQuestionsByCrit(String answerpageid,String realsubid);
	
	
	/**
	 * 通过考卷ID查询试题信息
	 * @param examid
	 * @return
	 */
	public List findQuestionsByExamid(String examid);
	
}
