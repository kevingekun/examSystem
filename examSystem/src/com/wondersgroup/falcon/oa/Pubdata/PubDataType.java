package com.wondersgroup.falcon.oa.Pubdata;

import java.util.*;

import org.apache.log4j.*;

import com.wondersgroup.falcon.oa.database.DBOperation;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysLog;

/**
 * <p>Title:  </p>
 * <p>Description: �б��������޸ĺ�ɾ�� </p>
 * {"T_GWLX","��������"},{"T_JMCD","���Ļ��̶ܳ�"},{"T_BGQX","����������"},
   {"T_WDLX","�ĵ�����"},{"T_ZTLX","�ĵ���������"},{"T_HYLX","��������"},
   {"T_GZLX","�ճ�����"},{"T_RZLX","������־����"},{"T_GGLM","������Ŀ"},
   {"T_GZZDLX","�����ƶ�����"},{"T_ZCFGFL","���߷�������"},{"T_MJ","�ܼ�"}
   ,{"T_RYDQZT","��Ա��ǰ״̬"}
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class PubDataType {
  private static Logger log = Logger.getLogger(PubDataType.class.getName());

  public PubDataType() {
  }

  /** �г����еĸ�λ
   *
   * @return
   */
  //Flag="" ���� Flag=1 ֻ�����õ�  Flag=0 δ����
  //id,mc,ms,qybz
  //
  public static String[][] listData(String Table,String Flag) {
    return listData(Table,Flag,0);
  }

  public static String[][] listData(String Table,String Flag,int Start) {
    String ret[][]=null;
    try{
        DBOperation db = new DBOperation();
        String sql="";

        if(!Flag.equals(""))
          sql+=" WHERE QYBZ='" + Flag + "' ";

        sql = "SELECT LXID,LXMC,LXMS,QYBZ" +
              " FROM " + Table + sql + " ORDER BY LXMC";

        List Datas = db.Query(sql,Start);

        if(Datas!=null){
          if (Datas.size()!= 0) {
            ret = new String[Datas.size()][4];
            for (int i = 0; i < Datas.size(); i++) {
              Object[] data = (Object[]) Datas.get(i);
              ret[i][0] = StringUtil.trim(data[0]);
              ret[i][1] = StringUtil.trim(data[1]);
              ret[i][2] = StringUtil.trim(data[2]);
              ret[i][3] = StringUtil.trim(data[3]);
            }
          }
          Datas.clear();
        }

    }
    catch(Exception e){
      log.error(e);
    }
    return ret;

  }

  //���
  public static String addData(String ryid,String name,String Table,String mc,String ms,String Qybz){
    String ret="0";
    try{
      DBOperation db = new DBOperation();
      Vector v_sql=new Vector();

       //������Ϣ
      String id=db.calculateID(Table,"LXID",new String[0]);
      String sql = "INSERT INTO " + Table + " (LXID,LXMC,LXMS,QYBZ) VALUES ('" + id + "','" + mc + "','" + ms + "','" + Qybz + "')";
      v_sql.add(sql);

      db.executeUpdate(v_sql);
      v_sql.clear();
      ret=id;
      SysLog.Write(ryid,"���" + name,"1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"���" + name,"0");
     }
     return ret;
  }

  //�޸�
  public static String updateData(String ryid,String name,String Table,String id,String mc,String ms,String Qybz){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

       //������Ϣ
      String sql = "UPDATE " + Table + " SET LXMC='" + mc + "',LXMS='" + ms + "',QYBZ='" + Qybz + "' WHERE LXID='" + id + "'";
      db.executeUpdate(sql);

      ret=id;
      SysLog.Write(ryid,"�޸�" + name,"1");
     }catch(Exception e){
       log.error(e);
       SysLog.Write(ryid,"�޸�" + name,"0");
     }
     return ret;
  }

  //ɾ��
  //ɾ����Ա������Ȩ�޹���
  public static String delData(String ryid,String name,String Table,String id){
    String ret="0";
    try{
      DBOperation db = new DBOperation();

      String sql="DELETE FROM " + Table + " WHERE LXID='" + id + "'";

      db.executeUpdate(sql);

      ret = id;
      SysLog.Write(ryid,"ɾ��" + name,"1");
    }
    catch(Exception e){
      log.error(e);
      SysLog.Write(ryid,"ɾ��" + name,"0");
    }
    return ret;
  }


  //id,mc,ms,qybz
  public static String[] getData(String Table,String id) {
    String[] ret=new String[]{"","","",""};
    try {
      DBOperation db=new DBOperation();
      String sql =
        "SELECT LXID,LXMC,LXMS,QYBZ" +
        " FROM " + Table + " WHERE LXID='" + id + "'";
        List Datas = db.Query(sql);

        if(Datas!=null){
          if (Datas.size()!= 0) {
            ret = new String[4];
            Object[] data = (Object[]) Datas.get(0);
            ret[0] = StringUtil.trim(data[0]);
            ret[1] = StringUtil.trim(data[1]);
            ret[2] = StringUtil.trim(data[2]);
            ret[3] = StringUtil.trim(data[3]);
          }
          Datas.clear();
        }

    }catch (Exception e) {
      log.error(e);
    }
    return ret;

  }

  /** �г����еĸ�λ
   *
   * @return
   */
  //Flag="" ���� Flag=1 ֻ�����õ�  Flag=0 δ����
  //Jgid="" ���� Jgid<>"" �û�����
  //id,mc,ms,qybz
  //
  public static String[][] listData(String Table,String Jgid,String Flag) {
    return listData(Table,Jgid,Flag,0);
  }


  public static String[][] listData(String Table,String Jgid,String Flag,int Start) {
      String ret[][]=null;
      try{
          DBOperation db = new DBOperation();
          String sql="";



          if(!Flag.equals(""))
            sql+=" AND QYBZ='" + Flag + "' ";

          sql = "SELECT LXID,LXMC,LXMS,QYBZ" +
                " FROM " + Table + " WHERE 1=1 "+ sql + " ORDER BY LXID";

          List Datas = db.Query(sql,Start);

          if(Datas!=null){
            if (Datas.size()!= 0) {
              ret = new String[Datas.size()][4];
              for (int i = 0; i < Datas.size(); i++) {
                Object[] data = (Object[]) Datas.get(i);
                ret[i][0] = StringUtil.trim(data[0]);
                ret[i][1] = StringUtil.trim(data[1]);
                ret[i][2] = StringUtil.trim(data[2]);
                ret[i][3] = StringUtil.trim(data[3]);
              }
            }
            Datas.clear();
          }

      }
      catch(Exception e){
        log.error(e);
      }
      return ret;

    }

}
