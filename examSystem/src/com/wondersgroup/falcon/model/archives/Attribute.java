package com.wondersgroup.falcon.model.archives;

import java.sql.Clob;
import java.util.Date;

import com.wondersgroup.falcon.model.dic.DicReleasestate;



public abstract class Attribute {

	private Long id;

	private String keyword; //关键词

	private String businessType;//业务类型

	private String htmlFile;//发布的HTML文件名
	
	private Clob filetext;//文件正文
	
	private String neirongstring;  //临时
	
	private String policy; //关联的法规文件   规则是;分割
	
//	private String status;//文件状态
	
//	private String releasestate_id;//发布状态
	
//	private DicReleasestate dicReleasestate; 
	
	
	private Date createtime;//创建时间
	
	private String createuser;//创建人
	
	private String remark;//备注
	

	

/*	public DicReleasestate getDicReleasestate() {
		return dicReleasestate;
	}

	public void setDicReleasestate(DicReleasestate dicReleasestate) {
		this.dicReleasestate = dicReleasestate;
	}*/

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Date getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Clob getFiletext() {
		return filetext;
	}

	public void setFiletext(Clob filetext) {
		this.filetext = filetext;
	}

	public String getHtmlFile() {
		return htmlFile;
	}

	public void setHtmlFile(String htmlFile) {
		this.htmlFile = htmlFile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}



	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNeirongstring() {
		return neirongstring;
	}

	public void setNeirongstring(String neirongstring) {
		this.neirongstring = neirongstring;
	}





}
