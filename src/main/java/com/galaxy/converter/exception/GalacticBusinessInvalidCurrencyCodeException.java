package com.galaxy.converter.exception;

/**
 * This class exception class for Invalid currency. If any invalid currency is
 * found this exception will be thrown.
 * 
 * @author Ramachandran S
 *
 */
public class GalacticBusinessInvalidCurrencyCodeException extends GalacticBusinessException {

	private static final long serialVersionUID = 1L;
	private final String message;

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public GalacticBusinessInvalidCurrencyCodeException(String message) {
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
