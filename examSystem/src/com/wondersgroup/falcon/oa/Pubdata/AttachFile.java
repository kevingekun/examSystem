package com.wondersgroup.falcon.oa.Pubdata;

import java.util.*;
import java.io.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.DateUtil;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;

public class AttachFile {
private static Logger log = Logger.getLogger(AttachFile.class.getName());

  public AttachFile(){
    }
    /*将附件信息先保存到临时表
     *@param ryid
     *@param gwid
     *@param fjbb
     *@param fjmc
     *@param fjdz
     *@param qtxx
     *@param
     *@param
     */
public void saveAttachFile(String ryid,String gwid,String fjbb,String fjmc,String fjdz,String qtxx){
try{
      DBOperation dbo = new DBOperation();
      String curDateTime = DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
      
      //取得记录序号
      String[] a={"ryid='"+ryid+"'"};
      String jlxh = dbo.calculateID("temptable","FJXH",a);
      
      //执行数据记录插入
      String attach_sql = "insert into temptable(RYID,GWID,FJXH,GWBB,FJMC,FJDZ,SCSJ,QTXX) values('"+ryid+"','"+gwid+"','"+jlxh+"','"+fjbb+"','"+fjmc+"','"+fjdz+"','"+curDateTime+"','"+qtxx+"')";
      dbo.executeUpdate(attach_sql);
      dbo.close();
      SysLog.Write(ryid,"上传一个附件:" + fjmc,"1");
     }catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"上传一个附件:" + fjmc,"0");
    }
}
  /*
   *向正式表里添加一条附件记录
   *
   */
  public void AddAttachedFile(String ryid,String getid,String fjbb,String fjmc,String fjdz,String tablename){
    String tempid = "";
    String tempname="";

    tempid = tablename.substring(tablename.length()-2)+"ID";
    tempname = tablename+"FJ";

    try{
        DBOperation dbo = new DBOperation();
        //String curDateTime = DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
        String[] a={"1=1"};
        String jlxh = dbo.calculateID(tempname,"FJXH",a);
        String attach_sql = "insert into "+tempname+"("+tempid+",FJXH,FJBB,FJMC,FJDZ) values('"+getid+"','"+jlxh+"','"+fjbb+"','"+fjmc+"','"+fjdz+"')";
        dbo.executeUpdate(attach_sql);
        dbo.close();
        SysLog.Write(ryid,"上传一个附件:" + fjmc,"1");
      }catch(Exception e){
        log.error(e);
        SysLog.Write(ryid,"上传一个附件:" + fjmc,"0");
      }
  }
  /*
   *向正式表里添加一新版本记录
   *
   */
  public void AddNewVersion(String ryid,String getid,String fjxh,String fjmc,String fjdz,String tablename){
    String tempid = "";
    String tempname="";

    tempid = tablename.substring(tablename.length()-2)+"ID";
    tempname = tablename+"FJ";

    try{
        DBOperation dbo = new DBOperation();
        //String curDateTime = DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
        String[] a={tempid+"="+getid,"FJXH="+fjxh};
        String fjbb = dbo.calculateID(tempname,"FJBB",a);
        String attach_sql = "insert into "+tempname+"("+tempid+",FJXH,FJBB,FJMC,FJDZ) values('"+getid+"','"+fjxh+"','"+fjbb+"','"+fjmc+"','"+fjdz+"')";
        dbo.executeUpdate(attach_sql);
        dbo.close();
        SysLog.Write(ryid,"上传一个新版本:" + fjmc,"1");
      }catch(Exception e){
        log.error(e);
        SysLog.Write(ryid,"上传一个附件:" + fjmc,"0");
      }
  }

  /*
    *向正式表里添加一条附件记录
    *不包含附件版本
    */
   public void AddAttachedFile(String ryid,String getid,String fjmc,String fjdz,String tablename){
     String tempid = "";
     String tempname="";

     tempid = tablename.substring(tablename.length()-2)+"ID";
     tempname = tablename+"FJ";

     try{
         DBOperation dbo = new DBOperation();
         //String curDateTime = DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
         String[] a={"1=1"};
         String jlxh = dbo.calculateID(tempname,"FJXH",a);
         String attach_sql = "insert into "+tempname+"("+tempid+",FJXH,FJMC,FJDZ) values('"+getid+"','"+jlxh+"','"+fjmc+"','"+fjdz+"')";
         dbo.executeUpdate(attach_sql);
         dbo.close();
         SysLog.Write(ryid,"添加一个附件:" + fjmc,"1");
       }catch(Exception e){
         log.error(e);
         SysLog.Write(ryid,"添加一个附件:" + fjmc,"0");
       }
   }

  /*
 *附件list
 */
