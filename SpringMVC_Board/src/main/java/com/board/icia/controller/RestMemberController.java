package com.board.icia.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.board.icia.dto.MemberDto;
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
	
//	@GetMapping(value="/rest-test",produces = "text/plain;charset=utf-8")
//	public String rest_test(MemberDto mb) {
	@GetMapping("/rest-test")
	public MemberDto rest_test(MemberDto mb) {
		System.out.println("MIME TYPE: "+ MediaType.APPLICATION_JSON_VALUE);
		System.out.println("MIME TYPE: "+ MediaType.TEXT_PLAIN_VALUE);
		System.out.println("MIME TYPE: "+ MediaType.APPLICATION_JSON_UTF8_VALUE);
		System.out.println("MIME TYPE: "+ MediaType.APPLICATION_XML_VALUE);
//		Service->DB
		return mb; //jackson(메세지 컨버터)에서 json으로 변환
	}
	
	@PostMapping(value="/post", produces = "application/json;charset=utf-8")
	public String[] post(Integer num) {
		return new String[] {"insert result:"+num};
	}

	@DeleteMapping(value="/delete", produces = "application/json;charset=utf-8")
	public String[] delete(Integer num) {
		return new String[] {"insert result:"+num};
	}
	
	@PutMapping(value="/put", produces = "application/json;charset=utf-8")
	public String[] put(Integer num) {
		return new String[] {"insert result:"+num};
	}
	
	@PatchMapping(value="/patch", produces = "application/json;charset=utf-8")
	public String[] patch(Integer num) {
		return new String[] {"insert result:"+num};
	}
	
//	url: "/account/male/1"
	@GetMapping(value = "/{dept}/{gender}/{num}")
	public HashMap<String, String> path_variable(@PathVariable String dept,
												 @PathVariable String gender,
												 @PathVariable ("num") Integer num){
		System.out.println("dept: "+dept);
		System.out.println("gender: "+gender);
		System.out.println("num: "+num);
		HashMap<String, String> h_map=new HashMap<String, String>();
		h_map.put("dept", dept);
		h_map.put("gender", gender);
		h_map.put("num", String.valueOf(num)); //num+"
		return h_map;
	}
	
	
}
