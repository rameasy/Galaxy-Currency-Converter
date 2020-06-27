package com.galaxy.converter.words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.galaxy.converter.exception.GalacticBusinessNumericSymbolException;
import com.galaxy.converter.exception.GalacticBusinessRomanSymbolException;
import com.galaxy.converter.util.Constants;
import com.galaxy.converter.util.RomanUtils;

/**
 * @author Ramachandran S
 *
 */
public class WordBook {
	private static final Logger logger = LoggerFactory.getLogger(WordBook.class);
	private List<String> wordList = new ArrayList<>();
	private Map<String, String> intergalacticWordMap = new HashMap<>();
	private Map<String, Float> metalWordMap = new HashMap<>();

	/**
	 * default constructor
	 */
	public WordBook() {
		// default constructor
	}

	public List<String> getWordList() {
		return wordList;
	}

	public void addWord(String word) {
		this.wordList.add(word);
	}

	public Map<String, String> getIntergalacticWordMap() {
		return intergalacticWordMap;
	}

	public void addWordToIntergalacticWordMap(String key, String value) {
		this.intergalacticWordMap.put(key, value);
	}

	public Map<String, Float> getMetalWordMap() {
		return metalWordMap;
	}

	public void addWordToMetalWordMap(String key, Float value) {
		this.metalWordMap.put(key, value);
	}

	/**
	 * This method adds the Words to wordList, metalWordMap and
	 * intergalacticWordMap. It stores the Roman value for the intergalacticWordMap
	 * and numerical numbers for metalWordMap. It also identifies the actual value
	 * each metal before storing it in metalWordMap.
	 * 
	 * @param words
	 * @param value
	 * @throws GalacticBusinessNumericSymbolException
	 * @throws GalacticBusinessRomanSymbolException
	 */
	public void addWordsToWordBook(String words, String value)
			throws GalacticBusinessNumericSymbolException, GalacticBusinessRomanSymbolException {
		logger.info("Entering addWordsToWordBook");
		try {
			// split the String with blanks space. If it is greater than 1 word then it is
			// with metal name
			String[] splitString = words.split(Constants.BLANK_SPACE);
			if (splitString.length > 1) {
				StringBuilder romanValue = new StringBuilder();
				for (String str : splitString) {
					if (!this.getWordList().contains(str)) {
						// if the string does not exist in the word book then add it
						this.addWord(str);
						// identify the roman value for the other words and convert it into numeric
						// value
						int otherNumber = RomanUtils.romanToNumber(romanValue.toString());
						float actualValueInNumber = (float) Integer.parseInt(value) / otherNumber;
						romanValue.setLength(0);
						// add the metal word to metalWordMap with the identified metal value
						this.addWordToMetalWordMap(str, actualValueInNumber);
					} else {
						romanValue.append(this.getIntergalacticWordMap().get(str));
					}
				}
			} else {
				// add the symbol and its value
				if (!this.getWordList().contains(words.trim())) {
					this.addWord(words.trim());
					this.addWordToIntergalacticWordMap(words.trim(), value);
				}
			}
			logger.info("Exiting addWordsToWordBook");
		} catch (NumberFormatException nfe) {
			logger.error("Exception caught {} for {}", nfe.getMessage(), value);
			throw new GalacticBusinessNumericSymbolException(Constants.INVALID_SYMBOL);
		} catch (GalacticBusinessRomanSymbolException | ArrayIndexOutOfBoundsException ex) {
			logger.error("Exception caught {} for {}", ex.getMessage(), value);
			throw new GalacticBusinessRomanSymbolException(Constants.INVALID_SYMBOL);
		}
	}
}
