package com.ashok.my.learnings.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashok.my.learnings.springboot.dto.RequestObject;
import com.ashok.my.learnings.springboot.service.DateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/date-difference")
public class DateController {

	@Autowired
	DateService service;

	@GetMapping("/calculate")
	public String calculateDateDifference(@Valid @RequestBody RequestObject input) {
		return service.calculateDateDifference(input);
	}
}
