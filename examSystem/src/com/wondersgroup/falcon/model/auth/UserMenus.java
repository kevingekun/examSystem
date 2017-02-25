package com.wondersgroup.falcon.model.auth;

import java.util.HashSet;
import java.util.Set;

public class UserMenus {
	private String menuid;
	private String parentid;
	private String menuname;
	private Long ordering;
	private Set usertype = new HashSet();
	private String menuepath;
	
	public String getMenuepath() {
		return menuepath;
	}
	public void setMenuepath(String menuepath) {
		this.menuepath = menuepath;
	}
	public Set getUsertype() {
		return usertype;
	}
	public void setUsertype(Set usertype) {
		this.usertype = usertype;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public Long getOrdering() {
		return ordering;
	}
	public void setOrdering(Long ordering) {
		this.ordering = ordering;
	}
}
