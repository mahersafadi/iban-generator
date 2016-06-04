package com.lendico.iban_generator;

/**
 * Represents the main exception in the exception hierarchy<br/>
 * Lendico
 * 
 * @author maher
 */
public class IbanException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -451241682758959407L;
	private Throwable source;

	public IbanException(String msg) {
		this.message = msg;
	}

	public IbanException(String msg, Throwable source) {
		this.message = msg;
		this.source = source;
	}

	protected String message;

	public Throwable getSource() {
		return source;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("MsgCode:[").append(this.message)
				.append("], Source:[")
				.append(source != null ? this.source.getMessage() : "-")
				.append(", Cause:")
				.append(source != null ? source.getCause() : "-").append("]")
				.toString();
	}
}
