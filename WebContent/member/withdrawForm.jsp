<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ��Ż�� ������</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
<form action="withdrawMember.do" method="post" name="frm">
	<table>
		<tr height="30">
			<td width="80"> ��й�ȣ Ȯ��</td>
			<td> <input type="password" name="pw_check" size="20"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="Ż��" onclick="return pwCheck()"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="���" onclick="location.href='home.jsp'">
			</td>
		</tr>		
	</table>
</form>
</body>
</html>