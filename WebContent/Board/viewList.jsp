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
<style type="text/css">
	body {
		font-family: 'Nanum Gothic', sans-serif;
		font-size: 13pt;
	}
	h2 {
		font-family: 'hanna', sans-serif;
		text-align: center;
	}
	
	table {
		width: 800px;
		margin-left: auto;
		margin-right: auto;
		text-align: center;
	}
	table, th, td{
		border: 1px solid #848484;
	}
	.no-border{
		border: 0px;
	}
	
	th {
		height: 30px;
		text-align: center;
		background-color: #81DAF5;
	}
	
	.button{
		width: 100px;
		height: 30px;
		font-size: 13pt;
		font-family: 'Nanum Gothic', sans-serif;
	}
</style>
<title>글 목록</title>
</head>
<body>
<h2>글 목록</h2>

<table>
<tr> <th width="50">번호</th> <th width="400">제목</th> <th>작성자</th> <th>날짜</th> </tr>
<%
	BoardBean bean = new BoardBean();
	//ArrayList list = (ArrayList)request.getAttribute("List");
	BoardImpl bi = new BoardImpl();
	ArrayList list = (ArrayList)bi.bList();
	
	for(int i=0; i<list.size(); i++){
		bean = (BoardBean)list.get(i);
	%>
	<tr><td><%=bean.getIdx()%></td>
	<td><a href="/Wiki/BoardServlet?actionMode=VIEW&idx=<%=bean.getIdx()%>"><%=bean.getTitle()%></a></td>
		<td><%=bean.getWriter()%></td><td><%=bean.getWriteDate()%></td></tr>
	<%} %>
</table>

</body>
</html>