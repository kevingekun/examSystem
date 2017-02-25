package com.wondersgroup.kaoshi.service;

import java.util.List;

import com.wondersgroup.falcon.question.model.EAdvice;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public interface EQuestionsService { 
	public PageReturn findQuetionsByType(PageTool pageInfo,String typeId,String stMc,Long busType,java.util.Date inputtime,Long eimportance,java.util.Date sjbegin,java.util.Date sjend,String xingzhi,String gzid,String gzdj,long difficulty);
	
	public EQuestions findOneQuestion(int sequence,String stTg,long equestions_type,long seequestionBuTypes,long important)throws Exception;
	
	public EQuestions load(Long id);
	
	public PageReturn findAdvice(PageTool pageInfo,Long id);
	
	public PageReturn findQuetionsBytiaojian(PageTool pageInfo,EQuestions equestions) ;
	
	public int findqQuestionCountByType(Long type);
	
	public void saveQuestion(EQuestions equestions);
	
	public void saveAdvice(long ST_ID,String content);
	
	public PageReturn findQuestionsByNodeState(PageTool pageInfo);
	//政策法规
	public PageReturn findPolicyNodeByNodeState(PageTool pageInfo);
	
	public PageReturn findstBypolicyNodeId(String policyNodeId,PageTool pageInfo);
	
	public void updatestate(String id);
	
	public void updateStmodefy(String id);
	
	public void updateModefy(String id);
	//办事指南
    public PageReturn findServiceNodeByNodeState(PageTool pageInfo);
    
    public PageReturn findSstBypolicyNodeId(String serviceNodeId,PageTool pageInfo);
	
	public void updateSstate(String id);
	
	public void updateSmodefy(String id);
	
	public void updateSModefy(String id);
	//问答资料
	public PageReturn findFaqNodeByNodeState(PageTool pageInfo);
	
    public PageReturn findFstBypolicyNodeId(String faqNodeId,PageTool pageInfo);
	
	public void updateFstate(String id);
	
	public void updateFmodefy(String id);
	
	public void updateFModefy(String id);
	//学习资料
	public PageReturn findCaseNodeByNodeState(PageTool pageInfo);
	
	public PageReturn findCstBypolicyNodeId(String faqNodeId,PageTool pageInfo);
		
	public void updateCstate(String id);
		
	public void updateCmodefy(String id);
		
	public void updateCModefy(String id);

	public List<EQuestions> findQuestionCounts(String serviceType, String bxType);
	
}
