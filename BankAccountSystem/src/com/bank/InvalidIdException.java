package com.bank;

// Custom exception for handling invalid Account ID
public class InvalidIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor takes an Account ID and sets the error message
	public InvalidIdException(int id) {
		super("The Account ID " + id + " is not valid.");
	}
}
