package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
	인터페이스 / 추상 클래스
	------------------
	-> 여러 개의 관련된 클래스를 모아서 관리
	-> 변수 여러개 / 클래스 클래스
		-------	  ----------
		배열명		인터페이스
		-> 한 개의 이름 으로 통합
		-> if을 여러개 사용 소스가 많이 길어진다
			-> Map
	1. 사용자 요청
		-> <a>, <form>, 자바스크립트의 이벤트 이용
		-> ajax / vue : 비동기
		  -----   ---
		  |			|-> 일반 Javascript
		  |-> 서버와 연결 -> fetch, axios
		  				  ------------- 비동기 전환
	2. Controller(Servlet -> DispatcherServlet)
		-> 1) 사용자 요청을 받는다
			  -------- URL
			  a.jsp?id=admin -> GET
			  a.jsp -> POST -> id=admin : 데이터를 감춘다
		   2) 처리하는 Model을 찾는다
		   			------
		   			데이터베이스 연동 -> 결과값을 브라우저 전송
		   			--------------------------------
		   			| 브라우저(jsp)로 전송
		   			  ---------- request / session
		   			  -> 한번 사용, JSP파일 한 개에서만 사용 : request
		   			  		request.setAttribute(key,object)
		   			  			-> JSP에서만 출력
		   			  			-> Java JavaScript -> JSON / XML
		   			  				|		 |
		   			  				----------
		   			  				-> 일반 데이터 (int, String)
		   			  				-> ~VO / List
		   			  					|		|
		   			  					{}		[]
		   			  					--		---
		   			  					객체 표현법
		   			  					-> json-simple
		   			  					-> jackson	
		   			  -> 모든 JSP에서 사용 : session
			3) Model이 가지고 있는 메소드를 이용해서 기능 처리
			4) 출력할 JSP를 찾아서 요청 결과값을 전송
			----------------------------------
				Servlet -> JSP
						request가 초기화
					-------------- request 유지 : forward
					|			|	
		브라우저 ---- 자바(Model) ------ 오라클(DAO)
			|		|
			---------
				| Controller : Java + HTML
		JSP / JavaScript : 결과값을 받아서 브라우저에 화면 출력
		Controller : 요청을 받아서 결과값을 브라우저로 전송
		Model
		DAO
		----------- 요청처리
		-> 단점
			-> Controller에 의존(집중)
				| 스프링6 -> 도메인 Controller
				---------------------------
					| 서버에 부담
					  --------- 서버를 나눠서 작업(MSA)
*/
public interface Model {
	// 사용자 요청시에 처리하는 기능 -> 기능 처리가 다르다
	public String excute(HttpServletRequest request, HttpServletResponse response);
}
