package com.peerlez.authorize.resource;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Base Class for all of the Access Token types (Bearer, MAC, JWT)
 *
 * @author A.Sillanpaa
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "accessToken", "tokenType", "expiresTime", "created",
		"clientId", "tokenStatus", "grantTypeId" })
public abstract class AccessToken {

	@XmlAttribute(name = "accessToken")
	protected String _accessToken;

	@XmlAttribute(name = "tokenType")
	protected String _tokenType;

	@XmlAttribute(name = "created")
	protected Timestamp _created;

	@XmlAttribute(name = "expiresTime")
	protected Timestamp _expiresTime;

	@XmlAttribute(name = "clientId")
	protected String _clientId;

	@XmlAttribute(name = "tokenStatus")
	protected String _tokenStatus;

	@XmlAttribute(name = "grantTypeId")
	protected Long _grantTypeId;

	/**
	 * Non argument constructor needed by JAXB
	 */
	public AccessToken() {	
	}

	/**
	 * Constructs a new MacAccessToken with the given parameters.
	 *
	 * @param accessToken the Access Token
	 * @param tokenType Access Tokens type
	 * @param expiresTime Access Tokens expiry time (epoch timestamp)
	 * @param created when the Access token was created (epoch timestamp)
	 * @param clientId client's identifier to whom client this AccessToken was
	 *            created
	 * @param status Access Tokens status. Can be ("revoked, valid, expired")
	 * @param grantTypeId identifier for which grant flow was used to get the
	 *            Access Token
	 */
	public AccessToken(String accessToken, String tokenType,
		Timestamp expiresTime, Timestamp created, String clientId,
		String status, Long grantTypeId, Long customerId) {
		_accessToken = accessToken;
		_tokenType = tokenType;
		_created = created;
		_clientId = clientId;
		_tokenStatus = status;
		_grantTypeId = grantTypeId;
		_expiresTime = expiresTime;
	}

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