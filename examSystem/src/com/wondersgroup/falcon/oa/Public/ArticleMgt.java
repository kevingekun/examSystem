package com.wondersgroup.falcon.oa.Public;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.Pubdata.AttachFile;
import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.DateUtil;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;
/**
 * <p>Title: 文章管理</p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class ArticleMgt {
  private static Logger log = Logger.getLogger(ArticleMgt.class.getName());

  public ArticleMgt() {
  }

  //查询记录
  //id,kwid,kwmc,kwqk,fbrq,wzbt,rymc,cbrq
  public String[][] listArticle(String kwid,String wzbt,String ryid){
    return listArticle(kwid,wzbt,ryid,0);
  }

  public String[][] listArticle(String kwid,String wzbt,String ryid,int Start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        if(!kwid.equals(""))  //可以查未发表的
          sql += " AND WZ.KWID='" + kwid + "'";
        else        //只能查发表的
          sql += " AND KW.FBRQ IS NOT NULL ";

        if(!wzbt.equals(""))
          sql+=" AND WZ.WZBT LIKE '%" + wzbt + "%' ";

        if(!ryid.equals(""))
          sql+=" AND WZ.RYID= '" + ryid + "' ";

        sql="SELECT WZ.WZID,KW.KWID,KW.KWMC,KW.KWQK,KW.FBRQ,WZ.WZBT,BM.BMMC,RY.RYMC,WZ.CBRQ " +
                   " FROM T_KW KW,T_KWWZ WZ,T_RY RY,T_BM BM WHERE WZ.KWID=KW.KWID(+) AND WZ.RYID=RY.RYID(+) AND RY.BMID=BM.BMID(+) " + sql + " ORDER BY KW.FBRQ DESC,KW.KWMC,KW.KWQK,WZ.WZBT";

        List ls = db.Query(sql,Start);

         //id,kwid,kwmc,kwqk,fbrq,wzbt,rymc,cbrq
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][8];
            for (int i = 0; i < ls.size(); i++) {
              Object[] wz = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(wz[0]);
              ret[i][1] = StringUtil.trim(wz[1]);
              ret[i][2] = StringUtil.trim(wz[2]);
              ret[i][3] = StringUtil.trim(wz[3]);
              ret[i][4] = StringUtil.trim(wz[4],"yyyy-MM-dd");
              ret[i][5] = StringUtil.trim(wz[5]);
              ret[i][6] = "<" + StringUtil.trim(wz[6]) + ">" + StringUtil.trim(wz[7]);
              ret[i][7] = StringUtil.trim(wz[8],"yyyy-MM-dd");

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
  public String addArticle(String kwid,String wzbt,String wznr,String ryid){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
      String id=db.getSequenceID("SEQ_WZID");
      String cbrq="TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd") + "','yyyy-MM-dd')";

      String sql = "INSERT INTO T_KWWZ (WZID,KWID,WZBT,WZNR,RYID,CBRQ ) VALUES " +
                   " ('" + id + "','" + kwid + "','" + wzbt + "','" + wznr + "','" + ryid + "'," + cbrq + ")" ;

      db.executeUpdate(sql);

      //附件
      AttachFile af=new AttachFile();
      af.attachList(id,ryid,"T_KWWZ");

      ret=id;
      SysLog.Write(ryid,"添加文章","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"添加文章","0");
     }
     return ret;
  }

  //修改
  public String updateArticle(String ryid,String wzid,String wzbt,String wznr){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //基本信息
       String sql = "UPDATE T_KWWZ SET WZBT='" + wzbt + "',WZNR='" + wznr + "'," +
          " CBRQ=TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd") + "','yyyy-MM-dd') " +
          " WHERE WZID='" + wzid + "'";
      db.executeUpdate(sql);

      ret=wzid;
      SysLog.Write(ryid,"修改文章","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"修改文章","0");
     }
     return ret;
  }

  //删除
  public String delArticle(String ryid,String id,String realpath){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

      String sql="DELETE FROM T_KWWZ WHERE WZID='" + id + "'";
      db.executeUpdate(sql);

      //附件
      AttachFile af=new AttachFile();
      af.DelAttachedFile(realpath,id,"T_KWWZ");

      ret = id;
      SysLog.Write(ryid,"删除文章","1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"删除文章","0");
    }
    return ret;
  }


  //kwid,kwmc,kwqk,fbrq,wzbt,wznr,rymc,cbrq
  public String[] getArticle(String id) {
    String[] ret=new String[]{"","","","","","","",""};
    try {
      DBOperation db=new DBOperation();
      String sql ="SELECT KW.KWID,KW.KWMC,KW.KWQK,KW.FBRQ,WZ.WZBT,WZ.WZNR,BM.BMMC,RY.RYMC,WZ.CBRQ " +
                   " FROM T_KW KW,T_KWWZ WZ,T_RY RY,T_BM BM WHERE WZ.KWID=KW.KWID(+) AND WZ.RYID=RY.RYID(+) AND RY.BMID=BM.BMID(+) AND WZ.WZID='" + id + "'";
        List ls = db.Query(sql);

        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[8];
            Object[] l = (Object[]) ls.get(0);
            ret[0] = StringUtil.trim(l[0]);
            ret[1] = StringUtil.trim(l[1]);
            ret[2] = StringUtil.trim(l[2]);
            ret[3] = StringUtil.trim(l[3],"yyyy-MM-dd");
            ret[4] = StringUtil.trim(l[4]);
            ret[5] = StringUtil.trim(l[5],"\r\n","");
            ret[6] = "<"+StringUtil.trim(l[6])+ ">" + StringUtil.trim(l[7]);
            ret[7] = StringUtil.trim(l[8],"yyyy-MM-dd");
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
  public String[][] getArticleAttach(String id) {
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
