package com.peerlez.authorize.exception;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.peerlez.authorize.utils.CommonUtils;

/**
 * Thrown to return a HTTP status code 400 Bad request with JSON response 
 * body with CacheControl headers.
 * 
 * @author A.Sillanpaa
 *
 */
public class OauthBadRequestException extends WebApplicationException {

	private static final long serialVersionUID = 1L;
	private List<String> _errors;

	/**
	 * Constructs new OauthBadRequestException with given response body/entity 
	 * 
	 * @param entity
	 * 				response body/entity
	 */
	public OauthBadRequestException(Object entity) {
		super(Response.status(Status.BAD_REQUEST)
				.entity(entity)
				.encoding("UTF-8")
				.type(MediaType.APPLICATION_JSON)
				.cacheControl(CommonUtils.cacheControl())
				.header("Pragma", "no-cache")
				.build());
	}

	/**
	 * Constructs new OauthBadRequestException with given response body/entity 
	 * and with given response type {@link MediaType}
	 * 
	 * @param entity
	 * 				response body/entity
	 * @param type
	 * 				response's {@link MediaType}
	 */
	public OauthBadRequestException(Object entity, MediaType type) {
		super(Response.status(Status.BAD_REQUEST)
				.entity(entity)
				.encoding("UTF-8")
				.type(type)
				.cacheControl(CommonUtils.cacheControl())
				.header("Pragma", "no-cache")
				.build());
	}
 
    /**
	 * Constructs a OauthBadRequestException with a list of errors
	 *
	 * @param errors the list of errors
	 *
     */
    public OauthBadRequestException(List<String> errors) {
        super(Response.status(Status.BAD_REQUEST)
        		.type(MediaType.APPLICATION_XHTML_XML)
        		.cacheControl(CommonUtils.cacheControl())
        		.encoding("UTF-8")
				.header("Pragma", "no-cache")
                .entity(new GenericEntity<List<String>>(errors){})
                .build());

        _errors = errors;
    }
 
    /**
	 * Gets the list of errors in this exception.
	 *
	 * @return list of errors
	 */
    public List<String> getErrors() {
        return _errors;
    }
}