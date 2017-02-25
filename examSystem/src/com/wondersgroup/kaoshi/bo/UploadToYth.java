package com.wondersgroup.kaoshi.bo;

import com.wondersgroup.framework.core5.storeprocedure.annotation.SpIn;
import com.wondersgroup.framework.core5.storeprocedure.annotation.SpOut;
import com.wondersgroup.framework.core5.storeprocedure.bean.SpSupportBean;

public class UploadToYth implements SpSupportBean{

	/**
	 * 准考证号
	 */
	@SpIn(order = 1)
	private String examId;
	/**
	 * 鉴定批次号
	 */
	@SpIn(order = 2)
	private String sjMc;
	/**
	 * 经办人
	 */
	@SpIn(order = 3)
	private String name;
	/**
	 * 返回代码
	 */
	@SpOut(order = 4)
	private Long retCode;
	/**
	 * 返回消息
	 */
	@SpOut(order = 5)
	private String retMsg;
	
	public UploadToYth(String examId, String sjMc, String name){
		super();
		this.examId = examId;
		this.sjMc = sjMc;
		this.name = name;
	}
	
	
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String getSjMc() {
		return sjMc;
	}
	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRetCode() {
		return retCode;
	}
	public void setRetCode(Long retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
}
