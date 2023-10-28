package com.ashok.my.learnings.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashok.my.learnings.springboot.dto.RequestObject;
import com.ashok.my.learnings.springboot.utils.DateDifferenceCalculator;

@Service
public class DateService {

	@Autowired
	DateDifferenceCalculator calculator;

	public DateService() {
		// TODO Auto-generated constructor stub
	}

	public String calculateDateDifference(RequestObject input) {
		long difference = calculator.calculateDifference(input.getDate1(), input.getDate2());
		return difference > 0 ? input.getDate1() + ", " + input.getDate2() + ", Difference: " + difference + " days"
				: input.getDate2() + ", " + input.getDate1() + ", Difference: " + Math.abs(difference) + " days";

	}

}