public List attachList(String ryid){

         DBOperation dbo = new DBOperation();
         String attachlist_sql =
             "select RYID,FJMC,SCSJ,QTXX,FJDZ,GWBB,FJXH from temptable where RYID='" + ryid + "'";
         List attachlist = dbo.Query(attachlist_sql.toString());
         dbo.close();
         return attachlist;

    }
/*

/*
 *附件list 对临时表temptable
 */
public List attachList(String getid,String ryid,String tablename){
         DBOperation dbo = new DBOperation();
         String attachlist_sql =
             "select RYID,FJXH,FJMC,FJDZ from temptable where RYID='" + ryid + "'";
         List attachlist = dbo.Query(attachlist_sql.toString());
         if(attachlist!=null) {
            for(int i=0;i<attachlist.size();i++){
              Object[] os = (Object[])attachlist.get(i);
              updateFwfj(getid,StringUtil.trim(os[1]),StringUtil.trim(os[2]),StringUtil.trim(os[3]),tablename);
            }
         }
         dbo.close();
         /*
          * 删除零时表
          */
         delTempTable(ryid);
         return attachlist;

    }
/*
 *删除临时表内容（本ryid所插入的内容）
 *
 */
public void delTempTable(String ryid){


  //  String realpath = SysParam.getParamValue("realpath");
   try{
     DBOperation dbo = new DBOperation();
     String delete_sql = "delete from temptable where ryid ='" + ryid + "'";
     dbo.executeUpdate(delete_sql);
     dbo.close();
        }catch(Exception e){
    log.error(e);
    }
    }
    /*
     *删除临时表内容（本ryid所插入的内容）
     *
     */
    public void delTempTable(String realpath,String ryid){
        String path="";

      //  String realpath = SysParam.getParamValue("realpath");
       try{
         List attach_temp = attachList(ryid);
         if(attach_temp!=null){
            for(int i=0;i<attach_temp.size();i++){
              Object[] os = (Object[])attach_temp.get(i);
               path = StringUtil.trim(os[4]);
               path=path.replace('/','\\');
            int start = path.toUpperCase().lastIndexOf("\\UPLOAD\\");
             path = path.substring(start);
            path=realpath+path;
            path = path.replace('\\','/');

             File f = new File(path);
            if(f.exists())
             f.delete();
            }
         }
         DBOperation dbo = new DBOperation();
         String delete_sql = "delete from temptable where ryid ='" + ryid + "'";
         dbo.executeUpdate(delete_sql);
         dbo.close();
            }catch(Exception e){
        log.error(e);
        }
        }

    /*
     *删除临时表内容（本ryid所插入的内容）
     *
     */
  public void delTempTableJL(String jlxh,String ryid){
       DBOperation dbo = new DBOperation();
        String delete_sql = "delete from temptable where FJXH ='"+jlxh+"' and RYID='"+ryid+"'";
        dbo.executeUpdate(delete_sql);
        dbo.close();
        }

/*
 * 将临时表的记录插入附件表
 * tablename 为主表名称
 */
