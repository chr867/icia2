<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>basic.jsp</h1>

<button onclick="connect()">접속</button>
<button onclick="disconnect()">접속 종료</button>
<hr>
<input type="text" id="chat-input">
<button onclick="send()">전송</button>


<script>

	let socket;
		
	function connect() {
		let url = "ws://"
		url += location.host;
		url += "${pageContext.request.contextPath}";
		url += "/basic";
		
		socket = new WebSocket(url);
		
	 	socket.onopen = function(){
			console.log("서버와 연결 중");
		}

		socket.onclose= function(){
			console.log("서버와 연결 종료");
		}

		socket.onerror = function(){
			console.log("서버와 연결 중 오류 발생");
		}

		socket.onmessage = function(evt){
			onMessage(evt);
		}
	}
	
	function onMessage(evt) {
		console.log(evt.data);
	}
	
	
	function disconnect(){
		socket.close();
	}
	
	function send(){
		let text = document.querySelector("#chat-input").value;
		if(!text){
			return;
		}
		
		socket.send(text);
		document.querySelector("#chat-input").value = ""
	}
</script>
</body>
</html>