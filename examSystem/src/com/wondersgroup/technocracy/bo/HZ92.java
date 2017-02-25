package com.wondersgroup.technocracy.bo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
* Hz92
*/
 
 
public class HZ92  implements java.io.Serializable {

     private Long hzz200;//使用记录ID
     private Long hzz001;//专家基本信息ID
     private Long hzz900;//委员会id
    private Long hzz100;//使用规则ID
     private String hzz021;//考试信息ID
    private String hzz022;//考评记录信息
    private String hzz023;//考点评价信息
    private String hzz024;//中心评价
     private String hzz025;//中心评价等级
     private String hzz026;//替换理由
     
     
     private Long chz001;//考场点ID
    private Long chz007;//考场ID
     private String hzz033;//专家自评
     private String hzz032;//专家点评
    
     private Date aae036;//数据生成日期
     private String aae013;//备注
     private String hzz027;//考场说明
     private String aaa131;//有效标示
     private String hzz034;//Z职务
	public Long getHzz200() {
		return hzz200;
	}
	public void setHzz200(Long hzz200) {
		this.hzz200 = hzz200;
	}
	public Long getHzz001() {
		return hzz001;
	}
	public void setHzz001(Long hzz001) {
		this.hzz001 = hzz001;
	}
	
	public Long getHzz900() {
		return hzz900;
	}
	public void setHzz900(Long hzz900) {
		this.hzz900 = hzz900;
	}
	public Long getHzz100() {
		return hzz100;
	}
	public void setHzz100(Long hzz100) {
		this.hzz100 = hzz100;
	}
	public String getHzz021() {
		return hzz021;
	}
	public void setHzz021(String hzz021) {
		this.hzz021 = hzz021;
	}
	public String getHzz022() {
		return hzz022;
	}
	public void setHzz022(String hzz022) {
		this.hzz022 = hzz022;
	}
	public String getHzz023() {
		return hzz023;
	}
	public void setHzz023(String hzz023) {
		this.hzz023 = hzz023;
	}
	public String getHzz024() {
		return hzz024;
	}
	public void setHzz024(String hzz024) {
		this.hzz024 = hzz024;
	}
	public String getHzz025() {
		return hzz025;
	}
	public void setHzz025(String hzz025) {
		this.hzz025 = hzz025;
	}
	public String getHzz026() {
		return hzz026;
	}
	public void setHzz026(String hzz026) {
		this.hzz026 = hzz026;
	}
	public Long getChz001() {
		return chz001;
	}
	public void setChz001(Long chz001) {
		this.chz001 = chz001;
	}
	public Long getChz007() {
		return chz007;
	}
	public void setChz007(Long chz007) {
		this.chz007 = chz007;
	}
	public String getHzz033() {
		return hzz033;
	}
	public void setHzz033(String hzz033) {
		this.hzz033 = hzz033;
	}
	public String getHzz032() {
		return hzz032;
	}
	public void setHzz032(String hzz032) {
		this.hzz032 = hzz032;
	}
	public Date getAae036() {
		return aae036;
	}
	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}
	public String getAae013() {
		return aae013;
	}
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}
	public String getHzz027() {
		return hzz027;
	}
	public void setHzz027(String hzz027) {
		this.hzz027 = hzz027;
	}
	public String getAaa131() {
		return aaa131;
	}
	public void setAaa131(String aaa131) {
		this.aaa131 = aaa131;
	}
	public String getHzz034() {
		return hzz034;
	}
	public void setHzz034(String hzz034) {
		this.hzz034 = hzz034;
	}
    
	 
     

	 
     
     
}