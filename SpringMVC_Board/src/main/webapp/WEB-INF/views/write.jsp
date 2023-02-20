<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<!-- summernote css/js -->
<link rel="stylesheet" href="/css/summernote-lite.css">
<script src="/js/summernote-lite.js"></script>
<script src="/js/summernote-ko-KR.js"></script>
<script>
	$(()=>{
		$('#summernote').summernote({
			  height: 300,                 // 에디터 높이
			  minHeight: null,             // 최소 높이
			  maxHeight: null,            // 최대 높이
			  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
			  lang: "ko-KR",					// 한글 설정
			  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
		})
	})
</script>
</head>
<body>
<h1>write.jsp</h1>
<h2 style="text-align: center;">글 작성</h2>
	
	<div style="width: 60%; margin: auto;">
		<form method="post" action="write" method="post" enctype="multipart/form-data" >
			<input type="text" name="b_title" style="width: 40%;"
				placeholder="제목"/> <br>
			<br>
			<textarea id="summernote" name="b_contents"></textarea>
			<br>
			파일첨부:<input type="file" name="files" id="files" multiple> 
			<input	type="hidden" id="fileCheck" name="fileCheck" value="0"> 
			<input	id="subBtn" type="button" value="글 작성"	onclick="goWrite(this.form)" />
			<input id="reset" type="reset"	value="취소"> 
		</form>
	</div>
	<script>
	
	function goWrite(frm){
		const title = frm.b_title.value
		const contents = frm.b_contents.value
//		console.log(frm.b_title.value) //input 태그
//		console.log(frm.b_contents.innerHTML) //공백=>&nbsp textarea 태그
		//console.log(typeof contents)
		if(title.trim()=='') {
			alert('제목을 입력해주세요')
			return false;
		}
		if(contents.trim()=='') {
			alert('내용을 입력해주세요')
			return false;
		}
		frm.submit()
	}
	</script>
</body>
</html>