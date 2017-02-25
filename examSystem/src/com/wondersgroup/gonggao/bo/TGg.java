package com.wondersgroup.gonggao.bo;

import java.util.Date;

/**
 * TGg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TGg implements java.io.Serializable {

	// Fields

	private String ggid;
	private TGglm ggglm;
	private String ggbt;
	private Date ggrq;
	private String bmid;
	private String ryid;
	private String ggnr;

	// Constructors

	/** default constructor */
	public TGg() {
	}

	/** full constructor */
	public TGg(String lmid, String ggbt, Date ggrq, String bmid, String ryid,
			String ggnr) {
		this.ggbt = ggbt;
		this.ggrq = ggrq;
		this.bmid = bmid;
		this.ryid = ryid;
		this.ggnr = ggnr;
	}

	// Property accessors

	public String getGgid() {
		return this.ggid;
	}

	public void setGgid(String ggid) {
		this.ggid = ggid;
	}

	public String getGgbt() {
		return this.ggbt;
	}

	public void setGgbt(String ggbt) {
		this.ggbt = ggbt;
	}

	public Date getGgrq() {
		return this.ggrq;
	}

	public void setGgrq(Date ggrq) {
		this.ggrq = ggrq;
	}

	public String getBmid() {
		return this.bmid;
	}

	public void setBmid(String bmid) {
		this.bmid = bmid;
	}

	public String getRyid() {
		return this.ryid;
	}

	public void setRyid(String ryid) {
		this.ryid = ryid;
	}

	public String getGgnr() {
		return this.ggnr;
	}

	public TGglm getGgglm() {
		return ggglm;
	}

	public void setGgglm(TGglm ggglm) {
		this.ggglm = ggglm;
	}

	public void setGgnr(String ggnr) {
		this.ggnr = ggnr;
	}


}