package com.wondersgroup.falcon.model.zhijian;

import java.util.Date;

public class Lypf {
	private String id;

	private String bkpr;

	private String pfr;

	private String beizhu;

	private String vcid;

	private String zjid;

	private Date pftime;
	
	private Date lytime;

	private double zf;

	private double mark;
	
	private double sumark;

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getBkpr() {
		return bkpr;
	}

	public void setBkpr(String bkpr) {
		this.bkpr = bkpr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public String getPfr() {
		return pfr;
	}

	public void setPfr(String pfr) {
		this.pfr = pfr;
	}

	public Date getPftime() {
		return pftime;
	}

	public void setPftime(Date pftime) {
		this.pftime = pftime;
	}

	public String getVcid() {
		return vcid;
	}

	public void setVcid(String vcid) {
		this.vcid = vcid;
	}

	public double getZf() {
		return zf;
	}

	public void setZf(double zf) {
		this.zf = zf;
	}

	public String getZjid() {
		return zjid;
	}

	public void setZjid(String zjid) {
		this.zjid = zjid;
	}

	public double getSumark() {
		return sumark;
	}

	public void setSumark(double sumark) {
		this.sumark = sumark;
	}

	public Date getLytime() {
		return lytime;
	}

	public void setLytime(Date lytime) {
		this.lytime = lytime;
	}

}
