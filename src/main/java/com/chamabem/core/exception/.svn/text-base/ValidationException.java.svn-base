package com.chamabem.core.exception;

import com.chamabem.util.ValidationResponse;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;


	private ValidationResponse validationResponse;

	public ValidationException(ValidationResponse validationResponse) {
		this.validationResponse = validationResponse;
	}
	
	public ValidationException(String message) {
		this.validationResponse = new ValidationResponse();
		this.validationResponse.setMessage(message);
	}

	public ValidationResponse getValidationResponse() {
		return validationResponse;
	}

	public void setValidationResponse(ValidationResponse validationResponse) {
		this.validationResponse = validationResponse;
	}
}