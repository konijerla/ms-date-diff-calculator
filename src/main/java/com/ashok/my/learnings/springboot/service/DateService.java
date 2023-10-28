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
		return "Date 1: " + input.getDate1() + ", Date 2: " + input.getDate2() + ", Difference: "
				+ calculator.calculateDifference(input.getDate1(), input.getDate2()) + " days";

	}

}
