package com.evleen.userservice.dto;

import java.time.LocalDateTime;

public class ApiErrorResponseDto {
	private LocalDateTime dateTime;
	private String code;
	private String message;
	private String details;

	public ApiErrorResponseDto() {
		super();
	}

	public ApiErrorResponseDto(LocalDateTime dateTime,String code, String message, String details) {
		super();
		this.dateTime = dateTime;
		this.code = code;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
