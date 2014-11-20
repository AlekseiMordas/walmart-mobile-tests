package com.walmart.utils;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class CalendarUtils {

	public static int getCurrentMonth() {
		final Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH);
	}
	
	public static String getCurrentMonth(final int month) {
        return new DateFormatSymbols().getMonths()[month];
    }
	
}
