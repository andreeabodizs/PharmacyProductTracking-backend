package com.product.tracking.exceptions;

public class OldPasswordIsIncorectException extends Exception {

	private static final long serialVersionUID = 6605827506013220350L;
	public OldPasswordIsIncorectException() {
		super("Old password is incorrect!");
	}
}
