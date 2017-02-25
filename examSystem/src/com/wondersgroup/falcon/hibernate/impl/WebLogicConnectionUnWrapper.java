package com.wondersgroup.falcon.hibernate.impl;

import java.lang.reflect.Method;
import java.sql.Connection;

import com.wondersgroup.falcon.hibernate.ConnectionUnWrapper;

public class WebLogicConnectionUnWrapper implements ConnectionUnWrapper {
	private static final String WEBLOGIC_PREFIX = "weblogic.jdbc.wrapper";

	public boolean match(Connection connection) {
		return connection.getClass().getName().startsWith(WEBLOGIC_PREFIX);
	}

	public Connection unwrap(Connection connection) {
		try {
			Method method = connection.getClass().getMethod("getVendorObj",
					new Class[] {});
			return (Connection) method.invoke(connection);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
