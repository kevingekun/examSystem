package com.wondersgroup.falcon.model.archives;

/**
 * @return
 */
public class PolicyNode extends Node {

	private PolicyAttr attribute;

	public PolicyNode() {
		super();
	}

	public PolicyNode(String name) {
		super(name);
	}

	/**
	 * �ڵ������ֵ������������ζ�Ÿýڵ���һ���ļ�������ڵ�ΪĿ¼
	 * @return
	 */
	public PolicyAttr getAttribute() {
		return attribute;
	}

	public void setAttribute(PolicyAttr attribute) {
		this.attribute = attribute;
	}

}
