package com.wondersgroup.kaoshi.action;

import java.util.Date;
import java.util.List;

import com.wondersgroup.kaoshi.service.EAnswerpaperService;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;

public class AnswerPaperQuerryAction extends AbstractPageNavAction {
	// 查询条件
	private String sjMc;
	private Date djsj;
	private Date djsjend;
	private Long SjId;

	// 可以审阅的试卷
	private List answerpapers;

	// 答题service
	private EAnswerpaperService eanswerpaperService;

	// 试卷service
	private EPapersService epapersservice;

	public void setEanswerpaperService(EAnswerpaperService eanswerpaperService) {
		this.eanswerpaperService = eanswerpaperService;
	}

	@Override
	public String doAcion() throws Exception {
		this.pageReturn = this.eanswerpaperService.findsypaper(this.pageTool,
				this.sjMc, this.djsj, djsjend);
		this.answerpapers = pageReturn.getReturnList();

		return SUCCESS;
	}

	/*
	 * 
	 */

	public String findPapers() throws Exception {

		this.pageReturn = this.epapersservice.findpaperbySjzt(this.pageTool,
				null, null, null, null, null);
		this.answerpapers = pageReturn.getReturnList();

		return SUCCESS;
	}

	public String findAnswerPaperBySjId() throws Exception {

		this.pageReturn = this.eanswerpaperService.findAnswerPaperBySjId(
				this.pageTool, this.SjId);
		this.answerpapers = pageReturn.getReturnList();

		return SUCCESS;
	}

	// public String doAcion() throws Exception {
	//		
	// this.pageReturn=this.eanswerpaperService.findAnswerPaper(this.pageTool,
	// this.sjMc);
	// this.answerpapers=pageReturn.getReturnList();
	//		
	// return SUCCESS;
	// }

	public String findAnswersBypaperId() throws Exception {

		this.pageReturn = this.eanswerpaperService.findAnswerPaper(
				this.pageTool, this.sjMc);
		this.answerpapers = pageReturn.getReturnList();

		return SUCCESS;
	}

	public List getAnswerpapers() {
		return answerpapers;
	}

	public void setAnswerpapers(List answerpapers) {
		this.answerpapers = answerpapers;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public Date getDjsj() {
		return djsj;
	}

	public void setDjsj(Date djsj) {
		this.djsj = djsj;
	}

	public Date getDjsjend() {
		return djsjend;
	}

	public void setDjsjend(Date djsjend) {
		this.djsjend = djsjend;
	}

}
