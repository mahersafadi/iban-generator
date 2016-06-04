package com.lendico.iban_generator.factories;

import com.lendico.iban_generator.core.IbanGenerator;
import com.lendico.iban_generator.core.impl.DefaultIbanHandler;
/**
 * Lendico
 * @author maher
 */
public final class IBanGeneratorFactory {
	private static class _Class {
		static IbanGenerator instance = new DefaultIbanHandler(
				CountriesMetasFactory.getCountriesLoader(), IBanValidatorFactory.get());
	}

	private IBanGeneratorFactory() {
	}

	public static IbanGenerator get() {
		return _Class.instance;
	}
}
