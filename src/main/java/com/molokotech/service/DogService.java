package com.molokotech.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.Dog;
import com.molokotech.repository.DogRepository;

@Service
public class DogService {

	@Autowired
	DogRepository dogRepository;

	public void createDog(Dog dog) {
		dogRepository.save(dog);
	}
	
}
