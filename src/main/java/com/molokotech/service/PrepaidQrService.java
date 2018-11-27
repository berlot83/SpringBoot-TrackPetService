package com.molokotech.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.PrepaidQR;
import com.molokotech.repository.PrepaidQrRepository;

@Service
public class PrepaidQrService {

	@Autowired
	public PrepaidQrRepository prepaidQrRepository;
	
	public void create(PrepaidQR prepaidQR) {
		prepaidQrRepository.save(prepaidQR);
	}
	
	public PrepaidQR findById(String id) {
		Optional<PrepaidQR> opt = prepaidQrRepository.findById(id) ;
		PrepaidQR prepaidQR = new PrepaidQR();
		if(opt.isPresent()){	
			prepaidQR = opt.get();
		}else {
			System.out.println("Algo salio mal");
		}
		return prepaidQR;
	}
	
	public PrepaidQR findBySpecialId(String id) {
		Optional<PrepaidQR> opt = prepaidQrRepository.findById(id) ;
		PrepaidQR prepaidQR = new PrepaidQR();
		if(opt.isPresent()){	
			prepaidQR = opt.get();
		}else {
			System.out.println("Algo salio mal");
		}
		return prepaidQR;
	}
	
}
