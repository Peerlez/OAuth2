package com.peerlez.authorize.resource;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.peerlez.authorize.common.OAuthConstants;
import com.peerlez.authorize.common.OAuthError;
import com.peerlez.authorize.exception.OauthBadRequestException;

/**
 * Class to hold the Authorization Request. Not needed in Implicit Grant, but
 * for future development if we decided to implement more Grant Flows.
 *
 * @author A.Sillanpaa
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "client_id", "response_type", "redirect_uri" })
public class OAuthRequest {

	@XmlAttribute(name = "client_id")
	private String _clientId;

	@XmlAttribute(name = "response_type")
	private String _responseType;

	@XmlAttribute(name = "redirect_uri")
	private String _redirectUri;

	/**
	 * Constructs an empty OAuthRequest
	 */
	public OAuthRequest() {
	}

	/**
	 * @param formParams parameters from authorize request
	 */
	public OAuthRequest(MultivaluedMap<String, String> formParams) {

		_clientId = formParams.getFirst(OAuthConstants.OAUTH_CLIENT_ID);
		_responseType = formParams.getFirst(OAuthConstants.OAUTH_RESPONSE_TYPE);
		_redirectUri = formParams.getFirst(OAuthConstants.OAUTH_REDIRECT_URI);
	}

	/**
	 * Gets this client ID of the request.
	 *
	 * @return client ID
	 */
	public String getClientID() {
		return _clientId;
	}

	/**
	 * Gets this response type of the request.
	 *
	 * @return response type
	 */
	public String getResponseType() {
		return _responseType;
	}

	/**
	 * Gets this redirect uri of the request.
	 *
	 * @return redirect uri
	 */
	public String getRedirectURI() {
		return _redirectUri;
	}

	/**
	 * Validates the request that all needed parameters are present.
	 *
	 * @throws OauthBadRequestException if the request isn't valid
	 */
	public void validate() throws OauthBadRequestException {

		if (null == _responseType) {
			throw new OauthBadRequestException(new OAuthError("Parameter "
					+ "is missing", OAuthConstants.OAUTH_RESPONSE_TYPE));
		}

		if (null == _clientId) {
			throw new OauthBadRequestException(new OAuthError("Parameter "
					+ "is missing", OAuthConstants.OAUTH_CLIENT_ID));
		}

		if (null == _redirectUri) {
			throw new OauthBadRequestException(new OAuthError("Parameter "
					+ "is missing", OAuthConstants.OAUTH_REDIRECT_URI));
		}
	}
}