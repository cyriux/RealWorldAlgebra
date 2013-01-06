package com.martraire.cyclicgroup;

import java.util.Comparator;

/**
 * Represents an IMM contract code in the format "U8"
 */
public class ImmExpiryCode {

	public static enum MonthCode {
		H, M, U, Z;
	}

	private static final int MONTH_ORDER = MonthCode.values().length;
	private static final int ORDER = MONTH_ORDER * 10;

	/**
	 * A comparator of ImmExpiryCode as of a given reference ImmExpiryCode
	 */
	public static class AsOfComparator implements Comparator<ImmExpiryCode> {

		private final ImmExpiryCode ref;

		public AsOfComparator(ImmExpiryCode reference) {
			this.ref = reference;
		}

		@Override
		public int compare(ImmExpiryCode code, ImmExpiryCode other) {
			return normalize(code.ordinal - ref.ordinal)
					- normalize(other.ordinal - ref.ordinal);
		}

		private int normalize(final int delta) {
			return (delta + ORDER) % ORDER;
		}

		@Override
		public String toString() {
			return "ImmExpiryCode Comparator as of " + ref;
		}

	}

	// the integer in the corresponding cycling group of integers
	private final int ordinal;

	private ImmExpiryCode(int ordinal) {
		this.ordinal = ordinal;
	}

	public static ImmExpiryCode valueOf(String code) {
		final int year = Integer.valueOf(code.substring(code.length() - 1));
		final MonthCode monthCode = MonthCode.valueOf(code.substring(0, 1));
		final int ordinal = year * MONTH_ORDER + monthCode.ordinal();
		return new ImmExpiryCode(ordinal);
	}

	public ImmExpiryCode next() {
		return roll(1);
	}

	public ImmExpiryCode previous() {
		return roll(-1);
	}

	private ImmExpiryCode roll(int steps) {
		return new ImmExpiryCode(ordinal + steps);
	}

	@Override
	public int hashCode() {
		return ordinal;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ImmExpiryCode)) {
			return false;
		}
		final ImmExpiryCode other = (ImmExpiryCode) obj;
		return ordinal == other.ordinal;
	}

	@Override
	public String toString() {
		return MonthCode.values()[ordinal % MONTH_ORDER].toString() + ordinal
				/ MONTH_ORDER;
	}
}
