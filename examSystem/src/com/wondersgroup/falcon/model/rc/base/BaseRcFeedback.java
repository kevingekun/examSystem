package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;

import com.wondersgroup.falcon.model.auth.User;


/**
 * This is an object that contains data related to the rc_feedback table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rc_feedback"
 */

public abstract class BaseRcFeedback  implements Comparable, Serializable {

	public static String REF = "RcFeedback";
	public static String PROP_DT = "Dt";
	public static String PROP_USER = "User";
	public static String PROP_FEEDBACK_STAT_SN = "FeedbackStatSn";
	public static String PROP_CONT = "Cont";
	public static String PROP_ID = "Id";
	public static String PROP_FORM_SN = "FormSn";


	// constructors
	public BaseRcFeedback () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRcFeedback (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String cont;
	private java.util.Date dt;

	// many to one
	private com.wondersgroup.falcon.model.rc.RcForm formSn;
	private User user;
	private com.wondersgroup.falcon.model.rc.RcFeedbackStat feedbackStatSn;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="feedback_sn"
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
	 * Return the value associated with the column: cont
	 */
	public java.lang.String getCont () {
		return cont;
	}

	/**
	 * Set the value related to the column: cont
	 * @param cont the cont value
	 */
	public void setCont (java.lang.String cont) {
		this.cont = cont;
	}



	/**
	 * Return the value associated with the column: dt
	 */
	public java.util.Date getDt () {
		return dt;
	}

	/**
	 * Set the value related to the column: dt
	 * @param dt the dt value
	 */
	public void setDt (java.util.Date dt) {
		this.dt = dt;
	}



	/**
	 * Return the value associated with the column: form_sn
	 */
	public com.wondersgroup.falcon.model.rc.RcForm getFormSn () {
		return formSn;
	}

	/**
	 * Set the value related to the column: form_sn
	 * @param formSn the form_sn value
	 */
	public void setFormSn (com.wondersgroup.falcon.model.rc.RcForm formSn) {
		this.formSn = formSn;
	}



	/**
	 * Return the value associated with the column: USER_ID
	 */
	public User getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: USER_ID
	 * @param user the USER_ID value
	 */
	public void setUser (User user) {
		this.user = user;
	}



	/**
	 * Return the value associated with the column: feedback_stat_sn
	 */
	public com.wondersgroup.falcon.model.rc.RcFeedbackStat getFeedbackStatSn () {
		return feedbackStatSn;
	}

	/**
	 * Set the value related to the column: feedback_stat_sn
	 * @param feedbackStatSn the feedback_stat_sn value
	 */
	public void setFeedbackStatSn (com.wondersgroup.falcon.model.rc.RcFeedbackStat feedbackStatSn) {
		this.feedbackStatSn = feedbackStatSn;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RcFeedback)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RcFeedback rcFeedback = (com.wondersgroup.falcon.model.rc.RcFeedback) obj;
			if (null == this.getId() || null == rcFeedback.getId()) return false;
			else return (this.getId().equals(rcFeedback.getId()));
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