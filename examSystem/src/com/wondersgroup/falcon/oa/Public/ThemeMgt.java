package com.wondersgroup.falcon.oa.Public;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.DateUtil;
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

public class ThemeMgt {
  private static Logger log = Logger.getLogger(ThemeMgt.class.getName());

  public ThemeMgt() {
  }

  //主题列表
  //lyid,bmid,bmmc,lybt,ryid,<bmmc>rymc,lysj,hfsm,zhgx
  public String[][] listTheme(String bmid,String lybt,String ryid,String lysj1,String lysj2){
     return listTheme(bmid,lybt,ryid,lysj1,lysj2,0);
  }

  public String[][] listTheme(String bmid,String lybt,String ryid,String lysj1,String lysj2,int Start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        if(!bmid.equals(""))
          sql+=" AND LY.BMID = '" + bmid + "' ";

        if(!ryid.equals(""))
          sql+=" AND LY.RYID = '" + ryid + "' ";

        if(!lybt.equals(""))
          sql+=" AND LY.LYBT LIKE '%" + lybt + "%' ";

        if(!lysj1.equals(""))
          sql+=" AND LY.ZHGX>=TO_DATE('" + lysj1 + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ";
        if(!lysj2.equals(""))
          sql+=" AND LY.ZHGX<=TO_DATE('" + lysj2 + " 23:59:59" + "','yyyy-MM-dd HH24:mi:ss') ";

        sql="SELECT LY.LYID,LY.BMID,LT.BMBT,LY.LYBT,LY.RYID,BM.NAME,RY.REALNAME,to_char(LY.LYSJ,'yyyy-MM-dd HH24:mi:ss') A,LY.HFSM,to_char(LY.ZHGX,'yyyy-MM-dd HH24:mi:ss') B "+
        		"FROM T_LTLY LY,T_LTBM LT,USERS RY,GROUPS BM  " +
            " WHERE LY.BMID=LT.BMID(+) AND LY.RYID=RY.USERNAME(+) AND RY.GROUP_ID=BM.GROUP_ID(+)   " +
            sql + " AND LY.DFLY='0' ORDER BY LY.ZHGX DESC,LY.LYID";

        List ls = db.Query_new(sql,Start);

        //lyid,bmid,bmmc,lybt,ryid,<bmmc>rymc,lysj,hfsm,zhgx
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][9];
            for (int i = 0; i < ls.size(); i++) {
              Object[] ly = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(ly[0]);
              ret[i][1] = StringUtil.trim(ly[1]);
              ret[i][2] = StringUtil.trim(ly[2]);
              ret[i][3] = StringUtil.trim(ly[3]);
              ret[i][4] = StringUtil.trim(ly[4]);
              ret[i][5] = "<" + StringUtil.trim(ly[5]) + ">" + StringUtil.trim(ly[6]);
              ret[i][6] = StringUtil.trim(ly[7],"yyyy-MM-dd HH:mm");
              ret[i][7] = StringUtil.trim(ly[8]);
              ret[i][8] = StringUtil.trim(ly[9],"yyyy-MM-dd HH:mm");
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
  public String addTheme(String dfly,String bmid,String ryid,String lybt,String lynr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();
      Vector v_sql=new Vector();
       //基本信息
      String id=db.getSequenceID("SEQ_LYID");
      String zhgx="TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") + "','yyyy-MM-dd HH24:mi:ss')";
      String sql = "INSERT INTO T_LTLY (LYID,BMID,LYBT,RYID,LYSJ,LYNR,DFLY,HFSM,ZHGX) VALUES " +
                   " ('" + id + "','" + bmid + "','" + lybt + "','" + ryid + "'," + zhgx + "," +
                   " '" + lynr + "','" + dfly + "',0," + zhgx + ")";
      v_sql.add(sql);

      if(!dfly.equals("0")){
        sql = "UPDATE T_LTLY SET HFSM=HFSM+1,ZHGX=" + zhgx + " WHERE LYID='" + dfly + "'";
        v_sql.add(sql);
        sql = "UPDATE T_LTBM SET LYSM=LYSM+1,ZHGX=" + zhgx + " WHERE BMID='" + bmid + "'";
        v_sql.add(sql);
      }else{
        sql = "UPDATE T_LTBM SET ZTSM=ZTSM+1,LYSM=LYSM+1,ZHGX=" + zhgx + " WHERE BMID='" + bmid + "'";
        v_sql.add(sql);
      }

       db.executeUpdate(v_sql);
       v_sql.clear();

      ret=id;
      SysLog.Write(ryid,"论坛留言","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"论坛留言","0");
     }
     return ret;
  }

