package com.wondersgroup.falcon.oa.Public;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;
/**
 * <p>Title: 论坛</p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class ForumMgt {
  private static Logger log = Logger.getLogger(ForumMgt.class.getName());

  public ForumMgt() {
  }
  
  public String[][] listForum(){
	  return this.listForum(0);
  }

  //版面列表
  //bmid,bmbt,bmms,ztsm,lysm,zhgx,rys(<bmmc>rymc，<bmmc>rymc，……)
  public String[][] listForum(int start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        sql="SELECT BMID,BMBT,BMMS,ZTSM,LYSM,to_char(ZHGX,'yyyy-MM-dd HH24:mi:ss') A FROM T_LTBM ORDER BY BMBT ";

        List ls = db.Query_new(sql,start);

       //bmid,bmbt,bmms,ztsm,lysm,zhgx,rys(<bmmc>rymc，<bmmc>rymc，……)
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][8];
            for (int i = 0; i < ls.size(); i++) {
              Object[] bm = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(bm[0]);
              ret[i][1] = StringUtil.trim(bm[1]);
              ret[i][2] = StringUtil.trim(bm[2]);
              ret[i][3] = StringUtil.trim(bm[3]);
              ret[i][4] = StringUtil.trim(bm[4]);
              ret[i][5] = StringUtil.trim(bm[5],"yyyy-MM-dd HH:mm");
              ret[i][6] = "";
              ret[i][7] = "";
            }
          }
          ls.clear();
        }


        //版面管理人员
        String[][] bmgl=null;
        sql="SELECT LT.BMID,GL.RYID,BM.NAME,RY.REALNAME FROM T_LTBM LT,T_LTBMGL GL,GROUPS BM,USERS RY  WHERE LT.BMID=GL.BMID(+) AND GL.RYID=RY.USERNAME(+) AND RY.Group_Id=BM.GROUP_ID(+)  ORDER BY LT.BMBT";
        List gls = db.Query(sql);
        if(ls!=null){
          if (gls.size()!= 0) {
            bmgl = new String[gls.size()][4];
            for (int i = 0; i < gls.size(); i++) {
              Object[] gl = (Object[]) gls.get(i);
              bmgl[i][0] = StringUtil.trim(gl[0]);
              bmgl[i][1] = StringUtil.trim(gl[1]);
              bmgl[i][2] = StringUtil.trim(gl[2]);
              bmgl[i][3] = StringUtil.trim(gl[3]);;
            }
          }
          gls.clear();
        }


        //显示
        if(bmgl!=null){
          for (int i = 0; i < ret.length; i++) {
            String flag = "0";
             for(int j=0;j<bmgl.length;j++){
                if(ret[i][0].equals(bmgl[j][0])){   //ltbmid
                  if(!bmgl[j][1].equals(""))
                    ret[i][6]+= "<" + bmgl[j][2] + ">" + bmgl[j][3] + "，";
                    ret[i][7]+=bmgl[j][1]+",";
                  flag = "1";
                }else if (flag.equals("1")){ //该序号已经查找完毕
                  break;
                }
              }
              if (ret[i][6].endsWith("，"))
                ret[i][6] = ret[i][6].substring(0, ret[i][6].length() - 1);
              if (ret[i][7].endsWith("，"))
            	  ret[i][7] = ret[i][7].substring(0, ret[i][7].length() - 1);
          }
        }
    }
    catch(Exception e){
      log.error(e);
    }
    return ret;
  }

  //添加
  public String addForum(String ryid,String bmbt,String bmms,String rylst){
    String ret="0";
    try{
      DBOperation db = new DBOperation();
      Vector v_sql=new Vector();
       //基本信息
      String id=db.calculateID("T_LTBM","BMID",new String[0]);

      String sql = "INSERT INTO T_LTBM (BMID,BMBT,BMMS,ZTSM,LYSM,ZHGX) VALUES " +
                   " ('" + id + "','" + bmbt + "','" + bmms + "',0,0,NULL)";
      v_sql.add(sql);

      //人员信息
       sql = "DELETE FROM T_LTBMGL WHERE BMID='" + id + "'";
       v_sql.add(sql);
       String[] rys = StringUtil.split(rylst, ";");
       if (rys != null) {
         for (int i = 0; i < rys.length; i++) {
           sql = "INSERT INTO T_LTBMGL (BMID,RYID) VALUES ('" + id + "','" + rys[i] + "')";
           v_sql.add(sql);
         }
       }

       db.executeUpdate(v_sql);
       v_sql.clear();

      ret=id;
      SysLog.Write(ryid,"添加论坛版面","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"添加论坛版面","0");
     }
     return ret;
  }

  //修改
  public String updateForum(String ryid,String bmid,String bmbt,String bmms,String rylst){
    String ret="0";
    try{
      DBOperation db = new DBOperation();
      Vector v_sql=new Vector();
       //基本信息
       String sql = "UPDATE T_LTBM SET BMBT='" + bmbt + "',BMMS='" + bmms + "' WHERE BMID='" + bmid + "'";
       v_sql.add(sql);

       //人员信息
       sql = "DELETE FROM T_LTBMGL WHERE BMID='" + bmid + "'";
       v_sql.add(sql);
       String[] rys = StringUtil.split(rylst, ";");
       if (rys != null) {
         for (int i = 0; i < rys.length; i++) {
           sql = "INSERT INTO T_LTBMGL (BMID,RYID) VALUES ('" + bmid + "','" + rys[i] + "')";
           v_sql.add(sql);
         }
       }

       db.executeUpdate(v_sql);
       v_sql.clear();


      ret=bmid;
      SysLog.Write(ryid,"修改论坛版面","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"修改论坛版面","0");
     }
     return ret;
  }

  //删除
  public String delForum(String ryid,String id){
    String ret="0";
    try{
      DBOperation db = new DBOperation();
      Vector v_sql=new Vector();

      v_sql.add("DELETE FROM T_LTLY WHERE BMID='" + id + "'");
      v_sql.add("DELETE FROM T_LTBMGL WHERE BMID='" + id + "'");
      v_sql.add("DELETE FROM T_LTBM WHERE BMID='" + id + "'");

      db.executeUpdate(v_sql);

      ret = id;
      SysLog.Write(ryid,"删除论坛版面","1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"删除论坛版面","0");
    }
    return ret;
  }


  //bmbt,bmms,rylst(bmid`ryid;bmid`ryid;……;)
  public String[] getForum(String id) {
    String[] ret=new String[]{"","",""};
    try {
      DBOperation db=new DBOperation();
      String sql ="SELECT BMBT,BMMS FROM T_LTBM WHERE BMID='" + id + "'";
      List ls = db.Query(sql);

        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[3];
            Object[] l = (Object[]) ls.get(0);
            ret[0] = StringUtil.trim(l[0]);
            ret[1] = StringUtil.trim(l[1]);
            ret[2] = "";
          }
          ls.clear();
        }


        //版面管理人员
        String[][] bmgl=null;
        sql="SELECT BMID,RYID FROM T_LTBMGL WHERE BMID='" + id + "'";
        List gls = db.Query(sql);
        if(ls!=null){
          if (gls.size()!= 0) {
            for (int i = 0; i < gls.size(); i++) {
              Object[] gl = (Object[]) gls.get(i);
              ret[2]+=StringUtil.trim(gl[0]) + "`" + StringUtil.trim(gl[1]) + ";";
            }
          }
          gls.clear();
        }


    }catch (Exception e) {
      log.error(e);
    }
    return ret;
  }
}
