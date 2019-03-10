package com.molokotech.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.molokotech.model.Cat;

@Repository
public interface CatRepository extends MongoRepository<Cat, String> {

}
