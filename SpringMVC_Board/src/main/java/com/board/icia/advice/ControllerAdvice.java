package com.board.icia.advice;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.board.icia.exception.InvaildIdException;

@RestControllerAdvice
public class ControllerAdvice {

//	아이디 중복 체크 예외
	@ExceptionHandler(InvaildIdException.class)
	public ResponseEntity<String> id_dup_exception_handler(InvaildIdException ex) {
		System.out.println("InvaildIdException Advice");
		return new ResponseEntity<String>(ex.getMessage(),getHeaders(),HttpStatus.I_AM_A_TEAPOT);
	}
	

	//한글 깨짐 방지
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();	
		headers.add("Content-Type", "text/plain;charset=utf-8");
		return headers;
	}
	
	//댓글 리스트 예외
}
