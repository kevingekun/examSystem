package com.wondersgroup.falcon.Util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;



/**
 * �����ȡ����
 * ���Ը����Ҫ��ò�ͬ���͵Ĳ���
 * ������Ϊ�տ�����Ĭ��ֵ
 */

public class RequestUtil {

    public RequestUtil() {
    }
    
    /**
     * 
     * <p>Description:[��Request��ȡֵ ����] </p>
     * 
     * Created by [Kevin Liang] [2009-6-26]
     * Midified by [�޸���] [�޸�ʱ��]
     *
     * @param request
     * @param paramName
     * @return
     */
    public static String[] getParameterValues(HttpServletRequest request, String paramName){
        String temp[]=new String[0];
        if(request.getParameterValues(paramName)!=null){
            temp = request.getParameterValues(paramName);
        }
        return temp;
    }

    
    /**
     * ��ȡString���Ͳ���ֵ
     * @param request request����
     * @param paramName �������
     * @param defaultstring ��ʼֵ������ֵΪ��ʱȡֵ��
     * @return ����ֵ
     */
    public static String getStringParameter(HttpServletRequest request, String paramName,String defaultstr){
        String temp=defaultstr;
        if(request.getParameter(paramName)!=null){
            temp = request.getParameter(paramName);
        }
        return temp;
    }

    /**
     * ���Character���Ͳ���ֵ
     * @param request request����
     * @param paramName �������
     * @param defaultCharacter ��ʼֵ(����ֵΪ��ʱȡֵ)
     * @return
     */
    public static Character getCharacterParameter(HttpServletRequest request,String paramName,Character defaultCharacter){
       Character temp = defaultCharacter;
       if(request.getParameter(paramName)!=null){
           if(request.getParameter(paramName).length()>0){
               temp = new Character(request.getParameter(paramName).toCharArray()[0]);
           }
           else{
               temp = null;
           }
       }
       return temp;
        
    }
    
    public static BigDecimal getBigDecimalParameter(HttpServletRequest request,String paramName,BigDecimal defaultBigDecimal) {
    	BigDecimal temp = defaultBigDecimal;
    	if(request.getParameter(paramName)!= null) {
    		
    			try {
					temp = new BigDecimal(request.getParameter(paramName));
				} catch (Exception e) {
					
				}
    		
    		
    	}
    	return temp;
    }
    
    /**
     * ��ȡint���Ͳ���ֵ
     * @param request request����
     * @param paramName �������
     * @param defaultInt ��ʼֵ������ֵΪ��ʱȡֵ��
     * @return ����ֵ
     */
    public static int getIntParameter(HttpServletRequest request, String paramName,int defaultInt){
        int temp = defaultInt;
        if(request.getParameter(paramName)!=null){
            try{
                temp = Integer.parseInt(request.getParameter(paramName));
            }
            catch(Exception ex){}
        }
        return temp;
    }

    /**
     * ��ȡlong���Ͳ���ֵ
     * @param request request����
     * @param paramName �������
     * @param defaultLong ��ʼֵ������ֵΪ��ʱȡֵ��
     * @return ����ֵ
     */
    public static long getLongParameter(HttpServletRequest request, String paramName,long defaultLong){
        long temp = defaultLong;
        if(request.getParameter(paramName)!=null){
            try{
                temp = Long.parseLong(request.getParameter(paramName));
            }
            catch(Exception ex){}
        }
        return temp;
    }

    /**
     * ��ȡDate���Ͳ���ֵ
     * @param request request����
     * @param paramName �������
     * @param time ��Ҫ��ӵ�ʱ��
     * @param defaultDate ��ʼֵ������ֵΪ��ʱȡֵ��
     * @return ����ֵ
     */
    public static Date getDateParameter(HttpServletRequest request, String paramName,String time,Date defaultDate){
        Date temp = defaultDate;
        if(request.getParameter(paramName)!=null){
            if(!request.getParameter(paramName).equals("")){
                String temps = request.getParameter(paramName);
                temps = temps + " " + time;
                temps = temps.replace('-','/');
                temps = temps.replace('：',':');

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                try{
                    temp = sdf.parse(temps);
                }
                catch(ParseException pe){}
            }
        }
        return temp;
    }

}
