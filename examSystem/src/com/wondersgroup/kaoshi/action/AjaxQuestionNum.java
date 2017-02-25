package com.wondersgroup.kaoshi.action;


import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractAction;
public class AjaxQuestionNum  extends AbstractAction{
	
	private EpaperquestionsService  epaperquestionsService;
	
	private String questionId;
	private String times;
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	
	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}
	@Override
	public String execute() throws Exception {
		/*
		 * 找到试卷的某个类型的所有的分数
		 */
		this.times=String.valueOf(this.epaperquestionsService.getAllNunber(questionId));
		return SUCCESS;
	}
	
}
