package com.wondersgroup.falcon.oa.Public;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.DateUtil;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;

/**
 * <p>Title: �������</p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class PublicationMgt {
  private static Logger log = Logger.getLogger(PublicationMgt.class.getName());

  public PublicationMgt() {
  }

  //��ѯ��¼
  //id,kwmc,kwqk,fbrq,kwms
  public String[][] listPublication(String fbrq1,String fbrq2,String kwmc,String kwqk,String fbbz){
    return listPublication(fbrq1,fbrq2,kwmc,kwqk,fbbz,0);
  }

  public String[][] listPublication(String fbrq1,String fbrq2,String kwmc,String kwqk,String fbbz,int Start){
    String ret[][]=null;
    String sql="";
    try{
        DBOperation db = new DBOperation();

        if(!fbbz.equals("")){
          if (fbbz.equals("1")) { //�ѷ����
            sql += " AND FBRQ IS NOT NULL";
            if (!fbrq1.equals(""))
              sql += " AND FBRQ>=TO_DATE('" + fbrq1 + " 00:00:00" +
                  "','yyyy-MM-dd HH24:mi:ss') ";
            if (!fbrq2.equals(""))
              sql += " AND FBRQ<=TO_DATE('" + fbrq2 + " 23:59:59" +
                  "','yyyy-MM-dd HH24:mi:ss') ";
          }
          else {       //δ����
            sql += " AND FBRQ IS NULL ";
          }
        }

        if(!kwmc.equals(""))
          sql+=" AND KWMC LIKE '%" + kwmc + "%' ";

        if(!kwqk.equals(""))
          sql+=" AND KWQK LIKE '%" + kwqk + "%' ";

        sql="SELECT KWID,KWMC,KWQK,FBRQ,KWMS " +
                   " FROM T_KW WHERE 1=1 " + sql + " ORDER BY FBRQ DESC,KWMC,KWQK";

        List ls = db.Query(sql,Start);

        //id,kwmc,kwqk,fbrq,kwms
        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[ls.size()][5];
            for (int i = 0; i < ls.size(); i++) {
              Object[] kw = (Object[]) ls.get(i);
              ret[i][0] = StringUtil.trim(kw[0]);
              ret[i][1] = StringUtil.trim(kw[1]);
              ret[i][2] = StringUtil.trim(kw[2]);
              ret[i][3] = StringUtil.trim(kw[3],"yyyy-MM-dd");
              ret[i][4] = StringUtil.trim(kw[4]);
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

  //���
  public String addPublication(String ryid,String kwmc,String kwqk,String kwms,String fbbz){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //������Ϣ
      String id=db.getSequenceID("SEQ_KWID");
      String fbrq="NULL";
      if(fbbz.equals("1"))
        fbrq="TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd") + "','yyyy-MM-dd')";

      String sql = "INSERT INTO T_KW (KWID,KWMC,KWQK,FBRQ,KWMS ) VALUES " +
                   " ('" + id + "','" + kwmc + "','" + kwqk + "'," + fbrq + ",'" + kwms + "')" ;

      db.executeUpdate(sql);

      ret=id;
      SysLog.Write(ryid,"��ӿ���","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"��ӿ���","0");
     }
     return ret;
  }

  //�޸�
  public String updatePublication(String ryid,String kwid,String kwmc,String kwqk,String kwms,String fbbz){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

      String fbrq="NULL";
      if(fbbz.equals("1"))
        fbrq="TO_DATE('" + DateUtil.getCurrentDateTime("yyyy-MM-dd") + "','yyyy-MM-dd')";

       //������Ϣ
       String sql = "UPDATE T_KW SET KWMC='" + kwmc + "',KWQK='" + kwqk + "',FBRQ=" + fbrq + ",KWMS='" + kwms + "' WHERE KWID='" + kwid + "'";
      db.executeUpdate(sql);

      ret=kwid;
      SysLog.Write(ryid,"�޸Ŀ���","1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"�޸Ŀ���","0");
     }
     return ret;
  }

  //ɾ��
  public String delPublication(String ryid,String id){
    String ret="0";
    try{
      DBOperation db = new DBOperation();
      Vector v_sql=new Vector();
      v_sql.add("DELETE FROM T_KWWZ WHERE KWID='" + id + "'");
      v_sql.add("DELETE FROM T_KW WHERE KWID='" + id + "'");
      db.executeUpdate(v_sql);
      v_sql.clear();

      //����

      ret = id;
      SysLog.Write(ryid,"ɾ������","1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"ɾ������","0");
    }
    return ret;
  }


  //kwmc,kwqk,kwms,fbrq
  public String[] getPublication(String id) {
    String[] ret=new String[]{"","","",""};
    try {
      DBOperation db=new DBOperation();
      String sql ="SELECT KWMC,KWQK,KWMS,FBRQ FROM T_KW WHERE KWID='" + id + "'";
        List ls = db.Query(sql);

        if(ls!=null){
          if (ls.size()!= 0) {
            ret = new String[4];
            Object[] l = (Object[]) ls.get(0);
            ret[0] = StringUtil.trim(l[0]);
            ret[1] = StringUtil.trim(l[1]);
            ret[2] = StringUtil.trim(l[2]);
            ret[3] = StringUtil.trim(l[3],"yyyy-MM-dd");
          }
          ls.clear();
        }


    }catch (Exception e) {
      log.error(e);
    }
    return ret;
  }
}
