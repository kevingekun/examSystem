package com.wondersgroup.test;

import com.opensymphony.xwork2.ActionSupport;
import com.wondersgroup.falcon.question.dao.Test;

public class TestAction extends ActionSupport {
	public Test testDo;
	

	public void setTestDo(Test testDo) {
		this.testDo = testDo;
	}


	public String execute() throws Exception{
		
		this.testDo.getName();
		return null;
		 
	 }
}
