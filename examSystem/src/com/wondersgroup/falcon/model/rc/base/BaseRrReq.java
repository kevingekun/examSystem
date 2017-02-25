package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the rr_req table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rr_req"
 */

public abstract class BaseRrReq  implements Comparable, Serializable {

	public static String REF = "RrReq";
	public static String PROP_REQ_NAME = "ReqName";
	public static String PROP_REQ_INFO = "ReqInfo";
	public static String PROP_CONFIRM = "Confirm";
	public static String PROP_REQ_OBJ = "ReqObj";
	public static String PROP_ID = "Id";
	public static String PROP_DEPT_SN = "DeptSn";


	// constructors
	public BaseRrReq () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRrReq (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String reqName;
	private java.lang.String reqInfo;
	private java.lang.String reqObj;
	private java.lang.String confirm;

	// many to one
	private com.wondersgroup.falcon.model.rc.RrDept deptSn;

	// collections
	private java.util.Set rrSubjects;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="req_sn"
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
	 * Return the value associated with the column: req_name
	 */
	public java.lang.String getReqName () {
		return reqName;
	}

	/**
	 * Set the value related to the column: req_name
	 * @param reqName the req_name value
	 */
	public void setReqName (java.lang.String reqName) {
		this.reqName = reqName;
	}



	/**
	 * Return the value associated with the column: req_info
	 */
	public java.lang.String getReqInfo () {
		return reqInfo;
	}

	/**
	 * Set the value related to the column: req_info
	 * @param reqInfo the req_info value
	 */
	public void setReqInfo (java.lang.String reqInfo) {
		this.reqInfo = reqInfo;
	}



	/**
	 * Return the value associated with the column: req_obj
	 */
	public java.lang.String getReqObj () {
		return reqObj;
	}

	/**
	 * Set the value related to the column: req_obj
	 * @param reqObj the req_obj value
	 */
	public void setReqObj (java.lang.String reqObj) {
		this.reqObj = reqObj;
	}



	/**
	 * Return the value associated with the column: confirm
	 */
	public java.lang.String getConfirm () {
		return confirm;
	}

	/**
	 * Set the value related to the column: confirm
	 * @param confirm the confirm value
	 */
	public void setConfirm (java.lang.String confirm) {
		this.confirm = confirm;
	}



	/**
	 * Return the value associated with the column: dept_sn
	 */
	public com.wondersgroup.falcon.model.rc.RrDept getDeptSn () {
		return deptSn;
	}

	/**
	 * Set the value related to the column: dept_sn
	 * @param deptSn the dept_sn value
	 */
	public void setDeptSn (com.wondersgroup.falcon.model.rc.RrDept deptSn) {
		this.deptSn = deptSn;
	}



	/**
	 * Return the value associated with the column: RrSubjects
	 */
	public java.util.Set getRrSubjects () {
		return rrSubjects;
	}

	/**
	 * Set the value related to the column: RrSubjects
	 * @param rrSubjects the RrSubjects value
	 */
	public void setRrSubjects (java.util.Set rrSubjects) {
		this.rrSubjects = rrSubjects;
	}

	public void addToRrSubjects (com.wondersgroup.falcon.model.rc.RrSubject rrSubject) {
		if (null == getRrSubjects()) setRrSubjects(new java.util.TreeSet());
		getRrSubjects().add(rrSubject);
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RrReq)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RrReq rrReq = (com.wondersgroup.falcon.model.rc.RrReq) obj;
			if (null == this.getId() || null == rrReq.getId()) return false;
			else return (this.getId().equals(rrReq.getId()));
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