package com.peerlez.authorize.resource;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Access Token and related info. Access Token is used to authorize requests to
 * back-end services as defined in OAuth2 specification.
 *
 * @author A.Sillanpaa
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "accessToken", "tokenType", "expiresTime", "created",
		"clientId", "tokenStatus", "grantTypeId" })
public final class AccessToken {

	@XmlAttribute(name = "accessToken")
	private String _accessToken;

	@XmlAttribute(name = "tokenType")
	private String _tokenType;

	@XmlAttribute(name = "created")
	private Timestamp _created;

	@XmlAttribute(name = "expiresTime")
	private Double _expiresTime;

	@XmlAttribute(name = "clientId")
	private String _clientId;

	@XmlAttribute(name = "tokenStatus")
	private String _tokenStatus;

	@XmlAttribute(name = "grantTypeId")
	private Long _grantTypeId;

	/**
	 * Constructs an empty AccessToken
	 */
	public AccessToken() {
	}

	/**
	 * Constructs a new AccessToken with the given parameters.
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
	}

	/**
	 * Gets this Access Token.
	 *
	 * @return Access Token
	 */
	public String getAccessToken() {
		return _accessToken;
	}

	/**
	 * Get this Access Token's expire time.
	 *
	 * @return the expire time of the Access Token as milliseconds since Unix
	 *         Epoch
	 */
	@XmlTransient
	public Double getExpiresTime() {
		return _expiresTime;
	}

	/**
	 * Gets Access Token status. Statuses are defined by OAuth2 specification.
	 *
	 * @return access token status
	 */
	public String getTokenStatus() {
		return _tokenStatus;
	}

	/**
	 * Gets token creation time as Unix timestamp
	 *
	 * @return timestamp when this Access Token was created
	 */
	public Timestamp getCreated() {
		return _created;
	}

	/**
	 * Gets client identifier; identifies the OAuth client from which this
	 * Access Token is made.
	 *
	 * @return client ID
	 */
	public String getClientId() {
		return _clientId;
	}

	/**
	 * Gets the Access Token's type (Bearer) only implemented
	 *
	 * @return Access Token's type
	 */
	public String getTokenType() {
		return _tokenType;
	}

	/**
	 * Gets the grant type ID which was used to get this Access Token
	 *
	 * @return grant type ID
	 */
	public Long getGrantTypeId() {
		return _grantTypeId;
	}

	/**
	 * Sets this Access Token's expire time in milliseconds since Unix Epoch.
	 *
	 * @param expiresTime new expire time in milliseconds since Unix Epoch
	 */
	@XmlTransient
	public Double setExpiresTime(Double expiresTime) {
		_expiresTime = expiresTime;
		return _expiresTime;
	}

	@Override
	public String toString() {
		return String.format("[token = %s, type = %s]", _accessToken, 
				_tokenType);
	}
}