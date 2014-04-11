package com.chamabem.dto;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.chamabem.core.persistence.CBEntity;

public abstract class CBAuthenticateDTO extends CBEntity{
	private static final long serialVersionUID = 1L;

    private String signature;

    private Long expires;

	@JsonIgnore
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@JsonIgnore
	public Long getExpires() {
		return expires;
	}

	public void setExpires(Long expires) {
		this.expires = expires;
	}
}