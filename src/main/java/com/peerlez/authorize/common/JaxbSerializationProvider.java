package com.peerlez.authorize.common;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/**
 * Custom provider class to provide serializer and deserializer functions
 * through custom Jackson ObjectMapper to serialize entities against JAXB
 * annotations.
 *
 * @author A.Sillanpaa
 *
 */
@Provider
public final class JaxbSerializationProvider implements
	ContextResolver<ObjectMapper> {

	/**
	 * JAXB
	 */
	private static JaxbAnnotationIntrospector _jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector(
		TypeFactory.defaultInstance());

	/**
	 * Constructor
	 */
	public JaxbSerializationProvider() {
	}

	/**
	 * Creates custom ObjectMapper with {@link AnnotationIntrospector} for
	 * introspection of annotation-based configuration for serialization and
	 * deserialization.
	 *
	 * @return custom Jackson {@link ObjectMapper} which contains Introspector
	 *         to serialize/deserialize entities.
	 */
	private static ObjectMapper getCustomObjectMapper() {

		final ObjectMapper jsonObjectMapper = new ObjectMapper();
		jsonObjectMapper.setAnnotationIntrospector(_jaxbAnnotationIntrospector);

		return jsonObjectMapper;
	}

	/* (non-Javadoc)
	 *
	 * @see javax.ws.rs.ext.ContextResolver#getContext(java.lang.Class) */
	@Override
	public ObjectMapper getContext(Class<?> type) {
		return getCustomObjectMapper();
	}
}