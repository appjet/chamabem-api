package com.chamabem.dto;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class StatusDTO {
   
    private String meetingPoint;
    
    private String driverName;
    
    private String timeApproximate;

	public String getMeetingPoint() {
		return meetingPoint;
	}

	public void setMeetingPoint(String meetingPoint) {
		this.meetingPoint = meetingPoint;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getTimeApproximate() {
		return timeApproximate;
	}
	
	public void setTimeApproximate(String timeApproximate) {
		this.timeApproximate = timeApproximate;
	}
}