package com.evleen.productservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiErrorResponseDto {
	private LocalDateTime dateTime;
	private String code;
	private String message;
	private String details;
}
