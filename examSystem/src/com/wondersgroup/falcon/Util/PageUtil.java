package com.wondersgroup.falcon.Util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

/**
 * ҳ���������
 * ���Ը�����Ҫ����ͬ���͵ı���ת����String���ͣ�����ҳ����ʾ
 */
public class PageUtil {

    public PageUtil(){

    }

    /**
     * ���String���͵ı���
     * @param outputStr ��Ҫ�����String���͵ı���
     * @return ҳ����ʾ����
     */
    public static String outputString(String outputStr){
        String pageStr = "";
        if(outputStr!=null){
            pageStr = outputStr;
        }
        return pageStr;
    }

    /**
     * ���Integer���͵ı���
     * @param outputInteger ��Ҫ�����Integer���͵ı���
     * @return ҳ����ʾ����
     */
    public static String outputInteger(Integer outputInteger){
        String pageStr = "";
        if(outputInteger!=null){
            pageStr = outputInteger.toString();
        }
        return pageStr;
    }

    /**
     * ���ݸ����ĸ�ʽ���Date���͵ı���
     * @param outputDate ��Ҫ�����Date���͵ı���
     * @param pattern ���Date�ĸ�ʽ
     * @return ҳ����ʾ����
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
     * ���Character���͵ı���
     * @param outputCharacter ��Ҫ�����Character���͵ı���
     * @return ҳ����ʾ����
     */
    public static String outputCharacter(Character outputCharacter){
        String pageStr="";
        if(outputCharacter!=null){
            pageStr = outputCharacter.toString();
        }
        return pageStr;
    }

    /**
     * ���ݸ�����Character-String��Ӧ��ϵ���Character���͵ı���
     * @param outputCharacter ��Ҫ�����Character���͵ı���
     * @param characterOld ����Character-String��Ӧ��ϵ�е�Character
     * @param strNew ����Character-String��Ӧ��ϵ�е�String
     * @return ҳ����ʾ����
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
     * ���ݸ�����StringOld-StringNew��Ӧ��ϵ���String���͵ı���
     * @param outputString ��Ҫ�����String���͵ı���
     * @param strOld ����StringOld-StringNew��Ӧ��ϵ�е�StringOld
     * @param strNew ����StringOld-StringNew��Ӧ��ϵ�е�StringNew
     * @return ҳ����ʾ����
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
     * ���Byte���͵ı���
     * @param outputByte ��Ҫ�����Byte���͵ı���
     * @return ҳ����ʾ�ַ���
     */
    public static String outputByte(Byte outputByte){
        String pageStr="";
        if(outputByte!=null){
            pageStr = outputByte.toString();
        }
        return pageStr;
    }

    /**
     * ���BigDecimal���͵ı���
     * @param outputBigDecimal ��Ҫ�����BigDecimal���͵ı���
     * @return ҳ����ʾ�ַ���
     */
    public static String outputBigDecimal(BigDecimal outputBigDecimal){
        String pageStr="";
        if(outputBigDecimal!=null){
            pageStr = outputBigDecimal.toString();
        }
        return pageStr;
    }

    /**
     * ���ݸ�����Byte-String��Ӧ��ϵ���Byte���͵ı���
     * @param outputByte ��Ҫ�����Byte���͵ı���
     * @param byteOld ����Byte-String��Ӧ��ϵ�е�Byte
     * @param strNew ����Byte-String��Ӧ��ϵ�е�String
     * @return  ҳ����ʾ����
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
     * ���Boolean���͵ı���
     * @param outputBoolean ��Ҫ�����Boolean���͵ı���
     * @param trueString �������Ϊ���桱��������ַ���
     * @param falseString �������Ϊ���١���������ַ���
     * @param nullString �������Ϊ���ա���������ַ���
     * @return ҳ����ʾ����
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
