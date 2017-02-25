package com.wondersgroup.falcon.model.auth;

import java.util.HashSet;
import java.util.Set;

public class UserType implements java.io.Serializable{ 

	private String usertypeid;
	private String usertypename ;
	private Set usermenus = new HashSet();
	
	public Set getUsermenus() {
		return usermenus;  
	}
	public void setUsermenus(Set usermenus) {
		this.usermenus = usermenus;
	}
	public String getUsertypeid() {
		return usertypeid;
	}
	public void setUsertypeid(String usertypeid) {
		this.usertypeid = usertypeid;
	}
	public String getUsertypename() {
		return usertypename;
	}
	public void setUsertypename(String usertypename) {
		this.usertypename = usertypename;
	}
	

}
