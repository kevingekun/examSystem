package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the rc_caller table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rc_caller"
 */

public abstract class BaseRcCaller  implements Comparable, Serializable {

	public static String REF = "RcCaller";
	public static String PROP_SEX = "Sex";
	public static String PROP_HOUSEHOLD = "Household";
	public static String PROP_IDENTITY = "Identity";
	public static String PROP_ADDR = "Addr";
	public static String PROP_OCCUPATION = "Occupation";
	public static String PROP_POCODE = "Pocode";
	public static String PROP_TEL = "Tel";
	public static String PROP_COMPANY = "Company";
	public static String PROP_NAME = "Name";
	public static String PROP_AGE = "Age";
	public static String PROP_ID = "Id";


	// constructors
	public BaseRcCaller () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRcCaller (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String name;
	private java.lang.String identity;
	private java.lang.String sex;
	private java.lang.Integer age;
	private java.lang.String occupation;
	private java.lang.String addr;
	private java.lang.String pocode;
	private java.lang.String company;
	private java.lang.String tel;
	private java.lang.String household;

	// collections
	private java.util.Set rcForms;
	private java.util.Set rrResults;
	private java.util.Set ipInforms;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="CALLER_SN"
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
	 * Return the value associated with the column: NAME
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: NAME
	 * @param name the NAME value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}



	/**
	 * Return the value associated with the column: IDENTITY
	 */
	public java.lang.String getIdentity () {
		return identity;
	}

	/**
	 * Set the value related to the column: IDENTITY
	 * @param identity the IDENTITY value
	 */
	public void setIdentity (java.lang.String identity) {
		this.identity = identity;
	}



	/**
	 * Return the value associated with the column: SEX
	 */
	public java.lang.String getSex () {
		return sex;
	}

	/**
	 * Set the value related to the column: SEX
	 * @param sex the SEX value
	 */
	public void setSex (java.lang.String sex) {
		this.sex = sex;
	}



	/**
	 * Return the value associated with the column: AGE
	 */
	public java.lang.Integer getAge () {
		return age;
	}

	/**
	 * Set the value related to the column: AGE
	 * @param age the AGE value
	 */
	public void setAge (java.lang.Integer age) {
		this.age = age;
	}



	/**
	 * Return the value associated with the column: OCCUPATION
	 */
	public java.lang.String getOccupation () {
		return occupation;
	}

	/**
	 * Set the value related to the column: OCCUPATION
	 * @param occupation the OCCUPATION value
	 */
	public void setOccupation (java.lang.String occupation) {
		this.occupation = occupation;
	}



	/**
	 * Return the value associated with the column: ADDR
	 */
	public java.lang.String getAddr () {
		return addr;
	}

	/**
	 * Set the value related to the column: ADDR
	 * @param addr the ADDR value
	 */
	public void setAddr (java.lang.String addr) {
		this.addr = addr;
	}



	/**
	 * Return the value associated with the column: POCODE
	 */
	public java.lang.String getPocode () {
		return pocode;
	}

	/**
	 * Set the value related to the column: POCODE
	 * @param pocode the POCODE value
	 */
	public void setPocode (java.lang.String pocode) {
		this.pocode = pocode;
	}



	/**
	 * Return the value associated with the column: COMPANY
	 */
	public java.lang.String getCompany () {
		return company;
	}

	/**
	 * Set the value related to the column: COMPANY
	 * @param company the COMPANY value
	 */
	public void setCompany (java.lang.String company) {
		this.company = company;
	}



	/**
	 * Return the value associated with the column: TEL
	 */
	public java.lang.String getTel () {
		return tel;
	}

	/**
	 * Set the value related to the column: TEL
	 * @param tel the TEL value
	 */
	public void setTel (java.lang.String tel) {
		this.tel = tel;
	}



	/**
	 * Return the value associated with the column: household
	 */
	public java.lang.String getHousehold () {
		return household;
	}

	/**
	 * Set the value related to the column: household
	 * @param household the household value
	 */
	public void setHousehold (java.lang.String household) {
		this.household = household;
	}



	/**
	 * Return the value associated with the column: RcForms
	 */
	public java.util.Set getRcForms () {
		return rcForms;
	}

	/**
	 * Set the value related to the column: RcForms
	 * @param rcForms the RcForms value
	 */
	public void setRcForms (java.util.Set rcForms) {
		this.rcForms = rcForms;
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



	/**
	 * Return the value associated with the column: IpInforms
	 */
	public java.util.Set getIpInforms () {
		return ipInforms;
	}

	/**
	 * Set the value related to the column: IpInforms
	 * @param ipInforms the IpInforms value
	 */
	public void setIpInforms (java.util.Set ipInforms) {
		this.ipInforms = ipInforms;
	}

	public void addToIpInforms (com.wondersgroup.falcon.model.rc.IpInform ipInform) {
		if (null == getIpInforms()) setIpInforms(new java.util.TreeSet());
		getIpInforms().add(ipInform);
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RcCaller)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RcCaller rcCaller = (com.wondersgroup.falcon.model.rc.RcCaller) obj;
			if (null == this.getId() || null == rcCaller.getId()) return false;
			else return (this.getId().equals(rcCaller.getId()));
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