package com.wondersgroup.falcon.model.archives;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
 

public class ExternalAnnex implements Serializable {

	private Long id ; 
	private Date uploadTime ;//修改时间
	private Date unzipTime ; //解压时间
	private Long unzipState ; //解压状态 0为未解压 1解压成功 2 解压失败
	
	private String filePath ;//文件路径
	private String fileName ;//文件名称
	private Blob fileText ;//文件正文
	private String type ; //service 办事指南  policy办事指南 
	
	public ExternalAnnex(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getUnzipTime() {
		return unzipTime;
	}

	public void setUnzipTime(Date unzipTime) {
		this.unzipTime = unzipTime;
	}

	public Long getUnzipState() {
		return unzipState;
	}

	public void setUnzipState(Long unzipState) {
		this.unzipState = unzipState;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Blob getFileText() {
		return fileText;
	}

	public void setFileText(Blob fileText) {
		this.fileText = fileText;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
