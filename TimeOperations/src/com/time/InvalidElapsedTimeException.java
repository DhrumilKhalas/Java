package com.time;

//Custom exception class for handling invalid elapsed time
public class InvalidElapsedTimeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor that accepts the elapsed time as a parameter
	public InvalidElapsedTimeException(long elapsedTime) {
		// Calls the parent class (Exception) constructor with a custom error message
		// The message indicates that the elapsed time provided is invalid and can not
		// be negative.
		super("The Elapsed Time " + elapsedTime + " is not valid. It cannot be negative.");
	}
}