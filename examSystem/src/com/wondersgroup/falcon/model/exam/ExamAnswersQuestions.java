package com.wondersgroup.falcon.model.exam;

public class ExamAnswersQuestions {
	private String id;
	private String marks;
	private String correctkey;
	private String answerkey;
	private int gainmark;
	private int ordering;
	private ExamAnswers examanswer;
	private ExamRealQuestions examrealques;
	

	public String getAnswerkey() {
		return answerkey;
	}
	public void setAnswerkey(String answerkey) {
		this.answerkey = answerkey;
	}
	public String getCorrectkey() {
		return correctkey;
	}
	public void setCorrectkey(String correctkey) {
		this.correctkey = correctkey;
	}
	public ExamAnswers getExamanswer() {
		return examanswer;
	}
	public void setExamanswer(ExamAnswers examanswer) {
		this.examanswer = examanswer;
	}

	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
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
	public int getGainmark() {
		return gainmark;
	}
	public void setGainmark(int gainmark) {
		this.gainmark = gainmark;
	}
	public ExamRealQuestions getExamrealques() {
		return examrealques;
	}
	public void setExamrealques(ExamRealQuestions examrealques) {
		this.examrealques = examrealques;
	}
}
