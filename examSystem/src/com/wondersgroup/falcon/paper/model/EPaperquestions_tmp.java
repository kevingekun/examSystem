package com.wondersgroup.falcon.paper.model;

import java.io.Serializable;

public class EPaperquestions_tmp implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long sjid;
	private long stid;
	private double sjStfs;
	private long sjStpx;
	private double wrong_percent;
	private long jdysid;
	private long stlxid;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSjid() {
		return sjid;
	}
	public void setSjid(long sjid) {
		this.sjid = sjid;
	}
	public long getStid() {
		return stid;
	}
	public void setStid(long stid) {
		this.stid = stid;
	}
	public double getSjStfs() {
		return sjStfs;
	}
	public void setSjStfs(double sjStfs) {
		this.sjStfs = sjStfs;
	}
	public long getSjStpx() {
		return sjStpx;
	}
	public void setSjStpx(long sjStpx) {
		this.sjStpx = sjStpx;
	}
	public double getWrong_percent() {
		return wrong_percent;
	}
	public void setWrong_percent(double wrong_percent) {
		this.wrong_percent = wrong_percent;
	}
	public long getJdysid() {
		return jdysid;
	}
	public void setJdysid(long jdysid) {
		this.jdysid = jdysid;
	}
	public long getStlxid() {
		return stlxid;
	}
	public void setStlxid(long stlxid) {
		this.stlxid = stlxid;
	}
	
}
