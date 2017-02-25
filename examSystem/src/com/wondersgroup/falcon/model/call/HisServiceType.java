package com.wondersgroup.falcon.model.call;

import java.util.Date;

public class HisServiceType {
		private Long id;                //来电服务类别的唯一标识
	    private Long callid;            //来电流水号
	    private Long userid;            //填写坐席工号（包括监理和普通咨询员）
	    private String servicetype;
	    private String flag;//服务类别
	    private Date eventtime;         //填写时间
		public Long getCallid() {
			return callid;
		}
		public void setCallid(Long callid) {
			this.callid = callid;
		}
		public Date getEventtime() {
			return eventtime;
		}
		public void setEventtime(Date eventtime) {
			this.eventtime = eventtime;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getServicetype() {
			return servicetype;
		}
		public void setServicetype(String servicetype) {
			this.servicetype = servicetype;
		}
		public Long getUserid() {
			return userid;
		}
		public void setUserid(Long userid) {
			this.userid = userid;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}

}
