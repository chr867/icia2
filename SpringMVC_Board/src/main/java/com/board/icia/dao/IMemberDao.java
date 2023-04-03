package com.board.icia.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.board.icia.dto.MemberDto;

@Repository
public interface IMemberDao {

	boolean access(MemberDto mb);

	boolean join(MemberDto mb);

	String getSecurityPw(String m_id);

	MemberDto getMemberInfo(String m_id);

	List<Map<String, Object>> test_map(HashMap<String, String> hMap);

	List<Map<String, Object>> test_params(@Param("cName") String cName, @Param("search") Integer search);
	
}
