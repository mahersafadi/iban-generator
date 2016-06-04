package com.lendico.iban_generator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lendico.iban_generator.api.BankAccount;
import com.lendico.iban_generator.api.CallBack;
import com.lendico.iban_generator.api.Iban;
import com.lendico.iban_generator.factories.IBanGeneratorFactory;

public class TestGrantUniqueIBANs {
	private static final Logger logger = LoggerFactory.getLogger(TestGrantUniqueIBANs.class);
	/**
	 * Test over 125000 German accounts, 125000 Austrian accounts and 125000
	 * Netherlands account. All accounts are valid. just to check if all
	 * generated codes are falid
	 */
	private static Map<String, Iban> checker = new ConcurrentHashMap<String, Iban>();
	Set<Integer> is = new HashSet<Integer>();
	Set<Integer> js = new HashSet<Integer>();
	Set<Long> ls = new HashSet<Long>();
	BankAccount bankAccount = new BankAccount();
	IbanGenerator ibanGenerator = IBanGeneratorFactory.get();
	Random rand = new Random();

	@Test
	public void generateCodes() {
		generate125000GermanCode();
		generate125000AustiaCode();
		generate125000NetherlandCode();
	}

	private void generate125000NetherlandCode() {
		int[] x = new int[1];
		CallBack callBack = (Iban iban) -> {
			assertNotNull(iban);
			assertNotNull(iban.getCode());
			assertNotNull(iban.getPrintedCode());
			logger.info(iban.getPrintedCode());
			assertEquals(null, checker.get(iban.getCode()));
			checker.put(iban.getCode(), iban);
			x[0]++;
		};
		bankAccount.setCode("NL");
		int i = 0, j = 0;
		while (i != 50) {
			i++;
			String bankCode = null;
			while (bankCode == null) {
				int n = (Math.abs(rand.nextInt()) + 10000) % 10000;
				if (!is.contains(n)) {
					is.add(n);
					bankCode = toCode(n, 4);
				}
			}
			j = 0;
			while (j != 2500) {
				j++;
				String accountNumber = null;
				while (accountNumber == null) {
					long n = (Math.abs(rand.nextLong()) + 1000000000) % 1000000000;
					if (!ls.contains(n)) {
						ls.add(n);
						accountNumber = toCode(n, 10);
					}
				}

				bankAccount.setBankNationalCode(bankCode);
				bankAccount.setBranchCode(null);
				bankAccount.setAccountNumber(accountNumber);
				ibanGenerator.generate(bankAccount, callBack);

			}
		}
		assertEquals(125000, x[0]);
	}

	private void generate125000AustiaCode() {
		int[] x = new int[1];
		CallBack callBack = (Iban iban) -> {
			assertNotNull(iban);
			assertNotNull(iban.getCode());
			assertNotNull(iban.getPrintedCode());
			logger.info(iban.getPrintedCode());
			assertEquals(null, checker.get(iban.getCode()));
			checker.put(iban.getCode(), iban);
			x[0]++;
		};
		bankAccount.setCode("AT");
		int i = 0, j = 0;
		while (i != 50) {
			i++;
			String bankCode = null;
			while (bankCode == null) {
				int n = (Math.abs(rand.nextInt()) + 100000) % 100000;
				if (!is.contains(n)) {
					is.add(n);
					bankCode = toCode(n, 5);
				}
			}
			j = 0;
			while (j != 2500) {
				j++;
				String accountNumber = null;
				while (accountNumber == null) {
					long n = (Math.abs(rand.nextLong()) + 10000000000L) % 10000000000L;
					if (!ls.contains(n)) {
						ls.add(n);
						accountNumber = toCode(n, 11);
					}
				}
				bankAccount.setBankNationalCode(bankCode);
				bankAccount.setBranchCode(null);
				bankAccount.setAccountNumber(accountNumber);
				ibanGenerator.generate(bankAccount, callBack);
			}
		}
		assertEquals(125000, x[0]);
	}

	public void generate125000GermanCode() {
		int[] x = new int[1];
		CallBack callBack = (Iban iban) -> {
			assertNotNull(iban);
			assertNotNull(iban.getCode());
			assertNotNull(iban.getPrintedCode());
			logger.info(iban.getPrintedCode());
			assertEquals(null, checker.get(iban.getCode()));
			checker.put(iban.getCode(), iban);
			x[0]++;
		};
		bankAccount.setCode("DE");
		int i = 0, j = 0, k = 0;
		while (i != 50) {
			i++;
			String bankCode = null;
			while (bankCode == null) {
				int n = (Math.abs(rand.nextInt()) + 10000) % 10000;
				if (!is.contains(n)) {
					is.add(n);
					bankCode = toCode(n, 4);
				}
			}
			j = 0;
			while (j != 50) {
				j++;
				String branchCode = null;
				while (branchCode == null) {
					int n = (Math.abs(rand.nextInt()) + 10000) % 10000;
					if (!js.contains(n)) {
						js.add(n);
						branchCode = toCode(n, 4);
					}
				}
				k = 0;
				while (k != 50) {
					k++;
					String accountNumber = null;
					while (accountNumber == null) {
						long n = (Math.abs(rand.nextLong()) + 1000000000) % 1000000000;
						if (!ls.contains(n)) {
							ls.add(n);
							accountNumber = toCode(n, 10);
						}
					}
					bankAccount.setBankNationalCode(bankCode);
					bankAccount.setBranchCode(branchCode);
					bankAccount.setAccountNumber(accountNumber);
					ibanGenerator.generate(bankAccount, callBack);
				}
			}
		}
		assertEquals(125000, x[0]);
	}

	private String toCode(int n, int length) {
		String s = String.valueOf(n);
		while (s.length() != length)
			s = "0" + s;
		return s;
	}

	private String toCode(long n, int length) {
		String s = String.valueOf(n);
		while (s.length() != length)
			s = "0" + s;
		return s;
	}
}
