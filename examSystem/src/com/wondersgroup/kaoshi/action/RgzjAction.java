package com.wondersgroup.kaoshi.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.question.model.EBusinesstype;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestiontype;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.service.EQuestionsService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractPageAction;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.popedom.bo.EUser;

public class RgzjAction extends AbstractPageAction {
	// �����Ծ�����
	private String sjMc;
	private long sjDjsx;
	private Date sjKksj;
	private Date sjYxqjzsj;
	private Double sjzf;
	private String model;
	private String sjLjcf;//立即出分
	private String sjBhgfs;
	// �Ծ�״̬
	private List sjzt;
	private String paperType;
	private String toUserId;

	// �Ծ��id
	private String paperid;

	// ���е���������
	private List equestiontypes;

	// ���е�ҵ������
	private List equestionBuTypes;

	private EPapersService epapersService;

	// ��ѯ�Ծ��ʵ���l�ӱ�
	private EpaperquestionsService epaperquestionsService;

	private EQuestionsService equestionsService;

	private EPapers epaper;

	private List equestions;

	// �Ծ��ѯ���
	private String stTg;

	private long stLx = -1;

	private long stYwlx = -1;

	private String[] questionids;

	private List epaperquestions;

	public List getEpaperquestions() {
		return epaperquestions;
	}

	public void setEpaperquestions(List epaperquestions) {
		this.epaperquestions = epaperquestions;
	}

	@Override
	public String doAcion() throws Exception {
		EQuestions equestions = new EQuestions();
		// ���ò�ѯ���
		equestions.setStTg(this.stTg);
		EBusinesstype ebusinesstype = new EBusinesstype();
		ebusinesstype.setPriority(this.stYwlx);
		equestions.setEbusinesstype(ebusinesstype);
		EQuestiontype equestiontype = new EQuestiontype();
		equestiontype.setPriority(this.stLx);
		equestions.setEquestiontype(equestiontype);

		PageReturn pr = this.equestionsService.findQuetionsBytiaojian(
				this.pageTool, equestions);

		this.equestions = pr.getReturnList();

		this.setFY(pr);

		// �ҵ����е�����
		this.equestiontypes = this.epaperquestionsService.findEQuestiontype();

		this.equestionBuTypes = this.epaperquestionsService.findallbuType();

		this.epaper = this.epapersService.getEPapersById(this.paperid);

		return SUCCESS;
	}

	public String addepaper() {
		// �õ���Ա
		AcegiUtil acegiUtil = new AcegiUtil();
		EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();

		this.epaper = new EPapers();
		this.epaper.setSjMc(sjMc);
		this.epaper.setSjDjsx(sjDjsx);
		this.epaper.setSjKksj(sjKksj);
		this.epaper.setSjYxqjzsj(sjYxqjzsj);
		this.epaper.setSjZjrid(user.getUsername());
		this.epaper.setSjZjsj(new Date());
		this.epaper.setSjZf(sjzf);
		//this.epaper.setModel(model);
		this.epaper.setSjLjcf(sjLjcf);
		this.epaper.setSjZt(4);
		this.epaper.setSjBhgfs(Double.valueOf(sjBhgfs));
		this.epaper.setPaperType(paperType);
		this.epaper.setToUserId(toUserId);

		this.epapersService.addEpaper(this.epaper);
		this.paperid = new Long(this.epaper.getSjId()).toString();
		/*
		 * ��ҳ������ʾ���е�����
		 */
		// �ҵ����е�����
		this.equestiontypes = this.epaperquestionsService.findEQuestiontype();
		/*
		 * �ҵ�����Ծ������
		 */
		epaperquestions = epaperquestionsService.findAllById(paperid);
		return SUCCESS;
	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:�Ծ��������
	 * </p>
	 * 
	 * Created by [www] [Aug 27, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @return
	 */
	public String addquestions() {
		// this.epaperquestionsService.addQuestion(questionids, paperid);
		return SUCCESS;
	}

	public void setEpapersService(EPapersService epapersService) {
		this.epapersService = epapersService;
	}

	public List getSjzt() {
		return sjzt;
	}

	public void setSjzt(List sjzt) {
		this.sjzt = sjzt;
	}

	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public long getSjDjsx() {
		return sjDjsx;
	}

	public void setSjDjsx(long sjDjsx) {
		this.sjDjsx = sjDjsx;
	}

	public Date getSjKksj() {
		return sjKksj;
	}

	public void setSjKksj(Date sjKksj) {
		this.sjKksj = sjKksj;
	}

	public Date getSjYxqjzsj() {
		return sjYxqjzsj;
	}

	public void setSjYxqjzsj(Date sjYxqjzsj) {
		this.sjYxqjzsj = sjYxqjzsj;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public List getEquestiontypes() {
		return equestiontypes;
	}

	public void setEquestiontypes(List equestiontypes) {
		this.equestiontypes = equestiontypes;
	}

	public List getEquestionBuTypes() {
		return equestionBuTypes;
	}

	public void setEquestionBuTypes(List equestionBuTypes) {
		this.equestionBuTypes = equestionBuTypes;
	}

	public EpaperquestionsService getEpaperquestionsService() {
		return epaperquestionsService;
	}

	public void setEquestionsService(EQuestionsService equestionsService) {
		this.equestionsService = equestionsService;
	}

	public List getEquestions() {
		return equestions;
	}

	public void setEquestions(List equestions) {
		this.equestions = equestions;
	}

	public EPapers getEpaper() {
		return epaper;
	}

	public void setEpaper(EPapers epaper) {
		this.epaper = epaper;
	}

	public String getStTg() {
		return stTg;
	}

	public void setStTg(String stTg) {
		this.stTg = stTg;
	}

	public long getStLx() {
		return stLx;
	}

	public void setStLx(long stLx) {
		this.stLx = stLx;
	}

	public long getStYwlx() {
		return stYwlx;
	}

	public void setStYwlx(long stYwlx) {
		this.stYwlx = stYwlx;
	}

	public String[] getQuestionids() {
		return questionids;
	}

	public void setQuestionids(String[] questionids) {
		this.questionids = questionids;
	}

	public Double getSjzf() {
		return sjzf;
	}

	public void setSjzf(Double sjzf) {
		this.sjzf = sjzf;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getSjLjcf() {
		return sjLjcf;
	}

	public void setSjLjcf(String sjLjcf) {
		this.sjLjcf = sjLjcf;
	}

	public String getSjBhgfs() {
		return sjBhgfs;
	}

	public void setSjBhgfs(String sjBhgfs) {
		this.sjBhgfs = sjBhgfs;
	}

}
