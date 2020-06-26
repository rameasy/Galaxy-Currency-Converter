package com.galaxy.converter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.galaxy.converter.helper.CurrencyHelper;

/**
 * @author Ramachandran S
 * 
 *         This class is the entry point for the Galaxy Currency converter
 *         application.
 *
 */
public class GalaxyCurrencyConverterApplication {
	private static final Logger logger = LoggerFactory.getLogger(GalaxyCurrencyConverterApplication.class);

	private static CurrencyHelper currencyHelper = null;
	static {
		currencyHelper = CurrencyHelper.getCurrencyHelper();
	}

	/**
	 * This method sends values and the questions and gets the answers in the form
	 * of List.
	 * 
	 * @param currencyValuesAndQuestion
	 * @return
	 */
	public List<String> getCurrencyValues(List<String> currencyValuesAndQuestion) {
		logger.info("Entering getConvertedValue");
		List<String> conversionList = currencyHelper.getIntergalacticConversionValues(currencyValuesAndQuestion);
		logger.info("Exiting getConvertedValue");
		return conversionList;
	}
}
