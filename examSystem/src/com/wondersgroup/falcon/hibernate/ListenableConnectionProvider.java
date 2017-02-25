/** 
 * @(#)ListenableConnectionProvider.java May 15, 2010
 * 
 * Copyright (c) 1995-2010 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wondersgroup.falcon.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.connection.ConnectionProviderFactory;

/**
 * @author suhualin
 * @version $Revision$ May 15, 2010
 * @author (lastest modification by $Author$)
 * @since 1.0
 */
public class ListenableConnectionProvider implements ConnectionProvider {
	private ConnectionProvider rawConnectionProvider;

	/**
	 * @see org.hibernate.connection.ConnectionProvider#close()
	 */
	public void close() throws HibernateException {
		try {
			rawConnectionProvider.close();
		} finally {
		}
	}

	/**
	 * @see org.hibernate.connection.ConnectionProvider#closeConnection(java.sql.Connection)
	 */
	public void closeConnection(Connection conn) throws SQLException {
		if (conn instanceof ListenableConnection) {
			ListenableConnection listenableConnection = (ListenableConnection) conn;
			ListenedStatictis.disconnect(listenableConnection);
			conn = listenableConnection.getListenedConnection();
		}
		rawConnectionProvider.closeConnection(conn);
	}

	/**
	 * @see org.hibernate.connection.ConnectionProvider#configure(java.util.Properties)
	 */
	public void configure(Properties props) throws HibernateException {
		Properties newProps = new Properties();
		newProps.putAll(props);
		newProps.remove(Environment.CONNECTION_PROVIDER);
		rawConnectionProvider = ConnectionProviderFactory
				.newConnectionProvider(newProps);
		String period = props.getProperty(Environment.CONNECTION_PREFIX
				+ ".monitor.period", null);
		String timeout = props.getProperty(Environment.CONNECTION_PREFIX
				+ ".monitor.timeout", null);
		String time = props.getProperty(Environment.CONNECTION_PREFIX
				+ ".monitor.time", null);
		String trace = props.getProperty(Environment.CONNECTION_PREFIX
				+ ".monitor.trace", null);
		ListenedStatictis listenedStatictis = new ListenedStatictis();
		if (StringUtils.isNotEmpty(period)) {
			listenedStatictis.setListenPeriod(Long.parseLong(period));
		}
		if (StringUtils.isNotEmpty(timeout)) {
			listenedStatictis.setTimeout(Long.parseLong(timeout));
		}
		if (StringUtils.isNotEmpty(time)) {
			listenedStatictis.setShowTime(Boolean.valueOf(time));
		}
		if (StringUtils.isNotEmpty(trace)) {
			listenedStatictis.setShowTrace(Boolean.valueOf(trace));
		}
		ListenedStatictis.listen();
	}

	/**
	 * @see org.hibernate.connection.ConnectionProvider#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		return ListenedStatictis.connect(rawConnectionProvider.getConnection());
	}

	/**
	 * @see org.hibernate.connection.ConnectionProvider#supportsAggressiveRelease()
	 */
	public boolean supportsAggressiveRelease() {
		return rawConnectionProvider.supportsAggressiveRelease();
	}
}
