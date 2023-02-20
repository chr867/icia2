<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- <script src="https://code.jquery.com/jquery-3.6.3.js"
	integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
	crossorigin="anonymous"></script> -->
	
</head>
<style>
#rTable td{
	text-align: center;
}
</style>
<body>
<h3>boardContentsAjax.jsp --글 상세 & 댓글 리스트</h3>
<table>
		<tr height="30">
			<td width="100" bgcolor="lightgray" align="center">NUM</td>
			<td colspan="5">${board.b_num}</td>
		</tr>
		<tr height="30">
			<td bgcolor="lightgray" align="center">WRITER</td>
			<td width="150">${board.b_id}</td>
			<td bgcolor="lightgray" align="center">DATE</td>
			<td width="150">${board.b_date}</td>
			<td bgcolor="lightgray" align="center">VIEWS</td>
			<td width="150">${board.b_views}</td>
		</tr>
		<tr height="30">
			<td bgcolor="lightgray" align="center">TITLE</td>
			<td colspan="5">${board.b_title}</td>
		</tr>
		<tr height="30">
			<td bgcolor="lightgray" align="center">CONTENTS</td>
			<td colspan="5" ><textarea readonly rows="5" cols="40">${board.b_contents}</textarea></td>
		</tr>
	</table>

	<form name="rFrm" id="rFrm">
		<!-- 댓글 입력 -->
		<table>
			<tr>
				<td><textarea rows="3" cols="50" name="r_contentes"
						id="r_contents"></textarea></td>
				<td><input type="button" value="댓글전송"
					onclick="replyInsert(${board.b_num})"
					style="width: 70px; height: 50px"></td>
			</tr>
		</table>
	</form>
	<!-- 댓글 출력 -->
	<table id="rTable">
		<thead>
			<tr>
				<td width="100">작성자</td>
				<td width="200">내용</td>
				<td width="200">작성일</td>
			</tr>
		</thead>
		<tbody id="reply_list">
		<c:forEach var="reply" items="${rList}">
			<tr height="25" align="center">
				<td width="100">${reply.r_id}</td>
				<td width="200">${reply.r_contentes}</td>
				<td width="200">${reply.r_date}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<template id="tr_template">
		<tr>
			<td width="100">{b_id}</td>
			<td width="200">{b_contents}</td>
			<td width="200">{b_date}</td>
		</tr>
	</template>

<script type="text/javascript">
function replyInsert(b_num){
/* 	let obj={}
	obj.r_bnum=b_num
	obj.r_contentes=$('#r_contents').val() */	
	
	/* form태그 요소데이터를 js객체로 변환, 단 file 태그가 있을때는 FormData 객체 사용할 것 */
	// form 태그의 name을 이용해 직렬화
	let obj=$('#rFrm').serializeObject();
	obj.r_bnum=b_num
//	alert('b_num: '+b_num)
	$.ajax({
		method : 'post', //json 넘길 시 post
		url : '/board/reply', //?'r_bnum='+bnum+'&r_contentes='+$('#r_contents').val()
//		1.get,post: url encoded 방식 - 한글, 특수문자 인코딩 됨
//		data : {r_bnum:bnum, r_contentes:$('#r_contents').val()}
//		data : {r_bnum:bnum, $('#rFrm').serialize(),} //file 태그는 제외
	
//		2.json 형식으로 넘김(서버에서 받을 때 @requestBody로 받아야 함)
		data : JSON.stringify(obj),
		contentType:'application/json;charset=UTF-8',
//		서버의 ContentType에 설정된 타입이 자동 설정됨
//		dataType : 'json', //js객체로 파싱
	}).done(res=>{
		console.log(res)
		let rList=''
		/* js for of문 */
/*   		for(let reply of res.reply_list){
			rList+='<tr height="25" align="center">'
			rList+='<td width="100">'+reply.r_id+'</td>'
			rList+='<td width="200">'+reply.r_contentes+'</td>'
			rList+='<td width="200">'+reply.r_date+'</td></tr>'
		}  */
		
		/* jQuery each문 */
/* 		$.each(res.reply_list,function(i,reply){
			rList+='<tr height="25" align="center">'
			rList+='<td width="100">'+reply.r_id+'</td>'
			rList+='<td width="200">'+reply.r_contentes+'</td>'
			rList+='<td width="200">'+reply.r_date+'/td></tr>'	
		}) */
		
		/* js forEach문 */
/* 		res.reply_list.forEach(reply=>{
			rList+='<tr height="25" align="center">'
			rList+='<td width="100">'+reply.r_id+'</td>'
			rList+='<td width="200">'+reply.r_contentes+'</td>'
			rList+='<td width="200">'+reply.r_date+'/td></tr>'	
		}) */

		/* template 활용 */
 		const $tbody=$('#rTable tbody')
		$tbody.empty()
		const $list=$('#reply_list')
		const $t = $('#tr_template').html()
		for(const r of res.reply_list){
			$list.append($t.replace('{b_id}',r.r_id)
						.replace('{b_contents}',r.r_contentes)
						.replace('{b_date}',r.r_date))
		}
		/* $('#rTable').html(rList); */
		$('#r_contents').val('').focus();
 	}).fail(err=>{
 		console.log(err)
 		$('#rTable').html(err.responseText); //에러 확인
 	})
}
</script>
	
</body>
</html>