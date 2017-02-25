package com.wondersgroup.popedom.bo;

import java.util.Date;

import com.wondersgroup.falcon.model.auth.User;

/**
 * UserDevolveMenus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EUserDevolveMenus implements java.io.Serializable {

	// Fields
	public static Long DEVOLVE = new Long(0);
	public static Long RECEIVE = new Long(1);
	
	private Long id;
	private User user;//移交给
	private EUserMenus userMenus;//菜单
	private Long devolveUserid;//移交人
	private Date devolveTime;//移交时间
	private Date endTime;//收回时间
	private Long sign;//收回标志 

	// Constructors

	/** default constructor */
	public EUserDevolveMenus() {
	}

	/** minimal constructor */
	public EUserDevolveMenus(Long id, EUserMenus userMenus) {
		this.id = id;
		this.userMenus = userMenus;
	}

	/** full constructor */
	public EUserDevolveMenus(Long id, User user, EUserMenus userMenus,
			Long devolveUserid, Date devolveTime, Date endTime,
			Long sign) {
		this.id = id;
		this.user = user;
		this.userMenus = userMenus;
		this.devolveUserid = devolveUserid;
		this.devolveTime = devolveTime;
		this.endTime = endTime;
		this.sign = sign;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public EUserMenus getUserMenus() {
		return this.userMenus;
	}

	public void setUserMenus(EUserMenus userMenus) {
		this.userMenus = userMenus;
	}	



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getDevolveUserid() {
		return devolveUserid;
	}

	public void setDevolveUserid(Long devolveUserid) {
		this.devolveUserid = devolveUserid;
	}

	public Date getDevolveTime() {
		return this.devolveTime;
	}

	public void setDevolveTime(Date devolveTime) {
		this.devolveTime = devolveTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getSign() {
		return this.sign;
	}

	public void setSign(Long sign) {
		this.sign = sign;
	}

}