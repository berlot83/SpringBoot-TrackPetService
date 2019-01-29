package com.molokotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.Buyer;
import com.molokotech.repository.BuyerRepository;

@Service
public class BuyerService {
	
	@Autowired
	BuyerRepository buyerRepository;
	
	public Buyer createBuyer(Buyer buyer) {
		return buyerRepository.save(buyer);	
	}

}
