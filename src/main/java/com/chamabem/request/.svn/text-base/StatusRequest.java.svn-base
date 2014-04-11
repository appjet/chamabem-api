package com.chamabem.request;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.NotEmpty;

import com.sun.istack.NotNull;

@XmlRootElement
public class StatusRequest {
	
	@NotNull
	@NotEmpty
	private String callId;
	
	@NotNull
	@NotEmpty
	private String status;

	
	public String getCallId() {
		return callId;
	}
	
	public void setCallId(String callId) {
		this.callId = callId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}