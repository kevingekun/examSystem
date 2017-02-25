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
 * <p>Title:[��Ϣ����ͳһƽ̨���ݴ�����] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2010-1-12]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */

public class JHKOperation {

	private static Log log = LogFactory.getLog(JHKOperation.class);

	private static final long serialVersionUID = 1000000000000001L;

	/**
	 * ��ȡ���ݿ�����
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
	 * <p>Description:[���Ҷ��󼯺�] </p>
	 * 
	 * Created by [Kevin Liang] [2010-1-12]
	 * Midified by [�޸���] [�޸�ʱ��]
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
	 * <p>Description:[���Ҷ���] </p>
	 * 
	 * Created by [Kevin Liang] [2010-1-12]
	 * Midified by [�޸���] [�޸�ʱ��]
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
		if(beans!=null && !beans.isEmpty()){ //ע������
			obj=beans.get(0);
		}
		return obj;
	}


	/**
	 * 
	 * <p>Description:[ִ�и��µ�sql���,����,�޸�,ɾ��] </p>
	 * 
	 * Created by [Kevin Liang] [2010-1-12]
	 * Midified by [�޸���] [�޸�ʱ��]
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
	 * <p>Description:[�޸�CLOB�ֶ�����] </p>
	 * 
	 * Created by [Kevin Liang] [2010-1-12]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param id
	 * @param strContent
	 */
	public void updateCLOB(String id , String strContent)throws SQLException, IOException{
		Connection conn = null;
		try {
			//ȡ�����ݿ�����         
			conn = this.getConnection();
			//�ֶ��ύ 
			conn.setAutoCommit(false);

			//����ResultSet �� Clob ���� 
			ResultSet rs = null; 
			oracle.sql.CLOB clob = null; 
			//����SQL 
			String sqlclob = "select content from app_hlpdoc Where ID=? FOR Update "; 
			java.sql.PreparedStatement pstmt = conn.prepareStatement(sqlclob); 
			//hid��varchar2���͵ģ�������setString 
			pstmt.setString(1,id); 
			//ִ��update��� 
			rs= pstmt.executeQuery(); 
			if(rs.next()) 
			{ 
				//ȡ�øղŵ�HCONTENT�����ݣ�Ҳ���Ǹղ���ӵ�empty_clob() 
				clob = (oracle.sql.CLOB)rs.getClob(1); 
			} 
			//��Ҫ��clob.getCharacterOutputStream()����ʽ��� 
			Writer write = clob.getCharacterOutputStream(); 
			//д��������ݣ�helpform.getHContent() ����ǰ��������� 

			write.write(strContent);

			write.flush(); 
			write.close(); 
			pstmt.close();
			rs.close(); 
			//�ύ 
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
