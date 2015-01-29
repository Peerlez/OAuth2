package com.peerlez.authorize;

import com.peerlez.authorize.resource.BearerAccessToken;
import com.peerlez.authorize.resource.JwtAccessToken;
import com.peerlez.authorize.resource.MacAccessToken;

/**
 * Interface to create different types of AccessTokens.
 * 
 * @author A.Sillanpaa
 *
 */
public interface CreateAccessToken {

	/**
	 * Create <code>Bearer</code> AccessToken
	 */
	BearerAccessToken createBearerAccessToken();
	
	/**
	 * Create <code>MAC</code> AccessToken
	 */
	MacAccessToken createMacAccessToken(String macAlgorithm);
	
	/**
	 * Create <code>JWT</code> AccessToken
	 */
	JwtAccessToken createJwtAccessToken();
}
