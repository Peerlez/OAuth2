package com.peerlez.authorize.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpHeaders;

import com.peerlez.authorize.utils.CommonUtils;

/**
 * Thrown to return a 404 Not Found JSON response with CacheControl headers.
 * 
 * @author A.Sillanpaa
 *
 */
public class OauthNotFoundException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs new OauthNotFoundException with given response body/entity
	 * 
	 * @param entity
	 * 				response body/entity
	 */
	public OauthNotFoundException(Object entity) {
		super(Response.status(Status.NOT_FOUND)
				.entity(entity)
				.encoding("UTF-8")
				.type(MediaType.APPLICATION_JSON)
				.cacheControl(CommonUtils.cacheControl())
				.header("Pragma", "no-cache")
				.build());
	}

	/**
	 * Constructs new OauthNotFoundException with given response body/entity and
	 * with given response type {@link MediaType}
	 * 
	 * @param entity
	 * 				response body/entity
	 * @param type
	 * 				response's {@link MediaType}
	 */
	public OauthNotFoundException(Object entity, MediaType type) {
		super(Response.status(Status.NOT_FOUND)
				.entity(entity)
				.encoding("UTF-8")
				.type(type)
				.cacheControl(CommonUtils.cacheControl())
				.header("Pragma", "no-cache")
				.build());
	}
}