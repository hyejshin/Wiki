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

<title>글 수정</title>

<script language="javascript">
function showdate(){
    var date = new Date();

    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 0부터 시작하므로 1더함 더함
    var day   = date.getDate();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }
   
    var today = "" + year + "/" + month + "/" + day; 
    
    document.getElementById('date').value = today;
}

function check(){
	var fr = document.getElementById("my_form");
	if(fr.title.value<1){
		alert("제목을 입력하세요.");
		fr.title.focus();
		return false;
	}
	if(fr.modifier.value<1){
		alert("수정자를 입력하세요.");
		fr.modifier.focus();
		return false;
	}
	if(fr.explanation.value<1){
		alert("내용을 입력하세요.");
		fr.explanation.focus();
		return false;
	}
	return true;
}
</script>

</head>
<body onload="showdate();">
<h2>글 수정</h2>

<%BoardBean bean = (BoardBean)request.getAttribute("Bean");%>
<form id="my_form" method="post" action="BoardUpdate.do" 
					enctype="multipart/form-data" onsubmit="return check();">
<input type="hidden" name="idx" value=<%=bean.getIdx()%>>
<input type="hidden" name="writerEmail" value=<%=bean.getWriterEmail()%>>
<table>
<tr><th>글 제목</th><td colspan="3"><input type="text" name="title" size="90" value='<%=bean.getTitle()%>'></td></tr>
<tr><th>작성자</th> <td><input type="text" name="writer" size="28" value=<%=bean.getWriter()%>></td>
	<th>작성일</th> <td><input type="text" name="writeDate" size="28" value=<%=bean.getWriteDate()%>></td></tr>
	<tr><th>수정자</th> <td><input type="text" name="modifier" size="28" value=<%=bean.getModifier()%>></td>
	<th>수정일</th> <td><input type="text" name="modifyDate" size="28" id="date"></td></tr>
	<tr><th>카테고리</th><td colspan="3"><input type="text" name="category" size="90" value=<%=bean.getCategory()%>></td></tr>
<tr><th>이미지</th><td colspan="3"><input type="file" name="file" size="70" value=<%=bean.getImage()%>></td></tr>
<tr><td colspan="4"><textarea rows="15" cols="118" name="explanation"><%=bean.getExplanation()%></textarea></td></tr>
</table>

<!-- 버튼부분 -->
<table class="no-border">
<tr class="no-border"><td class="no-border"colspan="3" height="20"></td></tr>
<tr class="no-border"><td class="no-border"width="300"></td>
		<td class="no-border"><input class="button" type="submit" value="확인"></td>
		<td class="no-border" width="100"></td>
		<td class="no-border"><input class="button" type="button" value="취소" 
			onClick="location.href='BoardUpdateCancel.do?idx=<%=bean.getIdx()%>';"></td>
		<td class="no-border" width="300"></td></tr>
</table>
</form>

</body>
</html>