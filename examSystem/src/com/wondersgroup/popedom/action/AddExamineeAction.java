package com.wondersgroup.popedom.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.Users_yth;
import com.wondersgroup.popedom.service.AddExamineeService;

public class AddExamineeAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private EUser euser;
	private AddExamineeService addexamineeService;
	private String role;
	private String examroomid;
	private String username;
	List<EUser> list;
	private List<Object[]> addlist;
	private List<UserTeam> uTeams;

	public String addexaminee() {
		byte b = 1;
		euser.setAgentId("3005");
		euser.setSex("0");
		euser.setStatus(b);
		euser.setUserstar("0");
		euser.setUserstar("0");
		euser.setEnabled(new Byte("1"));
		role = getRequest().getParameter("role");
		addexamineeService.addexaminee(euser, role);
		/*if("263".equals(role)){
			Users_yth users_yth = new Users_yth();
			try {
				BeanUtils.copyProperties(users_yth, euser);
				users_yth.setUser_id(euser.getId());
				saveUserYth(users_yth,role);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}*/
		return SUCCESS;
	}
	public void saveUserYth(Users_yth user,String role){
		addexamineeService.addexamineeYth(user, role);
	}

	public void queryuser() {
		username = getRequest().getParameter("username");
		list = addexamineeService.queryuser(username);
		HttpServletResponse response = ServletActionContext.getResponse();
		if (list.size() == 0) {
			try {
				response.getWriter().write("0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public String getrole() {
		this.addlist = this.addexamineeService.queryRole();
		this.uTeams = this.addexamineeService.geTeams();
		return SUCCESS;
	}

	@Override
	public String execute() {
		return SUCCESS;

	}

	public EUser getEuser() {
		return euser;
	}

	public void setEuser(EUser euser) {
		this.euser = euser;
	}

	public AddExamineeService getAddexamineeService() {
		return addexamineeService;
	}

	public void setAddexamineeService(AddExamineeService addexamineeService) {
		this.addexamineeService = addexamineeService;
	}

	public String getExamroomid() {
		return examroomid;
	}

	public void setExamroomid(String examroomid) {
		this.examroomid = examroomid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<EUser> getList() {
		return list;
	}

	public void setList(List<EUser> list) {
		this.list = list;
	}

	public List<Object[]> getAddlist() {
		return addlist;
	}

	public void setAddlist(List<Object[]> addlist) {
		this.addlist = addlist;
	}

	public List<UserTeam> getuTeams() {
		return uTeams;
	}

	public void setuTeams(List<UserTeam> uTeams) {
		this.uTeams = uTeams;
	}
	

}
