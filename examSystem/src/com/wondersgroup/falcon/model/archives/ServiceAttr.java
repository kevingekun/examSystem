package com.wondersgroup.falcon.model.archives;

import java.util.Date;

public class ServiceAttr extends Attribute {


	private String faxFile;

	private Long organtype;		//机构类型
	private Long external;		//是否发布到外网
	private Long tailor;		//是否可包月定制  1是  0 否
	private Date externalTime; 	//外网发布时间
	private Date modifyTime;	//修改时间
	
	private Long  modifystate ; //是否已经修改  1是  0 否

	public Long getModifystate() {
		return modifystate;
	}

	public void setModifystate(Long modifystate) {
		this.modifystate = modifystate;
	}
	
	public String getFaxFile() {
		return faxFile;
	}

	public void setFaxFile(String faxFile) {
		this.faxFile = faxFile;
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

	public Long getOrgantype() {
		return organtype;
	}

	public void setOrgantype(Long organtype) {
		this.organtype = organtype;
	}

	public Long getTailor() {
		return tailor;
	}

	public void setTailor(Long tailor) {
		this.tailor = tailor;
	}

	/**
	 * 对政策法规的链接
	 * 
	 * @return
	 */





}
