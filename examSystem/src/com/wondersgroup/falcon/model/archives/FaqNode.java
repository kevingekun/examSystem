package com.wondersgroup.falcon.model.archives;

public class FaqNode extends Node {

	private FaqAttr attribute;

	public FaqNode() {
		super();
	}

	public FaqNode(String name) {
		super(name);
	}

	/**
	 * �ڵ������ֵ������������ζ�Ÿýڵ���һ���ļ�������ڵ�ΪĿ¼
	 * @return
	 */
	public FaqAttr getAttribute() {
		return attribute;
	}

	public void setAttribute(FaqAttr attribute) {
		this.attribute = attribute;
	}
}
