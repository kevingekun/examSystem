package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;

import com.wondersgroup.falcon.model.auth.Authority;
import com.wondersgroup.falcon.model.auth.User;


/**
 * This is an object that contains data related to the rc_form table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rc_form"
 */

public abstract class BaseRcForm  implements Comparable, Serializable {

	public static String REF = "RcForm";
	public static String PROP_REFERENCE_SN = "ReferenceSn";
	public static String PROP_PERSONS = "Persons";
	public static String PROP_PROCEEDING = "Proceeding";
	public static String PROP_AUTHORITY = "Authority";
	public static String PROP_STAT = "Stat";
	public static String PROP_MEMO = "Memo";
	public static String PROP_DT = "Dt";
	public static String PROP_USER = "User";
	public static String PROP_CALLER_SN = "CallerSn";
	public static String PROP_CONT = "Cont";
	public static String PROP_HID = "Hid";
	public static String PROP_CATEGORY_SN = "CategorySn";
	public static String PROP_KEYS = "Keys";
	public static String PROP_ID = "Id";
	public static String PROP_RESULTABLE = "Resultable";
	//提交时间
	public static String PROP_TJ_DT = "TjDt";
	//下一处理人，提交时间
	public static String PROP_DEAL_DT="DealDt";
	
	// constructors
	public BaseRcForm () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRcForm (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String persons;
	private java.lang.String cont;
	private java.lang.String keys;
	private java.lang.String hid;
	private java.lang.String memo;
	private java.util.Date dt;
	private java.util.Date tjDt;
	private java.util.Date dealDt;
	private java.lang.String stat;
	private java.lang.String proceeding;
	private java.lang.String resultable;

	// many to one
	private Authority authority;
	private com.wondersgroup.falcon.model.rc.RcCaller callerSn;
	private User user;
	private com.wondersgroup.falcon.model.rc.RcCategory categorySn;
	private com.wondersgroup.falcon.model.rc.RcReference referenceSn;

	// collections
	private java.util.Set rcResearchs;
	private java.util.Set rcUrgents;
	private java.util.Set rcFeedbacks;
	
	private java.util.Set rcType;


	public java.util.Set getRcType() {
		return rcType;
	}

	public void setRcType(java.util.Set rcType) {
		this.rcType = rcType;
	}

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="form_sn"
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
	 * Return the value associated with the column: persons
	 */
	public java.lang.String getPersons () {
		return persons;
	}

	/**
	 * Set the value related to the column: persons
	 * @param persons the persons value
	 */
	public void setPersons (java.lang.String persons) {
		this.persons = persons;
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
	 * Return the value associated with the column: keys
	 */
	public java.lang.String getKeys () {
		return keys;
	}

	/**
	 * Set the value related to the column: keys
	 * @param keys the keys value
	 */
	public void setKeys (java.lang.String keys) {
		this.keys = keys;
	}



	/**
	 * Return the value associated with the column: hid
	 */
	public java.lang.String getHid () {
		return hid;
	}

	/**
	 * Set the value related to the column: hid
	 * @param hid the hid value
	 */
	public void setHid (java.lang.String hid) {
		this.hid = hid;
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

	//提交时间记录
	/**
	 * Return the value associated with the column: tjdt
	 */
	public java.util.Date getTjDt () {
		return tjDt;
	}

	/**
	 * Set the value related to the column: tjdt
	 * @param tjDt the tjdt value
	 */
	public void setTjDt (java.util.Date tjDt) {
		this.tjDt = tjDt;
	}
	
	
	//记录递交（下一环节受理人）时间 
	/**
	 * Return the value associated with the column: dealdt
	 */
	public java.util.Date getDealDt() {
		return dealDt;
	}

	/**
	 * Set the value related to the column: dealdt
	 * @param dealDt the dealdt value
	 */
	public void setDealDt(java.util.Date dealDt) {
		this.dealDt = dealDt;
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
	 * Return the value associated with the column: proceeding
	 */
	public java.lang.String getProceeding () {
		return proceeding;
	}

	/**
	 * Set the value related to the column: proceeding
	 * @param proceeding the proceeding value
	 */
	public void setProceeding (java.lang.String proceeding) {
		this.proceeding = proceeding;
	}



	/**
	 * Return the value associated with the column: resultable
	 */
	public java.lang.String getResultable () {
		return resultable;
	}

	/**
	 * Set the value related to the column: resultable
	 * @param resultable the resultable value
	 */
	public void setResultable (java.lang.String resultable) {
		this.resultable = resultable;
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
	 * Return the value associated with the column: category_sn
	 */
	public com.wondersgroup.falcon.model.rc.RcCategory getCategorySn () {
		return categorySn;
	}

	/**
	 * Set the value related to the column: category_sn
	 * @param categorySn the category_sn value
	 */
	public void setCategorySn (com.wondersgroup.falcon.model.rc.RcCategory categorySn) {
		this.categorySn = categorySn;
	}



	/**
	 * Return the value associated with the column: reference_sn
	 */
	public com.wondersgroup.falcon.model.rc.RcReference getReferenceSn () {
		return referenceSn;
	}

	/**
	 * Set the value related to the column: reference_sn
	 * @param referenceSn the reference_sn value
	 */
	public void setReferenceSn (com.wondersgroup.falcon.model.rc.RcReference referenceSn) {
		this.referenceSn = referenceSn;
	}



	/**
	 * Return the value associated with the column: RcResearchs
	 */
	public java.util.Set getRcResearchs () {
		return rcResearchs;
	}

	/**
	 * Set the value related to the column: RcResearchs
	 * @param rcResearchs the RcResearchs value
	 */
	public void setRcResearchs (java.util.Set rcResearchs) {
		this.rcResearchs = rcResearchs;
	}



	/**
	 * Return the value associated with the column: RcUrgents
	 */
	public java.util.Set getRcUrgents () {
		return rcUrgents;
	}

	/**
	 * Set the value related to the column: RcUrgents
	 * @param rcUrgents the RcUrgents value
	 */
	public void setRcUrgents (java.util.Set rcUrgents) {
		this.rcUrgents = rcUrgents;
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
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RcForm)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RcForm rcForm = (com.wondersgroup.falcon.model.rc.RcForm) obj;
			if (null == this.getId() || null == rcForm.getId()) return false;
			else return (this.getId().equals(rcForm.getId()));
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