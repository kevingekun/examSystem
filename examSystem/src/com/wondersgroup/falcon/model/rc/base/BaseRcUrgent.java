package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;

import com.wondersgroup.falcon.model.auth.Authority;
import com.wondersgroup.falcon.model.auth.User;


/**
 * This is an object that contains data related to the rc_urgent table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rc_urgent"
 */

public abstract class BaseRcUrgent  implements Comparable, Serializable {

	public static String REF = "RcUrgent";
	public static String PROP_AUTHORITY = "Authority";
	public static String PROP_MEMO = "Memo";
	public static String PROP_DT = "Dt";
	public static String PROP_USER = "User";
	public static String PROP_CONT = "Cont";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";
	public static String PROP_FORM_SN = "FormSn";


	// constructors
	public BaseRcUrgent () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRcUrgent (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String name;
	private java.lang.String cont;
	private java.lang.String memo;
	private java.util.Date dt;

	// many to one
	private com.wondersgroup.falcon.model.rc.RcForm formSn;
	private Authority authority;
	private User user;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="urgent_sn"
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





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RcUrgent)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RcUrgent rcUrgent = (com.wondersgroup.falcon.model.rc.RcUrgent) obj;
			if (null == this.getId() || null == rcUrgent.getId()) return false;
			else return (this.getId().equals(rcUrgent.getId()));
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