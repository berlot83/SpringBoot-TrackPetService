package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.molokotech.model.Buyer;

@Repository
public interface BuyerRepository  extends MongoRepository<Buyer, String>{

}
