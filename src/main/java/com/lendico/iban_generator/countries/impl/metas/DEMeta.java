package com.lendico.iban_generator.countries.impl.metas;

import com.lendico.iban_generator.api.BankAccount;
import com.lendico.iban_generator.countries.impl.AbstractCountryMeta;
/**
 * Lendico
 * @author maher
 */
public class DEMeta extends AbstractCountryMeta {
	/**
	 * DE bank account must have bank, branch codes. sum of both = 8 account
	 * number length must = 10 other fields are not necessaries
	 */
	@Override
	public boolean isValid(BankAccount bankAccount) {
		if (!super.isValid(bankAccount))
			return false;

		if (this.sum(bankAccount.getBankNationalCode(),
				bankAccount.getBranchCode()) != 8)
			return false;
		if (this.sum(bankAccount.getAccountNumber()) != 10)
			return false;
		return true;
	}

	@Override
	public String getLeftPart(BankAccount bankAccount) {
		return new StringBuilder().append(bankAccount.getBankNationalCode())
				.append(bankAccount.getBranchCode())
				.append(bankAccount.getAccountNumber()).toString();
	}

	@Override
	public String format(String code) {
		if (code == null || "".equals(code) || code.length() != this.getChars())
			return code;
		StringBuilder buffer = new StringBuilder();
		int index = 0;
		while (code.length() - index  >= 4) {
			buffer.append(code.substring(index, index+4)).append(' ');
			index += 4;
		}
		buffer.append(code.substring(index, code.length()));
		return buffer.toString();
	}
}
