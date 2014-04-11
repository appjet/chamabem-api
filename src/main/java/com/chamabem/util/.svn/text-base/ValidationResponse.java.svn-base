package com.chamabem.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ValidationResponse {

	List<Validation> validationItems = new ArrayList<Validation>();

	private String message;

	public void add(Validation validation) {
		validationItems.add(validation);
	}

	public List<Validation> getValidationItems() {
		return validationItems;
	}

	public void setValidationItems(List<Validation> validationItems) {
		this.validationItems = validationItems;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
