/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  copyright (C) 2012 nambi sankaran.
 */

package org.runway.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jsoup.Jsoup;
import org.springframework.security.core.codec.Base64;

public class TextUtils {

	public static String convertNewLineToBreak(String text) {

		String textChg = text.replaceAll("\n", "<br/>");

		return textChg;
	}

	public static boolean isEmpty(String value) {
		boolean result = false;

		if (value == null || value.trim().equals("")) {
			result = true;
		}

		return result;
	}

	public static String getSubStringOfLength(String value, int len) {

		String result = null;
		if (isEmpty(value)) {
			result = value;
		} else {

			if (value.length() <= len) {
				result = value;
			} else {
				result = value.substring(0, len);
			}
		}

		return result;
	}

	public static String getUrlFromString(String str) {
		String result = null;
		str = str.replaceAll("[/\\\\]+", "\\" + "-");
		result = str.replaceAll("\\s", "-");
		return result;
	}

	public static String extractTextFromHtml(String htmlStr) {
		return Jsoup.parse(htmlStr).text();
	}

	public static boolean isValidEmail(String email) {
		boolean result = false;
		if (email.length() > 2 && email.contains("@") && email.contains(".")) {
			result = true;
		}
		return result;
	}

	/**
	 * Generates MD5 for the text passed
	 * @param text
	 * @return
	 */
	public static String genMD5(String text) {
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(text.getBytes(), 0, text.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {// return same text if there was
												// an error
			e.printStackTrace();
			return text;
		}
	}

	/**
	 * Generates Hash for the string passed
	 * @param original
	 * @return
	 */
	public static String genHash(String original) {
		byte[] digested = original.getBytes();
		byte[] encoded = Base64.encode(digested);
		return new String(encoded);
	}

	public static void main(String args[]) {
		System.out.println(genHash("324231421"));

	}
}
