package com.peerlez.authorize;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.peerlez.authorize.db.DbQuery;

/**
 * @author A.Sillanpaa
 *
 */
public class AccessTokenDAO {

	private static final Logger LOG = LoggerFactory
		.getLogger(AccessTokenDAO.class);

	private final static String DB_CONN = "dbConnection";
	private static AccessTokenDAO _instance;
	private static ServletContext _servletContext;
	private static DbQuery _query;

	private AccessTokenDAO(ServletContext servletContext) {
		_servletContext = servletContext;
		_query = new DbQuery((Connection) _servletContext.getAttribute(DB_CONN));
	}

	/**
	 * Gets the singleton instance of this class.
	 *
	 * @return singleton instance of this class
	 */
	public static synchronized AccessTokenDAO instance(ServletContext 
			servletContext) {

		if (null == _instance) {
			_instance = new AccessTokenDAO(servletContext);
		}

		return _instance;
	}
	
	/**
	 * Deletes from DB the AccessToken matching the given token.
	 *
	 * @param accessToken 
	 * 				the Access Token to delete
	 *
	 * @return {@code true} if the AccessToken was deleted; {@code false} if not
	 *         		found
	 *
	 * @throws SQLException 
	 * 				if any database-related error occurs
	 */
	public boolean deleteAccessToken(String accessToken) throws SQLException {
		
		ResultSet result = _query.initializeCallable("oauth.deletetoken", 
				true, null);
		
		boolean deleted = false;
		while (result.next()) {
	          deleted = result.getBoolean(1);
		}
		LOG.info("Acccess Token {} deleted", accessToken);

		return deleted;
	}
	
	/**
	 * Deletes all expired Access Tokens from DB.
	 *
	 * @return number 
	 * 				of Access Tokens deleted
	 *
	 * @throws SQLException 
	 * 				if any database-related error occurs
	 */
	public long deleteExpiredTokens() throws SQLException {
		
		ResultSet result = _query.initializeCallable("oauth.tokencleanup", 
				true, null);

		Long deletedCount = null;
		while (result.next()) {
	          deletedCount = result.getLong(1);
		}

		LOG.info("{} expired acccess tokens deleted", deletedCount);

		return deletedCount;
	}
}
