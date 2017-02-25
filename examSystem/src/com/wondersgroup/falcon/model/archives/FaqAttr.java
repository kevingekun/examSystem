package com.wondersgroup.falcon.model.archives;

public class FaqAttr extends Attribute {

	private String voiceFile;

	private Long  modifystate ; //是否已经修改  1是  0 否

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
	 * 对应录音文件名称
	 * 
	 * @return
	 */

}
