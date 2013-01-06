package com.martraire.cyclicgroup;

/**
 * Represents an IMM contract code in the format "U8"
 */
public class ImmExpiryCode {

	private String year;
	private String monthCode;

	public ImmExpiryCode(String digit, String monthCode) {
		this.year = digit;
		this.monthCode = monthCode;
	}

	public static ImmExpiryCode valueOf(String code) {
		final String digit = code.substring(code.length() - 1);
		final String monthCode = code.substring(0, 1);
		return new ImmExpiryCode(digit, monthCode);
	}

	@Override
	public String toString() {
		return monthCode + year;
	}
}
