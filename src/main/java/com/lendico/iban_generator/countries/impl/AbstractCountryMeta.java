package com.lendico.iban_generator.countries.impl;

import com.lendico.iban_generator.api.BankAccount;
import com.lendico.iban_generator.countries.CountryMeta;
/**
 * Lendico
 * @author maher
 */
public abstract class AbstractCountryMeta implements CountryMeta {

	private String code;
	private String name;
	private String format;
	private int chars;

	public void setMeta(String[] items) throws CountryMetaException {
		this.code = items[0];
		this.name = items[1];
		this.chars = Integer.parseInt(items[2]);
		if (chars > 34)
			throw new CountryMetaException(
					CountryMetaException.Messages.INVALID_CODE.value());
		this.format = items[3];
		if (format == null || format.length() > 34)
			throw new CountryMetaException(
					CountryMetaException.Messages.INVALID_CODE.value());

	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getChars() {
		return chars;
	}

	public String getFormat() {
		return format;
	}

	protected int sum(String... strings) {
		if (strings == null)
			return 0;
		int ret = 0;
		for (String str : strings) {
			ret += str != null ? str.length() : 0;
		}
		return ret;
	}

	/**
	 * By default it is not custom, and it an be extendsed in the child
	 */
	@Override
	public boolean isCustomCheckCode() {
		return false;
	}
	
	/**
	 * Can not accept a bank account without bank code and account number
	 */
	@Override
	public boolean isValid(BankAccount bankAccount) {
		if (bankAccount == null || bankAccount.getBankNationalCode() == null
				|| bankAccount.getAccountNumber() == null)
			return false;
		return true;
	}
}
