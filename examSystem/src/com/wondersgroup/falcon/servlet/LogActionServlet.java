/**
 * 
 */
package com.wondersgroup.falcon.servlet;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.struts.action.ActionServlet;

/**
 * <p>Title:[LOG4J服务加载SERVLET] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2009-6-30]
 * Midified by [修改人] [修改时间]
 *
 */
public class LogActionServlet extends ActionServlet {
	private Log log = LogFactory.getLog(LogActionServlet.class);
	private final String WEB_APP_ROOT_DEFAULT = "webapp.root";


	public LogActionServlet() {} 

	public void init() throws ServletException { 
		log.info("Initializing, My MyActionServlet init this System's Const Variable"); 
		String prefix = this.getServletConfig().getServletContext().getRealPath("/");//读取项目的路径 
		String file = this.getServletConfig().getInitParameter("log4j"); 
		//读取log4j相对路径 
		String filePath = prefix + file; 
		log.info("读取log4j相对路径 ："+filePath); 
		DOMConfigurator.configure(filePath);//加载.xml文件       
		log.info("Initializing, end My Init"); 
		super.init();//应用了struts,此方法不能省，ActionServlet覆盖了的此方法中有很多重要操作 
		
		   
	} 
	   
}
