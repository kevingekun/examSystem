package com.wondersgroup.falcon.model.call;

public class Zhijiankaohe {
	private Long id;

	private String parentid;

	private String name;

	private String value;

	private String biaozhun;

	private int sort;

	private String comments;

	private String visible;

	public String getBiaozhun() {
		return biaozhun;
	}

	public void setBiaozhun(String biaozhun) {
		this.biaozhun = biaozhun;
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

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
