package com.wondersgroup.kaoshi.action;

import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.kaoshi.service.EQuestionsService;
import com.wondersgroup.kaoshi.util.AbstractAction;

public class AjaxUpdateCol extends AbstractAction {
	//查询试卷和实体的连接表
	private EQuestionsService  equestionsService;
	//参数
	private String quesionId;
	
	private long count;
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String execute() throws Exception {
		/*
		 * 找到试卷的某个类型的所有的分数
		 */

		//System.out.print(content+"内容-----------------");
		EQuestions equestions=this.equestionsService.load(new Long(quesionId));
		equestions.setStJyxgcs(equestions.getStJyxgcs()+1);
		this.setCount(equestions.getStJyxgcs());
		this.equestionsService.saveQuestion(equestions);
		this.equestionsService.saveAdvice(new Long(quesionId).longValue(),content);
		return SUCCESS;
	}
	public String getQuesionId() {
		return quesionId;
	}
	public void setQuesionId(String quesionId) {
		this.quesionId = quesionId;
	}
	public void setEquestionsService(EQuestionsService equestionsService) {
		this.equestionsService = equestionsService;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
}
