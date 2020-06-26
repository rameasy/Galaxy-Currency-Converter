package com.galaxy.converter.exception;

/**
 * This class exception class for Symbols missing the numeric values.
 * 
 * @author Ramachandran S
 *
 */
public class GalacticBusinessNumericSymbolException extends GalacticBusinessException {

	private static final long serialVersionUID = 1L;
	private final String message;

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public GalacticBusinessNumericSymbolException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * This method returns the message that is raised for this exception
	 * 
	 * @return
	 */
	@Override
	public String getExceptionMessage() {
		return message;
	}
}
