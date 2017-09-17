package com.rstang.util.encrypt;

import java.security.MessageDigest;

import com.rstang.util.validate.Assert;
import com.rstang.util.validate.ValidateUtils;

/**
 * Simple utility method for create MD5 sign
 *
 * @author yexiong
 */
public abstract class MD5Utils {

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/**
	 * create MD5 sign
	 * @param ip_str for create MD5 sign
	 * @return
	 */
	public static String encrypt(String ip_str) {
		return encrypt(ip_str, null);
	}
	
	/**
	 * create MD5 sign
	 * @param ip_str for sign value
	 * @param charsetName 
	 */
	public static String encrypt(String ip_str, String charsetName) {
		String result = null;
		try {
			Assert.notNull(ip_str, "Input for MD5 sign value must not be null!");
			MessageDigest md = MessageDigest.getInstance("MD5");
			if(ValidateUtils.isEmpty(charsetName)) {
				result = byteArrayToHexString(md.digest(ip_str.getBytes()));				
			} else {
				result = byteArrayToHexString(md.digest(ip_str.getBytes(charsetName)));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
