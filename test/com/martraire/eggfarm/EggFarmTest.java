package com.martraire.eggfarm;

import static com.martraire.eggfarm.Product.EGG;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class EggFarmTest {

	// Estimate production for the 3 coming weeks for cash management
	// Farm - Henhouse - Chicken -> Egg

	// Given a chicken lays one day each day
	// And that a henhouse hosts 30 chicken
	// And that a farm has 12 henhouses
	// When we count all the eggs over a week
	// Then the maximum total production amounts to 360 eggs

	private static final Date DAY = new Date(20130511);
	private static final Quantity ONE_EGG = new Quantity(1, EGG);
	private static final Quantity THIRTY_EGGS = new Quantity(30, EGG);

	private static final Iterable<Date> daySequence(Date startDate, int dayNumber) {
		final List<Date> days = new ArrayList<Date>();
		// FUNKY dates calculations for testing LOL
		for (long i = 0; i < dayNumber; i++) {
			days.add(new Date(startDate.getTime() + i));
		}
		return days;
	}

	@Test
	public void weekly_production_of_one_henhouse_of_30_chicken() {
		final ProductionChronicle individualProduction = ProductionChronicle.constantProductionOf(ONE_EGG,
				daySequence(DAY, 7));
		final ProductionChronicle henhouseProduction = individualProduction.times(30);
		assertEquals(7, henhouseProduction.size());
		assertEquals(ProductionChronicle.constantProductionOf(THIRTY_EGGS, daySequence(DAY, 7)), henhouseProduction);
	}
}
