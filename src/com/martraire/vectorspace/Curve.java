package com.martraire.vectorspace;

import static java.lang.Math.min;

import java.util.Arrays;

public final class Curve {

	private final double[] values;

	public Curve(double... value) {
		this.values = value;
	}

	public static interface Function {
		double evaluate(double v, double w);
	}

	private final Function ADDITION = new Function() {

		@Override
		public double evaluate(double v, double w) {
			return v + w;
		}

	};

	private final Function MULTIPLICATION = new Function() {

		@Override
		public double evaluate(double v, double w) {
			return v * w;
		}

	};

	public Curve add(Curve other) {
		return zipWith(other, ADDITION);
	}

	public Curve zipWith(Curve other, final Function function) {
		double[] result = new double[min(values.length, other.values.length)];
		for (int i = 0; i < result.length; i++) {
			result[i] = function.evaluate(values[i], other.values[i]);
		}
		return new Curve(result);
	}

	public Curve times(Curve other) {
		return zipWith(other, MULTIPLICATION);
	}

	public Curve times(final double coefficient) {
		double[] result = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = values[i] * coefficient;
		}
		return new Curve(result);
	}

	public double sum() {
		double result = 0;
		for (int i = 0; i < values.length; i++) {
			result += values[i];
		}
		return result;
	}

	@Override
	public int hashCode() {
		return values.hashCode();
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
		return Arrays.equals(values, other.values);
	}

	@Override
	public String toString() {
		return Arrays.toString(values);
	}

}