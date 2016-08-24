<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ�� ���� ���� ������</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
<h2> ȸ�� ���� ����</h2>
<%if(session.getAttribute("loginUser") != null){%>
	<div id="hello">  ${loginUser.user_name}�� ȸ�������� �����ϴ� ������ �Դϴ�.</div>
<%} %>
<form action="memberUpdate.do" method="post" name="frm">
	<table>
		<tr>
			<td> �̸� </td>
			<td> <input type="text" name="user_name" size="20" value="${loginUser.user_name}" readonly></td>
		</tr>
		<tr>
			<td> ���̵� </td>
			<td> <input type="text" name="user_id" size="20" value="${loginUser.user_id}" readonly></td>
		</tr>
		<tr>
			<td> ��й�ȣ </td>
			<td> <input type="password" name="user_pw" size="20">*</td>
		</tr>
		<tr height="30">
			<td width="80"> ��й�ȣ Ȯ��</td>
			<td> <input type="password" name="pw_check" size="20">*</td>
		</tr>
		<tr>
			<td> �̸��� </td>
			<td> <input type="text" name="user_email" size="20" value="${loginUser.user_email}"></td>
		</tr>
		<tr>
			<td> ��� </td>
			<td>
			<c:choose>
				<c:when test="${loginUser.admin == 0}">
				<input type="radio" name="admin" value="0" checked="checked"> �Ϲ�ȸ��
				<input type="radio" name="admin" value="1"> ������
				</c:when>
				<c:otherwise>
				<input type="radio" name="admin" value="0"> �Ϲ�ȸ��
				<input type="radio" name="admin" value="1"  checked="checked"> ������
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="Ȯ��" onclick="return joinCheck()"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" value="���" onclick="location.href='login.do'">
			</td>
		</tr>		
	</table>
</form>
</body>
</html>