package com.wondersgroup.kaoshi.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EUser;

public class PaperListAction extends AbstractPageNavAction {
	// ��ѯ���
	private EPapers epaper = new EPapers();
	private EUser eUser = new EUser();
	private Date sjKksjbegin;
	private Date sjKksjend;
	private Date sjZjsjbegin;
	private Date sjZjsjend;
	private String flag;
	private EPapersService epapersService;
	private HashMap sjzt;

	public HashMap getSjzt() {
		return sjzt;
	}

	public void setSjzt(HashMap sjzt) {
		this.sjzt = sjzt;
	}

	public List getEpapers() {
		return epapers;
	}

	public void setEpapers(List epapers) {
		this.epapers = epapers;
	}

	public void setEpapersService(EPapersService epapersService) {
		this.epapersService = epapersService;
	}

	private List epapers;

	@Override
	public String doAcion() throws Exception {
		this.pageReturn = this.epapersService.findpaperbySjzt(this.pageTool,
				new Long(2), this.epaper, sjKksjbegin, sjZjsjbegin, flag);
		this.epapers = this.pageReturn.getReturnList();
		//this.epapersService.findgrade(pageTool, new Long(2), epaper, eUser, sjKksjbegin, flag);
		return SUCCESS;
	}

	public EPapers getEpaper() {
		return epaper;
	}

	public void setEpaper(EPapers epaper) {
		this.epaper = epaper;
	}

	public Date getSjKksjbegin() {
		return sjKksjbegin;
	}

	public void setSjKksjbegin(Date sjKksjbegin) {
		this.sjKksjbegin = sjKksjbegin;
	}

	public Date getSjKksjend() {
		return sjKksjend;
	}

	public void setSjKksjend(Date sjKksjend) {
		this.sjKksjend = sjKksjend;
	}

	public Date getSjZjsjbegin() {
		return sjZjsjbegin;
	}

	public void setSjZjsjbegin(Date sjZjsjbegin) {
		this.sjZjsjbegin = sjZjsjbegin;
	}

	public Date getSjZjsjend() {
		return sjZjsjend;
	}

	public void setSjZjsjend(Date sjZjsjend) {
		this.sjZjsjend = sjZjsjend;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public EUser geteUser() {
		return eUser;
	}

	public void seteUser(EUser eUser) {
		this.eUser = eUser;
	}

}
