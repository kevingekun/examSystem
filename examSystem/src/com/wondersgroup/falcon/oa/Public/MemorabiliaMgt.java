package com.wondersgroup.falcon.oa.Public;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.Pubdata.AttachFile;
import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;

/**
 * <p>Title: 大事记管理</p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class MemorabiliaMgt {
  private static Logger log = Logger.getLogger(MemorabiliaMgt.class.getName());

  public MemorabiliaMgt() {
  }

  //查询记录
  //id,dsbt,dsnd,dsyf
  public String[][] listMemorabilia(String year,String month,String dsbt){
    return listMemorabilia(year,month,dsbt,0);
  }

  public String[][] listMemorabilia(String year,String month,String dsbt,int Start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        if (!year.equals(""))
          sql += " AND DSND='" + year + "'";
        if (!month.equals(""))
          sql += " AND DSYF='" + month + "'";

        if(!dsbt.equals(""))
          sql+=" AND DSBT LIKE '%" + dsbt + "%' ";

        sql="SELECT DSID,DSBT,DSND,DSYF,DSTS" +
                   " FROM T_DSJ WHERE 1=1 " + sql + " ORDER BY DSND DESC,DSYF,DSBT";

        List ls = db.Query(sql,Start);

        //id,dsbt,dsnd,dsyf
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][5];
            for (int i = 0; i < ls.size(); i++) {
              Object[] ds = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(ds[0]);
              ret[i][1] = StringUtil.trim(ds[1]);
              ret[i][2] = StringUtil.trim(ds[2]);
              ret[i][3] = StringUtil.trim(ds[3]);
              ret[i][4] = StringUtil.trim(ds[4]);
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
  public String addMemorabilia(String dsbt,String dsnd,String dsyf,String dsts,String dsnr,String ryid){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
      String id=db.calculateID("T_DSJ","DSID",new String[0]);

      String sql = "INSERT INTO T_DSJ (DSID,DSBT,DSND,DSYF,DSTS,DSNR ) VALUES " +
                   " ('" + id + "','" + dsbt + "','" + dsnd + "','" + dsyf+ "','" + dsts + "','" + dsnr + "')" ;

      db.executeUpdate(sql);

      //附件
      AttachFile af=new AttachFile();
      af.attachList(id,ryid,"T_DSJ");

      ret=id;
      SysLog.Write(ryid,"添加大事记","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"添加大事记","0");
     }
     return ret;
  }

  //修改
  public String updateMemorabilia(String ryid,String dsid,String dsbt,String dsnd,String dsyf,String dsts,String dsnr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
       String sql = "UPDATE T_DSJ SET DSBT='" + dsbt + "',DSND='" + dsnd + "',DSYF='" + dsyf+ "',DSTS='" + dsts + "',DSNR='" + dsnr + "' WHERE DSID='" + dsid + "'";
      db.executeUpdate(sql);

      ret=dsid;
      SysLog.Write(ryid,"修改大事记","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"修改大事记","0");
     }
     return ret;
  }

  //删除
  public String delMemorabilia(String ryid,String id,String realpath){
    String ret="0";
    try{
      DBOperation db = new DBOperation();
      String sql="DELETE FROM T_DSJ WHERE DSID='" + id + "'";

      db.executeUpdate(sql);

      //附件
      AttachFile af=new AttachFile();
      af.DelAttachedFile(realpath,id,"T_DSJ");

      ret = id;
      SysLog.Write(ryid,"删除大事记","1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"删除大事记","0");
    }
    return ret;
  }


  //dsbt,dsnd,dsyf,dsnr
  public String[] getMemorabilia(String id) {
    String[] ret=new String[]{"","","","",""};
    try {
      DBOperation db=new DBOperation();
      String sql ="SELECT DSBT,DSND,DSYF,DSTS,DSNR FROM T_DSJ WHERE DSID='" + id + "'";
        List ls = db.Query(sql);

        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[5];
            Object[] l = (Object[]) ls.get(0);
            ret[0] = StringUtil.trim(l[0]);
            ret[1] = StringUtil.trim(l[1]);
            ret[2] = StringUtil.trim(l[2]);
            ret[3] = StringUtil.trim(l[3]);
            ret[4] = StringUtil.trim(l[4],"\r\n","");
          }
          ls.clear();
        }


    }catch (Exception e) {
      log.error(e);
    }
    return ret;
  }

  //得到附件
  //id,xh,mc,dz
  public String[][] getMemorabiliaAttach(String id) {
    String[][] ret=null;
    try{
      AttachFile af=new AttachFile();
      List fjlst=af.FJList(id,"T_DSJ");

      if(fjlst!=null){
        ret = new String[fjlst.size()][4];
        for (int i = 0; i < fjlst.size(); i++) {
          Object[] ls = (Object[]) fjlst.get(i);
          ret[i][0] = StringUtil.trim(ls[0]);
          ret[i][1] = StringUtil.trim(ls[1]);
          ret[i][2] = StringUtil.trim(ls[2]);
          ret[i][3] = StringUtil.trim(ls[3]);
        }
        fjlst.clear();
      }

    }catch(Exception e){
      log.error(e);
    }

    return ret;
  }


  public String[] listAllYear(){
      String[] ret=null;
      try {
        DBOperation db=new DBOperation();
        String sql ="SELECT DISTINCT DSND FROM T_DSJ ORDER BY DSND";
          List ls = db.Query(sql);

          if(ls!=null){
            if (ls.size()!= 0) {
              ret = new String[ls.size()];
              for(int i=0;i<ls.size();i++){
                Object y = (Object) ls.get(0);
                ret[i] = StringUtil.trim(y);
              }
            }
            ls.clear();
          }


      }catch (Exception e) {
        log.error(e);
      }
      return ret;
  }
}
