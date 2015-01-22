package com.peerlez.authorize.common;

/**
 * Class to construct the OAuth2 error messages. Error message can contain only
 * an message of the error or an error message and error description.
 *
 * @author A.Sillanpaa
 *
 */
public final class OAuthError {

	private String _errorMessage;
	private String _errorDescription;

	/**
	 * Constructs an empty OAuthError
	 */
	public OAuthError() {
	}

	/**
	 * Constructs a new OAuthError with the given parameters
	 *
	 * @param errorMessage message of the error
	 * @param errorDescription human readable description of the error
	 */
	public OAuthError(String errorMessage, String errorDescription) {
		_errorMessage = errorMessage;
		_errorDescription = errorDescription;
	}

	/**
	 * Constructs a new OAuthError with the given parameters
	 *
	 * @param errorMessage message of the error
	 */
	public OAuthError(String errorMessage) {
		_errorMessage = errorMessage;
	}

	/**
	 * Get the error message
	 *
	 * @return error message
	 */
	public String getError() {
		return _errorMessage;
	}

	/**
	 * Get the error description
	 *
	 * @return description of the error
	 */
	public String getError_description() {
		return _errorDescription;
	}
}