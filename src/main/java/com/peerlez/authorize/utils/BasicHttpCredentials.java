package com.peerlez.authorize.utils;

import com.peerlez.authorize.utils.TokenUtils;

/**
 * Utilities to help extract, encode, decode HTTP <code>Authorization</code> 
 * Basic credentials
 *
 * @author A.Sillanpaa
 *
 */
public final class BasicHttpCredentials {

	/**
	 * Basic HTTP Authentication scheme name
	 */
	private static final String BASIC_SCHEME = "Basic";

	private String _username;
	private String _password;

	/**
	 * Construct using value of HTTP header <code>Authorization</code>.
	 *
	 * @param authorizationHeaderValue
	 */
	public BasicHttpCredentials(String authorizationHeaderValue) {

		String encodedCredentials = parseHttpCredentials(
			authorizationHeaderValue, BASIC_SCHEME);

		if (encodedCredentials == null) {
			return;
		}

		String token = TokenUtils.decode(encodedCredentials);

		String[] tokenSplit = token.split(":");

		if (tokenSplit.length > 1) {
			_username = tokenSplit[0];
			_password = tokenSplit[1];
		}
	}
	
	/**
	 * Parse token or other value of given type from a string like value of HTTP
	 * <code>Authorization</code> header.
	 *
	 * @param authHeader 
	 * 					Value of Authorization HTTP header
	 * 
	 * @param authScheme 
	 * 					HTTP Authentication scheme e.g. <code>Basic</code>, 
	 * 					<code>Bearer</code>
	 * 
	 * @return HTTP <code>Authorization</code> Header value; {@code null} if the 
	 * 					given HTTP header is null or is malformed
	 */
	public static String parseHttpCredentials(String authHeader,
		String authScheme) {

		if (authHeader == null) {
			return null;
		}

		String[] authSplit = authHeader.split(" ");

		if (authSplit.length == 2 && authSplit[0].equals(authScheme)) {
			return authSplit[1];
		}

		return null;
	}

	/**
	 * Get decoded user name.
	 *
	 * @return decoded user name
	 */
	public String getUsername() {
		return _username;
	}

	/**
	 * Get decoded password
	 *
	 * @return decoded password
	 */
	public String getPassword() {
		return _password;
	}
}