package com.board.icia.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.board.icia.service.BoardMM;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@ExtendWith(SpringExtension.class) // junit5
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
@WebAppConfiguration // controller Test시 필수
public class BoardRestControllerTest {

	@Autowired
	private WebApplicationContext ctx;
	
	@Mock
	private BoardMM mock_bm;
	
	private MockMvc mockMvc;
	
	@BeforeEach // junit5 모든 @Test 전에 매번 실행
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		System.out.println("BeforeEach");
	}
	
}
