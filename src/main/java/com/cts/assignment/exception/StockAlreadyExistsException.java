package com.cts.assignment.exception;

public class StockAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(String message) {
		super(message);
	}

}
