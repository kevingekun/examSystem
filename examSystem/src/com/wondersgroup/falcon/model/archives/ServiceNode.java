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
	 * �ڵ������ֵ������������ζ�Ÿýڵ���һ���ļ�������ڵ�ΪĿ¼
	 * @return
	 */
	public ServiceAttr getAttribute() {
		return attribute;
	}

	public void setAttribute(ServiceAttr attribute) {
		this.attribute = attribute;
	}
}
