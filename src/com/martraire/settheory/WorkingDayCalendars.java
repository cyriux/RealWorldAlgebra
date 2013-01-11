package com.martraire.settheory;

import java.util.Arrays;

public final class WorkingDayCalendars {

	public static final WorkingDayCalendar CLOSED = new WorkingDayCalendar() {

		@Override
		public boolean isWorkingDay(int day) {
			return false;
		}
	};

	public static final WorkingDayCalendar OPEN = new WorkingDayCalendar() {

		@Override
		public boolean isWorkingDay(int day) {
			return true;
		}
	};

	/**
	 * Represents the working days for a particular calendar
	 */
	public interface WorkingDayCalendar {
		boolean isWorkingDay(int day);
	}

	public static WorkingDayCalendar closedEveryDay() {
		return CLOSED;
	}

	public static WorkingDayCalendar openEveryDay() {
		return OPEN;
	}

	public static WorkingDayCalendar bankHolidays(final int... daysOff) {
		return new WorkingDayCalendar() {

			@Override
			public boolean isWorkingDay(int d) {
				Arrays.sort(daysOff);
				return Arrays.binarySearch(daysOff, d) < 0;
			}
		};
	}

	public static WorkingDayCalendar weekends(final int weekend1, final int weekend2) {
		return new WorkingDayCalendar() {

			@Override
			public boolean isWorkingDay(int d) {
				return !isWeekEnd(weekend1, weekend2, d);
			}

			private final boolean isWeekEnd(final int weekend1, final int weekend2, int d) {
				final int dayOfYear = d % 1000;
				// Assuming year starts on Monday...
				final int dayOfWeek = dayOfYear % 7;
				return dayOfWeek == weekend1 || dayOfWeek == weekend2;
			}
		};
	}

	public static WorkingDayCalendar union(final WorkingDayCalendar... workingDays) {
		return new WorkingDayCalendar() {

			@Override
			public boolean isWorkingDay(int day) {
				for (WorkingDayCalendar wd : workingDays) {
					if (!wd.isWorkingDay(day)) {
						return false;
					}
				}
				return true;
			}
		};
	}

}