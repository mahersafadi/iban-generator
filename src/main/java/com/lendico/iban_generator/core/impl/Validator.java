package com.lendico.iban_generator.core.impl;

import com.lendico.iban_generator.core.IbanValidator;
import com.lendico.iban_generator.core.impl.validators.StandardValidator;
import com.lendico.iban_generator.countries.CountryMeta;

/**
 * represents the validator applied over the IBAN code passed to
 * {@link IbanValidator#validate(code)}. currently just
 * {@link StandardValidator} implements it, and in for extending we will use
 * UPC, ISBN 10, ISBN 13, EAN<br/>
 * Lendico
 * 
 * @author maher
 */
public interface Validator {
	/**
	 * 
	 * @param code the IBAN Code
	 * @param countryMeta the country meta that the passed IBAN starts with its code
	 * @throws InvalidIbanException raised when IBAN code does not obtain the country ISO standards
	 */
	void apply(String code, CountryMeta countryMeta)
			throws InvalidIbanException;
}
