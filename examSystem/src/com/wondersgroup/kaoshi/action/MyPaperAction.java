package com.wondersgroup.kaoshi.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.service.EAnswerpaperService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EUser;

public class MyPaperAction extends AbstractPageNavAction {
	private EPapers epaper;

	private HashMap sjzt;
	// �ҵ��Ծ��id
	private String myPaperId;

	private List mypapers;

	private EAnswerpaper eqnswerpaper;

	// ����service
	private EAnswerpaperService eanswerpaperService;

	// ���е���������
	private List equestiontypes;

	// ��ѯ�Ծ��ʵ���l�ӱ�
	private EpaperquestionsService epaperquestionsService;

	// ��Ŀ
	private List eanswerquestionses;

	public List getEanswerquestionses() {
		return eanswerquestionses;
	}

	public void setEanswerquestionses(List eanswerquestionses) {
		this.eanswerquestionses = eanswerquestionses;
	}

	public void setEanswerpaperService(EAnswerpaperService eanswerpaperService) {
		this.eanswerpaperService = eanswerpaperService;
	}

	public String doAcion() throws Exception {
		AcegiUtil acegiUtil = new AcegiUtil();
		EUser usreid = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
		if (this.epaper == null) {
			this.epaper = new EPapers();
			this.epaper.setSjZt(-1);
		}
		this.pageReturn = this.eanswerpaperService.findmypaper(pageTool, usreid
				.getUsername().toString(), this.epaper);
		this.mypapers = pageReturn.getReturnList();
		return SUCCESS;
	}

	public String mypaperquestions() throws Exception {
		this.eqnswerpaper = this.eanswerpaperService.load(new Long(
				this.myPaperId));
		eanswerquestionses = new ArrayList();
		this.eanswerquestionses.addAll(eqnswerpaper.getEanswerquestionses());

		// �ҵ����е�����
		this.equestiontypes = this.epaperquestionsService
				.findEQuestiontypeAll();

		return SUCCESS;
	}

	public List getMypapers() {
		return mypapers;
	}

	public void setMypapers(List mypapers) {
		this.mypapers = mypapers;
	}

	public String getMyPaperId() {
		return myPaperId;
	}

	public void setMyPaperId(String myPaperId) {
		this.myPaperId = myPaperId;
	}

	public EAnswerpaper getEqnswerpaper() {
		return eqnswerpaper;
	}

	public void setEqnswerpaper(EAnswerpaper eqnswerpaper) {
		this.eqnswerpaper = eqnswerpaper;
	}

	public List getEquestiontypes() {
		return equestiontypes;
	}

	public void setEquestiontypes(List equestiontypes) {
		this.equestiontypes = equestiontypes;
	}

	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}

	public HashMap getSjzt() {
		return sjzt;
	}

	public void setSjzt(HashMap sjzt) {
		this.sjzt = sjzt;
	}

	public EPapers getEpaper() {
		return epaper;
	}

	public void setEpaper(EPapers epaper) {
		this.epaper = epaper;
	}

}
