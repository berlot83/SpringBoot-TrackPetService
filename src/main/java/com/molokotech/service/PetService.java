package com.molokotech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molokotech.model.Pet;
import com.molokotech.repository.PetRepository;

@Service
public class PetService {
	
	@Autowired
	PetRepository petRepository;

	public void createPet(Pet pet) {
		petRepository.save(pet);
	}
	
	public List<Pet> readAllPets(List<Pet> list){
		return list;
	}
}
