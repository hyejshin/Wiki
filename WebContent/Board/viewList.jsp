<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<%@page import="java.util.ArrayList" %>
<%@page import="DAO.BoardImpl" %>
<%@page import="DTO.BoardBean" %>
<%@include file="../top.jsp"%>

<link rel="stylesheet" type="text/css" href="/Wiki/CSS/viewListStyle.css">

<title>글 목록</title>
</head>
<body>
<h2>글 목록</h2>

<table>
<tr> <th width="50">번호</th> <th width="400">제목</th> <th>작성자</th> <th>날짜</th> </tr>
<%
	BoardBean bean = new BoardBean();
	ArrayList list = (ArrayList)request.getAttribute("List");
	
	for(int i=0; i<list.size(); i++){
		bean = (BoardBean)list.get(i);
	%>
	<tr><td><%=bean.getIdx()%></td>
	<td><a href="/Wiki/BoardServlet?actionMode=VIEW&idx=<%=bean.getIdx()%>"><%=bean.getTitle()%></a></td>
		<td><%=bean.getWriter()%></td><td><%=bean.getWriteDate()%></td></tr>
	<%} %>
</table>

<form name="joinForm" method="post" action="/Wiki/Board/write.jsp">
<table class="no-border">
<tr class="no-border"><td class="no-border"colspan="5" height="20"></td></tr>
<tr class="no-border"><td class="no-border"width="200"></td>
		<td class="no-border"></td>
		<td class="no-border"><input class="button" type="submit" value="글등록"></td>
		<td class="no-border"></td>
		<td class="no-border" width="200"></td></tr>
</table>
</form>

</body>
</html>