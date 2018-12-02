package com.molokotech.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.molokotech.model.Owner;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {

}
