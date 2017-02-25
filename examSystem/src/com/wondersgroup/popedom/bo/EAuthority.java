package com.wondersgroup.popedom.bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EAuthority implements Serializable{
	
	public static Byte SYSTEM_STATE = new Byte("0");//ϵͳ��ɫ
	public static Byte INDIVIDUAL_STATE = new Byte("1");//���˽�ɫ
		
	private Long id;
	private String name;
	private String description;
	private Byte state;
	private Long userid;
	private Set users = new HashSet();
	private Set menus = new HashSet();
	private Set operations = new HashSet();
	
	public void clearMenus() {
		for (Iterator iterator = this.getMenus().iterator();
			iterator.hasNext();
		    ((EUserMenus)(iterator.next())).getAuthoritys().remove(this));
		this.getMenus().clear();
	}
	
	public Set getMenus() {
		return menus;
	}
	public void setMenus(Set menus) {
		this.menus = menus;
	}
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

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Set getOperations() {
		return operations;
	}

	public void setOperations(Set operations) {
		this.operations = operations;
	}
}
