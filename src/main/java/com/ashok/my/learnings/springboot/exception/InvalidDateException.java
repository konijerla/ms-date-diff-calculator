/**
 * 
 */
package com.ashok.my.learnings.springboot.exception;

/**
 * 
 */
public class InvalidDateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidDateException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidDateException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidDateException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidDateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidDateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	
	
}
