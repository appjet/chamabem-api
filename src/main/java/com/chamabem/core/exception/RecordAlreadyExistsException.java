package com.chamabem.core.exception;

public class RecordAlreadyExistsException extends ChamabemException {

	private static final long serialVersionUID = 1L;
	
	public RecordAlreadyExistsException() {
		super("Registro já existente.");
	}

	public RecordAlreadyExistsException(Throwable throwable) {
		super("Registro já existente.");
	}

	public RecordAlreadyExistsException(String message, Throwable throwable) {
		super(throwable);
	}
}