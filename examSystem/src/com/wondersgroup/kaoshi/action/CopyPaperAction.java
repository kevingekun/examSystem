package com.wondersgroup.kaoshi.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EUser;

public class CopyPaperAction extends AbstractPageNavAction {
	// �Ծ�id
	private String paperid;
	private EPapers pager;

	// ʱ��
	private Date sjKksj;
	private Date sjYxqjzsj;
	private String model;

	private String newpaperid;

	// �Ծ�״̬
	private String paperState;
	
	private String preState;//页面原始试卷状态
	private String sjMc;

	private String advice;

	// ��ѯ�Ծ�
	private EPapersService epaperservice;
	// ��ѯ�Ծ��ʵ���l�ӱ�
	private EpaperquestionsService epaperquestionsService;

	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}

	public String execute() throws Exception {
		// ��ѯ�Ծ�
		EPapers epapers = epaperservice.getEPapersById(paperid);
		this.pager = epapers;
		return SUCCESS;
	}

	public String addPaper() {
		EPapers newepaper = new EPapers();
		newepaper.setEpaperquestionses(null);
		newepaper.setSjMc(this.pager.getSjMc());
		newepaper.setSjZf(this.pager.getSjZf());
		newepaper.setSjBhgfs(this.pager.getSjBhgfs());
		newepaper.setSjDjsx(this.pager.getSjDjsx());
		newepaper.setSjKksj(sjKksj);
		newepaper.setModel(model);
		newepaper.setSjYxqjzsj(sjYxqjzsj);
		newepaper.setSjZjsj(new Date());
		AcegiUtil acegiUtil = new AcegiUtil();
		EUser usre = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
		newepaper.setSjZjrid(usre.getUsername());
		this.epaperservice.addEpaper(newepaper);
		newpaperid = new Long(newepaper.getSjId()).toString();
		this.epaperquestionsService.addPaperQuestions(paperid, newpaperid);
		return SUCCESS;
	}

	public String changeState() {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext()); 
		try {
			 Map<Long, Long> map = this.epaperservice.updateState(paperid, paperState);
			 this.epaperservice.calculateScore(map, ctx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String papercheck() {
		// 得到人员
		AcegiUtil acegiUtil = new AcegiUtil();
		EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
		this.epaperservice.updatecheckpaper(paperid, paperState, advice, user
				.getUsername().toString());
		return SUCCESS;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public void setEpaperservice(EPapersService epaperservice) {
		this.epaperservice = epaperservice;
	}

	public EPapers getPager() {
		return pager;
	}

	public void setPager(EPapers pager) {
		this.pager = pager;
	}

	public String getNewpaperid() {
		return newpaperid;
	}

	public void setNewpaperid(String newpaperid) {
		this.newpaperid = newpaperid;
	}

	public String getPaperState() {
		return paperState;
	}

	public void setPaperState(String paperState) {
		this.paperState = paperState;
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

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPreState() {
		return preState;
	}

	public void setPreState(String preState) {
		this.preState = preState;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	@Override
	public String doAcion() throws Exception {
		return null;
	}

}
