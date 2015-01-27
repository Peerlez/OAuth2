package com.peerlez.authorize.api;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.peerlez.authorize.AccessTokenDAO;
import com.peerlez.authorize.common.OAuthError;
import com.peerlez.authorize.common.OAuthErrorConstants;
import com.peerlez.authorize.exception.OauthBadRequestException;


/**
 * Class provides the Authorization Endpoint as defined in OAuth2
 * specification.
 * 
 * @author A.Sillanpaa
 *
 */
@Path("/oauth2")
public class Oauth {

	@Context
	private ServletContext _servletContext;

	/**
	 * Access Token revocation endpoint as defined by OAuth2.
	 *
	 * @param token 
	 * 				The Access token that the client wants to get revoked
	 * @throws SQLException 
	 *
	 * @HTTP 200 The AccessToken was succesfully revoked(deleted from DB)
	 *
	 * @see <a href="http ://tools.ietf.org/html/rfc7009">RFC 7009 OAuth 2.0
	 *      Token Revocation</a>
	 */
	@POST
	@Path("revoke")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response revoke(@FormParam("token") String accessToken) 
			throws SQLException {

		if (accessToken == null || accessToken.isEmpty()) {
			throw new OauthBadRequestException(new OAuthError(
					OAuthErrorConstants.INVALID_REQUEST,
					OAuthErrorConstants.TOKEN_NEEDED));
		}

		AccessTokenDAO.instance(_servletContext).deleteAccessToken(accessToken);

		return Response.ok().build();
	}
}
