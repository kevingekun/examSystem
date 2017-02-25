package com.wondersgroup.falcon.oa.Authentication;

import java.util.*;

import org.apache.log4j.*;
import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;

/**
 * <p>Title: ������Ϣ��ά�� </p>
 * <p>Description: �������ŵ��б��������޸ĺ�ɾ�� </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class DepartManager {
  private static Logger log = Logger.getLogger(DepartManager.class.getName());

  public DepartManager() {
  }

  /** �г����еĸ�λ
   *
   * @return
   */
  //bmid bmmc bmms
  public String[][] listDepart(){
    return listDepart(0);
  }

  public String[][] listDepart(int Start){
    String ret[][]=null;
    try{
        DBOperation db = new DBOperation();
        String sql="";

        sql =
        "SELECT BMID,BMMC,BMMS FROM T_BM  ORDER BY BMMC";

        List Departs = db.Query(sql,Start);
        db.close();

        if(Departs!=null){
        if(Departs.size()!=0){
          ret=new String[Departs.size()][3];
          for (int i = 0; i < Departs.size(); i++) {
            Object[] depart = (Object[]) Departs.get(i);
            ret[i][0]=StringUtil.trim(depart[0]);
            ret[i][1]=StringUtil.trim(depart[1]);
            ret[i][2]=StringUtil.trim(depart[2]);
          }
        }
      }
      Departs.clear();
    }
    catch(Exception e){
      log.error(e);
    }
    return ret;
  }

  public String[] getDepart(String Bmid) {
    String[] ret=new String[]{"",""};
    try {
      DBOperation db=new DBOperation();
      String sql="SELECT BMMC,BMMS FROM T_BM WHERE BMID='" + Bmid + "'";
      List dps=db.Query(sql);

      if(dps!=null){
        if(dps.size()!=0){
          Object[] cz = (Object[]) dps.get(0);
          ret[0] = StringUtil.trim(cz[0]);
          ret[1] = StringUtil.trim(cz[1]);
          dps.clear();
        }
      }
    }catch (Exception e) {
      log.error(e);
    }
    return ret;

  }

  //���
  public String addDepart(String Ryid,String Bmmc,String Bmms){
    String ret="-1";
    try{
     ret=DmExist(Bmmc,"0");
     if(ret.equals("0")){  //������
       DBOperation db = new DBOperation();
       String Bmid = db.calculateID("T_BM", "BMID", new String[0]);
       String sql = "INSERT INTO T_BM (BMID,BMMC,BMMS,SJBM,JGID) VALUES ('" + Bmid +
           "','" + Bmmc + "','" + Bmms + "',NULL,'001')";
       db.executeUpdate(sql);
       db.close();
       ret = Bmid;
       SysLog.Write(Ryid, "��Ӳ���", "1");
     }else if(ret.equals("1"))  //����
       ret="0";

     }catch(Exception e){
       log.error(e);
       SysLog.Write(Ryid,"��Ӳ���","0");
     }
     return ret;
  }

  //�޸�
  public String updateDepart(String Ryid,String Bmid,String Bmmc,String Bmms){
    String ret="-1";
    try{
      ret=DmExist(Bmmc,Bmid);
     if(ret.equals("0")){  //������
       DBOperation db = new DBOperation();
       String sql = "UPDATE T_BM SET BMMC='" + Bmmc + "',BMMS='" + Bmms +
           "' WHERE BMID='" + Bmid + "'";
       db.executeUpdate(sql);
       db.close();
       ret = Bmid;
       SysLog.Write(Ryid, "�޸Ĳ���", "1");
     }else if(ret.equals("1"))  //����
       ret="0";

     }catch(Exception e){
       log.error(e);
       SysLog.Write(Ryid,"�޸Ĳ���","1");
     }
     return ret;
  }

  //ɾ��
  //û�ж�����Ա�͸�λ�ſ���ɾ������
  //-1 ʧ��  0 ��������Ա���߸�λ  bmid �ɹ�
  public String delDepart(String Ryid,String Bmid){
    String ret="-2";
    try{
      if (!Bmid.equals("1")) {
        if (hasUsed(Bmid).equals("")) {
          DBOperation db = new DBOperation();
          db.executeUpdate("DELETE FROM T_BM WHERE BMID='" + Bmid + "'");
          ret = Bmid;
          SysLog.Write(Ryid, "ɾ������", "1");
        }
        else {
          ret = "0";
        }
      }else{
        ret = "0";
      }
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(Ryid,"ɾ������","0");
    }
    return ret;
  }

  //�ж�����
  //0 ������ 1 ���� -1 ����
  public String DmExist(String bmmc,String id){
    String ret="-1";
    try{
     DBOperation db = new DBOperation();
     String sql = "SELECT BMID FROM T_BM WHERE BMMC='" +
         bmmc + "' AND BMID<>'" + id + "'";
     List dpt = db.Query(sql);
     db.close();

     if (dpt != null) {
       if(dpt.size()>0)
         ret="1";
       else
         ret="0";

       dpt.clear();
     }
   }
   catch(Exception e){
     log.error(e);
   }

   return ret;

  }



  //�Ƿ�������Ա����λ
  //����="":û��    <>"":rycount`gwcount
  public String hasUsed(String Czid){
    String ret="";
    try{
      DBOperation db = new DBOperation();
      //��Ա
      String sql = "SELECT COUNT(DISTINCT BMID) FROM T_RY WHERE BMID='" + Czid + "'";
      Object count=null;

      List dps=db.Query(sql);

      if(dps!=null){
        if(dps.size()!=0){
            count = (Object) dps.get(0);
            if(!StringUtil.trim(count).equals("0"))
              ret=StringUtil.trim(count);
        }
        dps.clear();
      }
      //��λ
      sql = "SELECT COUNT(DISTINCT BMID) FROM T_GW WHERE BMID='" + Czid + "'";

      dps=db.Query(sql);

      if(dps!=null){
        if(dps.size()!=0){
            count = (Object) dps.get(0);
            if(!StringUtil.trim(count).equals("0")){
              if (ret.equals(""))
                ret = StringUtil.trim(count);
              else
                ret += "`" + StringUtil.trim(count);
            }
        }
        dps.clear();
      }

    }
    catch(Exception e){
      log.error(e);
    }
    return ret;
  }
}
