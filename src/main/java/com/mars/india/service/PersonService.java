package com.mars.india.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.india.exception.NotFoundException;
import com.mars.india.model.Person;
import com.mars.india.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;

   /**
    * Returns Person
    *
    * @param Person , not null
    */
	public Person createPerson(Person person) {
		return personRepository.save(person);
	}

   /**
    * Returns all persons list
    */
    public List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    /**
     * Returns Person based on id
     *
     * @param id , not null
     */
    public Person getPersonById(Integer id) {
        return personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found for id :: " + id));
    }

    /**
     * Returns true/false based on person id
     *
     * @param id , not null
     */
    public boolean deletePersonById(Integer id) {
    	personRepository.findById(id).ifPresentOrElse(Person -> personRepository.deleteById(id), 
    							() -> new NotFoundException("Person not found for id :: " + id));
		return true;
    }

    /**
     * Returns person count
     */
	public Long personsCount() {
		return personRepository.personsCount();
	}

	/**
     * Returns person based on person id, person 
     *
     * @param id , not null
     * @param Person
     */
	public Person update(Integer id, Person person) {
		personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found for id :: " + id));
		person.setId(id);
    	return personRepository.save(person);
	}

}
