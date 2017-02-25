package com.wondersgroup.falcon.model.citizeninfo;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Chen Lei
 */
public class HisNode {

	private Long id;
	
	private Long paixu;

	private HisNode parent;


	private SortedSet children = new TreeSet();

	private String name;

	private int parent_id;
	
	private int visible;

	private int ordering;

	



	public HisNode() {
		this.visible = 1;
		ordering = 0;
	}

	public HisNode(String name) {
		this.name = name;
		this.visible = 1;
		ordering = 0;
	}

	/**
	 * 孩子节点的集合
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#getChildren()
	 */


	/**
	 * 唯一标识
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#getId()
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 节点是否可见
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#isVisible()
	 */
	
	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}
	/**
	 * 节点名称
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#getName()
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 父节点
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#getParent()
	 */
	public HisNode getParent() {
		return parent;
	}

	public void setParent(HisNode parent) {
		this.parent = parent;
	}

	/**
	 * 节点的顺序
	 * 
	 */
	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	// ***********************************************
	/**
	 * 为当前节点增加孩子
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#addChild(com.wondersgroup.falcon.model.archives.Node)
	 */
	public void addChild(HisNode child) {
		child.setParent(this);
		getChildren().add(child);
	}

	/**
	 * 移动
	 */
	public void move(int displacement) {
		ordering += displacement;
	}

	public SortedSet getChildren() {
		return children;
	}

	public void setChildren(SortedSet children) {
		this.children = children;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public Long getPaixu() {
		return paixu;
	}

	public void setPaixu(Long paixu) {
		this.paixu = paixu;
	}



}

