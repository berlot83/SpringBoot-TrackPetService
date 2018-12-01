package com.molokotech.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.molokotech.model.Owner;
import com.molokotech.model.Pet;
import com.molokotech.model.PrepaidQR;
import com.molokotech.model.User;
import com.molokotech.repository.PetRepository;
import com.molokotech.repository.PrepaidQrRepository;
import com.molokotech.repository.UserRepository;

@Component
@Service
public class PrepaidQrService {

	@Autowired
	PrepaidQrRepository prepaidQrRepository;

	public PrepaidQrService() {

	}

	public PrepaidQR createPrepaidQR(PrepaidQR prepaidQR) {
		return prepaidQrRepository.save(prepaidQR);
	}

	public PrepaidQR findById(String id) {
		Optional<PrepaidQR> opt = prepaidQrRepository.findById(id);
		PrepaidQR prepaidQR = new PrepaidQR();
		if (opt.isPresent()) {
			prepaidQR = opt.get();
		} else {
			System.out.println("Algo salio mal");
		}
		return prepaidQR;
	}

	public PrepaidQR findBySpecialId(String id) {
		Optional<PrepaidQR> opt = prepaidQrRepository.findById(id);
		PrepaidQR prepaidQR = new PrepaidQR();
		if (opt.isPresent()) {
			prepaidQR = opt.get();
		} else {
			System.out.println("Algo salio mal");
		}
		return prepaidQR;
	}

	public void updateQrService(String id, PrepaidQR prepaidQR, Pet pet, Owner owner, String user) {
		prepaidQR = findById(id);
		prepaidQR.setPet(pet);
		prepaidQR.setOwner(owner);
		prepaidQR.setUserName(user);
		prepaidQrRepository.save(prepaidQR);
	}
}
