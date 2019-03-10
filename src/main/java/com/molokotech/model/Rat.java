package com.molokotech.model;

public class Rat extends Animal {

	private String type;
	private String breed;
	private boolean bite;

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public boolean isBite() {
		return bite;
	}

	public void setBite(boolean bite) {
		this.bite = bite;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
