package com.board.icia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

	@RequestMapping("/basic")
	public String basic() {
		return "basic";
	}
	
}
