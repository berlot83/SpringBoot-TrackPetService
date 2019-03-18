package com.molokotech.model;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "prepaidqr")
public class PrepaidQR {
	
	@Id
	private ObjectId id;
	private String typeAnimal;
	private String[] animalTypes = {"dog", "cat", "horse", "rat", "fish"};
	private String strBase64;
	private String sellPoint;
	private String userName;
	private User user;
	private Animal animal;
	private Dog dog;
	private Cat cat;
	private Rat rat;
	private Horse horse;
	private Fish fish;
	private HamsterFishTank hamsterFishTank;
	private Owner owner;
	private String selledOnline;
	private String resultBase64Avatar;
	private String base64Backside;
	private LocalDate expiration; /* whitout use right now */
	private String activationToken; /* whitout use right now */
	private String lastLatitude;
	private String lastLongitude;
	
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
	public PrepaidQR(String strBase64, Animal animal, Owner owner) {
		this.strBase64 = strBase64;
		this.animal = animal;
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

	public String getResultBase64Avatar() {
		return resultBase64Avatar;
	}

	public void setResultBase64Avatar(String resultBase64Avatar) {
		this.resultBase64Avatar = resultBase64Avatar;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public String getBase64Backside() {
		return base64Backside;
	}

	public void setBase64Backside(String base64Backside) {
		this.base64Backside = base64Backside;
	}

	public Fish getFish() {
		return fish;
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Horse getHorse() {
		return horse;
	}

	public void setHorse(Horse horse) {
		this.horse = horse;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	public String getTypeAnimal() {
		return typeAnimal;
	}

	public void setTypeAnimal(String typeAnimal) {
		this.typeAnimal = typeAnimal;
	}

	public String[] getAnimalTypes() {
		return animalTypes;
	}

	public void setAnimalTypes(String[] animalTypes) {
		this.animalTypes = animalTypes;
	}

	public HamsterFishTank getHamsterFishTank() {
		return hamsterFishTank;
	}

	public void setHamsterFishTank(HamsterFishTank hamsterFishTank) {
		this.hamsterFishTank = hamsterFishTank;
	}

	public String getActivationToken() {
		return activationToken;
	}

	public void setActivationToken(String activationToken) {
		this.activationToken = activationToken;
	}

	public Rat getRat() {
		return rat;
	}

	public void setRat(Rat rat) {
		this.rat = rat;
	}

	public String getLastLatitude() {
		return lastLatitude;
	}

	public void setLastLatitude(String lastLatitude) {
		this.lastLatitude = lastLatitude;
	}

	public String getLastLongitude() {
		return lastLongitude;
	}

	public void setLastLongitude(String lastLongitude) {
		this.lastLongitude = lastLongitude;
	}

}
