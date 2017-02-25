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
	 * �ڵ������ֵ������������ζ�Ÿýڵ���һ���ļ�������ڵ�ΪĿ¼
	 * @return
	 */
	public CaseAttr getAttribute() {
		return attribute; 
	}   

	public void setAttribute(CaseAttr attribute) {
		this.attribute = attribute;
	}
}
