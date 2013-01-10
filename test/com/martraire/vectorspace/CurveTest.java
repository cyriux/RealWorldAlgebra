package com.martraire.vectorspace;

import static org.junit.Assert.*;

import org.junit.Test;

public class CurveTest {

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
		assertEquals(new Curve(11, 7), new Curve(11, 7).add(new Curve(0, 0)));
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
	public void price_of_financial_instrument() {
		final Curve cashflows = new Curve(11, 12);
		final Curve discountFactors = new Curve(1, 0.5);
		final Curve discountedCashflows = cashflows.times(discountFactors);
		final double position = 1000;
		final Curve scaledCashflows = discountedCashflows.times(position);
		final double price = scaledCashflows.sum();
		assertEquals(17000, price, 0.1);
	}

}
