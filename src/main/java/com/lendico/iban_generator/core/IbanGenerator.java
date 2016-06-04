package com.lendico.iban_generator.core;

import com.lendico.iban_generator.IbanException;
import com.lendico.iban_generator.api.BankAccount;
import com.lendico.iban_generator.api.CallBack;
import com.lendico.iban_generator.api.Iban;

/**
 * The {@link Iban} generator, accespts a {@link BankAccount} and return the
 * {@link CallBack#onsuccess(Iban)} if it is generated correctly or
 * {@link CallBack#onFail(IbanException)} when it contains problems Lendico
 * 
 * @author maher
 */
public interface IbanGenerator {

	void generate(BankAccount bankAccount, CallBack callBack);

}
