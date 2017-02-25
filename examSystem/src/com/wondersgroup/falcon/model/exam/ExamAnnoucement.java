package com.wondersgroup.falcon.model.exam;

import java.util.Date;

import com.wondersgroup.falcon.model.archives.Users;

public class ExamAnnoucement {
	private String id;
	private String title;
	private String content;
	private Date sendtime;
	private Users sender;
	private ExamPaper exampaper;
	private Date effectstarttime;
	private Date effectendtime;
	
	public Date getEffectendtime() {
		return effectendtime;
	}
	public void setEffectendtime(Date effectendtime) {
		this.effectendtime = effectendtime;
	}
	public Date getEffectstarttime() {
		return effectstarttime;
	}
	public void setEffectstarttime(Date effectstarttime) {
		this.effectstarttime = effectstarttime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Users getSender() {
		return sender;
	}
	public void setSender(Users sender) {
		this.sender = sender;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
