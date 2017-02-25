package com.wondersgroup.gonggao.bo;

import java.util.Date;

/**
 * TBm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Mgg implements java.io.Serializable {

	// Fields
    private String identifynumber;
	private Long hzz091;//主键id
	private Long sj_id;//考试id
	private String hzz092;//考生须知
	private String aae013;//备注
	private String aaa131;//有效标示
	private Date aae036;//录入时间
	public Long getHzz091() {
		return hzz091;
	}
	public void setHzz091(Long hzz091) {
		this.hzz091 = hzz091;
	}
	public Long getSj_id() {
		return sj_id;
	}
	public void setSj_id(Long sj_id) {
		this.sj_id = sj_id;
	}
	public String getHzz092() {
		return hzz092;
	}
	public void setHzz092(String hzz092) {
		this.hzz092 = hzz092;
	}
	public String getAae013() {
		return aae013;
	}
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}
	public String getAaa131() {
		return aaa131;
	}
	public void setAaa131(String aaa131) {
		this.aaa131 = aaa131;
	}
	public Date getAae036() {
		return aae036;
	}
	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}
	public String getIdentifynumber() {
		return identifynumber;
	}
	public void setIdentifynumber(String identifynumber) {
		this.identifynumber = identifynumber;
	}
	
 
	
	

}