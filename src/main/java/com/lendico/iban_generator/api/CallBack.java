package com.lendico.iban_generator.api;

import com.lendico.iban_generator.IbanException;

/**
 * Use this interface for as call back for generation the IBAN from the bank code. on fail is default<br/>
 * Lendico
 * 
 * @author maher
 *
 */
public interface CallBack {
	public void onsuccess(Iban iban);

	default public void onFail(IbanException ex) {
		ex.printStackTrace();
	}
}
