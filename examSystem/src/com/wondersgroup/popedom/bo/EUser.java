package com.wondersgroup.popedom.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EUser implements java.io.Serializable {

	private Long id;
	private String username;
	private String realname;
	private String password;
	private String agentId;
	private String deviceDn;
	private EGroup group;
	private Byte status;
	private Byte enabled;
	private String userType;
	private Byte visible;

	private String sex;// 考试人性别
	private Date createtime;// 考试人进中心时间
	private String userstar;// 考试人星级
	private String color;//考场考点id
	private String userflag;
	private Byte flag;
	private Byte iskaohe;
	
	private String userbianhao;//试卷id
	private String olduserbianhao;//原始鉴定批次id（hz95 id）

	// ���ƽ�Ȩ���û�
	private Byte devolve;

	private Set authorities = new HashSet();
	private Set teams = new HashSet(0);

	
	public EUser() {
		super();
	}

	public EUser(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public EGroup getGroup() {
		return group;
	}

	public void setGroup(EGroup group) {
		this.group = group;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getEnabled() {
		return enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Set getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set authorities) {
		this.authorities = authorities;
	}

	public void addAuthority(EAuthority authority) {
		this.getAuthorities().add(authority);
		authority.getUsers().add(this);
	}

	public void removeAuthority(EAuthority authority) {
		this.getAuthorities().remove(authority);
		authority.getUsers().remove(this);
	}

	public void clearAuthorities() {
		for (Iterator iterator = this.getAuthorities().iterator(); iterator
				.hasNext(); ((EAuthority) (iterator.next())).getUsers().remove(
				this))
			;
		this.getAuthorities().clear();
	}

	public Set getTeams() {
		return teams;
	}

	public void setTeams(Set teams) {
		this.teams = teams;
	}

	public void addTeams(EUserTeam team) {
		this.getTeams().add(team);
		team.getUsers().add(this);
	}

	public void removeTeams(EUserTeam team) {
		this.getTeams().remove(team);
		team.getUsers().remove(this);
	}

	public void clearTeams() {
		for (Iterator iterator = this.getTeams().iterator(); iterator.hasNext(); ((EUserTeam) (iterator
				.next())).getUsers().remove(this))
			;
		this.getTeams().clear();
	}

	public Byte getDevolve() {
		return devolve;
	}

	public void setDevolve(Byte devolve) {
		this.devolve = devolve;
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

	public String getUserbianhao() {
		return userbianhao;
	}

	public void setUserbianhao(String userbianhao) {
		this.userbianhao = userbianhao;
	}

	public String getOlduserbianhao() {
		return olduserbianhao;
	}

	public void setOlduserbianhao(String olduserbianhao) {
		this.olduserbianhao = olduserbianhao;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getUserflag() {
		return userflag;
	}

	public void setUserflag(String userflag) {
		this.userflag = userflag;
	}

	public Byte getVisible() {
		return visible;
	}

	public void setVisible(Byte visible) {
		this.visible = visible;
	}

	public Byte getFlag() {
		return flag;
	}

	public void setFlag(Byte flag) {
		this.flag = flag;
	}

	public Byte getIskaohe() {
		return iskaohe;
	}

	public void setIskaohe(Byte iskaohe) {
		this.iskaohe = iskaohe;
	}

}
