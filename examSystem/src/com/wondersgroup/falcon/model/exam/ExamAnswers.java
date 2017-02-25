package com.wondersgroup.falcon.model.exam;

import java.util.Date;
import java.util.Set;

public class ExamAnswers {
	private String examanswerid;
	private String username;
	private String examid;
	private ExamAnswerStatus examanswerstatus;
	private String usetime;		
	private Date starttime;	
	private Date endtime;
	private int wholemark;
	private ExamPaper exampaper;
	private Set examansrques;
	 
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public ExamPaper getExampaper() {
		return exampaper;
	}
	public void setExampaper(ExamPaper exampaper) {
		this.exampaper = exampaper;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getUsetime() {
		return usetime;
	}
	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}

	public int getWholemark() {
		return wholemark;
	}
	public void setWholemark(int wholemark) {
		this.wholemark = wholemark;
	}
	public String getExamanswerid() {
		return examanswerid;
	}
	public void setExamanswerid(String examanswerid) {
		this.examanswerid = examanswerid;
	}
	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public ExamAnswerStatus getExamanswerstatus() {
		return examanswerstatus;
	}
	public void setExamanswerstatus(ExamAnswerStatus examanswerstatus) {
		this.examanswerstatus = examanswerstatus;
	}
	public Set getExamansrques() {
		return examansrques;
	}
	public void setExamansrques(Set examansrques) {
		this.examansrques = examansrques;
	}
	
}
