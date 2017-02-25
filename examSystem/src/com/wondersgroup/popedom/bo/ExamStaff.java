package com.wondersgroup.popedom.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

 

public class ExamStaff implements java.io.Serializable {

	 private String zkh;//准考证号
	private String  examineename;//考生姓名
	private String IDnumber;//身份证号
	private String examid;//鉴定批次号
	private String examroom;//考场
	private String examroomadress;//考场地址
	public String getZkh() {
		return zkh;
	}
	public void setZkh(String zkh) {
		this.zkh = zkh;
	}
	public String getExamineename() {
		return examineename;
	}
	public void setExamineename(String examineename) {
		this.examineename = examineename;
	}
	public String getIDnumber() {
		return IDnumber;
	}
	public void setIDnumber(String iDnumber) {
		IDnumber = iDnumber;
	}
	 
	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public String getExamroom() {
		return examroom;
	}
	public void setExamroom(String examroom) {
		this.examroom = examroom;
	}
	public String getExamroomadress() {
		return examroomadress;
	}
	public void setExamroomadress(String examroomadress) {
		this.examroomadress = examroomadress;
	}
}
