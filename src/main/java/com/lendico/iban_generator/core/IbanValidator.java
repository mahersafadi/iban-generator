package com.lendico.iban_generator.core;

import com.lendico.iban_generator.IbanException;
import com.lendico.iban_generator.core.impl.InvalidIbanException;

/**
 * this interface is responsible about validate IBAN code. if iban code is not
 * valid then {@link InvalidIbanException} either wise every thing is passed
 * fine <br/>
 * Lendico
 * @author maher
 *
 */
public interface IbanValidator {
	/**
	 * 
	 * @param code, The IBAN code to be validate
	 * @throws IbanException 
	 */
	void validate(String code) throws IbanException;
	
	void clearValidator();

}
