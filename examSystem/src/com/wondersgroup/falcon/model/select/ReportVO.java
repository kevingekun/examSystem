package com.wondersgroup.falcon.model.select;

import java.util.Date;

public class ReportVO {
	private int colum;
	private String gonghao;
	private String realname;
	private String groupname;
	private String groupid;
	private Double mark;
	private String avgmark;
	private String monthavgmark;
	private String allavgmark;
	private String alljlt;
	private int zrs;
	public String getGonghao() {
		return gonghao;
	}
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	
	public Double getMark() {
		return mark;
	}
	public void setMark(Double mark) {
		this.mark = mark;
	}
	public int getColum() {
		return colum;
	}
	public void setColum(int colum) {
		this.colum = colum;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getAvgmark() {
		return avgmark;
	}
	public void setAvgmark(String avgmark) {
		this.avgmark = avgmark;
	}
	public String getMonthavgmark() {
		return monthavgmark;
	}
	public void setMonthavgmark(String monthavgmark) {
		this.monthavgmark = monthavgmark;
	}
	public int getZrs() {
		return zrs;
	}
	public void setZrs(int zrs) {
		this.zrs = zrs;
	}
	public String getAllavgmark() {
		return allavgmark;
	}
	public void setAllavgmark(String allavgmark) {
		this.allavgmark = allavgmark;
	}
	public String getAlljlt() {
		return alljlt;
	}
	public void setAlljlt(String alljlt) {
		this.alljlt = alljlt;
	}
	

	
	

}
