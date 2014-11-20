package com.walmart.utils.wait.exception;

/**
 * 
 * @author Maryia_Sakalouskaya
 * 
 *         Exception which will be thrown when wait not succeded
 * 
 */
public class TimeoutException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6045546352298678275L;

	public TimeoutException(String message, Throwable e) {
		super(message, e);
	}

	public TimeoutException(String message) {
		super(message);
	}
}
