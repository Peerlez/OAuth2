package com.peerlez.authorize.resource;

import java.sql.Timestamp;

/**
 * Base Class for all of the Access Token types (Bearer, MAC, JWT)
 *
 * @author A.Sillanpaa
 * 
 */
public abstract class AccessToken {

	/**
	 * Gets this Access Token.
	 *
	 * @return Access Token
	 */
	public abstract String getAccessToken();

	/**
	 * Get this Access Token's expire time.
	 *
	 * @return the expire time of the Access Token as milliseconds since Unix
	 *         Epoch
	 */
	public abstract Timestamp getExpiresTime();

	/**
	 * Gets Access Token status. Statuses are defined by OAuth2 specification.
	 *
	 * @return access token status
	 */
	public abstract String getTokenStatus();

	/**
	 * Gets token creation time as Unix timestamp
	 *
	 * @return timestamp when this Access Token was created
	 */
	public abstract Timestamp getCreated();

	/**
	 * Gets client identifier; identifies the OAuth client from which this
	 * Access Token is made.
	 *
	 * @return client ID
	 */
	public abstract String getClientId();

	/**
	 * Gets the Access Token's type
	 *
	 * @return Access Token's type
	 */
	public abstract String getTokenType();

	/**
	 * Gets the grant type ID which was used to get this Access Token
	 *
	 * @return grant type ID
	 */
	public abstract Long getGrantTypeId();
}