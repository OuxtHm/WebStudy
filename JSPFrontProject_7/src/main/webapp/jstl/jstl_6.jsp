<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%--
	1. 숫자 변환 : DecimalFormat
				<fmt:formatNumber>
	2. 날짜 변환 : SimpleDateFormat
				<fmt:formatDate>
 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자바 : 날짜</h2>
	<%
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		// -> 오라클 / MySQL -> TO_CHAR / DATE_FORMAT
	%>
	<%=today %>
	<h2>JSTL</h2>
	<c:set var="today" value="<%=date %>"/>
	<fmt:formatDate value="${today }" pattern="yyyy-MM-dd"/>
	<h2>자바:숫자변환</h2>
	<%
		int a = 12345678;
		DecimalFormat d = new DecimalFormat("##,###,###");
		String s = d.format(a);
	%>
	<%=s %>
	<h2>JSTL</h2>
	<c:set var="a" value="<%=a %>"/>
	<fmt:formatNumber value="${a }" type="currency"/>
</body>
</html>