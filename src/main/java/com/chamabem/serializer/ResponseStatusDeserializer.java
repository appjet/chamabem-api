package com.chamabem.serializer;

import java.io.IOException;

import com.chamabem.util.Response;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class ResponseStatusDeserializer extends JsonDeserializer<Response.Status> {

	@Override
	public Response.Status deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		return Response.Status.valueOf(jp.getText().toUpperCase());
	}
}
