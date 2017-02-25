package com.wondersgroup.kaoshi.action;

import java.util.List;

import com.wondersgroup.falcon.question.model.EAdvice;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.gonggao.bo.TGg;
import com.wondersgroup.kaoshi.service.EQuestionsService;
import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;

public class QuestionAction extends AbstractPageNavAction{
	private String questionId;
	private EQuestionsService equestionsService;
	private EQuestions equestions;
	
	private List<EAdvice> eadvice;
	public EQuestions getEquestions() {
		return equestions;
	}
	public void setEquestions(EQuestions equestions) {
		this.equestions = equestions;
	}
	public void setEquestionsService(EQuestionsService equestionsService) {
		this.equestionsService = equestionsService;
	}
//	public String execute() throws Exception
//	{
//		return this.doAcion();
//		
//	}
	@Override
	public String doAcion() throws Exception {
		//找到试题
		equestions=this.equestionsService.load(new Long(questionId));
		//找到所有的业务类型
		//找到业务类型
//		this.busTypes=this.epaperquestionsService.findallbuType();
		
		return SUCCESS;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
	
	public String findAdvice() throws Exception {
		//找到试题
		this.pageReturn=this.equestionsService.findAdvice(pageTool, new Long(questionId));
        eadvice=this.pageReturn.getReturnList();
      // System.out.print("action长度------------"+this.pageReturn.getReturnList().size());
		
		return SUCCESS;
	}
	public List<EAdvice> getEadvice() {
		return eadvice;
	}
	public void setEadvice(List<EAdvice> eadvice) {
		this.eadvice = eadvice;
	}


	
	
	
}
