package com.board.icia.servers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketTest extends TextWebSocketHandler {

	private class PrincipalHandshake extends DefaultHandshakeHandler{
		@Override
		protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
				Map<String, Object> attributes) {
			String name = UUID.randomUUID().toString();
			return new Principal() 
			
			{	
				@Override
				public String getName() {
					return name;
				}
			};
		}
		
	}
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	
		log.info("접속 완료");
		
		if(sessionList.contains(session)) sessionList.remove(sessionList.indexOf(session));
		else sessionList.add(session);
		
		System.out.println("접속 중인 인원 : " + sessionList.size());
		log.info("session : {}", session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		log.info("수신 완료");
		log.info(session.getId() + " : " + message);
		
		String msg = message.getPayload();
		for(WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage(msg));  // 발신 시 좌측 정렬 수신 시 우측 정렬
		}
		
//		if(msg.contains("안녕")){
//			Thread.sleep(1000);
//			session.sendMessage(new TextMessage("마 쪼리나"));
//		}
//		
//		if(msg.contains("올려줘")){
//			session.sendMessage(new TextMessage("쫄리면 쫄린다 하든가"));			
//		}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		log.info("접속 종료");
		log.info("session : {}", session);
		System.out.println("접속 중인 인원 : " + sessionList.size());
		
		sessionList.remove(sessionList.indexOf(session));
	}
		
}
