package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the ip_approach table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ip_approach"
 */

public abstract class BaseIpApproach  implements Comparable, Serializable {

	public static String REF = "IpApproach";
	public static String PROP_MEMO = "Memo";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";


	// constructors
	public BaseIpApproach () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseIpApproach (java.lang.String id) {
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

	// collections
	private java.util.Set ipPolicies;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="approach_sn"
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
	 * Return the value associated with the column: IpPolicies
	 */
	public java.util.Set getIpPolicies () {
		return ipPolicies;
	}

	/**
	 * Set the value related to the column: IpPolicies
	 * @param ipPolicies the IpPolicies value
	 */
	public void setIpPolicies (java.util.Set ipPolicies) {
		this.ipPolicies = ipPolicies;
	}

	public void addToIpPolicies (com.wondersgroup.falcon.model.rc.IpPolicy ipPolicy) {
		if (null == getIpPolicies()) setIpPolicies(new java.util.TreeSet());
		getIpPolicies().add(ipPolicy);
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.IpApproach)) return false;
		else {
			com.wondersgroup.falcon.model.rc.IpApproach ipApproach = (com.wondersgroup.falcon.model.rc.IpApproach) obj;
			if (null == this.getId() || null == ipApproach.getId()) return false;
			else return (this.getId().equals(ipApproach.getId()));
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