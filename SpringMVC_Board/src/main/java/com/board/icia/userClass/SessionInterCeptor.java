package com.board.icia.userClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterCeptor implements HandlerInterceptor{
	//default method (구현하지 않아도 에러X)

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("pre");
		HttpSession session=request.getSession();
		if(session.getAttribute("id")==null){
			System.out.println("비 로그인 상태");
			response.sendRedirect("/member/");
			return false;
		};
		return true;
	}
	
}
