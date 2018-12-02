package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.molokotech.model.Veterinary;

@Repository
public interface VeterinaryRepository extends MongoRepository<Veterinary, String>{

}
