package com.wondersgroup.falcon.paper.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.wondersgroup.falcon.model.auth.User;

/**
 * EAnswerpaper entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EAnswerpaper implements java.io.Serializable {

	// Fields

	private long djId;
	private EPapers epapers;
	private String djRyid;//username （身份证号）
	private String djRymc;
	private Date djKssj;
	private Date djJssj;
	private double djZf;
	private long djSyzt;
	private long djCjpm;
	private String groupId;// 小组id
	private String flag;// 状态 0:是普通的答卷 1：拨测题 2 拨测题 作答完毕
	private String userStar;// userid
	private String userSex;// 考试人性别
	private Date userDate;// 考试人进中心日期
	private Set eanswerquestionses = new HashSet(0);

	private String cause;// 未参加考试原因

	// Constructors

	/** default constructor */
	public EAnswerpaper() {
	}

	/** minimal constructor */
	public EAnswerpaper(String djRyid, Date djKssj, Date djJssj) {
		this.djRyid = djRyid;
		this.djKssj = djKssj;
		this.djJssj = djJssj;
	}

	/** full constructor */
	public EAnswerpaper(EPapers epapers, String djRyid, Date djKssj,
			Date djJssj, double djZf, long djSyzt, long djCjpm, String groupId,
			Set eanswerquestionses) {
		this.epapers = epapers;
		this.djRyid = djRyid;
		this.djKssj = djKssj;
		this.djJssj = djJssj;
		this.djZf = djZf;
		this.djSyzt = djSyzt;
		this.djCjpm = djCjpm;
		this.groupId = groupId;
		this.eanswerquestionses = eanswerquestionses;
	}

	// Property accessors

	public long getDjId() {
		return this.djId;
	}

	public void setDjId(long djId) {
		this.djId = djId;
	}

	public String getDjRyid() {
		return this.djRyid;
	}

	public void setDjRyid(String djRyid) {
		this.djRyid = djRyid;
	}

	public Date getDjKssj() {
		return this.djKssj;
	}

	public void setDjKssj(Date djKssj) {
		this.djKssj = djKssj;
	}

	public Date getDjJssj() {
		return this.djJssj;
	}

	public void setDjJssj(Date djJssj) {
		this.djJssj = djJssj;
	}

	public double getDjZf() {
		return this.djZf;
	}

	public void setDjZf(double djZf) {
		this.djZf = djZf;
	}

	public long getDjSyzt() {
		return this.djSyzt;
	}

	public void setDjSyzt(long djSyzt) {
		this.djSyzt = djSyzt;
	}

	public long getDjCjpm() {
		return this.djCjpm;
	}

	public void setDjCjpm(long djCjpm) {
		this.djCjpm = djCjpm;
	}

	public EPapers getEpapers() {
		return epapers;
	}

	public void setEpapers(EPapers epapers) {
		this.epapers = epapers;
	}

	public Set getEanswerquestionses() {
		return eanswerquestionses;
	}

	public void setEanswerquestionses(Set eanswerquestionses) {
		this.eanswerquestionses = eanswerquestionses;
	}

	public String getDjRymc() {
		return djRymc;
	}

	public void setDjRymc(String djRymc) {
		this.djRymc = djRymc;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserStar() {
		return userStar;
	}

	public void setUserStar(String userStar) {
		this.userStar = userStar;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}