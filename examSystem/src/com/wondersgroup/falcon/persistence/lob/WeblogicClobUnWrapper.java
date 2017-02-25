package com.wondersgroup.falcon.persistence.lob;

import java.lang.reflect.Method;
import java.sql.Clob;

public class WeblogicClobUnWrapper implements ClobUnWrapper {

	public Clob unwrap(Clob wrapped) {
		try {
			Method method = wrapped.getClass().getMethod("getVendorObj",
					new Class[] {});
			return (oracle.sql.CLOB) method.invoke(wrapped);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String wrappedType() {
		return "weblogic.jdbc.wrapper.Clob_oracle_sql_CLOB";
	}

}
