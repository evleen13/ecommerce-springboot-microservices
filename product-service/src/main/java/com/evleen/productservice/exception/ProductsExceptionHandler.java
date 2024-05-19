package com.evleen.productservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.evleen.productservice.dto.ApiErrorResponseDto;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ProductsExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiErrorResponseDto> handleProductNotFoundException(ProductNotFoundException ex,
			WebRequest request) {
		ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage(), request.getDescription(false));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(ProductNotAvailableException.class)
	public final ResponseEntity<ApiErrorResponseDto> handleProductNotAvailableException(ProductNotAvailableException ex,
			WebRequest request) throws Exception {
		ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ApiErrorResponseDto>(apiErrorResponseDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateProductKeyException.class)
	public ResponseEntity<ApiErrorResponseDto> handleDuplicateProductKeyException(DuplicateProductKeyException ex,
			WebRequest request) {
		ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.CONFLICT.value()), ex.getMessage(), request.getDescription(false));

		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
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
	public ResponseEntity<ApiErrorResponseDto> handleRuntimeException(Exception ex, WebRequest request) {
		ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage(),
				request.getDescription(false));

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
