package com.galaxy.converter.helper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.galaxy.converter.exception.GalacticBusinessException;
import com.galaxy.converter.exception.GalacticBusinessInvalidCurrencyCodeException;
import com.galaxy.converter.exception.GalacticBusinessNumericSymbolException;
import com.galaxy.converter.exception.GalacticBusinessRomanSymbolException;
import com.galaxy.converter.questions.Question;
import com.galaxy.converter.questions.QuestionTypes;
import com.galaxy.converter.util.Constants;
import com.galaxy.converter.words.WordBook;

/**
 * This class converts the galactic string to human readable numeric number and
 * returns the result in list.
 * 
 * @author Ramachandran S
 *
 */
public class CurrencyHelper {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyHelper.class);
	private static CurrencyHelper currencyHelper = null;

	/**
	 * This method returns the Currency helper object. it returns the object it has
	 * created earlier else return a new object
	 * 
	 * @return CurrencyHelper returns the object
	 */
	public static CurrencyHelper getCurrencyHelper() {
		if (currencyHelper == null)
			currencyHelper = new CurrencyHelper();
		return currencyHelper;
	}

	/**
	 * default constructor
	 */
	private CurrencyHelper() {

	}

	/**
	 * This method separates the intergalactic currencies and the questions. It
	 * validates the currencies and parses them and returns the values in numerical
	 * format. The numerical format data is added to the response message and
	 * returned as the list.
	 * 
	 * @param currencyValuesAndQuestion
	 * @param wordBook
	 * @return List<String> returns the result message
	 */
	public List<String> getIntergalacticConversionValues(List<String> currencyValuesAndQuestion) {
		logger.info("Entering getIntergalacticConversionValues");
		List<String> returningList = new ArrayList<>();
		QuestionTypes questionTypes = new QuestionTypes();
		WordBook wordBook = new WordBook();
		for (String values : currencyValuesAndQuestion) {
			try {
				// identify the question type
				Question question = questionTypes.getQuestionType(values);
				if (question != null) {
					validateGalacticCurrencies(question.getGalacticString(), wordBook);
					returningList.add(question.constructReturningMessage(question.getGalacticString(), wordBook));
				} else {
					// if it is not a question then add the symbol/metal to the WordBook
					setGalacticCurrenciesMappingValues(values, wordBook);

				}
			} catch (GalacticBusinessException ex) {
				logger.error("Exception caught {} for {}", ex.getExceptionMessage(), values);
				returningList.add(ex.getExceptionMessage());
			}
		}
		logger.info("Exiting getIntergalacticConversionValues with List :: {}", returningList);
		return returningList;
	}

	/**
	 * 
	 * This method parse the string values into key value pair. the key and values
	 * are added into WorkBook object so that it can be used for the conversion
	 * later. If the parsing is not successful then GalacticBusinessSymbolException
	 * is thrown
	 * 
	 * @param values
	 * @param wordBook
	 * @throws GalacticBusinessRomanSymbolException
	 * @throws GalacticBusinessNumericSymbolException
	 */
	private void setGalacticCurrenciesMappingValues(String values, WordBook wordBook)
			throws GalacticBusinessRomanSymbolException, GalacticBusinessNumericSymbolException {
		logger.info("Entering setGalacticCurrenciesMappingValues");
		try {
			// Split the string based on ' is ' value available on the String
			String[] keyValue = values.split(Constants.BLANK_SPACE + Constants.IS + Constants.BLANK_SPACE);
			keyValue[1] = keyValue[1].replace(Constants.CREDITS, Constants.EMPTY_STRING).trim();
			if (keyValue[0].split(Constants.BLANK_SPACE) != null) {
				wordBook.addWordsToWordBook(keyValue[0], keyValue[1]);
			}
		} catch (GalacticBusinessRomanSymbolException | ArrayIndexOutOfBoundsException ex) {
			throw new GalacticBusinessRomanSymbolException(Constants.INVALID_SYMBOL);
		} catch (GalacticBusinessNumericSymbolException nse) {
			throw nse;
		}
		logger.info("Exiting setGalacticCurrenciesMappingValues");
	}

	/**
	 * This method validates the String passed whether the String is available in
	 * the WorkBook. If it is not available then
	 * GalacticBusinessInvalidCurrencyCodeException exception is thrown.
	 * 
	 * @param galacticCurrencies
	 * @param wordBook
	 * @throws GalacticBusinessInvalidCurrencyCodeException
	 */
	private void validateGalacticCurrencies(String galacticCurrencies, WordBook wordBook)
			throws GalacticBusinessInvalidCurrencyCodeException {
		logger.info("Entering validateGalacticCurrencies");
		String[] currencies = galacticCurrencies.split(Constants.BLANK_SPACE);
		for (String currency : currencies) {
			if (!wordBook.getWordList().contains(currency)) {
				throw new GalacticBusinessInvalidCurrencyCodeException(Constants.INVALID_QUESTION);
			}
		}
		logger.info("Exiting validateGalacticCurrencies");
	}

}