public void updateFwfj(String getid,String fjxh,String fjmc,String fjdz,String tablename){
  String temp = tablename.substring(tablename.length()-2)+"ID";
  try{
    DBOperation dbo = new DBOperation();
    String updatefwfj_sql = "insert into "+tablename+"FJ"+"("+temp+",FJXH,FJMC,FJDZ) values('"+getid+"','"+fjxh+"','"+fjmc+"','"+fjdz+"')";
    dbo.executeUpdate(updatefwfj_sql);
    dbo.close();

    }catch(Exception e){
      log.error(e);
    }
    }
    /*
     * 将临时表的记录插入附件表
     *tablename 为主表名称
     */
    public void InsertToZS(String getid,String fjxh,String fjmc,String fjdz,String fjbb,String tablename){
      String temp = tablename.substring(tablename.length()-2)+"ID";
      try{
        DBOperation dbo = new DBOperation();
        String sql = "insert into "+tablename+"FJ"+"("+temp+",FJXH,FJMC,FJDZ,FJBB) values('"+getid+"','"+fjxh+"','"+fjmc+"','"+fjdz+"','"+fjbb+"')";
        dbo.executeUpdate(sql);
        dbo.close();
        }catch(Exception e){
          log.error(e);
        }
       }
        /*
         *附件list 对临时表temptable
         *对于收文，发文，会议附件
         */
    public List attachListSFH(String getid,String ryid,String tablename){
        DBOperation dbo = new DBOperation();
        String attachlist_sql =
                     "select RYID,FJXH,FJMC,FJDZ,GWBB from temptable where RYID='" + ryid + "'";
                 List attachlist = dbo.Query(attachlist_sql.toString());
                 if(attachlist!=null) {
                    for(int i=0;i<attachlist.size();i++){
                      Object[] os = (Object[])attachlist.get(i);
                      InsertToZS(getid,StringUtil.trim(os[1]),StringUtil.trim(os[2]),StringUtil.trim(os[3]),StringUtil.trim(os[4]),tablename);
                    }
                 }
                 dbo.close();
                 delTempTable(ryid);
                return  attachlist;

            }

/*public void MoveData(String tablename){
      try{
   DBOperation dbo = new DBOperation();
   String updatefwfj_sql = "insert into "+tablename+"(FWID,FJXH,FJBB,FJMC,FJDZ) values('"+fwid+"','"+fjxh+"','"+fjbb+"','"+fjmc+"','"+fjdz+"')";
   dbo.executeUpdate(updatefwfj_sql);
   dbo.close();
   }catch(Exception e){
     log.error(e);
   }

    }*/
//获得正式表里附件list，getid为得到的id，如fwid,swid...
//tablename 为表名
public List FJList(String getid,String tablename) {
    // String[] os = {fwid, ""};
   //String ret[][]=null;
   String tempid = "";
   String tempname="";

    tempid = tablename.substring(tablename.length()-2)+"ID";
    tempname = tablename+"FJ";

   DBOperation dbo= new DBOperation();
   String sql = "select "+tempid+",FJXH,FJMC,FJDZ from "+tempname+" where "+tempid+"='"+getid+"'";
     List list = dbo.Query(sql);
    /* if(list!=null) {
       if (list.size() > 0) {
         for (int i = 0; i < list.size(); i++) {
           Object[] os = (Object[]) list.get(i);
           ret[i][0] = StringUtil.trim(os[0]);//getid
           ret[i][1] = StringUtil.trim(os[1]);//jlxh
           ret[i][2] = StringUtil.trim(os[2]);//fjmc
           ret[i][3] = StringUtil.trim(os[3]);//fjdz
         }
       }
   }*/

  return list;
 }
//获得正式表里附件list，getid为得到的id，如fwid,swid...
//tablename 为表名
public List FJListSFH(String getid,String tablename) {
    // String[] os = {fwid, ""};
   //String ret[][]=null;
   String tempid = tablename.substring(tablename.length()-2)+"ID";
   String tempname = tablename+"FJ";
   DBOperation dbo= new DBOperation();
   String sql = "select "+tempid+",FJXH,FJMC,FJDZ,FJBB from "+tempname+" where "+tempid+"='"+getid+"'";
     List list = dbo.Query(sql);
    /* if(list!=null) {
       if (list.size() > 0) {
         for (int i = 0; i < list.size(); i++) {
           Object[] os = (Object[]) list.get(i);
           ret[i][0] = StringUtil.trim(os[0]);//getid
           ret[i][1] = StringUtil.trim(os[1]);//fjxh
           ret[i][2] = StringUtil.trim(os[2]);//fjmc
           ret[i][3] = StringUtil.trim(os[3]);//fjdz
           ret[i][4] = StringUtil.trim(os[4]);//fjbb
         }
       }
     }
*/
  return list;
 }

