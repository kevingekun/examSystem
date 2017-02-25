package com.wondersgroup.falcon.model.jhk;

import java.util.Date;

/**
 * AppHlpmedia generated by MyEclipse Persistence Tools
 */

public class AppHlpmedia implements java.io.Serializable {

	// Fields

	private String id;

	private String fileid;				//所属帮助文件标识ID,FK(APP_HLPDOC)
	private String content;				//多媒体文件内容
	private String name;				//多媒体文件名
	private String location;			//多媒体文件相对于所属帮助文件的位置
	private String type;				//多媒体文件类型
	private Date publishdate;			//发布日期
	private String systemcode;			//所属业务系统编码

	// Constructors

	/** default constructor */
	public AppHlpmedia() {
	}

	/** minimal constructor */
	public AppHlpmedia(String id) {
		this.id = id;
	}

	/** full constructor */
	public AppHlpmedia(String id, String fileid, String content, String name,
			String location, String type, Date publishdate, String systemcode) {
		this.id = id;
		this.fileid = fileid;
		this.content = content;
		this.name = name;
		this.location = location;
		this.type = type;
		this.publishdate = publishdate;
		this.systemcode = systemcode;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileid() {
		return this.fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPublishdate() {
		return this.publishdate;
	}

	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

	public String getSystemcode() {
		return this.systemcode;
	}

	public void setSystemcode(String systemcode) {
		this.systemcode = systemcode;
	}

}