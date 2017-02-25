package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;

import com.wondersgroup.falcon.model.auth.Authority;


/**
 * This is an object that contains data related to the rc_feedback_stat table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rc_feedback_stat"
 */

public abstract class BaseRcFeedbackStat  implements Comparable, Serializable {

	public static String REF = "RcFeedbackStat";
	public static String PROP_AUTHORITY = "Authority";
	public static String PROP_MEMO = "Memo";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";


	// constructors
	public BaseRcFeedbackStat () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRcFeedbackStat (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String name;
	private java.lang.String memo;

	// many to one
	private Authority authority;

	// collections
	private java.util.Set rcFeedbacks;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="feedback_stat_sn"
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
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}



	/**
	 * Return the value associated with the column: memo
	 */
	public java.lang.String getMemo () {
		return memo;
	}

	/**
	 * Set the value related to the column: memo
	 * @param memo the memo value
	 */
	public void setMemo (java.lang.String memo) {
		this.memo = memo;
	}



	/**
	 * Return the value associated with the column: AUTHORITY_ID
	 */
	public Authority getAuthority () {
		return authority;
	}

	/**
	 * Set the value related to the column: AUTHORITY_ID
	 * @param authority the AUTHORITY_ID value
	 */
	public void setAuthority (Authority authority) {
		this.authority = authority;
	}



	/**
	 * Return the value associated with the column: RcFeedbacks
	 */
	public java.util.Set getRcFeedbacks () {
		return rcFeedbacks;
	}

	/**
	 * Set the value related to the column: RcFeedbacks
	 * @param rcFeedbacks the RcFeedbacks value
	 */
	public void setRcFeedbacks (java.util.Set rcFeedbacks) {
		this.rcFeedbacks = rcFeedbacks;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RcFeedbackStat)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RcFeedbackStat rcFeedbackStat = (com.wondersgroup.falcon.model.rc.RcFeedbackStat) obj;
			if (null == this.getId() || null == rcFeedbackStat.getId()) return false;
			else return (this.getId().equals(rcFeedbackStat.getId()));
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