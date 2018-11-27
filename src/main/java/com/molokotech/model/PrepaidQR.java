package com.molokotech.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "prepaidqr")
public class PrepaidQR {
	
	@Id
	private String id;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String strBase64;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String specialId;
	private User user;
	
	public PrepaidQR() {
	
	}
	
	public PrepaidQR(String id, String strBase64, String specialId, User user) {
		this.id = id;
		this.strBase64 = strBase64;
		this.specialId = specialId;
		this.user = user;
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
	public String getSpecialId() {
		return specialId;
	}
	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
