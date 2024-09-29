package com.user.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=IncorrectCredentialException.class)
	public ResponseEntity<AppExceptionInfo>  IncorrectCredentialExceptionHandler(IncorrectCredentialException ex){
		
		  AppExceptionInfo info = new AppExceptionInfo();
		  info.setExMsg(ex.getMessage());
		  info.setExCode("INVALID001");
		  info.setExDate(LocalDateTime.now());
		  
		  return new ResponseEntity<AppExceptionInfo>(info, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value=UserAlredyRegisterExcpetion.class)
	public ResponseEntity<AppExceptionInfo> UserExistException(UserAlredyRegisterExcpetion ex){
		
		  AppExceptionInfo info = new AppExceptionInfo();
		  info.setExMsg(ex.getMessage());
		  info.setExCode("EXISTSUSER001");
		  info.setExDate(LocalDateTime.now());
		  
		  return new ResponseEntity<AppExceptionInfo>(info, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<AppExceptionInfo>  UserNotFound(UserNotFoundException ex){
		
		  AppExceptionInfo info = new AppExceptionInfo();
		  info.setExMsg(ex.getMessage());
		  info.setExCode("USERNOTFOUND001");
		  info.setExDate(LocalDateTime.now());
		  
		  return new ResponseEntity<AppExceptionInfo>(info, HttpStatus.BAD_REQUEST);
	}
}
