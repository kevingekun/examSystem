package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the rr_subject table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rr_subject"
 */

public abstract class BaseRrSubject  implements Comparable, Serializable {

	public static String REF = "RrSubject";
	public static String PROP_SUBJECT_STAT = "SubjectStat";
	public static String PROP_REQ_SN = "ReqSn";
	public static String PROP_BEGIN = "Begin";
	public static String PROP_SUBJECT_NAME = "SubjectName";
	public static String PROP_END = "End";
	public static String PROP_CRE_DATE = "CreDate";
	public static String PROP_ID = "Id";
	public static String PROP_SUBJECT_DESC = "SubjectDesc";


	// constructors
	public BaseRrSubject () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRrSubject (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String subjectName;
	private java.lang.String subjectDesc;
	private java.util.Date begin;
	private java.util.Date end;
	private java.lang.String subjectStat;
	private java.util.Date creDate;

	// many to one
	private com.wondersgroup.falcon.model.rc.RrReq reqSn;

	// collections
	private java.util.Set rrQuestions;
	private java.util.Set rrResources;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="subject_sn"
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
	 * Return the value associated with the column: subject_name
	 */
	public java.lang.String getSubjectName () {
		return subjectName;
	}

	/**
	 * Set the value related to the column: subject_name
	 * @param subjectName the subject_name value
	 */
	public void setSubjectName (java.lang.String subjectName) {
		this.subjectName = subjectName;
	}



	/**
	 * Return the value associated with the column: subject_desc
	 */
	public java.lang.String getSubjectDesc () {
		return subjectDesc;
	}

	/**
	 * Set the value related to the column: subject_desc
	 * @param subjectDesc the subject_desc value
	 */
	public void setSubjectDesc (java.lang.String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}



	/**
	 * Return the value associated with the column: begin
	 */
	public java.util.Date getBegin () {
		return begin;
	}

	/**
	 * Set the value related to the column: begin
	 * @param begin the begin value
	 */
	public void setBegin (java.util.Date begin) {
		this.begin = begin;
	}



	/**
	 * Return the value associated with the column: end
	 */
	public java.util.Date getEnd () {
		return end;
	}

	/**
	 * Set the value related to the column: end
	 * @param end the end value
	 */
	public void setEnd (java.util.Date end) {
		this.end = end;
	}



	/**
	 * Return the value associated with the column: subject_stat
	 */
	public java.lang.String getSubjectStat () {
		return subjectStat;
	}

	/**
	 * Set the value related to the column: subject_stat
	 * @param subjectStat the subject_stat value
	 */
	public void setSubjectStat (java.lang.String subjectStat) {
		this.subjectStat = subjectStat;
	}



	/**
	 * Return the value associated with the column: cre_date
	 */
	public java.util.Date getCreDate () {
		return creDate;
	}

	/**
	 * Set the value related to the column: cre_date
	 * @param creDate the cre_date value
	 */
	public void setCreDate (java.util.Date creDate) {
		this.creDate = creDate;
	}



	/**
	 * Return the value associated with the column: req_sn
	 */
	public com.wondersgroup.falcon.model.rc.RrReq getReqSn () {
		return reqSn;
	}

	/**
	 * Set the value related to the column: req_sn
	 * @param reqSn the req_sn value
	 */
	public void setReqSn (com.wondersgroup.falcon.model.rc.RrReq reqSn) {
		this.reqSn = reqSn;
	}



	/**
	 * Return the value associated with the column: RrQuestions
	 */
	public java.util.Set getRrQuestions () {
		return rrQuestions;
	}

	/**
	 * Set the value related to the column: RrQuestions
	 * @param rrQuestions the RrQuestions value
	 */
	public void setRrQuestions (java.util.Set rrQuestions) {
		this.rrQuestions = rrQuestions;
	}

	public void addToRrQuestions (com.wondersgroup.falcon.model.rc.RrQuestion rrQuestion) {
		if (null == getRrQuestions()) setRrQuestions(new java.util.TreeSet());
		getRrQuestions().add(rrQuestion);
	}



	/**
	 * Return the value associated with the column: RrResources
	 */
	public java.util.Set getRrResources () {
		return rrResources;
	}

	/**
	 * Set the value related to the column: RrResources
	 * @param rrResources the RrResources value
	 */
	public void setRrResources (java.util.Set rrResources) {
		this.rrResources = rrResources;
	}

	public void addToRrResources (com.wondersgroup.falcon.model.rc.RrResource rrResource) {
		if (null == getRrResources()) setRrResources(new java.util.TreeSet());
		getRrResources().add(rrResource);
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RrSubject)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RrSubject rrSubject = (com.wondersgroup.falcon.model.rc.RrSubject) obj;
			if (null == this.getId() || null == rrSubject.getId()) return false;
			else return (this.getId().equals(rrSubject.getId()));
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