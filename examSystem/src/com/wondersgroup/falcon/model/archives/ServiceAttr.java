package com.wondersgroup.falcon.model.archives;

import java.util.Date;

public class ServiceAttr extends Attribute {


	private String faxFile;

	private Long organtype;		//��������
	private Long external;		//�Ƿ񷢲�������
	private Long tailor;		//�Ƿ�ɰ��¶���  1��  0 ��
	private Date externalTime; 	//��������ʱ��
	private Date modifyTime;	//�޸�ʱ��
	
	private Long  modifystate ; //�Ƿ��Ѿ��޸�  1��  0 ��

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
	 * �����߷��������
	 * 
	 * @return
	 */





}
