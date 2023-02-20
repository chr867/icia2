<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>joinFrm.jsp--회원가입 양식</h1>
	<form name="joinFrm" action="/member/join" method="post" onsubmit="return check()">

		<table border="1">
			<tr>
				<td colspan="2" class="subject">회원가입</td>
			</tr>
			<tr>
				<td width="100">ID</td>
				<td><input type="text" id="id" name="m_id"></td>
			</tr>
			<tr>
				<td width="100">PW</td>
				<td><input type="password" id="pw" name="m_pw"></td>
			</tr>
			<tr>
				<td width="100">NAME</td>
				<td><input type="text" id="name" name="m_name"></td>
			</tr>
			<tr>
				<td width="100">BIRTH</td>
				<td><input type="text" id="birth" name="m_birth"></td>
			</tr>
			<tr>
				<td width="100">ADDR</td>
				<td><input type="text" id="addr" name="m_addr"></td>
			</tr>
			<tr>
				<td width="100">PHONE</td>
				<td><input type="text" id="phone" name="m_phone"></td>
			</tr>
			<tr>
				<td colspan="2" class="subject"><input type="submit"
					value="회원가입">
					<input type="reset" value="취소"/>
					</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
	//jQuery validate(유효성 검사)
	const check=()=>{
	console.log("ㅇㅇㅇㅇ")
	let frm = document.joinFrm
	let length=frm.length-1
	for(let i =0; i<length; i++){
		if(frm[i].value==''){
			alert(frm[i].name+"을 입력하세요!!!")
			frm[i].focus();
			return false; //실패시
		}
	}
	 return true; //성공시 서버 전송
	}
	</script>
</body>
</html>