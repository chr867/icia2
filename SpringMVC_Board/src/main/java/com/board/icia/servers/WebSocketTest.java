package com.board.icia.servers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketTest extends TextWebSocketHandler {
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	
		log.info("접속");
		
		sessionList.add(session);
		log.info("session : {}", session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		log.info("수신");
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
		
		log.info("종료");
		log.info("session : {}", session);
		log.info("status : {}", status);
	}
	
	
	
	
}
