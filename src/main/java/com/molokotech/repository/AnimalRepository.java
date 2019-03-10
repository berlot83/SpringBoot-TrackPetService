package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.molokotech.model.Animal;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String>{

}
