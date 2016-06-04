package com.lendico.iban_generator.countries;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lendico.iban_generator.countries.impl.CountryMetaException;
import com.lendico.iban_generator.factories.CountriesMetasFactory;

public class TestLoadCountriesMetaData {
	@Test
	public void testLoadCountriesMetaData() throws CountryMetaException{
		CountriesLoader countriesLoader = CountriesMetasFactory.getCountriesLoader();
		countriesLoader.load();
		CountryMeta countryMeta = countriesLoader.getCountryMetas("DE");
		assertNotNull(countryMeta);
		assertEquals(true, countryMeta.getChars() <=34);
		assertEquals(true, countryMeta.getFormat().length()<=34);
		assertEquals(true, "DE".equals(countryMeta.getCode()));
	}
}
