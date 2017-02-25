package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the auth_group table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="auth_group"
 */

public abstract class BaseAuthGroup  implements Comparable, Serializable {

	public static String REF = "AuthGroup";
	public static String PROP_AUTHORITY = "Authority";
	public static String PROP_AUT_AUTHORITY = "AutAuthority";
	public static String PROP_MEMO = "Memo";
	public static String PROP_ID = "Id";


	// constructors
	public BaseAuthGroup () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAuthGroup (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String memo;

	// many to one
	private com.wondersgroup.falcon.model.auth.Authority autAuthority;
	private com.wondersgroup.falcon.model.auth.Authority authority;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="auth_group_sn"
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
	 * Return the value associated with the column: AUT_AUTHORITY_ID
	 */
	public com.wondersgroup.falcon.model.auth.Authority getAutAuthority () {
		return autAuthority;
	}

	/**
	 * Set the value related to the column: AUT_AUTHORITY_ID
	 * @param autAuthority the AUT_AUTHORITY_ID value
	 */
	public void setAutAuthority (com.wondersgroup.falcon.model.auth.Authority autAuthority) {
		this.autAuthority = autAuthority;
	}



	/**
	 * Return the value associated with the column: AUTHORITY_ID
	 */
	public com.wondersgroup.falcon.model.auth.Authority getAuthority () {
		return authority;
	}

	/**
	 * Set the value related to the column: AUTHORITY_ID
	 * @param authority the AUTHORITY_ID value
	 */
	public void setAuthority (com.wondersgroup.falcon.model.auth.Authority authority) {
		this.authority = authority;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.AuthGroup)) return false;
		else {
			com.wondersgroup.falcon.model.rc.AuthGroup authGroup = (com.wondersgroup.falcon.model.rc.AuthGroup) obj;
			if (null == this.getId() || null == authGroup.getId()) return false;
			else return (this.getId().equals(authGroup.getId()));
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