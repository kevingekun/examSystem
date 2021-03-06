package com.wondersgroup.falcon.model.citizeninfo;

/**
 * DicInfo generated by MyEclipse Persistence Tools
 */

public class DicInfo  {


	private Long id;

	private String name;			//类别名称

	private char type;				//分组类别；1.个人 2.单位
	
	private char removed;			//是否删除；1.未删 2.已删

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

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public char getRemoved() {
		return removed;
	}

	public void setRemoved(char removed) {
		this.removed = removed;
	}



}