<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<link rel="stylesheet" type="text/css" 	href="http://fonts.googleapis.com/earlyaccess/hanna.css">
<link rel="stylesheet" type="text/css" 	href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css">

<title>Wiki</title>

<style type="text/css">
	#hello {
		text-align: left;
	}
	#menu {
		text-align: right;
	}
	body {
		font-family: 'Nanum Gothic', sans-serif;
		font-size: 13pt;
	}
</style>

</head>
<body>

<%if(session.getAttribute("loginUser") != null){%>
	<div id="hello"> �ȳ��ϼ���. ${loginUser.user_name}��:)</div>
<%} %>

<div id="menu">
<!-- ��ܿ� ���̴� �޴�  -->
<a href="/Wiki/home.jsp">Home</a>
<%if(session.getAttribute("loginUser") == null){%>
	<a href="login.do"> login </a><%
} else{%>
	<a href="logout.do"> logout </a><% %><br>
	<a href="memberUpdate.do"> ȸ���������� </a><% %>
	<a href="withdrawMember.do"> ȸ��Ż�� </a>
<%}%>

</div>

</body>
</html>