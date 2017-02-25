package com.wondersgroup.kaoshi.bo;

import java.util.Date;

public class RECORDORIGINALDATA {
	private String recordreference;
	
	private String voiceip;
	
	private String voiceid;
	
	private String channel;
	
	private String agentid;
	
	private String callerid;
	
	private Long recordlength;
	
	private String flag;
	
	private String directionflag;
	
	private String reservedthree;
	
	private Date startrecordtime;

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getCallerid() {
		return callerid;
	}

	public void setCallerid(String callerid) {
		this.callerid = callerid;
	}



	public Long getRecordlength() {
		return recordlength;
	}

	public void setRecordlength(Long recordlength) {
		this.recordlength = recordlength;
	}

	public String getDirectionflag() {
		return directionflag;
	}

	public void setDirectionflag(String directionflag) {
		this.directionflag = directionflag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}





	public Date getStartrecordtime() {
		return startrecordtime;
	}

	public void setStartrecordtime(Date startrecordtime) {
		this.startrecordtime = startrecordtime;
	}

	public String getReservedthree() {
		return reservedthree;
	}

	public void setReservedthree(String reservedthree) {
		this.reservedthree = reservedthree;
	}

	public String getRecordreference() {
		return recordreference;
	}

	public void setRecordreference(String recordreference) {
		this.recordreference = recordreference;
	}

	public String getVoiceip() {
		return voiceip;
	}

	public void setVoiceip(String voiceip) {
		this.voiceip = voiceip;
	}

	public String getVoiceid() {
		return voiceid;
	}

	public void setVoiceid(String voiceid) {
		this.voiceid = voiceid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}


	

}
