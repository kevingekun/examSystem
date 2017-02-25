package com.wondersgroup.falcon.model.exam;

import java.util.Date;
import java.util.Set;
public class ExamPaper {
	private String examid;
	private String examname;
	private String examtype;
	private Date effectstarttime;		
	private String examtime;	
	private Date effectendtime;
	private String exammark;

	private Set paperquestion;
	private Set examanswers ;
	public Date getEffectendtime() {
		return effectendtime;
	}
	public void setEffectendtime(Date effectendtime) {
		this.effectendtime = effectendtime;
	}
	public Date getEffectstarttime() {
		return effectstarttime;
	}
	public void setEffectstarttime(Date effectstarttime) {
		this.effectstarttime = effectstarttime;
	}
	public Set getExamanswers() {
		return examanswers;
	}
	public void setExamanswers(Set examanswers) {
		this.examanswers = examanswers;
	}
	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public String getExammark() {
		return exammark;
	}
	public void setExammark(String exammark) {
		this.exammark = exammark;
	}
	public String getExamname() {
		return examname;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	public String getExamtime() {
		return examtime;
	}
	public void setExamtime(String examtime) {
		this.examtime = examtime;
	}
	public String getExamtype() {
		return examtype;
	}
	public void setExamtype(String examtype) {
		this.examtype = examtype;
	}
	public Set getPaperquestion() {
		return paperquestion;
	}
	public void setPaperquestion(Set paperquestion) {
		this.paperquestion = paperquestion;
	}

}
