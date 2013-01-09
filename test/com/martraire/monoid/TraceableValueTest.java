package com.martraire.monoid;

import static java.lang.Math.abs;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TraceableValueTest {

	private static final double LIBOR_VALUE = 3.25;
	private final static String TODAY = "Jan 9, 2013";

	public final class TraceableValue {

		private final double value;
		private final String description;

		public TraceableValue(final double value, final String description) {
			this.value = value;
			this.description = description;
		}

		public double getValue() {
			return value;
		}

		public String getDescription() {
			return description;
		}

		public TraceableValue add(TraceableValue other) {
			if (other.value == 0) {
				return this;
			}
			final String journal = description + (other.value > 0 ? " + " : " - ") + other.description + " ("
					+ abs(other.value) + ")";
			return new TraceableValue(value + other.value, journal);
		}

		@Override
		public int hashCode() {
			return description.hashCode() ^ (int) (Double.doubleToLongBits(value) >>> 32);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof TraceableValue)) {
				return false;
			}
			final TraceableValue other = (TraceableValue) obj;
			return description.equals(other.description)
					&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
		}

		@Override
		public String toString() {
			return description + ": " + value;
		}
	}

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
		assertEquals(new TraceableValue(3.45, "Observation on Jan 9, 2013 + Margin (0.2)"),
				observation.add(new TraceableValue(0.20, "Margin")));
	}

	@Test
	public void observation_with_negative_additive_margin() {
		final TraceableValue observation = new TraceableValue(LIBOR_VALUE, "Observation on Jan 9, 2013");
		assertEquals(new TraceableValue(3.15, "Observation on Jan 9, 2013 - Margin (0.1)"),
				observation.add(new TraceableValue(-0.10, "Margin")));
	}
}
