package com.evleen.userservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.evleen.userservice.dto.ApiErrorResponseDto;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class UserExceptionsHandler {

	@ExceptionHandler(UserAlreadyRegisteredException.class)
	public final ResponseEntity<ApiErrorResponseDto> handleUserAlreadyRegisteredException(Exception ex,
			WebRequest request) throws Exception {
		ApiErrorResponseDto apiError = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.CONFLICT.value()), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ApiErrorResponseDto>(apiError, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ApiErrorResponseDto> handleUserNotFoundException(Exception ex,
			WebRequest request) throws Exception {
		ApiErrorResponseDto apiError = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ApiErrorResponseDto>(apiError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ApiErrorResponseDto> handleBeanConstraintVoilationException(Exception ex,
			WebRequest request) throws Exception {
		ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ApiErrorResponseDto>(apiErrorResponseDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ApiErrorResponseDto> handleAllExceptions(Exception ex, WebRequest request)
			throws Exception {
		ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(LocalDateTime.now(),
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ApiErrorResponseDto>(apiErrorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
