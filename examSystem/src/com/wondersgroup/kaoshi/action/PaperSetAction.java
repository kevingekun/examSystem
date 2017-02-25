package com.wondersgroup.kaoshi.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.wondersgroup.kaoshi.service.PaperSetService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;

public class PaperSetAction extends AbstractPageNavAction {

	private String sjmc;
	private String tkid;
	private String sjzf;
	private String sjType;
	private PaperSetService paperSetService;
	
	@Override
	public String doAcion() throws Exception {
		return null;
	}
	
	public void paperAdd() {
		String b = paperSetService.paperAdd(sjmc, tkid, sjzf, sjType);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(String.valueOf(b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String getSjmc() {
		return sjmc;
	}
	public void setSjmc(String sjmc) {
		this.sjmc = sjmc;
	}
	public String getTkid() {
		return tkid;
	}
	public void setTkid(String tkid) {
		this.tkid = tkid;
	}
	public String getSjzf() {
		return sjzf;
	}
	public void setSjzf(String sjzf) {
		this.sjzf = sjzf;
	}

	public String getSjType() {
		return sjType;
	}

	public void setSjType(String sjType) {
		this.sjType = sjType;
	}

	public void setPaperSetService(PaperSetService paperSetService) {
		this.paperSetService = paperSetService;
	}

}
