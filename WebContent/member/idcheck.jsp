<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>아이디 중복 체크</title>
<script type="text/javascript" src="script/member.js"></script>

</head>
<body>
	<h2>아이디 중복확인</h2>
	<form action="idCheck.do" method="get" name="frm">
		아이디 <input type=text name="user_id">
			<input type=submit value="중복체크">
		<br>
			<c:if test="${result==1}">
				<script type="text/javascript"> opener.document.frm.user_id.value="";
				</script>
				${user_id}는 이미 사용 중인 아이디 입니다.
			</c:if>
			<c:if test="${result==-1}">
				${user_id}는 사용 가능한 아이디입니다.
				<input type="button" value="사용" class="cancel" onclick="idok()">
			</c:if>
	</form>
</body>
</html>