/**
 * 
 */
package com.wondersgroup.kaoshi.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author www
 *
 */
public abstract  class AbstractPageAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;

	private int ppp=1;
	
	private String pager;
	
	private PageReturn pageReturn=new PageReturn();
	
	protected PageTool pageTool = new PageTool();
	
	/**
	 * struts2要执行的方法
	 */
	public String execute() throws Exception{
		//设置第几页
		pageTool.setCur(this.ppp);
		/*
		 * 执行方法
		 */
		String rel=this.doAcion();
		
		
		/*
		 * 设置分页
		 */
		pageTool.setTotal(pageReturn.getTotal());
		PageMaker pageMaker = new PageMaker();   
        setPager(pageMaker.makeList(pageTool,""));
		return rel;
		
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	protected void setFY(PageReturn pageReturn){
		this.setPageReturn(pageReturn);
	}
	
	/**
	 *
	 * <p>Description:[用来设置一共有多少条数据，和跳转地址] </p>
	 * 
	 * Created by [www] [Aug 7, 2009]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param jg
	 * @param action
	 */
	protected void setFY(int total){
		this.pageReturn.setTotal(total);
	}
	
	/**
	 * 需要继承的方法
	 * @return
	 * @throws Exception
	 */
	public abstract String doAcion()throws Exception;
	
	public int getPpp() {
		return ppp;
	}
	public void setPpp(int ppp) {
		this.ppp = ppp;
	}
	public String getPager() {
		return pager;
	}
	public void setPager(String pager) {
		this.pager = pager;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}


	public PageTool getPageTool() {
		return pageTool;
	}

	public void setPageTool(PageTool pageTool) {
		this.pageTool = pageTool;
	}

	public PageReturn getPageReturn() {
		return pageReturn;
	}

	public void setPageReturn(PageReturn pageReturn) {
		this.pageReturn = pageReturn;
	}
}
