package com.wondersgroup.falcon.oa.database;

import java.sql.*;
import java.util.*;

import org.apache.log4j.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.oa.util.SysParam;
import com.wondersgroup.falcon.persistence.HibernateUtil;

/**
 * <p>Title: ��ݿ����</p>
 * <p>Description: ��ѯ�����¡�ɾ������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class DBOperation {

  private static Logger log = Logger.getLogger(DBOperation.class.getName());

  private Connection conn = null;
  private PreparedStatement pst = null;


  public DBOperation() {
  }

  /** ��ѯ
   *
   * @param sql
   * @return
   */
  public List Query(String sql){
    return  Query(sql,0);
  }
  
  public List Query_new(String sql){
    return  Query_new(sql,0);
  }

  public List Query(String sql,int start) {
    List list = new LinkedList();
    try {
      boolean flag = false;

      if (conn == null) {
        flag = true;
      }
      if (conn != null) {
        if (conn.isClosed()) {
          flag = true;
        }
      }
      if (flag) {
    	if(!session.isOpen()){
    		session=HibernateUtil.getSession();
    	}
    	HibernateUtil.beginTransaction();
    	conn=session.connection();
    	
        //conn = DBControl.getConnection();
    	  
      }
      //��ϵͳ�����ȡ
      if(start!=0){
        String ps=SysParam.getParamValue("��ҳ��С");

        //ȥ�еı���
        String str_sql = sql.toUpperCase();
        if (str_sql.indexOf("*") == -1) {
          int firstnum = str_sql.indexOf("SELECT");
          int lastnum = str_sql.indexOf("FROM");
          String st = sql.substring(firstnum + 7, lastnum - 1);
          String end = sql.substring(lastnum);

          String[] ss = StringUtil.split(st, ",");
          StringBuffer sb = new StringBuffer("SELECT ");
          int length = ss.length;
          for (int i = 0; i < length; i++) {
            if (i != length - 1)
              sb.append(ss[i]).append(" AS DFOA_COL" + i + ",");
            else
              sb.append(ss[i]).append(" AS DFOA_COL" + i + " ");
          }
          sb.append(end);

          sql = sb.toString();
        }


        int pagesize = Integer.parseInt(ps);
        pst = conn.prepareStatement(
            "select * from (select mytable.*,rownum as r from  (" + sql +
            ") mytable where rownum<" + String.valueOf(start + pagesize) +
            ") where r>=" + String.valueOf(start));
      }
      else
        pst = conn.prepareStatement(sql);

      ResultSet rs = pst.executeQuery();

      list=operResult(rs);

    }
    catch (Exception e) {
      log.error(e+" ----"+sql);
      HibernateUtil.rollbackTransaction();
    }
    finally {
      
      close();
      HibernateUtil.commitTransaction();
      return list;
    }
  }
  public List Query_new(String sql,int start) {
      List list = new LinkedList();
      try {
        boolean flag = false;

        if (conn == null) {
          flag = true;
        }
        if (conn != null) {
          if (conn.isClosed()) {
            flag = true;
          }
        }
        if (flag) {
        	if(!session.isOpen()){
        		session=HibernateUtil.getSession();
        	}
        	HibernateUtil.beginTransaction();
        	conn=session.connection();
        	
        	//conn = DBControl.getConnection();
        }


        //��ϵͳ�����ȡ
        if(start!=0){
          String ps=SysParam.getParamValue("��ҳ��С");

          //ȥ�еı���
          String str_sql = sql.toUpperCase();
          if (str_sql.indexOf("*") == -1) {
            int firstnum = str_sql.indexOf("SELECT");
            int lastnum = str_sql.indexOf("FROM");
            String st = sql.substring(firstnum + 7, lastnum - 1);
            String end = sql.substring(lastnum);

            String[] ss = StringUtil.split(st, ",");
            StringBuffer sb = new StringBuffer("SELECT ");
            int length = ss.length;

            for (int i = 0; i < length; i++) {
              if (i != length - 1)
                sb.append(ss[i]).append(",");
              else
                sb.append(ss[i]).append(" ");
            }
           // sb = sb.substring(0);
            sb.append(end);

            sql = sb.toString();
          }


          int pagesize = Integer.parseInt(ps);
          pst = conn.prepareStatement(
              "select * from (select mytable.*,rownum as r from  (" + sql +
              ") mytable where rownum<" + String.valueOf(start + pagesize) +
              ") where r>=" + String.valueOf(start));
        }
        else
          pst = conn.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        list=operResult(rs);

      }
      catch (Exception e) {
        log.error(e+" ----"+sql);
        HibernateUtil.rollbackTransaction();
      }
      finally {
    	  
          close();
          HibernateUtil.commitTransaction();
          return list;
      }
    }

  /** ִ����ݿ���²���
   *  ����update��insert
   * @param sql
   */
  public void executeUpdate(String sql) {
    try {
      boolean flag = false;

      if (conn == null) {
        flag = true;
      }
      if (conn != null) {
        if (conn.isClosed()) {
          flag = true;
        }
      }
      if (flag) {
    	if(!session.isOpen()){
      		session=HibernateUtil.getSession();
      	}
    	HibernateUtil.beginTransaction();
      	conn=session.connection();
      	
        //conn = DBControl.getConnection();
      }
      pst = conn.prepareStatement(sql);
      pst.executeUpdate();
    }
    catch (Exception e) {
      log.error(e + "----" + sql);
      HibernateUtil.rollbackTransaction();
    }
    finally {
    	close();
    	HibernateUtil.commitTransaction();
    	
    }
  }

  /** ִ����ݿ���²���
   *  ����update��insert
   * @param sql
   */
  public void executeUpdate(Vector sqls) {
    if (sqls == null) {
      return;
    }
    String sql="";
    try {
      boolean flag = false;

      if (conn == null) {
        flag = true;
      }
      if (conn != null) {
        if (conn.isClosed()) {
          flag = true;
        }
      }
      if (flag) {
        //conn = DBControl.getConnection();
    	  if(!session.isOpen()){
        		session=HibernateUtil.getSession();
        	}
    	  HibernateUtil.beginTransaction();
        conn=session.connection();
        	
      }
      for (int i = 0; i < sqls.size(); i++) {
        sql=sqls.get(i).toString();
        pst = conn.prepareStatement(sql);
        pst.executeUpdate();
        pst.close();
      }
    }
    catch (Exception e) {
      log.error(e + "----" + sql);
      HibernateUtil.rollbackTransaction();
    }
    finally {
    	close();
    	HibernateUtil.commitTransaction();
    }
  }

  /** �ر���ݿ�����ӣ��ر�PrepareStatement
   *
   *
   */
  public void close() {
    try {
      if (pst != null) {
        pst.close();
      }
      if (conn != null) {
        if (!conn.isClosed()) {
          
          conn.close();
        }
      }
    }
    catch (Exception e) {
      log.error(e);
    }
  }

  /** ������
   *
   * @param rs
   * @return
   */
  private List operResult(ResultSet rs) {
    if (rs == null) {
      return null;
    }

    List list = new LinkedList();
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      while (rs.next()) {
        if (columnCount == 1) {
          list.add(rs.getObject(1));
        }
        else {
          Object[] objs = new Object[columnCount];
          for (int i = 0; i < columnCount; i++) {
            if (rsmd.getColumnType(i + 1) == Types.CLOB) {
              objs[i] = rs.getClob(i + 1).getSubString(1, 1000000);
            }
            else {
              objs[i] = rs.getObject(i + 1);
            }
          }
          list.add(objs);
        }
      }
    }
    catch (Exception e) {
      close();
      log.error(e);
    }
    return list;
  }

  //��һ�㷨���id�������
  //tableName :����
  //fieldName :id����ŵ��ֶ���
  //prefieldValue :��ص�ID�ֶε�ֵ ��ȡ�汾����ţ���ص�ID�ֶε�ֵΪnew String[]{"ID=1","XH=1"}
  //               ȡID��ֵ��new String[0]����
  //ʧ�ܷ��ء�0��
  public String calculateID(String tableName,String fieldName,String[] prefieldValue ) {
    String ret="0";
    try{
      String sql = "SELECT MAX(TO_NUMBER(" + fieldName + ")) FROM " + tableName;

      if (prefieldValue.length > 0) {
        sql += " WHERE " + prefieldValue[0];
        for (int i = 1; i < prefieldValue.length; i++)
          sql += " AND " + prefieldValue[i];
      }

      List l_id = Query(sql);
      if (l_id != null) {
        if (l_id.size() > 0) {
          Object obj = l_id.get(0);
          if (obj != null) {
            ret =String.valueOf(Integer.parseInt(obj.toString())+1);
          }else
            ret="1";
        }
      }
    }
    catch(Exception e){
      log.error(e);
    }
    return ret;
   }

   public String getSequenceID(String sequenceName) {
    String ret="0";
    try{
      String sql = "SELECT " + sequenceName +".NEXTVAL FROM DUAL";
      List l_id = Query(sql);
      if (l_id != null) {
        if (l_id.size() > 0) {
          Object obj = l_id.get(0);
          if (obj != null) {
            ret = obj.toString();
          }
        }
      }
    }
    catch(Exception e){
      log.error(e);
    }
    return ret;
   }

}
