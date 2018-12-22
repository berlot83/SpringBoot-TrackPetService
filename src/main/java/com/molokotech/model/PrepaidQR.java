package com.molokotech.model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "prepaidqr")
public class PrepaidQR {
	
	@Id
	private ObjectId id;
	private String strBase64;
	private String sellPoint;
	private String userName;
	private User user;
	private Pet pet;
	private Owner owner;
	private String selledOnline;
	
	@Autowired
	public PrepaidQR() {
	
	}
	
	@Autowired
	public PrepaidQR(ObjectId id, String strBase64, String userName) {
		this.id = id;
		this.strBase64 = strBase64;
		this.userName = userName;
	}
	
	@Autowired
	public PrepaidQR(String strBase64, Pet pet, Owner owner) {
		this.strBase64 = strBase64;
		this.pet = pet;
		this.owner = owner;
	}
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getStrBase64() {
		return strBase64;
	}

	public void setStrBase64(String strBase64) {
		this.strBase64 = strBase64;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
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

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSelledOnline() {
		return selledOnline;
	}

	public void setSelledOnline(String selledOnline) {
		this.selledOnline = selledOnline;
	}


}
