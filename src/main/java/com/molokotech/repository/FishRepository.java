package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.molokotech.model.Fish;

@Repository
public interface FishRepository extends MongoRepository<Fish, String> {

}
