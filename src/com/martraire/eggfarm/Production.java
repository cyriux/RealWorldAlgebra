package com.martraire.eggfarm;

import java.util.Date;

/**
 * Represents a production of a given product at a given date, e.g. a production
 * of 1 egg from a chicken for a day
 */
@ValueObject
public class Production implements Comparable<Production> {

	private final Date date;
	private final Quantity quantity;

	public static Production of(Quantity quantity, Date date) {
		return new Production(quantity, date);
	}

	public Production(Quantity quantity, Date date) {
		this.date = date;
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public Quantity getQuantity() {
		return quantity;
	}

	public Production times(int number) {
		return new Production(quantity.times(number), date);
	}

	@Override
	public int hashCode() {
		return 31 * (31 + date.hashCode()) + quantity.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Production)) {
			return false;
		}
		final Production other = (Production) obj;
		return date.equals(other.date) && quantity.equals(other.quantity);
	}

	@Override
	public int compareTo(Production other) {
		return date.compareTo(other.date);
	}

	@Override
	public String toString() {
		return quantity + " at " + date;
	}

}