  //修改
  public String updateTheme(String ryid,String lyid,String lybt,String lynr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();
      Vector v_sql=new Vector();

      String zhgx="TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss") + "','yyyy-MM-dd HH24:mi:ss')";
       //基本信息
       String sql = "UPDATE T_LTLY SET LYBT='" + lybt + "',LYNR='" + lynr + "'," +
           " LYSJ=" + zhgx + ",ZHGX=" + zhgx + " WHERE LYID='" + lyid + "'";
       v_sql.add(sql);

       String[] detail=getTheme(lyid);
       String bmid=detail[0];
       String dfly=detail[4];

       if(!dfly.equals("0")){
        sql = "UPDATE T_LTLY SET ZHGX=" + zhgx + " WHERE LYID='" + dfly + "'";
        v_sql.add(sql);
        sql = "UPDATE T_LTBM SET ZHGX=" + zhgx + " WHERE BMID='" + bmid + "'";
        v_sql.add(sql);
      }else{
        sql = "UPDATE T_LTBM SET ZHGX=" + zhgx + " WHERE BMID='" + bmid + "'";
        v_sql.add(sql);
      }

       db.executeUpdate(v_sql);
       v_sql.clear();

      ret=lyid;
      SysLog.Write(ryid,"修改论坛留言","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"修改论坛留言","0");
     }
     return ret;
  }

  //删除
  public String delTheme(String ryid,String id){
    String ret="0";
    try{
      DBOperation db = new DBOperation();
      Vector v_sql=new Vector();
      String sql="";

      String[] detail=getTheme(id);
      String bmid=detail[0];
      String dfly=detail[4];

      //计算减少的帖子数,最后更新
      int lys=getFeebackCount(id);

      sql="DELETE FROM T_LTLY WHERE DFLY='" + id + "' OR LYID='" + id + "'";
      db.executeUpdate(sql);

      //更新
      if(dfly.equals("0")){  //删除主题
        String bmzhgx = "TO_DATE('" + getLastbyForum(bmid) + "','yyyy-MM-dd HH24:mi:ss')";
        v_sql.add("UPDATE T_LTBM SET ZTSM=ZTSM-1,LYSM=LYSM-" + String.valueOf(lys) + ",ZHGX=" + bmzhgx + " WHERE BMID='" + bmid + "'");
      }else{  //删除帖子
        String lyzhgx= "TO_DATE('" + getLastbyTheme(dfly) + "','yyyy-MM-dd HH24:mi:ss')";
        String bmzhgx ="TO_DATE('" + getLastbyForum(bmid) + "','yyyy-MM-dd HH24:mi:ss')";
        v_sql.add("UPDATE T_LTLY SET HFSM=HFSM-1,ZHGX=" + lyzhgx + " WHERE LYID='" + dfly + "'");
        v_sql.add("UPDATE T_LTBM SET LYSM=LYSM-1,ZHGX=" + bmzhgx + " WHERE BMID='" + bmid + "'");
      }
      db.executeUpdate(v_sql);
      v_sql.clear();

      ret = id;
      SysLog.Write(ryid,"删除论坛留言","1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"删除论坛留言","0");
    }
    return ret;
  }

  //得到主题的最后更新
  private String getLastbyTheme(String lyid){
    String ret="";
    try{
      DBOperation db = new DBOperation();

      String sql = "SELECT MAX(LYSJ) FROM T_LTLY WHERE dfly='" + lyid + "' OR LYID='" + lyid + "'";
      List ls = db.Query(sql);
      if (ls != null) {
        Object c = (Object) ls.get(0); //date
        String tmp = StringUtil.trim(c,"yyyy-MM-dd HH:mm:ss");
        if (!tmp.equals(""))
          ret= tmp;
        ls.clear();
      }

    }catch(Exception e){
      log.error(e);
    }
    return ret;
  }

  //得到版面的最后更新
  private String getLastbyForum(String bmid){
    String ret="";
    try{
      DBOperation db = new DBOperation();

      String sql="SELECT MAX(LYSJ) FROM T_LTLY WHERE BMID='" + bmid + "'";
      List ls = db.Query(sql);
      if (ls != null) {
        Object c = (Object) ls.get(0); //date
        String tmp = StringUtil.trim(c,"yyyy-MM-dd HH:mm:ss");
        if (!tmp.equals(""))
          ret= tmp;
        ls.clear();
      }

    }catch(Exception e){
      log.error(e);
    }
    return ret;
  }

  //某个主题的帖子数(包括主题)
  private int getFeebackCount(String lyid){
    int ret=1;
    try{
      DBOperation db = new DBOperation();

      String sql = "SELECT COUNT(LYID) FROM T_LTLY WHERE DFLY='" + lyid + "'";
      List ls = db.Query(sql);
      if (ls != null) {
        Object c = (Object) ls.get(0); //count
        String tmpc = StringUtil.trim(c);
        if (!tmpc.equals(""))
          ret+= Integer.parseInt(tmpc);
        ls.clear();
      }

    }catch(Exception e){
      log.error(e);
    }
    return ret;
  }

  //主题和回复的信息
  //lyid,bmid,bmmc,lybt,ryid,<bmmc>rymc,lysj,lynr
  public String[][] viewTheme(String id){
    return viewTheme(id,0);
  }

  public String[][] viewTheme(String id,int Start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        sql="SELECT LY.LYID,LY.BMID,LT.NAME,LY.LYBT,LY.RYID,BM.BMMC,RY.Realname,to_char(LY.LYSJ,'yyyy-MM-dd HH24:mi:ss') A,LY.LYNR" +
            " FROM T_LTLY LY,GROUPS LT,USERS RY,T_BM BM " +
            " WHERE LY.BMID=LT.GROUP_ID(+) AND LY.RYID=RY.USERNAME(+) AND RY.Group_Id=BM.BMID(+) " +
            " AND ( LY.DFLY='" + id + "' OR LY.LYID='" + id +"') ORDER BY LY.DFLY,LY.LYID";

        List ls = db.Query_new(sql,Start);

        //lyid,bmid,bmmc,lybt,ryid,<bmmc>rymc,lysj
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][8];
            for (int i = 0; i < ls.size(); i++) {
              Object[] ly = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(ly[0]);
              ret[i][1] = StringUtil.trim(ly[1]);
              ret[i][2] = StringUtil.trim(ly[2]);
              ret[i][3] = StringUtil.trim(ly[3]);
              ret[i][4] = StringUtil.trim(ly[4]);
              ret[i][5] = "<" + StringUtil.trim(ly[5]) + ">" + StringUtil.trim(ly[6]);
              ret[i][6] = StringUtil.trim(ly[7],"yyyy-MM-dd HH:mm");
              ret[i][7] = StringUtil.trim(ly[8],"\r\n","");
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

  //bmid,bmmc,lybt,lynr,dfly
  public String[] getTheme(String id) {
    String[] ret=new String[]{"","","","",""};
    try {
      DBOperation db=new DBOperation();
      String sql ="SELECT LY.BMID,LT.BMBT,LY.LYBT,LY.LYNR,LY.DFLY FROM T_LTLY LY,T_LTBM LT WHERE LY.BMID=LT.BMID(+) AND LYID='" + id + "'";
      List ls = db.Query(sql);

        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[5];
            Object[] l = (Object[]) ls.get(0);
            ret[0] = StringUtil.trim(l[0]);
            ret[1] = StringUtil.trim(l[1]);
            ret[2] = StringUtil.trim(l[2]);
            ret[3] = StringUtil.trim(l[3],"\r\n","");
            ret[4] = StringUtil.trim(l[4]);
          }
          ls.clear();
        }


    }catch (Exception e) {
      log.error(e);
    }
    return ret;
  }
}
