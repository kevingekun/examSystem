package com.wondersgroup.gonggao.bo;

/**
 * TBm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TBm implements java.io.Serializable {

	// Fields

	private String bmid;
	private String bmmc;
	private String bmms;
	private String sjbm;
	private String jgid;

	// Constructors

	/** default constructor */
	public TBm() {
	}

	/** full constructor */
	public TBm(String bmmc, String bmms, String sjbm, String jgid) {
		this.bmmc = bmmc;
		this.bmms = bmms;
		this.sjbm = sjbm;
		this.jgid = jgid;
	}

	// Property accessors

	public String getBmid() {
		return this.bmid;
	}

	public void setBmid(String bmid) {
		this.bmid = bmid;
	}

	public String getBmmc() {
		return this.bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getBmms() {
		return this.bmms;
	}

	public void setBmms(String bmms) {
		this.bmms = bmms;
	}

	public String getSjbm() {
		return this.sjbm;
	}

	public void setSjbm(String sjbm) {
		this.sjbm = sjbm;
	}

	public String getJgid() {
		return this.jgid;
	}

	public void setJgid(String jgid) {
		this.jgid = jgid;
	}

}