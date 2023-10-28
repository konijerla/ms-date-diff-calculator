package com.ashok.my.learnings.springboot.dto;

public class Date {

	private int day;
	private int month;
	private int year;

	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int compareTo(Date other) {
		if (this.year != other.year) {
			return this.year - other.year;
		}
		if (this.month != other.month) {
			return this.month - other.month;
		}
		return this.day - other.day;
	}

	public long daysSince(Date other) {
		long days1 = this.day + daysInMonthsUntil(this.month, this.year);
		long days2 = other.day + daysInMonthsUntil(other.month, other.year);
		long daysDifference = 0;

		for (int year = other.year; year < this.year; year++) {
			daysDifference += isLeapYear(year) ? 366 : 365;
		}

		return daysDifference + (days1 - days2);
	}

	private int daysInMonthsUntil(int month, int year) {
		int days = 0;
		for (int i = 1; i < month; i++) {
			days += numberOfDaysInMonth(i, year);
		}
		return days;
	}

	private int numberOfDaysInMonth(int month, int year) {
		int[] daysInMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (month == 2 && isLeapYear(year)) {
			return 29;
		}
		return daysInMonth[month];
	}

	private boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	@Override
	public String toString() {
		return String.format("%02d %02d %04d", day, month, year);
	}
}