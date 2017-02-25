package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the rr_dept table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rr_dept"
 */

public abstract class BaseRrDept  implements Comparable, Serializable {

	public static String REF = "RrDept";
	public static String PROP_DEPT_NAME = "DeptName";
	public static String PROP_DEPT_DESC = "DeptDesc";
	public static String PROP_ID = "Id";


	// constructors
	public BaseRrDept () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRrDept (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String deptName;
	private java.lang.String deptDesc;

	// collections
	private java.util.Set rrReqs;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="dept_sn"
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
	 * Return the value associated with the column: dept_name
	 */
	public java.lang.String getDeptName () {
		return deptName;
	}

	/**
	 * Set the value related to the column: dept_name
	 * @param deptName the dept_name value
	 */
	public void setDeptName (java.lang.String deptName) {
		this.deptName = deptName;
	}



	/**
	 * Return the value associated with the column: dept_desc
	 */
	public java.lang.String getDeptDesc () {
		return deptDesc;
	}

	/**
	 * Set the value related to the column: dept_desc
	 * @param deptDesc the dept_desc value
	 */
	public void setDeptDesc (java.lang.String deptDesc) {
		this.deptDesc = deptDesc;
	}



	/**
	 * Return the value associated with the column: RrReqs
	 */
	public java.util.Set getRrReqs () {
		return rrReqs;
	}

	/**
	 * Set the value related to the column: RrReqs
	 * @param rrReqs the RrReqs value
	 */
	public void setRrReqs (java.util.Set rrReqs) {
		this.rrReqs = rrReqs;
	}

	public void addToRrReqs (com.wondersgroup.falcon.model.rc.RrReq rrReq) {
		if (null == getRrReqs()) setRrReqs(new java.util.TreeSet());
		getRrReqs().add(rrReq);
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RrDept)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RrDept rrDept = (com.wondersgroup.falcon.model.rc.RrDept) obj;
			if (null == this.getId() || null == rrDept.getId()) return false;
			else return (this.getId().equals(rrDept.getId()));
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