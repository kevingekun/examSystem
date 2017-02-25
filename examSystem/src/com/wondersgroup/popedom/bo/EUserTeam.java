package com.wondersgroup.popedom.bo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * UserTeam entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EUserTeam implements java.io.Serializable {

	// Fields

	private Long teamId;
	private String teamName;
	private String description;
	private Long pTeamId;
	private String teamaddress;
	private String contactname;
	private String contactph;
	private String flag;
	
	public String getTeamaddress() {
		return teamaddress;
	}
	public void setTeamaddress(String teamaddress) {
		this.teamaddress = teamaddress;
	}

	private Set users = new HashSet(0);
	
	public void addUsers(EUser user) {
		this.getUsers().add(user);
		user.getTeams().add(this);
	}
	public void removeUsers(EUser user) {
		this.getUsers().remove(user);
		user.getTeams().remove(this);
	}
	public void clearUsers() {
		for (Iterator iterator = this.getUsers().iterator();
			iterator.hasNext();
		    ((EUser)(iterator.next())).getTeams().remove(this));
		this.getUsers().clear();
	}

	// Constructors

	/** default constructor */
	public EUserTeam() {
	}

	/** minimal constructor */
	public EUserTeam(Long teamId, String teamName) {
		this.teamId = teamId;
		this.teamName = teamName;
	}

	/** full constructor */
	public EUserTeam(Long teamId, String teamName, String description,
			Set users) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.description = description;
		this.users = users;
	}

	// Property accessors

	public Long getTeamId() {
		return this.teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public Set getUsers() {
		return users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getContactph() {
		return contactph;
	}
	public void setContactph(String contactph) {
		this.contactph = contactph;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Long getpTeamId() {
		return pTeamId;
	}
	public void setpTeamId(Long pTeamId) {
		this.pTeamId = pTeamId;
	}
	
}