package com.galaxy.converter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GalaxyCurrencyConverterApplicationTests {
	private static GalaxyCurrencyConverterApplication gcca = null;

	List<String> argumentsList = new ArrayList<>();

	@BeforeAll
	public static void setUp() {
		gcca = new GalaxyCurrencyConverterApplication();
	}

	@Test
	public void convertValueTest1() {
		argumentsList.add("glob is I");
		argumentsList.add("prok is V");
		argumentsList.add("pish is X");
		argumentsList.add("tegj is L");
		argumentsList.add("glob glob Silver is 34 Credits");
		argumentsList.add("glob prok Gold is 57800 Credits");
		argumentsList.add("pish pish Iron is 3910 Credits");
		argumentsList.add("how much is pish tegj glob glob ?");
		argumentsList.add("how many Credits is glob prok Silver ?");
		argumentsList.add("how many Credits is glob prok Gold ?");
		argumentsList.add("how many Credits is glob prok Iron ?");
		argumentsList.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		argumentsList.add("how many Credits is asdf asdf asdf ?");
		argumentsList.add("how many Credits is glob glob prok Silver ?");
		List<String> expectedList = gcca.getCurrencyValues(argumentsList);
		assertNotNull(expectedList);
		assertEquals(7, expectedList.size());
		assertEquals("pish tegj glob glob is 42", expectedList.get(0));
		assertEquals("glob prok Silver is 68 Credits", expectedList.get(1));
		assertEquals("glob prok Gold is 57800 Credits", expectedList.get(2));
		assertEquals("glob prok Iron is 782 Credits", expectedList.get(3));
		assertEquals("I have no idea what you are talking about", expectedList.get(4));
		assertEquals("I have no idea what you are talking about", expectedList.get(5));
		assertEquals("I have no idea about the symbol", expectedList.get(6));
	}

	
	@Test
	public void convertValueTest2() {
		argumentsList.add("abcd is I");
		argumentsList.add("efgh is V");
		argumentsList.add("ijkl is C");
		argumentsList.add("mnop is M");
		argumentsList.add("qrst is ");
		argumentsList.add("abcd abcd abcd Silver is 21 Credits");//Silver = 7
		argumentsList.add("ijkl mnop Gold is 900000 Credits"); //Gold = 100
		argumentsList.add("ijkl abcd efgh Iron is 2080 Credits"); //Iron = 20
		argumentsList.add("ijkl ijkl Platinum is 40000 Credits"); //Platinum = 200
		argumentsList.add("ijkl ijkl Lead is were Credits"); //Platinum = 200
		argumentsList.add("how much is ijkl mnop ?");
		argumentsList.add("how much is mnop ijkl ?");
		argumentsList.add("how much is ijkl mnop ijkl ?");
		argumentsList.add("how many Credits is abcd efgh Platinum ?");
		argumentsList.add("how many Credits is ijkl ijkl ijkl Silver ?");
		argumentsList.add("how many wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		argumentsList.add("how many Credits is glob prok Iron ?");
		argumentsList.add("how many Credits is mnop ijkl Gold ?");
		argumentsList.add("how much is mnop qrst ?");
		argumentsList.add("how many Credits is efgh Lead ?");
		argumentsList.add(null);
		argumentsList.add(" ");
		List<String> expectedList = gcca.getCurrencyValues(argumentsList);
		assertNotNull(expectedList);
		assertEquals(14, expectedList.size());
		assertEquals("I have no idea about the symbol", expectedList.get(0));
		assertEquals("I have no idea about the symbol", expectedList.get(1));
		assertEquals("ijkl mnop is 900", expectedList.get(2));
		assertEquals("mnop ijkl is 1100", expectedList.get(3));
		assertEquals("I have no idea about the symbol", expectedList.get(4));
		assertEquals("abcd efgh Platinum is 800 Credits", expectedList.get(5));
		assertEquals("ijkl ijkl ijkl Silver is 2100 Credits", expectedList.get(6));
		assertEquals("I have no idea what you are talking about", expectedList.get(7));
		assertEquals("I have no idea what you are talking about", expectedList.get(8));
		assertEquals("mnop ijkl Gold is 1100000 Credits", expectedList.get(9));
		assertEquals("I have no idea what you are talking about", expectedList.get(10));
		assertEquals("I have no idea about the metal", expectedList.get(11));
		assertEquals("I have no idea what you are talking about", expectedList.get(12));
		assertEquals("I have no idea what you are talking about", expectedList.get(13));
	}

	@Test
	public void convertValueTest3() {
		List<String> expectedList = gcca.getCurrencyValues(null);
		assertNotNull(expectedList);
		assertEquals(1, expectedList.size());
		assertEquals("I have no idea what you are talking about", expectedList.get(0));
	}
	
	@Test
	public void convertValueTest4() {
		List<String> expectedList = gcca.getCurrencyValues(Collections.emptyList());
		assertNotNull(expectedList);
		assertEquals(1, expectedList.size());
		assertEquals("I have no idea what you are talking about", expectedList.get(0));
	}
}
