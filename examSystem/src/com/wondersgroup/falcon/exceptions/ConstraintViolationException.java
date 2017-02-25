package com.wondersgroup.falcon.exceptions;

/**
 * This exception is used to mark org.hibernate.exceptions.ConstraintViolationException.
 *
 * @author Bobby Sun
 */
public class ConstraintViolationException
	extends RuntimeException {

	private static final long serialVersionUID = -8381540921619298344L;

	public ConstraintViolationException() {
	}

	public ConstraintViolationException(String message) {
		super(message);
	}

	public ConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConstraintViolationException(Throwable cause) {
		super(cause);
	}
}
