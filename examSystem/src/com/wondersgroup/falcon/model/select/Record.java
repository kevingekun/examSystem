// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseMoney.java

package com.wondersgroup.falcon.model.select;


public class Record
{

	private String recordreference;
	private String startrecordtime;
	private String stoprecordtime;
	private String agentid;
	private String callerid;
	private String flag;
	
	
	
	public String getRecordreference() {
		return recordreference;
	}
	public void setRecordreference(String recordreference) {
		this.recordreference = recordreference;
	}
	
	public String getStartrecordtime() {
		return startrecordtime;
	}
	public void setStartrecordtime(String startrecordtime) {
		this.startrecordtime = startrecordtime;
	}
	
	
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public String getStoprecordtime() {
		return stoprecordtime;
	}
	public void setStoprecordtime(String stoprecordtime) {
		this.stoprecordtime = stoprecordtime;
	}
	public String getCallerid() {
		return callerid;
	}
	public void setCallerid(String callerid) {
		this.callerid = callerid;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	
}
