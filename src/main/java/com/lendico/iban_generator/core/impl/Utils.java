package com.lendico.iban_generator.core.impl;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lendico.iban_generator.api.BankAccount;
/**
 * Lendico
 * @author maher
 */
public class Utils {
	public static BigDecimal toNumber(String codeAsString) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < codeAsString.length(); i++) {
			char current = codeAsString.charAt(i);
			int ascii = (int) current;
			if (ascii >= 48 && ascii <= 57)
				buffer.append(current);
			else if (ascii >= 65 && ascii <= 90)
				buffer.append(Utils.replace(ascii));
			else
				return null;
		}
		return new BigDecimal(buffer.toString());
	}

	private static int replace(int ascii) {
		return 10 + (ascii - (int) 'A');
	}

	public static final BigDecimal mode = new BigDecimal("97");

	private static LoadingCache<String, BankAccount> cache = CacheBuilder
			.newBuilder().maximumSize(Long.MAX_VALUE)
			.expireAfterAccess(Long.MAX_VALUE, TimeUnit.MINUTES)
			.build(new CacheLoader<String, BankAccount>() {
				@Override
				public BankAccount load(String arg0) throws Exception {
					return null;
				}
			});

	public static void putInCache(String code, BankAccount bankAccount) {
		if (code != null && bankAccount != null)
			cache.put(code, bankAccount);
	}

	public static BankAccount getFromCache(String code) {
		if (code != null)
			return cache.getIfPresent(code);
		return null;
	}
	
	public static boolean isAsccII(String code) {
		code = code.toUpperCase();
		boolean valid = true;
		for (int i = 0; i < code.length() && valid; i++) {
			int curr = (int) code.charAt(i);
			if ((curr >= 48 && curr <= 57) || (curr >= 65 && curr <= 90))
				;
			else
				valid = false;
		}
		return valid;
	}
}
