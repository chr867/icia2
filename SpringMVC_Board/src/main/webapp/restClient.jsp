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
<h1>restClient.jsp</h1>

<script>
$.ajax({
	method:'get',
//	web.xml 필터 설정 후 _method=put, patch, delete 요청시
//  method:post로 설정(단, json으로는 못넘김)
//	url: '/member/rest-test',
//	url: '/member/patch',
	url: '/member/account/male/1',
//	data: {m_id: 'aaa', m_name:'홍길동'},
//	data: {num:100},
//	data: {_method:'patch',num:100},
//	dataType : 'json',
//	contentType: 'application/json;charset=utf-8'
}).done((data, status, xhr)=>{
	console.log(data) //json이 js객체로 변환
	if(data.num==1){
		location.href="/board/list"
	}
	console.log(status)
	console.log(xhr)
}).fail((xhr,status)=>{
	console.log(xhr)
	console.log(status)
}) 
	

</script>


</body>
</html>