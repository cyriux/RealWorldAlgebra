package com.martraire.eggfarm;

import static com.martraire.eggfarm.Product.EGG;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class ProductionTest {

	private final static Date TODAY = new Date(20130531);
	private final static Date YESTERDAY = new Date(20130530);

	@Test
	public void production_is_dated() {
		final Quantity quantity = new Quantity(1, EGG);
		assertEquals(new Production(quantity, TODAY), Production.of(quantity, TODAY));
		assertFalse(Production.of(quantity, YESTERDAY).equals(Production.of(quantity, TODAY)));
	}

	@Test
	public void production_is_comparable() {
		final Quantity quantity = new Quantity(1, EGG);
		final Production productionYesterday = Production.of(quantity, YESTERDAY);
		final Production productionToday = Production.of(quantity, TODAY);
		assertTrue(productionToday.compareTo(productionYesterday) > 0);
	}

}
