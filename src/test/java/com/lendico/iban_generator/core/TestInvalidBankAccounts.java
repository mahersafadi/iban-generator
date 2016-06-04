package com.lendico.iban_generator.core;

import org.junit.Test;

import com.lendico.iban_generator.IbanException;
import com.lendico.iban_generator.api.BankAccount;
import com.lendico.iban_generator.api.CallBack;
import com.lendico.iban_generator.api.Iban;
import com.lendico.iban_generator.factories.IBanGeneratorFactory;
import com.lendico.iban_generator.factories.IBanValidatorFactory;

public class TestInvalidBankAccounts {
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullBanlAccount(){
		IbanGenerator ibanGenerator = IBanGeneratorFactory.get();
		ibanGenerator.generate(null, new CallBack() {
			
			@Override
			public void onsuccess(Iban iban) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onFail(IbanException ex) {
				CallBack.super.onFail(ex);
				throw new IllegalArgumentException();
			}
		});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidDeutchBankAccount(){
		IbanGenerator ibanGenerator = IBanGeneratorFactory.get();
		ibanGenerator.generate(new BankAccount("DE", "11111111", "1", "12312312"), new CallBack() {
			
			@Override
			public void onsuccess(Iban iban) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onFail(IbanException ex) {
				CallBack.super.onFail(ex);
				throw new IllegalArgumentException();
			}
		});
	}
	
	@Test(expected=IbanException.class)
	public void validateInvalidIBANCode() throws IbanException{
		IbanValidator ibanValidator = IBanValidatorFactory.get();
		ibanValidator.validate("DE123123333"); 
	}
	
}
