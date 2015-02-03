package com.peerlez.authorize.resource;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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

	public final static String MAC_TOKEN = "jwt";
	
	/**
	 * Constructs a new JwtAccessToken with the given parameters.
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

		super(accessToken, tokenType,expiresTime, created, clientId,
			status, grantTypeId, customerId);
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