package com.galaxy.converter.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.galaxy.converter.exception.GalacticBusinessRomanSymbolException;

/**
 * @author Ramachandran S
 * 
 *         This class helps to identify the question.
 *
 */
public class RomanUtils {

	protected static final Map<Character, Integer> romanMap = new HashMap<>();

	private static final Pattern pattern = Pattern.compile(Constants.ROMAN_VALIDATION_PATTERN);
	static {
		romanMap.put(Constants.ROMAN_LETTER_I, 1);
		romanMap.put(Constants.ROMAN_LETTER_V, 5);
		romanMap.put(Constants.ROMAN_LETTER_X, 10);
		romanMap.put(Constants.ROMAN_LETTER_L, 50);
		romanMap.put(Constants.ROMAN_LETTER_C, 100);
		romanMap.put(Constants.ROMAN_LETTER_D, 500);
		romanMap.put(Constants.ROMAN_LETTER_M, 1000);
	}

	/**
	 * default constructor
	 */
	private RomanUtils() {

	}

	/**
	 * This method converts the Roman letters to numerical value
	 * 
	 * @param romanValue
	 * @return int returns the number
	 * @throws GalacticBusinessRomanSymbolException
	 */
	public static int romanToNumber(String romanValue) throws GalacticBusinessRomanSymbolException {
		int total = 0;
		int currentValue = 0;
		int previousValue = 0;
		if (validateRomanValue(romanValue)) {
			for (int i = romanValue.length() - 1; i >= 0; i--) {
				currentValue = romanMap.get(romanValue.charAt(i));
				total += (currentValue < previousValue) ? -1 * currentValue : currentValue;
				previousValue = currentValue;
			}
		}
		return total;
	}

	/**
	 * This method validates the Roman string passed into it
	 * 
	 * @param romanValue
	 * @return boolean
	 */
	public static boolean validateRomanValue(String romanValue) throws GalacticBusinessRomanSymbolException {
		Matcher m = pattern.matcher(romanValue);
		if (m.find()) {
			return true;
		}
		throw new GalacticBusinessRomanSymbolException(Constants.INVALID_SYMBOL);
	}
}
