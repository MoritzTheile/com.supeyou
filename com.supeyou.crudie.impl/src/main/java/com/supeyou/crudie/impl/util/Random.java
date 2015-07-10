package com.supeyou.crudie.impl.util;

public class Random {

	/**
	 * 
	 * @return random Hexa-Number of given length as String e.g. 23639ea34f89323ac
	 * 
	 */
	public static String randomKey(int length) {

		String result = null;

		try {

			String randomString = ("" + System.nanoTime()) + (Math.random() + System.currentTimeMillis()) + "" + ("" + System.nanoTime());

			result = new HashString("egalWelcherWert").hashString(randomString);

		} catch (Exception e) {

			// should never happen
			throw new RuntimeException(e.getMessage());

		}

		if (length > 0 && result.length() > length) {

			result = result.substring(0, length);

		}

		return result;

	}

	/**
	 * Ergebnis:
	 * 
	 * 7c1642 65e04ab3 22d0126485a4aa636113aa4cc201fbe8 8fa1a2277709a32e094b6acb176345b2
	 * 
	 */
	public static void main(String arg[]) {

		System.out.println(Random.randomKey(6));

		System.out.println(Random.randomKey(8));

		System.out.println(Random.randomKey(100));

		System.out.println(Random.randomKey(-100));

	}
}