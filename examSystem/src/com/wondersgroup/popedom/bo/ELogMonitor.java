/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.popedom.bo;

import java.util.Date;

/**考试状态监控表
 * @author mxk
 *
 */
public class ELogMonitor implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long logid;//主键id
	private Long userid;//用户id
	private Long examid;//考试id
	private String ip;//用户登录ip
	private Date logindate;//登录时间
	private Date startdate;//开始答题时间
	private Date enddate;//结束答题时间
	private String flag;//标记（）
	private String state;//状态（1未开始答题，2正在答题 3 网络异常退出，4结束答题）
	private String surplus;//剩余时间
	private String cheatflag;//作弊标示（1作弊0未作弊）
	private String remarks;//（备注）
	private String ifxz;
	
	public Long getLogid() {
		return logid;
	}
	public void setLogid(Long logid) {
		this.logid = logid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getExamid() {
		return examid;
	}
	public void setExamid(Long examid) {
		this.examid = examid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLogindate() {
		return logindate;
	}
	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSurplus() {
		return surplus;
	}
	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}
	public String getCheatflag() {
		return cheatflag;
	}
	public void setCheatflag(String cheatflag) {
		this.cheatflag = cheatflag;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIfxz() {
		return ifxz;
	}
	public void setIfxz(String ifxz) {
		this.ifxz = ifxz;
	}
	

}
