package com.wondersgroup.falcon.hibernate;

import java.sql.Connection;

public interface ConnectionUnWrapper {
	public boolean match(Connection connection);

	public Connection unwrap(Connection connection);
}
