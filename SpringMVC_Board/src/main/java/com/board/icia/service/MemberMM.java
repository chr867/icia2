package com.board.icia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.icia.dao.IMemberDao;
import com.board.icia.dto.MemberDto;

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


}
