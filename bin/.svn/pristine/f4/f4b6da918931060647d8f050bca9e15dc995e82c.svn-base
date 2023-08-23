package com.marolix.Bricks99.utility;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.marolix.Bricks99.exception.Bricks99Exception;

@RestControllerAdvice
public class Bricks99ControllerAdvice {
	@Autowired
	private Environment environment;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exp) {
		ErrorInfo e = new ErrorInfo();
		e.setTime(LocalDateTime.now());
		e.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		e.setErrorMesssaage(exp.getMessage());

		return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Bricks99Exception.class)
	public ResponseEntity<ErrorInfo> Bricks99ExceptionHanler (Bricks99Exception exp) {
		ErrorInfo e = new ErrorInfo();
		e.setTime(LocalDateTime.now());
		e.setErrorCode(HttpStatus.BAD_REQUEST.value());
		e.setErrorMesssaage(exp.getMessage());

		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
	}
}
