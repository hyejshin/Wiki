<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<%@include file="../top.jsp"%>
<link rel="stylesheet" type="text/css" href="../CSS/viewStyle.css">

<title>글 등록</title>
</head>
<body>
<h2>글 등록</h2>

<form method="post" action="/Wiki/BoardServlet?actionMode=INSERT">
<!-- 게시글 입력란 -->
<table>
<tr><th>글 제목</th><td colspan="3"><input type="text" name="title" size="90"></td></tr>
<tr><th>작성자</th> <td><input type="text" name="writer" size="30"></td>
	<th>작성일</th> <td><input type="text" name="writeDate" size="30"></td></tr>
	<tr><th>수정자</th> <td><input type="text" name="modifier" size="30"></td>
	<th>수정일</th> <td><input type="text" name="modifyDate" size="30"></td></tr>
	<tr><th>카테고리</th><td colspan="3"><input type="text" name="category" size="80"></td></tr>
<tr><th>이미지</th><td colspan="3"><input type="file" name="file" size="80"></td></tr>
<tr><td colspan="4"><textarea rows="20" cols="115" name="explanation"></textarea></td></tr>
</table>

<!-- 버튼부분 -->
<table class="no-border">
<tr class="no-border"><td class="no-border"colspan="5" height="20"></td></tr>
<tr class="no-border"><td class="no-border"width="200"></td>
		<td class="no-border"><input class="button" type="reset" value="다시작성"></td>
		<td class="no-border" width="50"></td>
		<td class="no-border"><input class="button" type="submit" value="등록"></td>
		<td class="no-border" width="200"></td></tr>
</table>
</form>

</body>
</html>