package com.wondersgroup.kaoshi.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wondersgroup.falcon.Util.NavigateForm;

public abstract class AbstractPageNavAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	protected PageTool pageTool = new PageTool();
	protected PageReturn pageReturn=new PageReturn();
	private int  currpage=1;
	private int  pagenum=20;
	public int getPagenum() {
		return pagenum; 
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}

	/**
	 * struts2要执行的方法
	 */
	public String execute() throws Exception{
		//设置第几页
		pageTool.setCur(this.currpage);
		pageTool.setSize(this.pagenum);
		/*
		 * 执行方法
		 */
		String rel=this.doAcion();
        
        /*
         * 分页使用的
         */
        NavigateForm navigateform = new NavigateForm();
	  	navigateform.setCurrpage(currpage);
	  	navigateform.setPagesize(pagenum);
	  	navigateform.setTotal(pageReturn.getTotal());
	  	navigateform.setPagenum(pagenum);
	  	request.setAttribute("navigateform", navigateform);
		return rel;
		
	}
	
	/**
	 * 需要继承的方法
	 * @return
	 * @throws Exception
	 */
	public abstract String doAcion()throws Exception;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
