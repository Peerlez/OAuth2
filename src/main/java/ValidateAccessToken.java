

import java.sql.SQLException;

import com.peerlez.authorize.resource.BearerAccessToken;
import com.peerlez.authorize.resource.JwtAccessToken;
import com.peerlez.authorize.resource.MacAccessToken;

/**
 * Interface to validate different types of AccessTokens.
 * 
 * @author A.Sillanpaa
 *
 */
public interface ValidateAccessToken {

	/**
	 * Validate <code>Bearer</code> AccessToken
	 * 
	 * @param accessToken
	 * 				AccessToken which needs to be validated
	 * 
	 * @return validated
	 * 				AccessToken {@link BearerAccessToken}; {@code null} if not 
	 * 				found
	 * 
	 * @throws SQLException
	 * 				if any database-related error occurs
	 */
	BearerAccessToken validateBearerAccessToken(String accessToken) 
		throws SQLException;
	
	/**
	 * Validate <code>MAC</code> AccessToken
	 * 
	 * @param accessToken
	 * 				AccessToken which needs to be validated
	 * 
	 * @return validated
	 * 				AccessToken {@link MacAccessToken}; {@code null} if not 
	 * 				found
	 * 
	 * @throws SQLException
	 * 				if any database-related error occurs
	 */
	MacAccessToken validateMacAccessToken(String accessToken) 
			throws SQLException;
	
	/**
	 * Validate <code>JWT</code> AccessToken
	 * 
	 * @param accessToken
	 * 				AccessToken which needs to be validated
	 * 
	 * @return validated
	 * 				AccessToken {@link JwtAccessToken}; {@code null} if not 
	 * 				found
	 * 
	 * @throws SQLException
	 * 				if any database-related error occurs
	 */
	JwtAccessToken validateJwtAccessToken(String accessToken) 
			throws SQLException;
}
