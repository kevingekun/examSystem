package com.wondersgroup.falcon.model.zhijian;

import java.util.Date;

public class Dxly {
	private String id;
	
	private String title;
	
	private String flid;
	
	private String agentid;
	
	private String callid;
	
	private String mark;
	
	private String beizhu;
	
	private Date teldt;

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getCallid() {
		return callid;
	}

	public void setCallid(String callid) {
		this.callid = callid;
	}

	public String getFlid() {
		return flid;
	}

	public void setFlid(String flid) {
		this.flid = flid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Date getTeldt() {
		return teldt;
	}

	public void setTeldt(Date teldt) {
		this.teldt = teldt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
