package com.galaxy.converter.questions;

import java.util.Arrays;
import java.util.List;

import com.galaxy.converter.exception.GalacticBusinessInvalidCurrencyCodeException;
import com.galaxy.converter.exception.GalacticBusinessRomanSymbolException;
import com.galaxy.converter.util.Constants;
import com.galaxy.converter.util.GalacticConversionUtils;
import com.galaxy.converter.words.WordBook;

/**
 * This class holds the question of type HowMany. It implements the Question
 * interface.
 * 
 * @author Ramachandran S
 *
 */
public class HowMany implements Question {

	private String question;

	private static List<String> strRemovalList = Arrays.asList(Constants.HOW_MANY, Constants.CREDITS, Constants.IS,
			Constants.QUESTION);

	public HowMany(String question) {
		this.setQuestion(question);
	}

	private void setQuestion(String question) {
		this.question = question;
	}

	/*
	 * This method returns the String after removing the unwanted Strings available
	 * in strRemovalList
	 */
	@Override
	public String getGalacticString() {
		//Remove the unwanted String from the question
		return GalacticConversionUtils.removeStrings(question, strRemovalList);
	}

	/*
	 * 
	 * This method returns the response message that is to be added to the returning
	 * List. It converts and calculate the numeric value based on the data available
	 * on WordBook object
	 */
	@Override
	public String constructReturningMessage(String question, WordBook wordBook)
			throws GalacticBusinessRomanSymbolException, GalacticBusinessInvalidCurrencyCodeException {
		int interGalacticNumericValue = GalacticConversionUtils.getNumericValue(question,
				wordBook.getIntergalacticWordMap());
		float metalValue = GalacticConversionUtils.getMetalValue(question, wordBook.getMetalWordMap());
		String returningMessage = question + Constants.BLANK_SPACE + Constants.IS + Constants.BLANK_SPACE + "%.0f"
				+ Constants.BLANK_SPACE + Constants.CREDITS;
		return String.format(returningMessage, (metalValue * interGalacticNumericValue));
	}

}
