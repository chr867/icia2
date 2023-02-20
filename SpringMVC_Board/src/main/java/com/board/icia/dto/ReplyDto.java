package com.board.icia.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Alias("reply")

public class ReplyDto {
	private int r_num; // pk 댓글번호
	private int r_bnum; // fk 게시글 번호
	private String r_contentes; //댓글 내용
	private String r_id;
	//jackson Timestamp 서로 호환되지 않음
	
	//날짜 형식을 Json으로 주고 받을때
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	//날짜 형식을 Json 외의 형태로 주고받을 때
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") 
//	private LocalDateTime r_date; //오라클 to_char(날짜 데이터,'YYYY/MM/DD, HI24:MI:SS')
	private String r_date; //오라클 to_char(날짜 데이터,'YYYY/MM/DD, HI24:MI:SS')
	//LocalDateTime 레거시는 메이븐에 jackson-datatype-jsr310 추가
}
