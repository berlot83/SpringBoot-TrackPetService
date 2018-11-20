package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.molokotech.model.QR;

public interface QrRepository extends MongoRepository<QR, String>{

	
}
