package com.molokotech.model;

import java.time.Period;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pets")
public class Pet {

	@Id
	private String id;
	private String petName;
	private String sex;
	private int age;
	private float weight;
	private String raze;
	private String clinicHistory;
	private String illness;
	private boolean medicated;
	private boolean status;
	private Period periodLost;
	private String dateAntiRabicVaccine;
	private String datePolivalentVaccine;
	private String dateSextupleVaccine;
	private String dateOctupleVaccine;
	private float quantityFood;
	private String foodBrand;
	private boolean neuteredPet;
	private boolean pregnant;
	private String coockedFood;
	private String lastVeterinaryVisit;
	private String videoUrl;
	
	public Pet() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRaze() {
		return raze;
	}

	public void setRaze(String raze) {
		this.raze = raze;
	}

	public String getClinicHistory() {
		return clinicHistory;
	}

	public void setClinicHistory(String clinicHistory) {
		this.clinicHistory = clinicHistory;
	}

	public String getIllness() {
		return illness;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}

	public boolean isMedicated() {
		return medicated;
	}

	public void setMedicated(boolean medicated) {
		this.medicated = medicated;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Period getPeriodLost() {
		return periodLost;
	}

	public void setPeriodLost(Period periodLost) {
		this.periodLost = periodLost;
	}

	public String getDateAntiRabicVaccine() {
		return dateAntiRabicVaccine;
	}

	public void setDateAntiRabicVaccine(String dateAntiRabicVaccine) {
		this.dateAntiRabicVaccine = dateAntiRabicVaccine;
	}

	public String getDatePolivalentVaccine() {
		return datePolivalentVaccine;
	}

	public void setDatePolivalentVaccine(String datePolivalentVaccine) {
		this.datePolivalentVaccine = datePolivalentVaccine;
	}

	public String getDateSextupleVaccine() {
		return dateSextupleVaccine;
	}

	public void setDateSextupleVaccine(String dateSextupleVaccine) {
		this.dateSextupleVaccine = dateSextupleVaccine;
	}

	public String getDateOctupleVaccine() {
		return dateOctupleVaccine;
	}

	public void setDateOctupleVaccine(String dateOctupleVaccine) {
		this.dateOctupleVaccine = dateOctupleVaccine;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getQuantityFood() {
		return quantityFood;
	}

	public void setQuantityFood(float quantityFood) {
		this.quantityFood = quantityFood;
	}

	public String getFoodBrand() {
		return foodBrand;
	}

	public void setFoodBrand(String foodBrand) {
		this.foodBrand = foodBrand;
	}

	public boolean isNeuteredPet() {
		return neuteredPet;
	}

	public void setNeuteredPet(boolean neuteredPet) {
		this.neuteredPet = neuteredPet;
	}

	public String getCoockedFood() {
		return coockedFood;
	}

	public void setCoockedFood(String coockedFood) {
		this.coockedFood = coockedFood;
	}

	public boolean isPregnant() {
		return pregnant;
	}

	public void setPregnant(boolean pregnant) {
		this.pregnant = pregnant;
	}

	public String getLastVeterinaryVisit() {
		return lastVeterinaryVisit;
	}

	public void setLastVeterinaryVisit(String lastVeterinaryVisit) {
		this.lastVeterinaryVisit = lastVeterinaryVisit;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}


}
