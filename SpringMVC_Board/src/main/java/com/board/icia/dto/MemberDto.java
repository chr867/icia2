package com.board.icia.dto;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain =true)
@Alias("member")
public class MemberDto {
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_birth;
	private String m_addr;
	private String m_phone;
	
	private int m_point;
	private String m_grade;

}
