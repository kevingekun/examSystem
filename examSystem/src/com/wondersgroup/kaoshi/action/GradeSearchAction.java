package com.wondersgroup.kaoshi.action;

import java.util.Date;
import java.util.List;

import com.wondersgroup.falcon.paper.dao.EPapersDAO;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.bo.UploadGradeDTO;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EUser;

public class GradeSearchAction extends AbstractPageNavAction{

	private EPapers epaper = new EPapers();
	private EUser eUser = new EUser();
	private Date sjKksjbegin;
	private String flag;
	private EPapersService epapersService;
	private List list;
	private String sjMc;
	private String colorCheck;
	private String pcid;
	
	@Override
	public String doAcion() throws Exception {
		this.pageReturn = this.epapersService.gradeSearch(pageTool,
				new Long(2), epaper, eUser, sjKksjbegin, flag,pcid);
		this.list = this.pageReturn.getReturnList();
		this.colorCheck = new EPapersDAO().checkUser();
		if(list.size()!=0){
			UploadGradeDTO o = (UploadGradeDTO) this.pageReturn.getReturnList().get(0);
			this.sjMc = o.getSjMc();
		}
		return SUCCESS;
	}
	
	public String toindex(){
		return SUCCESS;
	}
	
	public EUser geteUser() {
		return eUser;
	}

	public void seteUser(EUser eUser) {
		this.eUser = eUser;
	}

	public Date getSjKksjbegin() {
		return sjKksjbegin;
	}

	public void setSjKksjbegin(Date sjKksjbegin) {
		this.sjKksjbegin = sjKksjbegin;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setEpapersService(EPapersService epapersService) {
		this.epapersService = epapersService;
	}

	public EPapers getEpaper() {
		return epaper;
	}

	public void setEpaper(EPapers epaper) {
		this.epaper = epaper;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public String getColorCheck() {
		return colorCheck;
	}

	public void setColorCheck(String colorCheck) {
		this.colorCheck = colorCheck;
	}

	public String getPcid() {
		return pcid;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}
	
	

}
