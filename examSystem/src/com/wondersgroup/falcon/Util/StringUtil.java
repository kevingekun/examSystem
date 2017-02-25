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
   * <p>Description:[����������������] </p>
   * 
   * Created by [Kevin Liang] [2009-7-9]
   * Midified by [�޸���] [�޸�ʱ��]
   *
   * @param month
   * @return
   */
  public static String getMonthtoChinese(int month){
      
      return (new ChoiceFormat("1#һ|2#��|3#��|4#��|5#��|6#��|7#��|8#��|9#��|10#ʮ|11#ʮһ|12#ʮ��")).format(month);
      
  }
  
  /** ��������ݿⷵ�صĽ����ͳһת��Ϊ�ַ���������
   *
   * @param str �����ݿⷵ�صĶ���
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

  /** ��������ݿⷵ�صĽ����ͳһת��Ϊ�ַ���������
   *
   * @param str    �����ݿⷵ�صĶ���
   * @param format ����ʾ�ĸ�ʽ��Ҫ��
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

  /** �ַ���ת��Ϊ����
   *
   * @param str    �ַ�������
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
   * <p>Description:[�ַ���ƴ��] </p>
   * 
   * Created by [Kevin Liang] [2009-6-26]
   * Midified by [�޸���] [�޸�ʱ��]
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


  /** ���շָ���ָ��ַ���Ϊ����
   *
   * @param str    �ַ���
   * @param space  �ָ��
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

      if (index == -1) { //û�зָ��������ԭ��
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


  //string����  add by wzh
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
             throw new Exception("������� �û��� '" + str + "'�����Ϲ淶�� ԭ���ַ� '" + (char)(b) + "'����������������У�");
         } else {

         }
     }// for
 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] s={"��Կ����㿴������23��|/","dhaskjk2222","¬��˹������·��3333"};		
		System.out.println(StringUtil.stringSpell(s,"|||"));
		String ss[]=StringUtil.stringSplit(StringUtil.stringSpell(s,"|||"), "|||");
		for(int i=0;i<ss.length;i++)
		{
			System.out.println(ss[i]);
		}
	}

}
