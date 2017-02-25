package com.wondersgroup.kaoshi.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;

import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractAction;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PreviewPaperAction extends AbstractAction {

	private String fenshu;

	private EPapers pager;
	
	private String gzmc;
	private String gzdj;

	private String paperQuenId;

	// 试卷标识
	private String paperid;

	// 所有的试题类型
	private List equestiontypes;

	// 查询试卷
	private EPapersService epaperservice;

	// 试卷试题连接表
	private List epaperquestions;

	// 查询试卷和实体的连接表
	private EpaperquestionsService epaperquestionsService;
	
	private String wrongMessage;
	private String backUrl;
	
	public String execute() throws Exception {
		// 查询试卷
		EPapers epapers = epaperservice.getEPapersById(paperid);
		this.pager = epapers;

		// 查询试卷的试题
		epaperquestions = epaperquestionsService.findAllById(paperid);

		// 找到所有的类型
		if ("4".equals(epapers.getPaperType())) {
			this.equestiontypes = this.epaperquestionsService
					.findEQuestiontypeByPaperType();
		} else {
			this.equestiontypes = this.epaperquestionsService
					.findEQuestiontype();
		}

		double fenshuww = this.epaperquestionsService
				.findPaperQuestionFensu(paperid);
		this.fenshu = String.valueOf(fenshuww);

		return SUCCESS;

	}

	public String deleteQuestion() throws Exception {
		// 先删除这个试题

		this.epaperquestionsService.removeById(paperQuenId);

		// paperid=this.paperid;

		// 再重新查找
		return this.execute();
	}

	public String deletepaper() throws Exception {
		// 删除
		boolean b = this.epaperquestionsService.checkPaperRelate(paperid);
		if (b) {
			wrongMessage = "试卷已关联考生，请取消关联后再删除！";
			backUrl = "papersServlet?actionType=query&sjZt=-1";
			return "error";
		}else{
			this.epaperquestionsService.deleteById(paperid);
			return this.execute();
		}
	}

	public EPapers getPager() {
		return pager;
	}

	public void setPager(EPapers pager) {
		this.pager = pager;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public EPapersService getEpaperservice() {
		return epaperservice;
	}

	public void setEpaperservice(EPapersService epaperservice) {
		this.epaperservice = epaperservice;
	}

	public List getEpaperquestions() {
		return epaperquestions;
	}

	public void setEpaperquestions(List epaperquestions) {
		this.epaperquestions = epaperquestions;
	}

	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}

	public List getEquestiontypes() {
		return equestiontypes;
	}

	public void setEquestiontypes(List equestiontypes) {
		this.equestiontypes = equestiontypes;
	}

	public String getPaperQuenId() {
		return paperQuenId;
	}

	public void setPaperQuenId(String paperQuenId) {
		this.paperQuenId = paperQuenId;
	}

	public String getFenshu() {
		return fenshu;
	}

	public void setFenshu(String fenshu) {
		this.fenshu = fenshu;
	}

	public String getGzmc() {
		return gzmc;
	}

	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
	}

	public String getGzdj() {
		return gzdj;
	}

	public void setGzdj(String gzdj) {
		this.gzdj = gzdj;
	}

	public String getWrongMessage() {
		return wrongMessage;
	}

	public void setWrongMessage(String wrongMessage) {
		this.wrongMessage = wrongMessage;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
   
}
