package com.board.icia.dto;



import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Alias("board")

public class BoardDto {
	private int b_num;
	private String b_title;
	private String b_contents;
	private String b_id;	//fk

	private Timestamp b_date;	//오라클 date형,to_char()
	private int b_views;
}
