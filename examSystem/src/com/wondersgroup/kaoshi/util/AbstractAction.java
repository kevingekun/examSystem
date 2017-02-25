package com.wondersgroup.kaoshi.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractAction  extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	

}
