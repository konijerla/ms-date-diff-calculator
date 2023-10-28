package com.ashok.my.learnings.springboot.dto;

import com.ashok.my.learnings.springboot.constants.Constants;
import com.ashok.my.learnings.springboot.validator.ValidDate;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class RequestObject {

	@NotEmpty(message = "Date can not be null or empty.")
	@ValidDate(format = Constants.DATE_FORMAT_REGEX, length = 10, minYear = Constants.YEAR_RANGE_MIN, maxYear = Constants.YEAR_RANGE_MAX)
	private String date1;

	@NotEmpty(message = "Date can not be null or empty.")
	@ValidDate(format = Constants.DATE_FORMAT_REGEX, length = 10, minYear = Constants.YEAR_RANGE_MIN, maxYear = Constants.YEAR_RANGE_MAX)
	private String date2;

}
