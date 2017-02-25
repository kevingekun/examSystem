package com.wondersgroup.falcon.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;



public class RequestUtils {

	public RequestUtils() {

	}

	public static boolean exists(ServletRequest request,String name){
		return StringUtils.isNotEmpty(request.getParameter(name)); 
	}
	public static String[] getStringValues(HttpServletRequest request, String name){
		String temp[]=new String[0];
		if(request.getParameterValues(name)!=null){
			temp = request.getParameterValues(name);
		}
		return temp;
	}
	public static String getString(ServletRequest request,String name,String defaultValue){
		return ParameterUtils.getStringValue(request.getParameter(name),defaultValue);
	}
	public static short getShort(ServletRequest request,String name,short defaultValue){ 
		return ParameterUtils.getShortValue(request.getParameter(name),defaultValue);
	}

	public static short[] getShortValues(ServletRequest request,String name,short defaultValue){
		return ParameterUtils.getShortValues(request.getParameterValues(name),defaultValue);
	}    

	public static int getInt(ServletRequest request,String name,int defaultValue){ 
		return ParameterUtils.getIntValue(request.getParameter(name),defaultValue);
	}

	public static int[] getIntValues(ServletRequest request,String name,int defaultValue){
		return ParameterUtils.getIntValues(request.getParameterValues(name),defaultValue);

	}    

	public static long getLong(ServletRequest request,String name,long defaultValue){ 
		return ParameterUtils.getLongValue(request.getParameter(name),defaultValue);
	}

	public static long[] getLongValues(ServletRequest request,String name,long defaultValue){
		return ParameterUtils.getLongValues(request.getParameterValues(name),defaultValue);
	}

	public static float getFloat(ServletRequest request,String name,float defaultValue){ 
		return ParameterUtils.getFloatValue(request.getParameter(name),defaultValue);
	} 
	public static float[] getFloatValues(ServletRequest request,String name,float defaultValue){
		return ParameterUtils.getFloatValues(request.getParameterValues(name),defaultValue);
	}        

	public static double getDouble(ServletRequest request,String name,double defaultValue){ 
		return ParameterUtils.getDoubleValue(request.getParameter(name),defaultValue);
	} 

	public static double[] getDoubleValues(ServletRequest request,String name,double defaultValue){
		return ParameterUtils.getDoubleValues(request.getParameterValues(name),defaultValue);
	}     

	public static boolean getBoolean(ServletRequest request,String name,boolean defaultValue){ 
		return ParameterUtils.getBooleanValue(request.getParameter(name),defaultValue);
	}     

	public static boolean getBoolean(ServletRequest request,String name,String trueFlag,boolean defaultValue){ 
		return ParameterUtils.getBooleanValue(request.getParameter(name),trueFlag,defaultValue);
	}        

	public static boolean getBoolean(ServletRequest request,String name,String[] trueFlags,boolean defaultValue){ 
		return ParameterUtils.getBooleanValue(request.getParameter(name),trueFlags,defaultValue);
	}

	public static java.util.Date getDate(ServletRequest request,String name,java.util.Date defaultValue,String pattern){
		return ParameterUtils.getDate(request.getParameter(name), defaultValue, pattern);
	}
	public static java.util.Date getDate(ServletRequest request,String name,java.util.Date defaultValue){
		return ParameterUtils.getDate(request.getParameter(name), defaultValue, "yyyy-MM-dd HH:mm:ss");
	}

	public static java.sql.Date getSqlDate(ServletRequest request,String name,java.util.Date defaultValue,String pattern){
		return ParameterUtils.getSqlDate(request.getParameter(name), defaultValue, pattern);
	}
	public static java.sql.Date getSqlDate(ServletRequest request,String name,java.util.Date defaultValue){
		return ParameterUtils.getSqlDate(request.getParameter(name), defaultValue, "yyyy-MM-dd");
	}

	/**
	 * 
	 * <p>Description:[取得时间参数并增加小时、分钟] </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param request
	 * @param paramName
	 * @param time
	 * @param defaultDate
	 * @return
	 */
	public static Date getDate(HttpServletRequest request, String paramName,String time,Date defaultDate,String format){
		Date temp = defaultDate;
		String t = RequestUtils.getString(request, paramName, "");
		if(!t.equals("")){
			t = t+" "+time;
		}else {
			return defaultDate;
		}

		//"yyyy-MM-dd HH:mm:ss"
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			temp = sdf.parse(t);
		}
		catch(ParseException pe){
			pe.printStackTrace();
		}
		return temp;
	}

	public static Long[] toArray(long[] ary){
		Long []arry = new Long[ary.length];
		for(int i=0;i<ary.length;i++)
		{
			arry[i] = new Long(ary[i]);
		}
		return arry;
	}
	public static void populate(ServletRequest request,String prefix,String suffix,Object bean){
		Enumeration names = request.getParameterNames();
		HashMap properties = new HashMap();

		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String stripped = name;
			if (prefix != null) {
				if (!stripped.startsWith(prefix)) {
					continue;
				}
				stripped = stripped.substring(prefix.length());
			}
			if (suffix != null) {
				if (!stripped.endsWith(suffix)) {
					continue;
				}
				stripped = stripped.substring(0, stripped.length() - suffix.length());
			}
			Object parameterValue = null;
			parameterValue = request.getParameterValues(name);

			properties.put(stripped, parameterValue);
		}

		try {
			BeanUtils.populate(bean, properties);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}  	
	}
	public static void populate(ServletRequest request, String prefix,Object bean){
		populate(request,prefix,null, bean);
	}

	public static void populate(ServletRequest request,Object bean){
		populate(request,null,null, bean);
	}
}
