package com.wondersgroup.falcon.model.archives;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
 

public class ExternalAnnex implements Serializable {

	private Long id ; 
	private Date uploadTime ;//�޸�ʱ��
	private Date unzipTime ; //��ѹʱ��
	private Long unzipState ; //��ѹ״̬ 0Ϊδ��ѹ 1��ѹ�ɹ� 2 ��ѹʧ��
	
	private String filePath ;//�ļ�·��
	private String fileName ;//�ļ�����
	private Blob fileText ;//�ļ�����
	private String type ; //service ����ָ��  policy����ָ�� 
	
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
