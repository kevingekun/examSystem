package com.wondersgroup.technocracy.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Addexpert implements java.io.Serializable {
	private Long hzz001;
	private String idnumber;// 证件号码
	private String name;// 姓名
	private String idstyle;// 证件类型
	private String sex;// 性别
	private Long date;// 出生日期
	private String education;// 学历
	private String org;// 所属单位
	private String eaddress;// 地址
	private String phone;// 联系电话
	private String major;// 擅长专业
	private String indicate;// 有效标示
	//private String expertstyle;// 专家类别

	//private String order;// 评价等级
	private String state;// 审核状态
	private String remark;

	private String jtitle;// 职称
	private String workphone;//单位电话
	private String studymajor;//所学专业
	private String qq;
	private String overage;
	private String email;
	
 
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	 

	 
 
 

	 

	 

	 

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getHzz001() {
		return hzz001;
	}

	public void setHzz001(Long hzz001) {
		this.hzz001 = hzz001;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getEaddress() {
		return eaddress;
	}

	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
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

	 

	public String getJtitle() {
		return jtitle;
	}

	public void setJtitle(String jtitle) {
		this.jtitle = jtitle;
	}

	 

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIndicate() {
		return indicate;
	}

	public void setIndicate(String indicate) {
		this.indicate = indicate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWorkphone() {
		return workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public String getStudymajor() {
		return studymajor;
	}

	public void setStudymajor(String studymajor) {
		this.studymajor = studymajor;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getOverage() {
		return overage;
	}

	public void setOverage(String overage) {
		this.overage = overage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
