/** 
 * 
 * author:mxk 
 */
package com.wondersgroup.popedom.bo;

import java.util.Date;

/**
 * @author Administrator
 * 
 */
public class EuserTest {
	private int id;// 主键id
	private String provenance;// 出处
	private String referencenum;// 文号
	private String businesstype;// 业务类型
	private String servicetype;// 服务类型
	private String difficultylevel;// 难易度
	private String questiontype;// 试题类型
	private Date time;
	private String questionprop;// 试题性质
	private String topic;// 题目
	private String options;// 选项
	private String answer;// 答案

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public String getReferencenum() {
		return referencenum;
	}

	public void setReferencenum(String referencenum) {
		this.referencenum = referencenum;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getDifficultylevel() {
		return difficultylevel;
	}

	public void setDifficultylevel(String difficultylevel) {
		this.difficultylevel = difficultylevel;
	}

	public String getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getQuestionprop() {
		return questionprop;
	}

	public void setQuestionprop(String questionprop) {
		this.questionprop = questionprop;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
