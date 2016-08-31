<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<%@include file="../top.jsp"%>
<link rel="stylesheet" type="text/css" href="/Wiki/CSS/viewStyle.css">

<title>�� ���</title>

<script language="javascript">
function showdate(){
    var date = new Date();

    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 0���� �����ϹǷ� 1���� ����
    var day   = date.getDate();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }
   
    var today = "" + year + "/" + month + "/" + day; 
    
    document.getElementById('date').value = today;
}

function check(){
	var fr = document.getElementById("my_form");
	if(fr.title.value<1){
		alert("������ �Է��ϼ���.");
		fr.title.focus();
		return false;
	}
	if(fr.writer.value<1){
		alert("�ۼ��ڸ� �Է��ϼ���.");
		fr.writer.focus();
		return false;
	}
	if(fr.explanation.value<1){
		alert("������ �Է��ϼ���.");
		fr.explanation.focus();
		return false;
	}
	return true;
}
</script>

</head>
<body onload="showdate();">
<h2>�� ���</h2>

<form id="my_form" method="post" action="BoardInsert.do" 
			enctype="multipart/form-data" onsubmit="return check();">
<!-- �Խñ� �Է¶� -->
<input type="hidden" name="writerEmail" value="${loginUser.user_email}">
<table>
<tr><th>�� ����</th><td colspan="3"><input type="text" name="title" size="90"></td></tr>
<tr><th>�ۼ���</th> <td><input type="text" name="writer" size="28" value="${loginUser.user_name}"></td>
	<th>�ۼ���</th> <td><input type="text" name="writeDate" id="date" size="28"></td></tr>
	<tr><th>������</th> <td><input type="text" name="modifier" size="28"></td>
	<th>������</th> <td><input type="text" name="modifyDate" size="28"></td></tr>
	<tr><th>ī�װ�</th><td colspan="3"><input type="text" name="category" size="90"></td></tr>
<tr><th>�̹���</th><td colspan="3"><input type="file" name="file" size="70"></td></tr>
<tr><td colspan="4"><textarea rows="15" cols="115" name="explanation"></textarea></td></tr>
</table>

<!-- ��ư�κ� -->
<table class="no-border">
<tr class="no-border"><td class="no-border"colspan="5" height="20"></td></tr>
<tr class="no-border"><td class="no-border"width="200"></td>
		<td class="no-border"><input class="button" type="reset" value="�ٽ��ۼ�"></td>
		<td class="no-border"><input class="button" type="submit" value="���"></td>
		<td class="no-border"><input class="button" type="button" value="���" onClick="history.back(-1);"></td>
		<td class="no-border" width="200"></td></tr>
</table>
</form>

</body>
</html>