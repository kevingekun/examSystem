/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.kaoshi.bo;

/**
 * @author Administrator
 *
 */
public class EAnswertemp {
	private Long id;	//主键ID
	private Long userid;	//用户ID
	private Long examid;//试卷ID
	private String questionid;	//问题ID
	private String answer;	//	答案
	private String questiontype;	//		问题类型
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getExamid() {
		return examid;
	}
	public void setExamid(Long examid) {
		this.examid = examid;
	}
	public String getQuestionid() {
		return questionid;
	}
	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestiontype() {
		return questiontype;
	}
	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}
	

}
