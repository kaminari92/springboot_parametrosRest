package com.genzai.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genzai.model.Person;

@RestController
public class PersonaController {
	
	private List<Person> peopleList = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		peopleList.add(new Person("01254879-5", "DUI", "Wilmer", "Grijalva", "78451245"));
		peopleList.add(new Person("01254879-5", "DUI", "Wilber", "Vasquez", "78451245"));
		peopleList.add(new Person("01451953-2", "DUI", "Adalberto", "Vásquez", "61547852"));
		peopleList.add(new Person("00215345-7", "DUI", "Juan", "Perez", "61614540"));
		peopleList.add(new Person("00975134-9", "DUI", "Carlos", "Mojica", "70710061"));
	}
	
	//Parámetros con QueryParams y Map (para manejo de varios parámetros)
	@GetMapping("/searchPeople")
	public List<Person> searchPerson(@RequestParam Map<String, String> params){
		return getPeopleList().stream()
				.filter(p -> params.get("identification") == null || params.get("identification").equals(p.getIdentification()))
				.filter(p -> params.get("identificationType") == null || params.get("identificationType").equals(p.getIdentificationType()))
				.filter(p -> params.get("name") == null || params.get("name").equals(p.getName()))
				.filter(p -> params.get("lastName") == null || params.get("lastName").equals(p.getLastName()))
				.filter(p -> params.get("phone") == null || params.get("phone").equals(p.getPhone()))
				.collect(Collectors.toList());
	}

	public List<Person> getPeopleList() {
		return peopleList;
	}

	public void setPeopleList(List<Person> peopleList) {
		this.peopleList = peopleList;
	}

}
