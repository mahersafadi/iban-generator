package com.lendico.iban_generator.core.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lendico.iban_generator.IbanException;
import com.lendico.iban_generator.api.BankAccount;
import com.lendico.iban_generator.api.CallBack;
import com.lendico.iban_generator.core.IbanGenerateException;
import com.lendico.iban_generator.core.IbanGenerator;
import com.lendico.iban_generator.core.IbanValidator;
import com.lendico.iban_generator.countries.CountriesLoader;
import com.lendico.iban_generator.countries.CountryMeta;
import com.lendico.iban_generator.factories.IBanGeneratorFactory;

/**
 * This class is the default IBAN generator from a bank account. it is created
 * by {@link IBanGeneratorFactory} <br/>
 * Lendico
 * 
 * @author maher
 */
public class DefaultIbanHandler implements IbanGenerator {
	protected final Logger logger = LoggerFactory.getLogger(IbanGenerator.class);
	protected CountriesLoader countriesLoader;
	protected IbanValidator ibanValidator;

	public DefaultIbanHandler(CountriesLoader countriesLoader,
			IbanValidator ibanValidator) {
		this.countriesLoader = countriesLoader;
		this.ibanValidator = ibanValidator;
	}

	@Override
	public synchronized void generate(BankAccount bankAccount, CallBack callBack) {
		try {
			if (bankAccount == null || bankAccount.getAccountNumber() == null)
				throw new IbanGenerateException(
						IbanGenerateException.Messages.INVALID_BANK_ACCOUNT
								.value());
			CountryMeta countryMeta = countriesLoader
					.getCountryMetas(bankAccount.getCode());
			if (!countryMeta.isValid(bankAccount))
				throw new IbanGenerateException(
						IbanGenerateException.Messages.INVALID_BANK_ACCOUNT
								.value());

			String checkCode = null;
			String leftPart = countryMeta.getLeftPart(bankAccount);
			if (countryMeta.isCustomCheckCode())// For Bosnia and
												// Herzegovina(39)
												// and East Timor (38)
				checkCode = countryMeta.getCode();
			else
				checkCode = getCheckCode(bankAccount.getCode(), leftPart);

			String code = bankAccount.getCode() + checkCode + leftPart;
			if (!Utils.isAsccII(code))
				throw new IbanGenerateException(
						IbanGenerateException.Messages.CONTAINS_INVALID_LETTERS
								.value());

			if (Utils.getFromCache(code) == null)
				Utils.putInCache(code, bankAccount);
			else
				throw new IbanGenerateException(
						IbanGenerateException.Messages.CODE_IS_ALREAD_GRANTED
								.value());
			if (code.length() != countryMeta.getChars())
				throw new IbanGenerateException(
						IbanGenerateException.Messages.INVALID_IBAN_GEN_LENGTH
								.value());
			ibanValidator.validate(code);
			callBack.onsuccess(new IbanImpl(code, countryMeta.format(code)));
		} catch (IbanException e) {
			callBack.onFail(e);
		}
	}

	private String getCheckCode(String code, String leftPart) {
		code = code.toUpperCase();
		leftPart = leftPart.toUpperCase();
		StringBuilder sb = new StringBuilder().append(leftPart).append(code)
				.append("00");
		BigDecimal bg = Utils.toNumber(sb.toString());
		if (bg == null)
			logger.error("Contains invalid chars");
		bg = bg.remainder(Utils.mode);
		int val = bg.intValue();
		val = 98 - val;
		StringBuilder buffer = new StringBuilder();
		if (val < 10)
			buffer.append("0");
		buffer.append(val);
		return buffer.toString();
	}
}
