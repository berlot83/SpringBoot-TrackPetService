package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.molokotech.model.Setup;

public interface SetupRepository extends MongoRepository<Setup, String>{

}
