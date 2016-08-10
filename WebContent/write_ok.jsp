<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="com.oreilly.servlet.MultipartRequest, 
com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*, java.io.*" %>
<%@ page import="java.sql.*, java.util.*, boardBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글 등록 처리</title>
</head>
<body>
<% 
	//저장할 디렉토리 (절대경로)
	String savePath=application.getRealPath("");
	int sizeLimit = 5 * 1024 * 1024 ; // 5메가까지 제한 넘어서면 예외발생
	try{
	 MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "EUC-KR", new DefaultFileRenamePolicy());  
	 Enumeration formNames=multi.getFileNames();  // 폼의 이름 반환
	 
	 String formName = (String)formNames.nextElement(); 
	 String fileName = multi.getFilesystemName(formName); // 파일의 이름 얻기 
	 String title = multi.getParameter("title");
	 String writer = multi.getParameter("writer");
	 String writeDate = multi.getParameter("writeDate");
	 String content = multi.getParameter("content");%>
	 
	 <jsp:useBean id="boardMgr" class="boardBean.BoardMgr" scope="page"/>
	 <%if(fileName == null) {%>
	 <script>
		alert("파일 업로드를 하지 않았습니다.");
		history.back(-1);
	</script>
	<%} else {
		boardMgr.insertBoard(title, writer, writeDate, fileName, content);
	%>
	<script>
		alert("등록이 완료되었습니다.");
		location.href="Home.jsp";
	</script>
	<%}
	 } catch (Exception e) {
		System.out.println(e);
	}%>

</body>
</html>