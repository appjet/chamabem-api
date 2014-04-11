package com.chamabem.request;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.NotEmpty;

import com.sun.istack.NotNull;

@XmlRootElement
public class RegIdRequest {
	
	@NotNull
	@NotEmpty
	private String regId;
	
	@NotNull
	@NotEmpty
	private String username;
	
	@NotNull
	@NotEmpty
	private String password;

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}