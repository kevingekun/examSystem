package com.wondersgroup.falcon.persistence.lob;

import java.sql.Clob;
import java.sql.Blob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import oracle.sql.BLOB;
import oracle.sql.CLOB;

import org.hibernate.lob.SerializableBlob;
import org.hibernate.lob.SerializableClob;

public class OracleLobUtil {
	private static final Map<String, ClobUnWrapper> clobUnWrappers = new HashMap<String, ClobUnWrapper>();
	private static final Map<String, BlobUnWrapper> blobUnWrappers = new HashMap<String, BlobUnWrapper>();

	static {
		register(new WeblogicClobUnWrapper());
		registerBlob(new WeblogicBlobUnWrapper());
	}

	private static void register(ClobUnWrapper unWrapper) {
		clobUnWrappers.put(unWrapper.wrappedType(), unWrapper);
	}
	private static void registerBlob(BlobUnWrapper unWrapper) {
		blobUnWrappers.put(unWrapper.wrappedType(), unWrapper);
	}

	public static void setClobUnWrappers(List<ClobUnWrapper> clobUnWrappers) {
		for (ClobUnWrapper unWrapper : clobUnWrappers) {
			register(unWrapper);
		}
	}
	public static void setBlobUnWrappers(List<BlobUnWrapper> blobUnWrappers) {
		for (BlobUnWrapper unWrapper : blobUnWrappers) {
			registerBlob(unWrapper);
		}
	}

	public static CLOB unwrap(SerializableClob clob) {
		return unwrap(clob.getWrappedClob());
	}

	public static BLOB unwrap(SerializableBlob blob) {
		return unwrapBlob(blob.getWrappedBlob());
	}

	public static CLOB unwrap(Clob clob) {
		if (clob instanceof CLOB) {
			return (CLOB) clob;
		} else {
			ClobUnWrapper unWrapper = clobUnWrappers.get(clob.getClass()
					.getName());
			if (unWrapper != null) {
				return (CLOB) unWrapper.unwrap(clob);
			}

			throw new UnsupportedOperationException("Cann't unwrap "
					+ clob.getClass().getName() + " to oracle.sql.CLOB");
		}
	}
	public static BLOB unwrapBlob(Blob blob) {
		if (blob instanceof BLOB) {
			
			return (BLOB) blob;
			
		} else {
			BlobUnWrapper unWrapper = blobUnWrappers.get(blob.getClass()
					.getName());
			if (unWrapper != null) {
				return (BLOB) unWrapper.unwrap(blob);
			}

			throw new UnsupportedOperationException("Cann't unwrap "
					+ blob.getClass().getName() + " to oracle.sql.BLOB");
		}
	}

}
