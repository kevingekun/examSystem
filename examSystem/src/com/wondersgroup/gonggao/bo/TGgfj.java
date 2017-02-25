package com.wondersgroup.gonggao.bo;

/**
 * TGgfj entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TGgfj implements java.io.Serializable {

	// Fields

	private String ggid;
	private String fjxh;
	private String fjmc;
	private String fjdz;
	private String fjms;
	private byte[] fjnr;

	// Constructors

	/** default constructor */
	public TGgfj() {
	}

	/** minimal constructor */
	public TGgfj(String fjxh) {
		this.fjxh = fjxh;
	}

	/** full constructor */
	public TGgfj(String ggid,String fjxh, String fjmc, String fjdz, String fjms,byte[] fjnr) {
		this.ggid = ggid;
		this.fjxh = fjxh;
		this.fjmc = fjmc;
		this.fjdz = fjdz;
		this.fjms = fjms;
		this.fjnr = fjnr;
	}

	// Property accessors

	public String getGgid() {
		return this.ggid;
	}

	public void setGgid(String ggid) {
		this.ggid = ggid;
	}

	public String getFjxh() {
		return this.fjxh;
	}

	public void setFjxh(String fjxh) {
		this.fjxh = fjxh;
	}

	public String getFjmc() {
		return this.fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public String getFjdz() {
		return this.fjdz;
	}

	public void setFjdz(String fjdz) {
		this.fjdz = fjdz;
	}

	public String getFjms() {
		return this.fjms;
	}

	public void setFjms(String fjms) {
		this.fjms = fjms;
	}

	public byte[] getFjnr() {
		return fjnr;
	}

	public void setFjnr(byte[] fjnr) {
		this.fjnr = fjnr;
	}

}