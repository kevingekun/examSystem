package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the rr_question table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rr_question"
 */

public abstract class BaseRrQuestion  implements Comparable, Serializable {

	public static String REF = "RrQuestion";
	public static String PROP_QUESTION_TYPE = "QuestionType";
	public static String PROP_SUBJECT_SN = "SubjectSn";
	public static String PROP_ANSWER_TYPE = "AnswerType";
	public static String PROP_ID = "Id";
	public static String PROP_QUESTION_CONT = "QuestionCont";


	// constructors
	public BaseRrQuestion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRrQuestion (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String questionCont;
	private java.lang.String questionType;
	private java.lang.String answerType;

	// many to one
	private com.wondersgroup.falcon.model.rc.RrSubject subjectSn;

	// collections
	private java.util.Set rrAnswers;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="question_sn"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: question_cont
	 */
	public java.lang.String getQuestionCont () {
		return questionCont;
	}

	/**
	 * Set the value related to the column: question_cont
	 * @param questionCont the question_cont value
	 */
	public void setQuestionCont (java.lang.String questionCont) {
		this.questionCont = questionCont;
	}



	/**
	 * Return the value associated with the column: question_type
	 */
	public java.lang.String getQuestionType () {
		return questionType;
	}

	/**
	 * Set the value related to the column: question_type
	 * @param questionType the question_type value
	 */
	public void setQuestionType (java.lang.String questionType) {
		this.questionType = questionType;
	}



	/**
	 * Return the value associated with the column: answer_type
	 */
	public java.lang.String getAnswerType () {
		return answerType;
	}

	/**
	 * Set the value related to the column: answer_type
	 * @param answerType the answer_type value
	 */
	public void setAnswerType (java.lang.String answerType) {
		this.answerType = answerType;
	}



	/**
	 * Return the value associated with the column: subject_sn
	 */
	public com.wondersgroup.falcon.model.rc.RrSubject getSubjectSn () {
		return subjectSn;
	}

	/**
	 * Set the value related to the column: subject_sn
	 * @param subjectSn the subject_sn value
	 */
	public void setSubjectSn (com.wondersgroup.falcon.model.rc.RrSubject subjectSn) {
		this.subjectSn = subjectSn;
	}



	/**
	 * Return the value associated with the column: RrAnswers
	 */
	public java.util.Set getRrAnswers () {
		return rrAnswers;
	}

	/**
	 * Set the value related to the column: RrAnswers
	 * @param rrAnswers the RrAnswers value
	 */
	public void setRrAnswers (java.util.Set rrAnswers) {
		this.rrAnswers = rrAnswers;
	}

	public void addToRrAnswers (com.wondersgroup.falcon.model.rc.RrAnswer rrAnswer) {
		if (null == getRrAnswers()) setRrAnswers(new java.util.TreeSet());
		getRrAnswers().add(rrAnswer);
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RrQuestion)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RrQuestion rrQuestion = (com.wondersgroup.falcon.model.rc.RrQuestion) obj;
			if (null == this.getId() || null == rrQuestion.getId()) return false;
			else return (this.getId().equals(rrQuestion.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public int compareTo (Object obj) {
		if (obj.hashCode() > hashCode()) return 1;
		else if (obj.hashCode() < hashCode()) return -1;
		else return 0;
	}

	public String toString () {
		return super.toString();
	}


}