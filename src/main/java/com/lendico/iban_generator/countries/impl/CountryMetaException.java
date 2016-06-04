package com.lendico.iban_generator.countries.impl;

import com.lendico.iban_generator.IbanException;
/**
 * Lendico
 * @author maher
 */
public class CountryMetaException extends IbanException {

	/**
	 * 
	 */

	public enum Messages {
		META_PROCESSING("iban_gen.countries_meta.processing"), INVALID_META_FILE(
				"iban_gen.countries_meta.invalid_meta_file"), INVALID_CODE(
				"iban_gen.countries_meta.invalid_code[>34 char or format is null]"),
				META_NOT_EXIST("iban_gen.countries_meta.meta_not_exist");
		private final String msgCode;

		private Messages(String msgCode) {
			this.msgCode = msgCode;
		}

		public String value() {
			return this.msgCode;
		}
	}

	private static final long serialVersionUID = -5739357828179579996L;

	public CountryMetaException(String msg, Throwable source) {
		super(msg, source);
	}

	public CountryMetaException(String msg) {
		super(msg);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
