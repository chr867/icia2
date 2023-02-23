<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"
	integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
	crossorigin="anonymous"></script>
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
				<td>
					<input type="text" id="id" name="m_id">
					<input type="button" id="check_id" value="중복검사">
					<p id="result"></p>
				</td>
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
	
	$('#check_id').on('click',function(){
		if($('#id').val() != ''){
			$.ajax({
				method : 'get',
				url: '/member/userid', //대/중/소 (가능하면 명사)
				data: {m_id : $('#id').val()},
				dataType : 'html',
			}).done((res,status,xhr)=>{
				console.log(res)
				console.log(status)
				console.log(xhr)
				$('#result').html(res).css('color','blue')
			}).fail((err,status)=>{
				$('#result').html(err.responseText).css('color','red')
				console.log(err)
				console.log(status)
			})
		} // if end
	}) //on end
	
	</script>
</body>
</html>