package com.wondersgroup.falcon.model.archives;

public class CaseNode extends Node {

	private CaseAttr attribute;

	public CaseNode() {
		super();
	}

	public CaseNode(String name) {
		super(name);
	}

	/**
	 * 节点的属性值，若有属性意味着该节点是一个文件，否则节点为目录
	 * @return
	 */
	public CaseAttr getAttribute() {
		return attribute; 
	}   

	public void setAttribute(CaseAttr attribute) {
		this.attribute = attribute;
	}
}
