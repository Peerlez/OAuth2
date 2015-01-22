package com.peerlez.authorize.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.peerlez.authorize.common.OAuthErrorConstants;
import com.peerlez.authorize.utils.CommonUtils;

/**
 * Thrown to return a 302 redirecting the client with error message on URL 
 * #fragment component. Should be used only on Implicit Grant flow errors.
 * 
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.2.2.1"> RFC
 *      6749 section 4.2.2.1</a>
 * 
 * @author A.Sillanpaa
 *
 */
public class OauthFragmentException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs URL #fragment component which includes the error and error
	 * description
	 *
	 * @param uri address to redirect with error message
	 * @param error error message
	 * @param errorDescription human readable description of the error
	 *
	 * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.2.2.1"> RFC
	 *      6749 section 4.2.2.1</a>
	 *
	 * @return error response on URL #fragment
	 */
	public OauthFragmentException(String uri, String error, 
			String errorDescription) { 

		super(Response.temporaryRedirect(UriBuilder.fromPath(uri)
			.fragment(constructFragment(uri, error, errorDescription))
			.build())
			.type(MediaType.APPLICATION_FORM_URLENCODED)
			.header("Pragma", "no-cache")
			.cacheControl(CommonUtils.cacheControl())
			.build());
	}
	
	/**
	 * Constructs URL #fragment component which includes the error
	 *
	 * @param uri address to redirect with error message
	 * @param error error message
	 *
	 *
	 * @return error response on URL #fragment
	 */
	public OauthFragmentException(String uri, String error) {

		super(Response.temporaryRedirect(UriBuilder.fromPath(uri)
			.fragment(constructFragment(uri, error))
			.build())
			.type(MediaType.APPLICATION_FORM_URLENCODED)
			.header("Pragma", "no-cache")
			.cacheControl(CommonUtils.cacheControl())
			.build());
	}
	
	/**
	 * Constructs URL #fragment component which includes the error and error
	 * description
	 *
	 * @param uri address to redirect with error message
	 * @param error error message
	 * @param errorDescription human readable description of the error
	 *
	 *
	 * @return error response on URL #fragment
	 */
	private static String constructFragment(String uri, String error, 
			String errorDescription) {
		
		String fragment = String.format("%s=%s&%s=%s",
			OAuthErrorConstants.ERROR, error, OAuthErrorConstants.DESCRIPTION,
			errorDescription);

		return fragment;
	}
	
	/**
	 * Constructs URL #fragment component which includes the error and error
	 * description
	 *
	 * @param uri address to redirect with error message
	 * @param error error message
	 * 
	 * @return error response on URL #fragment
	 */
	private static String constructFragment(String uri, String error) {
		
		String fragment = String.format("%s=%s",
			OAuthErrorConstants.ERROR, error);

		return fragment;
	}
}
