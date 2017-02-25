package com.wondersgroup.falcon.model.auth;

import java.util.Set;

import com.wondersgroup.falcon.model.archives.Users;

public class Group implements java.io.Serializable{

	// group id
	private Long id;
	// group name
	private String name;
	// ACD group
	private String acdGroup;
	private String description;
	private Set users;
	
   
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAcdGroup() {
		return acdGroup;
	}
	public void setAcdGroup(String acdGroup) {
		this.acdGroup = acdGroup;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set getUsers() {
		return users;
	}
	public void setUsers(Set users) {
		this.users = users;
	}
}
