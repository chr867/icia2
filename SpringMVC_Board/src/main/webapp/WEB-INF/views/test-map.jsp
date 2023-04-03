<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>test-map.jsp</h1>

<c:forEach var="mb" items="${m_list}">
${mb.m_id},${mb.m_name},${mb.m_point},${mb.m_grade} <br>
</c:forEach>



</body>
</html>