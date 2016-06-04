package com.lendico.iban_generator.countries;

import com.lendico.iban_generator.countries.impl.CountryMetaException;

/**
 * Responsible about load meta data from properties file and get a Country meta
 * based on its code.
 * 
 * <br/>
 * Lendicoss
 * 
 * @author maher
 *
 */
public interface CountriesLoader {
	/**
	 * load from properties file
	 * 
	 * @throws CountryMetaException
	 */
	void load() throws CountryMetaException;

	/**
	 * get country meta based on country meta string data
	 * 
	 * @param code
	 * @return
	 */
	CountryMeta getCountryMetas(String code);

}
