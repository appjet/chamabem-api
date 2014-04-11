package com.chamabem.util;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Validation {

	
	private String fieldPath;
	
	private String message;
	
	public Validation (String fieldPath,String message){
		this.fieldPath = fieldPath;
		this.message = message;
	}
	
	public Validation (String message){
		this.message = message;
	}

	public String getFieldPath() {
		return fieldPath;
	}

	public void setFieldPath(String fieldPath) {
		this.fieldPath = fieldPath;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
