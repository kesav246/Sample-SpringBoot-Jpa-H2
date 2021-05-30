package com.mars.india.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	/**
	* The Constant LOGGER.
	*/
	   private static final Logger LOGGER = LogManager.getLogger(PersonController.class);
	
	@Autowired
	PersonService personService;
	

   /**
    * this method is used to save Person content
    *
    * @param Person , not null
    */
	@PostMapping(value = "/savePerson", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> savePerson(@RequestBody Person person) {
		LOGGER.trace("Entry :: savePerson in PersonController with params{}",
				person);
		return new ResponseEntity<>(personService.createPerson(person), HttpStatus.OK);
	}
	

   /**
    * this method is used to get all Persons content
    */
	@GetMapping("/allPersons")
    public ResponseEntity<List<Person>> getAllPersons() {
		LOGGER.trace("Entry :: getAllPersons in PersonController");
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }
	

   /**
    * this method is used to update Person content by id
    *
    * @param id , not null
    */
	@PutMapping("/updatePerson/{id}")
	public ResponseEntity<Person> update(@PathVariable int id, @RequestBody Person person)  {
		LOGGER.trace("Entry :: update in PersonController with params{}", id);
        return new ResponseEntity<>(personService.update(id, person), HttpStatus.OK);
    }
	

   /**
    * this method is used to get Person content by id
    *
    * @param id , not null
    */

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
    	LOGGER.trace("Entry :: getPersonById in PersonController with params{}", id);
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
    }
	

    /**
     * this method is used to delete Person content by id
     *
     * @param id , not null
     */

    @DeleteMapping("/deletePerson/{id}")
    public boolean deletePerson(@PathVariable Integer id) {
    	LOGGER.trace("Entry :: deletePerson in PersonController with params{}", id);
        return personService.deletePersonById(id);
    }
	

    /**
     * this method is used to get count of Persons
     */
    
    @GetMapping("/personsCount")
    public Long personsCount() {
    	LOGGER.trace("Entry :: personsCount in PersonController");
        return personService.personsCount();
    }

}
