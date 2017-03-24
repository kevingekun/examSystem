package com.wondersgroup.popedom.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.kaoshi.bo.EKaoshi;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EAuthority;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.ExamLimitKs;
import com.wondersgroup.popedom.service.AddExamineeService;
import com.wondersgroup.popedom.service.EAuthorityService;
import com.wondersgroup.popedom.service.EUserService;

public class UserAction extends AbstractPageNavAction {
	private EUserService  euserService;
	private EAuthorityService eauthorityService;
	private AddExamineeService addexamineeService;
	public void setEauthorityService(EAuthorityService eauthorityService) {
		this.eauthorityService = eauthorityService;
	}
	public void setEuserService(EUserService euserService) {
		this.euserService = euserService;
	}
	public void setAddexamineeService(AddExamineeService addexamineeService) {
		this.addexamineeService = addexamineeService;
	}

	//显示被屏蔽的考试的人员
	private List<EKaoshi> eKaoshi;
	
	private String[] deleteeKaoshi;
	private List<ExamLimitKs>staff;
	
	
	private String vpass;
	private String npass;
	//页面显示
	
	public List<ExamLimitKs> getStaff() {
		return staff;
	}
	public void setStaff(List<ExamLimitKs> staff) {
		this.staff = staff;
	}
	private HashMap userState;
	private List<EAuthority> eauthorities;
	private List users;
	
	private List<Object[]> addlist;
	
	//查询条件
	private String username;
	private String realname;
	private Byte enabled;
	private String userstar;
	@Override
	public String doAcion() throws Exception {
		EUser eu=new EUser();
		eu.setUsername(username);
		eu.setRealname(realname);
		eu.setUserstar(userstar);
		//eu.setEnabled(enabled);
		this.pageReturn=this.euserService.findAllUsers(this.pageTool,eu);
		this.users=this.pageReturn.getReturnList();
		this.addlist = this.addexamineeService.queryRole();
		//找到所有的角色
		eauthorities=this.eauthorityService.findAllauthority();
		return SUCCESS;
	}
	
	/**
	 * 删除用户
	 */
	private String userId;
	public String deleteUser() throws Exception{
		try{
			this.euserService.deleteUser(new Long(userId));
		}catch (Exception e) {
			this.getRequest().setAttribute("wrongMessage", "用户删除错误，可能用户与其他数据相关联！");
			this.getRequest().setAttribute("backUrl", "userview.action");
			return ERROR;
		}
		return SUCCESS;
	}
	/**
	 * 修改用户
	 */
	public void updateUser(){
		AcegiUtil acegiUtil = new AcegiUtil();
		long id = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser().getId();
		EUser euser=this.euserService.load(new Long(id));
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(euser.getPassword().equals(vpass)){
				euser.setPassword(npass);
				this.euserService.updateUser(euser);
				response.getWriter().write("修改成功！");
			}else{
				response.getWriter().write("原始密码错误！");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * 检查原始密码
	 */
	public void checkPassword(){
		AcegiUtil acegiUtil = new AcegiUtil();
		long id = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser().getId();
		EUser euser=this.euserService.load(new Long(id));
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(vpass.equals(euser.getPassword())){
				response.getWriter().write("1");
			}else{
				response.getWriter().write("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 得到被屏蔽的考试的人员
	 */
	public String getallkaoshi() throws Exception{
     this.pageReturn= this.euserService.getallkaoshi();
     this.staff=this.pageReturn.getReturnList();
	 return SUCCESS;
	}
	/**
	 * 释放被屏蔽的考试的人员
	 */
	public String deleteeKaoshi() throws Exception{
		try{
			this.euserService.deleteeKaoshi(deleteeKaoshi);
		}catch(Exception e){
			e.printStackTrace();
			
			this.getRequest().setAttribute("wrongMessage", "不能取消，抱歉");
			this.getRequest().setAttribute("backUrl", "kaoshi.action");
			e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List getUsers() {
		return users;
	}
	public void setUsers(List users) {
		this.users = users;
	}
	public HashMap getUserState() {
		return userState;
	}
	public void setUserState(HashMap userState) {
		this.userState = userState;
	}
	public List<EAuthority> getEauthorities() {
		return eauthorities;
	}
	public void setEauthorities(List<EAuthority> eauthorities) {
		this.eauthorities = eauthorities;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Byte getEnabled() {
		return enabled;
	}
	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}
	public List getEKaoshi() {
		return eKaoshi;
	}
	public void setEKaoshi(List kaoshi) {
		eKaoshi = kaoshi;
	}
	public String[] getDeleteeKaoshi() {
		return deleteeKaoshi;
	}
	public void setDeleteeKaoshi(String[] deleteeKaoshi) {
		this.deleteeKaoshi = deleteeKaoshi;
	}
	public String getUserstar() {
		return userstar;
	}
	public void setUserstar(String userstar) {
		this.userstar = userstar;
	}
	public String getNpass() {
		return npass;
	}
	public void setNpass(String npass) {
		this.npass = npass;
	}
	public String getVpass() {
		return vpass;
	}
	public void setVpass(String vpass) {
		this.vpass = vpass;
	}
	public List<Object[]> getAddlist() {
		return addlist;
	}
	public void setAddlist(List<Object[]> addlist) {
		this.addlist = addlist;
	}

}
