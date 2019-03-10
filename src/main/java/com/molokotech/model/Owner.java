package com.molokotech.model;

import org.springframework.data.annotation.Id;

public class Owner {
	
	@Id
    private String id;
   // @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String email;
    private String ownerName;
    private String ownerLastname;
    private String residence;
    private String address;
    private String dni;
    private String telephone1;
    private String telephone2;
    private String facebook;
    private String instagram;
    private String sessionToken;
    private String lostDogToken;
    private String name;
    private Animal animal;
    private String petNameInCharge;
    private String idPrepaidQrOwned;
    private String base64Backside;
    private double latitude;
    private double longitude;
    
    
    public Owner() {
    	
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerLastname() {
		return ownerLastname;
	}

	public void setOwnerLastname(String ownerLastname) {
		this.ownerLastname = ownerLastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getLostDogToken() {
		return lostDogToken;
	}

	public void setLostDogToken(String lostDogToken) {
		this.lostDogToken = lostDogToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPetNameInCharge() {
		return petNameInCharge;
	}

	public void setPetNameInCharge(String petNameInCharge) {
		this.petNameInCharge = petNameInCharge;
	}

	public String getIdPrepaidQrOwned() {
		return idPrepaidQrOwned;
	}

	public void setIdPrepaidQrOwned(String idPrepaidQrOwned) {
		this.idPrepaidQrOwned = idPrepaidQrOwned;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public String getBase64Backside() {
		return base64Backside;
	}

	public void setBase64Backside(String base64Backside) {
		this.base64Backside = base64Backside;
	}

}
