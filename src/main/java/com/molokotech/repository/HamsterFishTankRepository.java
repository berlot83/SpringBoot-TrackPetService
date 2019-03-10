package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.molokotech.model.HamsterFishTank;

@Repository
public interface HamsterFishTankRepository extends MongoRepository<HamsterFishTank, String> {

	
	
}
