package com.wondersgroup.falcon.model.rc.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the rc_reference table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="rc_reference"
 */

public abstract class BaseRcReference  implements Comparable, Serializable {

	public static String REF = "RcReference";
	public static String PROP_STAT = "Stat";
	public static String PROP_ADDR = "Addr";
	public static String PROP_MEMO = "Memo";
	public static String PROP_REGIST_NUMBER = "RegistNumber";
	public static String PROP_LEADER_NAME = "LeaderName";
	public static String PROP_LEADER_DUTY = "LeaderDuty";
	public static String PROP_LEADER = "Leader";
	public static String PROP_AGENT = "Agent";
	public static String PROP_TEL = "Tel";
	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";
	public static String PROP_LEADER_PHONE = "LeaderPhone";


	// constructors
	public BaseRcReference () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseRcReference (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String name;
	private java.lang.String addr;
	private java.lang.String tel;
	private java.lang.String agent;
	private java.lang.String leader;
	private java.lang.String stat;
	private java.lang.String memo;
	private java.lang.String leaderName;
	private java.lang.String leaderDuty;
	private java.lang.String leaderPhone;
	private java.lang.String registNumber;

	// collections
	private java.util.Set rcForms;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="org.hibernate.id.UUIDHexGenerator"
     *  column="reference_sn"
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
	 * Return the value associated with the column: addr
	 */
	public java.lang.String getAddr () {
		return addr;
	}

	/**
	 * Set the value related to the column: addr
	 * @param addr the addr value
	 */
	public void setAddr (java.lang.String addr) {
		this.addr = addr;
	}



	/**
	 * Return the value associated with the column: tel
	 */
	public java.lang.String getTel () {
		return tel;
	}

	/**
	 * Set the value related to the column: tel
	 * @param tel the tel value
	 */
	public void setTel (java.lang.String tel) {
		this.tel = tel;
	}



	/**
	 * Return the value associated with the column: agent
	 */
	public java.lang.String getAgent () {
		return agent;
	}

	/**
	 * Set the value related to the column: agent
	 * @param agent the agent value
	 */
	public void setAgent (java.lang.String agent) {
		this.agent = agent;
	}



	/**
	 * Return the value associated with the column: leader
	 */
	public java.lang.String getLeader () {
		return leader;
	}

	/**
	 * Set the value related to the column: leader
	 * @param leader the leader value
	 */
	public void setLeader (java.lang.String leader) {
		this.leader = leader;
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
	 * Return the value associated with the column: leader_name
	 */
	public java.lang.String getLeaderName () {
		return leaderName;
	}

	/**
	 * Set the value related to the column: leader_name
	 * @param leaderName the leader_name value
	 */
	public void setLeaderName (java.lang.String leaderName) {
		this.leaderName = leaderName;
	}



	/**
	 * Return the value associated with the column: leader_duty
	 */
	public java.lang.String getLeaderDuty () {
		return leaderDuty;
	}

	/**
	 * Set the value related to the column: leader_duty
	 * @param leaderDuty the leader_duty value
	 */
	public void setLeaderDuty (java.lang.String leaderDuty) {
		this.leaderDuty = leaderDuty;
	}



	/**
	 * Return the value associated with the column: leader_phone
	 */
	public java.lang.String getLeaderPhone () {
		return leaderPhone;
	}

	/**
	 * Set the value related to the column: leader_phone
	 * @param leaderPhone the leader_phone value
	 */
	public void setLeaderPhone (java.lang.String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}



	/**
	 * Return the value associated with the column: regist_number
	 */
	public java.lang.String getRegistNumber () {
		return registNumber;
	}

	/**
	 * Set the value related to the column: regist_number
	 * @param registNumber the regist_number value
	 */
	public void setRegistNumber (java.lang.String registNumber) {
		this.registNumber = registNumber;
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





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wondersgroup.falcon.model.rc.RcReference)) return false;
		else {
			com.wondersgroup.falcon.model.rc.RcReference rcReference = (com.wondersgroup.falcon.model.rc.RcReference) obj;
			if (null == this.getId() || null == rcReference.getId()) return false;
			else return (this.getId().equals(rcReference.getId()));
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