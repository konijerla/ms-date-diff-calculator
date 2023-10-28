package com.ashok.my.learnings.springboot.validator;

import static com.ashok.my.learnings.springboot.constants.Constants.DATE_FORMAT_REGEX;

import com.ashok.my.learnings.springboot.constants.Constants;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

	@Override
	public boolean isValid(String dateStr, ConstraintValidatorContext constraintValidatorContext) {
		// Check if the string matches the expected format "dd MM yyyy"
		if (!dateStr.matches(DATE_FORMAT_REGEX)) {
			return false;
		}
		// Split the string into day, month, and year
		String[] parts = dateStr.split(" ");
		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);

		// Basic checks for day, month, and year
		if (day < 1 || day > 31 
				|| month < 1 || month > 12 
				|| year < Constants.YEAR_RANGE_MIN || year > Constants.YEAR_RANGE_MAX) {
			return false;
		}

		// Additional checks for specific months (e.g., February)
		if (month == 2) {
			if (day > 29) {
				return false;
			}
			if (day == 29 && !isLeapYear(year)) {
				return false;
			}
		}

		// Additional checks for months with 30 days
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
			return false;
		}

		return true;
	}

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
}
