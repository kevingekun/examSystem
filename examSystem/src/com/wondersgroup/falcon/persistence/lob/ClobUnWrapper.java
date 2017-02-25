package com.wondersgroup.falcon.persistence.lob;

import java.sql.Clob;

public interface ClobUnWrapper {
	String wrappedType();

	Clob unwrap(Clob wrapped);
}
