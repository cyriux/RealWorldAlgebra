package com.martraire.group;

import java.util.ArrayList;
import java.util.List;


public enum AxiomaticOptimizer implements Optimizer {

	NONE {
		@Override
		public List<Command> optimize(List<Command> commands) {
			return commands;
		}
	},

	INSTANCE;

	@Override
	public List<Command> optimize(List<Command> commands) {
		final ArrayList<Command> filtered = new ArrayList<Command>();
		for (Command command : commands) {
			if (command instanceof NullPotent) {
				filtered.clear();
				filtered.add(command);
			} else if (command instanceof Inverse) {
				filtered.remove(((Inverse) command).reversed());
			} else {
				filtered.add(command);
			}
		}
		return filtered;
	}

}