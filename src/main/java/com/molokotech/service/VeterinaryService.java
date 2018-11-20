package com.molokotech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molokotech.model.Veterinary;
import com.molokotech.repository.VeterinaryRepository;

@Service
public class VeterinaryService{
	
	@Autowired
	VeterinaryRepository veterinaryRepository;
	
	public void saveVeterinary(Veterinary veterinary) {
		veterinaryRepository.save(veterinary);
	}
	
	public List<Veterinary> readAllVeterinary(List<Veterinary> veterinary) {
		return veterinaryRepository.findAll();
	}
	
	public Optional<Veterinary> readVeterinary(String id){
		return veterinaryRepository.findById(id);
	}
	
	public boolean ifExistVeterinary(String id) {
		return veterinaryRepository.existsById(id);
	}
	

}
