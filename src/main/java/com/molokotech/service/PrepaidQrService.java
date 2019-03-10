package com.molokotech.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.molokotech.dao.DBConnector;
import com.molokotech.model.Cat;
import com.molokotech.model.Dog;
import com.molokotech.model.Fish;
import com.molokotech.model.HamsterFishTank;
import com.molokotech.model.Horse;
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
	
	public void addTypeAnimal(String id, String typeAnimal) {
		DBConnector dbConnector = new DBConnector();
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Update update = new Update();
		update.set("typeAnimal", typeAnimal);
		dbConnector.DBConnectorMongoTemplate("dbpets").updateFirst(query, update, PrepaidQR.class);
		dbConnector.close();
	}

	public PrepaidQR updateQrServiceDog(String id, PrepaidQR prepaidQR, Dog dog, String user) {
		prepaidQR = findById(id);

		try {
			prepaidQR.setDog(dog);
			prepaidQR.setUserName(user);
			prepaidQrRepository.save(prepaidQR);
		} catch (Exception error) {
			error.printStackTrace();
			System.out.println(error.getMessage());
			System.out.println("bloque catch");
		}
		return prepaidQR;
	}

	public PrepaidQR updateQrServiceCat(String id, PrepaidQR prepaidQR, Cat cat, String user) {
		prepaidQR = findById(id);

		try {
			prepaidQR.setCat(cat);
			prepaidQR.setUserName(user);
			prepaidQrRepository.save(prepaidQR);
		} catch (Exception error) {
			error.printStackTrace();
			System.out.println(error.getMessage());
			System.out.println("bloque catch");
		}
		return prepaidQR;
	}
	
	public PrepaidQR updateQrServiceHorse(String id, PrepaidQR prepaidQR, Horse horse, String user) {
		prepaidQR = findById(id);

		try {
			prepaidQR.setHorse(horse);
			prepaidQR.setUserName(user);
			prepaidQrRepository.save(prepaidQR);
		} catch (Exception error) {
			error.printStackTrace();
			System.out.println(error.getMessage());
			System.out.println("bloque catch");
		}
		return prepaidQR;
	}
	
	public PrepaidQR updateQrServiceFishes(String id, PrepaidQR prepaidQR, Fish fish, String user) {
		prepaidQR = findById(id);

		try {
			prepaidQR.setFish(fish);
			prepaidQR.setUserName(user);
			prepaidQrRepository.save(prepaidQR);
		} catch (Exception error) {
			error.printStackTrace();
			System.out.println(error.getMessage());
			System.out.println("bloque catch");
		}
		return prepaidQR;
	}
	
	public PrepaidQR updateQrServiceHamsterFishTank(String id, PrepaidQR prepaidQR, HamsterFishTank hamsterFishTank, String user) {
		prepaidQR = findById(id);

		try {
			prepaidQR.setHamsterFishTank(hamsterFishTank);
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

	public void deletePrepaidQr(PrepaidQR prepaidQR) {
		prepaidQrRepository.delete(prepaidQR);
	}
	
	
}
