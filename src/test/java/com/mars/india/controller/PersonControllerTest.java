package com.mars.india.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mars.india.model.Person;
import com.mars.india.repository.PersonRepository;
import com.mars.india.service.PersonService;

@WebMvcTest(value = PersonController.class)
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;
	
	@MockBean
	private PersonRepository personRepository;

	Person mockPerson1 = new Person(101, "Jack", "Tiger");
	Optional<Person> mockPerson2 = null;
	List<Person> mockList = new LinkedList<>();

	String examplePersonJson = "{\"id\":101,\"firstName\":\"Jack\",\"surName\":\"Tiger\"}";
	
	String examplePersonJsonArray = "{[{\"id\":101,\"firstName\":\"Jack\",\"surName\":\"Tiger\"}]}";
	
	@Test
	void testSavePerson() throws Exception {

		Mockito.when(personService.createPerson(Mockito.any(Person.class))).thenReturn(mockPerson1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/savePerson")
				.accept(MediaType.APPLICATION_JSON).content(examplePersonJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	void testGetAllPersons() throws Exception {
		Mockito.when(personService.getAllPersons()).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/allPersons")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	void testUpdate() throws Exception {
		Mockito.when(personService.update(Mockito.anyInt(), Mockito.any(Person.class))).thenReturn(mockPerson1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/updatePerson/101")
				.accept(MediaType.APPLICATION_JSON).content(examplePersonJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	void testGetPersonById() throws Exception {
		Mockito.when(personService.getPersonById(Mockito.anyInt())).thenReturn(mockPerson1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/person/101")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals(examplePersonJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	void testDeletePerson() throws Exception {
		
		Mockito.when(personRepository.findById(Mockito.anyInt())).thenReturn(mockPerson2);

		Mockito.when(personService.deletePersonById(Mockito.anyInt())).thenReturn(true);

	    verify(personService, times(0)).deletePersonById(Mockito.anyInt());
	}

	@Test
	void testPersonsCount() throws Exception {
		Mockito.when(personService.personsCount()).thenReturn(0l);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/personsCount");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(0, response.getContentLengthLong());
	}

}
