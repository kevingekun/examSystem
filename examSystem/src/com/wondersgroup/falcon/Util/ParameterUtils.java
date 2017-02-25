package com.wondersgroup.falcon.Util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParameterUtils {
    private static Log log = LogFactory.getLog(ParameterUtils.class);

    private Object params = null;

    private Method methodGetValue = null;


    public ParameterUtils(Object params, String getMethodName) {
        if (params == null) {
            throw new IllegalArgumentException(
                    "The first argument 'params' cann't be null!");
        }
        this.params=params;
        try {
            methodGetValue = params.getClass().getMethod(getMethodName,
                    new Class[] { String.class });
            if (!methodGetValue.getReturnType().equals(String.class)
                    || methodGetValue.getModifiers() != Modifier.PUBLIC) {
                throw new IllegalArgumentException(
                        "The get-value-method's signature must be public String method(Sring)!");
            }
        } catch (Exception e) {
            log.error(e);
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    public static String getStringValue(String oriValue,
            String emptyDefaultValue) {
        if (StringUtils.isEmpty(oriValue))
            return emptyDefaultValue;
        return oriValue;
    }

    public static int getIntValue(String oriValue, int defaultValue) {
        String str = getStringValue(oriValue, null);
        if (str == null) {
            return defaultValue;
        }

        int value = defaultValue;
        try {
            value = Integer.parseInt(str);
        } catch (Exception e) {// invalid int string
        }

        return value;
    }
    
    public static int[] getIntValues(String[] values,int defaultValue){
    	if(!ArrayUtils.isEmpty(values)){
    		int[] result = new int[values.length];
    		for(int i=0;i<values.length;i++){
    			result[i]=ParameterUtils.getIntValue(values[i], defaultValue);
    		}
        	return result;
    	}else{
    		return new int[0];
    	}    	
    }

    public static long getLongValue(String oriValue, long defaultValue) {
        String str = getStringValue(oriValue, null);
        if (str == null) {
            return defaultValue;
        }

        long value = defaultValue;
        try {
            value = Long.parseLong(str);
        } catch (Exception e) {// invalid int string
        }

        return value;
    }
    
    public static long[] getLongValues(String[] values,long defaultValue){
    	if(!ArrayUtils.isEmpty(values)){
    		long[] result = new long[values.length];
    		for(int i=0;i<values.length;i++){
    			result[i]=getLongValue(values[i], defaultValue);
    		}
        	return result;
    	}else{
    		return new long[0];
    	}    	
    }
    
    public static short getShortValue(String oriValue, short defaultValue) {
        String str = getStringValue(oriValue, null);
        if (str == null) {
            return defaultValue;
        }

        short value = defaultValue;
        try {
            value = Short.parseShort(str);
        } catch (Exception e) {// invalid int string
        }

        return value;
    }   
    
    public static short[] getShortValues(String[] values,short defaultValue){
    	if(!ArrayUtils.isEmpty(values)){
    		short[] result = new short[values.length];
    		for(int i=0;i<values.length;i++){
    			result[i]=getShortValue(values[i], defaultValue);
    		}
        	return result;
    	}else{
    		return new short[0];
    	}    	
    }
    

    public static boolean getBooleanValue(String oriValue, boolean defaultValue) {
        return StringUtils.isNotEmpty(oriValue)?Boolean.valueOf(oriValue).booleanValue():defaultValue;
    }
    
    public static java.util.Date getDate(String oriValue,java.util.Date defaultValue,String pattern){
    	if(StringUtils.isEmpty(oriValue)){
    		return defaultValue;
    	}else{
    		if(StringUtils.isEmpty(pattern)){
    			pattern="yyyy-MM-dd";
    		}
    		try {
				return new SimpleDateFormat(pattern).parse(oriValue);
			} catch (ParseException e) {
				e.printStackTrace();
				return defaultValue;
			}
    	}
    }
    
    public static java.sql.Date getSqlDate(String oriValue,java.util.Date defaultValue,String pattern){
    	java.util.Date temp = getDate(oriValue, (java.util.Date)defaultValue, pattern);
    	return new java.sql.Date(temp.getTime());
    }    
    
    public static boolean[] getBooleanValues(String[] values, String trueFlag,
            boolean defaultValue) {
    	if(!ArrayUtils.isEmpty(values)){
    		boolean[] result = new boolean[values.length];
    		for(int i=0;i<values.length;i++){
    			result[i]=getBooleanValue(values[i],trueFlag, defaultValue);
    		}
        	return result;
    	}else{
    		return new boolean[0];
    	}    	
    }

    public static boolean getBooleanValue(String oriValue, String trueFlag,
            boolean defaultValue) {
        if (StringUtils.isNotEmpty(trueFlag)
                && StringUtils.isNotEmpty(trueFlag.trim())) {
            return getBooleanValue(oriValue, trueFlag.trim().split("|"),
                    defaultValue);
        }
        return getBooleanValue(oriValue, (String[]) null, defaultValue);
    }
    
    public static boolean[] getBooleanValues(String[] values, boolean defaultValue) {
    	if(!ArrayUtils.isEmpty(values)){
    		boolean[] result = new boolean[values.length];
    		for(int i=0;i<values.length;i++){
    			result[i]=getBooleanValue(values[i], defaultValue);
    		}
        	return result;
    	}else{
    		return new boolean[0];
    	}    	
    }

    public static boolean getBooleanValue(String oriValue, String[] trueFlags,
            boolean defaultValue) {
        String str = getStringValue(oriValue, null);
        if (str == null) {
            return defaultValue;
        }

        if (trueFlags == null) {
            trueFlags = new String[] { "true" };
        }

        for (int i = 0; i < trueFlags.length; i++) {
            if (trueFlags[i].equals(oriValue))
                return true;
        }

        return false;
    }
    
    public static boolean[] getBooleanValues(String[] values, String[] trueFlags,
            boolean defaultValue) {
    	if(!ArrayUtils.isEmpty(values)){
    		boolean[] result = new boolean[values.length];
    		for(int i=0;i<values.length;i++){
    			result[i]=getBooleanValue(values[i],trueFlags, defaultValue);
    		}
        	return result;
    	}else{
    		return new boolean[0];
    	}    	
    }


    public static float getFloatValue(String oriValue, float defaultValue) {
        String str = getStringValue(oriValue, null);
        if (str == null) {
            return defaultValue;
        }

        float value = defaultValue;
        try {
            value = Float.parseFloat(str);
        } catch (Exception e) {// invalid int string
        }

        return value;
    }
    
    public static float[] getFloatValues(String[] values,float defaultValue){
    	if(!ArrayUtils.isEmpty(values)){
    		float[] result = new float[values.length];
    		for(int i=0;i<values.length;i++){
    			result[i]=getFloatValue(values[i], defaultValue);
    		}
        	return result;
    	}else{
    		return new float[0];
    	}    	
    }

    public static double getDoubleValue(String oriValue, double defaultValue) {
        String str = getStringValue(oriValue, null);
        if (str == null) {
            return defaultValue;
        }

        double value = defaultValue;
        try {
            value = Double.parseDouble(str);
        } catch (Exception e) {// invalid int string
        }
        return value;
    }

    public static double[] getDoubleValues(String[] values,double defaultValue){
    	if(!ArrayUtils.isEmpty(values)){
    		double[] result = new double[values.length];
    		for(int i=0;i<values.length;i++){
    			result[i]=getDoubleValue(values[i], defaultValue);
    		}
        	return result;
    	}else{
    		return new double[0];
    	}    	
    }
    public static Map extractByPrefix(Map map, String prefixKey) {
        if (prefixKey == null || prefixKey.length() == 0 || map == null) {
            return map;
        }
        if (!prefixKey.endsWith(".")) {
            prefixKey += ".";
        }

        Map result = null;
        try {
            result = (Map) map.getClass().newInstance();
        } catch (Exception e) {
            result = new HashMap();
        }

        for (Iterator itr = map.entrySet().iterator(); itr.hasNext();) {
            Entry entry = (Entry) itr.next();
            if (entry.getKey() instanceof String
                    && ((String) entry.getKey()).startsWith(prefixKey)) {
                result.put(((String) entry.getKey()).substring(prefixKey
                        .length()), entry.getValue());
            }
        }
        return result;
    }

    private String getValue(String key) {
        try {
            return (String) methodGetValue.invoke(params, new Object[] { key });
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    public String getString(String key, String defaultValue) {
        return getStringValue(getValue(key), defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return getIntValue(getValue(key), defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return getLongValue(getValue(key), defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return getFloatValue(getValue(key), defaultValue);
    }

    public double getDouble(String key, double defaultValue) {
        return getDoubleValue(getValue(key), defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getBooleanValue(getValue(key), defaultValue);
    }

    public boolean getBoolean(String key, String trueFlag, boolean defaultValue) {
        return getBooleanValue(getValue(key), trueFlag, defaultValue);
    }

    public boolean getBoolean(String key, String[] trueFlags,
            boolean defaultValue) {
        return getBooleanValue(getValue(key), trueFlags, defaultValue);
    }
    

    public static void main(String[] args) {
       
    }
}
