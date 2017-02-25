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
 * <p>Title:[LOG4J�������SERVLET] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2009-6-30]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */
public class LogActionServlet extends ActionServlet {
	private Log log = LogFactory.getLog(LogActionServlet.class);
	private final String WEB_APP_ROOT_DEFAULT = "webapp.root";


	public LogActionServlet() {} 

	public void init() throws ServletException { 
		log.info("Initializing, My MyActionServlet init this System's Const Variable"); 
		String prefix = this.getServletConfig().getServletContext().getRealPath("/");//��ȡ��Ŀ��·�� 
		String file = this.getServletConfig().getInitParameter("log4j"); 
		//��ȡlog4j���·�� 
		String filePath = prefix + file; 
		log.info("��ȡlog4j���·�� ��"+filePath); 
		DOMConfigurator.configure(filePath);//����.xml�ļ�       
		log.info("Initializing, end My Init"); 
		super.init();//Ӧ����struts,�˷�������ʡ��ActionServlet�����˵Ĵ˷������кܶ���Ҫ���� 
		
		   
	} 
	   
}
