package com.martraire.eggfarm;

import static com.martraire.eggfarm.Product.EGG;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class ProductionChronicleTest {

	private static final Date DAY = new Date(20130511);
	private static final Date NEXT_DAY = new Date(20130511 + 1);
	private static final Quantity ONE_EGG = new Quantity(1, EGG);

	private static final Production PROD_1 = Production.of(ONE_EGG, DAY);
	private static final Production PROD_2 = Production.of(ONE_EGG, NEXT_DAY);
	private static final Production TOTAL_PROD = Production.of(new Quantity(2, EGG), NEXT_DAY);

	@Test
	public void production_over_2_days_is_production_over_1_day_plus_another() {
		assertEquals(new ProductionChronicle(PROD_1, PROD_2),
				new ProductionChronicle(PROD_1).add(new ProductionChronicle(PROD_2)));
	}

	@Test
	public void production_over_time_is_always_chronological() {
		assertEquals(new ProductionChronicle(PROD_1, PROD_2),
				new ProductionChronicle(PROD_2).add(new ProductionChronicle(PROD_1)));
	}

	@Test
	public void cumulated_production_over_time_on_end_date() {
		assertEquals(TOTAL_PROD, new ProductionChronicle(PROD_1, PROD_2).cumulatedBy(Product.EGG));
	}

	@Test
	public void cumulated_production_of_eggs_only_over_time_on_end_date() {
		assertEquals(TOTAL_PROD, new ProductionChronicle(Production.of(new Quantity(2, Product.BREAD), NEXT_DAY),
				TOTAL_PROD).cumulatedBy(Product.EGG));
	}
}
