package com.martraire.group;


public final class Decrement implements Command, Inverse {

	private final Increment reversed;

	public Decrement(Increment reversed) {
		this.reversed = reversed;
	}

	@Override
	public Command reversed() {
		return reversed;
	}

	@Override
	public void execute(Context context) {
		context.decrement(reversed.amount);
	}

	@Override
	public String toString() {
		return "ReverseIncrement [reversed=" + reversed + "]";
	}

}