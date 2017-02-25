package com.wondersgroup.falcon.model.archives;

import java.util.Date;

public class ArchiveLog {

	public static final Character POLICY = new Character('1');

	public static final Character FAQ = new Character('2');

	public static final Character SERVICE = new Character('3');

	public static final Character CASE = new Character('4');

	private Long id;

	private Long archiveid;

	private Long callid;

	private Long userid;

	private Date dt;

	private Character type;

	public Long getArchiveid() {
		return archiveid;
	}

	public void setArchiveid(Long archiveid) {
		this.archiveid = archiveid;
	}

	public Long getCallid() {
		return callid;
	}

	public void setCallid(Long callid) {
		this.callid = callid;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

}
