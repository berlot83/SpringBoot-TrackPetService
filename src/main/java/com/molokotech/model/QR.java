package com.molokotech.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.molokotech.utilities.TokenCreator;

@Document(collection = "qr")
public class QR {

	@Id
	private String id;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String strBase64;
	private String specialId;
	private User user;
	private Pet pet;
	private Veterinary veterinary;
	private boolean prePaid;
	
	public QR() {
		
	}
	
	public QR(User user, Pet pet, Veterinary veterinary, String strBase64) {
		this.user = user;
		this.pet = pet;
		this.veterinary = veterinary;
		this.strBase64 = strBase64;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStrBase64() {
		return strBase64;
	}

	public void setStrBase64(String strBase64) {
		this.strBase64 = strBase64;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public String getSpecialId() {
		return specialId;
	}

	public void setSpecialId() {
		String specialId = TokenCreator.createSpecialId();
		this.specialId = specialId;
	}

	public Veterinary getVeterinary() {
		return veterinary;
	}

	public void setVeterinary(Veterinary veterinary) {
		this.veterinary = veterinary;
	}

	public boolean isPrePaid() {
		return prePaid;
	}

	public void setPrePaid(boolean prePaid) {
		this.prePaid = prePaid;
	}
	
}
