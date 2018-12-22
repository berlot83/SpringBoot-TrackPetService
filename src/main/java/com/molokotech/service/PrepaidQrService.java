package com.molokotech.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.molokotech.model.Owner;
import com.molokotech.model.Pet;
import com.molokotech.model.PrepaidQR;
import com.molokotech.repository.PrepaidQrRepository;

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

	public PrepaidQR updateQrService(String id, PrepaidQR prepaidQR, Pet pet, Owner owner, String user) {
		prepaidQR = findById(id);

		try {
			prepaidQR.setPet(pet);
			prepaidQR.setOwner(owner);
			prepaidQR.setUserName(user);
			prepaidQrRepository.save(prepaidQR);
		} catch (Exception error) {
			error.printStackTrace();
			System.out.println(error.getMessage());
			System.out.println("bloque catch");
		}
		return prepaidQR;
	}

	public List<PrepaidQR> findAllPrepaidQR() {
		return prepaidQrRepository.findAll();
	}

}
