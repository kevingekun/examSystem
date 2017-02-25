package com.wondersgroup.kaoshi.bo;

import java.util.Date;

public class GradeUpload {

	private String sjMc;//试卷名称
	private Date sjKksj;//开考时间
	private String realname;//考生姓名
	private String idcard;//身份证号
	private String grade;//成绩
	private String state;//状态,作弊 缺考..
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
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
