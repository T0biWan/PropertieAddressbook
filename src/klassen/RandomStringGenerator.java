package klassen;

import java.security.SecureRandom;
import java.util.Random;

public class RandomStringGenerator {
	private static char[] VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();

	// Methode
	public String generateRandomString(int stringLänge) {
		SecureRandom randomSeed = new SecureRandom();
		Random random = new Random();
		char[] zeichen = new char[stringLänge];

		// Array füllen, mit den möglichen Zeichen
		for (int i = 0; i < stringLänge; i++) {
			if ((i % 10) == 0) {
				random.setSeed(randomSeed.nextLong());
			}
			zeichen[i] = VALID_CHARACTERS[random.nextInt(VALID_CHARACTERS.length)];
		}
		return new String(zeichen);
	}
	
	public int generateRandomInt(int maximalWert) {
		Random r = new Random();
		return r.nextInt(maximalWert);
	}
}