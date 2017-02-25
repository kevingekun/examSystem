package com.wondersgroup.falcon.model.exam;

import java.util.Set;

public class ExamRealQuestions {
	private String realquesid;
	private ExamImportance importance;
	private String businesstype;
	private String correctkey;
	private ExamQuestions examquestions;
	private ExamQuestionType examquestype;
	private Set examrqueskeys;
	

	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	public String getCorrectkey() {
		return correctkey;
	}
	public void setCorrectkey(String correctkey) {
		this.correctkey = correctkey;
	}

	public ExamImportance getImportance() {
		return importance;
	}
	public void setImportance(ExamImportance importance) {
		this.importance = importance;
	}
	public ExamQuestions getExamquestions() {
		return examquestions;
	}
	public void setExamquestions(ExamQuestions examquestions) {
		this.examquestions = examquestions;
	}
	public ExamQuestionType getExamquestype() {
		return examquestype;
	}
	public void setExamquestype(ExamQuestionType examquestype) {
		this.examquestype = examquestype;
	}
	public Set getExamrqueskeys() {
		return examrqueskeys;
	}
	public void setExamrqueskeys(Set examrqueskeys) {
		this.examrqueskeys = examrqueskeys;
	}
	public String getRealquesid() {
		return realquesid;
	}
	public void setRealquesid(String realquesid) {
		this.realquesid = realquesid;
	}

}
