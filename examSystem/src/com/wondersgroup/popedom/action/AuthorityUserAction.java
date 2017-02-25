package com.wondersgroup.popedom.action;

import java.util.List;

import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.popedom.bo.EAuthority;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.service.EAuthorityService;
import com.wondersgroup.popedom.service.EUserService;

public class AuthorityUserAction extends AbstractAction{

	private EAuthorityService eauthorityService;
	private EUserService  euserService;

	//传递参数
	private String userid;
	private String eauthorityides;
	
	//页面显示
	private List<EAuthority> eauthorities;
	private EUser euser;
	public String execute() throws Exception {
		euser=this.euserService.load(new Long(userid));
		//找到所有的角色
		this.eauthorities=this.eauthorityService.findAllauthority();
		return SUCCESS;
	}
	
	
	public String update() throws Exception{
		String[] authids=this.eauthorityides.split(",");
		this.euserService.updateUserAuth(new Long(userid), authids);
		
		euser=this.euserService.load(new Long(userid));
		//找到所有的角色
		this.eauthorities=this.eauthorityService.findAllauthority();
		return SUCCESS;
	}
	public void setEauthorityService(EAuthorityService eauthorityService) {
		this.eauthorityService = eauthorityService;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public EUser getEuser() {
		return euser;
	}
	public void setEuser(EUser euser) {
		this.euser = euser;
	}
	public void setEuserService(EUserService euserService) {
		this.euserService = euserService;
	}
	public List<EAuthority> getEauthorities() {
		return eauthorities;
	}
	public void setEauthorities(List<EAuthority> eauthorities) {
		this.eauthorities = eauthorities;
	}


	public String getEauthorityides() {
		return eauthorityides;
	}


	public void setEauthorityides(String eauthorityides) {
		this.eauthorityides = eauthorityides;
	}

}
