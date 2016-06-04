package com.lendico.iban_generator.core;

import com.lendico.iban_generator.IbanException;
import com.lendico.iban_generator.api.BankAccount;
import com.lendico.iban_generator.api.Iban;

/**
 * Used when we want to generate an {@link Iban} code using passed
 * {@link BankAccount} and this {@link BankAccount} does not obtain the country
 * standards which the {@link BankAccount#getCode()} represents that country
 * code<br/>
 * Lendico
 * 
 * @author maher
 */
public class IbanGenerateException extends IbanException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 923292692699003193L;

	public IbanGenerateException(String msg) {
		super(msg);
	}

	public enum Messages {
		INVALID_BANK_ACCOUNT("iban_gen.generator.INVALID_BANK_ACCOUNT"), INVALID_IBAN_GEN_LENGTH(
				"iban_gen.generator.INVALID_IBAN_GEN_LENGTH"), CODE_IS_ALREAD_GRANTED(
				"iban_gen.generator.CODE_IS_ALREAD_GRANTED"), CONTAINS_INVALID_LETTERS(
				"iban_gen.generator.CONTAINS_INVALID_LETTERS");
		private final String msgCode;

		private Messages(String msgCode) {
			this.msgCode = msgCode;
		}

		public String value() {
			return this.msgCode;
		}
	}
}
