package com.wondersgroup.technocracy.bo;

import java.io.Serializable;

public class ExpertInfo implements Serializable{

	private Long examid;//试卷id
	private Long teamid;//考场id
	private Long hz92id;
	private String sjmc;//试卷名称
    private String kcmc;//考场名称
    private long expertid;
    private String expertname;
    private String phone;//联系电话
    private String major;//擅长专业
    private String zc;//职称
	public Long getExamid() {
		return examid;
	}
	public void setExamid(Long examid) {
		this.examid = examid;
	}
	public Long getTeamid() {
		return teamid;
	}
	public void setTeamid(Long teamid) {
		this.teamid = teamid;
	}
	
	public String getSjmc() {
		return sjmc;
	}
	public void setSjmc(String sjmc) {
		this.sjmc = sjmc;
	}
	public String getKcmc() {
		return kcmc;
	}
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	public long getExpertid() {
		return expertid;
	}
	public void setExpertid(long expertid) {
		this.expertid = expertid;
	}
	public String getExpertname() {
		return expertname;
	}
	public void setExpertname(String expertname) {
		this.expertname = expertname;
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
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public Long getHz92id() {
		return hz92id;
	}
	public void setHz92id(Long hz92id) {
		this.hz92id = hz92id;
	}
    
}
