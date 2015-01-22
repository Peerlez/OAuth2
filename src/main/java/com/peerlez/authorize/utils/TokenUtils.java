package com.peerlez.authorize.utils;

import java.nio.charset.Charset;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

/**
 * Token Utils class to contain utilities to handle decode BASE64 encoded
 * string; And to generate random String and BASE 64 encode them to Access
 * Tokens.
 *
 * @author A.Sillanpaa
 *
 */
public final class TokenUtils {

	public static final Charset UTF_8 = Charset.forName("UTF-8");

	/**
	 * BASE64 decoder. Decodes BASE64 encoded Strings back to original String
	 * form.
	 *
	 * @param s BASE64 encoded String which needs to be decoded
	 *
	 * @return BASE64 decoded String
	 */
	public static String decode(String s) {

		byte[] decodedBytes = Base64.decodeBase64(s);
		String decode = new String(decodedBytes, UTF_8);

		return decode;
	}

	/**
	 * Constructs the random String and BASE64 encodes it to used as an
	 * AccessToken.
	 *
	 * @return BASE64 encoded random String
	 */
	public static String generateAccessToken() {

		String token = UUID.randomUUID().toString();

		byte[] encodedBytes = Base64.encodeBase64(token.getBytes(UTF_8));
		String encoded = new String(encodedBytes, UTF_8);

		return encoded;
	}
}