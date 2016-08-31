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

<title>글 보기</title>

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
<h2>글 보기</h2>

<% 

//해당 글이 잘 보여지는지 테스트
//BoardImpl bl = new BoardImpl();
//BoardBean bean =  bl.bSelect(21);

BoardBean bean = (BoardBean)request.getAttribute("Bean");

//이미지가 저장된 경로
String savePath = request.getServletContext().getRealPath("file_save");
String image = savePath + "\\" + bean.getImage();
 %>
<table>
<tr><th>글 제목</th><td colspan="3"><%=bean.getTitle()%></td></tr>
<tr><th>등록자</th> <td width="230"><%=bean.getWriter()%></td> <th>등록일</th> <td><%=bean.getWriteDate()%></td></tr>
<tr><th>수정자</th> <td width="230"><%=bean.getModifier()%></td> <th>최종수정일</th> <td><%=bean.getModifyDate()%></td></tr>
<tr><th>카테고리</th><td colspan="3"><%=bean.getCategory()%></td></tr>
<tr><td colspan="4" height="200">
<% // 사진이 있을 경우에만 이미지 출력
if (bean.getImage() != null){ %><img src='/Wiki/file_save/<%=bean.getImage()%>' width="300" height="200">
<%}%> <%=bean.getExplanation()%></td></tr>
</table>

<form name="joinForm" method="post">
<table class="no-border">
<tr class="no-border"><td class="no-border"colspan="5" height="20"></td></tr>
<tr class="no-border"><td class="no-border"width="200"></td>
		<td class="no-border"><input class="button" type="button" value="수정" onClick="user('update', <%=bean.getIdx()%>);"></td>
		<td class="no-border"><input class="button" type="button" value="목록" onClick="user('list', <%=bean.getIdx()%>);"></td>
		<td class="no-border"><input class="button" type="button" value="삭제" onClick="user('delete', <%=bean.getIdx()%>);"></td>
		<td class="no-border" width="200"></td></tr>
</table>
</form>

</body>
</html>