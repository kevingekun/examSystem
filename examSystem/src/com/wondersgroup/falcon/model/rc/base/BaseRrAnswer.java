package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the rr_answer table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rr_answer"
 */

public abstract class BaseRrAnswer  implements Comparable, Serializable {

	public static String REF = "RrAnswer";
	public static String PROP_QUESTION_SN = "QuestionSn";
	public static String PROP_ANSWER_VAR = "AnswerVar";
	public static String PROP_ID = "Id";
	public static String PROP_ANSWER_CONT = "AnswerCont";


	// constructors
	public BaseRrAnswer () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRrAnswer (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String answerCont;
	private java.math.BigDecimal answerVar;

	// many to one
	private com.wondersgroup.falcon.model.rc.RrQuestion questionSn;

	// collections
	private java.util.Set rrResults;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="answer_sn"
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
	 * Return the value associated with the column: answer_cont
	 */
	public java.lang.String getAnswerCont () {
		return answerCont;
	}

	/**
	 * Set the value related to the column: answer_cont
	 * @param answerCont the answer_cont value
	 */
	public void setAnswerCont (java.lang.String answerCont) {
		this.answerCont = answerCont;
	}



	/**
	 * Return the value associated with the column: answer_var
	 */
	public java.math.BigDecimal getAnswerVar () {
		return answerVar;
	}

	/**
	 * Set the value related to the column: answer_var
	 * @param answerVar the answer_var value
	 */
	public void setAnswerVar (java.math.BigDecimal answerVar) {
		this.answerVar = answerVar;
	}



	/**
	 * Return the value associated with the column: question_sn
	 */
	public com.wondersgroup.falcon.model.rc.RrQuestion getQuestionSn () {
		return questionSn;
	}

	/**
	 * Set the value related to the column: question_sn
	 * @param questionSn the question_sn value
	 */
	public void setQuestionSn (com.wondersgroup.falcon.model.rc.RrQuestion questionSn) {
		this.questionSn = questionSn;
	}



	/**
	 * Return the value associated with the column: RrResults
	 */
	public java.util.Set getRrResults () {
		return rrResults;
	}

	/**
	 * Set the value related to the column: RrResults
	 * @param rrResults the RrResults value
	 */
	public void setRrResults (java.util.Set rrResults) {
		this.rrResults = rrResults;
	}

	public void addToRrResults (com.wondersgroup.falcon.model.rc.RrResult rrResult) {
		if (null == getRrResults()) setRrResults(new java.util.TreeSet());
		getRrResults().add(rrResult);
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RrAnswer)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RrAnswer rrAnswer = (com.wondersgroup.falcon.model.rc.RrAnswer) obj;
			if (null == this.getId() || null == rrAnswer.getId()) return false;
			else return (this.getId().equals(rrAnswer.getId()));
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