package com.wondersgroup.popedom.bo;


 

public class ExamLimitKs implements java.io.Serializable {

	 private String zkzh;//准考证号
		private String  xm;//考生姓名
		private String IDnumber;//身份证号
		private String kssj;//考试时间
		private String sign;//限制登录原因
		private Long userID;//用户ID
		public Long getUserID() {
			return userID;
		}
		public void setUserID(Long userID) {
			this.userID = userID;
		}
		public String getZkzh() {
			return zkzh;
		}
		public void setZkzh(String zkzh) {
			this.zkzh = zkzh;
		}
		public String getXm() {
			return xm;
		}
		public void setXm(String xm) {
			this.xm = xm;
		}
		public String getIDnumber() {
			return IDnumber;
		}
		public void setIDnumber(String iDnumber) {
			IDnumber = iDnumber;
		}
		public String getKssj() {
			return kssj;
		}
		public void setKssj(String kssj) {
			this.kssj = kssj;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
}
