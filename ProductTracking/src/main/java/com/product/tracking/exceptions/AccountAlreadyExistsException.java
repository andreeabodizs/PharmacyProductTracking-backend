package com.product.tracking.exceptions;

public class AccountAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 7964038631947158637L;

	public AccountAlreadyExistsException(String email) {
		super("Account with email: " + email + " already exists");
	}
}
