package com.wondersgroup.popedom.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.popedom.bo.EAuthority;
import com.wondersgroup.popedom.bo.EUserMenus;
import com.wondersgroup.popedom.service.EAuthorityService;
import com.wondersgroup.popedom.service.EUserMenusService;

public class UserMeanuTreeView extends AbstractAction {
	private EUserMenusService euserMenusService;
	private EAuthorityService eauthorityService;

	
	//参数
	private String parentid;
	private String authorityid;
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	//页面显示
	
	private List<EUserMenus> emenus;
	private EAuthority eauthority;

	public EAuthority getEauthority() {
		return eauthority;
	}

	public void setEauthority(EAuthority eauthority) {
		this.eauthority = eauthority;
	}

	public List<EUserMenus> getEmenus() {
		return emenus;
	}

	public void setEmenus(List<EUserMenus> emenus) {
		this.emenus = emenus;
	}

	/**
	 * 给角色赋菜单
	 */
	@Override
	public String execute() throws Exception {
		//得到角色
		EAuthority ea=this.eauthorityService.loadEAuthority(new Long(authorityid));
		this.eauthority=ea;
		this.emenus=this.euserMenusService.findMenusByParent("0");
		return SUCCESS;
	}
	
	private String usermenusids;
	
	public String addUserMenuToAuthority() throws Exception{
		
		//给角色增加菜单
		String[] uerMenuids=this.usermenusids.split(",");
		this.eauthorityService.updateEAuthorities(authorityid, uerMenuids);
		
		//得到角色
		EAuthority ea=this.eauthorityService.loadEAuthority(new Long(authorityid));
		this.eauthority=ea;
		this.emenus=this.euserMenusService.findMenusByParent("0");
		return SUCCESS;
	}
	

	public String getUsermenusids() {
		return usermenusids;
	}

	public void setUsermenusids(String usermenusids) {
		this.usermenusids = usermenusids;
	}

	public void setEuserMenusService(EUserMenusService euserMenusService) {
		this.euserMenusService = euserMenusService;
	}

	public String getAuthorityid() {
		return authorityid;
	}

	public void setAuthorityid(String authorityid) {
		this.authorityid = authorityid;
	}

	public void setEauthorityService(EAuthorityService eauthorityService) {
		this.eauthorityService = eauthorityService;
	}
	
	
}
