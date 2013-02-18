package com.martraire.group;

public final class Context {

	private int count = 0;

	public void increment(final int amount) {
		count += amount;
	}

	public void decrement(final int amount) {
		count -= amount;
	}

	public void set(final int amount) {
		count = amount;
	}

	public void reset() {
		set(0);
	}

	public int count() {
		return count;
	}

}