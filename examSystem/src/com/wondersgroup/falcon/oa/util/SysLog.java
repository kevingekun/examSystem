package com.wondersgroup.falcon.oa.util;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;

/**
 * <p>Title:  系统日志</p>
 *
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class SysLog {
  private static Logger log = Logger.getLogger(SysLog.class.getName());

  public SysLog() {
  }

  public static String[][] listSysLog(String ryid,String rzsj1,String rzsj2,String rzjg) {
    return listSysLog(ryid,rzsj1,rzsj2,rzjg,0);
  }
  //ryid,<bmmc>rymc,rzsj,rznr,rzjg
  public static String[][] listSysLog(String ryid,String rzsj1,String rzsj2,String rzjg,int Start) {
    String ret[][]=null;
    try{
        DBOperation db = new DBOperation();
        String sql="";

        if(!ryid.equals(""))
          sql+=" AND RZ.RYID='" + ryid + "' ";

        if(!rzsj1.equals(""))
          sql+=" AND RZSJ>=TO_DATE('" + rzsj1 + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ";
        if(!rzsj2.equals(""))
          sql+=" AND RZSJ<=TO_DATE('" + rzsj2 + " 23:59:59" + "','yyyy-MM-dd HH24:mi:ss') ";

        if(!rzjg.equals(""))
          sql+=" AND RZ.RZJG='" + rzjg + "' ";

        sql = "SELECT RZ.RYID,BM.BMMC,RY.RYMC,to_char(RZ.RZSJ,'yyyy-MM-dd HH24:mi:ss') RZSJ,RZ.RZNR,RZ.RZJG FROM T_XTRZ RZ,T_BM BM,T_RY RY " +
            " WHERE RZ.RYID=RY.RYID(+) AND RY.BMID=BM.BMID(+) " + sql + " ORDER BY RZSJ DESC";

        List logs = db.Query_new(sql,Start);

        if(logs!=null){
          if (logs.size()!= 0) {
            ret = new String[logs.size()][5];
            for (int i = 0; i < logs.size(); i++) {
              Object[] log = (Object[]) logs.get(i);
              ret[i][0] = StringUtil.trim(log[0]);
              ret[i][1] = "<" + StringUtil.trim(log[1]) + ">" + StringUtil.trim(log[2]);
              ret[i][2] = StringUtil.trim(log[3]);
              ret[i][3] = StringUtil.trim(log[4]);
              ret[i][4] = StringUtil.trim(log[5]);
              if (ret[i][4].equals("1")) {
                ret[i][4] = "成功";
              }
              else {
                ret[i][4] = "失败";
              }
            }
          }
          logs.clear();
        }

    }
    catch(Exception e){
      log.error(e);
    }
    return ret;

  }

  //添加
  public static void  Write(String ryid,String rznr,String rzjg){
    try{
      DBOperation db = new DBOperation();
      String flag=SysParam.getParamValue("系统日志");
      //System.out.println("听说你要写日志=="+ryid);
      if(flag.equals("1")){
        //基本信息
        String sql = "INSERT INTO T_XTRZ (RYID,RZSJ,RZNR,RZJG) VALUES " +
            "('" + ryid + "',TO_DATE('" +
            DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") +
            "','yyyy-MM-dd HH24:mi:ss'),'" + rznr + "','" + rzjg + "')";

        db.executeUpdate(sql);
      }

     }catch(Exception e){
       log.error(e);
     }
     return;
  }

  //添加
  public static void  Delete(String ryid,String rzsj1,String rzsj2){
    try{
      DBOperation db = new DBOperation();
       String sql="";

      if(!rzsj1.equals(""))
          sql+=" AND RZSJ>=TO_DATE('" + rzsj1 + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ";
      if(!rzsj2.equals(""))
          sql+=" AND RZSJ<=TO_DATE('" + rzsj2 + " 23:59:59" + "','yyyy-MM-dd HH24:mi:ss') ";

       //基本信息
      sql = "DELETE FROM T_XTRZ WHERE 1=1 " + sql;

      db.executeUpdate(sql);

      Write(ryid,"删除日志（" + rzsj1 + "至" + rzsj2 + "）","1");

     }catch(Exception e){
       Write(ryid,"删除日志（" + rzsj1 + "至" + rzsj2 + "）","0");
       log.error(e);
     }
     return;
  }

}
