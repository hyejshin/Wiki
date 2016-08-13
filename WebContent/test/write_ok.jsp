<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.oreilly.servlet.MultipartRequest,
                   com.oreilly.servlet.multipart.DefaultFileRenamePolicy,
                   java.util.*" %>                
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write_ok.jsp 1315842신혜정</title>
<style type="text/css">
	h2 {text-align: center}
	#table {margin: 0px auto}
	td.head {background: pink; font-weight: bold; text-align: center}
	#button {text-align: center}
</style>
</head>
<body>
<% 
	//저장할 디렉토리 (절대경로)
	String savePath=application.getRealPath("");
	int sizeLimit = 5 * 1024 * 1024 ; // 5메가까지 제한 넘어서면 예외발생
	try{
	 MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());  
	 Enumeration formNames=multi.getFileNames();  // 폼의 이름 반환
	 // 자료가 많을 경우엔 while 문을 사용
	 String formName=(String)formNames.nextElement(); 
	 String fileName=multi.getFilesystemName(formName); // 파일의 이름 얻기 
	 String title = multi.getParameter("title");
	 String content = multi.getParameter("content");%>
	 
	 <%if(fileName == null) {   // 파일이 업로드 되지 않았을때
	   out.print("파일 업로드 되지 않았음");
	 } else {
	   String originalFilePath = application.getRealPath("") + "\\" + fileName;
	   %>
	   	<h2>게시글 등록 완료</h2>
		<hr width="800">
		<table id="table">
			<tr><td class="head">제목</td><td><input type="text" name="title" size="60"
			value="<%out.print(title); %>"></td><tr/>
			<tr><td class="head">사진</td><td><%out.print("<img src='./" + fileName + "' border='0'><br>" ); 
			out.print(originalFilePath);%></td><tr/>
			<tr><td class="head">내용</td><td><textarea name="content" rows="15" cols="80"><%out.print(content); %></textarea></td></tr>
		</table>
		<hr width="800">
	 <%}
	} catch(Exception e) {
	 out.print(e);
	} 
%>
</body>
</html>
