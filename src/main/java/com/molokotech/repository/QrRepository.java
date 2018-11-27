package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.molokotech.model.QR;

@Repository
public interface QrRepository extends MongoRepository<QR, String>{

	
}
