package com.wondersgroup.falcon.model.zhijian;

import java.util.Date;

public class RECORDORIGINALDATA {
	private String recordreference;
	
	private String agentid;
	
	private String callerid;
	
	private Long recordlength;
	
	private String flag;
	
	private String EXTENSION;
	
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

	public String getEXTENSION() {
		return EXTENSION;
	}

	public void setEXTENSION(String extension) {
		EXTENSION = extension;
	}


	

}
