package com.wondersgroup.technocracy.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Querydisplay implements java.io.Serializable {
	private Long hzz001;
	private String idnumber;// 证件号码
	private String name;// 姓名
	private String idstyle;// 证件类型
	private String org;// 所属单位
	//private String eaddress;// 地址
	private String phone;// 联系电话
	private String major;// 擅长专业
	 private String expertstyle;// 专家类别
	private String jtitle;// 职称
	private Long date;
	
	public Long getHzz001() {
		return hzz001;
	}
	public void setHzz001(Long hzz001) {
		this.hzz001 = hzz001;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdstyle() {
		return idstyle;
	}
	public void setIdstyle(String idstyle) {
		this.idstyle = idstyle;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
 
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getExpertstyle() {
		return expertstyle;
	}
	public void setExpertstyle(String expertstyle) {
		this.expertstyle = expertstyle;
	}
	public String getJtitle() {
		return jtitle;
	}
	public void setJtitle(String jtitle) {
		this.jtitle = jtitle;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	 
	 

}
