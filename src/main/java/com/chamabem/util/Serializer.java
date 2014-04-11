package com.chamabem.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class Serializer {

	public static String json(Object object) {
		String serialized = null;

		try {
			ObjectMapper mapper = new ObjectMapper();
			serialized = mapper.writeValueAsString(object);

		} catch (IOException cause) {
			new RuntimeException(cause);
		}

		return serialized;
	}
	
	public static String json(Object object, String callback) {
		String serialized = json(object);

		if(!Strings.isEmpty(callback)) {
			serialized = callback + "(" + serialized + ")";
		}
		
		
		return serialized;
	}
}
