package com.wondersgroup.falcon.model.archives;

import java.util.Date;

public class PolicyAttr extends Attribute {

	private Long effective;		//有效性 0 失效、1 正常、2 部分失效、3 废止

	private String fileno; 		//文号

	private String issuer;		//发文单位

	private String receiver;	//收文单位

	private Date issueDt;		//发文时间

	private Date effectiveDt;	//生效时间

	private Date expiredDt;		//失效时间

	private String faxFile;		//是否对外发送 0 否 1是， 发送内容传真、短信、电子邮件 

	private Long newdoc;		//是否是新文件 是则显示在最新法规目录中 1是  0 否
	
	private Long tailor;		//是否可包月定制  1是  0 否
	
	private Long redtop;		//是否是红头文件
	private String filetype;	//文件类型  令 通知 文件 等 （与红头文件有关）

	private Long external;		//是否发布到外网
	private Date externalTime; 	//外网发布时间
	private Date modifyTime;	//修改时间
	private Long year;
	
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}



	private Long  modifystate ; //是否已经修改  1是  0 否 2表示对应的考试试题正在修改

	public Long getModifystate() {
		return modifystate;
	}

	public void setModifystate(Long modifystate) {
		this.modifystate = modifystate;
	}


	public Long getExternal() {
		return external;
	}



	public void setExternal(Long external) {
		this.external = external;
	}



	public Date getExternalTime() {
		return externalTime;
	}



	public void setExternalTime(Date externalTime) {
		this.externalTime = externalTime;
	}



	public Date getModifyTime() {
		return modifyTime;
	}



	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}



	public Long getRedtop() {
		return redtop;
	}



	public void setRedtop(Long redtop) {
		this.redtop = redtop;
	}



	public String getFiletype() {
		return filetype;
	}



	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}



	public Long getNewdoc() {
		return newdoc;
	}



	public void setNewdoc(Long newdoc) {
		this.newdoc = newdoc;
	}



	public PolicyAttr() {
		super();
		effective = new Long(1);
	}



	/**
	 * 生效时间
	 * @return
	 */
	public Date getEffectiveDt() {
		return effectiveDt;
	}

	public void setEffectiveDt(Date effectiveDt) {
		this.effectiveDt = effectiveDt;
	}

	/**
	 * 失效时间
	 * @return
	 */
	public Date getExpiredDt() {
		return expiredDt;
	}

	public void setExpiredDt(Date expiredDt) {
		this.expiredDt = expiredDt;
	}

	/**
	 * 对应传真文件名
	 * @return
	 */
	public String getFaxFile() {
		return faxFile;
	}

	public void setFaxFile(String faxFile) {
		this.faxFile = faxFile;
	}

	/**
	 * 政策法规文号
	 * @return
	 */
	public String getFileno() {
		return fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
	}

	/**
	 * 发文时间
	 * @return
	 */
	public Date getIssueDt() {
		return issueDt;
	}

	public void setIssueDt(Date issueDt) {
		this.issueDt = issueDt;
	}

	/**
	 * 发文单位
	 * @return
	 */
	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * 收文单位
	 * @return
	 */
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}



	public Long getEffective() {
		return effective;
	}



	public void setEffective(Long effective) {
		this.effective = effective;
	}



	public Long getTailor() {
		return tailor;
	}



	public void setTailor(Long tailor) {
		this.tailor = tailor;
	}

}
