package com.wondersgroup.falcon.Util;


import java.text.ChoiceFormat;
import java.util.*;
/**
 * <p>Title:  </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class StringUtil {
  public StringUtil() {
  }
  
  
  /**
   * 
   * <p>Description:[方法功能中文描述] </p>
   * 
   * Created by [Kevin Liang] [2009-7-9]
   * Midified by [修改人] [修改时间]
   *
   * @param month
   * @return
   */
  public static String getMonthtoChinese(int month){
      
      return (new ChoiceFormat("1#一|2#二|3#三|4#四|5#五|6#六|7#七|8#八|9#九|10#十|11#十一|12#十二")).format(month);
      
  }
  
  /** 处理从数据库返回的结果，统一转化为字符型来处理
   *
   * @param str 从数据库返回的对象
   * @return
   */
  public static String trim(Object str) {
    String s = "";
    if (str != null) {
      try {
        s = DateUtil.DateToStr((java.util.Date) str);
      }
      catch (Exception e) {
        s = str.toString();
      }
      s = s.trim();
    }
    else
      return "";

    if (s.compareTo("null") == 0) {
      return "";
    }
    return s;
  }

  /** 处理从数据库返回的结果，统一转化为字符型来处理
   *
   * @param str    从数据库返回的对象
   * @param format 对显示的格式的要求
   * @return
   */
  public static String trim(Object str, String dateFormat) {
    String s = "";

    if (str != null) {
      try {
        s = DateUtil.DateToStr( (java.util.Date) str, dateFormat);
      }
      catch (Exception e) {
        s = str.toString();
      }
      s = s.trim();
    }
    if (s.compareTo("null") == 0) {
      return "";
    }
    return s;
  }

  /** 字符串转换为数字
   *
   * @param str    字符串对象
   * @return
   */
  public static int strToInt(String str) {
    int n = -999;
    if (str != null) {
      try {
        n = Integer.parseInt(str);
      }
      catch (Exception e) {

      }
    }
    return n;
  }
  /**
   * 
   * <p>Description:[字符串拼接] </p>
   * 
   * Created by [Kevin Liang] [2009-6-26]
   * Midified by [修改人] [修改时间]
   *
   * @param str
   * @return
   */
  public static String stringSpell(String str[],String space) {
      StringBuilder sb = new StringBuilder();
      
      for (int i = 0; i < str.length; i++) {
    	  if(i>0){
    		  sb.append(space);
    	  }
          sb.append(str[i]);
      }
      return  sb.toString();
      
  }


  /** 按照分割符分割字符串为数组
   *
   * @param str    字符串
   * @param space  分割符
   * @return
   */
  public static String[] stringSplit(String str, String space) {
    if (str == null) {
      return null;
    }
    if(str.equals("")){
      return null;
    }

    Vector v = new Vector();

    try{
      int start = 0;
      int index = str.indexOf(space);

      if (index == -1) { //没有分割符，返回原串
        v.add(str);
      }
      else {
        if (index == 0) {
          v.add("");
        }
        else {
          v.add(str.substring(0, index));
        }

        start = index + space.length();
        while (start < str.length()) {
          index = str.indexOf(space, start);
          if (index == -1) {
            v.add(str.substring(start, str.length()));
            break;
          }
          else {
            v.add(str.substring(start, index));
          }
          start = index + space.length();
        }

        if (start == str.length()) {
          v.add("");
        }
      }
    }
    catch(Exception e){
       return null;
    }

    if (v != null) {
      Object[] objs = v.toArray();
      if (objs != null) {
        int count = objs.length;
        String[] os = new String[count];
        for (int i = 0; i < count; i++) {
          os[i] = trim(objs[i]);
        }
        return os;
      }
    }
    return null;
  }
  

  public static String trim(Object str,String oldstr,String newstr){
    String ret=trim(str);
    try{
      String[] tmp=stringSplit(ret,oldstr);
      StringBuffer tmpstr=new StringBuffer("");
      if(tmp!=null){
        for(int i=0;i<tmp.length;i++){
          if(i==tmp.length-1)
            tmpstr=tmpstr.append(tmp[i]);
          else
            tmpstr=tmpstr.append(tmp[i]).append(newstr);
        }
        ret=tmpstr.toString();
      }
    }catch(Exception e){

    }
    return ret;
  }
  
  public static String getEmptyStringIfNull(String str) {
      if (str == null) return "";
      return str;
  }


  //string处理  add by wzh
 public static String parameterTrim(String str){
    if(str==null) return null;
    if(str.compareTo("-1")==0) return "";
    if(str.compareTo("null")==0) return "";
    return str.trim();
  }
 
 public static void checkGoodName(String str) throws Exception {
     byte[] s = str.getBytes();
     int length = s.length;
     byte b = 0;

     for (int i = 0; i < length; i++) {
         b = s[i];
         //if ((b >= 'a') && (b <= 'z')) {
             // lower char
        // } else if ((b >= 'A') && (b <= 'Z')) {
             // upper char
         //} else if ((b >= '0') && (b <= '9') && (i != 0)) {
             // numeric char
        // } else
                     if (( (b=='&') || (b=='$') || (b=='%') || (b==' ') || (b=='"')) && (i == 0)) {
                             // not good char, throw an BadInputException
             throw new Exception("您输入的 用户名 '" + str + "'不符合规范！ 原因：字符 '" + (char)(b) + "'不允许出现在名字中！");
         } else {

         }
     }// for
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] s={"打对抗打算看见考虑23；|/","dhaskjk2222","卢卡斯到福建路口3333"};		
		System.out.println(StringUtil.stringSpell(s,"|||"));
		String ss[]=StringUtil.stringSplit(StringUtil.stringSpell(s,"|||"), "|||");
		for(int i=0;i<ss.length;i++)
		{
			System.out.println(ss[i]);
		}
	}

}
