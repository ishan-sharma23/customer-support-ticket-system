package com.kapturecx.supportticketsystem.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
		return buildError(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI(), List.of());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception,
			HttpServletRequest request) {
		List<String> errors = exception.getBindingResult().getFieldErrors().stream()
				.map(this::formatFieldError)
				.toList();
		return buildError(HttpStatus.BAD_REQUEST, "Validation failed", request.getRequestURI(), errors);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException exception,
			HttpServletRequest request) {
		return buildError(HttpStatus.CONFLICT, "Duplicate or invalid data", request.getRequestURI(), List.of());
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiError> handleIllegalState(IllegalStateException exception, HttpServletRequest request) {
		return buildError(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI(), List.of());
	}

	private ResponseEntity<ApiError> buildError(HttpStatus status, String message, String path, List<String> errors) {
		ApiError apiError = new ApiError(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, path,
				errors);
		return ResponseEntity.status(status).body(apiError);
	}

	private String formatFieldError(FieldError fieldError) {
		return fieldError.getField() + ": " + fieldError.getDefaultMessage();
	}
}
