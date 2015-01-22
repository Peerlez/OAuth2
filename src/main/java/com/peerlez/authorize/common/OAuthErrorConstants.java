package com.peerlez.authorize.common;

/**
 * Provides OAuth2 error messages as constants to use while parsing and
 * formatting for OAuth2 error responses.
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-5.2"> RFC
 *      6749 section 5.2</a>
 * 
 * @author A.Sillanpaa
 *
 */
public abstract class OAuthErrorConstants {

	public static final String ERROR = "error";
	public static final String DESCRIPTION = "error_description";
	public static final String URI = "error_uri";
	public static final String INVALID_REQUEST = "invalid_request";
	public static final String INVALID_CLIENT = "invalid_client";
	public static final String INVALID_GRANT = "invalid_grant";
	public static final String UNAUTHORIZED_CLIENT = "unauthorized_client";
	public static final String UNSUPPORTED_GRANT_TYPE = "unsupported_grant_type";
	public static final String INVALID_SCOPE = "invalid_scope";
	public static final String INVALID_TOKEN = "invalid_token";
	public static final String REDIRECT_URI_MISMATCH = "redirect_uri_mismatch";
	public static final String UNSUPPORTED_RESPONSE_TYPE = "unsupported_response_type";
	public static final String ACCESS_DENIED = "access_denied";
	public static final String NOT_FOUND = "not_found";
	public static final String CLIENT_NOT_FOUND = "client_not_found";
	public static final String INVALID_AUTH_TOKEN = "invalid_authentication_token";
	public static final String INVALID_CREDENTIALS = "Incorrect credentials";
	public static final String BASIC_TOKEN_MALFORMED = "Given token was badly formatted";
	public static final String TOKEN_NEEDED = "Access Token required";
}