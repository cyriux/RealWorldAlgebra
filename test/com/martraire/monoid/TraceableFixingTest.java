package com.martraire.monoid;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TraceableFixingTest {

	private static final double LIBOR_VALUE = 3.25;
	private final static String TODAY = "Jan 9, 2013";

	public final class FixingValue {

		private final double value;
		private final String description;

		public FixingValue(final double value, final String description) {
			this.value = value;
			this.description = description;
		}

		public double getValue() {
			return value;
		}

		public String getDescription() {
			return description;
		}

		@Override
		public String toString() {
			return description + ": " + value;
		}

	}

	@Test
	public void original_observation_is_simply_timestamped() {
		final FixingValue observation = new FixingValue(LIBOR_VALUE,
				"Observation on " + TODAY);
		assertEquals(3.25, observation.getValue(), 0.001);
		assertEquals("Observation on Jan 9, 2013", observation.getDescription());
		assertEquals("Observation on Jan 9, 2013: 3.25", observation.toString());
	}
}
