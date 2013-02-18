package com.martraire.group;

public class Reset extends BaseCommand implements NullPotent {

	protected Reset(int amount) {
		super(amount);
	}

	@Override
	public void execute(Context context) {
		context.set(amount);
	}

	@Override
	public String toString() {
		return "Reset [amount=" + amount + "]";
	}

}