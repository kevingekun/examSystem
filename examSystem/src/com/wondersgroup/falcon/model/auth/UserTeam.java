package com.wondersgroup.falcon.model.auth;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * UserTeam entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class UserTeam implements java.io.Serializable {

	// Fields

	private Long teamId;
	private Long pteamid;
	private String teamName;
	private String description;
	private String contactname;
	private String contactph;
	private String teamaddress;
	private String flag;
	private String count;
	private String numstart;
	private String numend;
	
	
	public UserTeam() {
		super();
	}
	public UserTeam(Long teamId) {
		super();
		this.teamId = teamId;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getNumstart() {
		return numstart;
	}
	public void setNumstart(String numstart) {
		this.numstart = numstart;
	}
	public String getNumend() {
		return numend;
	}
	public void setNumend(String numend) {
		this.numend = numend;
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
	public String getTeamaddress() {
		return teamaddress;
	}
	public void setTeamaddress(String teamaddress) {
		this.teamaddress = teamaddress;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPteamid() {
		return pteamid;
	}
	public void setPteamid(Long pteamid) {
		this.pteamid = pteamid;
	}
	

}