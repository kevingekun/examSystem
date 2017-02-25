package com.wondersgroup.falcon.model.exam;

public class ExamRqueKeys {
	private String id;
	private int ordering;
	private ExamRealQuestions examrques;
	private ExamKeys examkey;
	
	public ExamKeys getExamkey() {
		return examkey;
	}
	public void setExamkey(ExamKeys examkey) {
		this.examkey = examkey;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public ExamRealQuestions getExamrques() {
		return examrques;
	}
	public void setExamrques(ExamRealQuestions examrques) {
		this.examrques = examrques;
	}

}
