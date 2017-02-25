package com.wondersgroup.falcon.model.archives;

import java.util.Date;

public class Users {

	private Long id;
	private String username;
	private String realname;
	private String password;
	private String agentId;
	private String deviceDn;
	private String sex;
	private Byte status;
	private Byte enabled;
	private Date createtime;
	private String userstar;

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getDeviceDn() {
		return deviceDn;
	}

	public void setDeviceDn(String deviceDn) {
		this.deviceDn = deviceDn;
	}

	public Byte getEnabled() {
		return enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUserstar() {
		return userstar;
	}

	public void setUserstar(String userstar) {
		this.userstar = userstar;
	}

}
