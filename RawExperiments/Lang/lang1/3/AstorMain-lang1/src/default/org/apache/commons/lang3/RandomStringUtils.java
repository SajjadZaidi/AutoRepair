package org.apache.commons.lang3;


public class RandomStringUtils {
	private static final java.util.Random RANDOM = new java.util.Random();

	public RandomStringUtils() {
		super();
	}

	public static java.lang.String random(final int count) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, false, false);
	}

	public static java.lang.String randomAscii(final int count) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, 32, 127, false, false);
	}

	public static java.lang.String randomAlphabetic(final int count) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, true, false);
	}

	public static java.lang.String randomAlphanumeric(final int count) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, true, true);
	}

	public static java.lang.String randomNumeric(final int count) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, false, true);
	}

	public static java.lang.String random(final int count, final boolean letters, final boolean numbers) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, 0, 0, letters, numbers);
	}

	public static java.lang.String random(final int count, final int start, final int end, final boolean letters, final boolean numbers) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, start, end, letters, numbers, null, org.apache.commons.lang3.RandomStringUtils.RANDOM);
	}

	public static java.lang.String random(final int count, final int start, final int end, final boolean letters, final boolean numbers, final char... chars) {
		return org.apache.commons.lang3.RandomStringUtils.random(count, start, end, letters, numbers, chars, org.apache.commons.lang3.RandomStringUtils.RANDOM);
	}

	public static java.lang.String random(int count, int start, int end, final boolean letters, final boolean numbers, final char[] chars, final java.util.Random random) {
		if (count == 0) {
			return "";
		} else if (count < 0) {
			throw new java.lang.IllegalArgumentException((("Requested random string length " + count) + " is less than 0."));
		} 
		if ((chars != null) && ((chars.length) == 0)) {
			throw new java.lang.IllegalArgumentException("The chars array must not be empty");
		} 
		if ((start == 0) && (end == 0)) {
			if (chars != null) {
				end = chars.length;
			} else {
				if ((!letters) && (!numbers)) {
					end = java.lang.Integer.MAX_VALUE;
				} else {
					end = 'z' + 1;
					start = ' ';
				}
			}
		} else {
			if (end <= start) {
				throw new java.lang.IllegalArgumentException((((("Parameter end (" + end) + ") must be greater than start (") + start) + ")"));
			} 
		}
		final char[] buffer = new char[count];
		final int gap = end - start;
		while ((count--) != 0) {
			char ch;
			if (chars == null) {
				ch = ((char)((random.nextInt(gap)) + start));
			} else {
				ch = chars[((random.nextInt(gap)) + start)];
			}
			if (((letters && (java.lang.Character.isLetter(ch))) || (numbers && (java.lang.Character.isDigit(ch)))) || ((!letters) && (!numbers))) {
				if ((ch >= 56320) && (ch <= 57343)) {
					if (count == 0) {
						count++;
					} else {
						buffer[count] = ch;
						count--;
						buffer[count] = ((char)(55296 + (random.nextInt(128))));
					}
				} else if ((ch >= 55296) && (ch <= 56191)) {
					if (count == 0) {
						count++;
					} else {
						buffer[count] = ((char)(56320 + (random.nextInt(128))));
						count--;
						buffer[count] = ch;
					}
				} else if ((ch >= 56192) && (ch <= 56319)) {
					count++;
				} else {
					buffer[count] = ch;
				}
			} else {
				count++;
			}
		}
		return new java.lang.String(buffer);
	}

	public static java.lang.String random(final int count, final java.lang.String chars) {
		if (chars == null) {
			return org.apache.commons.lang3.RandomStringUtils.random(count, 0, 0, false, false, null, org.apache.commons.lang3.RandomStringUtils.RANDOM);
		} 
		return org.apache.commons.lang3.RandomStringUtils.random(count, chars.toCharArray());
	}

	public static java.lang.String random(final int count, final char... chars) {
		if (chars == null) {
			return org.apache.commons.lang3.RandomStringUtils.random(count, 0, 0, false, false, null, org.apache.commons.lang3.RandomStringUtils.RANDOM);
		} 
		return org.apache.commons.lang3.RandomStringUtils.random(count, 0, chars.length, false, false, chars, org.apache.commons.lang3.RandomStringUtils.RANDOM);
	}
}

