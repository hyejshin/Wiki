<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<%@page import="com.member.db.MemberBean" %>
<%@page import="DAO.BoardImpl" %>
<%@page import="DTO.BoardBean" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.ArrayList" %>
<%@include file="../top.jsp"%>
<link rel="stylesheet" type="text/css" href="/Wiki/CSS/viewStyle.css">

<title>�� ����</title>

<script language="javascript">
function user(actionMode, idx){
	if(actionMode == 'update'){
		url = "AuthorityCheck.do?option=update&idx="+idx;
	}else if(actionMode == 'delete'){
		url = "BoardDelete.do?idx="+idx;
	}else if(actionMode == 'list'){
		url = "BoardList.do";
	}
	document.joinForm.method = "post";
	document.joinForm.action = url;
	document.joinForm.submit();
}
</script>
</head>
<body>
<h2>�� ����</h2>

<% 

//�ش� ���� �� ���������� �׽�Ʈ
//BoardImpl bl = new BoardImpl();
//BoardBean bean =  bl.bSelect(21);

BoardBean bean = (BoardBean)request.getAttribute("Bean");

//�̹����� ����� ���
String savePath = request.getServletContext().getRealPath("file_save");
String image = savePath + "\\" + bean.getImage();
 %>
<table>
<tr><th>�� ����</th><td colspan="3"><%=bean.getTitle()%></td></tr>
<tr><th>�����</th> <td width="230"><%=bean.getWriter()%></td> <th>�����</th> <td><%=bean.getWriteDate()%></td></tr>
<tr><th>������</th> <td width="230"><%=bean.getModifier()%></td> <th>����������</th> <td><%=bean.getModifyDate()%></td></tr>
<tr><th>ī�װ�</th><td colspan="3"><%=bean.getCategory()%></td></tr>
<% // ������ ���� ��쿡�� �̹��� ���
if (bean.getImage() != null){ %>
<tr><td colspan="4"><div align="center">
<img src='/Wiki/file_save/<%=bean.getImage()%>' width="300" height="200"></div></td></tr>
<%} %>
<tr><td colspan="4" height="200"><%=bean.getExplanation()%></td></tr>
</table>

<form name="joinForm" method="post">
<table class="no-border">
<tr class="no-border"><td class="no-border"colspan="5" height="20"></td></tr>
<tr class="no-border"><td class="no-border"width="200"></td>
		<td class="no-border"><input class="button" type="button" value="����" onClick="user('update', <%=bean.getIdx()%>);"></td>
		<td class="no-border"><input class="button" type="button" value="���" onClick="user('list', <%=bean.getIdx()%>);"></td>
		<td class="no-border"><input class="button" type="button" value="����" onClick="user('delete', <%=bean.getIdx()%>);"></td>
		<td class="no-border" width="200"></td></tr>
</table>
</form>

</body>
</html>