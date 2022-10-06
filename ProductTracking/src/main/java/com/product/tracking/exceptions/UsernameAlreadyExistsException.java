package com.product.tracking.exceptions;

public class UsernameAlreadyExistsException extends Exception {
	private static final long serialVersionUID = -4085941064911930211L;

	public UsernameAlreadyExistsException(String username) {
		super("Account with username: " + username + " already exists");
	}
}
