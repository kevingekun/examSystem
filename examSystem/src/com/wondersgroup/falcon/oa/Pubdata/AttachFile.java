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
    /*��������Ϣ�ȱ��浽��ʱ��
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
      
      //ȡ�ü�¼���
      String[] a={"ryid='"+ryid+"'"};
      String jlxh = dbo.calculateID("temptable","FJXH",a);
      
      //ִ�����ݼ�¼����
      String attach_sql = "insert into temptable(RYID,GWID,FJXH,GWBB,FJMC,FJDZ,SCSJ,QTXX) values('"+ryid+"','"+gwid+"','"+jlxh+"','"+fjbb+"','"+fjmc+"','"+fjdz+"','"+curDateTime+"','"+qtxx+"')";
      dbo.executeUpdate(attach_sql);
      dbo.close();
      SysLog.Write(ryid,"�ϴ�һ������:" + fjmc,"1");
     }catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"�ϴ�һ������:" + fjmc,"0");
    }
}
  /*
   *����ʽ�������һ��������¼
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
        SysLog.Write(ryid,"�ϴ�һ������:" + fjmc,"1");
      }catch(Exception e){
        log.error(e);
        SysLog.Write(ryid,"�ϴ�һ������:" + fjmc,"0");
      }
  }
  /*
   *����ʽ�������һ�°汾��¼
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
        SysLog.Write(ryid,"�ϴ�һ���°汾:" + fjmc,"1");
      }catch(Exception e){
        log.error(e);
        SysLog.Write(ryid,"�ϴ�һ������:" + fjmc,"0");
      }
  }

  /*
    *����ʽ�������һ��������¼
    *�����������汾
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
         SysLog.Write(ryid,"���һ������:" + fjmc,"1");
       }catch(Exception e){
         log.error(e);
         SysLog.Write(ryid,"���һ������:" + fjmc,"0");
       }
   }

  /*
 *����list
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
 *����list ����ʱ��temptable
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
          * ɾ����ʱ��
          */
         delTempTable(ryid);
         return attachlist;

    }
/*
 *ɾ����ʱ�����ݣ���ryid����������ݣ�
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
     *ɾ����ʱ�����ݣ���ryid����������ݣ�
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
     *ɾ����ʱ�����ݣ���ryid����������ݣ�
     *
     */
  public void delTempTableJL(String jlxh,String ryid){
       DBOperation dbo = new DBOperation();
        String delete_sql = "delete from temptable where FJXH ='"+jlxh+"' and RYID='"+ryid+"'";
        dbo.executeUpdate(delete_sql);
        dbo.close();
        }

/*
 * ����ʱ��ļ�¼���븽����
 * tablename Ϊ��������
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
     * ����ʱ��ļ�¼���븽����
     *tablename Ϊ��������
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
         *����list ����ʱ��temptable
         *�������ģ����ģ����鸽��
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
//�����ʽ���︽��list��getidΪ�õ���id����fwid,swid...
//tablename Ϊ����
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
//�����ʽ���︽��list��getidΪ�õ���id����fwid,swid...
//tablename Ϊ����
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
 *����ʽ����ɾ����¼
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
   SysLog.Write(ryid,"ɾ��һ���ļ��ĸ������ļ��ţ�" + getid,"1");
 }catch(Exception e){
   log.error(e);
   SysLog.Write(ryid,"ɾ��һ���ļ��ĸ������ļ��ţ�" + getid,"0");
 }
}
//��д��־ FromTableֻ���±���ɾ��ʵ���ļ�
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

  //��д��־
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
 *delete getid ��ĳ��¼��ŵļ�¼
 *
 */
public void DelAttachedFileJLXH(String ryid,String getid,String tablename,String jlxh){
  String tempid = tablename.substring(tablename.length()-2)+"ID";
  String tempname = tablename+"FJ";

 try{ DBOperation dbo= new DBOperation();
   String sql = "delete from " + tempname + " where " + tempid + "='" +
       getid + "' and FJXH='" + jlxh + "'";
       dbo.executeUpdate(sql);
       SysLog.Write(ryid,"ɾ��һ���ļ���һ���������ļ��ţ�" + getid,"1");
 }catch(Exception e){
   log.error(e);
      SysLog.Write(ryid,"ɾ��һ���ļ���һ���������ļ��ţ�" + getid,"0");
 }
}

//add by pn  050621
//ͨ������ȡ����Ӧ���ֶ���
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
