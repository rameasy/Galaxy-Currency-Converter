package com.galaxy.converter;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.galaxy.converter.helper.CurrencyHelper;
import com.galaxy.converter.util.Constants;

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
		if (!CollectionUtils.isEmpty(currencyValuesAndQuestion)) {
			List<String> conversionList = currencyHelper.getIntergalacticConversionValues(currencyValuesAndQuestion);
			logger.info("Exiting getConvertedValue");
			return conversionList;
		} else {
			logger.info("Exiting getConvertedValue with errors");
			return Arrays.asList(Constants.INVALID_QUESTION);
		}

	}
}
