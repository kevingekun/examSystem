package com.wondersgroup.falcon.model.archives;

public class FaqAttr extends Attribute {

	private String voiceFile;

	private Long  modifystate ; //�Ƿ��Ѿ��޸�  1��  0 ��

	public Long getModifystate() {
		return modifystate;
	}

	public void setModifystate(Long modifystate) {
		this.modifystate = modifystate;
	}
	
//	private String tablerecord;

//	public String getTablerecord() {
//		return tablerecord;
//	}
//
//	public void setTablerecord(String tablerecord) {
//		this.tablerecord = tablerecord;
//	}

	public String getVoiceFile() {
		return voiceFile;
	}

	public void setVoiceFile(String voiceFile) {
		this.voiceFile = voiceFile;
	}

	/**
	 * ��Ӧ¼���ļ�����
	 * 
	 * @return
	 */

}