/*
 *在正式表里删除记录
 *
 */
public void DelAttachedFile(String realpath,String ryid,String getid,String tablename){
  String tempid = tablename.substring(tablename.length()-2)+"ID";
  String tempname = tablename+"FJ";
  String path="";

 try{

   List filelist = FJList(getid,tablename);
   if(filelist!=null){
     for(int i=0;i<filelist.size();i++){
       Object[] os = (Object[])filelist.get(i);
       path = StringUtil.trim(os[3]);

       path=path.replace('/','\\');
      int start = path.toUpperCase().lastIndexOf("\\UPLOAD\\");
       path = path.substring(start);
       path=realpath+path;
       path=path.replace('\\','/');

          File f = new File(path);
          if(f.exists())
          f.delete();
     }
   }

   DBOperation dbo= new DBOperation();
   String sql = "delete from " + tempname + " where " + tempid + "='" +
       getid + "'";
   dbo.executeUpdate(sql);
   SysLog.Write(ryid,"删除一个文件的附件，文件号：" + getid,"1");
 }catch(Exception e){
   log.error(e);
   SysLog.Write(ryid,"删除一个文件的附件，文件号：" + getid,"0");
 }
}
//不写日志 FromTable只更新表，不删除实际文件
  public void DelAttachedFile(String getid,String tablename){
    //String tempid = tablename.substring(2)+"ID";
    String tempid=getIDbyTablename(tablename);
    String tempname = tablename+"FJ";
    String path="";
   // String realpath=SysParam.getParamValue("realpath");
   try{
     DBOperation dbo= new DBOperation();
     String sql = "delete from " + tempname + " where " + tempid + "='" +
         getid + "'";
     dbo.executeUpdate(sql);
   }catch(Exception e){
     log.error(e);
   }
  }

  //不写日志
  public void DelAttachedFile(String realpath,String getid,String tablename){
    String tempid = tablename.substring(tablename.length()-2)+"ID";
    String tempname = tablename+"FJ";
    String path="";
   // String realpath=SysParam.getParamValue("realpath");
   try{
     List filelist = FJList(getid,tablename);
   if(filelist!=null){
     for(int i=0;i<filelist.size();i++){
       Object[] os = (Object[])filelist.get(i);
       path = StringUtil.trim(os[3]);

       path=path.replace('/','\\');

       int start = path.toUpperCase().lastIndexOf("\\UPLOAD\\");
       path = path.substring(start);
       path=realpath+path;
       path = path.replace('\\','/');
          File f = new File(path);
          if(f.exists())
          f.delete();
     }
   }


     DBOperation dbo= new DBOperation();
     String sql = "delete from " + tempname + " where " + tempid + "='" +
         getid + "'";
     dbo.executeUpdate(sql);
   }catch(Exception e){
     log.error(e);
   }
  }


/*
 *delete getid 中某记录序号的记录
 *
 */
public void DelAttachedFileJLXH(String ryid,String getid,String tablename,String jlxh){
  String tempid = tablename.substring(tablename.length()-2)+"ID";
  String tempname = tablename+"FJ";

 try{ DBOperation dbo= new DBOperation();
   String sql = "delete from " + tempname + " where " + tempid + "='" +
       getid + "' and FJXH='" + jlxh + "'";
       dbo.executeUpdate(sql);
       SysLog.Write(ryid,"删除一个文件的一个附件，文件号：" + getid,"1");
 }catch(Exception e){
   log.error(e);
      SysLog.Write(ryid,"删除一个文件的一个附件，文件号：" + getid,"0");
 }
}

//add by pn  050621
//通过表名取到对应的字段名
private String getIDbyTablename(String tablename){
   String ret="";
   if(tablename.toUpperCase().equals("T_SWDB") || tablename.toUpperCase().equals("T_SLRWFF")){
     ret=tablename.substring(tablename.length()-2,tablename.length()) + "ID";}
   else{
     ret=tablename.substring(2) + "ID";
   }
   return ret;
}

}
