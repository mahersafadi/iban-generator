package com.lendico.iban_generator.api;

/**
 * BankAcount used as input from the external services<br/>
 * It contains code of the country, bank national code, branch number, account
 * number.<br/>
 * Any Bank account must contain at least code, bank code and account number<br/>
 * There are different accounts types depends on the country, library will
 * filter the not needed fields internally but this class must contain all
 * optional fields<br/>
 * Lendico
 * 
 * @author maher
 *
 */
public class BankAccount {
	private String code;
	private String bankNationalCode;
	private String branchCode;;
	private String accountNumber;
	private String nationalcheckDigits;
	private String getAccountType;
	private String ownerAccountNumber;
	private String businessIdentifierCode;

	public BankAccount() {
	}

	public BankAccount(String code, String bankCode, String branchCode2,
			String accountNumber2) {
		this.code = code;
		this.bankNationalCode = bankCode;
		this.branchCode = branchCode2;
		this.accountNumber = accountNumber2;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBankNationalCode() {
		return bankNationalCode;
	}

	public void setBankNationalCode(String bankNationalCode) {
		this.bankNationalCode = bankNationalCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getNationalcheckDigits() {
		return nationalcheckDigits;
	}

	public void setNationalcheckDigits(String nationalcheckDigits) {
		this.nationalcheckDigits = nationalcheckDigits;
	}

	public String getGetAccountType() {
		return getAccountType;
	}

	public void setGetAccountType(String getAccountType) {
		this.getAccountType = getAccountType;
	}

	public String getOwnerAccountNumber() {
		return ownerAccountNumber;
	}

	public void setOwnerAccountNumber(String ownerAccountNumber) {
		this.ownerAccountNumber = ownerAccountNumber;
	}

	public String getBusinessIdentifierCode() {
		return businessIdentifierCode;
	}

	public void setBusinessIdentifierCode(String businessIdentifierCode) {
		this.businessIdentifierCode = businessIdentifierCode;
	}
}
