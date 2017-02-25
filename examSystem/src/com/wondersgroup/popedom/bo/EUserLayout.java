package com.wondersgroup.popedom.bo;


public class EUserLayout {

	private String id;
	private Integer ordering;
	private EUser user;
	
	public Integer getOrdering() {
		return ordering;
	}
	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}
	public EUser getUser() {
		return user;
	}
	public void setUser(EUser user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
