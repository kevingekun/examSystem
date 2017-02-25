package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;

import com.wondersgroup.falcon.model.citizeninfo.CompanyInfo;
import com.wondersgroup.falcon.model.citizeninfo.PersonInfo;


/**
 * This is an object that contains data related to the ip_inform table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ip_inform"
 */

public abstract class BaseIpInform  implements Comparable, Serializable {

	public static String REF = "IpInform";
	public static String PROP_POLICY_SN = "PolicySn";
	public static String PROP_STAT = "Stat";
	public static String PROP_CALLER_SN = "CallerSn";
	public static String PROP_ID = "Id";
	//public static String User_ID = "UserId";


	// constructors
	public BaseIpInform () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseIpInform (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String stat;
	
	private java.lang.String userid;

	// many to one
	private com.wondersgroup.falcon.model.rc.RcCaller callerSn;
	private PersonInfo personInfo;
	
	private com.wondersgroup.falcon.model.rc.IpPolicy policySn;
   //many to one 单位采集用户
	private CompanyInfo companyInfo;


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="inform_sn"
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
	 * Return the value associated with the column: stat
	 */
	public java.lang.String getStat () {
		return stat;
	}

	/**
	 * Set the value related to the column: stat
	 * @param stat the stat value
	 */
	public void setStat (java.lang.String stat) {
		this.stat = stat;
	}



	/**
	 * Return the value associated with the column: CALLER_SN
	 */
	public com.wondersgroup.falcon.model.rc.RcCaller getCallerSn () {
		return callerSn;
	}

	/**
	 * Set the value related to the column: CALLER_SN
	 * @param callerSn the CALLER_SN value
	 */
	public void setCallerSn (com.wondersgroup.falcon.model.rc.RcCaller callerSn) {
		this.callerSn = callerSn;
	}



	/**
	 * Return the value associated with the column: policy_sn
	 */
	public com.wondersgroup.falcon.model.rc.IpPolicy getPolicySn () {
		return policySn;
	}

	/**
	 * Set the value related to the column: policy_sn
	 * @param policySn the policy_sn value
	 */
	public void setPolicySn (com.wondersgroup.falcon.model.rc.IpPolicy policySn) {
		this.policySn = policySn;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.IpInform)) return false;
		else {
			com.wondersgroup.falcon.model.rc.IpInform ipInform = (com.wondersgroup.falcon.model.rc.IpInform) obj;
			if (null == this.getId() || null == ipInform.getId()) return false;
			else return (this.getId().equals(ipInform.getId()));
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

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public java.lang.String getUserid() {
		return userid;
	}

	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}


}