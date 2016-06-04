package com.lendico.iban_generator.countries.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lendico.iban_generator.countries.CountriesLoader;
import com.lendico.iban_generator.countries.CountryMeta;
import com.lendico.iban_generator.factories.CountriesMetasFactory;
/**
 * Lendico
 * @author maher
 */
public class DefaultCountriesLoader implements CountriesLoader {
	private Map<String, CountryMeta> countriesMetas = new ConcurrentHashMap<String, CountryMeta>();
	private boolean isLoaded = false;
	public void load() throws CountryMetaException {
		if(isLoaded)
			return;
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("countries-metas.properties");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			while (reader.ready()) {
				CountryMeta current = CountriesMetasFactory.createCountryMeta(reader);
				this.countriesMetas.put(current.getCode(), current);
			}
			isLoaded = true;
		} catch (IOException e) {
			throw new CountryMetaException(
					CountryMetaException.Messages.INVALID_META_FILE.value(), e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public CountryMeta getCountryMetas(String code) {
		return this.countriesMetas.get(code);
	}

}
