package com.wondersgroup.falcon.oa.Public;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.Pubdata.AttachFile;
import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;

/**
 * <p>Title: 政策法规</p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class PolicyMgt {
  private static Logger log = Logger.getLogger(PolicyMgt.class.getName());

  public PolicyMgt() {
  }

  //查询记录
  //fgid,lxid,lxmc,fgbt,fbjb,fgsj
  public String[][] listPolicy(String lxid,String fgsj1,String fgsj2,String fgbt,String fbjb){
    return listPolicy(lxid,fgsj1,fgsj2,fgbt,fbjb,0);
  }

  public String[][] listPolicy(String lxid,String fgsj1,String fgsj2,String fgbt,String fbjb,int Start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        if(!lxid.equals(""))
          sql+=" AND FG.LXID = '" + lxid + "' ";

        if(!fgsj1.equals(""))
          sql+=" AND FG.FGSJ>=TO_DATE('" + fgsj1 + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ";
        if(!fgsj2.equals(""))
          sql+=" AND FG.FGSJ<=TO_DATE('" + fgsj2 + " 23:59:59" + "','yyyy-MM-dd HH24:mi:ss') ";

        if(!fgbt.equals(""))
          sql+=" AND FG.FGBT LIKE '%" + fgbt + "%' ";

        if(!fbjb.equals(""))
          sql+=" AND FG.FBJB LIKE '%" + fbjb + "%' ";

        sql="SELECT FG.FGID,FG.LXID,LX.LXMC,FG.FGBT,FG.FBJB,FG.FGSJ" +
                   " FROM T_ZCFG FG,T_ZCFGFL LX WHERE FG.LXID=LX.LXID(+) " +
                    sql + " ORDER BY FG.FGSJ DESC,FG.LXID,FG.FGBT";

        List ls = db.Query(sql,Start);

        //fgid,lxid,lxmc,fgbt,fbjb,fgsj
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][6];
            for (int i = 0; i < ls.size(); i++) {
              Object[] fg = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(fg[0]);
              ret[i][1] = StringUtil.trim(fg[1]);
              ret[i][2] = StringUtil.trim(fg[2]);
              ret[i][3] = StringUtil.trim(fg[3]);
              ret[i][4] = StringUtil.trim(fg[4]);
              ret[i][5] = StringUtil.trim(fg[5],"yyyy-MM-dd");
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
  public String addPolicy(String lxid,String fgbt,String fbjb,String fgsj,String fgnr,String ryid){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
      String id=db.calculateID("T_ZCFG","FGID",new String[0]);

      String sql = "INSERT INTO T_ZCFG (FGID,LXID,FGBT,FBJB,FGSJ,FGNR) VALUES " +
                   " ('" + id + "','" + lxid + "','" + fgbt + "','" + fbjb + "'," +
                   " TO_DATE('" + fgsj + "','yyyy-MM-dd HH24:mi:ss')," +
                   "'" + fgnr + "')";

      db.executeUpdate(sql);

      //附件
      AttachFile af=new AttachFile();
      af.attachList(id,ryid,"T_ZCFG");

      ret=id;
      SysLog.Write(ryid,"添加政策法规","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"添加政策法规","0");
     }
     return ret;
  }

  //修改
  public String updatePolicy(String ryid,String fgid,String lxid,String fgbt,String fbjb,String fgsj,String fgnr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
       String sql = "UPDATE T_ZCFG SET LXID='" + lxid + "',FGBT='" + fgbt + "',FBJB='" + fbjb + "'," +
                    " FGSJ=TO_DATE('" + fgsj + "','yyyy-MM-dd HH24:mi:ss'),FGNR='" + fgnr + "' WHERE FGID='" + fgid + "'";
      db.executeUpdate(sql);

      //附件


      ret=fgid;
      SysLog.Write(ryid,"修改政策法规","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"修改政策法规","0");
     }
     return ret;
  }

  //删除
  public String delPolicy(String ryid,String id,String realpath){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

      String sql="DELETE FROM T_ZCFG WHERE FGID='" + id + "'";
      db.executeUpdate(sql);

      //附件
      AttachFile af=new AttachFile();
      af.DelAttachedFile(realpath,id,"T_ZCFG");

      ret = id;
      SysLog.Write(ryid,"删除政策法规","1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"删除政策法规","0");
    }
    return ret;
  }


  //lxid,lxmc,fgbt,fbjb,fgsj,fgnr
  public String[] getPolicy(String id) {
    String[] ret=new String[]{"","","","","",""};
    try {
      DBOperation db=new DBOperation();
      String sql="SELECT FG.LXID,LX.LXMC,FG.FGBT,FG.FBJB,FG.FGSJ,FG.FGNR " +
                   " FROM T_ZCFG fG,T_ZCFGFL LX WHERE FG.LXID=LX.LXID(+) " +
                   " AND FG.FGID='" + id + "'";
        List ls = db.Query(sql);

        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[6];
            Object[] l = (Object[]) ls.get(0);
            ret[0] = StringUtil.trim(l[0]);
            ret[1] = StringUtil.trim(l[1]);
            ret[2] = StringUtil.trim(l[2]);
            ret[3] = StringUtil.trim(l[3]);
            ret[4] = StringUtil.trim(l[4],"yyyy-MM-dd");
            ret[5] = StringUtil.trim(l[5],"\r\n","");
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
  public String[][] getPolicyAttach(String id) {
    String[][] ret=null;
    try{
      AttachFile af=new AttachFile();
      List fjlst=af.FJList(id,"T_KWWZ");

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

}
