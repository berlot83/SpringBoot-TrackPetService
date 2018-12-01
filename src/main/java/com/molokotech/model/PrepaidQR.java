package com.molokotech.model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "prepaidqr")
public class PrepaidQR {
	
	@Id
	private ObjectId id;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String strBase64;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String specialId;
	private String sellPoint;
	private String userName;
	private User user;
	private Pet pet;
	private Owner owner;
	
	@Autowired
	public PrepaidQR() {
	
	}
	
	@Autowired
	public PrepaidQR(ObjectId id, String strBase64, String specialId, String sellPoint) {
		this.id = id;
		this.strBase64 = strBase64;
		this.specialId = specialId;
		this.sellPoint = sellPoint;
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
	public String getSpecialId() {
		return specialId;
	}
	public void setSpecialId(String specialId) {
		this.specialId = specialId;
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


}
