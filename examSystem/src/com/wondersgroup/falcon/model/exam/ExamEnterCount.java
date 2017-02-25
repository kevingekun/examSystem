package com.wondersgroup.falcon.model.exam;

import java.util.Date;

import com.wondersgroup.falcon.model.archives.Users;

public class ExamEnterCount {
	private String id;
	private Date entertime;
	private Users users;
	private ExamPaper exampaper;
	
	public Date getEntertime() {
		return entertime;
	}
	public void setEntertime(Date entertime) {
		this.entertime = entertime;
	}
	public ExamPaper getExampaper() {
		return exampaper;
	}
	public void setExampaper(ExamPaper exampaper) {
		this.exampaper = exampaper;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
}
