package com.lendico.iban_generator.factories;

import java.io.BufferedReader;
import java.io.IOException;

import com.lendico.iban_generator.countries.CountriesLoader;
import com.lendico.iban_generator.countries.CountryMeta;
import com.lendico.iban_generator.countries.impl.CountryMetaException;
import com.lendico.iban_generator.countries.impl.DefaultCountriesLoader;
/**
 * Lendico
 * @author maher
 */
public final class CountriesMetasFactory {
	private static class CountriesLoaderClass {
		static CountriesLoader instance = new DefaultCountriesLoader();
	}

	private CountriesMetasFactory() {

	}

	public static CountriesLoader getCountriesLoader()  {
		CountriesLoader countriesLoader = CountriesLoaderClass.instance;
		try {
			countriesLoader.load();
		} catch (CountryMetaException e) {
			throw new RuntimeException(e.getMessage());
		}
		return countriesLoader;
	}

	private static final String metasPackage = "com.lendico.iban_generator.countries.impl.metas.";

	@SuppressWarnings("unchecked")
	public static CountryMeta createCountryMeta(BufferedReader reader) throws CountryMetaException{
		
		Class<? extends CountryMeta> c;
		try {
			String line = reader.readLine();
			String [] items = line.split(",");
			c = (Class<? extends CountryMeta>) Class
					.forName(metasPackage + items[0].toUpperCase() + "Meta");

			CountryMeta instance = c.newInstance();
			instance.setMeta(items);
			return instance;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new CountryMetaException(CountryMetaException.Messages.META_NOT_EXIST.value(), e);
		} catch (IOException e) {
			throw new CountryMetaException(CountryMetaException.Messages.INVALID_META_FILE.value(), e);
		}
	}

}
