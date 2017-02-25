package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;

import com.wondersgroup.falcon.model.citizeninfo.CompanyInfo;
import com.wondersgroup.falcon.model.citizeninfo.PersonInfo;


/**
 * This is an object that contains data related to the rr_resource table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rr_resource"
 */

public abstract class BaseRrResource  implements Comparable, Serializable {

	public static String REF = "RrResource";
	public static String PROP_STAT = "Stat";
	public static String PROP_SUBJECT_SN = "SubjectSn";
	public static String PROP_USER = "User";
	public static String PROP_CALLER_SN = "CallerSn";
	
	public static String PROP_ID = "Id";


	// constructors
	public BaseRrResource () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRrResource (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String stat;

	// many to one
	private com.wondersgroup.falcon.model.rc.RrSubject subjectSn;
	private com.wondersgroup.falcon.model.rc.RrCaller callerSn;
	private com.wondersgroup.falcon.model.auth.User user;
	
	private PersonInfo personInfo;
     
	private CompanyInfo companyInfo;
	
	
	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="resource_sn"
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
	 * Return the value associated with the column: STAT
	 */
	public java.lang.String getStat () {
		return stat;
	}

	/**
	 * Set the value related to the column: STAT
	 * @param stat the STAT value
	 */
	public void setStat (java.lang.String stat) {
		this.stat = stat;
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
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RrResource)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RrResource rrResource = (com.wondersgroup.falcon.model.rc.RrResource) obj;
			if (null == this.getId() || null == rrResource.getId()) return false;
			else return (this.getId().equals(rrResource.getId()));
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


}