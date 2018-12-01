package com.molokotech.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.molokotech.model.Owner;


public interface OwnerRepository extends MongoRepository<Owner, String> {

}
