<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1>JSP 지시자</h1>
			<table class="table">
				<tr>
					<td colspan="2">
						JSP페이지에 대한 전반적인 설정이나 동작방식을 지시하는 명령문
					</td>
				</tr>
				<tr>
					<th colspan="2">지시자 종류</th>
				</tr>
				<tr>
					<td>page</td>
					<td>JSP파일의 전체 속성을 설정</td>
				</tr>
				<tr>
					<td>include</td>
					<td>JSP의 특정 영역에 다른 JSP를 포함</td>
				</tr>
				<tr>
					<td>taglib</td>
					<td>태그를 이용하여 자바의 제어문, 화면이동, 변환</td>
				</tr>
			</table>
			<h2>page 지시자 속성</h2>
			<table class="table">
				<tr>
					<td colspan=3>
						기본 형태 : &lt;%@ page 속성="값" %&gt;
					</td>
				</tr>
				<tr>
					<td>contentType</td>
					<td>문자 설정 / 브라우저 출력</td>
					<td>
					&lt;%@ page contentType="text/html;charset=UTF-8" %&gt;<br>
					text/xml : xml변환, text/plain : json변환
					</td>
				</tr>
				<tr>
					<td>import</td>
					<td>java 클래스 라이브러리 읽기</td>
					<td>
					&lt;%@ page import="java.util.*" %&gt;<br>
					&lt;%@ page import="java.util.*, java.io.*" %&gt;<br>
					&lt;%@ page import="java.util.*" %&gt;<br>
					&lt;%@ page import="java.io.*" %&gt;
					</td>
				</tr>
				<tr>
					<td>errorPage</td>
					<td>오류 발생 시 이동할 JSP파일을 지정</td>
					<td>
					&lt;%@ page errorPage="error.jsp" %&gt;<br>
					404 : 서버에 파일이 없는 경우<br>
					500 : 자바소스 오류가 있는 경우(데이터베이스)<br>
					403 : 접근 거부<br>
					415 : GET/POST 처리 방식이 틀린 경우(Spring)<br> 
					400 : Bad Request : 매개변수가 잘못 설정<br>
					200 : 정상 수행 : 화면에 출력
					</td>
				</tr>
				<tr>
					<td>buffer</td>
					<td>HTML을 저장하는 메뢰 크기(출력버퍼)</td>
					<td>
					&lt;%@ page buffer="16kb" %&gt;<br>
					8kb가 default<br>
					브라우저에 읽어가면 자동 flush : 접속자마다 한 개만 생성<br>
					autoFlush=true -> 자동화 처리가 된다
					</td>
				</tr>
			</table>
			<h2>include 지시자</h2>
			<table class="table">
				<tr>
					<td>
						기본 형식 : &lt;%@ include file="파일명"  %&gt;<br>
					</td>
				</tr>
				<tr>
					<td>
						정적 페이지 -> 파일명을 변경 할 수 없다 : 한 개 지정이 가능<br>
						포함된 모든 JSP를 모아서 한 번에 컴파일을 한다
					</td>
				</tr>
			</table>
			<h2>taglib</h2>
			<table class="table">
				<tr>
					<td>
						기본 형식 : &lt;%@ taglib prefix="접두어" uri=""" %&gt;<br>
					</td>
				</tr>
				<tr>
					<td>
						***core : 제어문, 변수선언, 화면이동<br>
						***fn : 메소드 -> 지원하는 String<br>
						***fmt : 변환 (날짜, 숫자)<br>
						sql : 데이터베이스 연동<br>
						xml : XML 파싱<br>
						사용처 -> MVC 구조에서 주로 사용(Sprig)<br>
						prefix="c" / prefix="fn" <br>
						&lt;c: , &gt;fn:
						&lt;c:forEach &gt;
						&lt;c:if &gt;
						&lt;c:choose &gt;
						&lt;c:redirect &gt;
						&lt;c:url &gt;
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>