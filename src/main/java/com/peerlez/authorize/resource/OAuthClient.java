package com.peerlez.authorize.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * An OAuth2 Client.
 *
 * Represents a client which is pre-registered to our systems and assigned with
 * client_id from us. The <code>client_id</code> is used on authorization
 * request to identify the client. The client needs to register also their
 * absolute <code>redirect_uri</code> to our systems.
 *
 * Part of the OAuth 2 specification.
 *
 * <b>NOTE</b>: Implicit Grant type doesn't include the usage of
 * <code>client_secret</code> on client authentication according to OAuth 2
 * specification. So the <code>client_secret</code> is for future
 * implementations.
 *
 * @author A.Sillanpaa
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "client_id", "client_secret", "redirect_uri",
		"created", "description" })
public final class OAuthClient {

	@XmlAttribute(name = "id")
	private Long _id;

	@XmlAttribute(name = "client_id")
	private String _clientId;

	@XmlAttribute(name = "redirect_uri")
	private String _redirectUri;

	@XmlAttribute(name = "description")
	private String _description;

	@XmlAttribute(name = "created")
	private Double _created;

	/**
	 * Constructs an empty Client
	 */
	public OAuthClient() {
	}

	/**
	 * Constructs a new Client with the given parameters.
	 *
	 * @param clientId unique identifier of the client
	 * @param redirectUri registered redirect uri
	 * @param description
	 * @param created timestamp (epoch) when client was created
	 */
	public OAuthClient(Long id, String clientId, String redirectUri,
		String description, Double created) {
		_clientId = clientId;
		_redirectUri = redirectUri;
		_description = description;
		_created = created;
	}

	/**
	 * Gets this client's id.
	 *
	 * @return client's id
	 */
	public String getClientID() {
		return _clientId;
	}

	/**
	 * Gets this client's redirect uri.
	 *
	 * @return client's redirect uri
	 */
	public String getRedirectURI() {
		return _redirectUri;
	}

	/**
	 * Gets this client's ID.
	 *
	 * @return client's ID
	 */
	public long getID() {
		return _id;
	}

	/**
	 * Gets this client's description.
	 *
	 * @return client's description
	 */
	public String getDescription() {
		return _description;
	}

	@Override
	public String toString() {
		return String.format("[clientId = %s redirectUri = %s]", _clientId, 
				_redirectUri);
	}
}