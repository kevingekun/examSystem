package com.wondersgroup.falcon.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

/**
 * @author suhualin
 * @version $Revision$ May 16, 2010
 * @author (lastest modification by $Author$)
 * @since 1.0
 */
public class JdbcUtil {
	private static final String HIBERNATE_DSNAME = "hibernate";
	private static String defaultDsName;

	private static Map<String, DataSource> dataSources = new HashMap<String, DataSource>();

	private static ThreadLocal<String> threadDsName = new ThreadLocal<String>();

	public void setDataSources(Map<String, DataSource> dataSources) {
		JdbcUtil.dataSources = dataSources;
	}

	public void setDefaultDsName(String defaultDsName) {
		JdbcUtil.defaultDsName = defaultDsName;
	}

	public static String getCurrentDsName() {
		String result = (String) threadDsName.get();
		if (result == null) {
			result = defaultDsName;
		}
		return result;
	}

	protected static boolean isHibernate(String dsName) {
		return HIBERNATE_DSNAME.equals(dsName);
	}

	public static void selectDataSource(String dsName) {
		if (dsName == null) {
			dsName = defaultDsName;
		}
		if (HIBERNATE_DSNAME.equals(dsName) || dataSources.containsKey(dsName)) {
			threadDsName.set(dsName);
		} else {
			throw new IllegalArgumentException("找不到指定数据源:" + dsName);
		}
	}

	/**
	 * 执行Connection回调
	 * 
	 * @param callback
	 * @return
	 */
	public static Object doInConnection(final ConnectionCallback callback,boolean commit) {
		String dsName = getCurrentDsName();
		if (isHibernate(dsName)) {
			return doInHibernateConnection(callback);
		}

		DataSource ds = dataSources.get(dsName);
		Connection connection = null;
		Object result = null;
		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			try {
				result = callback.doInConnection(connection);
				if(commit){
					connection.commit();
				}
			} catch (Throwable e) {
				connection.rollback();
				throw e;
			}
			return result;
		} catch (RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new InfrastructureException(e);
		} finally {
			close(connection);
			threadDsName.set(null);
		}
	}
	
	public static Object doInConnection(final ConnectionCallback callback){
		return doInConnection(callback,true);
	}
	
	

	/**
	 * 执行Connection回调
	 * 
	 * @param callback
	 * @return
	 */
	public static Object doInHibernateConnection(
			final ConnectionCallback callback) {
		return HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return callback.doInConnection(session.connection());
			}
		});
	}

	/**
	 * 执行Statement回调
	 * 
	 * @param <T>
	 * @param statement
	 * @param callback
	 * @return
	 * @throws Throwable
	 */
	public static <T extends Statement> Object doInStatement(T statement,
			StatementCallback<T> callback) throws Throwable {
		try {
			return callback.execute(statement);
		} finally {
			close(statement);
		}
	}

	/**
	 * 执行ResultSet回调
	 * 
	 * @param rs
	 * @param callback
	 * @return
	 * @throws Throwable
	 */
	public static Object doInResultSet(ResultSet rs, ResultSetCallback callback)
			throws Throwable {
		try {
			return callback.execute(rs);
		} finally {
			close(rs);
		}
	}

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws Throwable
	 */
	public static int executeUpdate(Connection connection, final String sql)
			throws Throwable {
		Statement statement = connection.createStatement();
		return (Integer) doInStatement(statement,
				new StatementCallback<Statement>() {
					public Object execute(Statement statement) throws Throwable {
						return statement.executeUpdate(sql);
					}
				});
	}

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws Throwable
	 */
	public static int executeUpdate(Connection connection, final String sql,
			final Object... params) throws Throwable {
		PreparedStatement statement = connection.prepareStatement(sql);
		return (Integer) doInStatement(statement,
				new StatementCallback<PreparedStatement>() {
					public Object execute(PreparedStatement statement)
							throws Throwable {
						for (int i = 0; i < params.length; i++) {
							statement.setObject(i + 1, params[i]);
						}
						return statement.executeUpdate(sql);
					}
				});
	}

	/**
	 * 执行查询
	 * 
	 * @param connection
	 * @param sql
	 * @param callback
	 * @return
	 * @throws Throwable
	 */
	public static Object executeQuery(Connection connection, final String sql,
			final ResultSetCallback callback) throws Throwable {
		Statement statement = connection.createStatement();
		return doInStatement(statement, new StatementCallback<Statement>() {
			public Object execute(Statement statement) throws Throwable {
				ResultSet rs = statement.executeQuery(sql);
				return doInResultSet(rs, callback);
			}
		});
	}

	/**
	 * 执行查询
	 * 
	 * @param connection
	 * @param sql
	 * @param callback
	 * @return
	 * @throws Throwable
	 */
	public static Object executeQuery(Connection connection, final String sql,
			final ResultSetCallback callback, final Object... params)
			throws Throwable {
		PreparedStatement statement = connection.prepareStatement(sql);
		return doInStatement(statement,
				new StatementCallback<PreparedStatement>() {
					public Object execute(PreparedStatement statement)
							throws Throwable {
						for (int i = 0; i < params.length; i++) {
							statement.setObject(i + 1, params[i]);
						}
						statement.execute();
						ResultSet rs = statement.getResultSet();
						return doInResultSet(rs, callback);
					}
				});
	}

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws Throwable
	 */
	public static int executeUpdate(final String sql) throws Throwable {
		return (Integer) doInConnection(new ConnectionCallback() {
			public Object doInConnection(Connection connection)
					throws Throwable {
				return executeUpdate(connection, sql);
			}
		});
	}

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws Throwable
	 */
	public static int executeUpdate(final String sql, final Object... params)
			throws Throwable {
		return (Integer) doInConnection(new ConnectionCallback() {
			public Object doInConnection(Connection connection)
					throws Throwable {
				return executeUpdate(connection, sql, params);
			}
		});
	}

	/**
	 * 执行查询
	 * 
	 * @param connection
	 * @param sql
	 * @param callback
	 * @return
	 * @throws Throwable
	 */
	public static Object executeQuery(final String sql,
			final ResultSetCallback callback) throws Throwable {
		return doInConnection(new ConnectionCallback() {
			public Object doInConnection(Connection connection)
					throws Throwable {
				return executeQuery(connection, sql, callback);
			}
		});
	}

	/**
	 * 执行查询
	 * 
	 * @param connection
	 * @param sql
	 * @param callback
	 * @return
	 * @throws Throwable
	 */
	public static Object executeQuery(final String sql,
			final ResultSetCallback callback, final Object... params)
			throws Throwable {
		return doInConnection(new ConnectionCallback() {
			public Object doInConnection(Connection connection)
					throws Throwable {
				return executeQuery(connection, sql, callback, params);
			}
		});
	}

	public static void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e) {
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
		}
	}

	public static void close(Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
		}
	}
}
