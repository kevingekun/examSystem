package com.wondersgroup.gonggao.bo;

/**
 * TGglm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TGglm implements java.io.Serializable {

	// Fields

	private String lxid;
	private String lxmc;
	private String lxms;
	private String qybz;

	// Constructors

	/** default constructor */
	public TGglm() {
	}

	/** full constructor */
	public TGglm(String lxmc, String lxms, String qybz) {
		this.lxmc = lxmc;
		this.lxms = lxms;
		this.qybz = qybz;
	}

	// Property accessors

	public String getLxid() {
		return this.lxid;
	}

	public void setLxid(String lxid) {
		this.lxid = lxid;
	}

	public String getLxmc() {
		return this.lxmc;
	}

	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}

	public String getLxms() {
		return this.lxms;
	}

	public void setLxms(String lxms) {
		this.lxms = lxms;
	}

	public String getQybz() {
		return this.qybz;
	}

	public void setQybz(String qybz) {
		this.qybz = qybz;
	}

}