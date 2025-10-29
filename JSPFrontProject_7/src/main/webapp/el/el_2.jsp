<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL -> 내장 객체
	--------------
	param -> request.getParameter()
	paramValues -> request.getParameterValues()
	requestScope -> request.getAttribute()
	sessionScop	-> session.getAttribute()
	applicationScope -> application.gettribute()
	${일반변수(X)}
	EL -> VO 출력
	
 --%>
<%
	String name = "홍길동";
	request.setAttribute("name1", "홍길동");
	session.setAttribute("name2", "심청이");
	// id 비교
	application.setAttribute("name3", "박문수");
	// 전체에서 공유
	
	/* 
		${key이름} 
		-> Scope가 생략이 가능
			request = session = application
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름 :${name1 }
	이름 :${name2 }
	이름 :${name3 }
</body>
</html>