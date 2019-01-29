package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.molokotech.model.Buyer;


public interface BuyerRepository  extends MongoRepository<Buyer, String>{

}
