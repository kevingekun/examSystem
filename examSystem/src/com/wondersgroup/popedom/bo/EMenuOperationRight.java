package com.wondersgroup.popedom.bo;

import java.util.HashSet;
import java.util.Set;

/**
 * MenuOperationRight entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EMenuOperationRight implements java.io.Serializable {

	// Fields

	private String rightId;
	private EUserMenus userMenus;
	private String rightname;
	private String description;
	private Set authoritiesOperationRights = new HashSet(0);

	// Constructors

	/** default constructor */
	public EMenuOperationRight() {
	}

	/** minimal constructor */
	public EMenuOperationRight(String rightId, String rightname) {
		this.rightId = rightId;
		this.rightname = rightname;
	}

	/** full constructor */
	public EMenuOperationRight(String rightId, EUserMenus userMenus,
			String rightname, String description, Set authoritiesOperationRights) {
		this.rightId = rightId;
		this.userMenus = userMenus;
		this.rightname = rightname;
		this.description = description;
		this.authoritiesOperationRights = authoritiesOperationRights;
	}

	// Property accessors

	public String getRightId() {
		return this.rightId;
	}

	public void setRightId(String rightId) {
		this.rightId = rightId;
	}

	public EUserMenus getUserMenus() {
		return this.userMenus;
	}

	public void setUserMenus(EUserMenus userMenus) {
		this.userMenus = userMenus;
	}

	public String getRightname() {
		return this.rightname;
	}

	public void setRightname(String rightname) {
		this.rightname = rightname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getAuthoritiesOperationRights() {
		return this.authoritiesOperationRights;
	}

	public void setAuthoritiesOperationRights(Set authoritiesOperationRights) {
		this.authoritiesOperationRights = authoritiesOperationRights;
	}

}