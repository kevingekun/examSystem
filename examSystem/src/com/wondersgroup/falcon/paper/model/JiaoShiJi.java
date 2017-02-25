package com.wondersgroup.falcon.paper.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * EPapers entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class JiaoShiJi implements java.io.Serializable {


	private String sjMc;// 试卷名称
	private String ks_rs;//考生数
	private long sj_id;//试卷
	private long kc_id;//试卷
	
	public String getSjMc() {
		return sjMc;
	}
	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}
	public String getKs_rs() {
		return ks_rs;
	}
	public void setKs_rs(String ks_rs) {
		this.ks_rs = ks_rs;
	}
	public long getSj_id() {
		return sj_id;
	}
	public void setSj_id(long sj_id) {
		this.sj_id = sj_id;
	}
	public long getKc_id() {
		return kc_id;
	}
	public void setKc_id(long kc_id) {
		this.kc_id = kc_id;
	}

	}
	

	