package com.wondersgroup.popedom.bo;

import java.io.Serializable;

public class EGroup implements Serializable {

	// group id
	private Long id;
	// group name
	private String name;
	// ACD group
	private String acdGroup;
	private String description;
	
	
	
	public EGroup() {
		super();
	}
	public EGroup(Long id) {
		super();
		this.id = id;
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
}
