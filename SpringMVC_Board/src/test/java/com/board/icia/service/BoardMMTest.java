package com.board.icia.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.board.icia.dto.BoardDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class) //junit4
@ExtendWith(SpringExtension.class)   //junit5
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class BoardMMTest {
	
	@Autowired
	private BoardMM bm;
	
	@Test
	public void testExist() {		
		log.info("bm = "+ bm);
		assertNotEquals(bm, null);
	}

	@Test
	public void testGetList() {
		
		List<BoardDto> bList=bm.getBoardList(2);
		bList.forEach(b -> log.info("b = "+ b));
		
		assertEquals(9, bList.size());
		assertNotEquals(bm, null);
	}
	
}
