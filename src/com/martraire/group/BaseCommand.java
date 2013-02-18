package com.martraire.group;

public abstract class BaseCommand implements Command {
	protected final int amount;

	protected BaseCommand(int amount) {
		this.amount = amount;
	}

}