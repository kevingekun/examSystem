package com.wondersgroup.falcon.question.model;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.Util.StringUtil;
import com.wondersgroup.falcon.question.model.EQuestions;



/**
 * EQuestions entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EAdvice implements java.io.Serializable {
	private static final Log log = LogFactory.getLog(EAdvice.class);
	
	// Fields

	private long id;						//题目ID
	private Long  ST_ID;		//关联的题目
	private String ryId;//工号	
	private String rymc;//真实姓名
	private String content;//修改内容
	private Date dt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getST_ID() {
		return ST_ID;
	}
	public void setST_ID(Long st_id) {
		ST_ID = st_id;
	}
	public String getRyId() {
		return ryId;
	}
	public void setRyId(String ryId) {
		this.ryId = ryId;
	}
	public String getRymc() {
		return rymc;
	}
	public void setRymc(String rymc) {
		this.rymc = rymc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public static Log getLog() {
		return log;
	}
	
	






/** default constructor */
	public EAdvice() {
	}



	/** full constructor */
	public EAdvice(long ST_ID, String ryId,
			String rymc,
			String content,Date dt
		
			
	) {
		this.ST_ID = ST_ID;
		this.ryId = ryId;
		this.rymc =rymc;
		this.content = content;
		this.dt = dt;
		
	}

	

	//-------------------------------
	
	// Property accessors

	}