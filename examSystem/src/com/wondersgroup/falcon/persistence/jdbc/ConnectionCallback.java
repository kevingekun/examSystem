package com.wondersgroup.falcon.persistence.jdbc;

import java.sql.Connection;

/**
 * @author suhualin
 * @version $Revision$ May 16, 2010
 * @author (lastest modification by $Author$)
 * @since 1.0
 */
public interface ConnectionCallback {
	/**
	 * JDBC 连接回调
	 * 
	 * @param connection
	 */
	Object doInConnection(Connection connection) throws Throwable;
}
