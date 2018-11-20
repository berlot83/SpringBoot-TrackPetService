package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.molokotech.model.Pet;

public interface PetRepository extends MongoRepository<Pet, String>{

}
