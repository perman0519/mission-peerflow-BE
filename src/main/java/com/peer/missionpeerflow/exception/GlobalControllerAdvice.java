package com.peer.missionpeerflow.exception;

import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.*;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>>  methodArgumentNotValidException(MethodArgumentNotValidException e) {
		Map<String, Object> response = new HashMap<>();
		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		List<String> errorList = new ArrayList<>();

		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			errorList.add(fieldError.getDefaultMessage());
		}
		errors.put("message", errorList);
		response.put("error", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = ForbiddenException.class)
	public ResponseEntity forbiddenException(ForbiddenException e) {
		Map<String, Object> response = new HashMap<>();
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		response.put("error", error);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

	@ExceptionHandler(value = DataException.class)
	public ResponseEntity dataException(DataException e) {
		Map<String, Object> response = new HashMap<>();
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		response.put("error", error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity handleDataAccessException(DataAccessException e) {
		String errorMessage = "An error occurred while accessing the database.";
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}

//	@ExceptionHandler(value = InvalidFormatException.class)
//	public ResponseEntity invalidFormatException(InvalidFormatException e) {
////		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
//		Map<String, Object> response = new HashMap<>();
//		Map<String, String> error = new HashMap<>();
//		error.put("InvalidFormat", e.getMessage());
//		response.put("error", error);
//		return ResponseEntity.status(HttpStatus.In).body(response);
//	}

	@ExceptionHandler(value = UnauthorizedException.class)
	public ResponseEntity UnauthorizedException(UnauthorizedException e) {
//		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		Map<String, Object> response = new HashMap<>();
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		response.put("error", error);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity notFoundException(NotFoundException e) {
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		Map<String, Object> response = new HashMap<>();
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		response.put("error", error);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(value = ConflictException.class)
	public ResponseEntity conflictException(ConflictException e) {
//		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		Map<String, Object> response = new HashMap<>();
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		response.put("error", error);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exception(Exception e) {
//		e.printStackTrace(); 디버깅용 코드
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		Map<String, Object> response = new HashMap<>();
		Map<String, String> error = new HashMap<>();
		error.put("message", e.getMessage());
		response.put("error", error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
