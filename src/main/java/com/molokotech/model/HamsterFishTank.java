package com.molokotech.model;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class HamsterFishTank {

	@Id
	private ObjectId id;
	private String housing;
	private boolean wheel;
	private int rodentQuantity;
	private String houseCleaned;
	private ArrayList<Rat> rats = new ArrayList<>(3);
	private Rat rat;

	public String getHousing() {
		return housing;
	}

	public void setHousing(String housing) {
		this.housing = housing;
	}

	public boolean isWheel() {
		return wheel;
	}

	public void setWheel(boolean wheel) {
		this.wheel = wheel;
	}

	public int getRodentQuantity() {
		return rodentQuantity;
	}

	public void setRodentQuantity(int rodentQuantity) {
		this.rodentQuantity = rodentQuantity;
	}

	public String getHouseCleaned() {
		return houseCleaned;
	}

	public void setHouseCleaned(String houseCleaned) {
		this.houseCleaned = houseCleaned;
	}

	public ArrayList<Rat> getRats() {
		return rats;
	}

	public void setRats(ArrayList<Rat> rats) {
		this.rats = rats;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Rat getRat() {
		return rat;
	}

	public void setRat(Rat rat) {
		this.rat = rat;
	}

}
