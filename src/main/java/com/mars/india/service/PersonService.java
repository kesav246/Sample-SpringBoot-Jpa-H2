package com.mars.india.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.india.model.Person;
import com.mars.india.repository.PersonRepository;
import com.mars.india.util.NotFoundException;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	public Person createPerson(Person person) {
		return personRepository.save(person);
	}
	
    public List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    public Person getPersonById(Integer id) {
        return personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found for id :: " + id));
    }

    public boolean deletePersonById(Integer id) {
    	personRepository.findById(id).ifPresentOrElse(Person -> personRepository.deleteById(id), 
    							() -> new NotFoundException("Person not found for id :: " + id));
		return true;
    }

	public Long personsCount() {
		return personRepository.personsCount();
	}

	public Person update(Integer id, Person person) {
		personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found for id :: " + id));
        
		person.setId(id);
    	return personRepository.save(person);
	}

}
