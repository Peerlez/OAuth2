package com.peerlez.authorize.resource;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * This class is holder Access Token validation result. AccessTokens are
 * validated every time by the back-end services when making the back-end
 * request with AccessToken in <code>WWW-Authorization</code> HTTP Header.
 *
 * @author A.Sillanpaa
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@XmlType(propOrder = { "accessToken", "tokenStatus", "expires" })
public class AccessTokenValidation {

	@XmlAttribute(name = "accessToken")
	private String _accessToken;

	/**
	 * Access Token status. Valid values "revoked", "valid", "expired".
	 */
	@XmlAttribute(name = "tokenStatus")
	private String _tokenStatus;

	/**
	 * Expiry time. In milliseconds (unix Epoch).
	 */
	@XmlAttribute(name = "expires")
	private Timestamp _expiresTime;

	/**
	 * Non-argument constructor. Necessary for JAXB.
	 */
	public AccessTokenValidation() {
	}

	/**
	 * Constructs a new Access Token validation with the given parameters.
	 *
	 * @param accessToken the Access Token
	 * @param tokenStatus Access Token status can be ("revoked, valid, expired")
	 * @param expires Access Tokens expiry time (epoch timestamp)
	 */
	public AccessTokenValidation(String accessToken, String tokenStatus, 
			Timestamp expires) {
		_accessToken = accessToken;
		_tokenStatus = tokenStatus;
		_expiresTime = expires;
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
	 * Get this Access Token's expire time value as milliseconds since Unix
	 * Epoch.
	 *
	 * @return the expire time of the Access Token in UNIX Epoch timestamp in
	 *         milliseconds
	 */
	@XmlTransient
	public Timestamp getExpiresTime() {
		return _expiresTime;
	}

	/**
	 * Gets access token status
	 *
	 * @return access token status
	 */
	public String getTokenStatus() {
		return _tokenStatus;
	}

	/**
	 * Is token expired
	 *
	 * @return true if token is expired, false otherwise
	 */
	public boolean isExpired() {
		return getTokenStatus().equals("expired");
	}

	@Override
	public String toString() {
		return String.format("[token = %s, status = %s]", _accessToken, 
				_tokenStatus);
	}
}