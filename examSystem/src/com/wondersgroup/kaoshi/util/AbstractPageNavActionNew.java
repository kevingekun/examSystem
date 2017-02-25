package com.wondersgroup.kaoshi.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;


public abstract class AbstractPageNavActionNew extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	protected PageTool pageTool = new PageTool();
	protected PageReturn pageReturn=new PageReturn();
	private int  currpage=1;
	private int  pagenum=10;
	
	public int getPagenum() {
		return pagenum; 
	}
	
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getCurrpage() {
		return currpage;
	}
	
	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
