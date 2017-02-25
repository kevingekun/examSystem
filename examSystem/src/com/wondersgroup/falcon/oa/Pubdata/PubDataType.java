package com.wondersgroup.falcon.oa.Pubdata;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;

/**
 * <p>Title:  </p>
 * <p>Description: 列表、新增、修改和删除 </p>
 * {"T_GWLX","公文类型"},{"T_JMCD","公文机密程度"},{"T_BGQX","案卷保管期限"},
   {"T_WDLX","文档类型"},{"T_ZTLX","文档载体类型"},{"T_HYLX","会议类型"},
   {"T_GZLX","日程类型"},{"T_RZLX","工作日志类型"},{"T_GGLM","公告栏目"},
   {"T_GZZDLX","规章制度类型"},{"T_ZCFGFL","政策法规类型"},{"T_MJ","密级"}
   ,{"T_RYDQZT","人员当前状态"}
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class PubDataType {
  private static Logger log = Logger.getLogger(PubDataType.class.getName());

  public PubDataType() {
  }

  /** 列出所有的岗位
   *
   * @return
   */
  //Flag="" 所有 Flag=1 只有启用的  Flag=0 未启用
  //id,mc,ms,qybz
  //
  public static String[][] listData(String Table,String Flag) {
    return listData(Table,Flag,0);
  }

  public static String[][] listData(String Table,String Flag,int Start) {
    String ret[][]=null;
    try{
        DBOperation db = new DBOperation();
        String sql="";

        if(!Flag.equals(""))
          sql+=" WHERE QYBZ='" + Flag + "' ";

        sql = "SELECT LXID,LXMC,LXMS,QYBZ" +
              " FROM " + Table + sql + " ORDER BY LXMC";

        List Datas = db.Query(sql,Start);

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

  //添加
  public static String addData(String ryid,String name,String Table,String mc,String ms,String Qybz){
    String ret="0";
    try{
      DBOperation db = new DBOperation();
      Vector v_sql=new Vector();

       //基本信息
      String id=db.calculateID(Table,"LXID",new String[0]);
      String sql = "INSERT INTO " + Table + " (LXID,LXMC,LXMS,QYBZ) VALUES ('" + id + "','" + mc + "','" + ms + "','" + Qybz + "')";
      v_sql.add(sql);

      db.executeUpdate(v_sql);
      v_sql.clear();
      ret=id;
      SysLog.Write(ryid,"添加" + name,"1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"添加" + name,"0");
     }
     return ret;
  }

  //修改
  public static String updateData(String ryid,String name,String Table,String id,String mc,String ms,String Qybz){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
      String sql = "UPDATE " + Table + " SET LXMC='" + mc + "',LXMS='" + ms + "',QYBZ='" + Qybz + "' WHERE LXID='" + id + "'";
      db.executeUpdate(sql);

      ret=id;
      SysLog.Write(ryid,"修改" + name,"1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"修改" + name,"0");
     }
     return ret;
  }

  //删除
  //删除人员关联、权限关联
  public static String delData(String ryid,String name,String Table,String id){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

      String sql="DELETE FROM " + Table + " WHERE LXID='" + id + "'";

      db.executeUpdate(sql);

      ret = id;
      SysLog.Write(ryid,"删除" + name,"1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"删除" + name,"0");
    }
    return ret;
  }


  //id,mc,ms,qybz
  public static String[] getData(String Table,String id) {
    String[] ret=new String[]{"","","",""};
    try {
      DBOperation db=new DBOperation();
      String sql =
        "SELECT LXID,LXMC,LXMS,QYBZ" +
        " FROM " + Table + " WHERE LXID='" + id + "'";
        List Datas = db.Query(sql);

        if(Datas!=null){
          if (Datas.size()!= 0) {
            ret = new String[4];
            Object[] data = (Object[]) Datas.get(0);
            ret[0] = StringUtil.trim(data[0]);
            ret[1] = StringUtil.trim(data[1]);
            ret[2] = StringUtil.trim(data[2]);
            ret[3] = StringUtil.trim(data[3]);
          }
          Datas.clear();
        }

    }catch (Exception e) {
      log.error(e);
    }
    return ret;

  }

  /** 列出所有的岗位
   *
   * @return
   */
  //Flag="" 所有 Flag=1 只有启用的  Flag=0 未启用
  //Jgid="" 所有 Jgid<>"" 该机构的
  //id,mc,ms,qybz
  //
  public static String[][] listData(String Table,String Jgid,String Flag) {
    return listData(Table,Jgid,Flag,0);
  }


  public static String[][] listData(String Table,String Jgid,String Flag,int Start) {
      String ret[][]=null;
      try{
          DBOperation db = new DBOperation();
          String sql="";



          if(!Flag.equals(""))
            sql+=" AND QYBZ='" + Flag + "' ";

          sql = "SELECT LXID,LXMC,LXMS,QYBZ" +
                " FROM " + Table + " WHERE 1=1 "+ sql + " ORDER BY LXID";

          List Datas = db.Query(sql,Start);

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

}
