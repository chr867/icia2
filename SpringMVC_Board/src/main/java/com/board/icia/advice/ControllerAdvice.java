package com.board.icia.advice;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.board.icia.exception.InvaildIdException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(InvaildIdException.class)
	public ResponseEntity<String> id_dup_exception_handler(InvaildIdException ex) {
		System.out.println("InvaildIdException Advice");
		return new ResponseEntity<String>(ex.getMessage(),getHeaders(),HttpStatus.I_AM_A_TEAPOT);
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/plain;charset=utf-8");
		return headers;
	}
}
