package com.galaxy.converter.exception;

/**
 * This class acts as the super class of all the Exception in this application.
 * Any new business exception created in future has to be extended to this
 * exception class
 * 
 * @author Ramachandran S
 *
 */
public class GalacticBusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String message;
	
	/**
	 * This method Returns the exception Message
	 * 
	 * @param message
	 */
	public GalacticBusinessException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * This method returns the message that is raised for this exception
	 * 
	 * @return
	 */
	public String getExceptionMessage() {
		return message;
	}
}
