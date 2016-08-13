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
<select>
<option value="all">전체</option>
<option value="title">제목</option>
<option value="writer">작성자</option>
<option value="category">카테고리</option>
</select>
<input type="text" name="keyword" size="60">
<input type="submit" value="검색">
</div>
</form>

</body>
</html>


