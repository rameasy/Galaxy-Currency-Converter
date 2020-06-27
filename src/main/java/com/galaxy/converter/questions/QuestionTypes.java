package com.galaxy.converter.questions;

import org.apache.commons.lang3.StringUtils;

import com.galaxy.converter.exception.GalacticBusinessInvalidCurrencyCodeException;
import com.galaxy.converter.util.Constants;

/**
 * @author Ramachandran S
 * 
 *         This class helps to identify the question.
 *
 */
public class QuestionTypes {

	/**
	 * This method gets the question as input, identifies the question type and
	 * returns it.
	 * 
	 * @param question
	 * @return Question
	 */
	public Question getQuestionType(String question) throws GalacticBusinessInvalidCurrencyCodeException {
		if (StringUtils.isBlank(question)) {
			throw new GalacticBusinessInvalidCurrencyCodeException(Constants.INVALID_QUESTION);
		}
		if (question.startsWith(Constants.HOW_MUCH)) {
			return new HowMuch(question);
		} else if (question.startsWith(Constants.HOW_MANY)) {
			return new HowMany(question);
		}
		return null;
	}
}
