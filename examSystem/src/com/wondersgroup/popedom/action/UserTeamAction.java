/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.popedom.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.wondersgroup.falcon.Util.RequestUtils;
import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.service.EUserTeamService;

/**
 * @author Administrator
 *
 */
public class UserTeamAction extends AbstractPageNavAction {
	
	private EUserTeamService teamservice;
	private List<EUser> userlist;
	private String teamid;
	private UserTeam userteam;

	//更新接受参数
	private String teamId;
	private String contactph;
	private String teamaddress;
	private String teamName;
	private String numstart;
	private String numend;
	private String count;
	/* (non-Javadoc)
	 * @see com.wondersgroup.kaoshi.util.AbstractPageNavAction#doAcion()
	 */
	@Override
	public String doAcion() throws Exception {
		// TODO Auto-generated method stub
		String remoteAddr = ServletActionContext.getRequest().getRemoteAddr(); 
		System.out.println("获取用户ip地址"+remoteAddr);
		this.userlist=this.teamservice.queryUserByid(this.teamid);
		System.out.println("查询值：" + this.userlist);
		return SUCCESS;
	}
	 
	public String queryTeamByid() throws Exception{
		this.userteam=this.teamservice.queryTeamByid(this.teamid);
		if(this.userteam.getPteamid()==0){
			return ERROR;
		}else{
			return SUCCESS;
		}
	}
	public String updateTeam(){
		UserTeam uteam=new UserTeam();
		uteam.setContactph(this.contactph);
		uteam.setTeamaddress(this.teamaddress);
		uteam.setTeamName(this.teamName);
		uteam.setTeamId(Long.valueOf(this.teamId));
		uteam.setCount(this.count);
		uteam.setNumend(this.numend);
		uteam.setNumstart(this.numstart);
		this.userteam=this.teamservice.updateTeam(uteam);
		return SUCCESS;
	}
	public void getTeamByName(){
		try {
			teamName = java.net.URLDecoder.decode(teamName , "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		boolean b = this.teamservice.getTeamByName(teamName);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if (b) {
				response.getWriter().write(SUCCESS);
			}else{
				response.getWriter().write("notFound");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String addTeam(){
		String s = this.teamservice.addTeam(userteam);
		if("success".equals(s)){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	public EUserTeamService getTeamservice() {
		return teamservice;
	}
	public void setTeamservice(EUserTeamService teamservice) {
		this.teamservice = teamservice;
	}
	public List<EUser> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<EUser> userlist) {
		this.userlist = userlist;
	}
	public String getTeamid() {
		return teamid;
	}
	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}

	public UserTeam getUserteam() {
		return userteam;
	}

	public void setUserteam(UserTeam userteam) {
		this.userteam = userteam;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getContactph() {
		return contactph;
	}

	public void setContactph(String contactph) {
		this.contactph = contactph;
	}

	public String getTeamaddress() {
		return teamaddress;
	}

	public void setTeamaddress(String teamaddress) {
		this.teamaddress = teamaddress;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}



	public String getNumstart() {
		return numstart;
	}

	public void setNumstart(String numstart) {
		this.numstart = numstart;
	}

	public String getNumend() {
		return numend;
	}

	public void setNumend(String numend) {
		this.numend = numend;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	
}
