package com.wondersgroup.kaoshi.bo;

import java.util.Date;

public class UploadGradeDTO {

	private String sjMc;
	private Date sjKksj;
	private String realname;
	private String username;
	private double djZf;
	private String cheatflag;
	private long sjId;
	private long userid;
	public String getSjMc() {
		return sjMc;
	}
	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}
	
	public Date getSjKksj() {
		return sjKksj;
	}
	public void setSjKksj(Date sjKksj) {
		this.sjKksj = sjKksj;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public double getDjZf() {
		return djZf;
	}
	public void setDjZf(double djZf) {
		this.djZf = djZf;
	}
	public String getCheatflag() {
		return cheatflag;
	}
	public void setCheatflag(String cheatflag) {
		this.cheatflag = cheatflag;
	}
	public long getSjId() {
		return sjId;
	}
	public void setSjId(long sjId) {
		this.sjId = sjId;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	
}
