package com.lendico.iban_generator.core.impl.validators;

import java.math.BigDecimal;

import com.lendico.iban_generator.core.impl.DefaultIbanValidator;
import com.lendico.iban_generator.core.impl.InvalidIbanException;
import com.lendico.iban_generator.core.impl.Utils;
import com.lendico.iban_generator.core.impl.Validator;
import com.lendico.iban_generator.countries.CountryMeta;

/**
 * This class is the main validator in validation process, it is automatically
 * loaded by {@link DefaultIbanValidator} so ne need to instance it<br/>
 * Lendico
 * 
 * @author maher
 */
public class StandardValidator implements Validator {
	@Override
	public void apply(String code, CountryMeta countryMeta)
			throws InvalidIbanException {
		if (code.length() != countryMeta.getChars())
			throw new InvalidIbanException(
					InvalidIbanException.Messages.CODE_LENGTH_MISMATCH.value());
		String right = code.substring(0, 4);
		String left = code.substring(4);
		String newCode = left + right;
		BigDecimal bg = Utils.toNumber(newCode);
		bg = bg.remainder(Utils.mode);
		if (bg.intValue() != 1)
			throw new InvalidIbanException(
					InvalidIbanException.Messages.MODE_BY_97_IS_NOT_1.value());
	}
}
