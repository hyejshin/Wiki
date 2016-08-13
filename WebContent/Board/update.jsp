<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<%@include file="../top.jsp"%>
<%@page import="DAO.BoardImpl" %>
<%@page import="DTO.BoardBean" %>

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
	}
	table, th, td{
		border: 1px solid #CEECF5;
	}
	.no-border{
		border: 0px;
	}
	
	th {
		width: 150px;
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

<title>글 수정</title>
</head>
<body>
<h2>글 수정</h2>

<%BoardBean bean = (BoardBean)request.getAttribute("Bean");%>
<form method="post" action="/Wiki/BoardServlet?actionMode=UPDATE">
<input type="hidden" name="idx" value=<%=bean.getIdx()%>>
<table>
<tr><th>글 제목</th><td colspan="3"><input type="text" name="title" size="80" value=<%=bean.getTitle()%>></td></tr>
<tr><th>작성자</th> <td><input type="text" name="writer" size="28" value=<%=bean.getWriter()%>></td>
	<th>작성일</th> <td><input type="text" name="writeDate" size="28" value=<%=bean.getWriteDate()%>></td></tr>
	<tr><th>수정자</th> <td><input type="text" name="modifier" size="28" value=<%=bean.getModifier()%>></td>
	<th>수정일</th> <td><input type="text" name="modifyDate" size="28" value=<%=bean.getModifyDate()%>></td></tr>
	<tr><th>카테고리</th><td colspan="3"><input type="text" name="category" size="80" value=<%=bean.getCategory()%>></td></tr>
<tr><th>이미지</th><td colspan="3"><input type="file" name="file" size="60" value=<%=bean.getImage()%>></td></tr>
<tr><td colspan="4"><textarea rows="15" cols="92" name="explanation"><%=bean.getExplanation()%></textarea></td></tr>
</table>

<!-- 버튼부분 -->
<table class="no-border">
<tr class="no-border"><td class="no-border"colspan="5" height="20"></td></tr>
<tr class="no-border"><td class="no-border"width="200"></td>
		<td class="no-border"><input class="button" type="submit" value="수정"></td>
		<td class="no-border" width="50"></td>
		<td class="no-border"><input class="button" type="button" value="취소" onClick="history.back(-1);"></td>
		<td class="no-border" width="200"></td></tr>
</table>
</form>

</body>
</html>