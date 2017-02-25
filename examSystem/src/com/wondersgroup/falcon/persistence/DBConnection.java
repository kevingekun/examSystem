package com.wondersgroup.falcon.persistence;

import java.util.*;
import java.sql.*;

import javax.naming.*;

/**
*数据库连接
*/


public class DBConnection
{

		private final String br = "\r\n";
		private Connection dbCon_sel = null;
		String sHint = "";

		/*数据库连接*/

		Connection dbCon = null;
		Connection preCon = null; // for PreparedStatement only
		Statement stmt;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		ResultSet preRs = null; // for PreparedStatement's resultset only
		CallableStatement precall;
		
		javax.naming.InitialContext context = null;
		javax.sql.DataSource ds = null;


		public DBConnection() {	
		}

/**
		 * 获得数据库连接
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 * @throws Exception
		 * @return Connection
		 */
		public Connection getDBConnection() throws ClassNotFoundException,
				SQLException, Exception {	

					

				//通过连接池连接数据库
		/*Context initCtx = new InitialContext();
		Context ctx = (Context)initCtx.lookup("java:comp/env");
		//获取连接池对象
		Object obj = (Object)ctx.lookup("jdbc/SHYBDBPool");

		//类型转换
		javax.sql.DataSource ds = (javax.sql.DataSource)obj;
		conn = ds.getConnection();
*/

			        Connection conn=null;
					String DRIVER = "oracle.jdbc.driver.OracleDriver"; 
					String URL = "jdbc:oracle:thin:@172.16.31.22:1521:orcl";
					                                
					String USERNAME = "falcon";
					String PASSWORD = "falcon";
						try {
								 Class.forName(DRIVER);
								 conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);  
								    
								} catch (java.sql.SQLException e) {
									
								    e.printStackTrace();
								} catch (ClassNotFoundException e) 
								{
								       e.printStackTrace();
								}
							
/*
				//jdbc-odbc连接SQLServer2000
				String user = "shyb";
				String password = "shyb";
				conn = null;

				try {
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						conn = DriverManager.getConnection("jdbc:odbc:shyb", user,password);
				} catch (java.lang.ClassNotFoundException e) {
						System.out.println("forname()" + e.getMessage());
				} catch (SQLException ex) {
						System.out.println(ex.getMessage());
				}
*/
/*
				//jdbc直连SQLServer2000
			  String user="shyb";
			  String password="shyb";
			  //Connection conn=null;
			  try{
				  Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
				  conn= DriverManager.getConnection("jdbc:microsoft:sqlserver://168.30.1.43:1433;DatabaseName=shyb", user, password);
			  }catch(java.lang.ClassNotFoundException e){
				  System.out.println("forname()"+e.getMessage());
			  }catch (SQLException ex){
				  System.out.println(ex.getMessage());
			  }
*/
				return conn;
		}
		
		
		/**
		 * 关闭数据库
		 * @return boolean
		 */
		public boolean close() {

				try {
						if (rs != null) {
								rs.close();
						}
						if (preRs != null) {
								preRs.close();
						}
						if (stmt != null) {
								stmt.close();
						}
						if (preStmt != null) {
								preStmt.close();
						}
						if (dbCon != null) {
								dbCon.close();
						}
						if (preCon != null) {
								preCon.close();
						}
						if (precall != null) {
								precall.close();

						}
						return true;
				} catch (Exception e) {
						sHint = e.getMessage();
						System.out.println(sHint);
						return false;
				}
		}

		public int runInsert(String sql_String, String table_Name) {
				Connection dbCon = null;
				String execString = "";
				//Random ids = new Random();
				/* 检查处理String的方法 */
				//sql_String = String.valueOf(ids) + sql_String;
				int resultValue = 0;
				execString = "insert into " + table_Name + " values (" + sql_String +
						")";
				try {
						dbCon = getDBConnection();
						Statement Stat = dbCon.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						resultValue = Stat.executeUpdate(execString);
				} catch (SQLException ex) {
						sHint = "executeUpdate:" + ex.getMessage();
						System.out.println(sHint);
				} catch (Exception e) {
						sHint = "executeUpdate:" + e.getMessage();
						System.out.println(sHint);
				} finally {
						try {
								if (dbCon != null) {
										dbCon.close();
								}
						} catch (SQLException e) {
								//show Wrong Message
						}
				}
				return resultValue;
		}


		/**
		 * execute a SQL statement with recordset returned
		 * @param sql String
		 * @return ResultSet
		 */
		public ResultSet execQuery(String sql) {
				try {
						dbCon = getDBConnection();
						stmt = dbCon.createStatement();
						rs = stmt.executeQuery(sql);
						return rs;
				} catch (SQLException ex) {
						sHint = "executeQuery:" + ex.getMessage();
						
				} catch (Exception e) {
						sHint = e.getMessage();
						System.out.println(sHint);
						
				}

				return null;
		}
		

		public int execUpdate(String sql) {
				int ret = -1;
				try {
						dbCon = getDBConnection();
						stmt = dbCon.createStatement();
						ret = stmt.executeUpdate(sql);
				} catch (SQLException ex) {
						sHint = "executeUpdate:" + ex.getMessage();
				} catch (Exception e) {
						sHint = e.getMessage();
						System.out.println(sHint);
				}

				return ret;
		}

		public boolean preStmt(String sql) {
				try {
						preCon = getDBConnection();
						preStmt = preCon.prepareStatement(sql);
						return true;
				} catch (SQLException ex) {
						sHint = "PrepraedStatement:" + ex.getMessage();
						System.out.println(sHint);
						return false;
				} catch (Exception e) {
						sHint = e.getMessage();
						System.out.println(sHint);
						return false;
				}
		}

		public boolean setBytes(int index, byte[] indexValue) {
				try {
						if (preStmt != null) {
								preStmt.setBytes(index, indexValue);
						}
						if (precall != null) {
								precall.setBytes(index, indexValue);
						}
						return true;

				} catch (SQLException ex) {
						sHint = "preStmt.setBytes:" + ex.getMessage();
						System.out.println(sHint);
						return false;
				}
		}

		public boolean setInt(int index, int indexValue) {
				try {
						if (preStmt != null) {
								preStmt.setInt(index, indexValue);
						}
						if (precall != null) {
								precall.setInt(index, indexValue);
						}
						return true;
				} catch (SQLException ex) {
						sHint = "preStmt.setBytes:" + ex.getMessage();
						System.out.println(sHint);
						return false;
				}
		}

		public boolean setString(int index, String StringValue) {
				try {
						if (preStmt != null) {
								preStmt.setString(index, StringValue);
						}
						if (precall != null) {
								precall.setString(index, StringValue);
						}
						return true;
				} catch (SQLException ex) {
						sHint = "preStmt.setString:" + ex.getMessage();
						System.out.println(sHint);
						return false;
				}
		}

		public int execUpdate() {
				int ret = -1;
				try {
						if (preStmt != null) {
								ret = preStmt.executeUpdate();
						}
						if (precall != null) {
								ret = precall.executeUpdate();
						}
						return ret;
				} catch (SQLException ex) {
						sHint = "PrepraedStatement executeUpdate:" + ex.getMessage();
						System.out.println(sHint);
						return ret;
				}
		}
		
		

		public ResultSet execQuery() {
				try {
						if (preStmt != null) {
								preRs = preStmt.executeQuery();
						}
						if (precall != null) {
								preRs = precall.executeQuery();
						}
				} catch (SQLException ex) {
						sHint = "PrepraedStatement executeQuery:" + ex.getMessage();
						System.out.println(sHint);
				}

				return preRs;
		}


		public void runClose() {

				try {
						if (dbCon_sel != null) {
								dbCon_sel.close();
						}
				} catch (SQLException e) {
						//show Wrong Message
						System.out.println(e.getMessage());
				}
		}

		/**
		 * result set package
		 * @param sql String
		 * @return ArrayList
		 */
		public ArrayList execSelectSql(String sql) {
				StringBuffer sb = new StringBuffer();
				Connection dbCon = null;
				HashMap h = new HashMap();
				ArrayList al = new ArrayList();
				try {
						dbCon = getDBConnection();
						Statement st = dbCon.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
						ResultSet rs = st.executeQuery(sql);
						ResultSetMetaData mt = rs.getMetaData();
						int rowcount = mt.getColumnCount();
						while (rs.next()) {
								h = new HashMap();
								for (int k = 1; k <= rowcount; k++) {
										h.put(mt.getColumnName(k), rs.getString(k));
								}
								al.add(h);
						}
						rs.close();
						st.close();
				} catch (SQLException e) {
						e.printStackTrace();
				} catch (Exception e) {
						e.printStackTrace();
				} finally {
						try {
								dbCon.close();
						} catch (Exception e) {
						}
				}
				return al;
		}

		/**
		 * exec procedure due to the java
		 * @param sql String
		 * @return boolean
		 */
		public boolean preCall(String sql) {
				try {
						preCon = getDBConnection();
						precall = preCon.prepareCall(sql);
						return true;
				} catch (SQLException ex) {
						sHint = "PrepraedStatement:" + ex.getMessage();
						System.out.println(sHint);
						return false;
				} catch (Exception e) {
						sHint = e.getMessage();
						System.out.println(sHint);
						return false;
				}
		}
		
	

		public boolean registerOutParameter(int index, String a) {
				if (a == "String") {
						try {
								precall.registerOutParameter(index, java.sql.Types.VARCHAR);
								return true;
						} catch (SQLException ex) {
								sHint = "precall.registerOutParameter:" + ex.getMessage();
								System.out.println(sHint);
								return false;
						}
				}
				if (a == "int") {
						try {
								precall.registerOutParameter(index, java.sql.Types.INTEGER);
								return true;
						} catch (SQLException ex) {
								sHint = "precall.registerOutParameter:" + ex.getMessage();
								System.out.println(sHint);
								return false;
						}
				}
				if (a == "float") {
						try {
								precall.registerOutParameter(index, java.sql.Types.FLOAT);
								return true;
						} catch (SQLException ex) {
								sHint = "precall.registerOutParameter:" + ex.getMessage();
								System.out.println(sHint);
								return false;
						}
				} else {
						return false;
				}
		}

		public int getInt(int index) {
				try {
						return precall.getInt(index);

				} catch (SQLException ex) {
						sHint = "preStmt.getInt:" + ex.getMessage();
						System.out.println(sHint);
						return 0;
				}
		}

		public String getString(int index) {
				try {
						return precall.getString(index);

				} catch (SQLException ex) {
						sHint = "preStmt.getString:" + ex.getMessage();
						System.out.println(sHint);
						return "";
				}
		}

		public float getFloat(int index) {
				try {
						return precall.getFloat(index);

				} catch (SQLException ex) {
						sHint = "preStmt.getFloat:" + ex.getMessage();
						System.out.println(sHint);
						return 0;
				}
		}

		public String getStr(String str) {
				if(str.equals("")) {
						return "";
				} else {
						try {
								String temp_p = str;
								byte[] temp_t = temp_p.getBytes("ISO8859-1");
								String temp = new String(temp_t);
								return temp;
						} catch(Exception e) {

						}
						return "null";
				}
		}

}
