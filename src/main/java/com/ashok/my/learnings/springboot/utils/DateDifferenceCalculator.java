package com.ashok.my.learnings.springboot.utils;

import org.springframework.stereotype.Component;

import com.ashok.my.learnings.springboot.dto.Date;
import com.ashok.my.learnings.springboot.exception.InvalidDateException;

@Component
public class DateDifferenceCalculator {

	public long calculateDifference(String strDate1, String strDate2) {
		Date date1 = createDate(strDate1);
		Date date2 = createDate(strDate2);

		// Ensure date1 is the earliest date and date2 is the latest date
		if (date1.compareTo(date2) > 0) {
			Date temp = date1;
			date1 = date2;	
			date2 = temp;
//			throw new InvalidDateException("Date 1: '" + strDate1 + "' should be before to Date2: '" + strDate2 + "");
			return -1 * date2.daysSince(date1);

		}

		return date2.daysSince(date1);

	}

	private static Date createDate(String dateString) {
		String[] parts = dateString.trim().split(" ");
		if (parts.length != 3) {
			throw new InvalidDateException("Invalid date '" + dateString + "' supplied. ");
		}

		try {
			int day = Integer.parseInt(parts[0]);
			int month = Integer.parseInt(parts[1]);
			int year = Integer.parseInt(parts[2]);

			return new Date(day, month, year);
		} catch (NumberFormatException e) {
			throw new InvalidDateException("Invalid date '" + dateString + "' supplied. ", e);
		}
	}
}