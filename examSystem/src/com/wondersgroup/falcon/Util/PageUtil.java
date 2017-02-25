package com.wondersgroup.falcon.Util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

/**
 * 页面输出工具
 * 可以根据需要将不同类型的变量转换成String类型，便于页面显示
 */
public class PageUtil {

    public PageUtil(){

    }

    /**
     * 输出String类型的变量
     * @param outputStr 需要输出的String类型的变量
     * @return 页面显示内容
     */
    public static String outputString(String outputStr){
        String pageStr = "";
        if(outputStr!=null){
            pageStr = outputStr;
        }
        return pageStr;
    }

    /**
     * 输出Integer类型的变量
     * @param outputInteger 需要输出的Integer类型的变量
     * @return 页面显示内容
     */
    public static String outputInteger(Integer outputInteger){
        String pageStr = "";
        if(outputInteger!=null){
            pageStr = outputInteger.toString();
        }
        return pageStr;
    }

    /**
     * 根据给定的格式输出Date类型的变量
     * @param outputDate 需要输出的Date类型的变量
     * @param pattern 输出Date的格式
     * @return 页面显示内容
     */
    public static String outputDate(Date outputDate,String pattern){
        String pageStr = "";
        if(outputDate!=null){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                pageStr = sdf.format(outputDate);
            }catch(Exception e){}
        }
        return pageStr;
    }

    /**
     * 输出Character类型的变量
     * @param outputCharacter 需要输出的Character类型的变量
     * @return 页面显示内容
     */
    public static String outputCharacter(Character outputCharacter){
        String pageStr="";
        if(outputCharacter!=null){
            pageStr = outputCharacter.toString();
        }
        return pageStr;
    }

    /**
     * 根据给定的Character-String对应关系输出Character类型的变量
     * @param outputCharacter 需要输出的Character类型的变量
     * @param characterOld 给定Character-String对应关系中的Character
     * @param strNew 给定Character-String对应关系中的String
     * @return 页面显示内容
     */
    public static String outputCharacter(Character outputCharacter,Character[] characterOld,String[] strNew){
        String pageStr="";
        if(outputCharacter!=null && characterOld!=null && strNew!=null){
            for(int i=0;i<characterOld.length;i++){
                if(outputCharacter.equals(characterOld[i])){
                    if(i<strNew.length){
                        pageStr = (strNew[i]==null?"":strNew[i]);
                    }
                }
            }
        }
        return pageStr;
    }
    
    /**
     * 根据给定的StringOld-StringNew对应关系输出String类型的变量
     * @param outputString 需要输出的String类型的变量
     * @param strOld 给定StringOld-StringNew对应关系中的StringOld
     * @param strNew 给定StringOld-StringNew对应关系中的StringNew
     * @return 页面显示内容
     */
    public static String outputString(String outputString,String[] strOld,String[] strNew){
        String pageStr="";
        if(outputString!=null && strOld!=null && strNew!=null){
            for(int i=0;i<strOld.length;i++){
                if(outputString.equals(strOld[i])){
                    if(i<strNew.length){
                        pageStr = (strNew[i]==null?"":strNew[i]);
                    }
                }
            }
        }
        return pageStr;
    }
    
    
    /**
     * 输出Byte类型的变量
     * @param outputByte 需要输出的Byte类型的变量
     * @return 页面显示字符串
     */
    public static String outputByte(Byte outputByte){
        String pageStr="";
        if(outputByte!=null){
            pageStr = outputByte.toString();
        }
        return pageStr;
    }

    /**
     * 输出BigDecimal类型的变量
     * @param outputBigDecimal 需要输出的BigDecimal类型的变量
     * @return 页面显示字符串
     */
    public static String outputBigDecimal(BigDecimal outputBigDecimal){
        String pageStr="";
        if(outputBigDecimal!=null){
            pageStr = outputBigDecimal.toString();
        }
        return pageStr;
    }

    /**
     * 根据给定的Byte-String对应关系输出Byte类型的变量
     * @param outputByte 需要输出的Byte类型的变量
     * @param byteOld 给定Byte-String对应关系中的Byte
     * @param strNew 给定Byte-String对应关系中的String
     * @return  页面显示内容
     */
    public static String outputByte(Byte outputByte,Byte[] byteOld,String[] strNew){
        String pageStr="";
        if(outputByte!=null && byteOld!=null && strNew!=null){
            for(int i=0;i<byteOld.length;i++){
                if(outputByte.equals(byteOld[i])){
                    if(i<strNew.length){
                        pageStr = (strNew[i]==null?"":strNew[i]);
                    }
                }
            }
        }
        return pageStr;
    }
    
    /**
     * 输出Boolean类型的变量
     * @param outputBoolean 需要输出的Boolean类型的变量
     * @param trueString 如果变量为“真”，输出的字符串
     * @param falseString 如果变量为“假”，输出的字符串
     * @param nullString 如果变量为“空”，输出的字符串
     * @return 页面显示内容
     */
    public static String outputBoolean(Boolean outputBoolean,String trueString,String falseString,String nullString){
        String pageStr="";
        if(outputBoolean==null){
            pageStr=(nullString==null?"":nullString);
        }
        else{
            if(outputBoolean.equals(new Boolean("true"))){
                pageStr=(trueString==null?"":trueString);
            }
            else{
                pageStr=(falseString==null?"":falseString);
            }
        }
        return pageStr;
    }


}
