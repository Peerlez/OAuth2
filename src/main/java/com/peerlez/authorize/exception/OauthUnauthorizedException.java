package com.peerlez.authorize.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpHeaders;

import com.peerlez.authorize.common.OAuthConstants;
import com.peerlez.authorize.utils.CommonUtils;

/**
 * Thrown to return a 401 Unauthorized with {@link HttpHeaders.WWW_AUTHENTICATE}
 * HTTP header and also with the Realm.
 * 
 * @author A.Sillanpaa
 *
 */
public class OauthUnauthorizedException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs new OauthUnauthorizedException with 
	 * {@link HttpHeaders.WWW_AUTHENTICATE} HTTP header and also with the used 
	 * Realm.
	 * 
	 */
	public OauthUnauthorizedException() {
		super(Response.status(Status.UNAUTHORIZED)
				.header(HttpHeaders.WWW_AUTHENTICATE,
				OAuthConstants.WWWAuthHeader.BASIC_REALM)
				.cacheControl(CommonUtils.cacheControl())
				.header("Pragma", "no-cache")
				.build());
	}

	/**
	 * Constructs new OauthUnauthorizedException with 
	 * {@link HttpHeaders.WWW_AUTHENTICATE} HTTP header and also with the given 
	 * Realm.
	 * 
	 * @param realm
	 * 			realm to add to HTTP header
	 * 				
	 */
	public OauthUnauthorizedException(String realm) {
		super(Response.status(Status.UNAUTHORIZED)
				.header(HttpHeaders.WWW_AUTHENTICATE, realm)
				.cacheControl(CommonUtils.cacheControl())
				.header("Pragma", "no-cache")
				.build());
	}
}
