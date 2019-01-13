package com.molokotech.utilities;

import java.time.LocalDate;
import java.time.Period;

/* First calculate the expiration Date with LocalDate, get now and plus a month, return LocalDate */
public class Expiration {
	
	/* Preset only one month */
	public static LocalDate calculateExpirationDate() {
		LocalDate today = LocalDate.now();
		LocalDate expiration = today.plusMonths(1);
		return expiration;
	}
	
	/* Catch the expiration Date persisted on DB, no other way, from the object in DB and compare with the quantity of month authorized to use */
	public static boolean validateExpirationDate(LocalDate expiration) {
		Period period = Period.between(LocalDate.now(), expiration);
		Period periodMax = Period.ofMonths(1);
		boolean result = false;
		
		if(period.getMonths() <= periodMax.getMonths()) {
			/* TRUE authorized to use */
			System.out.println("Authorized to use = " + true);
			result = true;
		}else {
			/* FALSE unauthorized to use */
			System.out.println("Unathorized to use =" + false);
			result = false;
		}
		return result;
	}
	public static void main(String[] args) {
	LocalDate exp =	Expiration.calculateExpirationDate();
	Expiration.validateExpirationDate(exp);
	}
}
