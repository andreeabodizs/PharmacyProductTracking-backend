package com.product.tracking.exceptions;

public class PznAlreadyExistsException extends Exception {
	private static final long serialVersionUID = -729743861172943149L;
	
	public PznAlreadyExistsException(String pzn) {
		super("Product with pzn: " + pzn + " already exists");
	}

}
