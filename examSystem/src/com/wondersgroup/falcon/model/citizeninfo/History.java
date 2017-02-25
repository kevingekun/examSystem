package com.wondersgroup.falcon.model.citizeninfo;

import java.util.Date;

import com.wondersgroup.falcon.model.archives.Users;
import com.wondersgroup.falcon.model.zhijian.RECORDORIGINALDATA;

public class History {
	private Long id;

	private String type;

	private Users user;

	private String callid;

	private Date startime;

	private Date endtime;



	private String phoneid;

	private String comments;

	private String sort;

	private String zhonglei;

	private String name;

	private String zhuidian;

	private Manualcall_log calllog;
	
	private RECORDORIGINALDATA reservedthree;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getCallid() {
		return callid;
	}

	public void setCallid(String callid) {
		this.callid = callid;
	}

	public Date getStartime() {
		return startime;
	}

	public void setStartime(Date startime) {
		this.startime = startime;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getZhonglei() {
		return zhonglei;
	}

	public void setZhonglei(String zhonglei) {
		this.zhonglei = zhonglei;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Manualcall_log getCalllog() {
		return calllog;
	}

	public void setCalllog(Manualcall_log calllog) {
		this.calllog = calllog;
	}


	public String getZhuidian() {
		return zhuidian;
	}

	public void setZhuidian(String zhuidian) {
		this.zhuidian = zhuidian;
	}

	public RECORDORIGINALDATA getReservedthree() {
		return reservedthree;
	}

	public void setReservedthree(RECORDORIGINALDATA reservedthree) {
		this.reservedthree = reservedthree;
	}




}