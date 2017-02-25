package com.wondersgroup.kaoshi.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.paper.dao.EPapersDAO;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.bo.UploadGradeDTO;
import com.wondersgroup.kaoshi.bo.UploadToYth;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EUser;

public class UploadGradeToJdzxAction extends AbstractPageNavAction{

	private EPapers epaper = new EPapers();
	private EUser eUser = new EUser();
	private Date sjKksjbegin;
	private String flag;
	private EPapersService epapersService;
	private List list;
	private String sjMc;
	private String sjid;
	private String colorCheck;
	
	@Override
	public String doAcion() throws Exception {
		this.pageReturn = this.epapersService.findGradeForUploadToJdzx(pageTool, 
				new Long(2), epaper, eUser, sjKksjbegin, flag);
		this.list = this.pageReturn.getReturnList();
		this.colorCheck = new EPapersDAO().checkUser();
		if(list.size()!=0){
			UploadGradeDTO o = (UploadGradeDTO) this.pageReturn.getReturnList().get(0);
			this.sjMc = o.getSjMc();
			this.sjid = String.valueOf(o.getSjId());
		}
		return SUCCESS;
	}

	public void uploadToJdzxByWs(){
		try {
			sjMc = java.net.URLDecoder.decode(sjMc , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		AcegiUtil acegiUtil = new AcegiUtil();
		String name= ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser().getRealname();
		String rtMsg = this.epapersService.uploadToJdzxByWs(sjid, sjMc, name);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(rtMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public String getSjid() {
		return sjid;
	}

	public void setSjid(String sjid) {
		this.sjid = sjid;
	}

}
