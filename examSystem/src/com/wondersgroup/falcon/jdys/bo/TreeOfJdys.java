package com.wondersgroup.falcon.jdys.bo;

import java.io.Serializable;

public class TreeOfJdys implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int jd_id;
	private int parentid;
	private String name;
	private int dj;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getJd_id() {
		return jd_id;
	}
	public void setJd_id(int jd_id) {
		this.jd_id = jd_id;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDj() {
		return dj;
	}
	public void setDj(int dj) {
		this.dj = dj;
	}
}
