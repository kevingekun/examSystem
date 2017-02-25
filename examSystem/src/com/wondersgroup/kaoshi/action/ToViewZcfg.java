package com.wondersgroup.kaoshi.action;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.service.EUserService;

public class ToViewZcfg extends AbstractAction {
	
	private EUserService euserService;
	
	public void setEuserService(EUserService euserService) {
		this.euserService = euserService;
	}
	
	public String cname;
	
	public String docid;

	public String toViewZcfg(){
		AcegiUtil acegiUtil=new AcegiUtil();
		EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
		EUser euser=this.euserService.load(user.getId());
		this.getRequest().setAttribute("euser", euser);
		return SUCCESS;
	}
	public String toViewWdzl(){
		return SUCCESS;
	}
	
	
	public String toViewNode(){
		
		return SUCCESS;
	}
	
	public String toViewXxzl(){
		
		return SUCCESS;
	}
	
	public String toViewBszn(){
		return SUCCESS;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public String getDocid() {
		return docid;
	}


	public void setDocid(String docid) {
		this.docid = docid;
	}
}
