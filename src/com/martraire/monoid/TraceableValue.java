package com.martraire.monoid;

import static java.lang.Math.abs;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Represents a value with two operations (addition and multiplication), where
 * each value keeps track of the operations used to calculate it.
 */
public final class TraceableValue {

	private final double value;
	private final String description;

	public TraceableValue(final double value, final String description) {
		this.value = value;
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public TraceableValue add(final TraceableValue other) {
		if (other.value == 0) {
			return this;
		}
		final String journal = description + (other.value > 0 ? " + " : " - ") + other.description + " ("
				+ formatted(abs(other.value)) + ")";
		return new TraceableValue(value + other.value, journal);
	}

	public TraceableValue multiply(final TraceableValue other) {
		if (other.value == 1) {
			return this;
		}
		final String journal = description + " * " + other.description + " (" + formatted(other.value) + ")";
		return new TraceableValue(value * other.value, journal);
	}

	private static String formatted(final double v) {
		final DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
		df.applyPattern("0.00");
		return df.format(v);
	}

	@Override
	public int hashCode() {
		return description.hashCode() ^ (int) (Double.doubleToLongBits(value) >>> 32);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TraceableValue)) {
			return false;
		}
		final TraceableValue other = (TraceableValue) obj;
		return description.equals(other.description) && abs(value - other.value) < 0.01;
	}

	@Override
	public String toString() {
		return description + ": " + value;
	}

}