package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;

import com.wondersgroup.falcon.model.citizeninfo.CompanyInfo;
import com.wondersgroup.falcon.model.citizeninfo.PersonInfo;


/**
 * This is an object that contains data related to the rr_result table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rr_result"
 */

public abstract class BaseRrResult  implements Comparable, Serializable {

	public static String REF = "RrResult";
	public static String PROP_ANSWER_SN = "AnswerSn";
	public static String PROP_USER = "User";
	public static String PROP_RESULT_ANSWER = "ResultAnswer";
	public static String PROP_CALLER_SN = "CallerSn";
	public static String PROP_RESULT_DATE = "ResultDate";
	public static String PROP_ID = "Id";


	// constructors
	public BaseRrResult () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRrResult (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.util.Date resultDate;
	private java.lang.String resultAnswer;

	// many to one
	private com.wondersgroup.falcon.model.rc.RrCaller callerSn;
	private com.wondersgroup.falcon.model.rc.RrAnswer answerSn;
	private com.wondersgroup.falcon.model.auth.User user;

	private PersonInfo personInfo;
	
	private CompanyInfo companyInfo;

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="result_sn"
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
	 * Return the value associated with the column: result_date
	 */
	public java.util.Date getResultDate () {
		return resultDate;
	}

	/**
	 * Set the value related to the column: result_date
	 * @param resultDate the result_date value
	 */
	public void setResultDate (java.util.Date resultDate) {
		this.resultDate = resultDate;
	}



	/**
	 * Return the value associated with the column: result_answer
	 */
	public java.lang.String getResultAnswer () {
		return resultAnswer;
	}

	/**
	 * Set the value related to the column: result_answer
	 * @param resultAnswer the result_answer value
	 */
	public void setResultAnswer (java.lang.String resultAnswer) {
		this.resultAnswer = resultAnswer;
	}



	/**
	 * Return the value associated with the column: CALLER_SN
	 */
	public com.wondersgroup.falcon.model.rc.RrCaller getCallerSn () {
		return callerSn;
	}

	/**
	 * Set the value related to the column: CALLER_SN
	 * @param callerSn the CALLER_SN value
	 */
	public void setCallerSn (com.wondersgroup.falcon.model.rc.RrCaller callerSn) {
		this.callerSn = callerSn;
	}



	/**
	 * Return the value associated with the column: answer_sn
	 */
	public com.wondersgroup.falcon.model.rc.RrAnswer getAnswerSn () {
		return answerSn;
	}

	/**
	 * Set the value related to the column: answer_sn
	 * @param answerSn the answer_sn value
	 */
	public void setAnswerSn (com.wondersgroup.falcon.model.rc.RrAnswer answerSn) {
		this.answerSn = answerSn;
	}



	/**
	 * Return the value associated with the column: USER_ID
	 */
	public com.wondersgroup.falcon.model.auth.User getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: USER_ID
	 * @param user the USER_ID value
	 */
	public void setUser (com.wondersgroup.falcon.model.auth.User user) {
		this.user = user;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RrResult)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RrResult rrResult = (com.wondersgroup.falcon.model.rc.RrResult) obj;
			if (null == this.getId() || null == rrResult.getId()) return false;
			else return (this.getId().equals(rrResult.getId()));
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