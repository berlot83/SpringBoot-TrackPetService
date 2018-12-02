package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.molokotech.model.Pet;

@Repository
public interface PetRepository extends MongoRepository<Pet, String>{

}
