<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>write.jsp</h1>
<h2 style="text-align: center;">�� �ۼ�</h2>
	
	<div style="width: 60%; margin: auto;">
		<form method="post" action="write" method="post"
			enctype="multipart/form-data">
			<input type="text" name="b_title" style="width: 40%;"
				placeholder="����" /> <br>
			<br>
			<textarea id="summernote" name="b_contents"></textarea>
			<br>
			<!-- ����÷��:<input type="file" name="files" id="files" multiple>  -->
			<input	type="hidden" id="fileCheck" name="fileCheck" value="0"> 
			<input	id="subBtn" type="button" value="�� �ۼ�"	onclick="goWrite(this.form)" />
			<input id="reset" type="reset"	value="���"> 
		</form>
	</div>
</body>
</html>