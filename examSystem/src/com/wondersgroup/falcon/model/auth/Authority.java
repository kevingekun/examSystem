package com.wondersgroup.falcon.model.auth;

import java.util.HashSet;
import java.util.Set;


public class Authority implements java.io.Serializable{
	public static Byte SYSTEM_STATE = new Byte("0");//ϵͳ��ɫ
	public static Byte INDIVIDUAL_STATE = new Byte("1");//
	private Long id;
	private String name;
	private String description;
	private Set users = new HashSet();
	
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
