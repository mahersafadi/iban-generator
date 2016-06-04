package com.lendico.iban_generator.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lendico.iban_generator.IbanException;
import com.lendico.iban_generator.api.BankAccount;
import com.lendico.iban_generator.api.CallBack;
import com.lendico.iban_generator.api.Iban;
import com.lendico.iban_generator.factories.IBanGeneratorFactory;

/**
 * This is the most important test. it is for concurrent calls. 100 thread will
 * ask the tool to generate ibans, then thread generates 1000 code;
 * 
 * @author maher
 *
 */
public class TestConcurrentGeneration {
	private static final Logger logger = LoggerFactory.getLogger(TestConcurrentGeneration.class);
	private static Map<String, Iban> checker = new ConcurrentHashMap<String, Iban>();

	@Test
	public void test() {
		logger.info("Generating Random test bank accounts ....");
		List<List<BankAccount>> list = this.generateBankAccounts();
		IbanGenerator gen = IBanGeneratorFactory.get();
		int c = 0;
		System.out
				.println("finish generating test bank accounts, not call iban generator");
		for (List<BankAccount> l : list) {
			Thread t = new Thread(new Generator(l, gen, ++c));
			t.setDaemon(true);
			t.start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static Set<String> gens = new HashSet<String>();

	static class Generator implements Runnable {
		List<BankAccount> bankAccs;
		private IbanGenerator gen;
		private int threadnum;

		public Generator(List<BankAccount> bankAccs, IbanGenerator gen, int i) {
			this.bankAccs = bankAccs;
			this.gen = gen;
			this.threadnum = i;
		}

		@Override
		public void run() {
			final int x [] = new int[1];
			CallBack cb1 = new CallBack() {
				
				@Override
				public void onsuccess(Iban iban) {
					logger.info(iban.getPrintedCode());
					assertEquals(null, checker.get(iban.getCode()));
					checker.put(iban.getCode(), iban);
				}
				@Override
				public void onFail(IbanException ex) {
					CallBack.super.onFail(ex);
					x[0]++;
				}
			};

			for (BankAccount bankAccount : bankAccs) {
				gen.generate(bankAccount, cb1);
			}
			assertEquals(0, x[0]);
			logger.info("Thread [" + this.threadnum + "] is finished");
		}
	}

	private List<List<BankAccount>> generateBankAccounts() {
		Random rand = new Random();
		List<List<BankAccount>> list = new ArrayList<List<BankAccount>>();
		String[] x = new String[] { "DE", "AT", "NL" };
		int curr = 0;
		for (int i = 0; i < 25; i++) {
			logger.info("Generating group for thread no:" + (i + 1));
			List<BankAccount> l = new ArrayList<BankAccount>();
			for (int j = 0; j < 100; j++) {
				curr = Math.abs(rand.nextInt()) % 3;
				String code = x[curr];
				if (code == "DE")
					l.add(generateDeCode(rand));
				else if (code == "NL")
					l.add(generateNlCode(rand));
				if (code == "AT")
					l.add(generateAtCode(rand));
			}
			list.add(l);
		}
		return list;
	}

	private BankAccount generateAtCode(Random rand) {
		String bankCode = null;
		while (bankCode == null) {
			int n = (Math.abs(rand.nextInt()) + 100000) % 100000;
			bankCode = toCode(n, 5);
		}

		String accountNumber = null;
		while (accountNumber == null) {
			long n = (Math.abs(rand.nextLong()) + 10000000000L) % 10000000000L;
			accountNumber = toCode(n, 11);

		}
		return new BankAccount("AT", bankCode, null, accountNumber);
	}

	private BankAccount generateNlCode(Random rand) {
		String bankCode = null;
		while (bankCode == null) {
			int n = (Math.abs(rand.nextInt()) + 100000) % 100000;
			bankCode = toCode(n, 4);

		}

		String accountNumber = null;
		while (accountNumber == null) {
			long n = (Math.abs(rand.nextLong()) + 10000000000L) % 10000000000L;
			accountNumber = toCode(n, 10);
		}
		return new BankAccount("NL", bankCode, null, accountNumber);
	}

	private BankAccount generateDeCode(Random rand) {
		String bankCode = null;
		while (bankCode == null) {
			int n = (Math.abs(rand.nextInt()) + 100000) % 100000;
			bankCode = toCode(n, 4);
		}
		String branchCode = null;
		while (branchCode == null) {
			int n = (Math.abs(rand.nextInt()) + 10000) % 10000;
			branchCode = toCode(n, 4);
		}
		String accountNumber = null;
		while (accountNumber == null) {
			long n = (Math.abs(rand.nextLong()) + 10000000000L) % 10000000000L;
			accountNumber = toCode(n, 10);
		}
		return new BankAccount("DE", bankCode, branchCode, accountNumber);
	}

	private String toCode(long n, int length) {
		n = Math.abs(n);
		String s = String.valueOf(n);
		while (s.length() <= length)
			s = "0" + s;
		if (s.length() > length)
			s = s.substring(0, length);
		if (gens.contains(s))
			return null;
		gens.add(s);
		return s;
	}
}
