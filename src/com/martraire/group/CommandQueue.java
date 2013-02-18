package com.martraire.group;

import java.util.Arrays;
import java.util.List;


public final class CommandQueue implements Command {

	private final List<Command> commands;
	private final Optimizer optimizer;

	public CommandQueue(Optimizer optimizer, Command... commands) {
		this.optimizer = optimizer;
		this.commands = Arrays.asList(commands);
	}

	public CommandQueue(Optimizer optimizer, List<Command> commands) {
		this.optimizer = optimizer;
		this.commands = commands;
	}

	@Override
	public void execute(Context context) {
		for (Command command : optimizer.optimize(commands)) {
			command.execute(context);
		}
	}

	@Override
	public String toString() {
		return "CommandQueue [commands=" + commands + "]";
	}

}