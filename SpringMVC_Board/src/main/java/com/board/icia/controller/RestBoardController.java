package com.board.icia.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.icia.dto.ReplyDto;
import com.board.icia.service.BoardMM;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RestBoardController {
	@Autowired
	private BoardMM bm;
	
	//댓글 쓰기
	/*
	 * @PostMapping("/board/reply") public @ResponseBody List<ReplyDto>
	 * reply_insert(ReplyDto reply,HttpSession session) {
	 * log.info(reply.toString());
	 * reply.setR_id(session.getAttribute("id").toString()); List<ReplyDto>
	 * reply_list = bm.reply_insert(reply); // ObjectMapper object_Mapper = new
	 * ObjectMapper(); // String json=object_Mapper.writeValueAsString(reply_list);
	 * return reply_list; //json 형태로 변환 (jackson) }
	 */
	
	@PostMapping("/board/reply") 
	//consumes = "application/json;charset=UTF-8" 파라미터 데이터 형식 (한글 깨짐 방지)
	//produces = "application/json;charset=UTF-8" 리턴 할 데이터 형식 (한글 깨짐 방지)
	//consumes = "text/plain;charset=UTF-8" 파라미터 데이터 형식 (순수한 텍스트)
	public HashMap<String, Object> reply_insert(@RequestBody ReplyDto reply,HttpSession session) {
		log.info(reply.toString());
		reply.setR_id(session.getAttribute("id").toString());
		List<ReplyDto> reply_list = bm.reply_insert(reply);
		String paging = bm.getPaging(1);
		HashMap<String, Object> hMap=new HashMap<String, Object>();
		hMap.put("reply_list", reply_list);
		hMap.put("paging", paging);
		return hMap; //json 형태로 변환 (jackson)
	}
}
