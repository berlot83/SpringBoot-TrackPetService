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
	
	@Autowired
	public PrepaidQR() {
	
	}
	
	@Autowired
	public PrepaidQR(ObjectId id, String strBase64, String specialId) {
		this.id = id;
		this.strBase64 = strBase64;
		this.specialId = specialId;
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

}
