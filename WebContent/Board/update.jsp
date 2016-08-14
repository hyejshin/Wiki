<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<%@include file="../top.jsp"%>
<%@page import="DAO.BoardImpl" %>
<%@page import="DTO.BoardBean" %>

<link rel="stylesheet" type="text/css" href="/Wiki/CSS/viewStyle.css">

<title>�� ����</title>
</head>
<body>
<h2>�� ����</h2>

<%BoardBean bean = (BoardBean)request.getAttribute("Bean");%>
<form method="post" action="/Wiki/BoardServlet?actionMode=UPDATE">
<input type="hidden" name="idx" value=<%=bean.getIdx()%>>
<table>
<tr><th>�� ����</th><td colspan="3"><input type="text" name="title" size="80" value=<%=bean.getTitle()%>></td></tr>
<tr><th>�ۼ���</th> <td><input type="text" name="writer" size="28" value=<%=bean.getWriter()%>></td>
	<th>�ۼ���</th> <td><input type="text" name="writeDate" size="28" value=<%=bean.getWriteDate()%>></td></tr>
	<tr><th>������</th> <td><input type="text" name="modifier" size="28" value=<%=bean.getModifier()%>></td>
	<th>������</th> <td><input type="text" name="modifyDate" size="28" value=<%=bean.getModifyDate()%>></td></tr>
	<tr><th>ī�װ�</th><td colspan="3"><input type="text" name="category" size="80" value=<%=bean.getCategory()%>></td></tr>
<tr><th>�̹���</th><td colspan="3"><input type="file" name="file" size="60" value=<%=bean.getImage()%>></td></tr>
<tr><td colspan="4"><textarea rows="15" cols="92" name="explanation"><%=bean.getExplanation()%></textarea></td></tr>
</table>

<!-- ��ư�κ� -->
<table class="no-border">
<tr class="no-border"><td class="no-border"colspan="5" height="20"></td></tr>
<tr class="no-border"><td class="no-border"width="200"></td>
		<td class="no-border"><input class="button" type="submit" value="����"></td>
		<td class="no-border" width="50"></td>
		<td class="no-border"><input class="button" type="button" value="���" onClick="history.back(-1);"></td>
		<td class="no-border" width="200"></td></tr>
</table>
</form>

</body>
</html>