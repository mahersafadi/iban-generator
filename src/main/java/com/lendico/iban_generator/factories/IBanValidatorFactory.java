package com.lendico.iban_generator.factories;

import com.lendico.iban_generator.core.IbanValidator;
import com.lendico.iban_generator.core.impl.DefaultIbanValidator;
/**
 * Lendico
 * @author maher
 */
public final class IBanValidatorFactory {
	private static class IbanValidatorClass {
		static IbanValidator instance = new DefaultIbanValidator(CountriesMetasFactory.getCountriesLoader());
	}

	private IBanValidatorFactory() {
	}

	public static IbanValidator get() {
		return IbanValidatorClass.instance;
	}

}
