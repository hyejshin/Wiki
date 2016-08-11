<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*, boardBean.*" %>
 	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" 	href="http://fonts.googleapis.com/earlyaccess/hanna.css">
<link rel="stylesheet" type="text/css" 	href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css">


<style type="text/css">
	body {
		font-family: 'Nanum Gothic', sans-serif;
		font-size: 13pt;
	}
	h2 {
		font-family: 'hanna', sans-serif;
		text-align: center;
	}
	
	table {
		width: 800px;
		margin-left: auto;
		margin-right: auto;
	}
	table, th, td{
		border: 1px solid #848484;
	}
	.no-border{
		border: 0px;
	}
	
	th {
		width: 150px;
		height: 30px;
		text-align: center;
		background-color: #81DAF5;
	}
	
	.button{
		width: 100px;
		height: 30px;
		font-size: 13pt;
		font-family: 'Nanum Gothic', sans-serif;
	}
</style>


<title>글 보기</title>
</head>
<body>
<h2>글 보기</h2>
<jsp:useBean id="boardMgr" class="boardBean.BoardMgr"/>
<% 
Board info = null;
info = boardMgr.getBoardInfo(46);

String filePath = application.getRealPath("") + "\\" + info.getImage();
%>
<table>
<tr><th>글 제목</th><td colspan="3"><%=info.getTitle()%></td></tr>
<tr><th>등록자</th> <td width="230"><%=info.getWriter()%></td> <th>등록일</th> <td><%=info.getWriteDate()%></td></tr>
<tr><th>수정자</th> <td width="230"><%=info.getModifier()%></td> <th>최종수정일</th> <td><%=info.getModifyDate()%></td></tr>
<tr><td colspan="4"><div align="center"><img src="./"<%=filePath%> width="400"></div></td></tr>
<tr><td colspan="4"><%=filePath%><%=info.getExplanation()%></td></tr>
</table>
<table class="no-border">
<tr class="no-border"><td class="no-border"colspan="5" height="20"></td></tr>
<tr class="no-border"><td class="no-border"width="200"></td>
		<td class="no-border"><input class="button" type="reset" value="수정하기"></td>
		<td class="no-border" width="50"></td>
		<td class="no-border"><input class="button" type="submit" value="삭제"></td>
		<td class="no-border" width="200"></td></tr>
</table>

</body>
</html>