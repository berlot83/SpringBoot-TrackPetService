package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.molokotech.model.PrepaidQR;

@Repository
public interface PrepaidQrRepository extends MongoRepository<PrepaidQR, String> {
	
	PrepaidQR findBySelledOnline();
	
}
