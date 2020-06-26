package com.galaxy.converter.questions;

import com.galaxy.converter.exception.GalacticBusinessInvalidCurrencyCodeException;
import com.galaxy.converter.exception.GalacticBusinessRomanSymbolException;
import com.galaxy.converter.words.WordBook;

/**
 * @author Ramachandran S
 * 
 *         This is an interface class of type Question.
 *
 */
public interface Question {
	/**
	 * This method constructs the message and returns to the list. It also finds the
	 * valid numerical value for the question before the message is constructed.
	 * 
	 * @param question
	 * @param wordBook
	 * @return String
	 * @throws GalacticBusinessRomanSymbolException
	 * @throws GalacticBusinessInvalidCurrencyCodeException
	 */
	public String constructReturningMessage(String question, WordBook wordBook)
			throws GalacticBusinessRomanSymbolException, GalacticBusinessInvalidCurrencyCodeException;

	/**
	 * This method returns the String after removing unrelated text from the
	 * question.
	 * 
	 * @return String
	 */
	public String getGalacticString();
}
