package com.wondersgroup.popedom.bo;

import java.util.Date;

public class HZ95 {

	private Long jd_id;//主键
	private String jd_mc;//鉴定批次名称
	private Long sj_id;//试卷id
	private String ks_gl;//是否关联考试
	private String aaa131;//是否有效
	private String ks_rs;//考生数
	private Date aae036;//导入时间
	
	
	public Long getJd_id() {
		return jd_id;
	}
	public void setJd_id(Long jd_id) {
		this.jd_id = jd_id;
	}
	public String getJd_mc() {
		return jd_mc;
	}
	public void setJd_mc(String jd_mc) {
		this.jd_mc = jd_mc;
	}
	public Long getSj_id() {
		return sj_id;
	}
	public void setSj_id(Long sj_id) {
		this.sj_id = sj_id;
	}
	public String getKs_gl() {
		return ks_gl;
	}
	public void setKs_gl(String ks_gl) {
		this.ks_gl = ks_gl;
	}
	public String getAaa131() {
		return aaa131;
	}
	public void setAaa131(String aaa131) {
		this.aaa131 = aaa131;
	}
	public String getKs_rs() {
		return ks_rs;
	}
	public void setKs_rs(String ks_rs) {
		this.ks_rs = ks_rs;
	}
	public Date getAae036() {
		return aae036;
	}
	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}
	
	
	
}
