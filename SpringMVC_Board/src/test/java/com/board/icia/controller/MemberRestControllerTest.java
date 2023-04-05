package com.board.icia.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@ExtendWith(SpringExtension.class) // junit5
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
@WebAppConfiguration // controller Test시 필수
public class MemberRestControllerTest {
	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc; // 가짜 객체

	@BeforeEach // junit5 모든 @Test 전에 매번 실행
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		System.out.println("BeforeEach");
	}

	@Test
	public void testIdCheck() throws Exception {

		mockMvc.perform(get("/member/userid").param("m_id", "ttttt")).andExpect(status().isOk()
				).andExpect(content().string("사용할 수 있는 아이디")
				).andDo(print());
	}

}
