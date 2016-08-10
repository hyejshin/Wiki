<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="com.oreilly.servlet.MultipartRequest, 
com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*, java.io.*" %>
<%@ page import="java.sql.*, java.util.*, boardBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�� ��� ó��</title>
</head>
<body>
<% 
	//������ ���丮 (������)
	String savePath=application.getRealPath("");
	int sizeLimit = 5 * 1024 * 1024 ; // 5�ް����� ���� �Ѿ�� ���ܹ߻�
	try{
	 MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "EUC-KR", new DefaultFileRenamePolicy());  
	 Enumeration formNames=multi.getFileNames();  // ���� �̸� ��ȯ
	 
	 String formName = (String)formNames.nextElement(); 
	 String fileName = multi.getFilesystemName(formName); // ������ �̸� ��� 
	 String title = multi.getParameter("title");
	 String writer = multi.getParameter("writer");
	 String writeDate = multi.getParameter("writeDate");
	 String content = multi.getParameter("content");%>
	 
	 <jsp:useBean id="boardMgr" class="boardBean.BoardMgr" scope="page"/>
	 <%if(fileName == null) {%>
	 <script>
		alert("���� ���ε带 ���� �ʾҽ��ϴ�.");
		history.back(-1);
	</script>
	<%} else {
		boardMgr.insertBoard(title, writer, writeDate, fileName, content);
	%>
	<script>
		alert("����� �Ϸ�Ǿ����ϴ�.");
		location.href="Home.jsp";
	</script>
	<%}
	 } catch (Exception e) {
		System.out.println(e);
	}%>

</body>
</html>