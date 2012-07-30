/*
 * Copyright 2005 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package www.zt.com.util;

import java.security.SecureRandom;

/**
 * Implementation of the RandomStringGenerator that allows you to define the length of the random part.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.7 $ $Date: 2005/03/24 05:03:00 $
 * @since 3.0
 */
public final class RandomStringGenerator {

	/** The array of printable characters to be used in our random string. */
	private static final char[] PRINTABLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345679".toCharArray();

	/** The default maximum length. */
	private static final int DEFAULT_MAX_RANDOM_LENGTH = 20;

	/** An instance of secure random to ensure randomness is secure. */
	private SecureRandom randomizer = new SecureRandom();

	/** The maximum length the random string can be. */
	private final int maximumRandomLength;

	public RandomStringGenerator() {
		this.maximumRandomLength = DEFAULT_MAX_RANDOM_LENGTH;
	}

	public RandomStringGenerator(final int maxRandomLength) {
		this.maximumRandomLength = maxRandomLength;
	}

	public int getMinLength() {
		return this.maximumRandomLength;
	}

	public int getMaxLength() {
		return this.maximumRandomLength;
	}

	public synchronized String getNewString() {
		final byte[] random = new byte[this.maximumRandomLength];

		this.randomizer.nextBytes(random);

		return convertBytesToString(random);
	}

	private String convertBytesToString(final byte[] random) {
		final char[] output = new char[random.length];
		for (int i = 0; i < random.length; i++) {
			final int index = Math.abs(random[i] % PRINTABLE_CHARACTERS.length);
			output[i] = PRINTABLE_CHARACTERS[index];
		}

		return new String(output);
	}
}
