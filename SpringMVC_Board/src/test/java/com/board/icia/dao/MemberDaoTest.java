package com.board.icia.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.board.icia.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@ExtendWith(SpringExtension.class)   //junit5
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class MemberDaoTest {
	
	@Autowired
	private IMemberDao dao;
	
	@Test
	public void loginTest() {
		
		log.info("dao = " + dao);

		assertNotEquals(null, dao);
		assertNotNull(dao);
		assertEquals("에이", dao.getMemberInfo("aaa").getM_name());
		
	}

//	@Test
	@Transactional
	public void insertTest() {
		
		MemberDto mb = new MemberDto();
		mb.setM_id("yyy").setM_pw("1111");
		
	}
	
	
	
}
