package com.peerlez.authorize.common;

/**
 * Abstract class to contain constants to use while parsing and formatting
 * OAuth2 requests and responses.
 *
 * @author A.Sillanpaa
 *
 */
public abstract class OAuthConstants {

	/**
	 * Contains HTTP Header values
	 */
	public static final class HeaderType {
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
		public static final String AUTHORIZATION = "Authorization";
	}

	public static final String AUTH_TOKEN = "authentication_token";

	/**
	 * Contains values to add with WWW-Authenticate HTTP Header
	 */
	public static final class WWWAuthHeader {
		public static final String REALM = "realm";
		public static final String BASIC = "Basic";
		public static final String BASIC_REALM = "Basic realm=\"oauth2 secure\"";
	}

	/**
	 * HTTP content type constants
	 */
	public static final class ContentType {
		public static final String URL_ENCODED = "application/x-www-form-urlencoded";
		public static final String JSON = "application/json" + ";charset=utf-8";
	}

	/**
	 * Authorization request params
	 */
	public static final String OAUTH_RESPONSE_TYPE = "response_type";
	public static final String OAUTH_CLIENT_ID = "client_id";
	public static final String OAUTH_CLIENT_SECRET = "client_secret";
	public static final String OAUTH_REDIRECT_URI = "redirect_uri";
	public static final String OAUTH_USERNAME = "username";
	public static final String OAUTH_PASSWORD = "password";

	public static final String OAUTH_SCOPE = "scope";
	public static final String OAUTH_STATE = "state";
	public static final String OAUTH_GRANT_TYPE = "grant_type";
	public static final String OAUTH_BEARER = "bearer";

	/**
	 * Authorization response params
	 */
	public static final String OAUTH_CODE = "code";
	public static final String OAUTH_ACCESS_TOKEN = "access_token";
	public static final String OAUTH_EXPIRES_IN = "expires_in";
	public static final String OAUTH_REFRESH_TOKEN = "refresh_token";

	public static final String OAUTH_TOKEN_TYPE = "token_type";
	public static final String OAUTH_TOKEN = "oauth_token";

	public static final String EXPIRED = "expired";
	public static final String REVOKED = "revoked";

	public static final String AUTHENTICATION_TOKEN = "authentication_token";
	public static final String RESPONSE_TYPE_TOKEN = "token";
}