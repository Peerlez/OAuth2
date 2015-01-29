package com.peerlez.authorize.resource;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author A.Sillanpaa
 *
 */
public class BearerAccessToken extends AccessToken {

	public final static String BEARER_TOKEN = "bearer";

	@XmlAttribute(name = "accessToken")
	private String _accessToken;

	@XmlAttribute(name = "tokenType")
	private String _tokenType;

	@XmlAttribute(name = "created")
	private Timestamp _created;

	@XmlAttribute(name = "expiresTime")
	private Timestamp _expiresTime;

	@XmlAttribute(name = "clientId")
	private String _clientId;

	@XmlAttribute(name = "tokenStatus")
	private String _tokenStatus;

	@XmlAttribute(name = "grantTypeId")
	private Long _grantTypeId;

	/**
	 * Constructs an empty BearerAccessToken, needed for JAXB.
	 */
	public BearerAccessToken() {
	}

	/**
	 * Constructs a new BearerAccessToken with the given parameters.
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
	public BearerAccessToken(String accessToken, String tokenType,
		Timestamp expiresTime, Timestamp created, String clientId,
		String status, Long grantTypeId, Long customerId) {
		_accessToken = accessToken;
		_tokenType = tokenType;
		_created = created;
		_clientId = clientId;
		_tokenStatus = status;
		_grantTypeId = grantTypeId;
	}

	@Override
	public String getAccessToken() {
		return _accessToken;
	}

	@Override
	public Timestamp getExpiresTime() {
		return _expiresTime;
	}

	@Override
	public String getTokenStatus() {
		return _tokenStatus;
	}

	@Override
	public Timestamp getCreated() {
		return _created;
	}

	@Override
	public String getClientId() {
		return _clientId;
	}

	@Override
	public String getTokenType() {
		return _tokenType;
	}

	@Override
	public Long getGrantTypeId() {
		return _grantTypeId;
	}
	
	@Override
	public String toString() {
		return String.format("[token = %s, type = %s]", _accessToken, 
				_tokenType);
	}
}