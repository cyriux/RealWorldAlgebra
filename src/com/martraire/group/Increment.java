package com.martraire.group;


public final class Increment extends BaseCommand {

	public Increment(int amount) {
		super(amount);
	}

	@Override
	public void execute(Context context) {
		context.increment(amount);
	}

	@Override
	public String toString() {
		return "Increment [amount=" + amount + "]";
	}

}