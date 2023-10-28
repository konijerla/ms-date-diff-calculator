package com.ashok.my.learnings.springboot.validator;

import static com.ashok.my.learnings.springboot.constants.Constants.DATE_FORMAT;
import static com.ashok.my.learnings.springboot.constants.Constants.YEAR_RANGE_MAX;
import static com.ashok.my.learnings.springboot.constants.Constants.YEAR_RANGE_MIN;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ashok.my.learnings.springboot.exception.InvalidDateException;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidDateValidator.class)
@Target({ ElementType.FIELD, ElementType.TYPE_USE, ElementType.RECORD_COMPONENT })
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidDate {
	String message() default "Invalid date supplied. Date should be in '" + DATE_FORMAT + "' format. "
			+ "And length of date should be 10 chars inclusing space. " + "And Year should be between "
			+ YEAR_RANGE_MIN + " and " + YEAR_RANGE_MAX + ". " + "Example : 25 03 2019.";

	String format() default "\\d{2} \\d{2} \\d{4}";

	int minYear() default 1800;

	int maxYear() default 2900;

	int length() default 8;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<? extends Throwable> exception() default InvalidDateException.class;

}