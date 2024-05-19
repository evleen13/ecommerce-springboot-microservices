package com.evleen.productservice.exception;

public class DuplicateProductKeyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateProductKeyException() {
		super();
	}

	public DuplicateProductKeyException(String message) {
		super(message);
	}

}
