package com.martraire.monoid;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TraceableValueTest {

	private static final double LIBOR_VALUE = 3.25;
	private final static String TODAY = "Jan 9, 2013";

	@Test
	public void original_observation_is_simply_timestamped() {
		final TraceableValue observation = new TraceableValue(LIBOR_VALUE, "Observation on " + TODAY);
		assertEquals(3.25, observation.getValue(), 0.001);
		assertEquals("Observation on Jan 9, 2013", observation.getDescription());
		assertEquals("Observation on Jan 9, 2013: 3.25", observation.toString());
		assertEquals(new TraceableValue(3.25, "Observation on Jan 9, 2013"), observation);
	}

	@Test
	public void observation_with_zero_additive_margin() {
		final TraceableValue observation = new TraceableValue(LIBOR_VALUE, "Observation on Jan 9, 2013");
		assertEquals(new TraceableValue(3.25, "Observation on Jan 9, 2013"),
				observation.add(new TraceableValue(0, "Margin")));
	}

	@Test
	public void observation_with_positive_additive_margin() {
		final TraceableValue observation = new TraceableValue(LIBOR_VALUE, "Observation on Jan 9, 2013");
		assertEquals(new TraceableValue(3.45, "Observation on Jan 9, 2013 + Margin (0.20)"),
				observation.add(new TraceableValue(0.20, "Margin")));
	}

	@Test
	public void observation_with_negative_additive_margin() {
		final TraceableValue observation = new TraceableValue(LIBOR_VALUE, "Observation on Jan 9, 2013");
		assertEquals(new TraceableValue(3.15, "Observation on Jan 9, 2013 - Margin (0.10)"),
				observation.add(new TraceableValue(-0.10, "Margin")));
	}

	@Test
	public void observation_with_two_successive_margins() {
		final TraceableValue observation = new TraceableValue(LIBOR_VALUE, "Observation on Jan 9, 2013");
		final TraceableValue margin1 = new TraceableValue(0.25, "Margin1");
		final TraceableValue margin2 = new TraceableValue(-0.10, "Margin2");
		assertEquals(new TraceableValue(3.4, "Observation on Jan 9, 2013 + Margin1 (0.25) - Margin2 (0.10)"),
				observation.add(margin1).add(margin2));
	}

	@Test
	public void observation_with_unit_coefficient() {
		final TraceableValue observation = new TraceableValue(LIBOR_VALUE, "Observation on Jan 9, 2013");
		assertEquals(new TraceableValue(3.25, "Observation on Jan 9, 2013"),
				observation.multiply(new TraceableValue(1, "Leverage")));
	}

	@Test
	public void observation_with_coefficient_two() {
		final TraceableValue observation = new TraceableValue(LIBOR_VALUE, "Observation on Jan 9, 2013");
		assertEquals(new TraceableValue(6.50, "Observation on Jan 9, 2013 * Leverage (2.00)"),
				observation.multiply(new TraceableValue(2, "Leverage")));
	}

	@Test
	public void observation_with_negative_coefficient_and_additive_margin() {
		final TraceableValue observation = new TraceableValue(LIBOR_VALUE, "Observation on Jan 9, 2013");
		assertEquals(new TraceableValue(0.85, "Observation on Jan 9, 2013 * Reverse (-1.00) + Margin (4.10)"),
				observation.multiply(new TraceableValue(-1, "Reverse")).add(new TraceableValue(4.10, "Margin")));
	}
}
