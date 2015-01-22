package com.peerlez.authorize.utils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.CacheControl;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * Common Utils class to contain utilities to handle the common {
 * {@link #cacheControl()} instance with proper values according to OAuth2
 * specification to use on
 * {@link com.peerlez.authorize.OAuthFragmentResponseFactory
 * OAuthFragmentResponseFactory}
 *
 * Provides also new HTTP {@link Client} instance for other classes to use.
 *
 * @author A.Sillanpaa
 *
 */
public final class CommonUtils {

	private static final CacheControl CC = new CacheControl();
	private static Client _client;

	/**
	 * CacheControl method with preventing responses to be cached.
	 *
	 * @return CacheControl new instance of CacheControl with options
	 *         <code>NoStore</code>prevents browsers to store data on local
	 *         storage <code>NoCache</code>should revalidate this resource every
	 *         time <code>NoTransform</code>prevents the response data to be
	 *         transformed different format
	 */
	public static CacheControl cacheControl() {
		CC.setNoStore(true);
		CC.setNoCache(true);
		CC.setNoTransform(true);
		return CC;
	}

	/**
	 * Constructs new instance of {@link Client} with configuration
	 * {@link JacksonFeature} to ensure correct serialization of resource
	 * classes. Only one instance of {@link Client} can be created.
	 *
	 * @return instance of {@link Client}
	 */
	public static synchronized Client getHttpClient() {

		if (null == _client) {
			ClientConfig config = new ClientConfig()
				.register(new JacksonFeature());

			_client = ClientBuilder.newClient(config);
		}
		return _client;
	}
}