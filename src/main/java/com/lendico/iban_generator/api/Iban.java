package com.lendico.iban_generator.api;

/**
 * The returned item from 'generate' process. <br/>
 * Lendico
 * 
 * @author maher
 *
 */
public interface Iban {
	/**
	 * 
	 * @return the IBAN code, used on {@link CallBack#onsuccess(Iban)}
	 */
	String getCode();

	/**
	 * 
	 * @return the code to be printed in the country's ISO format, used on
	 *         {@link CallBack#onsuccess(Iban)}
	 */
	String getPrintedCode();
}
