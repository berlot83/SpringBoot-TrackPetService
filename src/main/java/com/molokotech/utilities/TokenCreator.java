package com.molokotech.utilities;

import java.time.Instant;
import java.util.Random;


/* Used on sign up to insert email token to recovery pass */
public class TokenCreator {
	
	public static String createAleatoryToken() {
		Instant instant = Instant.now();
		String token = Long.toString(instant.toEpochMilli());
		Random random = new Random();
		String randomNumber = Integer.toString(random.nextInt(9) + 1);
		String tokenResult = token.concat(randomNumber);
		
		return tokenResult;
	}
	
	public static String sixCharToken() {
		String sixCharToken = TokenCreator.createAleatoryToken().substring(8, 14);
		return sixCharToken;
	}
	
	public static String createSpecialId() {
		String tokenNumberSixDigits = TokenCreator.sixCharToken();
		String regex = "ABCDEFGHIJKLMNOPQXYZSabcdefghijkmnlopqxyzs"; //Tiene que tener 41 porque sino se excede y tira indexOutBoundsExp en el random de abajo

		/* Create aleatory indexOf() abecedary to select a char, must be diferent index */
		Random random = new Random();
		int randomNumber1 = random.nextInt(41) + 1;
		int randomNumber2 = random.nextInt(41) + 1;
		int randomNumber3 = random.nextInt(41) + 1;
		
		char aleatoryChar1 = regex.charAt(randomNumber1);
		char aleatoryChar2 = regex.charAt(randomNumber2);
		char aleatoryChar3 = regex.charAt(randomNumber3);
		
		/* concat three diferent char */
		String aleatorycharToString1 = String.valueOf(aleatoryChar1).toString();
		String aleatorycharToString2 = String.valueOf(aleatoryChar2).toString();
		String aleatorycharToString3 = String.valueOf(aleatoryChar3).toString();
		
		/* concat them with aleatory token number with six numbers */
		String result = tokenNumberSixDigits.concat(aleatorycharToString1).concat(aleatorycharToString2).concat(aleatorycharToString3);
		return result;
	}
	
	public static void main(String[] args) {
		String test = TokenCreator.createSpecialId();
		System.out.println(test);
	}
}
