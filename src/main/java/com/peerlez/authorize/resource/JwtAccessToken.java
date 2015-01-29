package com.peerlez.authorize.resource;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * JSON Web Token Class.
 * 
 * @see <a href="https://tools.ietf.org/html/draft-ietf-oauth-json-web-token-32">
 * draft-ietf-oauth-json-web-token-32</a>
 * 
 * @author A.Sillanpaa
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "accessToken", "tokenType", "expiresTime", "created",
		"clientId", "tokenStatus", "grantTypeId" })
public class JwtAccessToken extends AccessToken {

	public final static String MAC_TOKEN = "mac";
	
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
	 * Constructs an empty MacAccessToken, needed for JAXB.
	 */
	public JwtAccessToken() {
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
	public JwtAccessToken(String accessToken, String tokenType,
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
