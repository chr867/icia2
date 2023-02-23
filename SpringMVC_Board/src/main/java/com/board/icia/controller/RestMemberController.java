package com.board.icia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.board.icia.service.MemberMM;

@RestController //@ResponseBody 생략 가능
@RequestMapping("/member")
public class RestMemberController {
	@Autowired
	private MemberMM mm;
	
	@GetMapping(value = "/userid", produces = "text/plain;charset=utf-8"/* , consumes = ""*/ )
	public ResponseEntity<?> id_avaliable(String m_id) {
//		ResponseEntity<?> result = null;
//		if(mm.id_avaliable(m_id)) {
//			result=ResponseEntity.ok().body("사용가능한 아이디");
//		}else{
//			result=ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("사용할 수 없는 아이디");			
//		}
//		return result;
		
//		@RestControllerAdvice 를 이용해서 예외처리
		return ResponseEntity.ok(mm.id_avaliable(m_id));
		
		// MediaType.APPLICATION_JSON_VALUE
				// "application/json;charset=utf-8",
				// MediaType.TEXT_PLAIN_VALUE
				// "text/plain;charset=utf-8"
				// consumes, produces는 문제없으면 생략가능함
	}
}
