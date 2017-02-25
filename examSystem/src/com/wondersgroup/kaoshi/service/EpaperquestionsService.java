package com.wondersgroup.kaoshi.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestiontype;
import com.wondersgroup.kaoshi.bo.EAnswertemp;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public interface EpaperquestionsService {

	
	public PageReturn findAllByPage(PageTool pageTool,String id,EQuestions equestion);
	
	public PageReturn findAllByPage(PageTool pageInfo,EQuestions equestion);
	
	public List findAllByIdType(String paperId,String type);
	
	public List findAllById(String id);
	
	public List findEQuestiontype();
	public int  getAllNunber(String questionId);
	
	public void removeById(String id);
	
	public void deleteById(String id);
	
	public boolean checkPaperRelate(String sjid);
	
	public void addQuestion(String[] questionIds,String sjid,Map<String, Double> map);

	//代替addQuestion方法
	public void addQs(String[] questionIds,String sjid, Double fenzhi);
	
	public void addPaperQuestions(String paperid,String newpaperId);
	
	public List findallbuType();
	
	public double findPaperQuestionFensu(String paperid);
	
	public double  findFenshuByTypeAnd(String sjid,String typeId);
	
	public List findallEimportances();

	public List<EQuestiontype> findEQuestiontypeByPaperType();

	public List findEQuestiontypeAll();
	
	public void addAnswerTemp(String userid,String answer,String shijuanid,String questionid,String questionidtype,Connection conn );
	
	public List<EAnswertemp> pingIp(String examid);
}
