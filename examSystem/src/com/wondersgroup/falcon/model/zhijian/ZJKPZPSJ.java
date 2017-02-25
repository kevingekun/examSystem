package com.wondersgroup.falcon.model.zhijian;

public class ZJKPZPSJ {
	private Long id;

	private String name;

	private String maxmark;

	private String minmark;

	private String beizhu;
	
	private String visible;
	
	private String parentid;

	private int sort;

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaxmark() {
		return maxmark;
	}

	public void setMaxmark(String maxmark) {
		this.maxmark = maxmark;
	}

	public String getMinmark() {
		return minmark;
	}

	public void setMinmark(String minmark) {
		this.minmark = minmark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

}
