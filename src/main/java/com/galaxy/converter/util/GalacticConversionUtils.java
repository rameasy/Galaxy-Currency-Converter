package com.galaxy.converter.util;

import java.util.List;
import java.util.Map;

import com.galaxy.converter.exception.GalacticBusinessInvalidCurrencyCodeException;
import com.galaxy.converter.exception.GalacticBusinessRomanSymbolException;

/**
 * @author Ramachandran S
 * 
 *         This class helps to identify the question.
 *
 */
public class GalacticConversionUtils {

	/**
	 * default constructor
	 */
	private GalacticConversionUtils() {

	}

	/**
	 * This method removes the strings from question that is available in the list.
	 * 
	 * @param question
	 * @param strList
	 * @return String
	 */
	public static String removeStrings(String question, List<String> strList) {
		for (String strToBeRemoved : strList) {
			question = question.replace(strToBeRemoved, Constants.EMPTY_STRING);
		}
		return question.trim();
	}

	/**
	 * This method identifies the metal value that is available in the question
	 * 
	 * @param question
	 * @param metalMap
	 * @return
	 * @throws GalacticBusinessInvalidCurrencyCodeException
	 */
	public static float getMetalValue(String question, Map<String, Float> metalMap)
			throws GalacticBusinessInvalidCurrencyCodeException {
		float metalValue = 1f;
		boolean valueNotFound = true;
		//split the question with blank space and iterate the values on the metalMap 
		for (String key : question.split(Constants.BLANK_SPACE)) {
			if (metalMap.get(key) != null) {
				metalValue *= metalMap.get(key);
				valueNotFound = false;
			}
		}
		if (valueNotFound) { // If the metal name is not found on the Map
			throw new GalacticBusinessInvalidCurrencyCodeException(Constants.INVALID_METAL);
		}
		return metalValue;
	}

	/**
	 * This method returns the numeric value for the given question.
	 * 
	 * @param question
	 * @param romanMap
	 * @return int
	 * @throws GalacticBusinessRomanSymbolException
	 * @throws GalacticBusinessInvalidCurrencyCodeException
	 */
	public static int getNumericValue(String question, Map<String, String> romanMap)
			throws GalacticBusinessRomanSymbolException, GalacticBusinessInvalidCurrencyCodeException {
		StringBuilder romanValue = new StringBuilder();
		boolean valueNotFound = true;
		//split the question with blank space and iterate the values on the romanMap
		for (String key : question.split(Constants.BLANK_SPACE)) {
			if (romanMap.get(key) != null) {
				romanValue.append(romanMap.get(key));
				valueNotFound = false;
			}
		}
		if (valueNotFound) { // If the intergalactic symbol name is not found on the Map
			throw new GalacticBusinessInvalidCurrencyCodeException(Constants.INVALID_SYMBOL);
		}
		return RomanUtils.romanToNumber(romanValue.toString());
	}
}
