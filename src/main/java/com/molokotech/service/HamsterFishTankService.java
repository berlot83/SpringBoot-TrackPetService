package com.molokotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.HamsterFishTank;
import com.molokotech.repository.HamsterFishTankRepository;

@Service
public class HamsterFishTankService {

	@Autowired
	HamsterFishTankRepository hamsterFishTankRepository;

	public void createHamsterFishTank(HamsterFishTank hamsterFishTank) {
		hamsterFishTankRepository.save(hamsterFishTank);
	}
	
	
}
