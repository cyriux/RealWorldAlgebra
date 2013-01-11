package com.martraire.settheory;

import static com.martraire.settheory.WorkingDayCalendars.bankHolidays;
import static com.martraire.settheory.WorkingDayCalendars.closedEveryDay;
import static com.martraire.settheory.WorkingDayCalendars.union;
import static com.martraire.settheory.WorkingDayCalendars.weekends;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.martraire.settheory.WorkingDayCalendars.WorkingDayCalendar;

public class WorkingDayCalendarTest {

	private static int date(int year, int dayOfYear) {
		return 1000 * year + dayOfYear;
	}

	private static final int NEW_YEAR = date(2013, 358);
	private static final int JAN_11 = date(2013, 11);
	private static final int JAN_13 = date(2013, 13);
	private static final int XMAS = date(2013, 0);

	private static final int SUNDAY = 7;
	private static final int SATURDAY = 6;

	@Test
	public void closed_on_every_day() {
		assertFalse(closedEveryDay().isWorkingDay(JAN_11));
	}

	@Test
	public void closed_on_one_multiple_day() {
		final WorkingDayCalendar bankHolidays = bankHolidays(XMAS, NEW_YEAR);
		assertTrue(bankHolidays.isWorkingDay(JAN_11));
		assertFalse(bankHolidays.isWorkingDay(XMAS));
		assertFalse(bankHolidays.isWorkingDay(NEW_YEAR));
	}

	@Test
	public void closed_every_weekend() {
		final WorkingDayCalendar weekends = weekends(SATURDAY, SUNDAY);
		assertTrue(weekends.isWorkingDay(JAN_11));
		assertFalse(weekends.isWorkingDay(JAN_13));
	}

	@Test
	public void closed_weekend_and_xmas() {
		final WorkingDayCalendar workingDays = union(weekends(SATURDAY, SUNDAY), bankHolidays(XMAS));
		assertTrue(workingDays.isWorkingDay(NEW_YEAR));
		assertTrue(workingDays.isWorkingDay(JAN_11));
		assertFalse(workingDays.isWorkingDay(JAN_13));
		assertFalse(workingDays.isWorkingDay(XMAS));
	}

}
