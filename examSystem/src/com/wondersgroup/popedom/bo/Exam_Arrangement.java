package com.wondersgroup.popedom.bo;

import java.io.Serializable;

public class Exam_Arrangement implements Serializable{

	private String kdid;
	private String kcid;
	private String username;
	private String password;
	private String expertName;
	private String jobTitle;
	public String getKdid() {
		return kdid;
	}
	public void setKdid(String kdid) {
		this.kdid = kdid;
	}
	public String getKcid() {
		return kcid;
	}
	public void setKcid(String kcid) {
		this.kcid = kcid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
}
