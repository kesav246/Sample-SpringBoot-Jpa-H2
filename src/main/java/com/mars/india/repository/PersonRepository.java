package com.mars.india.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mars.india.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>{
	@Query("SELECT COUNT(p) FROM Person p")
	long personsCount();
}
