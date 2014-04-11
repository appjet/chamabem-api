package com.chamabem.core.exception;

public class AccessDeniedException extends ChamabemException {

	private static final long serialVersionUID = 1L;

	public AccessDeniedException(String message) {
		super(message);
	}
	
	public AccessDeniedException(Throwable cause) {
		super(cause);
	}
}