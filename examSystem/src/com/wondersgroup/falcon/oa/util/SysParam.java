package com.wondersgroup.falcon.oa.util;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;

/**
 * <p>Title: 系统参数 </p>
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

  /** 列出所有的岗位
   *
   * @return
   */
  //Flag="" 所有 Flag=1 只有启用的  Flag=0 未启用
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

  //修改
  public static String updateParam(String ryid,String id,String csnr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
      String sql = "UPDATE T_XTCS SET CSNR='" + csnr + "' WHERE CSID='" + id + "'";
      db.executeUpdate(sql);

      ret=id;
      SysLog.Write(ryid,"修改系统参数","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"修改系统参数","0");
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
    //缺省
    if(csmc.equals("分页大小"))
      ret="20";
    else if(csmc.equals("系统日志"))
      ret="0";
    else if(csmc.equals("SMTP邮件服务器"))
      ret="localhost";
    else if(csmc.equals("POP3邮件服务器"))
      ret="localhost";
    else if(csmc.equals("FTP连接参数"))
      ret="anonymous`anonymous`127.0.0.1`21";
    else if(csmc.equals("FTP下载路径"))
      ret="/download";
    else if(csmc.equals("FTP上传路径"))
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
