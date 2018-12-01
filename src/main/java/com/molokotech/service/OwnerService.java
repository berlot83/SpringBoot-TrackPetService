package com.molokotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molokotech.model.Owner;
import com.molokotech.repository.OwnerRepository;

@Service
public class OwnerService {
	
	@Autowired
	OwnerRepository ownerRepository;
	
	public void createOwner(Owner owner) {
		ownerRepository.save(owner);
	}
	

}
