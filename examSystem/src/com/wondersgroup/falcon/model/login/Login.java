package com.wondersgroup.falcon.model.login;

import java.util.Date;

public class Login {
	private String log_id;
	private String agentid;
	private String acdgroup;
	private String dn;
	private Date logindt;
	private Date agentlogindt;
	private Date logoffdt;
	private Date startworkdt;
	private String info;
	private String usertype;

	public String getLog_id() {
		return log_id;
	}

	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getAcdgroup() {
		return acdgroup;
	}

	public void setAcdgroup(String acdgroup) {
		this.acdgroup = acdgroup;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public Date getLogindt() {
		return logindt;
	}

	public void setLogindt(Date logindt) {
		this.logindt = logindt;
	}

	public Date getAgentlogindt() {
		return agentlogindt;
	}

	public void setAgentlogindt(Date agentlogindt) {
		this.agentlogindt = agentlogindt;
	}

	public Date getLogoffdt() {
		return logoffdt;
	}

	public void setLogoffdt(Date logoffdt) {
		this.logoffdt = logoffdt;
	}

	public Date getStartworkdt() {
		return startworkdt;
	}

	public void setStartworkdt(Date startworkdt) {
		this.startworkdt = startworkdt;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

}
