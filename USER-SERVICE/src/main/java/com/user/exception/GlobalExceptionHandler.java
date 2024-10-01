package com.user.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import io.swagger.v3.core.util.Json;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = IncorrectCredentialException.class)
	public ResponseEntity<AppExceptionInfo> IncorrectCredentialExceptionHandler(IncorrectCredentialException ex) {

		AppExceptionInfo info = new AppExceptionInfo();
		info.setExMsg(ex.getMessage());
		info.setExCode("INVALID001");
		info.setExDate(LocalDateTime.now());

		return new ResponseEntity<AppExceptionInfo>(info, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserAlredyRegisterExcpetion.class)
	public ResponseEntity<AppExceptionInfo> UserExistException(UserAlredyRegisterExcpetion ex) {

		AppExceptionInfo info = new AppExceptionInfo();
		info.setExMsg(ex.getMessage());
		info.setExCode("EXISTSUSER001");
		info.setExDate(LocalDateTime.now());

		return new ResponseEntity<AppExceptionInfo>(info, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<AppExceptionInfo> UserNotFound(UserNotFoundException ex) {

		AppExceptionInfo info = new AppExceptionInfo();
		info.setExMsg(ex.getMessage());
		info.setExCode("USERNOTFOUND001");
		info.setExDate(LocalDateTime.now());

		return new ResponseEntity<AppExceptionInfo>(info, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)

	public ResponseEntity<Map<String, String>> InputVoilationException(ConstraintViolationException e)
			 {

		Map<String, String> map = new HashMap<>();

		e.getConstraintViolations().forEach(x -> {
			String fieldName = x.getPropertyPath().toString();

			String message = x.getMessage();
			map.put(fieldName, message);
		});
		
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

}
