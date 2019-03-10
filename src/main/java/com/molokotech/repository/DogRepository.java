package com.molokotech.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.molokotech.model.Dog;

@Repository
public interface DogRepository extends MongoRepository<Dog, String> {

}
