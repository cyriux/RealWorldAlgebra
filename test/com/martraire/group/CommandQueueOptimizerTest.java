package com.martraire.group;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CommandQueueOptimizerTest {

	private static final List<Command> optimize(final List<Command> commands) {
		return AxiomaticOptimizer.INSTANCE.optimize(commands);
	}

	private static final int result(final List<Command> commands) {
		final Context context = new Context();
		new CommandQueue(AxiomaticOptimizer.NONE, commands).execute(context);
		return context.count();
	}

	private static final int optimizedResult(final List<Command> commands) {
		final Context context = new Context();
		new CommandQueue(AxiomaticOptimizer.NONE, commands).execute(context);
		return context.count();
	}

	@Test
	public void singleCommandRemainsAsIs() {
		final List<Command> commands = Arrays.<Command> asList(new Increment(3));
		assertEquals(commands, optimize(commands));
		assertEquals(result(commands), optimizedResult(commands));
	}

	@Test
	public void oppositeCommandsCancelOut() {
		final Increment increment = new Increment(3);
		final List<Command> commands = Arrays.<Command> asList(increment, new Decrement(increment));
		assertEquals(Arrays.<Command> asList(), optimize(commands));
		assertEquals(result(commands), optimizedResult(commands));
	}

	@Test
	public void nullPotentCommandIgnoresPastCommands() {
		final Command reset = new Reset(11);
		final Command increment2 = new Increment(5);
		final List<Command> commands = Arrays.<Command> asList(new Increment(3), reset, increment2);
		assertEquals(Arrays.<Command> asList(reset, increment2), optimize(commands));
		assertEquals(result(commands), optimizedResult(commands));
	}

}
