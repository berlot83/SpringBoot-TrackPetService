package com.molokotech.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.molokotech.model.Horse;

@Repository
public interface HorseRepository extends MongoRepository<Horse, String>{

}
