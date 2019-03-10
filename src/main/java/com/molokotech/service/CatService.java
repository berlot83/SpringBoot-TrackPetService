package com.molokotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molokotech.model.Cat;
import com.molokotech.repository.CatRepository;

@Service
public class CatService {
	
	@Autowired
	CatRepository catRepository;

	public void createCat(Cat cat) {
		catRepository.save(cat);
	}

}
