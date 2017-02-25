package com.wondersgroup.falcon.model.exam;

import java.sql.Clob;
import java.util.Date;
import java.util.Set;

import com.wondersgroup.falcon.model.archives.Users;

public class TrainFile {
	private String id;
	private Users sender;
	private Clob content;
	private String contentstring;
	private Date sendtime;
	private Set fujian;
	private String title;
	private int type;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Clob getContent() {
		return content;
	}
	public void setContent(Clob content) {
		this.content = content;
	}
	public String getContentstring() {
		return contentstring;
	}
	public void setContentstring(String contentstring) {
		this.contentstring = contentstring;
	}
	public Set getFujian() {
		return fujian;
	}
	public void setFujian(Set fujian) {
		this.fujian = fujian;
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
