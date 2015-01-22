package com.peerlez.authorize.resource;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Resource class to map <code>ClientCredentials Grant</code> flow type an
 * AccessToken in the system. AccessToken is used for requests to back-end
 * services. <code>ClientCredentials Grant</code> is used on service-to-service
 * authorization. With valid AccessToken service can have information from other
 * back-end service APIs.
 *
 * @author A.Sillanpaa
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "access_token", "token_type", "expires_in" })
public class ClientCredentials {

	@XmlAttribute(name = "access_token")
	private String _accessToken;

	@XmlAttribute(name = "token_type")
	private String _tokenType;

	@XmlAttribute(name = "expires_in")
	private Timestamp _expiresIn;

	public ClientCredentials() {
	}

	public ClientCredentials(String accessToken, String tokenType,
		Timestamp expireIn) {
		_accessToken = accessToken;
		_tokenType = tokenType;
		_expiresIn = expireIn;
	}

	/**
	 * Gets this AccessToken.
	 *
	 * @return AccessToken
	 */
	public String getAccessToken() {
		return _accessToken;
	}

	/**
	 * Gets this AccessToken's type.
	 *
	 * @return AccessToken's type
	 */
	public String getTokenType() {
		return _tokenType;
	}

	/**
	 * Sets this AccessToken's expire time value.
	 *
	 * @param expire the expire time of the AccessToken
	 */
	public void setExpireIn(Timestamp expire) {
		_expiresIn = expire;
	}

	/**
	 * Gets this AccessToken's expire time.
	 *
	 * @return AccessToken's expire time
	 */
	public Timestamp getExpiresIn() {
		return _expiresIn;
	}
}