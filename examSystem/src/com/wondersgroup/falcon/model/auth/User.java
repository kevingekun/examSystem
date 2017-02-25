package com.wondersgroup.falcon.model.auth;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.wondersgroup.falcon.paper.model.EAnswerpaper;

public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String realname;

	private String password;

	private String agentId;

	private String deviceDn;

	private Group group;

	private Byte status;

	private Byte enabled;

	// private String color;
	//
	private String sex;
	//
	// private String putong;

	private Set authorities = new HashSet();

	// private int visible;

	// private String userbianhao;

	private UserType usertype;

	// private Byte flag;
	private Date createtime;
	private String userstar;

	// public int getVisible() {
	// return visible;
	// }
	//
	// public void setVisible(int visible) {
	// this.visible = visible;
	// }

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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
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

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public Set getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set authorities) {
		this.authorities = authorities;
	}

	public void addAuthority(Authority authority) {
		this.getAuthorities().add(authority);
		authority.getUsers().add(this);
	}

	public void removeAuthority(Authority authority) {
		this.getAuthorities().remove(authority);
		authority.getUsers().remove(this);
	}

	public void clearAuthorities() {
		for (Iterator iterator = this.getAuthorities().iterator(); iterator
				.hasNext(); ((Authority) (iterator.next())).getUsers().remove(
				this))
			;
		this.getAuthorities().clear();
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

	// public String getColor() {
	// return color;
	// }
	//
	// public void setColor(String color) {
	// this.color = color;
	// }
	//
	// public String getUserbianhao() {
	// return userbianhao;
	// }
	//
	// public void setUserbianhao(String userbianhao) {
	// this.userbianhao = userbianhao;
	// }
	//
	// public String getSex() {
	// return sex;
	// }
	//
	// public void setSex(String sex) {
	// this.sex = sex;
	// }
	//
	// public String getPutong() {
	// return putong;
	// }
	//
	// public void setPutong(String putong) {
	// this.putong = putong;
	// }
	//
	// public Byte getFlag() {
	// return flag;
	// }
	//
	// public void setFlag(Byte flag) {
	// this.flag = flag;
	// }

}
