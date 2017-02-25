package com.wondersgroup.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * <p>Title:[类标题] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2009-6-29]
 * Midified by [修改人] [修改时间]
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
//		//在后台输出 
//		Logger logger1 = Logger.getLogger("console"); 
//		logger1.debug("debug!!!"); 
//		logger1.info("info!!!"); 
//		logger1.warn("warn!!!"); 
//		logger1.error("error!!!"); 
//		logger1.fatal("fatal!!!");
////		//在NT系统日志输出 
//		Logger logger2 = Logger.getLogger("NTlog"); 
//		//NTEventLogAppender nla = new NTEventLogAppender(); 
//		logger2.debug("debug!!!"); 
//		logger2.info("info!!!"); 
//		logger2.warn("warn!!!"); 
//		logger2.error("error!!!"); 
//		//只有这个错误才会写入2000日志 
//		logger2.fatal("fatal!!!");
//		//把日志发送到mail 
//		Logger logger3 = Logger.getLogger("MailLog"); 
//		//SMTPAppender sa = new SMTPAppender(); 
//		logger3.warn("warn!!!"); 
//		logger3.error("error!!!"); 
//		logger3.fatal("fatal!!!"); 
	} 


	
}
