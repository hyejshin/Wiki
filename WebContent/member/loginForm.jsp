<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�α��� ������</title>
<script type = "text/javascript" src="script/member.js"></script>
</head>
<body>
	<h2>�α���</h2>
	<form action="login.do" method="post" name="frm">
		<table>
	 		<tr>
				<td>���̵� </td>
				<td><input type="text" name="user_id" value="${user_id}"></td>
			</tr>
			<tr>
				<td>��й�ȣ</td>
				<td><input type="password" name="user_pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="�α���" onclick="return loginCheck()">&nbsp;&nbsp; 
				<input type="reset" value="���" onclick="location.href='home.jsp'"> &nbsp;&nbsp;
				<input type="button" value="���� �����"	 onclick="location.href='join.do'">
				</td>
			</tr>
			<tr><td colspan="2">${message}</td></tr>
		</table>	
	</form>
</body>
</html>