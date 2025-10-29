<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="com.sist.dao.*" %>
<%
	String cno = request.getParameter("cno");
	if(cno==null)
		cno="1";
	// main에서 코딩
	MusicDAO dao = MusicDAO.newInstance();
	List<String> list = dao.musicListData(Integer.parseInt(cno));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>지니뮤직 Top100</h1>
	<a href="music.jsp?con=1">TOP 100</a>
	<a href="music.jsp?con=2">가요</a>
	<a href="music.jsp?con=3">트롯</a>
	<a href="music.jsp?con=4">OST</a>
	<a href="music.jsp?con=6">Classic</a><p>
	<%
		int i = 1;
		for(String title : list)
		{
	%>
			<%=i %>.<%=title %><br>
	<%
		if(i==50)
		{
	%>
			<hr>
	<%
		}
			i++;
		}
	%>
</body>
</html>