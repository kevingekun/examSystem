package com.wondersgroup.falcon.oa.Public;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.Pubdata.AttachFile;
import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.DateUtil;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;
/**
 * <p>Title: 规章制度</p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class RegulationMgt {
  private static Logger log = Logger.getLogger(RegulationMgt.class.getName());

  public RegulationMgt() {
  }

  //查询记录
  //ZDID,LXID,LXMC,ZDBT,BMID,BMMC,ZDSJ
  public String[][] listRegulation(String lxid,String zdsj1,String zdsj2,String zdbt,String bmid){
    return listRegulation(lxid,zdsj1,zdsj2,zdbt,bmid,0);
  }

  public String[][] listRegulation(String lxid,String zdsj1,String zdsj2,String zdbt,String bmid,int Start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        if(!lxid.equals(""))
          sql+=" AND ZD.LXID = '" + lxid + "' ";

        if(!zdsj1.equals(""))
          sql+=" AND ZD.ZDSJ>=TO_DATE('" + zdsj1 + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ";
        if(!zdsj2.equals(""))
          sql+=" AND ZD.ZDSJ<=TO_DATE('" + zdsj2 + " 23:59:59" + "','yyyy-MM-dd HH24:mi:ss') ";

        if(!zdbt.equals(""))
          sql+=" AND ZD.ZDBT LIKE '%" + zdbt + "%' ";

        if(!bmid.equals(""))
          sql+=" AND ZD.BMID = '" + bmid + "' ";

        sql="SELECT ZD.ZDID,ZD.LXID,LX.LXMC,ZD.ZDBT,ZD.BMID,BM.BMMC,ZD.ZDSJ" +
                   " FROM T_GZZD ZD,T_GZZDLX LX,T_BM BM WHERE ZD.LXID=LX.LXID(+) AND ZD.BMID=BM.BMID(+) " +
                    sql + " ORDER BY ZD.ZDSJ DESC,ZD.LXID,ZD.ZDBT";

        List ls = db.Query(sql,Start);

        //ZDID,LXID,LXMC,ZDBT,BMID,BMMC,ZDSJ
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][7];
            for (int i = 0; i < ls.size(); i++) {
              Object[] zd = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(zd[0]);
              ret[i][1] = StringUtil.trim(zd[1]);
              ret[i][2] = StringUtil.trim(zd[2]);
              ret[i][3] = StringUtil.trim(zd[3]);
              ret[i][4] = StringUtil.trim(zd[4]);
              ret[i][5] = StringUtil.trim(zd[5]);
              ret[i][6] = StringUtil.trim(zd[6],"yyyy-MM-dd");
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
  public String addRegulation(String lxid,String zdbt,String bmid,String zdnr,String ryid){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
      String id=db.getSequenceID("SEQ_GZID");

      String sql = "INSERT INTO T_GZZD (ZDID,LXID,ZDBT,BMID,ZDSJ,ZDNR) VALUES " +
                   " ('" + id + "','" + lxid + "','" + zdbt + "','" + bmid + "'," +
                   " TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") + "','yyyy-MM-dd HH24:mi:ss')," +
                   "'" + zdnr + "')";

      db.executeUpdate(sql);

      //附件
      AttachFile af=new AttachFile();
      af.attachList(id,ryid,"T_GZZD");

      ret=id;
      SysLog.Write(ryid,"添加规章制度","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"添加规章制度","0");
     }
     return ret;
  }

  //修改
  public String updateRegulation(String ryid,String zdid,String lxid,String zdbt,String zdnr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
       String sql = "UPDATE T_GZZD SET LXID='" + lxid + "',ZDBT='" + zdbt + "'," +
           " ZDSJ=TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") + "','yyyy-MM-dd HH24:mi:ss')," +
           " ZDNR='" + zdnr + "' WHERE ZDID='" + zdid + "'";
      db.executeUpdate(sql);

      //附件


      ret=zdid;
      SysLog.Write(ryid,"修改规章制度","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"修改规章制度","0");
     }
     return ret;
  }

  //删除
  public String delRegulation(String ryid,String id,String realpath){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

      String sql="DELETE FROM T_GZZD WHERE ZDID='" + id + "'";
      db.executeUpdate(sql);

      //附件
      AttachFile af=new AttachFile();
      af.DelAttachedFile(realpath,id,"T_GZZD");

      ret = id;
      SysLog.Write(ryid,"删除规章制度","1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"删除规章制度","0");
    }
    return ret;
  }


  //LXID,LXMC,ZDBT,BMID,BMMC,ZDSJ,ZDNR
  public String[] getRegulation(String id) {
    String[] ret=new String[]{"","","","","","",""};
    try {
      DBOperation db=new DBOperation();
      String sql="SELECT ZD.LXID,LX.LXMC,ZD.ZDBT,ZD.BMID,BM.BMMC,ZD.ZDSJ,ZD.ZDNR " +
                   " FROM T_GZZD ZD,T_GZZDLX LX,T_BM BM WHERE ZD.LXID=LX.LXID(+) AND ZD.BMID=BM.BMID(+) " +
                   " AND ZD.ZDID='" + id + "'";
        List ls = db.Query(sql);

        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[7];
            Object[] l = (Object[]) ls.get(0);
            ret[0] = StringUtil.trim(l[0]);
            ret[1] = StringUtil.trim(l[1]);
            ret[2] = StringUtil.trim(l[2]);
            ret[3] = StringUtil.trim(l[3]);
            ret[4] = StringUtil.trim(l[4]);
            ret[5] = StringUtil.trim(l[5],"yyyy-MM-dd");
            ret[6] = StringUtil.trim(l[6],"\r\n","");
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
  public String[][] getRegulationAttach(String id) {
    String[][] ret=null;
    try{
      AttachFile af=new AttachFile();
      List fjlst=af.FJList(id,"T_GZZD");

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
