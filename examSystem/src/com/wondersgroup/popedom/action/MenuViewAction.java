package com.wondersgroup.popedom.action;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.EUserMenus;
import com.wondersgroup.popedom.service.EUserMenusService;

public class MenuViewAction extends AbstractAction {
	private EUserMenusService euserMenusService;
	public void setEuserMenusService(EUserMenusService euserMenusService) {
		this.euserMenusService = euserMenusService;
	}
	/*
	 * 页面中要用到的属性
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	private List<EUserMenus> eusermenus;
	
	private String userStar;
	
	private String logOutUrl;
	
	@Override
	public String execute() throws Exception {
		//得到人员
		AcegiUtil acegiUtil=new AcegiUtil();
		EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
		if(("1").equals(user.getUserstar())){
			userStar = "1";
		}else{
			userStar = "";
		}
		this.eusermenus=this.euserMenusService.findTopMenus(user.getId(), "0", new Long(2), new Long(1));
		return SUCCESS;
	}
	
	
	private String parentid;
	
	/**
	 * 
	 *
	 * <p>Description:左边的菜单显示 </p>
	 * 
	 * Created by [www] [Nov 12, 2009]
	 * Midified by [修改人] [修改时间]
	 *
	 * @return
	 * @throws Exception
	 */
	public String leftMenuView() throws Exception{
		//得到人员
		AcegiUtil acegiUtil=new AcegiUtil();
		EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
		if(("1").equals(user.getUserstar())){
			userStar = "1";
		}else{
			userStar = "";
		}
		//找到二级菜单
		this.eusermenus=this.euserMenusService.findTopMenus(user.getId(), parentid, new Long(2), new Long(1));
		//找到三级菜单,因为是三级菜单也受到权限控制，所有，要重新查找一遍，以防止把没有权限的也显示出来
		Iterator<EUserMenus> it=this.eusermenus.iterator();
		while(it.hasNext()){
			EUserMenus euserMenus=it.next();
//			euserMenus.setChildMenus(null);
			List<EUserMenus> childs=this.euserMenusService.findTopMenus(user.getId(),euserMenus.getId(),new Long(2),new Long(1));
			euserMenus.setChildMenusView(childs);
		}
		return SUCCESS;
	}

	public String logOut(){
		AcegiUtil acegiUtil=new AcegiUtil();
		EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
		getRequest().getSession().removeAttribute("ACEGI_SECURITY_LAST_USERNAME");
		getRequest().getSession().removeAttribute("ACEGI_SECURITY_CONTEXT");
		getRequest().getSession().invalidate();
		Enumeration enumeration =  getRequest().getSession().getAttributeNames();
		while(enumeration.hasMoreElements()){
			String s = (String) enumeration.nextElement();
			System.out.print(s+"::::");
			System.out.println(getRequest().getSession().getAttribute(s));
		}
		if("1".equals(user.getUserstar())){
			this.logOutUrl="/acegilogin.jsp";
		}else{
			this.logOutUrl="/adminlogin.jsp";
		}
		return SUCCESS;
	}
	
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public List<EUserMenus> getEusermenus() {
		return eusermenus;
	}

	public void setEusermenus(List<EUserMenus> eusermenus) {
		this.eusermenus = eusermenus;
	}

	public String getUserStar() {
		return userStar;
	}
	public void setUserStar(String userStar) {
		this.userStar = userStar;
	}

	public String getLogOutUrl() {
		return logOutUrl;
	}

	public void setLogOutUrl(String logOutUrl) {
		this.logOutUrl = logOutUrl;
	}

}
