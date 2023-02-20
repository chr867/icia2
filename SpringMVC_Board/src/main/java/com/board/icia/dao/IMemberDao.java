package com.board.icia.dao;

import org.springframework.stereotype.Repository;

import com.board.icia.dto.MemberDto;

@Repository
public interface IMemberDao {

	boolean access(MemberDto mb);

	boolean join(MemberDto mb);

	String getSecurityPw(String m_id);

	MemberDto getMemberInfo(String m_id);
	
}
