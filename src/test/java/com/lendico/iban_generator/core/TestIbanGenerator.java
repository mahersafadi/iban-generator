package com.lendico.iban_generator.core;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.lendico.iban_generator.IbanException;
import com.lendico.iban_generator.api.BankAccount;
import com.lendico.iban_generator.api.Iban;
import com.lendico.iban_generator.factories.IBanGeneratorFactory;

@RunWith(Parameterized.class)
public class TestIbanGenerator {
	private String code;
	private String bankCode;
	private String branchCode;
	private String accountNumber;

	public TestIbanGenerator(String code, String bankCode, String branchCode, String accountNumber) {
		this.code = code;
		this.bankCode = bankCode;
		this.branchCode = branchCode;
		this.accountNumber = accountNumber;
	}

	@Test
	public void testIbanGeneratorAndValidator() throws IbanException {
		IbanGenerator ibanGenerator = IBanGeneratorFactory.get();
		assertNotNull(ibanGenerator);
		BankAccount bankAccount = mock(BankAccount.class);
		when(bankAccount.getCode()).thenReturn(code);
		when(bankAccount.getBankNationalCode()).thenReturn(bankCode);// 4
		when(bankAccount.getBranchCode()).thenReturn(branchCode);// 4
		when(bankAccount.getAccountNumber()).thenReturn(accountNumber);// 10
		ibanGenerator.generate(bankAccount, (Iban iban) -> {
			assertNotNull(iban);
			assertNotNull(iban.getCode());
			assertNotNull(iban.getPrintedCode());
			System.err.println(iban.getPrintedCode());
		});
	}

	@Parameterized.Parameters
	public static Collection getParams() {
		return Arrays.asList(new Object[][] { { "DE", "1234", "5678", "1234567890" }, // Deutch
				{ "AT", "12345", "", "12345678912" }, // Austeria
				{ "NL", "1234", "", "1234567890" } // Nethrland
		});
	}
}
