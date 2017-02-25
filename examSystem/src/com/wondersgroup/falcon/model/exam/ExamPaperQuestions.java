package com.wondersgroup.falcon.model.exam;

public class ExamPaperQuestions {
	private String id;
	private String questionmarks;
	private int ordering;
	private ExamRealQuestions realquestion;
    private ExamPaper exampaper;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ExamPaper getExampaper() {
		return exampaper;
	}
	public void setExampaper(ExamPaper exampaper) {
		this.exampaper = exampaper;
	}

	public String getQuestionmarks() {
		return questionmarks;
	}
	public void setQuestionmarks(String questionmarks) {
		this.questionmarks = questionmarks;
	}

	public ExamRealQuestions getRealquestion() {
		return realquestion;
	}
	public void setRealquestion(ExamRealQuestions realquestion) {
		this.realquestion = realquestion;
	}
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
}
