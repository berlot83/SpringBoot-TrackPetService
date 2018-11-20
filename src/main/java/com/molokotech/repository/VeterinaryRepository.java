package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.molokotech.model.Veterinary;

public interface VeterinaryRepository extends MongoRepository<Veterinary, String>{

}
