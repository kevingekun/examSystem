package com.wondersgroup.falcon.oa.Authentication;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;

/**
 * <p>Title: �û���֤ </p>
 * <p>Description:  </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class Auth {
  private static Logger log = Logger.getLogger(Auth.class.getName());

  public Auth() {
  }

  /** �����֤
  *
  * @param rydm �û�����
  * @param rymm ����
  * @return  >0����֤ͨ���������û�ID
  *          0��û�д��û�
  *          -1���������
  *          -2���û�ͣ��
  *          -3: �����쳣
  */
 //return: ret=ryid,bmid,bmmc,rymc,rygh,dzyj,yddh
 public static String[] Validate(String rydm, String rymm) {
   DBOperation db = new DBOperation();

   String[] ret=new String[]{"-3","","","","","",""};

   try{
     String sql = "SELECT RY.BMID,BM.BMMC,RY.RYID,RY.RYMC,RY.RYMM,RY.RYGH,RY.QYBZ,RY.DZYJ,RY.YDDH " +
                  " FROM T_RY RY,T_BM BM WHERE RY.BMID=BM.BMID(+) AND RYDM='" + rydm.toUpperCase() + "'";
     List persons = db.Query(sql);
     db.close();

     if (persons != null) {
       if(persons.size()>0){
         Object[] person = null;
         person = (Object[]) persons.get(0);
         ret[0] = StringUtil.trim(person[2]);  //ryid
         ret[1] = StringUtil.trim(person[0]);  //bmid
         ret[2] = StringUtil.trim(person[1]);  //bmmc
         ret[3] = StringUtil.trim(person[3]);  //rymc
         ret[4] = StringUtil.trim(person[5]);  //rygh
         ret[5] = StringUtil.trim(person[7]);  //dzyj
         ret[6] = StringUtil.trim(person[8]);  //yddh

         String pass=StringUtil.trim(person[4]);
         String qybz=StringUtil.trim(person[6]);

         if (qybz.equals("1")) {
           if (!pass.equals(Encrpt(rymm)))
             ret[0] = "-1";
         }
         else
           ret[0] = "-2";
       }
       else
         ret[0]="0";

       persons.clear();
     }
     else
       ret[0] = "0";
   }
   catch(Exception e){
     log.error(e);
     ret[0]="-3";
   }

   return ret;
 }

 //�õ��û�������Ȩ��
 //qxlx,qxid,qxmc
 public static String[][] getUserPermission(String Ryid){
   String ret[][]=null;
    try{
        DBOperation db = new DBOperation();
        String sql="";
        if(Ryid.equals("1")){
          sql = "SELECT QXLX,QXID,QXMC FROM T_QX ORDER BY QXLX,QXMC";
        }else{
          sql =
              "SELECT QX.QXLX,QX.QXID,QX.QXMC FROM T_RYGW RYGW,T_GW GW,T_GWQX GWQX,T_QX QX " +
              " WHERE RYGW.GWID=GW.GWID(+) AND GW.GWID=GWQX.GWID(+) AND GWQX.QXID=QX.QXID(+) " +
              " AND RYGW.RYID='" + Ryid + "' ORDER BY QX.QXLX,QX.QXMC";
        }

        List Qxs = db.Query(sql);
        db.close();

        if(Qxs!=null){
          if(Qxs.size()!=0){
            ret=new String[Qxs.size()][3];
            for (int i = 0; i < Qxs.size(); i++) {
              Object[] qx = (Object[]) Qxs.get(i);
              if(!StringUtil.trim(qx[1]).equals("")){
                ret[i][0] = StringUtil.trim(qx[0]);
                ret[i][1] = StringUtil.trim(qx[1]);
                ret[i][2] = StringUtil.trim(qx[2]);
              }
            }
          }
          Qxs.clear();
        }

    }catch(Exception e){
      log.error(e);
    }
    return ret;

 }

 //�Ƿ�ӵ��ĳ��Ȩ��
 //0:��  1:��
 public static String hasPermission(String Ryid,String Qxid){
   String ret="0";

   try{
       if(Ryid.equals("1")){
         ret="1";
       }else{
         DBOperation db = new DBOperation();
         String sql = "";

         sql = "SELECT GWQX.QXID FROM T_RYGW RYGW,T_GW GW,T_GWQX GWQX " +
             " WHERE RYGW.GWID=GW.GWID(+) AND GW.GWID=GWQX.GWID(+) " +
             " AND RYGW.RYID='" + Ryid + "' AND GWQX.QXID='" + Qxid + "'";

         List Qxs = db.Query(sql);
         db.close();

         if (Qxs != null) {
           if (Qxs.size() != 0) {
             ret = "1";
           }
           Qxs.clear();
         }
       }

   }catch(Exception e){
     log.error(e);
   }
   return ret;
 }

 /**  �޸�����
   *
   * @param name �û���
   * @param pass ������
   */
  //-1 ʧ��  0 �����벻��ȷ 1 �ɹ�
  public static String updatePass(String ryid,String rydm,String oldpass,String newpass){
    String ret="-1";
    try {

      String valid[]=Validate(rydm,oldpass);

      if(Integer.parseInt(valid[0])>0){  //��֤�ɹ�
        DBOperation db = new DBOperation();

        String sql = "UPDATE T_RY SET RYMM='" + Encrpt(newpass) + "' WHERE RYDM='" + rydm.toUpperCase() + "'";
        db.executeUpdate(sql);
        ret="1";
        SysLog.Write(ryid,"�޸�����","1");
      }else{
        ret = "0";
        SysLog.Write(ryid, "�޸�����", "0");
      }
    }
    catch (Exception e) {
      log.error(e);
      SysLog.Write(ryid,"�޸�����","0");
    }

    return ret;

  }

 //����
 public static String Encrpt(String rymm){
   String ret=rymm;

   return ret;
 }

 //����
 public static String Decrpt(String rymm){
   String ret=rymm;

   return ret;
 }


}
