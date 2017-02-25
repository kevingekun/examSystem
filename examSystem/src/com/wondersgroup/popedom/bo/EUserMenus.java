package com.wondersgroup.popedom.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserMenus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EUserMenus implements java.io.Serializable {

	// Fields

	private String id;
	private String parentid;
	private String menuname;
	private String description;
	private String menueurl;
	private String icon;
	private Long ordering;
	private Long systemtype;
	private Long status;
	private Long devolve;
	private Set authoritys = new HashSet(0);
	private Set childMenus = new HashSet(0);
	//显示第三层菜单
	private List<EUserMenus> childMenusView=new ArrayList<EUserMenus>();

	/** default constructor */
	public EUserMenus() {
	}

	/** minimal constructor */
	public EUserMenus(String id, String menuname, Long systemtype, Long status) {
		this.id = id;
		this.menuname = menuname;
		this.systemtype = systemtype;
		this.status = status;
	}

	/** full constructor */
	public EUserMenus(String id, String parentid, String menuname,
			String description, String menueurl, String icon, Long ordering,
			Long systemtype, Long status, Long devolve
			) {
		this.id = id;
		this.parentid = parentid;
		this.menuname = menuname;
		this.description = description;
		this.menueurl = menueurl;
		this.icon = icon;
		this.ordering = ordering;
		this.systemtype = systemtype;
		this.status = status;
		this.devolve = devolve;
	}


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuname() {
		return this.menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenueurl() {
		return this.menueurl;
	}

	public void setMenueurl(String menueurl) {
		this.menueurl = menueurl;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getOrdering() {
		return this.ordering;
	}

	public void setOrdering(Long ordering) {
		this.ordering = ordering;
	}

	public Long getSystemtype() {
		return this.systemtype;
	}

	public void setSystemtype(Long systemtype) {
		this.systemtype = systemtype;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getDevolve() {
		return this.devolve;
	}

	public void setDevolve(Long devolve) {
		this.devolve = devolve;
	}

	public Set getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(Set authoritys) {
		this.authoritys = authoritys;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Set getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(Set childMenus) {
		this.childMenus = childMenus;
	}

	public List<EUserMenus> getChildMenusView() {
		return childMenusView;
	}

	public void setChildMenusView(List<EUserMenus> childMenusView) {
		this.childMenusView = childMenusView;
	}


}