package com.wondersgroup.falcon.model.select;

import java.util.Date;

public class SelectVO {
	private String id;
	private String recordreference;
	private String agentid;
	private String callerid;
	private Long recordlength;
	private String sort;
	private String type;
	private Date stoprecordtime;
	private Date startrecordtime;
	private Date chouyangtime;
	private String zhijianid;
	private Date endteldt;
	private Date ringdt;
	private Date hangupdt;
	private Date weitiaodate;
	private String groupname;
	private String comments;
	private String realname;
	private Long hhxjlength;
	private String fpzrs;
	private String fpzrsa;
	private String fpzrsb;
	private String fpzrsc;
	private String fpzrsd;
	private String fpzsc;
	private String grbcqzts;//个人被抽取总条数
	private String grbcqztsa;//个人被抽取总条数
	private String grbcqztsb;//个人被抽取总条数
	private String grbcqztsc;//个人被抽取总条数
	private String grbcqztsd;//个人被抽取总条数
	private String zhijiansqxgcs;//质检申请修改次数
	private String shenhetongguocs;//审核通过次数
	private String weitiaoreason;
	private String gonghao;
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

	public Date getStartrecordtime() {
		return startrecordtime;
	}

	public void setStartrecordtime(Date startrecordtime) {
		this.startrecordtime = startrecordtime;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStoprecordtime() {
		return stoprecordtime;
	}

	public void setStoprecordtime(Date stoprecordtime) {
		this.stoprecordtime = stoprecordtime;
	}

	public String getRecordreference() {
		return recordreference;
	}

	public void setRecordreference(String recordreference) {
		this.recordreference = recordreference;
	}

	public String getZhijianid() {
		return zhijianid;
	}

	public void setZhijianid(String zhijianid) {
		this.zhijianid = zhijianid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getChouyangtime() {
		return chouyangtime;
	}

	public void setChouyangtime(Date chouyangtime) {
		this.chouyangtime = chouyangtime;
	}

	
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	

	public Date getRingdt() {
		return ringdt;
	}

	public void setRingdt(Date ringdt) {
		this.ringdt = ringdt;
	}

	public Date getHangupdt() {
		return hangupdt;
	}

	public void setHangupdt(Date hangupdt) {
		this.hangupdt = hangupdt;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Date getEndteldt() {
		return endteldt;
	}

	public void setEndteldt(Date endteldt) {
		this.endteldt = endteldt;
	}

	public Long getHhxjlength() {
		return hhxjlength;
	}

	public void setHhxjlength(Long hhxjlength) {
		this.hhxjlength = hhxjlength;
	}



	public String getFpzrs() {
		return fpzrs;
	}

	public void setFpzrs(String fpzrs) {
		this.fpzrs = fpzrs;
	}

	public String getFpzsc() {
		return fpzsc;
	}

	public void setFpzsc(String fpzsc) {
		this.fpzsc = fpzsc;
	}

	public String getGrbcqzts() {
		return grbcqzts;
	}

	public void setGrbcqzts(String grbcqzts) {
		this.grbcqzts = grbcqzts;
	}

	public String getZhijiansqxgcs() {
		return zhijiansqxgcs;
	}

	public void setZhijiansqxgcs(String zhijiansqxgcs) {
		this.zhijiansqxgcs = zhijiansqxgcs;
	}

	public String getShenhetongguocs() {
		return shenhetongguocs;
	}

	public void setShenhetongguocs(String shenhetongguocs) {
		this.shenhetongguocs = shenhetongguocs;
	}

	public String getWeitiaoreason() {
		return weitiaoreason;
	}

	public void setWeitiaoreason(String weitiaoreason) {
		this.weitiaoreason = weitiaoreason;
	}

	public Date getWeitiaodate() {
		return weitiaodate;
	}

	public void setWeitiaodate(Date weitiaodate) {
		this.weitiaodate = weitiaodate;
	}

	public String getGrbcqztsa() {
		return grbcqztsa;
	}

	public void setGrbcqztsa(String grbcqztsa) {
		this.grbcqztsa = grbcqztsa;
	}

	public String getGrbcqztsb() {
		return grbcqztsb;
	}

	public void setGrbcqztsb(String grbcqztsb) {
		this.grbcqztsb = grbcqztsb;
	}

	public String getGrbcqztsc() {
		return grbcqztsc;
	}

	public void setGrbcqztsc(String grbcqztsc) {
		this.grbcqztsc = grbcqztsc;
	}

	public String getGrbcqztsd() {
		return grbcqztsd;
	}

	public void setGrbcqztsd(String grbcqztsd) {
		this.grbcqztsd = grbcqztsd;
	}

	public String getFpzrsa() {
		return fpzrsa;
	}

	public void setFpzrsa(String fpzrsa) {
		this.fpzrsa = fpzrsa;
	}

	public String getFpzrsb() {
		return fpzrsb;
	}

	public void setFpzrsb(String fpzrsb) {
		this.fpzrsb = fpzrsb;
	}

	public String getFpzrsc() {
		return fpzrsc;
	}

	public void setFpzrsc(String fpzrsc) {
		this.fpzrsc = fpzrsc;
	}

	public String getFpzrsd() {
		return fpzrsd;
	}

	public void setFpzrsd(String fpzrsd) {
		this.fpzrsd = fpzrsd;
	}

	public String getGonghao() {
		return gonghao;
	}

	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}

}
