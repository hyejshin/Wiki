<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table>
<tr><th>name<th></tr>
<%
Connection myConn = null;
Statement stmt = null;
String mySQL = null;
String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
String user = "wiki";
String passwd = "1234";
String dbdriver = "oracle.jdbc.driver.OracleDriver";
Class.forName(dbdriver);
myConn = DriverManager.getConnection(dburl, user, passwd);
stmt = myConn.createStatement();
%>

<%
String s_major = "컴퓨터과학";
mySQL = "select name from STUDENT where major='" + s_major + "'";
ResultSet myResultSet = stmt.executeQuery(mySQL);
if(myResultSet != null){
while(myResultSet.next()){
String name = myResultSet.getString("name");
%>
<tr><td align="center"><%=name%></td></tr>
<%
}
}
%>
</table>
<%
stmt.close();
myConn.close();
%>

</body>
</html>