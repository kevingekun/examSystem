package com.wondersgroup.falcon.oa.database;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;

import org.apache.log4j.*;

/**
 * <p>Title: 数据库访问 </p>
 * <p>Description: 连接数据库、释放连接</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: wonders</p>
 * @author peter
 * @version 1.0
 */

public class DBControl {
  private static Logger log = Logger.getLogger(DBControl.class.getName());
  public DBControl() {
  }

  ///连接数据库
  //本地wa
  public static Connection getConnection() {
    Connection conn = null;
    String url = //"jdbc:oracle:thin:@127.0.0.1:1521:orcl";
        //"jdbc:oracle:thin:@127.0.0.1:1521:orcl";   //705是
     "jdbc:oracle:thin:@172.16.27.185:1521:orcl";
    //"jdbc:oracle:thin:@localhost:1521:xzzx";
                 //"jdbc:oracle:thin:@168.10.200.1:1521:ybkrnl";
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      conn = DriverManager.getConnection(url, "ccuser", "ccpwd");
      //conn = DriverManager.getConnection(url, "ccuser", "ccpwd");
      //conn = DriverManager.getConnection(url, "yboa", "yboa");
    }
    catch (Exception e) {
      log.error(e);
    }
    return conn;
  }
  ///连接数据库
  //本地wa
  public static Connection getConnection_wonders() {
    Connection conn = null;
    String url = "jdbc:oracle:thin:@logistics:1521:oa";
                 //"jdbc:oracle:thin:@168.10.200.1:1521:ybkrnl";
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      conn = DriverManager.getConnection(url, "oa", "oa");
      //conn = DriverManager.getConnection(url, "yboa", "yboa");
    }
    catch (Exception e) {
      log.error(e);
    }
    return conn;
  }

  //服务器
  public static Connection getConnection_websphere() {
    Connection conn = null;
    Context ctx = null;
    try {

      Properties props = new Properties();
      props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                        "com.ibm.websphere.naming.WsnInitialContextFactory");

      ctx = new InitialContext(props);

      DataSource ds = (DataSource) ctx.lookup("jdbc/dfoa");

      conn = ds.getConnection();

    }
    catch (Exception e) {
      log.error(e);
    }
    return conn;
  }

  //服务器weblogic
 public static Connection getConnection_weblogic() {
   Connection conn = null;
   //Context ctx = null;
   try {
      Properties p = new Properties();
      p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
      p.put(Context.PROVIDER_URL, "t3://172.16.30.90:7001");
      InitialContext ic = new InitialContext(p);
      DataSource ds = (DataSource) ic.lookup("hzsboaJNDI");
      conn = ds.getConnection();
    }
    catch (Exception e) {
      log.error(e);
      return null;
    }
    return conn;
    }

  /* try {

     Properties props = new Properties();
     props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                       "weblogic.jndi.WLInitialContextFactory");

     ctx = new InitialContext(props);

     DataSource ds = (DataSource) ctx.lookup("jdbc/dfoa");

     conn = ds.getConnection();

   }
   catch (Exception e) {
     log.error(e);
   }*/
  // return conn;



  ///释放数据库连接
public static void  releaseConnection(Connection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
    }
    catch (Exception e) {
      log.error(e);
    }
  }

}
