package com.walmart.exceptions;

public class ErrorMessagePresent extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5905512457135185108L;

	public ErrorMessagePresent(String message) {
		super(message);
	}

}
