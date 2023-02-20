<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>

<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.1/dist/sweetalert2.all.min.js
"></script>
<script type="text/javascript">
$(()=>{
	let check=0
	check=${check}
	console.log(check)
	if(check==1){
	Swal.fire({
		icon: 'success',
		title: '회원가입 성공',
		text: '로그인 해주세요!'
	})
	}else if(check==2){
		Swal.fire({
			icon: 'error',
			title: '로그인 실패',
			text: '아이디 혹은 비번 오류입니다!'
		})
	}
})
</script>

<body>
<h1>Home.jsp-로그인 페이지</h1>
	<form action="/member/access" name="logFrm" method="post">
		<table border="1">
			<tr>
				<td colspan="2" align="center" bgcolor="skyblue">로그인</td>
			</tr>
			<tr>
				<td><input type="text" name="m_id"></td>
				<td rowspan="2"><button>로그인</button>
			</tr>
			<tr>
				<td><input type="password" name="m_pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center" bgcolor="skyblue">com.board.icia</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><a href="./join">회원가입</a></td>
			</tr>
		</table>
	</form>
	<h3>${msg}</h3>
</body>
</html>
