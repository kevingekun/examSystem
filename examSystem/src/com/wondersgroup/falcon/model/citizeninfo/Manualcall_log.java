package com.wondersgroup.falcon.model.citizeninfo;

import java.util.Date;
import java.util.Set;

public class Manualcall_log {
	private String log_id;

	private String callid;

	private String userid;

	private Date ringdt;

	private String telno;

	private String noanswer;

	private Date canceldt;

	private Date establisheddt;

	private String interval;

	private Date endteldt;

	private Date noanswerdt;

	private Date hangupdt;

	private Date froendteldt;

	private Set history;

	private Date transdt;

	private String transflag;

	public String getLog_id() {
		return log_id;
	}

	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}

	public String getCallid() {
		return callid;
	}

	public void setCallid(String callid) {
		this.callid = callid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getNoanswer() {
		return noanswer;
	}

	public void setNoanswer(String noanswer) {
		this.noanswer = noanswer;
	}

	public Date getCanceldt() {
		return canceldt;
	}

	public void setCanceldt(Date canceldt) {
		this.canceldt = canceldt;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public Date getRingdt() {
		return ringdt;
	}

	public void setRingdt(Date ringdt) {
		this.ringdt = ringdt;
	}

	public Date getEstablisheddt() {
		return establisheddt;
	}

	public void setEstablisheddt(Date establisheddt) {
		this.establisheddt = establisheddt;
	}

	public Date getEndteldt() {
		return endteldt;
	}

	public void setEndteldt(Date endteldt) {
		this.endteldt = endteldt;
	}

	public Date getNoanswerdt() {
		return noanswerdt;
	}

	public void setNoanswerdt(Date noanswerdt) {
		this.noanswerdt = noanswerdt;
	}

	public Date getHangupdt() {
		return hangupdt;
	}

	public void setHangupdt(Date hangupdt) {
		this.hangupdt = hangupdt;
	}

	public Set getHistory() {
		return history;
	}

	public void setHistory(Set history) {
		this.history = history;
	}

	public Date getFroendteldt() {
		return froendteldt;
	}

	public void setFroendteldt(Date froendteldt) {
		this.froendteldt = froendteldt;
	}

	public Date getTransdt() {
		return transdt;
	}

	public void setTransdt(Date transdt) {
		this.transdt = transdt;
	}

	public String getTransflag() {
		return transflag;
	}

	public void setTransflag(String transflag) {
		this.transflag = transflag;
	}

}
