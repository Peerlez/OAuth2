package com.peerlez.authorize.common;

/**
 * Abstract class to contain constants to use while parsing and formatting grant
 * types for OAuth2 request.
 *
 * @author A.Sillanpaa
 *
 */
public abstract class GrantType {

	public static final String AUTHORIZATION_CODE = "authorization_code";
	public static final String RESOURCE_OWNER_PASSWORD = "password";
	public static final String CLIENT_CREDENTIALS = "client_credentials";
	public static final String REFRESH_TOKEN = "refresh_token";
	public static final String IMPLICIT_GRANT = "token";
}