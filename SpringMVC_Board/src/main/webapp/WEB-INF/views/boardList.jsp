<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"
	integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM="
	crossorigin="anonymous"></script>
<script src="/js/jquery.serializeObject.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>

</head>
<body>
	<h1>boardList.jsp --게시판 목록</h1>
	<c:if test="${!empty id}">
		<div align="right">
			<form id="logoutFrm" action="/member/logout" method="post">
				<a href="javascript:logout()">로그아웃</a>

			</form>
		</div>
	</c:if>

	<table id="one_table">
		<tr height="30">
			<td width="80" bgcolor="pink" align="center">ID</td>
			<td>${member.m_id}</td>
		</tr>
		<tr height="30">
			<td width="80" bgcolor="pink" align="center">NAME</td>
			<td>${member.m_name}</td>
		</tr>
		<tr height="30">
			<td width="80" bgcolor="pink" align="center">GNAME</td>
			<td>${member.m_grade}</td>
		</tr>
		<tr height="30">
			<td width="80" bgcolor="pink" align="center">POINT</td>
			<td>${member.m_point}</td>
		</tr>
	</table>

	<table>
		<tr bgcolor="skyblue" height="30">
			<th width="100">번호</th>
			<th width="100">제목</th>
			<th width="100">작성자</th>
			<th width="100">작성일</th>
			<th width="100">조회수</th>
		</tr>
		<c:forEach var="board" items="${bList}">
			<tr height="25">
				<td align="center">${board.b_num}</td>
				<!-- href="#" 페이지 맨위로 이동뒤 이벤트 발생
				     href="#;" 페이지 현재위치에서 이벤트 발생 -->
				<td align="center"><a href="#" data-bs-toggle="modal"
					data-bs-target="#myModal" onclick="articleView(${board.b_num})">
						${board.b_title}</a></td>
				<td align="center">${board.b_id}</td>
				<td align="center">${board.b_date}</td>
				<td align="center">${board.b_views}</td>
			</tr>
		</c:forEach>
	</table>

	<div align="center">${paging}</div>

	<!-- The Modal -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">글상세 보기</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">Modal body..</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
	<!-- Modal End -->
	
<script type="text/javascript">
	function logout() {
		$('#logoutFrm').submit();
	}

	function articleView(bnum){
		$.ajax({
			method:'get',
			url: '/board/contents',//+bnum,
			data:{b_num:bnum},
//			서버에서 받을 데이터 형식 (스프링에선 생략 가능)
//			dataType:'json', //html(text),xml,jsonp
		}).done(res=>{
			console.log(res)
			$('.modal-body').html(res)
		}).fail(err=>{
			console.log(err)	
		})
	}
		
	/* $(()=>{
		$.ajax({
			url:'/board/list',
			
		}).done(res=>{
			res.boardList10
			res.게시글 개수
		})
		
	}) */
</script>
</body>
</html>