package com.peerlez.authorize.api;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.peerlez.authorize.AccessTokenDAO;
import com.peerlez.authorize.common.OAuthError;
import com.peerlez.authorize.common.OAuthErrorConstants;
import com.peerlez.authorize.exception.OauthNotFoundException;
import com.peerlez.authorize.resource.AccessToken;

/**
 * Class providing Token Endpoint (from OAuth2 specification). Also contains
 * some other Access Token associated API methods.
 *
 * @author A.Sillanpaa
 * 
 */
@Path("/tokens")
public final class Authorize {
	
	@Context
	private ServletContext _servletContext;

	/**
	 * Validate the given <code>AccessToken</code>.
	 *
	 * @param token 
	 * 				<code>AccessToken</code> which needs to be validated
	 *
	 * @return Access Token 
	 * 				the <code>AccessToken</code> which is validated
	 *
	 * @throws SQLException 
	 * 				if any database-related error occurs
	 *
	 * @HTTP 200 The AccessToken is found and returned.
	 * @HTTP 404 The AccessToken is not found.
	 */
	@GET
	@Path("{token}/validation")
	@Produces({ MediaType.APPLICATION_JSON })
	public AccessToken validateAccessToken(
		@PathParam("token") String accessToken) throws SQLException {

		AccessToken validation = AccessTokenDAO.instance(_servletContext)
				.validateAccessToken(accessToken);

		if (validation == null) {
			throw new OauthNotFoundException(new OAuthError(
					OAuthErrorConstants.NOT_FOUND,
					OAuthErrorConstants.INVALID_TOKEN));
		}
		return validation;
	}
}
