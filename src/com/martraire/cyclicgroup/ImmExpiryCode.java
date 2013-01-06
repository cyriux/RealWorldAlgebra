package com.martraire.cyclicgroup;

/**
 * Represents an IMM contract code in the format "U8"
 */
public class ImmExpiryCode {

	public static enum MonthCode {
		H, M, U, Z;
	}

	private static final int ORDER = MonthCode.values().length;

	// the integer in the corresponding cycling group of integers
	private final int ordinal;

	private ImmExpiryCode(int ordinal) {
		this.ordinal = ordinal;
	}

	public static ImmExpiryCode valueOf(String code) {
		final int year = Integer.valueOf(code.substring(code.length() - 1));
		final MonthCode monthCode = MonthCode.valueOf(code.substring(0, 1));
		final int ordinal = year * ORDER + monthCode.ordinal();
		return new ImmExpiryCode(ordinal);
	}

	@Override
	public String toString() {
		return MonthCode.values()[ordinal % ORDER].toString() + ordinal / ORDER;
	}

	public ImmExpiryCode next() {
		return new ImmExpiryCode(ordinal + 1);
	}

	public ImmExpiryCode previous() {
		return new ImmExpiryCode(ordinal - 1);
	}
}
