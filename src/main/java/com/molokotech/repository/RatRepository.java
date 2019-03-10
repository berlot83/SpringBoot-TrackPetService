package com.molokotech.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.molokotech.model.Rat;

public interface RatRepository extends MongoRepository<Rat, String>{

}
