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
	String saveFolder = "file_save/public"; //파일이 업로드되는 폴더를 지정
	String realFolder = application.getRealPath(saveFolder); //저장할 디렉토리 (절대경로)
	int sizeLimit = 5 * 1024 * 1024 ; // 5메가까지 제한 넘어서면 예외발생
	try{
	/*MultipartRequest multi = new MultipartRequest(1, 2, 3, 4, 5);
	1. 전송할 파일의 정보를 갖고 있는 request의 instance -> jsp의 request
	2. 서버상의 절대 경로 -> String realFolder = application.getRealPath("file_save/public");
	3. 최대 업로드될 파일 크기 -> 5M -> int maxSize = 5 * 1024 * 1024;
	4. 문자 코드 타입 - 인코딩 타입 -> "euc-kr"
	5. 기본 보안 적용 -> new DefaultFileRenamePolicy()
	*/
	 MultipartRequest multi = new MultipartRequest(request, realFolder, sizeLimit, "EUC-KR", new DefaultFileRenamePolicy());  
	 Enumeration formNames=multi.getFileNames();  // 폼의 이름 반환
	 
	 String formName = (String)formNames.nextElement(); 
	 String fileName = multi.getFilesystemName(formName); // 파일의 이름 얻기 
	 String title = multi.getParameter("title");
	 String writer = multi.getParameter("writer");
	 String writeDate = multi.getParameter("writeDate");
	 String modifier = multi.getParameter("modifier");
	 String modifyDate = multi.getParameter("modifyDate");
	 String category = multi.getParameter("category");
	 String explanation = multi.getParameter("explanation");%>
	 
	 <jsp:useBean id="boardMgr" class="boardBean.BoardMgr" scope="page"/>
	 <%if(fileName == null) {%>
	 <script>
		alert("파일 업로드를 하지 않았습니다.");
		history.back(-1);
	</script>
	<%} else {
		boardMgr.insertBoard(title, writer, writeDate, modifier, modifyDate, category, fileName, explanation);
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