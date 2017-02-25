package com.wondersgroup.falcon.paper.model;

import java.util.Date;

/**
 * EPapers entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EPost implements java.io.Serializable {	
	private long id;
	private String bt;
	private String nr;
	private String sjmc;
	private String ryid;
	private Date sj;
	private String fjlj;
	

	// Constructors
	/** minimal constructor */
	public EPost( long id,String bt,String nr, String sjmc, String ryid,Date sj)
	{this.id=id;
	this.bt=bt;
	this.nr=nr;
	this.ryid=ryid;
	this.sj=sj;
	this.sjmc=sjmc;
	
	}
	/** full constructor */
	public EPost( long id,String bt,String nr, String sjmc, String ryid,Date sjm,String fjlj)
	{this.id=id;
	this.bt=bt;
	this.fjlj=fjlj;
	this.nr=nr;
	this.ryid=ryid;
	this.sj=sj;
	this.sjmc=sjmc;
	
	}
	/** default constructor */
	public EPost() {
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getSjmc() {
		return sjmc;
	}

	public void setSjmc(String sjmc) {
		this.sjmc = sjmc;
	}

	public String getRyid() {
		return ryid;
	}

	public void setRyid(String ryid) {
		this.ryid = ryid;
	}

	public Date getSj() {
		return sj;
	}

	public void setSj(Date sj) {
		this.sj = sj;
	}

	public String getFjlj() {
		return fjlj;
	}

	public void setFjlj(String fjlj) {
		this.fjlj = fjlj;
	}

}

	

	
