package com.supeyou.crudie.impl.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * This class provides a way to hash a string. In particular this class can be used to save passwords in databases without giving the opportunity to reconstruct the plain text password from the saved String.
 * 
 * For usage see main() method.
 * 
 * @author theile
 * 
 */
public class HashString {

	private final String masterPassword;

	public HashString(String masterPassword) {

		this.masterPassword = masterPassword;

	}

	/**
	 * 
	 * see methodName
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public String hashString(String password) throws Exception {
		String hashword = null;
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update((password + masterPassword).getBytes());
		BigInteger hash = new BigInteger(1, md5.digest());
		hashword = hash.toString(16);
		return hashword;
	}

	/**
	 * Die main-Methode demonstriert die Funktionsweise.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Test ist bestanden, wenn es keine false-Ausgaben gibt:");
		String masterPassword = "myMasterPassword";
		String passwordInput1 = "myPassword123";

		String hashValue = new HashString(masterPassword).hashString(passwordInput1);
		System.out.println(hashValue);

		String passwordInput2 = "myPassword123";

		System.out.println(hashValue.equals(new HashString(masterPassword).hashString(passwordInput2)));

		System.out.println(!hashValue.equals(new HashString(masterPassword + "asdf").hashString(passwordInput2)));
		System.out.println(!hashValue.equals(new HashString(masterPassword).hashString(passwordInput2 + "asdf")));

	}
}
