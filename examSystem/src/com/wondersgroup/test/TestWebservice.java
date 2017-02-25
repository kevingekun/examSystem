package com.wondersgroup.test;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import com.wondersgroup.local.bo.User;
@SuppressWarnings("unchecked")
public class TestWebservice {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ServiceException, RemoteException  {
		

			String endpoint = "http://localhost:8080/sdggjy/services/PublicService";
			String aac001="2116735";
			String aac147="370213198511012816";
		Service service = new Service();
		User user =new User();
		user.setEmail("www.baidu.com");
//			Call call = null;
//			call = (Call) service.createCall();
//			call.setOperationName("doService");
//			call.setTargetEndpointAddress(new java.net.URL(endpoint));
//			call.addParameter("aac001",
//					org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
//			call.addParameter("aac147",
//					org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
//			call.setReturnType(new QName("JSONArray"), JSONArray.class);
//			JSONArray jsonArray = (JSONArray) call.invoke(new Object[] {aac001,aac147});
//			System.out.println("return value is " + jsonArray.toString());
		Call call = (Call) service.createCall();      
		call.setTargetEndpointAddress(endpoint); 
		
		//这里的QName的ns和wsdd文件中的对应       
		QName qn = new QName("urn:User", "User");     
		//这里是将对象序列化和反序列化的配置      
		call.registerTypeMapping(User.class, qn, new BeanSerializerFactory(User.class, qn), new BeanDeserializerFactory(User.class, qn));    
		call.setOperationName("setUser");
		//设置参数类型 
		call.addParameter("user", qn, ParameterMode.IN);    
		System.out.println("---------");
		call.invoke(new Object[] { user });
		System.out.println("+++++++");
			 
			 
		

	}
	
	private static String getXml(String xmlfile) throws IOException {
		
		InputStream is = new DefaultResourceLoader().getResource(xmlfile).getInputStream();

		byte[] buffer = new byte[is.available()];
		is.read(buffer);

		String xml = new String(buffer);

		return xml;

	}

}
