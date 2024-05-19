package com.evleen.orderservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.evleen.orderservice.dto.ApiErrorResponseDto;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class OrderExceptionHandler {
	@ExceptionHandler(ProductOutOfStockException.class)
	public ResponseEntity<ApiErrorResponseDto> handleProductOutOfStockException(ProductOutOfStockException ex,
			WebRequest request) {
		ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), request.getDescription(false));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(InvalidOrderStatusException.class)
	public ResponseEntity<ApiErrorResponseDto> handleInvalidOrderStatusException(InvalidOrderStatusException ex,
			WebRequest request) {
		ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), request.getDescription(false));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(OrderNotFoundException.class)
	public final ResponseEntity<ApiErrorResponseDto> handleOrderNotFoundException(OrderNotFoundException ex,
			WebRequest request) throws Exception {
		ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ApiErrorResponseDto>(apiErrorResponseDto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ApiErrorResponseDto> handleBeanConstraintVoilationException(
			ConstraintViolationException ex, WebRequest request) throws Exception {
		ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ApiErrorResponseDto>(apiErrorResponseDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ApiErrorResponseDto> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex, WebRequest request) throws Exception {
		ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ApiErrorResponseDto>(apiErrorResponseDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponseDto> handleException(Exception ex, WebRequest request) {
		ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage(),
				request.getDescription(false));

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
