package com.peerlez.authorize;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.peerlez.authorize.resource.AccessToken;
import com.peerlez.authorize.utils.CommonUtils;

/**
 * This class helps to build responses to Authorization requests as defined in
 * OAuth2 specification.
 *
 * @author A.Sillanpaa
 *
 */
public final class OAuthFragmentResponseFactory {

	private static OAuthFragmentResponseFactory _instance;

	/**
	 * Gets the singleton instance of this class.
	 *
	 * @return singleton instance of this class
	 */
	public static synchronized OAuthFragmentResponseFactory instance() {

		if (null == _instance) {
			_instance = new OAuthFragmentResponseFactory();
		}

		return _instance;
	}

	/**
	 * Constructs URL #fragment component which includes the AccessToken
	 *
	 * @param accessToken AccessToken which is added to #fragment
	 * @param uri the URI to redirect the client with AccessToken in URL
	 *            #fragment component
	 *
	 * @return Response which includes URL #fragment component
	 */
	public Response constructFragment(AccessToken accessToken, String uri) {

		StringBuilder url = new StringBuilder();
		url.append("access_token=" + accessToken.getAccessToken());
		url.append("&token_type=" + accessToken.getTokenType());
		url.append("&expires_in="
			+ getSecondsDeltaToNow(accessToken.getExpiresTime()));

		return accessTokenFragmentResponse(url.toString(), uri);
	}

	/**
	 * Return time difference between given time and current time in seconds.
	 *
	 * @param accessTokenTime Time to compare with as seconds from midnight,
	 *            January 1, 1970 UTC
	 * @return expireTime the difference time in seconds between
	 *         <code>expire</code> and <code>now</code> timestamps
	 */
	private long getSecondsDeltaToNow(Double accessTokenTime) {
		return accessTokenTime.longValue() - System.currentTimeMillis() / 1000L;
	}

	/**
	 * Adds the constructed URL #fragment component to Response
	 *
	 * @param fragment fragment that is added to Response
	 * @param uri the URI to redirect the client with AccessToken in URL
	 *            #fragment component
	 * @return Response which includes URL #fragment component
	 */
	private Response accessTokenFragmentResponse(String fragment, String uri) {

		return Response
			.temporaryRedirect(UriBuilder.fromPath(uri)
			.fragment(fragment)
			.build())
			.status(302)
			.header("Pragma", "no-cache")
			.cacheControl(CommonUtils.cacheControl())
			.build();
	}
}