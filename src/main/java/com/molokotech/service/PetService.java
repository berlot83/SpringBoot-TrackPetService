package com.molokotech.service;

import java.util.List;
import java.util.Optional;

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
	
	public Pet readPet(String id){
		Pet pet = null;
		Optional<Pet> opt = petRepository.findById(id);
		if(opt.isPresent()) {
			pet = opt.get();
		}else {
			System.out.println("Algo salió mal.");
		}
		return pet;
	}
	
	public List<Pet> readAllPets(List<Pet> list){
		return list;
	}
}
