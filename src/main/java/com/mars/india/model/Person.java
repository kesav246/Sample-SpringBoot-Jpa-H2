package com.mars.india.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	@Id
	@Column(name = "ID")
	Integer id;
	@Column(name = "FirstName")
	String firstName;
	@Column(name = "SurName")
	String surName;
}
