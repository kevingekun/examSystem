package com.wondersgroup.falcon.persistence;

import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.Util.PropertiesUtil;
import com.wondersgroup.falcon.dao.archives.PolicyTransferDAO;
import com.wondersgroup.falcon.oa.util.StringUtil;

/**
 * 
 * <p>Title:[信息中心统一平台数据处理类] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2010-1-12]
 * Midified by [修改人] [修改时间]
 *
 */

public class JHKOperation {

	private static Log log = LogFactory.getLog(JHKOperation.class);

	private static final long serialVersionUID = 1000000000000001L;

	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;

		PropertiesUtil pu = new PropertiesUtil();
		String strDriver = pu.getProperties("jdbc.driverClassName.JHK");
		String strUrl = pu.getProperties("jdbc.url.JHK");	
		String strUser = pu.getProperties("jdbc.userName.JHK");	
		String strPass = pu.getProperties("jdbc.password.JHK");
		try {
			DbUtils.loadDriver(strDriver);    
			conn = DriverManager.getConnection(strUrl, strUser, strPass);
		} catch (SQLException e) {
			// handle the exception
			e.printStackTrace();
		} finally {
			//DbUtils.closeQuietly(conn);
		}
		return conn;
	}

	/**
	 * 
	 * <p>Description:[查找对象集合] </p>
	 * 
	 * Created by [Kevin Liang] [2010-1-12]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param sqlString
	 * @param clazz
	 * @return
	 */
	public List query(String sqlString, Class clazz) {
		List beans = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			beans =
				(List) qRunner.query(
						conn,
						sqlString,
						new BeanListHandler(clazz));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return beans;
	}

	/**
	 * 
	 * <p>Description:[查找对象] </p>
	 * 
	 * Created by [Kevin Liang] [2010-1-12]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param sqlString
	 * @param clazz
	 * @return
	 */
	public Object get(String sqlString, Class clazz) {
		List beans = null;
		Object obj = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			beans =
				(List) qRunner.query(
						conn,
						sqlString,
						new BeanListHandler(clazz));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		if(beans!=null && !beans.isEmpty()){ //注意这里
			obj=beans.get(0);
		}
		return obj;
	}


	/**
	 * 
	 * <p>Description:[执行更新的sql语句,插入,修改,删除] </p>
	 * 
	 * Created by [Kevin Liang] [2010-1-12]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param sqlString
	 * @return
	 */
	public boolean update(String sqlString) throws Exception{
		Connection conn = null;
		boolean flag = false;
		try {
			conn = getConnection();
			QueryRunner qRunner = new QueryRunner();
			int i = qRunner.update(conn,sqlString);
			if (i > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return flag;
	}

	/**
	 * 
	 * <p>Description:[修改CLOB字段内容] </p>
	 * 
	 * Created by [Kevin Liang] [2010-1-12]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param id
	 * @param strContent
	 */
	public void updateCLOB(String id , String strContent)throws SQLException, IOException{
		Connection conn = null;
		try {
			//取得数据库连接         
			conn = this.getConnection();
			//手动提交 
			conn.setAutoCommit(false);

			//定义ResultSet 和 Clob 变量 
			ResultSet rs = null; 
			oracle.sql.CLOB clob = null; 
			//更新SQL 
			String sqlclob = "select content from app_hlpdoc Where ID=? FOR Update "; 
			java.sql.PreparedStatement pstmt = conn.prepareStatement(sqlclob); 
			//hid是varchar2类型的，所以用setString 
			pstmt.setString(1,id); 
			//执行update语句 
			rs= pstmt.executeQuery(); 
			if(rs.next()) 
			{ 
				//取得刚才的HCONTENT的内容，也就是刚才添加的empty_clob() 
				clob = (oracle.sql.CLOB)rs.getClob(1); 
			} 
			//需要用clob.getCharacterOutputStream()流方式输出 
			Writer write = clob.getCharacterOutputStream(); 
			//写入具体内容，helpform.getHContent() 存的是帮助的内容 

			write.write(strContent);

			write.flush(); 
			write.close(); 
			pstmt.close();
			rs.close(); 
			//提交 
			conn.commit(); 
			conn.close(); 

		} catch (SQLException e) {

			e.printStackTrace();
			throw e ;

		}  catch (IOException e) {

			e.printStackTrace();
			throw e ;
		} finally {
			DbUtils.closeQuietly(conn);
		} 

	}

}
