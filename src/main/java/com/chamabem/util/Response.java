package com.chamabem.util;

import static org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL;
import com.chamabem.serializer.ResponseStatusDeserializer;
import com.chamabem.serializer.ResponseStatusSerializer;
import com.chamabem.util.Serializer;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonPropertyOrder({ "status", "message" })
public class Response<D> {

	private Status status;

	private String message;

	public enum Status {

		OK, ERROR;
	}

	@Override
	public String toString() {
		return Serializer.json(this);
	}

	@JsonSerialize(using = ResponseStatusSerializer.class)
	public Status getStatus() {
		return status;
	}

	@JsonDeserialize(using = ResponseStatusDeserializer.class)
	public void setStatus(Status status) {
		this.status = status;
	}

	@JsonSerialize(include = NON_NULL)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}