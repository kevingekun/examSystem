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
	 * �õ�ѡ�е�����
	 * @param ischeck �Ƿ�ѡ��
	 * @return ���ѡ�е�����
	 */
	
	public List findQuestionType(int ischeck);
	
	/**
	 * ����Ȩ�����������ͬȨ����Ŀ����.
	 * @param allnum
	 * @return ĳ�����Ͳ�ͬȨ�ص���Ŀ����
	 */
	
	public int[] getImportanceNum(int allnum);
	
	/**
	 * ͨ����ͬȨ�ص����������ݿ����ȡ��¼.
	 */
	
	public List findByNumAndImp(int num,int numfigure,String subtype);
	
	/**
	 * ����ID��ѯһ���Ծ�(���ţ�����������Ŀ)
	 * @param id
	 * @return
	 */
	
	public ExamPaper findExamPaperById(String id);
	
	/**
	 * ����ID��ѯ�������
	 */
	public ExamAnswerStatus findExamAnsStaById(String id);
	
	/**
	 * ���ݴ��ID��ѯһ�Ŵ��
	 * @param anspaperid ���ID
	 * @return ���
	 */
	public ExamAnswers finAnswerPaperById(String anspaperid);
	
	/**
	 * ���ݴ��ID������IDȡ�ö�Ӧ�������
	 * @param answerpageid ���ID
	 * @param realsubid ����ID
	 * @return ĳ�Ŵ���Ӧ������
	 */
	public ExamAnswersQuestions findAnswerQuestionsByCrit(String answerpageid,String realsubid);
	
	
	/**
	 * ͨ������ID��ѯ������Ϣ
	 * @param examid
	 * @return
	 */
	public List findQuestionsByExamid(String examid);
	
}
