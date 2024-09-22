package com.apiproject.response;

public class ResponseMessage {
	private boolean status;
	private String message;
	private String employeeId;
	private String dateTime;


	public ResponseMessage(boolean status, String message, String employeeId, String dateTime) {
		this.status = status;
		this.message = message;
		this.employeeId = employeeId;
		this.dateTime = dateTime;
	}

	// Getters e Setters
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
