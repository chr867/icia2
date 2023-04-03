package com.board.icia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.icia.dao.IMemberDao;
import com.board.icia.dto.MemberDto;
import com.board.icia.exception.InvaildIdException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberMM {
	@Autowired
	//private MemberDao mDao;
	private IMemberDao mDao;
	
	public MemberDto access(MemberDto mb) {
		//복호화는 안되지만 비교는 가능
		BCryptPasswordEncoder pwEncoder=new BCryptPasswordEncoder();
		String encoPw=mDao.getSecurityPw(mb.getM_id());
		if(encoPw!=null) {
			if(pwEncoder.matches(mb.getM_pw(), encoPw)) {
				log.info("로그인 성공");
				return mDao.getMemberInfo(mb.getM_id());
			}else {
				log.info("비번 오류");
				return null;
			}
			
		}else {
			log.info("아이디 오류");
			return null;
		}
		//1111입력 <-> db저장 pw와 비교
		
//		return mDao.access(mb);
	}

	public boolean join(MemberDto mb) {
		//Encoder(암호화)<->Decoder(복호화)
		//스프링은 복호화 불가능
		BCryptPasswordEncoder pwEncoder=new BCryptPasswordEncoder();
		mb.setM_pw(pwEncoder.encode(mb.getM_pw()));
		return mDao.join(mb);
	}

	public String id_avaliable(String m_id) {
		MemberDto mb=mDao.getMemberInfo(m_id);
		if(mb!=null) throw new InvaildIdException("사용할 수 없는 아이디.");
		return "사용할 수 있는 아이디";
	}

	public List<Map<String, Object>> test_map(HashMap<String, String> hMap) {
		List<Map<String,Object>> m_list= mDao.test_map(hMap);
		return m_list;
	}

	public List<Map<String, Object>> test_params(String cName, Integer search) {
		List<Map<String, Object>> m_list=mDao.test_params(cName,search);
		return m_list;
	}

}
