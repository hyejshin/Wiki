<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" 	href="http://fonts.googleapis.com/earlyaccess/hanna.css">
<link rel="stylesheet" type="text/css" 	href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css">

<%@include file="../top.jsp"%>

<style type="text/css">
	#title {
		margin-top: 100px;
		font-family: 'hanna', sans-serif;
		font-size: 60pt;
	}
	div {
		text-align: center;
	}
</style>
<title>Home</title>
</head>
<body>
<div id="title">Wiki <img src="./file_save/ibm_watson.png" width="150"></div> 
<br><br>

<form method="post" action="/Wiki/BoardServlet?actionMode=SEARCH">
<div>
<select name="option">
<option value="all">��ü</option>
<option value="title">����</option>
<option value="writer">�ۼ���</option>
<option value="category">ī�װ�</option>
</select>
<input type="text" name="keyword" size="60">
<input type="submit" value="�˻�">
</div>
</form>

</body>
</html>


