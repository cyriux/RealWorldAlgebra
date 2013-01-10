package com.martraire.vectorspace;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.martraire.vectorspace.CurveTest.Curve;

public class CurveTest {

	public static final class Curve {
		public static final Curve NEUTRAL_ELEMENT = new Curve(0, 0);

		private final double[] value;

		public Curve(double... value) {
			this.value = value;
		}

		public Curve add(Curve other) {
			double[] result = new double[value.length];
			for (int i = 0; i < value.length; i++) {
				result[i] = value[i] + other.value[i];
			}
			return new Curve(result);
		}

		public Curve times(Curve other) {
			double[] result = new double[value.length];
			for (int i = 0; i < value.length; i++) {
				result[i] = value[i] * other.value[i];
			}
			return new Curve(result);
		}

		public Curve times(final double coefficient) {
			double[] result = new double[value.length];
			for (int i = 0; i < value.length; i++) {
				result[i] = value[i] * coefficient;
			}
			return new Curve(result);
		}

		public double sum() {
			double result = 0;
			for (int i = 0; i < value.length; i++) {
				result += value[i];
			}
			return result;
		}

		@Override
		public int hashCode() {
			return value.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Curve)) {
				return false;
			}
			final Curve other = (Curve) obj;
			return Arrays.equals(value, other.value);
		}

		@Override
		public String toString() {
			return Arrays.toString(value);
		}

	}

	@Test
	public void scalar_addition() {
		assertEquals(new Curve(15), new Curve(11).add(new Curve(4)));
	}

	@Test
	public void vector_addition() {
		assertEquals(new Curve(15, 16), new Curve(11, 7).add(new Curve(4, 9)));
	}

	@Test
	public void vector_addition_on_neutral_element() {
		assertEquals(new Curve(11, 7), new Curve(11, 7).add(Curve.NEUTRAL_ELEMENT));
	}

	@Test
	public void scalar__scalar_scaling() {
		assertEquals(new Curve(44), new Curve(11).times(new Curve(4)));
	}

	@Test
	public void scalar_scaling_on_neutral_element2() {
		assertEquals(new Curve(5, 7), new Curve(5, 7).times(new Curve(1, 1)));
	}

	@Test
	public void scalar_scaling() {
		assertEquals(new Curve(10, 14), new Curve(5, 7).times(2));
	}

	@Test
	public void price_of_bond() {
		final Curve cashflows = new Curve(11, 12);
		final Curve discountFactors = new Curve(1, 0.5);
		final Curve discountedCashflows = cashflows.times(discountFactors);
		final double position = 1000;
		final Curve scaledCashflows = discountedCashflows.times(position);
		final double price = scaledCashflows.sum();
		assertEquals(17000, price, 0.1);
	}

}
