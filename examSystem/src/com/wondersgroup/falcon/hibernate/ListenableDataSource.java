package com.wondersgroup.falcon.hibernate;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("static-access")
public class ListenableDataSource implements DataSource {
	private static Log logger = LogFactory.getLog(ListenableDataSource.class);

	private DataSource rawDataSource;

	private ListenedStatictis listenedStatictis;

	public void setListenedStatictis(ListenedStatictis listenedStatictis) {
		this.listenedStatictis = listenedStatictis;
	}

	public void setRawDataSource(DataSource rawDataSource) {
		this.rawDataSource = rawDataSource;
	}

	public void init() {
		logger.info("Listening on DataSource " + rawDataSource);
		//listenedStatictis.listen();
	}

	public Connection getConnection() throws SQLException {
		return listenedStatictis.connect(rawDataSource.getConnection());
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		return listenedStatictis.connect(rawDataSource.getConnection(username,
				password));
	}

	public PrintWriter getLogWriter() throws SQLException {
		return rawDataSource.getLogWriter();
	}

	public int getLoginTimeout() throws SQLException {
		return rawDataSource.getLoginTimeout();
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		rawDataSource.setLogWriter(out);
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		rawDataSource.setLoginTimeout(seconds);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
