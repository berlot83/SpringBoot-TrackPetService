package com.molokotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.Horse;
import com.molokotech.repository.HorseRepository;

@Service
public class HorseService {

	@Autowired
	HorseRepository horseRepository;

	public void createHorse(Horse horse) {
		horseRepository.save(horse);
	}
	
}
