package com.wondersgroup.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * <p>Title:[�����] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2009-6-29]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */
public class Log4jTest {
	
	public static void main(String args[]) 
	{ 
		
		 PropertyConfigurator.configure("D:/workspace/falnewexam/src/log4j.properties " );
	        Logger logger  =  Logger.getLogger(Log4jTest.class );
	        logger.info("info ");
	        logger.debug( " debug " );
	        logger.error( " error " );
	        org.hibernate.id.UUIDHexGenerator d;
	      
	        
//		PropertyConfigurator.configure("D:/workspace/falnewexam/src/log4j.properties"); 
//		//�ں�̨��� 
//		Logger logger1 = Logger.getLogger("console"); 
//		logger1.debug("debug!!!"); 
//		logger1.info("info!!!"); 
//		logger1.warn("warn!!!"); 
//		logger1.error("error!!!"); 
//		logger1.fatal("fatal!!!");
////		//��NTϵͳ��־��� 
//		Logger logger2 = Logger.getLogger("NTlog"); 
//		//NTEventLogAppender nla = new NTEventLogAppender(); 
//		logger2.debug("debug!!!"); 
//		logger2.info("info!!!"); 
//		logger2.warn("warn!!!"); 
//		logger2.error("error!!!"); 
//		//ֻ���������Ż�д��2000��־ 
//		logger2.fatal("fatal!!!");
//		//����־���͵�mail 
//		Logger logger3 = Logger.getLogger("MailLog"); 
//		//SMTPAppender sa = new SMTPAppender(); 
//		logger3.warn("warn!!!"); 
//		logger3.error("error!!!"); 
//		logger3.fatal("fatal!!!"); 
	} 


	
}
