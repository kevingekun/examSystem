package com.wondersgroup.falcon.persistence.lob;

import java.lang.reflect.Method;
import java.sql.Blob;

public class WeblogicBlobUnWrapper implements BlobUnWrapper {

	public Blob unwrap(Blob wrapped) {
		try {
			Method method = wrapped.getClass().getMethod("getVendorObj",
					new Class[] {});
			return (oracle.sql.BLOB) method.invoke(wrapped);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String wrappedType() {
		return "weblogic.jdbc.wrapper.Blob_oracle_sql_BLOB";
	}

}
