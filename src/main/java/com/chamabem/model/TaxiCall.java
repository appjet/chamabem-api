package com.chamabem.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.NotEmpty;

import com.chamabem.core.persistence.CBEntity;

@Entity
@XmlRootElement
@Table(name = "TaxiCall", uniqueConstraints = @UniqueConstraint(columnNames = "callId"))
public class TaxiCall extends CBEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @NotEmpty
    private String callId;
    
    @ManyToOne
    private User user;
    
    private Double latitude;

    private Double longitude;
    
    private String address;
    
    private String meetingPoint;
    
    private String driverName;

    private String timeApproximate;
    
    private String status;
    
	@Override
	@JsonIgnore
	public Serializable getId() {
		return this.callId;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonIgnore
	public String getMeetingPoint() {
		return meetingPoint;
	}

	public void setMeetingPoint(String meetingPoint) {
		this.meetingPoint = meetingPoint;
	}
	
	@JsonIgnore
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	@JsonIgnore
	public String getTimeApproximate() {
		return timeApproximate;
	}

	public void setTimeApproximate(String timeApproximate) {
		this.timeApproximate = timeApproximate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}