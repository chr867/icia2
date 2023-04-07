package com.board.icia.servers;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketTest extends TextWebSocketHandler {

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		log.info("접속");
		log.info("session : {}", session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		log.info("수신");
		log.info("session : {}", session);
		log.info("message : {}", message);

		String msg = message.getPayload();
		session.sendMessage(new TextMessage(msg));
		
		if(msg.equals("1")){
			session.sendMessage(new TextMessage("마 쪼리나"));			
		}
		
		if(msg.equals("2")){
			session.sendMessage(new TextMessage("쫄리면 쫄린다 하든가"));			
		}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		log.info("종료");
		log.info("session : {}", session);
		log.info("status : {}", status);
	}
	
	
	
	
}
