package unit.com.ashok.my.learnings.springboot.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ashok.my.learnings.springboot.SpringbootApplication;
import com.ashok.my.learnings.springboot.dto.RequestObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = SpringbootApplication.class)
public class DateDifferenceControllerTests {

	@Autowired
	private MockMvc mockMvc;
	private static final String PATH = "/date-difference/calculate";

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void testValidDateDifferenceCalculation() throws Exception {
		RequestObject object = new RequestObject("02 04 1990", "25 12 2020");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(object);

		MvcResult result = mockMvc.perform(get(PATH).content(requestJson) // Valid date
				.contentType(APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isOk()).andReturn();

		assertEquals("Date 1: 02 04 1990, Date 2: 25 12 2020, Difference: 11225 days",
				result.getResponse().getContentAsString());

	}

	@Test
	public void testInvalidDateDifferenceCalculation() throws Exception {
		RequestObject object = new RequestObject("31 02 2023", "25 12 2020");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(object);

		MvcResult result = mockMvc.perform(get(PATH).content(requestJson) // Invalid date
				.contentType(APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists()).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(
				"date1: Invalid date supplied. Date should be in 'dd MM yyyy' format. And length of date should be 10 chars inclusing space. And Year should be between 1900 and 2020. Example : 25 03 2019."));
	}

	@Test
	public void testOutOfRangeDateDifferenceCalculation() throws Exception {
		RequestObject object = new RequestObject("31 12 1899", "25 12 2020");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(object);

		MvcResult result = mockMvc.perform(get(PATH).content(requestJson) // out of range date
				.contentType(APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists()).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(
				"date1: Invalid date supplied. Date should be in 'dd MM yyyy' format. And length of date should be 10 chars inclusing space. And Year should be between 1900 and 2020. Example : 25 03 2019."));
	}
	
	@Test
	public void testFirstIsLaterToSecondDateDifferenceCalculation() throws Exception {
		RequestObject object = new RequestObject("26 12 2020", "25 12 2020");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(object);

		MvcResult result = mockMvc.perform(get(PATH).content(requestJson) // first date is later to second date
				.contentType(APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").exists()).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(
				"Date 1: '26 12 2020' should be before to Date2: '25 12 2020"));
	}
	
	@Test
	public void testInvalidFormatDateDifferenceCalculation() throws Exception {
		RequestObject object = new RequestObject("26/12/2020", "25 12 2020");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(object);

		MvcResult result = mockMvc.perform(get(PATH).content(requestJson) // invalid date format
				.contentType(APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists()).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(
				"on field 'date1': rejected value [26/12/2020]"));
		assertTrue(result.getResponse().getContentAsString().contains(
				"date1: Invalid date supplied. Date should be in 'dd MM yyyy' format. And length of date should be 10 chars inclusing space. And Year should be between 1900 and 2020. Example : 25 03 2019."));
	}
}
