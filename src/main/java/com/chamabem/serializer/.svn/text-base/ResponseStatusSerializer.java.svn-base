package com.chamabem.serializer;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.chamabem.util.Response;

public class ResponseStatusSerializer extends JsonSerializer<Response.Status> {

	@Override
	public void serialize(Response.Status type, JsonGenerator gen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		gen.writeString(type.name().toLowerCase());
	}
}