package com.wondersgroup.falcon.persistence.lob;

import java.sql.Blob;

public interface BlobUnWrapper {
	String wrappedType();

	Blob unwrap(Blob wrapped);
}
