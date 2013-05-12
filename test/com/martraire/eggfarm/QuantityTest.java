package com.martraire.eggfarm;

import static com.martraire.eggfarm.Product.BREAD;
import static com.martraire.eggfarm.Product.EGG;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QuantityTest {
	@Test
	public void quantity_of_1_egg_and_another_egg_gives_2_eggs() {
		assertEquals(new Quantity(2, EGG), new Quantity(1, EGG).add(new Quantity(1, EGG)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void quantity_of_1_egg_and_something_else_makes_no_sense() {
		new Quantity(1, BREAD).add(new Quantity(1, EGG));
	}

	public void quantity_of_1_egg_by_30_chicken_gives_30_eggs() {
		new Quantity(30, EGG).add(new Quantity(1, EGG).times(30));
	}
}
