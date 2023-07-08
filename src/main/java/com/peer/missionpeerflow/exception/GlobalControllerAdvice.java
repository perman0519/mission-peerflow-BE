package com.peer.missionpeerflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>>  methodArgumentNotValidException(MethodArgumentNotValidException e) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", "Validation Error Or Bad Request");
		response.put("message", "The provided data is not valid.");

		Map<String, String> errors = new HashMap<>();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		for (ObjectError globalError : e.getBindingResult().getGlobalErrors()) {
			errors.put(globalError.getObjectName(), globalError.getDefaultMessage());
		}
		response.put("errors", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = ForbiddenException.class)
	public ResponseEntity forbiddenException(ForbiddenException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity notFoundException(NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(value = ConflictException.class)
	public ResponseEntity conflictException(ConflictException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exception(Exception e) {
//		e.printStackTrace(); 디버깅용 코드
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}
