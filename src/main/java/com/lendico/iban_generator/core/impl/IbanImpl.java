package com.lendico.iban_generator.core.impl;

import com.lendico.iban_generator.api.Iban;

/**
 * this class is responsible for holding the returned IBAN, it provides the IBAN
 * code and the printed code.<br/>
 * Lendico
 * 
 * @author maher
 */
class IbanImpl implements Iban {

	private String code;
	private String printedCode;

	IbanImpl(String code, String printedCode) {
		this.code = code;
		this.printedCode = printedCode;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getPrintedCode() {
		return this.printedCode;
	}

}
