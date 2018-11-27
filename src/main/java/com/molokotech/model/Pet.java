package com.molokotech.model;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "pets")
public class Pet {
	@Id
	private String id;
	private String petName;
	private int age;
	private String raze;
	private String clinicHistory;
	private String illness;
	private boolean medicated;
	private boolean status;
	private boolean subscription;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastVeterinaryVisit;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateLost;
	private Period periodLost;
	private String dateAntiRabicVaccine;
	private String datePolivalentVaccine;
	private String dateSextupleVaccine;
	private String dateOctupleVaccine;
	
	public Pet() {
		
	}
	
	public Pet(String petName, int age, String raze, String clinicHistory, String illness, boolean medicated, boolean status,
			boolean subscription, LocalDate lastVeterinaryVisit, LocalDate dateLost, Period periodLost, String dateAntiRabicVaccine,
			String datePolivalentVaccine, String dateSextupleVaccine, String dateOctupleVaccine) {
		super();
		this.petName = petName;
		this.age = age;
		this.raze = raze;
		this.clinicHistory = clinicHistory;
		this.illness = illness;
		this.medicated = medicated;
		this.status = status;
		this.subscription = subscription;
		this.lastVeterinaryVisit = lastVeterinaryVisit;
		this.dateLost = dateLost;
		this.periodLost = periodLost;
		this.dateAntiRabicVaccine = dateAntiRabicVaccine;
		this.datePolivalentVaccine = datePolivalentVaccine;
		this.dateSextupleVaccine = dateSextupleVaccine;
		this.dateOctupleVaccine = dateOctupleVaccine;
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
	public boolean isSubscription() {
		return subscription;
	}
	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}
	public LocalDate getDateLost() {
		return dateLost;
	}
	public void setDateLost(LocalDate dateLost) {
		this.dateLost = dateLost;
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

	public LocalDate getLastVeterinaryVisit() {
		return lastVeterinaryVisit;
	}

	public void setLastVeterinaryVisit(LocalDate lastVeterinaryVisit) {
		this.lastVeterinaryVisit = lastVeterinaryVisit;
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

}
