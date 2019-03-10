package com.molokotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.Fish;
import com.molokotech.repository.FishRepository;

@Service
public class FishService {

	@Autowired
	FishRepository fishRepository;

	public void createFish(Fish fish) {
		fishRepository.save(fish);
	}
	
}
