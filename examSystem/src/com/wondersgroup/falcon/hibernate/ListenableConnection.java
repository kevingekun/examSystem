/** 
 * @(#)ListenableConnection.java May 15, 2010
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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * @author suhualin
 * @version $Revision$ May 15, 2010
 * @author (lastest modification by $Author$)
 * @since 1.0
 */
public class ListenableConnection implements Connection {

	private final Connection listenedConnection;

	private final String id;

	private final int index;

	private final Date startTime;

	private Date stopTime;

	private final StackTraceElement[] trace;

	private long cost;

	/**
	 * @return the listenConnection
	 */
	public Connection getListenedConnection() {
		return listenedConnection;
	}

	/**
	 * @param stopTime
	 *            the stopTime to set
	 */
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
		this.cost = this.stopTime.getTime() - this.startTime.getTime();
	}

	/**
	 * @return the stopTime
	 */
	public Date getStopTime() {
		return stopTime;
	}

	/**
	 * @return the cost
	 */
	public long getCost() {
		return cost;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the trace
	 */
	public StackTraceElement[] getTrace() {
		return trace;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param listenConnection
	 */
	protected ListenableConnection(Connection listenConnection, String id,
			int index) {
		super();
		this.listenedConnection = listenConnection;
		this.id = id;
		this.index = index;
		this.startTime = new Date();
		this.trace = new Throwable().getStackTrace();
	}

	/**
	 * @see java.sql.Connection#clearWarnings()
	 */
	public void clearWarnings() throws SQLException {
		listenedConnection.clearWarnings();
	}

	/**
	 * @see java.sql.Connection#close()
	 */
	public void close() throws SQLException {
		try {
			listenedConnection.close();
		} finally {
			ListenedStatictis.disconnect(this);
		}
	}

	/**
	 * @see java.sql.Connection#commit()
	 */
	public void commit() throws SQLException {
		listenedConnection.commit();
	}

	/**
	 * @see java.sql.Connection#createStatement()
	 */
	public Statement createStatement() throws SQLException {

		return listenedConnection.createStatement();
	}

	/**
	 * @see java.sql.Connection#createStatement(int, int, int)
	 */
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {

		return listenedConnection.createStatement(resultSetType,
				resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * @see java.sql.Connection#createStatement(int, int)
	 */
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return listenedConnection.createStatement(resultSetType,
				resultSetConcurrency);
	}

	/**
	 * @see java.sql.Connection#getAutoCommit()
	 */
	public boolean getAutoCommit() throws SQLException {
		return listenedConnection.getAutoCommit();
	}

	/**
	 * @see java.sql.Connection#getCatalog()
	 */
	public String getCatalog() throws SQLException {
		return listenedConnection.getCatalog();
	}

	/**
	 * @see java.sql.Connection#getHoldability()
	 */
	public int getHoldability() throws SQLException {
		return listenedConnection.getHoldability();
	}

	/**
	 * @see java.sql.Connection#getMetaData()
	 */
	public DatabaseMetaData getMetaData() throws SQLException {

		return listenedConnection.getMetaData();
	}

	/**
	 * @see java.sql.Connection#getTransactionIsolation()
	 */
	public int getTransactionIsolation() throws SQLException {

		return listenedConnection.getTransactionIsolation();
	}

	/**
	 * @see java.sql.Connection#getTypeMap()
	 */
	public Map getTypeMap() throws SQLException {

		return listenedConnection.getTypeMap();
	}

	/**
	 * @see java.sql.Connection#getWarnings()
	 */
	public SQLWarning getWarnings() throws SQLException {

		return listenedConnection.getWarnings();
	}

	/**
	 * @see java.sql.Connection#isClosed()
	 */
	public boolean isClosed() throws SQLException {
		return listenedConnection.isClosed();
	}

	/**
	 * @see java.sql.Connection#isReadOnly()
	 */
	public boolean isReadOnly() throws SQLException {

		return listenedConnection.isReadOnly();
	}

	/**
	 * @see java.sql.Connection#nativeSQL(java.lang.String)
	 */
	public String nativeSQL(String sql) throws SQLException {

		return listenedConnection.nativeSQL(sql);
	}

	/**
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int, int)
	 */
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {

		return listenedConnection.prepareCall(sql, resultSetType,
				resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int)
	 */
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {

		return listenedConnection.prepareCall(sql, resultSetType,
				resultSetConcurrency);
	}

	/**
	 * @see java.sql.Connection#prepareCall(java.lang.String)
	 */
	public CallableStatement prepareCall(String sql) throws SQLException {

		return listenedConnection.prepareCall(sql);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int,
	 *      int)
	 */
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return listenedConnection.prepareStatement(sql, resultSetType,
				resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int)
	 */
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return listenedConnection.prepareStatement(sql, resultSetType,
				resultSetConcurrency);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int)
	 */
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		return listenedConnection.prepareStatement(sql, autoGeneratedKeys);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int[])
	 */
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		return listenedConnection.prepareStatement(sql, columnIndexes);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String,
	 *      java.lang.String[])
	 */
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		return listenedConnection.prepareStatement(sql, columnNames);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String)
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return listenedConnection.prepareStatement(sql);
	}

	/**
	 * @see java.sql.Connection#releaseSavepoint(java.sql.Savepoint)
	 */
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		listenedConnection.releaseSavepoint(savepoint);

	}

	/**
	 * @see java.sql.Connection#rollback()
	 */
	public void rollback() throws SQLException {
		listenedConnection.rollback();
	}

	/**
	 * @see java.sql.Connection#rollback(java.sql.Savepoint)
	 */
	public void rollback(Savepoint savepoint) throws SQLException {
		listenedConnection.rollback(savepoint);
	}

	/**
	 * @see java.sql.Connection#setAutoCommit(boolean)
	 */
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		listenedConnection.setAutoCommit(autoCommit);
	}

	/**
	 * @see java.sql.Connection#setCatalog(java.lang.String)
	 */
	public void setCatalog(String catalog) throws SQLException {
		listenedConnection.setCatalog(catalog);
	}

	/**
	 * @see java.sql.Connection#setHoldability(int)
	 */
	public void setHoldability(int holdability) throws SQLException {
		listenedConnection.setHoldability(holdability);
	}

	/**
	 * @see java.sql.Connection#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean readOnly) throws SQLException {
		listenedConnection.setReadOnly(readOnly);
	}

	/**
	 * @see java.sql.Connection#setSavepoint()
	 */
	public Savepoint setSavepoint() throws SQLException {
		return listenedConnection.setSavepoint();
	}

	/**
	 * @see java.sql.Connection#setSavepoint(java.lang.String)
	 */
	public Savepoint setSavepoint(String name) throws SQLException {
		return listenedConnection.setSavepoint(name);
	}

	/**
	 * @see java.sql.Connection#setTransactionIsolation(int)
	 */
	public void setTransactionIsolation(int level) throws SQLException {
		listenedConnection.setTransactionIsolation(level);
	}

	/**
	 * @see java.sql.Connection#setTypeMap(java.util.Map)
	 */
	

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id.hashCode() + index;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || !(obj instanceof ListenableConnection)) {
			return false;
		}
		ListenableConnection other = (ListenableConnection) obj;
		return other.id.equals(this.id) && other.index == this.index;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return toString(false, false);
	}

	public String toString(boolean time, boolean stack) {
		int size = id.length() + String.valueOf(index).length() + 3;
		if (time) {
			size += 20;
		}
		if (stack) {
			size += this.trace.length * 256;
		}
		Writer writer = new StringWriter(size);
		PrintWriter printWriter = new PrintWriter(writer);
		printWriter.print(id);
		printWriter.print("[");
		printWriter.print(index);
		printWriter.print("]");
		if (cost > 0) {
			printWriter.print("[");
			printWriter.print(cost);
			printWriter.print("ms]");
		}
		if (time) {
			try {
				printWriter.print("[");
				printWriter.print(format.format(this.startTime));
				if (this.stopTime != null) {
					printWriter.print("~");
					printWriter.print(format.format(this.stopTime));
				}
				printWriter.print("]");
			} catch (Exception e) {

			}
		}

		if (stack) {
			for (int i = 0; i < this.trace.length; i++) {
				printWriter.println();
				printWriter.print("\tat ");
				printWriter.print(trace[i]);
			}
		}
		printWriter.flush();
		return writer.toString();
	}

	private static DateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Blob createBlob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Clob createClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public NClob createNClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public SQLXML createSQLXML() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Properties getClientInfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getClientInfo(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isValid(int timeout) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		// TODO Auto-generated method stub
		
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
