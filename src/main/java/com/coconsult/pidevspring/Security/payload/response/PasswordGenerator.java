package com.coconsult.pidevspring.Security.payload.response;


import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final int PASSWORD_LENGTH = 10;

	public static String generateNewPassword() {
		StringBuilder sb = new StringBuilder();
		Random random = new SecureRandom();

		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}
}
