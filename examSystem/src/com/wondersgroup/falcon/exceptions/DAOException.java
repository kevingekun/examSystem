/**
 * 
 */
package com.wondersgroup.falcon.exceptions;

/**
 * <p>Title:[DAO异常类] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2009-6-30]
 * Midified by [修改人] [修改时间]
 *
 */
public class DAOException extends RuntimeException {

	/**  */
	private static final long serialVersionUID = -7978947539227110205L;

	
	public DAOException() {
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}
	
}
