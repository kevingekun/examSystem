package com.wondersgroup.falcon.oa.Public;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.DateUtil;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;

/**
 * <p>Title: 领导信箱</p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class MessageMgt {
  private static Logger log = Logger.getLogger(MessageMgt.class.getName());

  public MessageMgt() {
  }

  //查询记录
  //jyid,jybt,jynr,jyry,bmmc,jyrymc,jyrq,ryid,bmmc,rymc,dfrq,dfnr
  public String[][] listMessage(String jybt,String jyrq1,String jyrq2){
    return listMessage(jybt,jyrq1,jyrq2,0);
  }

  public String[][] listMessage(String jybt,String jyrq1,String jyrq2,int Start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        if(!jyrq1.equals(""))
          sql+=" AND XX.JYRQ>=TO_DATE('" + jyrq1 + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ";
        if(!jyrq2.equals(""))
          sql+=" AND XX.JYRQ<=TO_DATE('" + jyrq2 + " 23:59:59" + "','yyyy-MM-dd HH24:mi:ss') ";

        if(!jybt.equals(""))
          sql+=" AND XX.JYBT LIKE '%" + jybt + "%' ";

        sql="SELECT XX.JYID,XX.JYBT,XX.JYNR,XX.JYRY,BM1.BMMC,RY1.RYMC,XX.JYRQ,XX.RYID,BM2.BMMC,RY2.RYMC,XX.DFRQ,XX.DFNR" +
                   " FROM T_LDXX XX,T_RY RY1,T_RY RY2,T_BM BM1,T_BM BM2 WHERE XX.JYRY=RY1.RYID(+) AND XX.RYID=RY2.RYID(+) " +
                   " AND RY1.BMID=BM1.BMID(+) AND RY2.BMID=BM2.BMID(+) " + sql + " ORDER BY XX.JYRQ DESC";

        List ls = db.Query(sql,Start);

       //jyid,jybt,jynr,jyry,bmmc,jyrymc,jyrq,ryid,bmmc,rymc,dfrq,dfnr
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][12];
            for (int i = 0; i < ls.size(); i++) {
              Object[] xx = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(xx[0]);
              ret[i][1] = StringUtil.trim(xx[1]);
              ret[i][2] = StringUtil.trim(xx[2],"\r\n","");
              ret[i][3] = StringUtil.trim(xx[3]);
              ret[i][4] = StringUtil.trim(xx[4]);
              ret[i][5] = StringUtil.trim(xx[5]);
              ret[i][6] = StringUtil.trim(xx[6],"yyyy-MM-dd");
              ret[i][7] = StringUtil.trim(xx[7]);
              ret[i][8] = StringUtil.trim(xx[8]);
              ret[i][9] = StringUtil.trim(xx[9]);
              ret[i][10] = StringUtil.trim(xx[10],"yyyy-MM-dd");
              ret[i][11] = StringUtil.trim(xx[11],"\r\n","");
            }
          }
          ls.clear();
        }

    }
    catch(Exception e){
      log.error(e);
    }
    return ret;
  }



//查询记录
  //jyid,jybt,jynr,jyry,bmmc,jyrymc,jyrq,ryid,bmmc,rymc,dfrq,dfnr
  public String[][] listMessage2(String jybt,String jyrq1,String jyrq2,String ryid){
    return listMessage2(jybt,jyrq1,jyrq2,ryid,0);
  }

  public String[][] listMessage2(String jybt,String jyrq1,String jyrq2,String ryid,int Start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        if(!jyrq1.equals(""))
          sql+=" AND XX.JYRQ>=TO_DATE('" + jyrq1 + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ";
        if(!jyrq2.equals(""))
          sql+=" AND XX.JYRQ<=TO_DATE('" + jyrq2 + " 23:59:59" + "','yyyy-MM-dd HH24:mi:ss') ";

        if(!jybt.equals(""))
          sql+=" AND XX.JYBT LIKE '%" + jybt + "%' ";

        sql="SELECT XX.JYID,XX.JYBT,XX.JYNR,XX.JYRY,BM1.BMMC,RY1.RYMC,XX.JYRQ,XX.RYID,BM2.BMMC,RY2.RYMC,XX.DFRQ,XX.DFNR" +
                   " FROM T_LDXX XX,T_RY RY1,T_RY RY2,T_BM BM1,T_BM BM2 WHERE XX.JYRY=RY1.RYID(+) AND XX.RYID=RY2.RYID(+) " +
                   " AND RY1.BMID=BM1.BMID(+) AND RY2.BMID=BM2.BMID(+) and XX.JYRY='"+ryid+"'" + sql + " ORDER BY XX.JYRQ DESC";

        List ls = db.Query(sql,Start);

       //jyid,jybt,jynr,jyry,bmmc,jyrymc,jyrq,ryid,bmmc,rymc,dfrq,dfnr
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][12];
            for (int i = 0; i < ls.size(); i++) {
              Object[] xx = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(xx[0]);
              ret[i][1] = StringUtil.trim(xx[1]);
              ret[i][2] = StringUtil.trim(xx[2],"\r\n","");
              ret[i][3] = StringUtil.trim(xx[3]);
              ret[i][4] = StringUtil.trim(xx[4]);
              ret[i][5] = StringUtil.trim(xx[5]);
              ret[i][6] = StringUtil.trim(xx[6],"yyyy-MM-dd");
              ret[i][7] = StringUtil.trim(xx[7]);
              ret[i][8] = StringUtil.trim(xx[8]);
              ret[i][9] = StringUtil.trim(xx[9]);
              ret[i][10] = StringUtil.trim(xx[10],"yyyy-MM-dd");
              ret[i][11] = StringUtil.trim(xx[11],"\r\n","");
            }
          }
          ls.clear();
        }

    }
    catch(Exception e){
      log.error(e);
    }
    return ret;
  }
  //添加
  public String addMessage(String jybt,String jynr,String jyry){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
      String id=db.getSequenceID("SEQ_JYID");

      String sql = "INSERT INTO T_LDXX (JYID,JYBT,JYNR,JYRY,JYRQ) VALUES " +
                   " ('" + id + "','" + jybt + "','" + jynr + "','" + jyry + "'," +
                   " TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") + "','yyyy-MM-dd HH24:mi:ss'))";

      db.executeUpdate(sql);

      ret=id;
      SysLog.Write(jyry,"领导信箱留言","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(jyry,"领导信箱留言","0");
     }
     return ret;
  }

  //修改
  public String updateMessage(String ryid,String jyid,String jybt,String jynr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
       String sql = "UPDATE T_LDXX SET JYBT='" + jybt + "',JYNR='" + jynr + "'," +
                    " JYRQ=TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") + "','yyyy-MM-dd HH24:mi:ss') " +
                    " WHERE JYID='" + jyid + "'";
      db.executeUpdate(sql);

      ret=jyid;
      SysLog.Write(ryid,"修改领导信箱留言","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"修改领导信箱留言","0");
     }
     return ret;
  }

  //删除
  public String delMessage(String ryid,String id){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

      String sql="DELETE FROM T_LDXX WHERE JYID='" + id + "'";
      db.executeUpdate(sql);

      ret = id;
      SysLog.Write(ryid,"删除领导信箱留言","1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"删除领导信箱留言","0");
    }
    return ret;
  }


  //jybt,jynr
  public String[] getMessage(String id) {
    String[] ret=new String[]{"",""};
    try {
      DBOperation db=new DBOperation();
      String sql ="SELECT JYBT,JYNR FROM T_LDXX WHERE JYID='" + id + "'";
        List ls = db.Query(sql);

        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[2];
            Object[] l = (Object[]) ls.get(0);
            ret[0] = StringUtil.trim(l[0]);
            ret[1] = StringUtil.trim(l[1],"\r\n","");
          }
          ls.clear();
        }


    }catch (Exception e) {
      log.error(e);
    }
    return ret;
  }

  //添加修改回复
  public String updateFeeback(String jyid,String dfry,String dfnr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
       String sql = "UPDATE T_LDXX SET DFNR='" + dfnr + "',RYID='" + dfry + "'," +
                    " DFRQ=TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") + "','yyyy-MM-dd HH24:mi:ss') " +
                    " WHERE JYID='" + jyid + "'";
      db.executeUpdate(sql);

      ret=jyid;
      SysLog.Write(dfry,"领导信箱回复","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(dfry,"领导信箱回复","0");
     }
     return ret;
  }

  //删除
  public String delFeeback(String ryid,String jyid){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

      String sql = "UPDATE T_LDXX SET DFNR=NULL,RYID=NULL," +
                    " DFRQ=NULL " +
                    " WHERE JYID='" + jyid + "'";

      db.executeUpdate(sql);

      ret = jyid;
      SysLog.Write(ryid,"删除领导信箱回复","1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"删除领导信箱回复","0");
    }
    return ret;
  }


  //jybt,dfnr
  public String[] getFeeback(String id) {
    String[] ret=new String[]{"",""};
    try {
      DBOperation db=new DBOperation();
      String sql ="SELECT JYBT,DFNR FROM T_LDXX WHERE JYID='" + id + "'";
        List ls = db.Query(sql);

        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[2];
            Object[] l = (Object[]) ls.get(0);
            ret[0] = StringUtil.trim(l[0]);
            ret[1] = StringUtil.trim(l[1],"\r\n","");
          }
          ls.clear();
        }


    }catch (Exception e) {
      log.error(e);
    }
    return ret;
  }

  //列出新的
  //jyid,jybt,jyry,bmmc,jyrymc,jyrq
  public String[][] listNewMessage(){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        sql = "SELECT XX.JYID,XX.JYBT,XX.JYRY,BM.BMMC,RY.RYMC,to_char(XX.JYRQ,'yyyy-MM-dd HH24:mi:ss') A" +
            " FROM T_LDXX XX,T_RY RY,T_BM BM WHERE XX.JYRY=RY.RYID(+) " +
            " AND RY.BMID=BM.BMID(+) AND XX.RYID IS NULL ORDER BY XX.JYRQ DESC";

        List ls = db.Query_new(sql);

        //jyid,jybt,jyry,bmmc,jyrymc,jyrq
        if (ls != null) {
          if (ls.size() != 0) {
            ret = new String[ls.size()][6];
            for (int i = 0; i < ls.size(); i++) {
              Object[] xx = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(xx[0]);
              ret[i][1] = StringUtil.trim(xx[1]);
              ret[i][2] = StringUtil.trim(xx[2]);
              ret[i][3] = StringUtil.trim(xx[3]);
              ret[i][4] = StringUtil.trim(xx[4]);
              ret[i][5] = StringUtil.trim(xx[5], "yyyy-MM-dd HH:mm");
            }
          }
          ls.clear();
        }
    }
    catch(Exception e){
      log.error(e);
    }
    return ret;
  }
  //列出新的
  //jyid,jybt,jyry,bmmc,jyrymc,jyrq
  public String[][] listNewMessage2(String ryid){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        sql = "SELECT XX.JYID,XX.JYBT,XX.JYRY,BM.BMMC,RY.RYMC,to_char(XX.JYRQ,'yyyy-MM-dd HH24:mi:ss') A" +
            " FROM T_LDXX XX,T_RY RY,T_BM BM WHERE XX.JYRY=RY.RYID(+) " +
            " AND RY.BMID=BM.BMID(+) AND XX.JYRY='"+ryid+"' ORDER BY XX.JYRQ DESC";

        List ls = db.Query_new(sql);

        //jyid,jybt,jyry,bmmc,jyrymc,jyrq
        if (ls != null) {
          if (ls.size() != 0) {
            ret = new String[ls.size()][6];
            for (int i = 0; i < ls.size(); i++) {
              Object[] xx = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(xx[0]);
              ret[i][1] = StringUtil.trim(xx[1]);
              ret[i][2] = StringUtil.trim(xx[2]);
              ret[i][3] = StringUtil.trim(xx[3]);
              ret[i][4] = StringUtil.trim(xx[4]);
              ret[i][5] = StringUtil.trim(xx[5], "yyyy-MM-dd HH:mm");
            }
          }
          ls.clear();
        }
    }
    catch(Exception e){
      log.error(e);
    }
    return ret;
  }

}
