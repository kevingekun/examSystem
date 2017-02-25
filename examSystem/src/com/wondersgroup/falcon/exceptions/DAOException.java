/**
 * 
 */
package com.wondersgroup.falcon.exceptions;

/**
 * <p>Title:[DAO�쳣��] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2009-6-30]
 * Midified by [�޸���] [�޸�ʱ��]
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
