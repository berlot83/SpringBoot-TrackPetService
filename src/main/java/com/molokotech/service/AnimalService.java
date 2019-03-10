package com.molokotech.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.Animal;
import com.molokotech.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	AnimalRepository animalRepository;

	public void createAnimal(Animal animal) {
		animalRepository.save(animal);
	}
	
	public Animal readAnimal(String id){
		Animal animal = null;
		Optional<Animal> opt = animalRepository.findById(id);
		if(opt.isPresent()) {
			animal = opt.get();
		}else {
			System.out.println("Algo salió mal.");
		}
		return animal;
	}
	
	public List<Animal> readAllAnimals(List<Animal> list){
		return list;
	}
}
