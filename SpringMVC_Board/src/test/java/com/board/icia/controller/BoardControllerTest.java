package com.board.icia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.board.icia.dto.BoardDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@ExtendWith(SpringExtension.class)   //junit5
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
@WebAppConfiguration  // controller Test시 필수
public class BoardControllerTest {
	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;  // 가짜 객체
	
//	@Before  // junit4
//	@BeforeAll  // junit5 모든 @Test 전에 1번만 static 메소드를 실행
	@BeforeEach  // junit5 모든 @Test 전에 매번 실행
	public void setup() {	
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		System.out.println("BeforeEach");
	}
	
	@Test
	public void test() {
		System.out.println("test1");
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void listTest() throws Exception {
		System.out.println("test2");
		
		MvcResult result = mockMvc.perform(get("/board/list").param("pageNum", "1")).andReturn(); 
		List<BoardDto> bList = (List<BoardDto>)result.getModelAndView().getModel().get("bList");
//		List<BoardDto> bList = (List<BoardDto>)result.getModelAndView().getModelMap().getAttribute("bList");
		
		assertEquals(bList.size(), 10);
		
		log.info("1= " + bList);		
	}
	
	
}
