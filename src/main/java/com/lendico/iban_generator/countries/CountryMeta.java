package com.lendico.iban_generator.countries;

import com.lendico.iban_generator.api.BankAccount;
import com.lendico.iban_generator.countries.impl.CountryMetaException;

/**
 * Each country has its own meta, to valid length, bank code items, branch code
 * items and account and so on, Meta data are set from
 * <tt>countries-metas.properties</tt> each row represents single country: data
 * in row=Code,Length, left part and right part of the IBAN, . <br/>
 * Lendicoss
 * 
 * @author maher
 *
 */
public interface CountryMeta {
	/**
	 * 
	 * @return Get the code, DE, AT, NL, .....
	 */
	public String getCode();

	/**
	 * 
	 * @return name of the country, it may used for logging purposes
	 */
	public String getName();

	/**
	 * 
	 * @return the length of IBAN for this country
	 */
	public int getChars();

	public String getFormat();

	/**
	 * it will return a runtime exception if number of items parameter is not
	 * less than 5 items
	 * 
	 * @param items
	 * @throws CountryMetaException
	 */
	public void setMeta(String[] items) throws CountryMetaException;

	/**
	 * Check if the passed parameter satisfies the the country bank account
	 * standards. in length and number of required items in the selected item
	 * 
	 * @param bankAccount
	 * @return true if all bank account items are satisfied with current country
	 *         standards
	 */
	public boolean isValid(BankAccount bankAccount);

	/**
	 * Example: DE46123456554... -> DE46 is the right part, and the let part is
	 * 123456554..., so this method gets the left part of the code. this is done
	 * by combining bank national code, branch if there is, account number and
	 * other items
	 * 
	 * @param bankAccount
	 * @return
	 */
	public String getLeftPart(BankAccount bankAccount);

	/**
	 * Used if there is a custom check sum, like Portugal and Montenegro
	 * 
	 * @return
	 */
	public boolean isCustomCheckCode();

	/**
	 * format the IBAN code in the current country format
	 * 
	 * @param code, the valid IBAN code
	 * @return 
	 */
	public String format(String code);
}
