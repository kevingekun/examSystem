package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;

import com.wondersgroup.falcon.model.auth.User;


/**
 * This is an object that contains data related to the ip_policy table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ip_policy"
 */

public abstract class BaseIpPolicy  implements Comparable, Serializable {

	public static String REF = "IpPolicy";
	public static String PROP_BEGIN = "Begin";
	public static String PROP_SUBJECT = "Subject";
	public static String PROP_USER = "User";
	public static String PROP_END = "End";
	public static String PROP_CREDAT = "Credat";
	public static String PROP_OBJECT = "Object";
	public static String PROP_CONTENT = "Content";
	public static String PROP_ID = "Id";
	public static String PROP_APPROACH_SN = "ApproachSn";


	// constructors
	public BaseIpPolicy () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseIpPolicy (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String subject;
	private java.lang.String content;
	private java.lang.String object;
	private java.util.Date credat;
	private java.util.Date begin;
	private java.util.Date end;

	// many to one
	private com.wondersgroup.falcon.model.rc.IpApproach approachSn;
	private User user;

	// collections
	private java.util.Set ipInforms;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="policy_sn"
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
	 * Return the value associated with the column: subject
	 */
	public java.lang.String getSubject () {
		return subject;
	}

	/**
	 * Set the value related to the column: subject
	 * @param subject the subject value
	 */
	public void setSubject (java.lang.String subject) {
		this.subject = subject;
	}



	/**
	 * Return the value associated with the column: content
	 */
	public java.lang.String getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: content
	 * @param content the content value
	 */
	public void setContent (java.lang.String content) {
		this.content = content;
	}



	/**
	 * Return the value associated with the column: object
	 */
	public java.lang.String getObject () {
		return object;
	}

	/**
	 * Set the value related to the column: object
	 * @param object the object value
	 */
	public void setObject (java.lang.String object) {
		this.object = object;
	}



	/**
	 * Return the value associated with the column: credat
	 */
	public java.util.Date getCredat () {
		return credat;
	}

	/**
	 * Set the value related to the column: credat
	 * @param credat the credat value
	 */
	public void setCredat (java.util.Date credat) {
		this.credat = credat;
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
	 * Return the value associated with the column: approach_sn
	 */
	public com.wondersgroup.falcon.model.rc.IpApproach getApproachSn () {
		return approachSn;
	}

	/**
	 * Set the value related to the column: approach_sn
	 * @param approachSn the approach_sn value
	 */
	public void setApproachSn (com.wondersgroup.falcon.model.rc.IpApproach approachSn) {
		this.approachSn = approachSn;
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
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.IpPolicy)) return false;
		else {
			com.wondersgroup.falcon.model.rc.IpPolicy ipPolicy = (com.wondersgroup.falcon.model.rc.IpPolicy) obj;
			if (null == this.getId() || null == ipPolicy.getId()) return false;
			else return (this.getId().equals(ipPolicy.getId()));
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