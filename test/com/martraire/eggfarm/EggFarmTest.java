package com.martraire.eggfarm;

import static com.martraire.eggfarm.Product.EGG;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;

public class EggFarmTest {

	private static Calendar calendar(int yyyy, int mm, int dd) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		calendar.set(yyyy, mm, dd, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	private static ProductionChronicle dailyProductionOf(Product product, Calendar calendar, int... numbers) {
		final List<Production> productions = new ArrayList<Production>();
		for (int i = 0; i < numbers.length; i++) {
			productions.add(Production.of(new Quantity(numbers[i], product), calendar.getTime()));
			calendar.add(Calendar.DATE, 1);
		}
		return ProductionChronicle.of(productions);
	}

	@Test
	public void weekly_production_of_one_henhouse_of_30_chicken() {
		final Calendar may30 = calendar(2013, Calendar.MAY, 30);
		final ProductionChronicle henhouse1 = dailyProductionOf(EGG, may30, 12, 11, 9, 14, 13, 10, 8);
		final Calendar jun4 = calendar(2013, Calendar.JUNE, 4);
		final ProductionChronicle henhouse2 = dailyProductionOf(EGG, jun4, 12, 11, 9, 14, 13, 10, 8);
		assertEquals(henhouse2.times(2).cumulatedBy(EGG), henhouse1.add(henhouse2).cumulatedBy(EGG));
	}

}
