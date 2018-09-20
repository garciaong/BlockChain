package com.dumb.blockchain.component;

import java.security.MessageDigest;

public class HashManager {

	private static final String HASH_TYPE = "SHA-256";
	private static final String ENCODING = "UTF-8"; 
	
	public static String applySha256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_TYPE);
			// Applies sha256 to our input,
			byte[] hash = digest.digest(input.getBytes(ENCODING));
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
