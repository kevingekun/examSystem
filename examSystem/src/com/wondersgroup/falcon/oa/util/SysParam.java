package com.wondersgroup.falcon.oa.util;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;

/**
 * <p>Title: ϵͳ���� </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class SysParam {
  private static Logger log = Logger.getLogger(SysParam.class.getName());

  public SysParam() {
  }

  /** �г����еĸ�λ
   *
   * @return
   */
  //Flag="" ���� Flag=1 ֻ�����õ�  Flag=0 δ����
  //id,mc,ms,qybz
  //
  public static String[][] listParams() {
    String ret[][]=null;
    try{
        DBOperation db = new DBOperation();
        String sql="";

        sql = "SELECT CSID,CSMC,CSNR,CSMS" +
              " FROM T_XTCS ORDER BY CSMC";

        List Datas = db.Query(sql);

        if(Datas!=null){
          if (Datas.size()!= 0) {
            ret = new String[Datas.size()][4];
            for (int i = 0; i < Datas.size(); i++) {
              Object[] data = (Object[]) Datas.get(i);
              ret[i][0] = StringUtil.trim(data[0]);
              ret[i][1] = StringUtil.trim(data[1]);
              ret[i][2] = StringUtil.trim(data[2]);
              ret[i][3] = StringUtil.trim(data[3]);
            }
          }
          Datas.clear();
        }

    }
    catch(Exception e){
      log.error(e);
    }
    return ret;

  }

  //�޸�
  public static String updateParam(String ryid,String id,String csnr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //������Ϣ
      String sql = "UPDATE T_XTCS SET CSNR='" + csnr + "' WHERE CSID='" + id + "'";
      db.executeUpdate(sql);

      ret=id;
      SysLog.Write(ryid,"�޸�ϵͳ����","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"�޸�ϵͳ����","0");
     }
     return ret;
  }

  //id,mc,ms,qybz
  public static String[] getParam(String id) {
    String[] ret=new String[]{"","",""};
    try {
      DBOperation db=new DBOperation();
      String sql =
        "SELECT CSMC,CSNR,CSMS FROM T_XTCS WHERE CSID='" + id + "'";
        List Datas = db.Query(sql);

        if(Datas!=null){
          if (Datas.size()!= 0) {
            ret = new String[3];
            Object[] data = (Object[]) Datas.get(0);
            ret[0] = StringUtil.trim(data[0]);
            ret[1] = StringUtil.trim(data[1]);
            ret[2] = StringUtil.trim(data[2]);
          }
          Datas.clear();
        }

    }catch (Exception e) {
      log.error(e);
    }
    return ret;
  }

  public static String getParamValue(String csmc) {
    String ret="";
    //ȱʡ
    if(csmc.equals("��ҳ��С"))
      ret="20";
    else if(csmc.equals("ϵͳ��־"))
      ret="0";
    else if(csmc.equals("SMTP�ʼ�������"))
      ret="localhost";
    else if(csmc.equals("POP3�ʼ�������"))
      ret="localhost";
    else if(csmc.equals("FTP���Ӳ���"))
      ret="anonymous`anonymous`127.0.0.1`21";
    else if(csmc.equals("FTP����·��"))
      ret="/download";
    else if(csmc.equals("FTP�ϴ�·��"))
      ret="/upload";

    try {
      DBOperation db=new DBOperation();
      String sql =
        "SELECT CSNR FROM T_XTCS WHERE CSMC='" + csmc + "'";
        List Datas = db.Query(sql);

        if(Datas!=null){
          if (Datas.size()!= 0) {
            Object data = (Object) Datas.get(0);
            ret = StringUtil.trim(data);
          }
          Datas.clear();
        }


    }catch (Exception e) {
      log.error(e);
    }
    return ret;
  }


}
