package com.wondersgroup.falcon.model.call;

import java.util.Date;

public class HisServiceType {
		private Long id;                //�����������Ψһ��ʶ
	    private Long callid;            //������ˮ��
	    private Long userid;            //��д��ϯ���ţ������������ͨ��ѯԱ��
	    private String servicetype;
	    private String flag;//�������
	    private Date eventtime;         //��дʱ��
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
