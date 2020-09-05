package com.product.promotion.api.exception;

public class ApiError {

	private String errorMessage;
	private int statusCode;

	public ApiError(final String errorMessage, final int statusCode) {
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
