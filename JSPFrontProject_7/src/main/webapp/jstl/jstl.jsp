<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%
	String msg="Hello JSTL";
/* 
	날짜 / 숫자 = 오라클
	문자 관련 = Model
	<c:forEach> <c:if> <c:choose>
	화면이동 -> _ok
*/
%>

<c:set var="msg" value="Hello JSTL"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>자바이 String 클래스의 메소드</h1>
	<%= msg.length() %><br>
	<%=msg.substring(0,5) %><br>
	<%=msg.replace("l", "k") %><br>
	<h1>JSTL</h1>
	${fn:length(msg) }<br>
	${fn:substring(msg,0,5) }<br>
	${fn:replace(msg, 'l', 'k') }<br>
</body>
</html>