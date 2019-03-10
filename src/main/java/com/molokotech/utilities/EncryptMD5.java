package com.molokotech.utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptMD5 {

	public String encryptToMD5(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(data.toLowerCase().trim().getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.toString());
		return data;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		EncryptMD5 md5 = new EncryptMD5();
		md5.encryptToMD5("jaggerloco@hotmail.com");
	}

}
