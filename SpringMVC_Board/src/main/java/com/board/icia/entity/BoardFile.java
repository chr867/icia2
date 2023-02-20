package com.board.icia.entity;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;

//Entity 테이블 구조와 동일하게

@Data
@Accessors(chain = true)
@Alias ("boardfile")
public class BoardFile {
	private int bf_num;
	private int bf_bnum;
	private String bf_origName;
	private String bf_sysname;
}
