package com.wondersgroup.falcon.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
/**
 * 
 * <p>Title:[常用配置属性工具类] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2009-9-4]
 * Midified by [修改人] [修改时间]
 *
 */
public class PropertiesUtil {
	private Log log =LogFactory.getLog(getClass());
	
	private Properties properties =new Properties();
	
	public PropertiesUtil(){
		try {
			this.parseProperty("/config.properties");
		} catch (IOException e) {
			log.info("error:"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public PropertiesUtil(String file){
		try {
			this.parseProperty(file);
		} catch (IOException e) {
			log.info("error:"+e.getMessage());
			 
		}
	}
	
	public Properties parseProperty(String prop) throws IOException {
		InputStream is = this.getClass().getResourceAsStream(prop);
		properties.load(is);
		is.close();
		return null;
	}

	public String getProperties(String propStr) {
		return properties.getProperty(propStr);
	}
	
	public static void main(String arg[]){
		Log log =LogFactory.getLog(PropertiesUtil.class);
		PropertiesUtil pu =new PropertiesUtil("/config.properties");
		//log.info(pu.getProperties("note.autor"));
		System.out.println(pu.getProperties("note.autor"));
	}
	

}
