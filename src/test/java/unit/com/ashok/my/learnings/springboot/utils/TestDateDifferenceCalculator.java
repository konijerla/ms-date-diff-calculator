package unit.com.ashok.my.learnings.springboot.utils;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.ashok.my.learnings.springboot.SpringbootApplication;
import com.ashok.my.learnings.springboot.exception.InvalidDateException;
import com.ashok.my.learnings.springboot.utils.DateDifferenceCalculator;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = SpringbootApplication.class)

public class TestDateDifferenceCalculator {

	@Autowired
	DateDifferenceCalculator calculator;

	@Test
	public void testValidDatesCalculateDifference() {
		String date1 = "12 12 2019";
		String date2 = "12 12 2020";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
		LocalDate localDate1 = LocalDate.parse(date1, formatter);
		LocalDate localDate2 = LocalDate.parse(date2, formatter);
		long expectedDiff = Math.abs(localDate1.toEpochDay() - localDate2.toEpochDay());
		
		long actualDiff = calculator.calculateDifference(date1, date2);
		assertTrue(expectedDiff == actualDiff);

	}
	
	@Test
	public void testSamedDatesCalculateDifference() {
		String date1 = "12 12 2019";
		String date2 = date1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
		LocalDate localDate1 = LocalDate.parse(date1, formatter);
		LocalDate localDate2 = LocalDate.parse(date2, formatter);
		long expectedDiff = localDate1.toEpochDay() - localDate2.toEpochDay();
		
		long actualDiff = calculator.calculateDifference(date1, date2);
		assertTrue(expectedDiff == actualDiff);
	}
	
	@Test
	public void testFirstIsLaterToSecondCalculateDifference() {
		String date1 = "12 12 2019";
		String date2 = "12 12 2018";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
		LocalDate localDate1 = LocalDate.parse(date1, formatter);
		LocalDate localDate2 = LocalDate.parse(date2, formatter);
		long expectedDiff = Math.abs(localDate1.toEpochDay() - localDate2.toEpochDay());
		
		long actualDiff = Math.abs(calculator.calculateDifference(date1, date2));
		assertTrue(expectedDiff == actualDiff);
	}
}
