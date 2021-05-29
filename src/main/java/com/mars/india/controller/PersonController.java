package com.mars.india.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mars.india.model.Person;
import com.mars.india.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@PostMapping(value = "/savePerson", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> savePerson(@RequestBody Person person) {
		return new ResponseEntity<>(personService.createPerson(person), HttpStatus.OK);
	}
	
	@GetMapping("/allPersons")
    public ResponseEntity<List<Person>> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }
	
	@PutMapping("/updatePerson/{id}")
	public ResponseEntity<Person> update(@PathVariable int id, @RequestBody Person person)  {
        return new ResponseEntity<>(personService.update(id, person), HttpStatus.OK);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deletePerson/{id}")
    public boolean deletePerson(@PathVariable Integer id) {
        return personService.deletePersonById(id);
    }
    
    @GetMapping("/personsCount")
    public Long personsCount() {
        return personService.personsCount();
    }

}
