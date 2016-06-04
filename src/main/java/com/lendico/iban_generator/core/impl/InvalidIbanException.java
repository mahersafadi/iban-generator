package com.lendico.iban_generator.core.impl;

import com.lendico.iban_generator.IbanException;
import com.lendico.iban_generator.core.IbanValidator;
/**
 * This {@link Exception} is raised when an invalid IBAN code is passed to {@link IbanValidator#validate(code)}
 * Lendico
 * @author maher
 */
public class InvalidIbanException extends IbanException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6517571842564837030L;

	public enum Messages {
		NOT_SUPPORTED_COUNTRY_CODE(
				"iban_gen.validator.NOT_SUPPORTED_COUNTRY_CODE"), CODE_LENGTH_MISMATCH(
				"iban_gen.validator.CODE_LENGTH_MISMATCH"), MODE_BY_97_IS_NOT_1(
				"iban_gen.validator.MODE_BY_97_IS_NOT_1");
		private final String msgCode;

		private Messages(String msgCode) {
			this.msgCode = msgCode;
		}

		public String value() {
			return this.msgCode;
		}
	}

	public InvalidIbanException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
