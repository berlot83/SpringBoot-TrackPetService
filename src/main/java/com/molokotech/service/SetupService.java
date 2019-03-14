package com.molokotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.Setup;
import com.molokotech.repository.SetupRepository;
import com.molokotech.repository.UserRepository;

@Service
public class SetupService {

	@Autowired
	SetupRepository setupRepository;
	@Autowired
	UserRepository userRepositoy;
	
	public void createSetup(Setup setup) {
		setupRepository.save(setup);
	}

}
