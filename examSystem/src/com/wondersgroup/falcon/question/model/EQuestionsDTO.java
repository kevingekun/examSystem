package com.wondersgroup.falcon.question.model;

import java.util.Date;

public class EQuestionsDTO {
	private long id;
	private String difficulty;//难易度
	private String questiontype;//试题类型
	private String importence;//重要程度
	private String profession;//工种
	private String tg;//题干
	private String xx;//选项
	private String da;//答案
	private String reference;//试题出处
	private Date lrsj;//录入时间
	private String grade;//等级
	private String expert;//专家
	private String remark;//备注
	private long state;//审核状态
	private long usageCount;//使用次数
	private String stFz;//试题分值
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getQuestiontype() {
		return questiontype;
	}
	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}
	public String getImportence() {
		return importence;
	}
	public void setImportence(String importence) {
		this.importence = importence;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getTg() {
		return tg;
	}
	public void setTg(String tg) {
		this.tg = tg;
	}
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getLrsj() {
		return lrsj;
	}
	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getExpert() {
		return expert;
	}
	public void setExpert(String expert) {
		this.expert = expert;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getState() {
		return state;
	}
	public void setState(long state) {
		this.state = state;
	}
	public long getUsageCount() {
		return usageCount;
	}
	public void setUsageCount(long usageCount) {
		this.usageCount = usageCount;
	}
	public String getStFz() {
		return stFz;
	}
	public void setStFz(String stFz) {
		this.stFz = stFz;
	}
	public String getXx() {
		return xx;
	}
	public void setXx(String xx) {
		this.xx = xx;
	}
	public String getDa() {
		return da;
	}
	public void setDa(String da) {
		this.da = da;
	}
	
	
	
}
