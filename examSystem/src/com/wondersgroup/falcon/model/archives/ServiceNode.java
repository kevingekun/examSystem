package com.wondersgroup.falcon.model.archives;

public class ServiceNode extends Node {

	private ServiceAttr attribute;

	public ServiceNode() {
		super();
	}

	public ServiceNode(String name) {
		super(name);
	}

	/**
	 * 节点的属性值，若有属性意味着该节点是一个文件，否则节点为目录
	 * @return
	 */
	public ServiceAttr getAttribute() {
		return attribute;
	}

	public void setAttribute(ServiceAttr attribute) {
		this.attribute = attribute;
	}
}
